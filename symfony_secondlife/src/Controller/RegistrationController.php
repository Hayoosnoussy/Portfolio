<?php

namespace App\Controller;

use App\Entity\Compte;
use App\Entity\Compteclient;
use App\Entity\Comptecoach;
use App\Form\RegistrationFormType;
use App\Security\LoginFormAuthenticator;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Security\Guard\GuardAuthenticatorHandler;

class RegistrationController extends AbstractController
{



    /**
     * @Route("/register", name="app_register")
     */
    public function register(Request $request, UserPasswordEncoderInterface $passwordEncoder, GuardAuthenticatorHandler $guardHandler, LoginFormAuthenticator $authenticator): Response
    {
            if ( $this->getUser() ) {
                return $this->redirect('/');

        } else {
        $user = new Compte();
        $form = $this->createForm(RegistrationFormType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // encode the plain password phase ******************************
            $user->setPassword(
                $passwordEncoder->encodePassword(
                    $user,
                    $form->get('plainPassword')->getData()
                )
            );

            // file upload phase *********************************************
            /** @var UploadedFile $imgurl */
            $imgFile = $form->get('imgurl')->getData();
            // this condition is needed because the 'brochure' field is not required
            // so the PDF file must be processed only when a file is uploaded
            if ($imgFile) {
                $originalFilename = pathinfo($imgFile->getClientOriginalName(), PATHINFO_FILENAME);
                // this is needed to safely include the file name as part of the URL
                $safeFilename = transliterator_transliterate('Any-Latin; Latin-ASCII; [^A-Za-z0-9_] remove; Lower()', $originalFilename);
                $newFilename = $safeFilename.'-'.uniqid().'.'.$imgFile->guessExtension();

                // Move the file to the directory where brochures are stored
                try {
                    $imgFile->move(
                        $this->getParameter('img_directory'),
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }
                // updates the 'brochureFilename' property to store the PDF file name
                // instead of its contents
                $user->setImgurl($newFilename);
            }

            if (($form->get('role')->getData()=='client') ||($form->get('role')->getData()=='0')) {
                $user->setRoles(array("ROLE_Client")) ;
            } else if (($form->get('role')->getData()=='entraineur') ||($form->get('role')->getData()=='1')) {
                $user->setRoles(array("ROLE_Coach")) ;
            }

            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($user);
            $entityManager->flush();


            //RE-RECUPERATION COMPTE
            $user2 = new Compte();
            $user2 =  $this->getDoctrine()
                ->getRepository(Compte::class)
                ->findOneBy(['email' => $user->getEmail() ]);
            //INSERTION COMPTE TYPE
            if (($form->get('role')->getData()=='client') ||($form->get('role')->getData()=='0')) {
                $client = new Compteclient();
                $client->setComptelogin($user2);
                $entityManager = $this->getDoctrine()->getManager();
                $entityManager->persist($client);
                $entityManager->flush();

            } else if (($form->get('role')->getData()=='entraineur') ||($form->get('role')->getData()=='1')) {
                $coach = new Comptecoach();
                $coach->setComptelogin($user2);
                $entityManager = $this->getDoctrine()->getManager();
                $entityManager->persist($coach);
                $entityManager->flush(); }


            return $guardHandler->authenticateUserAndHandleSuccess(
                $user,
                $request,
                $authenticator,
                'main' // firewall name in security.yaml
            );
        }

        return $this->render('registration/register.html.twig', [
            'registrationForm' => $form->createView(),
        ]); }
    }
}
