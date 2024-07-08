<?php

namespace App\Entity;
use ApiPlatform\Core\Annotation\ApiResource;
use App\Repository\OrdonanceRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * @ORM\Entity(repositoryClass=OrdonanceRepository::class)
 * @ApiResource
 */
class Ordonance
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups("post:read")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Groups("post:read")
     */
    private $code;

    /**
     * @ORM\Column(type="integer")
     * @Groups("post:read")
     */
    private $nbr;

    /**
     * @ORM\Column(type="string", length=255)
     * @Groups("post:read")
     */
    private $duree;

    /**
     * @ORM\Column(type="string", length=255)
     * @Groups("post:read")
     */
    private $utilisation;

    /**
     * @ORM\ManyToOne(targetEntity=Consultation::class, inversedBy="ordonances")
     * @Groups("post:read")
     */
    private $consultation;

    /**
     * @ORM\ManyToOne(targetEntity=Medicament::class, inversedBy="ordonances")
     * @Groups("post:read")
     */
    private $medicament;

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

    public function getNbr(): ?int
    {
        return $this->nbr;
    }

    public function setNbr(int $nbr): self
    {
        $this->nbr = $nbr;

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

    

    public function getConsultation(): ?Consultation
    {
        return $this->consultation;
    }

    public function setConsultation(?Consultation $consultation): self
    {
        $this->consultation = $consultation;

        return $this;
    }

    public function getMedicament(): ?Medicament
    {
        return $this->medicament;
    }

    public function setMedicament(?Medicament $medicament): self
    {
        $this->medicament = $medicament;

        return $this;
    }
}
