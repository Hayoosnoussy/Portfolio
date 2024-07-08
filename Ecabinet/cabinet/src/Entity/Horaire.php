<?php

namespace App\Entity;

use ApiPlatform\Core\Annotation\ApiResource;
use App\Repository\HoraireRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=HoraireRepository::class)
 * @ApiResource
 * 
 */

class Horaire
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
     * @ORM\Column(type="DateTime")

     */
    private $hd;

    /**
     * @ORM\Column(type="DateTime")
     */
    private $hf;

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

    
    public function getHd(): ?\DateTimeInterface
    {
        return $this->hd;
    }

    public function setHd(\DateTimeInterface $hd): self
    {
        $this->hd = $hd;

        return $this;
    }

    public function getHf(): ?\DateTimeInterface
    {
        return $this->hf;
    }

    public function setHf(\DateTimeInterface $hf): self
    {
        $this->hf = $hf;

        return $this;
    }
}
