<?php

namespace App\Entity;
use ApiPlatform\Core\Annotation\ApiResource;
use App\Repository\MedicamentRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=MedicamentRepository::class)
 * @ApiResource
 */
class Medicament
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $code;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $libelle;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $duree;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $utilisation;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $ctrIndication;

    /**
     * @ORM\OneToMany(targetEntity=Ordonance::class, mappedBy="medicament")
     */
    private $ordonances;

    

    
    public function __construct()
    {
        $this->famille = new ArrayCollection();
        $this->ordonances = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getCode(): ?string
    {
        return $this->code;
    }

    public function setCode(string $code): self
    {
        $this->code = $code;

        return $this;
    }

    public function getLibelle(): ?string
    {
        return $this->libelle;
    }

    public function setLibelle(string $libelle): self
    {
        $this->libelle = $libelle;

        return $this;
    }

    public function getDuree(): ?string
    {
        return $this->duree;
    }

    public function setDuree(string $duree): self
    {
        $this->duree = $duree;

        return $this;
    }

    public function getUtilisation(): ?string
    {
        return $this->utilisation;
    }

    public function setUtilisation(string $utilisation): self
    {
        $this->utilisation = $utilisation;

        return $this;
    }

    public function getCtrIndication(): ?string
    {
        return $this->ctrIndication;
    }

    public function setCtrIndication(string $ctrIndication): self
    {
        $this->ctrIndication = $ctrIndication;

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
            $ordonance->setMedicament($this);
        }

        return $this;
    }

    public function removeOrdonance(Ordonance $ordonance): self
    {
        if ($this->ordonances->removeElement($ordonance)) {
            // set the owning side to null (unless already changed)
            if ($ordonance->getMedicament() === $this) {
                $ordonance->setMedicament(null);
            }
        }

        return $this;
    }

 

 
}
