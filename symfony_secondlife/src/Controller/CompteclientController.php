<?php

namespace App\Controller;

use App\Entity\Compteclient;
use App\Form\CompteclientType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/compteclient")
 */
class CompteclientController extends AbstractController
{
    /**
     * @Route("/", name="compteclient_index", methods={"GET"})
     */
    public function index(): Response
    {
        $compteclients = $this->getDoctrine()
            ->getRepository(Compteclient::class)
            ->findAll();

        return $this->render('compteclient/index_front.html.twig.twig', [
            'compteclients' => $compteclients,
        ]);
    }

    /**
     * @Route("/new", name="compteclient_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $compteclient = new Compteclient();
        $form = $this->createForm(CompteclientType::class, $compteclient);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($compteclient);
            $entityManager->flush();

            return $this->redirectToRoute('compteclient_index');
        }

        return $this->render('compteclient/new.html.twig', [
            'compteclient' => $compteclient,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idcompteclient}", name="compteclient_show", methods={"GET"})
     */
    public function show(Compteclient $compteclient): Response
    {
        return $this->render('compteclient/show.html.twig', [
            'compteclient' => $compteclient,
        ]);
    }

    /**
     * @Route("/{idcompteclient}/edit", name="compteclient_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Compteclient $compteclient): Response
    {
        $form = $this->createForm(CompteclientType::class, $compteclient);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('base');
        }

        return $this->render('compteclient/edit.html.twig', [
            'compteclient' => $compteclient,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idcompteclient}", name="compteclient_delete", methods={"POST"})
     */
    public function delete(Request $request, Compteclient $compteclient): Response
    {
        if ($this->isCsrfTokenValid('delete'.$compteclient->getIdcompteclient(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($compteclient);
            $entityManager->flush();
        }

        return $this->redirectToRoute('compteclient_index');
    }
}
