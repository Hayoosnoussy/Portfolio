<?php

namespace App\Controller;

use App\Entity\Comptecoach;
use App\Form\ComptecoachType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/comptecoach")
 */
class ComptecoachController extends AbstractController
{
    /**
     * @Route("/", name="comptecoach_index", methods={"GET"})
     */
    public function index(): Response
    {
        $comptecoaches = $this->getDoctrine()
            ->getRepository(Comptecoach::class)
            ->findAll();

        return $this->render('comptecoach/index_front.html.twig', [
            'comptecoaches' => $comptecoaches,
        ]);
    }

    /**
     * @Route("/bysport", name="comptecoachbysport", methods={"GET"})
     */
    public function coachBySport($sport): Response
    {
        $comptecoaches = $this->getDoctrine()
            ->getRepository(Comptecoach::class)
            ->findBy($sport);

        return $this->render('comptecoach/index_front.html.twig', [
            'comptecoaches' => $comptecoaches,
        ]);
    }

    /**
     * @Route("/new", name="comptecoach_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $comptecoach = new Comptecoach();
        $form = $this->createForm(ComptecoachType::class, $comptecoach);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($comptecoach);
            $entityManager->flush();

            return $this->redirectToRoute('comptecoach_index');
        }

        return $this->render('comptecoach/new.html.twig', [
            'comptecoach' => $comptecoach,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idcomptecoach}", name="comptecoach_show", methods={"GET"})
     */
    public function show(Comptecoach $comptecoach): Response
    {
        return $this->render('comptecoach/show.html.twig', [
            'comptecoach' => $comptecoach,
        ]);
    }

    /**
     * @Route("/{idcomptecoach}/edit", name="comptecoach_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Comptecoach $comptecoach): Response
    {
        $form = $this->createForm(ComptecoachType::class, $comptecoach);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('base');
        }

        return $this->render('comptecoach/edit.html.twig', [
            'comptecoach' => $comptecoach,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idcomptecoach}", name="comptecoach_delete", methods={"POST"})
     */
    public function delete(Request $request, Comptecoach $comptecoach): Response
    {
        if ($this->isCsrfTokenValid('delete'.$comptecoach->getIdcomptecoach(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($comptecoach);
            $entityManager->flush();
        }

        return $this->redirectToRoute('comptecoach_index');
    }
}
