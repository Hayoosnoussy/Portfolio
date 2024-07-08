<?php

namespace App\Controller;
use App\Repository\PatientRepository;
use App\Entity\Patient;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use FOS\RestBundle\View\View;

class ApiPatientController extends AbstractController
{
    /**
     * @Route("/api2/patient", name="api_patient_get",methods={"GET"})
     * 
     */
    public function index(PatientRepository $patientRepository)
    {
        $repository = $this->getDoctrine()->getRepository(Patient::class);
        $address= $repository->findall();
        $json = json_encode($address);
        $reponse = new Response($json, 200);
            return $reponse;
    }
}
