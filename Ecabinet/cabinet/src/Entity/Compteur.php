<?php

namespace App\Entity;
use ApiPlatform\Core\Annotation\ApiResource;
use App\Repository\CompteurRepository;
use Doctrine\ORM\Mapping as ORM;
/**
 * @ORM\Entity(repositoryClass=CompteurRepository::class)
 * @ApiResource
 */
class Compteur
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer")
     */
    private $annee;

    /**
     * @ORM\Column(type="integer")
     */
    private $numconsultation;

    /**
     * @ORM\Column(type="integer")
     */
    private $numrdv;

    public function getId(): ?int
    {
        return $this->id;
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

    public function getNumconsultation(): ?int
    {
        return $this->numconsultation;
    }

    public function setNumconsultation(int $numconsultation): self
    {
        $this->numconsultation = $numconsultation;

        return $this;
    }

    public function getNumrdv(): ?int
    {
        return $this->numrdv;
    }

    public function setNumrdv(int $numrdv): self
    {
        $this->numrdv = $numrdv;

        return $this;
    }
}
