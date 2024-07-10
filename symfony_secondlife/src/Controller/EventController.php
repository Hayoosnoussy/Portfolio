<?php

namespace App\Controller;

use App\Entity\Categorieevent;
use App\Entity\Event;
use Swift_Message;
use Swift_Mailer;
use App\Form\EventType;
use Knp\Component\Pager\PaginatorInterface;
use App\Repository\EventRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\HttpFoundation\JsonResponse;

/**
 * @Route("/EventAdmin")
 */
class EventController extends AbstractController
{
    /**
     * @Route("/", name="event_index", methods={"GET"})
     */
    public function index(EventRepository $repository,PaginatorInterface $paginator, Request $request,NormalizerInterface $Nomalizer): Response
    {
        $allevent = $this->getDoctrine()->getRepository(Event::class)->findBy([], ['dateDebEvent' => 'ASC']);
        if(isset($_GET['search'])) {
            $requestString = $_GET['search'];
            $Event = $repository->findStudentByNsc($requestString);


            return $this->json(['retour' => $this->renderView('event/content.html.twig', ['Event' => $Event])]);
        }
        $Event = $paginator->paginate(
            $allevent,
            $request->query->getInt('page', 1),
            3
        );
        $jsonContent = $Nomalizer->normalize($allevent,'json',["groups"=>'post:read']);
   return new Response(json_encode($jsonContent));


      //  return $this->render('event/index.html.twig', [
     //       'Event' => $Event,
     //   ]);
    }

    /**
     * @Route("/new", name="event_new")
     */
    public function new(Request $request,Swift_Mailer $mailer,NormalizerInterface $Nomalizer): Response
    {
        $event = new Event();
        $category = new Categorieevent();


        $datedeb=$request->get("dateDebEvent");
        $dateFin=$request->get("dateFinEvent");

        $form = $this->createForm(EventType::class, $event);
        $form->handleRequest($request);
        $entityManager = $this->getDoctrine()->getManager();
        $event->setNomEvent($request->get("nomEvent"));
        $event->setDescriptionEvent($request->get("descriptionEvent"));
        $event->setIsactive($request->get("isactive"));
        $event->setDateDebEvent(new \DateTime($datedeb));
        $event->setDateFinEvent(new \DateTime($dateFin));
          $event->setImageevent($request->get("imageevent"));
        $event->setNbrParticipant($request->get("nbrParticipant"));
       // $event->setCategoriee($request->get("categorieE"));
        $entityManager->persist($event);
        $entityManager->flush();

        $images = $form->get('imageevent')->getData();

        if ($form->isSubmitted() && $form->isValid()) {

            /*-------------------Début Image--------------------*/
            $file=$event->getImageevent();
            $Filename = uniqid().'.'.$images->guessExtension();
            try {
                $file->move(
                    $this->getParameter('images_directory'),
                    $Filename);
            }
            catch(FileException $e){
            }
            /*-------------------Fin Image--------------------*/
            $event->setImageevent($Filename);

    
            $message = (new Swift_Message('Nouveau Evénement ajouter '))
                // On attribue l'expéditeur
                ->setFrom('no-reply@SecnodLife.com')
                // On attribue le destinataire
                ->setTo('ahlem.benfradj@esprit.tn')
                ->setBody(
                    $this->renderView(
                        'Event/notificationmail.html.twig', compact('event')
                    ),
                    'text/html'
                )
            ;
            //envoie le msg
            $mailer->send($message);

            $this->addFlash('message', 'Votre message a été transmis, nous vous répondrons dans les meilleurs délais.'); // Permet un message flash de renvoi

            return $this->redirectToRoute('event_index');
        }

        $jsonContent = $Nomalizer->normalize($event,'json',["groups"=>'post:read']);
        return new Response(json_encode($jsonContent));

        return $this->render('event/new.html.twig', [
            'event' => $event,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idEvent}", name="event_show")
     */
    public function show(Event $event,NormalizerInterface $Nomalizer)
    {
        $jsonContent = $Nomalizer->normalize($event,'json',["groups"=>'post:read']);
        return new Response(json_encode($jsonContent));
        return $this->render('event/show.html.twig', [
            'event' => $event,
        ]);
    }

    /**
     * @Route("/{idEvent}/edit", name="event_edit", methods={"GET"})
     */
    public function edit(Request $request, Event $event,NormalizerInterface $Nomalizer)
    {
        $em = $this->getDoctrine()->getManager();
        $event=$this->getDoctrine()->getManager()->getRepository(Event::class)->find($request->get("idEvent"));

        $event->setNomEvent($request->get("nomEvent"));
        $event->setDescriptionEvent($request->get("descriptionEvent"));
        $event->setIsactive($request->get("isactive"));
    /*    $event->setDateDebEvent($request->get("dateDebEvent"));
        $event->setDateFinEvent($request->get("dateFinEvent"));
       */ $event->setNbrParticipant($request->get("nbrParticipant"));


        $em->persist($event);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($event);
        return new JsonResponse("Evenement a ete modifiee avec succes ");

    }



    /**
     * @Route("/delete/{idEvent}", name="event_delete")
     */
    public function delete(Request $request, Event $event): Response
    {
        $idEvent  = $request->get("idEvent");

           if($event!=null){
               $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($event);
            $entityManager->flush();
            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted=$serializer->normalize(  $event);
            return new JsonResponse("Evenement a ete supprimer avec succes");
        }

        return  new JsonResponse("id event invalide");
    }



    /**
     * @Route("/Participer/{idEvent}", name="event_delete")
     */
    public function Participer(Request $request, Event $event , $idEvent): Response
    {

        $entityManager = $this->getDoctrine()->getManager();
        $event = $entityManager->getRepository(Event::class)->find($idEvent);
        $event->countParticipant();
        $entityManager->flush();
        return  new JsonResponse("id event ");

    }




}
