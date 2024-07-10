<?php

namespace App\Controller;

use App\Entity\Compte;
use App\Entity\Compteclient;
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
 * @Route("/json/compte")
 */
class JsonCompteController extends AbstractController
{

    /**
     * @Route("/admin/all", name="compte_index", methods={"GET"})
     */
    public function index(NormalizerInterface $Normalizer): Response
    {
        $comptes = $this->getDoctrine()
            ->getRepository(Compte::class)
            ->findAll();

       $jsonContent = $Normalizer->normalize($comptes,'json',['groups'=>'post:read']);
       return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/new", name="compte_new", methods={"GET","POST"})
     */
    public function new(Request $request,NormalizerInterface $Normalizer,UserPasswordEncoderInterface $passwordEncoder): Response
    {
        $compte = new Compte();

            $entityManager = $this->getDoctrine()->getManager();
        if($request->get('aboutMe')) $compte->setAboutme($request->get('aboutMe'));
        if($request->get('adresse'))$compte->setAdresse($request->get('adresse'));
        if($request->get('age'))$compte->setAge( (int) $request->get('age'));
        if($request->get('email')) $compte->setEmail($request->get('email'));
        if($request->get('telephone')) $compte->setTelephone($request->get('telephone'));
        if($request->get('ville'))  $compte->setVille($request->get('ville'));
        if($request->get('imgUrl'))$compte->setImgurl($request->get('imgUrl'));
        $compte->setNom($request->get('nom'));
        $compte->setIsclosed(false);
        $compte->setLogin($request->get('login'));
        $compte->setPassword($passwordEncoder->encodePassword(
            $compte,
            $request->get('password')
        ));
        $compte->setPrenom($request->get('prenom'));
        $compte->setRoles(array($request->get('roles')));

            $entityManager->persist($compte);
            $entityManager->flush();

        if ( in_array('ROLE_Client',$compte->getRoles())) {
            $compteclient = new Compteclient();
            $compteclient->setComptelogin($compte);
            $compteclient->setTaille(0);
            $compteclient->setPoids(0);
            $entityManager2 = $this->getDoctrine()->getManager();
            $entityManager2->persist($compteclient);
            $entityManager2->flush();
          //  $role ='ROLE_CLIENT';
        } else if
             ( in_array('ROLE_Coach',$compte->getRoles())) {
            $comptecoach = new Comptecoach();
            $comptecoach->setComptelogin($compte);
            $comptecoach->setPrixheure(0);
            $comptecoach->setValide(0);
            $entityManager2 = $this->getDoctrine()->getManager();
            $entityManager2->persist($comptecoach);
            $entityManager2->flush();
            } else
                if ( in_array('ROLE_ADMIN',$compte->getRoles())) {
                    $role ='ROLE_ADMIN';
                }
        return $this->json([
            'username' => $compte->getUsername(),
         //   'role' => $role,
            'nom' => $compte->getNom(),
            'login' => $compte->getLogin(),
            'idcompte'=>$compte->getIdCompte(),
            'email' =>$compte->getEmail(),
            'prenom' =>$compte->getPrenom(),
            'password' =>$compte->getPassword(),
            'ville' =>$compte->getVille(),
            'aboutme' =>$compte->getAboutme(),
            'adresse' =>$compte->getAdresse(),
            'imgurl' =>$compte->getImgurl(),
            'telephone' =>$compte->getTelephone(),
            'age' =>$compte->getAge(),
        ]);
    }







    /**
     * @Route("/visite/{idcompte}", name="compte_show", methods={"GET"})
     */
    public function show(Request $request,NormalizerInterface $Normalizer): Response
    {
        $compte = $this->getDoctrine()
            ->getRepository(Compte::class)
            ->findOneBy(['idcompte'=>$request->get('idcompte')]);
$role='';
        if ( in_array('ROLE_Client',$compte->getRoles())) {
              $role ='ROLE_CLIENT';
        } else if
        ( in_array('ROLE_Coach',$compte->getRoles())) {
            $role ='ROLE_COACH';
        } else
            if ( in_array('ROLE_ADMIN',$compte->getRoles())) {
                $role ='ROLE_ADMIN';
            }
        return $this->json([
            'username' => $compte->getUsername(),
               'role' => $role,
            'nom' => $compte->getNom(),
            'login' => $compte->getLogin(),
            'idcompte'=>$compte->getIdCompte(),
            'email' =>$compte->getEmail(),
            'prenom' =>$compte->getPrenom(),
            'password' =>$compte->getPassword(),
            'ville' =>$compte->getVille(),
            'aboutme' =>$compte->getAboutme(),
            'adresse' =>$compte->getAdresse(),
            'imgurl' =>$compte->getImgurl(),
            'telephone' =>$compte->getTelephone(),
            'age' =>$compte->getAge(),
        ]);
      //  $jsonContent = $Normalizer->normalize($compte,'json',['groups'=>'post:read']);
    //    return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/{idcompte}/edit", name="compte_edit", methods={"POST"})
     */
    public function edit(Request $request, NormalizerInterface $Normalizer, UserPasswordEncoderInterface $passwordEncoder): Response
    {
        $compte = $this->getDoctrine()
            ->getRepository(Compte::class)
            ->findOneBy(['idcompte'=>$request->get('idcompte')]);
        if($request->get('aboutMe')) $compte->setAboutme($request->get('aboutMe'));
        if($request->get('adresse'))$compte->setAdresse($request->get('adresse'));
        if($request->get('age'))$compte->setAge( (int) $request->get('age'));
        if($request->get('email')) $compte->setEmail($request->get('email'));
        if($request->get('telephone')) $compte->setTelephone($request->get('telephone'));
        if($request->get('ville'))  $compte->setVille($request->get('ville'));
        if($request->get('imgUrl'))$compte->setImgurl($request->get('imgUrl'));
        if($request->get('nom'))$compte->setNom($request->get('nom'));
       /* if (!is_null($request->get('password'))) {$compte->setPassword(
           $passwordEncoder->encodePassword(
               $compte, $request->get('password')
           )
       );} */
        if($request->get('prenom'))$compte->setPrenom($request->get('prenom'));

        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->flush();
        $jsonContent = $Normalizer->normalize($compte,'json',['groups'=>'post:read']);

        return new Response("updated".json_encode($jsonContent));
    }

    /**
     * @Route("/delete/{idcompte}", name="compte_delete")
     */
    public function delete(Request $request, NormalizerInterface $Normalizer): Response
    {

        $compte = $this->getDoctrine()
            ->getRepository(Compte::class)
            ->findOneBy(['idcompte'=>$request->get('idcompte')]);
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($compte);
            $entityManager->flush();


        $jsonContent = $Normalizer->normalize($compte,'json',['groups'=>'post:read']);
        return new Response("deleted".json_encode($jsonContent));
    }
}
