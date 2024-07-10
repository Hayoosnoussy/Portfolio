<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Sport
 *
 * @ORM\Table(name="sport")
 * @ORM\Entity
 */
class Sport
{
    /**
     * @var int
     *
     * @ORM\Column(name="idsport", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idsport;

    /**
     * @var string
     *
     * @ORM\Column(name="nomSport", type="string", length=45, nullable=false)
     */
    private $nomsport;

    /**
     * @var string|null
     *
     * @ORM\Column(name="descriptionSport", type="string", length=45, nullable=true)
     */
    private $descriptionsport;

    public function getIdsport(): ?int
    {
        return $this->idsport;
    }

    public function getNomsport(): ?string
    {
        return $this->nomsport;
    }

    public function setNomsport(string $nomsport): self
    {
        $this->nomsport = $nomsport;

        return $this;
    }

    public function getDescriptionsport(): ?string
    {
        return $this->descriptionsport;
    }

    public function setDescriptionsport(?string $descriptionsport): self
    {
        $this->descriptionsport = $descriptionsport;

        return $this;
    }

    public function __toString()
    {
        return $this->nomsport;
    }


}
