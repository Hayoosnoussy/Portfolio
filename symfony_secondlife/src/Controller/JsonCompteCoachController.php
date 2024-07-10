<?php

namespace App\Controller;

use App\Entity\Compte;
use App\Entity\Compteclient;
use App\Entity\Comptecoach;
use App\Entity\Sport;
use App\Form\ComptecoachType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

/**
 * @Route("/json/comptecoach")
 */
class JsonCompteCoachController extends AbstractController
{
    /**
     * @Route("/", name="comptecoach_index", methods={"GET"})
     */
    public function index(NormalizerInterface $Normalizer): Response
    {
        $comptecoaches = $this->getDoctrine()
            ->getRepository(Comptecoach::class)
            ->findAll();

        $jsonContent = $Normalizer->normalize($comptecoaches,'json',['groups'=>'post:read']);
        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/bysport", name="comptecoachbysport", methods={"GET"})
     */
    public function coachBySport($sport,NormalizerInterface $Normalizer): Response
    {
        $comptecoaches = $this->getDoctrine()
            ->getRepository(Comptecoach::class)
            ->findBy($sport);

        $jsonContent = $Normalizer->normalize($comptecoaches,'json',['groups'=>'post:read']);
        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/new", name="comptecoach_new", methods={"POST"})
     */
    public function new(Request $request,NormalizerInterface $Normalizer): Response
    {
        $compte = $this->getDoctrine()
            ->getRepository(Compte::class)
            ->findOneBy(['idcompte'=>$request->get('compteLogin')]);

        $comptecoach = new Comptecoach();
        $comptecoach->setComptelogin($compte);

        $comptecoach = new Comptecoach();
        if($request->get('prixHeure')) $comptecoach->setPrixheure($request->get('prixHeure'));
        if($request->get('idsport')) {
            $sport = $this->getDoctrine()
                ->getRepository(Sport::class)
                ->findOneBy(['idsport'=>$request->get('idsport')]);
            $comptecoach->setSport($sport);
        }

            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($comptecoach);
            $entityManager->flush();

        $jsonContent = $Normalizer->normalize($comptecoach,'json',['groups'=>'post:read']);
        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/visite/{comptelogin}", name="comptecoach_show", methods={"GET"})
     */
    public function show(Request $request,NormalizerInterface $Normalizer): Response
    {
        $comptecoach = $this->getDoctrine()
            ->getRepository(Comptecoach::class)
            ->findOneBy(['comptelogin'=>$request->get('comptelogin')]);

        $jsonContent = $Normalizer->normalize($comptecoach,'json',['groups'=>'post:read']);
        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/{comptelogin}/edit", name="comptecoach_edit", methods={"GET","POST"})
     */
    public function edit(Request $request,NormalizerInterface $Normalizer): Response
    {
        $comptecoach = $this->getDoctrine()
            ->getRepository(Comptecoach::class)
            ->findOneBy(['comptelogin'=>$request->get('comptelogin')]);

        if($request->get('prixHeure')) $comptecoach->setPrixHeure($request->get('prixHeure'));
        if ($request->get('valide')) $comptecoach->setValide($request->get('valide'));
        if($request->get('idsport')) {
            $sport = $this->getDoctrine()
                ->getRepository(Sport::class)
                ->findOneBy(['idsport'=>$request->get('idsport')]);
            $comptecoach->setSport($sport);
        }
        $this->getDoctrine()->getManager()->flush();

        $jsonContent = $Normalizer->normalize($comptecoach,'json',['groups'=>'post:read']);
        return new Response("updated ".json_encode($jsonContent));
    }

    /**
     * @Route("/delete/{comptelogin}", name="comptecoach_delete")
     */
    public function delete(Request $request,NormalizerInterface $Normalizer): Response
    {
        $compteccoach = $this->getDoctrine()
            ->getRepository(Comptecoach::class)
            ->findOneBy(['comptelogin'=>$request->get('comptelogin')]);

        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->remove($compteccoach);
        $entityManager->flush();

        $jsonContent = $Normalizer->normalize($compteccoach,'json',['groups'=>'post:read']);
        return new Response("deleted ".json_encode($jsonContent));
    }
}
