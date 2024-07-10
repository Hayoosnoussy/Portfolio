<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * Categorieevent
 *
 * @ORM\Table(name="categorieevent")
 * @ORM\Entity
 */
class Categorieevent
{
    /**
     * @var int
     * @Groups("post:read")

     * @ORM\Column(name="categorieE", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $categoriee;

    /**
     * @var string
     * @Groups("post:read")

     * @ORM\Column(name="NameCat", type="string", length=200, nullable=false)
     */
    private $namecat;

    /**
     * @var string
     * @Groups("post:read")

     * @ORM\Column(name="DesCatEvent", type="string", length=250, nullable=false)
     */
    private $descatevent;

    /**
     * @param int $categoriee
     */
    public function setCategoriee(int $categoriee): void
    {
        $this->categoriee = $categoriee;
    }




    public function getCategoriee(): ?int
    {
        return $this->categoriee;
    }

    public function getNamecat(): ?string
    {
        return $this->namecat;
    }

    public function setNamecat(string $namecat): self
    {
        $this->namecat = $namecat;

        return $this;
    }

    public function getDescatevent(): ?string
    {
        return $this->descatevent;
    }

    public function setDescatevent(string $descatevent): self
    {
        $this->descatevent = $descatevent;

        return $this;
    }
    public function __toString()
    {
        return (string) $this->getCategoriee();
    }

}
