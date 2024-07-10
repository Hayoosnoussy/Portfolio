<?php

namespace App\Controller;

use App\Entity\Compte;
use App\Entity\Compteclient;
use App\Form\CompteclientType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

/**
 * @Route("/json/compteclient")
 */
class JsonCompteClientController extends AbstractController
{
    /**
     * @Route("/", name="compteclient_index", methods={"GET"})
     */
    public function index(NormalizerInterface $Normalizer): Response
    {
        $compteclients = $this->getDoctrine()
            ->getRepository(Compteclient::class)
            ->findAll();

        $jsonContent = $Normalizer->normalize($compteclients,'json',['groups'=>'post:read']);
        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/new", name="compteclient_new", methods={"POST"})
     */
    public function new(Request $request,NormalizerInterface $Normalizer): Response
    {
        $compte = $this->getDoctrine()
            ->getRepository(Compte::class)
            ->findOneBy(['idcompte'=>$request->get('compteLogin')]);

        $compteclient = new Compteclient();
        $compteclient->setComptelogin($compte);
       if($request->get('exceptionMedicale')) $compteclient->setExceptionmedicale($request->get('exceptionMedicale'));
       if ($request->get('poids')) $compteclient->setPoids($request->get('poids'));
       if ($request->get('taille')) $compteclient->setTaille($request->get('taille'));


            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($compteclient);
            $entityManager->flush();

        $jsonContent = $Normalizer->normalize($compteclient,'json',['groups'=>'post:read']);
        return new Response(json_encode($jsonContent));

    }

    /**
     * @Route("/visite/{comptelogin}", name="compteclient_show", methods={"GET"})
     */
    public function show(Request $request,NormalizerInterface $Normalizer): Response
    {
        $compteclient = $this->getDoctrine()
            ->getRepository(Compteclient::class)
            ->findOneBy(['comptelogin'=>$request->get('comptelogin')]);

        $jsonContent = $Normalizer->normalize($compteclient,'json',['groups'=>'post:read']);
        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/{comptelogin}/edit", name="compteclient_edit", methods={"POST"})
     */
    public function edit(Request $request,NormalizerInterface $Normalizer): Response
    {
        $compteclient = $this->getDoctrine()
            ->getRepository(Compteclient::class)
            ->findOneBy(['comptelogin'=>$request->get('comptelogin')]);

        if($request->get('exceptionMedicale')) $compteclient->setExceptionmedicale($request->get('exceptionMedicale'));
        if ($request->get('poids')) $compteclient->setPoids($request->get('poids'));
        if ($request->get('taille')) $compteclient->setTaille($request->get('taille'));
            $this->getDoctrine()->getManager()->flush();

        $jsonContent = $Normalizer->normalize($compteclient,'json',['groups'=>'post:read']);
        return new Response("updated ".json_encode($jsonContent));


    }

    /**
     * @Route("/delete/{idcompteclient}", name="compteclient_delete")
     */
    public function delete(Request $request,NormalizerInterface $Normalizer): Response
    {
        $compteclient = $this->getDoctrine()
            ->getRepository(Compteclient::class)
            ->findOneBy(['idcompteclient'=>$request->get('idcompteclient')]);

            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($compteclient);
            $entityManager->flush();

        $jsonContent = $Normalizer->normalize($compteclient,'json',['groups'=>'post:read']);
        return new Response("deleted ".json_encode($jsonContent));

    }
}
