<?php

namespace App\Controller;

use App\Entity\Personne;
use App\Form\RegistrationType;
use App\Repository\PersonneRepository;
use App\Repository\UsersRepository;
use App\Security\EmailVerifier;
use App\Security\UserAuthenticator;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Address;
use Symfony\Component\Mime\Message;
use Symfony\Bridge\Twig\Mime\TemplatedEmail;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Security\Guard\GuardAuthenticatorHandler;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;
use Symfony\Contracts\Translation\TranslatorInterface;
use SymfonyCasts\Bundle\VerifyEmail\Exception\VerifyEmailExceptionInterface;

class SecurityController extends AbstractController
{
    private EmailVerifier $emailVerifier;

    public function __construct(EmailVerifier $emailVerifier)
    {
        $this->emailVerifier = $emailVerifier;
    }


    /**
     * @Route("/inscription", name="security_registartion")
     */

    public function registration(Request $request ,UserPasswordEncoderInterface $encoder
        , GuardAuthenticatorHandler $guardHandler
        , UserAuthenticator $authenticator, EntityManagerInterface $entityManager , \Swift_Mailer $mailer1 , MailerInterface $mailer): Response
    {
        $personne = new Personne();
        $form = $this->createForm(RegistrationType::class, $personne);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $personne->getPhoto();
            #tzedet lyoum khater img mouch lezem
            if ($file) {
                $fileName = md5(uniqid()) . '.' . $file->guessExtension();
                try {
                    $file->move(
                        $this->getParameter('images_directory'),
                        $fileName
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }
                $personne->setPhoto($fileName);
            }
            $personne->setRole("User");

            $personne->setRoles(['ROLE_USER']);
            $hash = $encoder->encodePassword($personne , $personne->getPassword());
            $personne->setPassword($hash);
            $personne->setActivationToken(md5(uniqid()));
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($personne);
            $entityManager->flush();



            // generate a signed url and email it to the user
            $this->emailVerifier->sendEmailConfirmation('app_verify_email', $personne,
                (new TemplatedEmail())
                    ->from(new Address('yahiasnoossi@gmail.com', 'Security'))
                    ->to($personne->getEmail())
                    ->subject('Please Confirm your Email')
                    ->htmlTemplate('registration/confirmation_email.html.twig')
            );

            return $guardHandler->authenticateUserAndHandleSuccess(
                $personne,
                $request,
                $authenticator,
                'main' // firewall name in security.yaml
            );
        }

        return $this->render('security/registration.html.twig', [
            'personne' => $personne,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/verify/email", name="app_verify_email")
     */
    public function verifyUserEmail(Request $request, TranslatorInterface $translator): Response
    {
        $this->denyAccessUnlessGranted('IS_AUTHENTICATED_FULLY');

        // validate email confirmation link, sets User::isVerified=true and persists
        try {
            $this->emailVerifier->handleEmailConfirmation($request, $this->getUser());
        } catch (VerifyEmailExceptionInterface $exception) {
            $this->addFlash('verify_email_error', $translator->trans($exception->getReason(), [], 'VerifyEmailBundle'));

            return $this->redirectToRoute('security_registartion');
        }

        // @TODO Change the redirect on success and handle or remove the flash message in your templates
        $this->addFlash('success', 'Your email address has been verified.');

        return $this->redirectToRoute('app_login');
    }



    /**
     * @Route("/activation/{token}", name="activation")
     */
    public function activation($token, PersonneRepository $usersRepository){
        $user = $usersRepository->findOneBy(['activation_token' => $token]);
        if(!$user)
            throw $this->createNotFoundException("Cet utilisateur n'existe pas");

        $user->setActivationToken(null);
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($user);
        $entityManager->flush();
        $this->addFlash('message', 'Vous avez bien activé votre compte');

        return $this->redirectToRoute('app_login');
    }




/**
     * @Route("/login", name="app_login")
     */
    public function login(AuthenticationUtils $authenticationUtils): Response
    {
        // if ($this->getUser()) {
        //     return $this->redirectToRoute('target_path');
        // }

        // get the login error if there is one
        $error = $authenticationUtils->getLastAuthenticationError();
        // last username entered by the user
        $lastUsername = $authenticationUtils->getLastUsername();
        return $this->render('security/login.html.twig', ['last_username' => $lastUsername, 'error' => $error]);
    }

    /**
     * @Route("/logout", name="app_logout")
     */
    public function logout()
    {
    }







}
