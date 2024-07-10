<?php

namespace App\Controller;

use App\Entity\Compte;
use App\Entity\Compteadmin;
use App\Entity\Compteclient;
use App\Entity\Comptecoach;
use App\Entity\CompteCoachSearch;
use App\Entity\CompteCoachSportSearch;
use App\Form\CompteCoachSearchType;
use App\Form\RegistrationFormType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class BaseController extends AbstractController
{
    private $compteclient = null;
    private $compteadmin = null;
    private $comptecoach = null ;
    private $compteid = 0 ;

public function checkcompteclient(int $id):bool
{
    $compte =  $this->getDoctrine()
        ->getRepository(Compteclient::class)
        ->findOneBy(['comptelogin' => $id ]);
    if ($compte) {return true;} else {return false ;}
}
    public function checkcomptecoach(int $id):bool
    {
        $compte =  $this->getDoctrine()
            ->getRepository(Comptecoach::class)
            ->findOneBy(['comptelogin' => $id ]);
        if ($compte) {return true;} else {return false ;}
    }
    public function checkcompteadmin(int $id):bool
    {
        $compte =  $this->getDoctrine()
            ->getRepository(Compteadmin::class)
            ->findOneBy(['comptelogin' => $id ]);
        if ($compte) {return true;} else {return false ;}
    }

public function setUserExtra(){
    $username =  $this->getUser()->getUsername();
    $this->compteid = ($this->getDoctrine()
        ->getRepository(Compte::class)
        ->findOneBy(['login' => $username] ))->GetIdcompte();


    if (! ($this->checkcompteclient($this->compteid)) == false )
    {
        $this->compteclient =($this->getDoctrine()
            ->getRepository(Compteclient::class)
            ->findOneBy(['comptelogin' => $this->compteid ])) ;

    } else if (! ($this->checkcomptecoach($this->compteid)) == false )
    {
        $this->comptecoach =($this->getDoctrine()
            ->getRepository(Comptecoach::class)
            ->findOneBy(['comptelogin' => $this->compteid ])) ;

    } else if (! ($this->checkcompteadmin($this->compteid)) == false )
    {
        $this->compteadmin =($this->getDoctrine()
            ->getRepository(Compteadmin::class)
            ->findOneBy(['comptelogin' => $this->compteid ])) ;
    }
}

public function setUserRole(){
    if (! ($this->checkcompteclient($this->compteid)) == false )
    {

        $this->getUser()->setRoles(array("ROLE_Client")) ;

    } else if (! ($this->checkcomptecoach($this->compteid)) == false )
    {
        $this->getUser()->setRoles(array("ROLE_Coach")) ;

    } else if (! ($this->checkcompteadmin($this->compteid)) == false )
    {
        $this->getUser()->setRoles(array("ROLE_ADMIN")) ;
    }
    else { $this->getUser()->setRoles(array("ROLE_VIDE")) ;}
}

    /**
     * @Route("/profile", name="profile" , methods={"GET"})
     */
    public function profile(): Response
    {
//SET USER EXTRAS
$this->setUserExtra();
//SET USER ROLE
//$this->setUserRole();

        return $this->render('profiles/profile.html.twig', [
            'controller_name' => 'BaseController', 'compteclient' => $this->compteclient ,
            'comptecoach' => $this->comptecoach , 'compteadmin' => $this->compteadmin
        ]);
    }



    /**
     * @Route("/coachs", name="coachs")
     */
    public function coachsSearch(Request $request): Response
    {

        $comptecoaches=null;

        $repository = $this->getDoctrine()->getRepository(Comptecoach::class);

        $comptecoaches = $this->getDoctrine()
            ->getRepository(Comptecoach::class)
            ->findAll();


        $CompteCoachSearch = new CompteCoachSearch();
        $form = $this->createForm(CompteCoachSearchType::class,$CompteCoachSearch);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()) {
            $var = $CompteCoachSearch->getNom();
            if ($var != ""){
                $query = $repository->createQueryBuilder('a')
                    ->join('a.comptelogin', 'd')
                    ->where('d.nom = :nom')
                    ->setParameter('nom', $var)
                    ->getQuery();
                $comptecoaches = $query->getResult();


          } else {
                return $this->redirectToRoute('coachs');
         }
        }

        return $this->render('profiles/coachs.html.twig', [
            'comptecoaches' => $comptecoaches ,'form' =>$form->createView()
        ]);


    }





    /**
     * @Route("/profilecoach/{idcomptecoach}", name="profilecoach", methods={"GET"})
     */
    public function show(Comptecoach $comptecoach): Response
    {
        $this->setUserExtra();
      //  $this->setUserRole();
        return $this->render('profiles/profilevisitecoach.html.twig', [
            'comptecoach' => $comptecoach, 'compteclient'=>$this->compteclient
        ]);
    }


    /**
     * @Route("/follow/test/{idclient}/{idcoach}",name="following_follow")
     */
    public function follow(Request $request ){
        $em = $this->getDoctrine()->getManager();

       $cc = new Compteclient();
       $cc =  $em ->getRepository(Compteclient::class)
           ->findOneBy(array('idcompteclient' => $request->get('idclient')));
       $cc->setCoachlogin($request->get('idcoach'));
       $em->flush($cc);

        return $this->redirectToRoute('profilecoach',[
            'idcomptecoach' => $request->get('idcoach') ]);
    }


    /**
     * @Route("/unfollow/test/{idclient}",name="following_unfollow")
     */
    public function unfollow(Request $request ){
        $em = $this->getDoctrine()->getManager();

        $cc = new Compteclient();
        $cc =  $em ->getRepository(Compteclient::class)
            ->findOneBy(array('idcompteclient' => $request->get('idclient')));
        $cc->setCoachlogin('0');
        $em->flush($cc);

        return $this->redirectToRoute('coachs');
    }

    /**
     * @Route("/secure-area", name="homepage")
     */
    public function indexAction()
    {

        if($this->getUser()->hasRole('ROLE_ADMIN'))
        {    return $this->redirect($this->generateUrl('compteadmin_index')); }
        elseif( ($this->getUser()->hasRole('ROLE_Client') || ($this->getUser()->hasRole('ROLE_Coach') ) ) )
        {   return $this->redirect($this->generateUrl('base')); }
        throw new \Exception(AccessDeniedException::class);
    }

    /**
     * @Route("/", name="base")
     */
    public function indexfront(): Response
    {
        if ($this->getUser()) {
        if (  in_array("ROLE_ADMIN",$this->getUser()->getRoles()) ) {
            return $this->render('back/index.html.twig', [
                'controller_name' => 'BaseController',
            ]); }
        else {
            return $this->render('front/index.html.twig', [
                'controller_name' => 'BaseController',
            ]);
        }
        } else {
        return $this->render('front/index.html.twig', [
            'controller_name' => 'BaseController',
        ]); }
    }
    /**
     * @Route("/admin", name="backbase")
     */
    public function indexback(): Response
    {
        return $this->render('back/index.html.twig', [
            'controller_name' => 'BaseController',
        ]);
    }






    /**
     * @Route("/admin/profile", name="admin_profile")
     */
    public function admin_profile(): Response
    {
        return $this->render('back/profile.html.twig', [
            'controller_name' => 'BaseController',
        ]);
    }


    /**
     * @Route("/admin/dashboard", name="dashboard")
     */
    public function dashboard(Request $request):Response
    {
        $form1 = $this->createFormBuilder()
            ->add('email', TextType::class)
            ->getForm();

        $form2 = $this->createFormBuilder()
            ->add('email', TextType::class)
            ->getForm();

        $form1->handleRequest($request);


      $email = $form1->get('email')->getData();
        if ($form1->isSubmitted() ) {
            return $this->redirectToRoute('addadmin',
                array(  'mailcompte' => $email )
            );
        }

        $form2->handleRequest($request);
        $email2 = $form2->get('email')->getData();
        if ($form2->isSubmitted()  ) {
            return $this->redirectToRoute('compte_delete_admin',
                array(  'mailcompte' => $email2 )
            );
        }


        return $this->render('back/dashboard.html.twig', [
            'controller_name' => 'BaseController',
            'form1' => $form1->createView(),
            'form2' => $form2->createView(),
        ]);
    }






    /**
     * @Route("/admin/new/{mailcompte}/", name="addadmin")
     */
    public function admin_new(Request $request): Response
    {

            $entityManager = $this->getDoctrine()->getManager();
            $compte = new Compte();
            $compte = $this->getDoctrine()
                ->getRepository(Compte::class)
                ->findOneBy(array('email' => $request->get('mailcompte')));
            if ($compte === null) {
                $compte = new Representative();
            }
            //   $compte->beAdmin();
            $compte->setRoles(array("ROLE_ADMIN"));
            $compteadmin = new Compteadmin();
            $compteadmin->setComptelogin($compte);

            $entityManager->persist($compteadmin);
            $entityManager->persist($compte);
            $entityManager->flush();


            return $this->render('back/profile.html.twig', [
                'controller_name' => 'BaseController',

            ]);
        }



    /**
     * @Route("/admin/remove/{mailcompte}", name="compte_delete_admin")
     */
    public function delete(Request $request): Response
    {

            $entityManager = $this->getDoctrine()->getManager();
            $compte = $this->getDoctrine()
            ->getRepository(Compte::class)
            ->findOneBy(array('email' => $request->get('mailcompte')));
            $entityManager->remove($compte);
            $entityManager->flush();


        return $this->render('back/profile.html.twig', [
            'controller_name' => 'BaseController',
        ]);
    }

}
