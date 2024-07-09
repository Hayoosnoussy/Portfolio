<?php


namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use App\Entity\SecCategory;
use App\Repository\CategoryRepository;

/**
 * @ORM\Entity(repositoryClass=CategoryRepository::class)
 * @ORM\Table(name="Categories")
 */

class Category
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
    private $name;

    /**
     * @ORM\OneToMany(targetEntity=SecCategory::class, mappedBy="category", orphanRemoval=true)
     */
    private $SecCategories;

    /**
     * @ORM\OneToMany(targetEntity=Produit::class, mappedBy="category", orphanRemoval=true)
     */
    private $produits;

    public function __construct()
    {
        $this->SecCategories = new ArrayCollection();
        $this->produits = new ArrayCollection();
    }
    public function getId(): ?int
    {
        return $this->id;
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
    public function construct()
    {

        $this->name = new ArrayCollection();
        $this->SecCategories = new ArrayCollection();
        $this->produits = new ArrayCollection();

    }


    /**
     * @return Collection|SecCategory[]
     */
    public function getSecCategories(): Collection
    {
        return $this->SecCategories;
    }

    public function addSecCategorie(SecCategory $SecCategorie): self
    {
        if (!$this->SecCategories->contains($SecCategorie)) {
            $this->SecCategories[] = $SecCategorie;
            $SecCategorie->setCategory($this);
        }

        return $this;
    }

    public function removeSecCategorie(SecCategory $SecCategorie): self
    {
        if ($this->SecCategories->removeElement($SecCategorie)) {
            // set the owning side to null (unless already changed)
            if ($SecCategorie->getCategory() === $this) {
                $SecCategorie->setCategory(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|Produit[]
     */
    public function getProduits(): Collection
    {
        return $this->produits;
    }

    public function addProduit(Produit $produit): self
    {
        if (!$this->produits->contains($produit)) {
            $this->produits[] = $produit;
            $produit->setCategory($this);
        }

        return $this;
    }

    public function removeProduit(Produit $produit): self
    {
        if ($this->produits->removeElement($produit)) {
            // set the owning side to null (unless already changed)
            if ($produit->getCategory() === $this) {
                $produit->setCategory(null);
            }
        }

        return $this;
    }

    public function __toString()
    {
        return $this->name;
    }

}