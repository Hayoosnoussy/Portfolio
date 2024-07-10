<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * Comptecoach
 *
 * @ORM\Table(name="comptecoach", uniqueConstraints={@ORM\UniqueConstraint(name="compteLogin_UNIQUE", columns={"compteLogin"})}, indexes={@ORM\Index(name="roelCoachFK_idx", columns={"compteLogin"}), @ORM\Index(name="roelCoachFK_idx1", columns={"sport"})})
 * @ORM\Entity
 */
class Comptecoach
{
    /**
     * @var int
     *@Groups ("post:read")
     * @ORM\Column(name="idcomptecoach", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idcomptecoach;

    /**
     * @var int|null
     *@Groups ("post:read")
     * @ORM\Column(name="prixHeure", type="integer", nullable=true)
     * @Assert\Positive(
     * message = "prix heure erroné"
     * )
     */
    private $prixheure;

    /**
     * @var bool|null
     *@Groups ("post:read")
     * @ORM\Column(name="valide", type="boolean", nullable=true)
     */
    private $valide = '0';

    /**
     * @var \Compte
     *
     * @ORM\ManyToOne(targetEntity="Compte")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="compteLogin", referencedColumnName="idcompte")
     * })
     */
    private $comptelogin;

    /**
     * @var \Sport
     *
     * @ORM\ManyToOne(targetEntity="Sport")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="sport", referencedColumnName="idsport")
     * })
     */
    private $sport;

    public function getIdcomptecoach(): ?int
    {
        return $this->idcomptecoach;
    }

    public function getPrixheure(): ?int
    {
        return $this->prixheure;
    }

    public function setPrixheure(?int $prixheure): self
    {
        $this->prixheure = $prixheure;

        return $this;
    }

    public function getValide(): ?bool
    {
        return $this->valide;
    }

    public function setValide(?bool $valide): self
    {
        $this->valide = $valide;

        return $this;
    }

    public function getComptelogin(): ?Compte
    {
        return $this->comptelogin;
    }

    public function setComptelogin(?Compte $comptelogin): self
    {
        $this->comptelogin = $comptelogin;

        return $this;
    }

    public function getSport(): ?Sport
    {
        return $this->sport;
    }

    public function setSport(?Sport $sport): self
    {
        $this->sport = $sport;

        return $this;
    }


}
