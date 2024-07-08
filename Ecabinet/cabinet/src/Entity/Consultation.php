<?php

namespace App\Entity;

use ApiPlatform\Core\Annotation\ApiResource;
use App\Repository\ConsultationRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * @ORM\Entity(repositoryClass=ConsultationRepository::class)
 * @ApiResource
 */

class Consultation
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @Groups("post:read")
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer")
     * @Groups("post:read")
     */
    private $numero;

    /**
     * @ORM\Column(type="integer")
     * @Groups("post:read")
     */
    private $annee;

    /**
     * @ORM\Column(type="date")
     * @Groups("post:read")
     */
    private $dateConsultation;

    /**
     * @ORM\ManyToOne(targetEntity=Patient::class, inversedBy="consultations")
     * @Groups("post:write")
     * @ORM\JoinColumn(nullable=false)
     */
    private $patient;

    /**
     * @ORM\Column(type="string", length=255)
     * @Groups("post:read")
     */
    private $observation;

    /**
     * @ORM\Column(type="float")
     * @Groups("post:read")
     */
    private $montant;

    /**
     * @ORM\OneToMany(targetEntity=Ordonance::class, mappedBy="consultation")
     * 
     */
    private $ordonances;

   


    


    public function __construct()
    {
        $this->ordonances = new ArrayCollection();
        $this->compteurs = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNumero(): ?int
    {
        return $this->numero;
    }

    public function setNumero(int $numero): self
    {
        $this->numero = $numero;

        return $this;
    }

    public function getAnnee(): ?int
    {
        return $this->annee;
    }

    public function setAnnee(int $annee): self
    {
        $this->annee = $annee;

        return $this;
    }

    public function getDateConsultation(): ?\DateTimeInterface
    {
        return $this->dateConsultation;
    }

    public function setDateConsultation(\DateTimeInterface $dateConsultation): self
    {
        $this->dateConsultation = $dateConsultation;

        return $this;
    }

    public function getPatient(): ?Patient
    {
        return $this->patient;
    }

    public function setPatient(?Patient $patient): self
    {
        $this->patient = $patient;

        return $this;
    }

    public function getObservation(): ?string
    {
        return $this->observation;
    }

    public function setObservation(string $observation): self
    {
        $this->observation = $observation;

        return $this;
    }

    public function getMontant(): ?float
    {
        return $this->montant;
    }

    public function setMontant(float $montant): self
    {
        $this->montant = $montant;

        return $this;
    }

    /**
     * @return Collection|Ordonance[]
     */
    public function getOrdonances(): Collection
    {
        return $this->ordonances;
    }

    public function addOrdonance(Ordonance $ordonance): self
    {
        if (!$this->ordonances->contains($ordonance)) {
            $this->ordonances[] = $ordonance;
            $ordonance->setConsultation($this);
        }

        return $this;
    }

    public function removeOrdonance(Ordonance $ordonance): self
    {
        if ($this->ordonances->removeElement($ordonance)) {
            // set the owning side to null (unless already changed)
            if ($ordonance->getConsultation() === $this) {
                $ordonance->setConsultation(null);
            }
        }

        return $this;
    }

  
}
