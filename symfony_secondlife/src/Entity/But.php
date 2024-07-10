<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * But
 *
 * @ORM\Table(name="but")
 * @ORM\Entity
 */
class But
{
    /**
     * @var int
     *
     * @ORM\Column(name="idBut", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idbut;

    /**
     * @var string
     *
     * @ORM\Column(name="titreBut", type="string", length=25, nullable=false)
     */
    private $titrebut;

    public function getIdbut(): ?int
    {
        return $this->idbut;
    }

    public function getTitrebut(): ?string
    {
        return $this->titrebut;
    }

    public function setTitrebut(string $titrebut): self
    {
        $this->titrebut = $titrebut;

        return $this;
    }


}
