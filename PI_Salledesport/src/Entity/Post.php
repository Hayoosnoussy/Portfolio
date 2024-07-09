<?php

namespace App\Entity;

use App\Repository\PostRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=PostRepository::class)
 */
class Post
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="sujet", type="string", length=255)
     *
     * @Assert\Length(
     * min = 3,
     *max = 255,
     *minMessage = "Le sujet de la post doit comporter au moins 3 caractères",
     *maxMessage = "Le sujet de la post ne doit pas dépasser les {{limit}} 255 caractères"
     *
     * )
     *
     ** @Assert\NotNull(message="Le sujet de la post ne doit pas etre null ")
     *
     */
    private $sujet;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=5000)
     *
     * @Assert\Length(
     * min = 3,
     *max = 5000,
     *minMessage = "La description de la post doit comporter au moins 3 caractères",
     *maxMessage = "La description de la post ne doit pas dépasser les {{limit}} 5000 caractères"
     *
     * )
     *
     ** @Assert\NotNull(message="La description de la post ne doit pas etre null ")
     *
     */
    private $description;

    /**
     * @var \DateTime
     *
     * @ORM\Column(type="date")
     * @Assert\GreaterThan("today")
     */
    private $date;

    /**
     * @ORM\ManyToOne(targetEntity=Users::class, inversedBy="posts")
     * @ORM\JoinColumn(nullable=false, onDelete="CASCADE")
     */
    private $user;

    /**
     * @ORM\OneToMany(targetEntity=Commentaire::class, mappedBy="post")
     */
    private $commentaires;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $categorie;

    /**
     * @ORM\ManyToMany(targetEntity=Users::class, inversedBy="postlikes")
     * @ORM\JoinTable(name="likes")}
     */
    private $likes;

    /**
     * @ORM\ManyToMany(targetEntity=Users::class, inversedBy="postdislikes")
     * @ORM\JoinTable(name="dislikes")}
     */
    private $dislikes;

    public function __construct()
    {
        $this->commentaires = new ArrayCollection();
        $this->likes = new ArrayCollection();
        $this->dislikes = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getSujet(): ?string
    {
        return $this->sujet;
    }

    public function setSujet(string $sujet): self
    {
        $this->sujet = $sujet;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }

    public function getUser(): ?Users
    {
        return $this->user;
    }

    public function setUser(?Users $user): self
    {
        $this->user = $user;

        return $this;
    }

    /**
     * @return Collection|Commentaire[]
     */
    public function getCommentaires(): Collection
    {
        return $this->commentaires;
    }

    public function addCommentaire(Commentaire $commentaire): self
    {
        if (!$this->commentaires->contains($commentaire)) {
            $this->commentaires[] = $commentaire;
            $commentaire->setPost($this);
        }

        return $this;
    }

    public function removeCommentaire(Commentaire $commentaire): self
    {
        if ($this->commentaires->removeElement($commentaire)) {
            // set the owning side to null (unless already changed)
            if ($commentaire->getPost() === $this) {
                $commentaire->setPost(null);
            }
        }

        return $this;
    }

    public function getCategorie(): ?string
    {
        return $this->categorie;
    }

    public function setCategorie(string $categorie): self
    {
        $this->categorie = $categorie;

        return $this;
    }

    /**
     * @return Collection|Users[]
     */
    public function getLikes(): Collection
    {
        return $this->likes;
    }

    public function addLike(Users $like): self
    {
        if (!$this->likes->contains($like)) {
            $this->likes[] = $like;
        }

        return $this;
    }

    public function removeLike(Users $like): self
    {
        $this->likes->removeElement($like);

        return $this;
    }

    /**
     * @return Collection|Users[]
     */
    public function getDislikes(): Collection
    {
        return $this->dislikes;
    }

    public function addDislike(Users $dislike): self
    {
        if (!$this->dislikes->contains($dislike)) {
            $this->dislikes[] = $dislike;
        }

        return $this;
    }

    public function removeDislike(Users $dislike): self
    {
        $this->dislikes->removeElement($dislike);

        return $this;
    }
}
