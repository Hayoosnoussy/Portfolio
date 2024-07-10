<?php

namespace App\Controller;

use App\Entity\Regimes;
use App\Form\RepasType;
use App\Entity\Repas;
use App\Form\SearchRepasType;
use App\Repository\MyClassRepository;
use Doctrine\ORM\EntityManagerInterface;
use MessageBird\Resources\Groups;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Component\Mime\Email;
use Symfony\Component\Mailer\MailerInterface;
use Knp\Bundle\SnappyBundle\Snappy\Response\PdfResponse;
use Knp\Snappy\Pdf;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;


class ClubController extends AbstractController
{
    /**
     * @Route("/back/MJ/Club_gest", name="Club_gest")
     */
    public function index(MailerInterface $mailer, Request $request): Response
    {
        $Repas = new Repas();
        $form = $this->createForm(RepasType::class, $Repas);
        $form->add('ajouter',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted())
        {

            if ($form->isValid())
            {
                $em = $this->getDoctrine()->getManager();
                $em->persist($Repas);
                $em->flush();
                $email = (new Email())
                    ->from('secondaryhp@gmail.com')
                    ->to('secondaryhp@gmail.com')
                    ->subject('Repas')
                    ->text("Repas Ajouté ! ❤");
                $mailer->send($email);


                return $this->redirectToRoute('club');
            }
            else
            {
                $this->addFlash('error', 'Remarque les mots interdits sont (mot1,mot2...)!');

            }
        }

        return $this->render('back/MJ/Club_gest/ajouter_Repas.html.twig', [
            'post_form' => $form->createView()

        ]);
    }

    /**
     * @Route("/JSON/List_Rep", name="jsonL")
     */
    public function getRep(NormalizerInterface $normalizer)
    {
        
           $repository = $this->getDoctrine()->getRepository(Repas :: class);
        $user = $repository->findAll();
        $jsonContent =  $normalizer->normalize($user,'json',['groups' => 'repas']);
        dump($jsonContent);
        return  new Response(json_encode($jsonContent));
    }
    /**
     * @Route("/JSON/Add_Rep" ,name="jsonA")  
     */
    public function addRep( Request $request,NormalizerInterface $normalizer,MailerInterface $mailer)
    {
       $em = $this->getDoctrine()->getManager();
       $repas= new Repas();
       $repas->setTitre($request->get('Titre'));
        $repas->setDescription($request->get('Description'));
        $repas->setImguml($request->get('ImgUml'));
   $message = (new Email())
            ->from('secondaryhp@gmail.com')
            ->to("secondaryhp@gmail.com")
           ->subject('Repas')
                    ->text("Repas Ajouté ! ❤");
        

        $mailer->send($message);
    $em->persist($repas);
    $em->flush();
        $content=$normalizer->normalize($repas,'json',['groups'=>'repas']);
    return new Response(json_encode($content));
    }
    /**
     * @Route("/back/MJ/club", name="club")
     */
    public function viewRepasAction(Request $request,MyClassRepository $repo)
    {
        $repas = $repo->findBy(['titre' => 'mots'], ['titre' => 'asc'], 5);
        $form = $this->createForm(SearchRepasType::class);
        $search = $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()){
            // On recherche les annonces correspondant aux mots clés
            $repas= $repo->search(
                $search->get('mots')->getData()
            );
            return $this->render('back/MJ/club/afficher_Repas.html.twig', ['rep' => $repas, 'form' => $form->createView()]);
        }

        $rep = $this->getDoctrine()->getRepository(Repas::class)->findAll();
        return $this->render('back/MJ/club/afficher_Repas.html.twig', ['rep' => $rep, 'form' => $form->createView()]);
    }
    /**
     * @Route("/back/MJ/Club_sup/{id}", name="Club_sup")
     */
    public function deleteRepasAction($id)
    {

        $em=$this->getDoctrine()->getManager();
        $rep = $em->getRepository(Repas::class)->find($id);
        $em->remove($rep);
        $em->flush();
        $this->addFlash('message',"Repas supprimé");
        return $this->redirectToRoute('club');

    }
    /**
     * @Route("/back/MJ/Club_mod/{id}", name="Club_mod")
     */
    public function editRepasAction(Request $request, $id, MailerInterface $mailer)
    {

        $rep = $this->getDoctrine()->getRepository(Repas::class)->find($id);
        $rep->setTitre($rep->getTitre());
        $rep->setDescription($rep->getDescription());
        $rep->setImgUml($rep->getImgUml());
        $rep->setIdRegime($rep->getIdRegime());
        $form = $this->createFormBuilder($rep)
        ->add('titre',TextType::class, ['attr' =>['placeholder'=>'enter title here']])
        ->add('description',TextareaType::class)
        ->add('imguml',TextType::class)
        ->add('id_regime',EntityType::class, [
            'class'  => Regimes::class,
            'choice_label' => 'titre',
        ])
            ->add('Modifier',SubmitType::class)

        ->getForm();
        $form->handleRequest($request);
        if($form->isSubmitted())
        {
            if ($form->isValid()) {
                $titre = $form['titre']->getData();
                $desc = $form['description']->getData();
                $ImgUml = $form['imguml']->getData();
                $IdRegime = $form['id_regime']->getData();
                $em = $this->getDoctrine()->getManager();
                $rep = $em->getRepository(Repas::class)->find($id);
                $rep->setTitre($titre);
                $rep->setDescription($desc);
                $rep->setImgUml($ImgUml);
                $rep->setIdRegime($IdRegime);
                $em->flush();
                $email = (new Email())
                    ->from('secondaryhp@gmail.com')
                    ->to('secondaryhp@gmail.com')
                    ->subject('Repas')
                    ->text("Repas Modifié ! ❤");
                $mailer->send($email);
                $this->addFlash('mod', 'Repas Modifié!');

                return $this->redirectToRoute('club');
            }
            else
            {
                $this->addFlash('error_mod', 'Remarque les mots interdits sont (mot1,mot2...)!');

            }
        }

        return $this->render('back/MJ/Club_mod/modifier_Repas.html.twig', [
            'post_form' => $form->createView()

        ]);

    }

    /**
     * @Route("/front/MJ/client", name="client")
     */
    public function view2RepasAction()
    {
        $rep = $this->getDoctrine()->getRepository(Repas::class)->findAll();
        return $this->render('front/MJ/client/afficher_Repas.html.twig', ['rep' => $rep]);
    }
    /**
     * @Route("/front/MJ/front", name="front")
     */
    public function view3RepasAction()
    {
        $rep = $this->getDoctrine()->getRepository(Repas::class)->findAll();
        return $this->render('front/MJ/front/Main.html.twig', ['rep' => $rep]);
    }


    /**
     * @Route("/back/MJ/PDF1", name="pdf1")
     */
    public function PDFAction(Request $request)
    {
        $rep = $this->getDoctrine()->getRepository(Repas::class)->findAll();
        $form = $this->createForm(RepasType::class);
        $form->handleRequest($request);
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('back/MJ/club/afficher_Repas.html.twig', [
                'rep' => $rep,'form' => $form->createView()
            ]
        );

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("Repaspdf.pdf", [
            "Attachment" => true
        ]);

        return $this->redirectToRoute('club');
    }

}
