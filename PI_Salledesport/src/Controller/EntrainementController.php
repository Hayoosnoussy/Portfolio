<?php

namespace App\Controller;

use App\Entity\Abonnement;
use App\Entity\Coach;
use App\Entity\Entrainement;
use App\Form\CoachProfileType;
use App\Form\EntrainementType;
use App\Repository\AbonnementRepository;
use App\Repository\EntrainementRepository;
use App\UserNotifierService;
use http\Client;
use MercurySeries\FlashyBundle\FlashyNotifier;
use PouleR\DeezerAPI\DeezerAPIClient;
use PouleR\DeezerAPI\DeezerAPIException;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpClient\MockHttpClient;
use Symfony\Component\HttpClient\Response\MockResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/entrainement")
 */
class EntrainementController extends AbstractController
{
    /**
     * @Route("/{id}", name="entrainement_index", methods={"GET"})
     */
    public function index(Request $request,
                          EntrainementRepository $entrainementRepository,
    AbonnementRepository $abonnementRepository
    ): Response
    {
        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery
        ("SELECT c FROM App\Entity\Coach c WHERE c.id = :id");
        $query->setParameter('id', $request->attributes->get('id'));
        $coach = $query->getSingleResult();
        $query = $em->createQuery
        ("SELECT e FROM App\Entity\Entrainement e WHERE e.coach = :coach");
        $query->setParameter('coach', $coach);
        $entrainements = $query->getResult();
        $query1 = $em->createQuery
        ("SELECT abbs FROM App\Entity\Abonnement abbs WHERE abbs.coach = :coach");
        $query1->setParameter('coach', $coach);
        $abbs = $query1->getResult();
        return $this->render('entrainement/index.html.twig', [
            'entrainements' => $entrainements,
            'abbs' => $abbs,
            'coach' => $coach,
        ]);
    }

    /**
     * @Route("/coach/new/{jour}/{heure}", name="entrainement_new", methods={"GET","POST"})
     */
    public function new(Request $request, FlashyNotifier $flashy): Response
    {
        $entrainement = new Entrainement();
        $em = $this->getDoctrine()->getManager();
        $user = $this->getUser();
        $query = $em->createQuery("SELECT c FROM App\Entity\Coach c WHERE c.user = :user");
        $query->setParameter('user', $user);
        $coach = $query->getSingleResult();
        $entrainement->setCoach($coach);
        $entrainement->setJour($request->attributes->get('jour'));
        $entrainement->setHeure($request->attributes->get('heure'));
        $form = $this->createForm(EntrainementType::class, $entrainement);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($entrainement);
            $entityManager->flush();
            $flashy->primary('Entrenement '.$entrainement->getTitre().' le '.$entrainement->getJour().' a '
                .$entrainement->getHeure().' heure est Ajouter');

            return $this->redirect("/entrainement/".$entrainement->getCoach()->getId());
        }

        return $this->render('entrainement/new.html.twig', [
            'entrainement' => $entrainement,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/coach/{id}", name="entrainement_show", methods={"GET"})
     */
    public function show(Entrainement $entrainement): Response
    {
        return $this->render('entrainement/show.html.twig', [
            'entrainement' => $entrainement,
        ]);
    }

    /**
     * @Route("/coach/{id}/edit", name="entrainement_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Entrainement $entrainement, FlashyNotifier $flashy): Response
    {
        $form = $this->createForm(EntrainementType::class, $entrainement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            $flashy->mutedDark('Entrenement '.$entrainement->getTitre().' le '.$entrainement->getJour().' a '
                .$entrainement->getHeure().' heure est Modifier');
            return $this->redirect("/entrainement/".$entrainement->getCoach()->getId());
        }

        return $this->render('entrainement/edit.html.twig', [
            'entrainement' => $entrainement,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/coach/{id}", name="entrainement_delete", methods={"DELETE"})
     */
    public function delete(Request $request, Entrainement $entrainement,FlashyNotifier $flashy): Response
    {
        if ($this->isCsrfTokenValid('delete'.$entrainement->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($entrainement);
            $entityManager->flush();

        }
        $flashy->error('Entrenement '.$entrainement->getTitre().' le '.$entrainement->getJour().' a '
            .$entrainement->getHeure().' est Supprimer');
        return $this->redirect("/entrainement/".$entrainement->getCoach()->getId());
    }
    /**
     * @Route("/coach/profile/{id}", name="profile_edit", methods={"GET","POST"})
     */

    public function profile(Request $request, Coach $coach, FlashyNotifier $flashy):Response
    {

        $form = $this->createForm(CoachProfileType::class, $coach);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){
            $file = $form->get('image')->getData();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('images_directory'),
                    $fileName
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }
            $coach->setImage($fileName);
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->flush();
            $flashy->success('les modifications de votre profile on été bien effectuées');
            return $this->redirect("/entrainement/".$coach->getId());

        }
        return $this->render('coachprofile/coachprofile.html.twig', [
            'form' => $form->createView(),
            'coach' =>$coach
        ]);

    }

}
