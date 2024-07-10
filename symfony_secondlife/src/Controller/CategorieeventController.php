<?php

namespace App\Controller;

use App\Entity\Categorieevent;
use App\Form\CategorieeventType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

/**
 * @Route("/EventAdmin/categorieevent")
 */
class CategorieeventController extends AbstractController
{
    /**
     * @Route("/", name="categorieevent_index", methods={"GET"})
     */
    public function index(NormalizerInterface $Nomalizer)
    {
        $categorieevents = $this->getDoctrine()
            ->getRepository(Categorieevent::class)
            ->findAll();

        $jsonContent = $Nomalizer->normalize($categorieevents,'json',["groups"=>'post:read']);
        return new Response(json_encode($jsonContent));


        return $this->render('categorieevent/index.html.twig', [
            'categorieevents' => $categorieevents,
        ]);
    }

    /**
     * @Route("/new", name="categorieevent_new", methods={"GET"})
     */
    public function new(Request $request,NormalizerInterface $Nomalizer)
    {
        $categorieevent = new Categorieevent();
        $form = $this->createForm(CategorieeventType::class, $categorieevent);
        $form->handleRequest($request);

            $entityManager = $this->getDoctrine()->getManager();

            $categorieevent->setCategoriee($request->get("categoriee"));
            $categorieevent->setNamecat($request->get("namecat"));
            $categorieevent->setDescatevent($request->get("descatevent"));

        $entityManager->persist($categorieevent);
            $entityManager->flush();
        $jsonContent = $Nomalizer->normalize($categorieevent,'json',["groups"=>'post:read']);
        return new Response(json_encode($jsonContent));



            return $this->redirectToRoute('categorieevent_index');



        return $this->render('categorieevent/new.html.twig', [
            'categorieevent' => $categorieevent,
            'form' => $form->createView(),
        ]);

    }

    /**
     * @Route("/{categoriee}", name="categorieevent_show", methods={"GET"})
     */
    public function show(Categorieevent $categorieevent,NormalizerInterface $Nomalizer): Response
    {
        $jsonContent = $Nomalizer->normalize($categorieevent,'json',["groups"=>'post:read']);
        return new Response(json_encode($jsonContent));
        return $this->render('categorieevent/show.html.twig', [
            'categorieevent' => $categorieevent,
        ]);
    }

    /**
     * @Route("/edit/{categoriee}", name="categorieevent_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Categorieevent $categorieevent): Response
    {
        $em = $this->getDoctrine()->getManager();

        $categorieevent->setNamecat($request->get("namecat"));
        $categorieevent->setDescatevent($request->get("descatevent"));
        $event=$this->getDoctrine()->getManager()->getRepository(Categorieevent::class)->find($request->get("categoriee"));


        $em->persist($categorieevent);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($categorieevent);
        return new JsonResponse("Evenement a ete modifiee avec succes ");
    }

    /**
     * @Route("/delete/{categoriee}", name="categorieevent_delete", methods={"GET"})
     */
      public function delete(Request $request, Categorieevent $categorieeventCat): Response
    {
        $categorieeventCat->getCategoriee();

        if($categorieeventCat!=null){
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($categorieeventCat);
            $entityManager->flush();
            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted=$serializer->normalize(  $categorieeventCat);
            return new JsonResponse("Categorie a ete supprimer avec succes");
        }

        return  new JsonResponse("id Categorie invalide");
    }
   // return $this->redirectToRoute('categorieevent_index');

}
