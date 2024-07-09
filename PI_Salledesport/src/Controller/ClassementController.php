<?php

namespace App\Controller;

use App\Repository\AbonnementRepository;
use App\Repository\CoachRepository;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ClassementController extends AbstractController
{
    /**
     * @Route("/classement", name="classement")
     */
    public function index(CoachRepository $coachRepository, AbonnementRepository $abbs, FlashyNotifier $flashy) : Response
    {
        $flashy->warning('Classement de nos entraineurs selon le nombre des abonnés');
        return $this->render('classement/classement.html.twig',
            ['coach' => $coachRepository->findAll(),
                'abbs' => $abbs->findAll(),
            ]);
    }
}
