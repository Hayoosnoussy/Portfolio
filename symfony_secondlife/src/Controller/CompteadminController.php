<?php

namespace App\Controller;

use App\Entity\Compteadmin;
use App\Entity\Compteclient;
use App\Form\CompteadminType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/compteadmin")
 */
class CompteadminController extends AbstractController
{
    /**
     * @Route("/", name="compteadmin_index", methods={"GET"})
     */
    public function index(): Response
    {


        $compteadmins = $this->getDoctrine()
            ->getRepository(Compteadmin::class)
            ->findAll();

        return $this->render('compteadmin/index_front.html.twig.twig', [
            'compteadmins' => $compteadmins,
        ]);
    }

    /**
     * @Route("/new", name="compteadmin_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $compteadmin = new Compteadmin();
        $form = $this->createForm(CompteadminType::class, $compteadmin);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($compteadmin);
            $entityManager->flush();

            return $this->redirectToRoute('compteadmin_index');
        }

        return $this->render('compteadmin/new.html.twig', [
            'compteadmin' => $compteadmin,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idcompteadmin}", name="compteadmin_show", methods={"GET"})
     */
    public function show(Compteadmin $compteadmin): Response
    {
        return $this->render('compteadmin/show.html.twig', [
            'compteadmin' => $compteadmin,
        ]);
    }

    /**
     * @Route("/{idcompteadmin}/edit", name="compteadmin_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Compteadmin $compteadmin): Response
    {
        $form = $this->createForm(CompteadminType::class, $compteadmin);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('compteadmin_index');
        }

        return $this->render('compteadmin/edit.html.twig', [
            'compteadmin' => $compteadmin,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idcompteadmin}", name="compteadmin_delete", methods={"POST"})
     */
    public function delete(Request $request, Compteadmin $compteadmin): Response
    {
        if ($this->isCsrfTokenValid('delete'.$compteadmin->getIdcompteadmin(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($compteadmin);
            $entityManager->flush();
        }

        return $this->redirectToRoute('compteadmin_index');
    }
}
