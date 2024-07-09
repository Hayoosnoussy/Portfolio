<?php


namespace App\Controller;

use App\Repository\AbonnementRepository;
use App\Repository\CoachRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
class DefaultController extends AbstractController
{
    function index(CoachRepository $coachRepository, AbonnementRepository $abbs): response
    {


        return $this->render('index_front.html.twig',
            ['coach' => $coachRepository->findAll(),
                'abbs' => $abbs->findAll(),
            ]);
    }
    function admin(){
        return $this->render('index_back.html.twig');
    }
}