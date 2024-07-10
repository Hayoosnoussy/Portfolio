<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Symfony\Component\Serializer\Annotation\Groups;
use Doctrine\ORM\Mapping as ORM;
use App\Entity\Categorieevent;


/**
 * Event
 *
 * @ORM\Table(name="event", indexes={@ORM\Index(name="Categorie", columns={"categorieE"})})
 * @ORM\Entity
 */
class Event
{
    /**
     * @var int
     *
     * @ORM\Column(name="ID_Event", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups("post:read")
     */
    private $idEvent;

    /**
     * @var string
     *@Groups("post:read")
     * @ORM\Column(name="Nom_Event", type="string", length=100, nullable=false)
     */
    private $nomEvent;

    /**
     * @var string
     *@Groups("post:read")
     * @ORM\Column(name="Description_Event", type="string", length=250, nullable=false)
     */
    private $descriptionEvent;

    /**
     * @var string
     *@Groups("post:read")
     * @ORM\Column(name="IsActive", type="string", length=20, nullable=false)
     */
    private $isactive;

    /**
     * @var \DateTime
     *@Groups("post:read")
     * @ORM\Column(name="Date_deb_Event", type="date", nullable=false)
     */
    private $dateDebEvent;

    /**
     * @var \DateTime
     *@Groups("post:read")
     * @ORM\Column(name="Date_fin_Event", type="date", nullable=false)
     */
    private $dateFinEvent;

    /**
     * @var string
     *@Groups("post:read")
     * @ORM\Column(name="ImageEvent", type="string", length=250, nullable=false)
     */
        private $imageevent;

    /**
     * @var int
     *@Groups("post:read")
     * @ORM\Column(name="nbr_Participant", type="integer", nullable=false)
     */
    private $nbrParticipant;

    /**
     * @var Categorieevent
     *@Groups("post:read")
     * @ORM\ManyToOne(targetEntity=Categorieevent::class)
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="categorieE", referencedColumnName="categorieE")
     * })
     *
     */
    private $categoriee;

    /**
     * @ORM\OneToMany(targetEntity=Comments::class, mappedBy="events", orphanRemoval=true)
     * @Groups("post:read")
     */
    private $comments;

    public function __construct()
    {
        $this->comments = new ArrayCollection();
    }

    public function getIdEvent(): ?int
    {
        return $this->idEvent;
    }

    public function getNomEvent(): ?string
    {
        return $this->nomEvent;
    }

    public function setNomEvent(string $nomEvent): self
    {
        $this->nomEvent = $nomEvent;

        return $this;
    }

    public function getDescriptionEvent(): ?string
    {
        return $this->descriptionEvent;
    }

    public function setDescriptionEvent(string $descriptionEvent): self
    {
        $this->descriptionEvent = $descriptionEvent;

        return $this;
    }

    public function getIsactive(): ?string
    {
        return $this->isactive;
    }

    public function setIsactive(string $isactive): self
    {
        $this->isactive = $isactive;

        return $this;
    }

    public function getDateDebEvent(): ?\DateTimeInterface
    {
        return $this->dateDebEvent;
    }

    public function setDateDebEvent(\DateTimeInterface $dateDebEvent): self
    {
        $this->dateDebEvent = $dateDebEvent;

        return $this;
    }

    public function getDateFinEvent(): ?\DateTimeInterface
    {
        return $this->dateFinEvent;
    }

    public function setDateFinEvent(\DateTimeInterface $dateFinEvent): self
    {
        $this->dateFinEvent = $dateFinEvent;

        return $this;
    }

    public function getImageevent()
    {
        return $this->imageevent;
    }

    public function setImageevent($imageevent)
    {
        $this->imageevent = $imageevent;

        return $this;
    }

    public function getNbrParticipant(): ?int
    {
        return $this->nbrParticipant;
    }

    public function setNbrParticipant(int $nbrParticipant): self
    {
        $this->nbrParticipant = $nbrParticipant;

        return $this;
    }

    public function setCategoriee(?Categorieevent $categoriee): self
    {
        $this->categoriee = $categoriee;

        return $this;
    }

    /**
     * @return Collection|Comments[]
     */
    public function getComments(): Collection
    {
        return $this->comments;
    }

    public function addComment(Comments $comment): self
    {
        if (!$this->comments->contains($comment)) {
            $this->comments[] = $comment;
            $comment->setEvents($this);
        }

        return $this;
    }

    public function removeComment(Comments $comment): self
    {
        if ($this->comments->removeElement($comment)) {
            // set the owning side to null (unless already changed)
            if ($comment->getEvents() === $this) {
                $comment->setEvents(null);
            }
        }

        return $this;
    }

    public function countParticipant(): self
    {

        $this->nbrParticipant = $this->nbrParticipant - 1;

        return $this;
    }

    public function getCategoriee(): ?Categorieevent
    {
        return $this->categoriee;
    }


}
