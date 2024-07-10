<?php

namespace App\Controller;

use App\Entity\Groupe;
use App\Entity\Seance;
use App\Form\GroupeType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Exception\ExceptionInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;

/**
 * @Route("/groupeadmin")
 */
class GroupeController extends AbstractController
{
    /**
     * @Route("/groupe", name="groupe_admin", methods={"GET"})
     */
    public function index(): Response
    {
        $groupes = $this->getDoctrine()
            ->getRepository(Groupe::class)
            ->findAll();

        return $this->render('groupe/index.html.twig', [
            'groupes' => $groupes,
        ]);
    }

    /**
     * @Route("/groupe/new", name="groupe_new", methods={"GET","POST"})
     * @param Request $request
     * @return Response
     */
    public function new(Request $request): Response
    {
        $groupe = new Groupe();
        $form = $this->createForm(GroupeType::class, $groupe);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($groupe);
            $entityManager->flush();

            return $this->redirectToRoute('groupe_admin');
        }

        return $this->render('groupe/new.html.twig', [
            'groupe' => $groupe,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/groupe/{idclass}", name="groupe_show", methods={"GET"})
     * @param Groupe $groupe
     * @return Response
     */
    public function show(Groupe $groupe): Response
    {
        return $this->render('groupe/show.html.twig', [
            'groupe' => $groupe,
        ]);
    }


    /**
     * @Route("/{idclass}/edit", name="groupe_edit", methods={"GET","POST"})
     * @param Request $request
     * @param Groupe $groupe
     * @return Response
     */
    public function edit(Request $request, Groupe $groupe): Response
    {
        $form = $this->createForm(GroupeType::class, $groupe);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('groupe_admin');
        }

        return $this->render('groupe/edit.html.twig', [
            'groupe' => $groupe,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idclass}", name="groupe_delete", methods={"POST"})
     * @param Request $request
     * @param Groupe $groupe
     * @return Response
     */
    public function delete(Request $request, Groupe $groupe): Response
    {
        if ($this->isCsrfTokenValid('delete' . $groupe->getIdclass(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($groupe);
            $entityManager->flush();
        }

        return $this->redirectToRoute('groupe_admin');
    }


    /** @Route("/addgroupejson" , name="addgroupejson", methods={"GET"})
     * @param Request $request
     * @param NormalizerInterface $Nomalizer
     * @return Response
     * @throws ExceptionInterface
     */
    public function addGroupeJson(Request $request, NormalizerInterface $Nomalizer): Response
    {
        $groupe = new groupe();
        $entityManager = $this->getDoctrine()->getManager();
        $groupe->setNomclass($request->get("nomClass"));
        $groupe->setTypeclass($request->get("typeClass"));

        $entityManager->persist($groupe);
        $entityManager->flush();
        $jsonContent = $Nomalizer->normalize($groupe, 'json', ["groups" => 'post:read']);
        return new JsonResponse("Groupe ajouté avec succes");
    }

    /**
     * @Route("/{idclass}/modifgroupejson" , name="modifgroupejson", methods={"GET","POST"})
     * @param Request $request
     * @param SerializerInterface $serializer
     * @return Response
     */
    public function ModifGroupeJson(Request $request, SerializerInterface $serializer): Response
    {
        $content = $request->getContent();
        $data = $serializer->deserialize($content, Groupe::class, 'json');
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($data);
        $entityManager->flush();
        return new Response('Groupe modifié');
    }

    /**
     * @Route("/{idclass}/deletegroupejson" , name="deletegroupejson", methods={"GET","POST"})
     * @param Groupe $groupe
     * @return Response
     */
    public function DeleteGroupeJson(Groupe $groupe): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->remove($groupe);
        $entityManager->flush();
        return new Response('Groupe supprimée');
    }


    /**
     * @Route("/affjson/{id}", name="affjson" )
     * @param $id
     * @param NormalizerInterface $Normalizer
     * @return Response
     * @throws ExceptionInterface
     */
    public function affjson($id, NormalizerInterface $Normalizer): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $groupe = $entityManager
            ->getRepository(Groupe::class)
            ->find($id);
        $jsonContent = $Normalizer->normalize($groupe, 'json', ['groups' => ['groupe:read', 'Object'], "preserve_empty_objects" => true]);

        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/listegroupes", name="listegroupes" , methods={"GET"})
     * @param NormalizerInterface $Normalizer
     * @return Response
     * @throws ExceptionInterface
     */
    public function listegroupes(NormalizerInterface $Normalizer): Response
    {
        $entityManager = $this->getDoctrine();
        $groupe = $entityManager
            ->getRepository(Groupe::class)
            ->findAll();
        $jsonContent = $Normalizer->normalize($groupe, 'json', ['groups' => 'groupe:read']);
        $retour = json_encode($jsonContent);
        return new Response($retour);
    }

    /**
     * @Route("/listeseances", name="listeseances" , methods={"GET"})
     * @param NormalizerInterface $Normalizer
     * @return Response
     * @throws ExceptionInterface
     */
    public function listeseances(NormalizerInterface $Normalizer)
    {
        $entityManager = $this->getDoctrine();
        $list = $entityManager
            ->getRepository(Seance::class)
            ->findAll();
        $jsonContent = $Normalizer->normalize($list, 'json', ['groups' => 'seance:read']);
        $retour = json_encode($jsonContent);
        return new Response($retour);


    }

    /**
     * @Route("/addseancejson" , name="addseancejson", methods={"GET","POST"})
     * @param Request $request
     * @param NormalizerInterface $Normalizer
     * @return Response
     * @throws ExceptionInterface
     */
    public function AddSeanceJson(Request $request, NormalizerInterface $Normalizer): Response
    {
        $Seance = new seance();
        $datedeb=$request->get("dateSeance");
        $entityManager = $this->getDoctrine()->getManager();
        $Seance->setTitreseance($request->get("titreSeance"));
        $Seance->setDescseance($request->get("descSeance"));
        $Seance->setDateseance(new \DateTime($datedeb));
        $Seance->setIdcoach($request->get("idcoach"));
        $Seance->setIdroutine($request->get("idroutine"));
        $Seance->setZeroLikes();
        $Seance->setGroupe($request->get("groupe"));


        $entityManager->persist($Seance);
        $entityManager->flush();
        $jsonContent = $Normalizer->normalize($Seance, 'json', ["groups" => 'seance:read']);
        return new JsonResponse("Séance ajouté avec succes");
    }


    /**
     * @Route("/like/{idseance}" , name="like", methods={"GET"})
     * @param $idseance
     * @return Response
     */
    public function like($idseance): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $seance = $entityManager->getRepository(Seance::class)->find($idseance);
        $seance->incrementLikeCount();
        $entityManager->flush();

        return new JsonResponse("Like avec success");
    }

}
