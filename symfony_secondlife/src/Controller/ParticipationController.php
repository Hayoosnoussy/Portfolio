<?php

namespace App\Controller;

use App\Entity\Compte;
use App\Entity\Participation;
use App\Entity\Seance;
use App\Form\ParticipationType;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Psr\Log\LoggerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/participation")
 */
class ParticipationController extends AbstractController
{
    private $logger;

    /**
     * @Route("/", name="participation_index", methods={"GET"})
     */
    public function index(): Response
    {
        $participations = $this->getDoctrine()
            ->getRepository(Participation::class)
            ->findAll();

        return $this->render('participation/index.html.twig', [
            'participations' => $participations,
        ]);
    }

    /**
     * @Route("/new/{idseance}", name="participation_new", methods={"GET"})
     * @param Request $request
     * @param int $idseance
     * @param FlashyNotifier $flashy
     * @return Response
     */
    public function new(Request $request, int $idseance, FlashyNotifier $flashy): Response
    {
        $participation = new Participation();
        $entityManager = $this->getDoctrine()->getManager();
        $seance = $entityManager->getRepository(Seance::class)->find($idseance);
        $participation->setIdseance($seance);
        $participation->setIdclient("wassim");
        $participation->setIdcoach($seance->getIdcoach());
        $participation->setIdroutine($seance->getIdroutine());
        $participation->setStatus("Participé");
        $flashy->info('Success participation');
        $entityManager->persist($participation);
        $entityManager->flush();
        return $this->redirectToRoute('seance_index');


    }
    /**
     * @Route("/newjson/{idseance}/{IdClient}", name="newjson", methods={"GET"})
     * @param Request $request
     * @param int $idseance
     * @param FlashyNotifier $flashy
     *
     * @return Response
     */
    public function newjson(Request $request, int $idseance, FlashyNotifier $flashy,Compte $IdClient): Response
    {
        $participation = new Participation();
        $entityManager = $this->getDoctrine()->getManager();
        $seance = $entityManager->getRepository(Seance::class)->find($idseance);
        $client = $entityManager->getRepository(Compte::class)->find($IdClient);


        $participation->setIdseance($seance);
        $participation->setIdclient($client);
        $participation->setIdcoach($seance->getIdcoach());
        $participation->setIdroutine($seance->getIdroutine());
        $participation->setStatus("Participé");
        $flashy->info('Success participation');
        $entityManager->persist($participation);
        $entityManager->flush();
        return new JsonResponse("Participation avec success");


    }



    /**
     * @Route("/{id}", name="participation_delete", methods={"POST"})
     * @param Request $request
     * @param Participation $participation
     * @param FlashyNotifier $flashy
     * @return Response
     */
    public function delete(Request $request, Participation $participation, FlashyNotifier $flashy): Response
    {
        if ($this->isCsrfTokenValid('delete' . $participation->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $flashy->info('Element supprimmé');
            $entityManager->remove($participation);
            $entityManager->flush();
        }

        return $this->redirectToRoute('participation_index');
    }
}
