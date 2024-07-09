<?php

namespace App\Controller;

use App\Data\SearchPostData;
use App\Entity\Commentaire;
use App\Entity\Post;
use App\Form\CommentaireType;
use App\Form\PostType;
use App\Form\SearchPostType;
use App\Repository\PostRepository;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/post")
 */
class PostController extends AbstractController
{
    /**
     * @Route("/like/{id}", name="post_like", methods={"GET"})
     */
    public function like(Request $request, Post $post, PaginatorInterface $paginator): Response
    {
        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery("SELECT p FROM App\Entity\Post p WHERE p.id = :id");
        $query->setParameter('id', $request->attributes->get('id'));
        $post = $query->getSingleResult();
        $user = $this->getUser();
        $nbr1 = count($post->getLikes());
        $post->addLike($user);
        $nbr2 = count($post->getLikes());
        if ($nbr1 == $nbr2){
            $post->removeLike($user);
        }
        $em->persist($post);
        $em->flush();
        $query = $em->createQuery("SELECT c FROM App\Entity\Commentaire c WHERE c.post = :post");
        $query->setParameter('post', $post);
        $commentaires = $query->getResult();

        $commentaire = new Commentaire();
        $commentaire->setUser($this->getUser());
        $commentaire->setPost($post);
        $form = $this->createForm(CommentaireType::class, $commentaire);
        $form->handleRequest($request);
        $commentaires=$paginator->paginate($commentaires,$request->query->getInt('page',1),3);
        return $this->redirectToRoute('commentaire_index', [
            'commentaires' => $commentaires,
            'post' => $post,
            'id' => $post->getId(),
            'commentaire' => $commentaire,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/dislike/{id}", name="post_dislike", methods={"GET"})
     */
    public function dislike(Request $request, Post $post, PaginatorInterface $paginator): Response
    {
        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery("SELECT p FROM App\Entity\Post p WHERE p.id = :id");
        $query->setParameter('id', $request->attributes->get('id'));
        $post = $query->getSingleResult();
        $user = $this->getUser();
        $nbr1 = count($post->getDislikes());
        $post->addDislike($user);
        $nbr2 = count($post->getDislikes());
        if ($nbr1 == $nbr2){
            $post->removeDislike($user);
        }
        $em->persist($post);
        $em->flush();
        $query = $em->createQuery("SELECT c FROM App\Entity\Commentaire c WHERE c.post = :post");
        $query->setParameter('post', $post);
        $commentaires = $query->getResult();

        $commentaire = new Commentaire();
        $commentaire->setUser($this->getUser());
        $commentaire->setPost($post);
        $form = $this->createForm(CommentaireType::class, $commentaire);
        $form->handleRequest($request);
        $commentaires=$paginator->paginate($commentaires,$request->query->getInt('page',1),3);
        return $this->redirectToRoute('commentaire_index', [
            'commentaires' => $commentaires,
            'post' => $post,
            'id' => $post->getId(),
            'commentaire' => $commentaire,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/tri", name="tri_index", methods={"GET"})
     */
    public function indexTri(Request $request, PostRepository $postRepository, PaginatorInterface $paginator): Response
    {
        $data = new SearchPostData();
        $form = $this->createForm(SearchPostType::class, $data);
        $form->handleRequest($request);
        $em = $this->getDoctrine()->getManager();
        if(!empty($data->p)){
            $query = $em->createQuery("SELECT p FROM App\Entity\Post p WHERE p.sujet LIKE :p");
            $query->setParameter('p', "%{$data->p}%");
            $posts = $query->getResult();
        } else {
            $posts = $postRepository->findAll();
        }

        for($I = count($posts) - 2;$I >= 0; $I--) {
            for($J = 0; $J <= $I; $J++) {
                if(count($posts[$J + 1]->getLikes()) > count($posts[$J]->getLikes())) {
                    $t = $posts[$J + 1];
                    $posts[$J + 1] = $posts[$J];
                    $posts[$J] = $t;
                }
            }
        }

        $posts=$paginator->paginate($posts,$request->query->getInt('page',1),3);

        return $this->render('post/index.html.twig', [
            'posts' => $posts,
            'form' => $form->createView()
        ]);
    }
    /**
     * @Route("/", name="post_index", methods={"GET"})
     */
    public function index(Request $request, PostRepository $postRepository, PaginatorInterface $paginator): Response
    {
        $data = new SearchPostData();
        $form = $this->createForm(SearchPostType::class, $data);
        $form->handleRequest($request);
        $em = $this->getDoctrine()->getManager();
        if(!empty($data->p)){
            $query = $em->createQuery("SELECT p FROM App\Entity\Post p WHERE p.sujet LIKE :p");
            $query->setParameter('p', "%{$data->p}%");
            $posts = $query->getResult();
        } else {
            $posts = $postRepository->findAll();
        }
        $posts=$paginator->paginate($posts,$request->query->getInt('page',1),3);

        return $this->render('post/index.html.twig', [
            'posts' => $posts,
            'form' => $form->createView()
        ]);
    }

    /**
     * @Route("/new", name="post_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $post = new Post();
        $post->setUser($this->getUser());
        $form = $this->createForm(PostType::class, $post);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $post->setDate(new \DateTime());
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($post);
            $entityManager->flush();

            return $this->redirectToRoute('post_index');
        }

        return $this->render('post/new.html.twig', [
            'post' => $post,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="post_show", methods={"GET"})
     */
    public function show(Post $post): Response
    {
        return $this->render('post/show.html.twig', [
            'post' => $post,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="post_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Post $post): Response
    {
        $form = $this->createForm(PostType::class, $post);
        $form->handleRequest($request);

        if ($form->isSubmitted()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('post_index');
        }

        return $this->render('post/edit.html.twig', [
            'post' => $post,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="post_delete", methods={"DELETE"})
     */
    public function delete(Request $request, Post $post): Response
    {
        if ($this->isCsrfTokenValid('delete'.$post->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($post);
            $entityManager->flush();
        }

        return $this->redirectToRoute('post_index');
    }
}
