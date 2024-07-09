<?php

namespace App\Entity;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use App\Repository\MarqueRepository;


/**
 * @ORM\Entity(repositoryClass=MarqueRepository::class)
 * @ORM\Table(name="Marque")
 */
class Marque
{

    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity=SecCategory::class, inversedBy="marquess")
     */
    private $SecCategorie;

    /**
     * @ORM\OneToMany(targetEntity=Produit::class, mappedBy="marques")
     */
    private $produits;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $name;

    public function __construct()
    {
        $this->produits = new ArrayCollection();
    }

    public function getSecCategorie(): ?SecCategory
    {
        return $this->SecCategorie;
    }

    public function setSecCategorie(?SecCategory $SecCategorie): self
    {
        $this->SecCategorie = $SecCategorie;

        return $this;
    }

    /**
     * @return Collection<int, Produit>
     */
    public function getProduits(): Collection
    {
        return $this->produits;
    }

    public function addProduit(Produit $produit): self
    {
        if (!$this->produits->contains($produit)) {
            $this->produits[] = $produit;
            $produit->setMarques($this);
        }

        return $this;
    }

    public function removeProduit(Produit $produit): self
    {
        if ($this->produits->removeElement($produit)) {
            // set the owning side to null (unless already changed)
            if ($produit->getMarques() === $this) {
                $produit->setMarques(null);
            }
        }

        return $this;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(string $name): self
    {
        $this->name = $name;

        return $this;
    }

}