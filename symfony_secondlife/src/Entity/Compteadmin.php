<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * Compteadmin
 *
 * @ORM\Table(name="compteadmin", uniqueConstraints={@ORM\UniqueConstraint(name="compteLogin_UNIQUE", columns={"compteLogin"})}, indexes={@ORM\Index(name="roleAdminFK_idx", columns={"compteLogin"})})
 * @ORM\Entity
 */
class Compteadmin
{
    /**
     * @var int
     *
     * @ORM\Column(name="idcompteadmin", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idcompteadmin;

    /**
     * @var string|null
     *
     * @ORM\Column(name="DescriptionTache", type="string", length=45, nullable=true, options={"default"="Vide"})
     */
    private $descriptiontache = 'Vide';

    /**
     * @var \Compte
     *
     * @ORM\ManyToOne(targetEntity="Compte")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="compteLogin", referencedColumnName="idcompte")
     * })
     */
    private $comptelogin;

    public function getIdcompteadmin(): ?int
    {
        return $this->idcompteadmin;
    }

    public function getDescriptiontache(): ?string
    {
        return $this->descriptiontache;
    }

    public function setDescriptiontache(?string $descriptiontache): self
    {
        $this->descriptiontache = $descriptiontache;

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
       return strval($this->comptelogin);

    }


}
