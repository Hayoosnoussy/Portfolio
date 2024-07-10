<?php

namespace App\Controller;

use App\Entity\Compte;
use App\Entity\Comptecoach;
use App\Form\CompteType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

/**
 * @Route("/json/utils")
 */
class JsonUtilsController extends AbstractController
{

    /**
     * @Route("/jsonlogin", name="jsonlogin", methods={"POST"})
     */
    public function login(Request $request): Response
    {
      /*  return $this->json([
                'user' => $this->getUser() ? $this->getUser()->getId() : null]
        );
    */
$role ='';
        $user = $this->getUser();
        if ( in_array('ROLE_Client',$user->getRoles())) {
            $role ='ROLE_CLIENT';
    } else {
            if ( in_array('ROLE_Coach',$user->getRoles())) {
                $role ='ROLE_COACH';
        } else

                if ( in_array('ROLE_ADMIN',$user->getRoles())) {
                    $role ='ROLE_ADMIN';
                }
        }
      //  echo $request;
     //   console.log("Message here");
        $repository = $this->getDoctrine()->getRepository(Compte::class);
        $compte = $repository->findOneBy(array('login' => $user->getUsername()));

        return $this->json([
            'username' => $compte->getUsername(),
            'role' => $role,
            'nom' => $compte->getNom(),
            'login' => $compte->getLogin(),
            'idcompte'=>$compte->getIdCompte(),
            'email' =>$compte->getEmail(),
            'prenom' =>$compte->getPrenom(),
            'ville' =>$compte->getVille(),
            'password' =>$compte->getPassword(),
            'aboutme' =>$compte->getAboutme(),
            'adresse' =>$compte->getAdresse(),
            'imgurl' =>$compte->getImgurl(),
            'telephone' =>$compte->getTelephone(),
            'age' =>$compte->getAge(),
        ]);


    }


}