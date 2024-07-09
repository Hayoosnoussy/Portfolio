<?php

namespace App\Controller;

use App\Entity\Produit;
use App\Form\ProduitType;
use App\Repository\PersonneRepository;
use App\Repository\ProduitRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use CMEN\GoogleChartsBundle\GoogleCharts\Options\PieChart\PieSlice;
use Knp\Component\Pager\PaginatorInterface;


class ProduitController extends AbstractController
{

    /**
     * @Route("/admin/details", name="produit_index1", methods={"GET"})
     */
    public function index1(Request $request, PaginatorInterface $paginator,ProduitRepository $ProduitRepository,PersonneRepository $PersonneRepository): Response
    {
        $em = $this->getDoctrine()->getManager();
        $dql = "SELECT p FROM App\Entity\Produit p";
        //  $data = $this->getDoctrine()->getRepository(Produit::class)->findAll();
        $data = $em->createQuery($dql);
        $produits = $paginator->paginate(
            $data, // Requête contenant les données à paginer (Nos produits)
            $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            4 // Nombre de produits par page
        );

        $ProduitsAll = $ProduitRepository->findAll();
        $PersonnesAll = $PersonneRepository->findAll();
        $pieChart = new PieChart();
        $pieChart->getData()->setArrayToDataTable(
            [['Gestion Hotel', 'Number per section'],
                ['Les Produits',     count($ProduitsAll)],
                ['Les Personnes',     count($PersonnesAll)],

            ]
        );
        $pieSlice1 = new PieSlice();
        $pieSlice1->setColor('#e7515a');
        $pieSlice2 = new PieSlice();
        $pieSlice2->setColor('#805dca');
        $pieChart->getOptions()->setSlices([$pieSlice1, $pieSlice2]);
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(700);
        $pieChart->getOptions()->setTitle(' Nombre de produit par rapport aux personnes');
        $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#e0e6ed');
        $pieChart->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(20);
        $pieChart->getOptions()->getBackgroundColor()->setFill("#fffafa");
        $pieChart->getOptions()->getLegend()->getTextStyle()->setColor("#e0e6ed");


        return $this->render('/produit/index1.html.twig', [
            'produits' => $produits,
            'pieChart' => $pieChart,
        ]);
    }




    /**
     * @Route("/admin/archivelost", name="produit_index", methods={"GET"})
     */
    public function index(ProduitRepository $produitRepository): Response
    {
        return $this->render('produit/index.html.twig', [
            'produits' => $produitRepository->findAll(),
        ]);
    }

    /**
     * @Route("/admin/archivefound", name="produit_index3", methods={"GET"})
     */
    public function index2(ProduitRepository $produitRepository): Response
    {
        return $this->render('produit/index2.html.twig', [
            'produits' => $produitRepository->findAll(),
        ]);
    }

    /**
     * @Route("/mylndf", name="produit_index2", methods={"GET"})
     */
    public function myindex(ProduitRepository $produitRepository): Response
    {
        $em = $this->getDoctrine()->getManager();
        $user = $this->getUser();
        $query = $em->createQuery("SELECT r FROM App\Entity\Produit r WHERE r.Personnes = :user");
        $query->setParameter('user', $user);
        $produits = $query->getResult();
        return $this->render('mylf.html.twig', [
            'produits' => $produits,
        ]);
    }



    /**
     * @Route("/profil/lostndfound/new", name="produit_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {

        $produit = new Produit();
        $produit->setPersonnes($this->getUser());
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $photo = $form->get('photo')->getData();
            // this condition is needed because the 'Image' field is not required
            if ($photo)
            {
                $originalFilename = pathinfo($photo->getClientOriginalName(), PATHINFO_FILENAME);
                // this is needed to safely include the file name as part of the URL
                $safeFilename = $originalFilename;
                // security
                $fileName = $safeFilename.'-'.uniqid().'.'.$photo->guessExtension();
                // Move the file to the directory where Images are stored
                try{
                    $photo->move(
                        $this->getParameter('images_directory'),$fileName);
                } catch (FileException $e)
                {
                }
                //updates the  property to store the Image file name
                $produit->setPhoto($fileName);
            }


            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($produit);
            $entityManager->flush();



            return $this->redirectToRoute('home');
        }

        return $this->render('produit/new.html.twig', [
            'produit' => $produit,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/admin/lostndfound/{id}", name="produit_show", methods={"GET"})
     */
    public function show(Produit $produit): Response
    {
        return $this->render('produit/show.html.twig', [
            'produit' => $produit,
        ]);
    }

    /**
     * @Route("/profil/lostndfound/object/{id}", name="produit_showfront", methods={"GET"})
     */
    public function showfront(Produit $produit): Response
    {
        return $this->render('produit/showfront.html.twig', [
            'produit' => $produit,
        ]);
    }

    /**
     * @Route("/admin/lostndfound/{id}/edit", name="produit_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Produit $produit): Response
    {
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('produit_index');
        }

        return $this->render('produit/edit.html.twig', [
            'produit' => $produit,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/profil/lostndfound/{id}", name="produit_delete", methods={"POST"})
     */
    public function delete(Request $request, Produit $produit): Response
    {
        if ($this->isCsrfTokenValid('delete'.$produit->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($produit);
            $entityManager->flush();
        }

        return $this->redirectToRoute('produit_index');
    }


}
