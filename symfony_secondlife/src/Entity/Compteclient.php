<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * Compteclient
 *
 * @ORM\Table(name="compteclient", uniqueConstraints={@ORM\UniqueConstraint(name="compteLogin_UNIQUE", columns={"compteLogin"})}, indexes={@ORM\Index(name="coachLogin", columns={"coachLogin"}), @ORM\Index(name="compteLogin_idx", columns={"compteLogin"})})
 * @ORM\Entity
 */
class Compteclient
{
    /**
     * @var int
     *@Groups ("post:read")
     * @ORM\Column(name="idcompteclient", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idcompteclient;

    /**
     * @var int|null
     *@Groups ("post:read")
     * @ORM\Column(name="poids", type="integer", nullable=true)
     * @Assert\Positive(
     * message="Poids erroné"
     * )
     */
    private $poids;

    /**
     * @var int|null
     *@Groups ("post:read")
     * @ORM\Column(name="taille", type="integer", nullable=true)
     * @Assert\Positive(
     * message="taille erroné"
     * )
     */
    private $taille;

    /**
     * @var string|null
     *@Groups ("post:read")
     * @ORM\Column(name="exceptionMedicale", type="string", length=255, nullable=true, options={"default"="Vide"})
     */
    private $exceptionmedicale = 'Vide';

    /**
     * @var int|null
     *@Groups ("post:read")
     * @ORM\Column(name="coachLogin", type="integer", nullable=true)
     */
    private $coachlogin = '0';

    /**
     * @var \Compte
     * @ORM\ManyToOne(targetEntity="Compte")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="compteLogin", referencedColumnName="idcompte")
     * })
     */
    private $comptelogin;

    public function getIdcompteclient(): ?int
    {
        return $this->idcompteclient;
    }

    public function getPoids(): ?int
    {
        return $this->poids;
    }

    public function setPoids(?int $poids): self
    {
        $this->poids = $poids;

        return $this;
    }

    public function getTaille(): ?int
    {
        return $this->taille;
    }

    public function setTaille(?int $taille): self
    {
        $this->taille = $taille;

        return $this;
    }

    public function getExceptionmedicale(): ?string
    {
        return $this->exceptionmedicale;
    }

    public function setExceptionmedicale(?string $exceptionmedicale): self
    {
        $this->exceptionmedicale = $exceptionmedicale;

        return $this;
    }

    public function getCoachlogin(): ?int
    {
        return $this->coachlogin;
    }

    public function setCoachlogin(?int $coachlogin): self
    {
        $this->coachlogin = $coachlogin;

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
    public function __toString()
    {
        return strval($this->getComptelogin());
    }

}
