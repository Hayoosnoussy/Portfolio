<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Ordonance;
use App\Repository\OrdonanceRepository;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
class OrdonanceController extends AbstractController
{    /**
     * @Route("/get/ordonance/{id}", name="register", methods={"GET"})
     */
    public function index(OrdonanceRepository $ordonanceRepository,SerializerInterface $serializer,$id){
  /*  $ordonance = $ordonanceRepository->findAll();*/
  $repository = $this->getDoctrine()->getRepository(Ordonance::class);
  $ordonance= $repository->findBy(array('consultation'=>$id), null, 8, 0);

    $json=$serializer->serialize($ordonance,'json',['groups'=>'post:read']);
    return new Response($json,200,[
        'Content-Type'=>"application/json"
    ]);


}
}
