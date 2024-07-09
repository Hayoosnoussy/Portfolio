<?php


namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use App\Repository\SecCategoryRepository;

/**
 * @ORM\Entity(repositoryClass=SecCategoryRepository::class)
 * @ORM\Table(name="SecCategories")
 */
class SecCategory
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
     * @ORM\ManyToOne(targetEntity=Category::class, inversedBy="SecCategories")
     * @ORM\JoinColumn(nullable=false)
     */
    private $Category;
    /**
     * @ORM\OneToMany(targetEntity=Produit::class, mappedBy="SecCategorie", orphanRemoval=true)
     */
    private $produits;

    /**
     * @ORM\OneToMany(targetEntity=Marque::class, mappedBy="SecCategorie")
     */
    private $marques;

    /**
     * @ORM\OneToMany(targetEntity=Marque::class, mappedBy="SecCategorie")
     */
    private $marquess;

    public function __construct()
    {
        $this->produits = new ArrayCollection();
        $this->marques = new ArrayCollection();
        $this->marquess = new ArrayCollection();
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

    public function getCategory(): ?Category
    {
        return $this->Category;
    }

    public function setCountry(?Category $Category): self
    {
        $this->Category = $Category;

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

    /**
     * @return Collection<int, Marque>
     */
    public function getMarques(): Collection
    {
        return $this->marques;
    }

    public function addMarque(Marque $marque): self
    {
        if (!$this->marques->contains($marque)) {
            $this->marques[] = $marque;
            $marque->setSecCategorie($this);
        }

        return $this;
    }

    public function removeMarque(Marque $marque): self
    {
        if ($this->marques->removeElement($marque)) {
            // set the owning side to null (unless already changed)
            if ($marque->getSecCategorie() === $this) {
                $marque->setSecCategorie(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection<int, Marque>
     */
    public function getMarquess(): Collection
    {
        return $this->marquess;
    }

    public function addMarquess(Marque $marquess): self
    {
        if (!$this->marquess->contains($marquess)) {
            $this->marquess[] = $marquess;
            $marquess->setSecCategorie($this);
        }

        return $this;
    }

    public function removeMarquess(Marque $marquess): self
    {
        if ($this->marquess->removeElement($marquess)) {
            // set the owning side to null (unless already changed)
            if ($marquess->getSecCategorie() === $this) {
                $marquess->setSecCategorie(null);
            }
        }

        return $this;
    }

}