<?php

namespace App\Entity;

use App\Entity\Category;

use App\Entity\SecCategory;

use App\Repository\ProduitRepository;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;


/**
 * @ORM\Entity(repositoryClass=ProduitRepository::class)
 */
class Produit
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255 , nullable=false)
     */
    #LOST OR FOUND
    private $Status;

    /**
     * @ORM\Column(type="string", length=255 , nullable=true)
     */
    private $Adress;




    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $CarteType;
    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $Bank;

    /**
     * @var \DateTime
     *@Groups("post:read")
     * @ORM\Column(type="date", nullable=false)
     */
    private $Date;

    /**
     * @ORM\ManyToOne(targetEntity=App\Entity\Category::class, inversedBy="produits")
     * @ORM\JoinColumn(nullable=false)
     * @Assert\NotBlank(message="You need to select a Category")
     */
    private $Categorie;

    /**
     * @ORM\ManyToOne(targetEntity=App\Entity\SecCategory::class, inversedBy="produits")
     * @ORM\JoinColumn(nullable=false)
     * @Assert\NotBlank(message="You need to select a seconde category")
     */
    private $SecCategorie;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     * @Assert\Regex(
     *     pattern = "/^[a-zA-Z]+([a-zA-Z0-9_ .,()%]*)$/i",
     *     htmlPattern = "[a-zA-Z]+([a-zA-Z0-9_ .,()%]*)", message="Le nom de produit {{ value }} ne peut pas être de cette façon"
     * )
     * @Groups("post:read")
     */
    private $owner_name;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     * @Assert\Regex(
     *     pattern = "/^[a-zA-Z]+([a-zA-Z0-9_ .,()%]*)$/i",
     *     htmlPattern = "[a-zA-Z]+([a-zA-Z0-9_ .,()%]*)", message="Le nom de produit {{ value }} ne peut pas être de cette façon"
     * )
     * @Groups("post:read")
     */
    private $owner_ln;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $marque;

    /**
     * @ORM\Column(type="string", length=255 , nullable=true)
     * @Groups ("post:read")
     */
    private $photo;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $coleur;

    /**
     * @ORM\ManyToOne(targetEntity="App\Entity\Personne" , inversedBy="produits")
     * @ORM\JoinColumn(nullable=false)
     */
    private $Personnes;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $adress_supp;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    #Par Defautl Tunisienne
    private $nationalite;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    #Neuf , Satisfait ,etc
    private $objet_status;

    /**
     * @ORM\Column(type="string", length=255 , nullable=true)
     * @Groups("post:read")
     */
    private $question_supp;

    /**
     * @ORM\Column(type="string", length=255 , nullable=true)
     * @Assert\Regex(
     *     pattern = "/^[a-zA-Z]+([a-zA-Z0-9_ .,()%]*)$/i",
     *     htmlPattern = "[a-zA-Z]+([a-zA-Z0-9_ .,()%]*)", message="Le nom de produit {{ value }} ne peut pas être de cette façon"
     * )
     * @Groups("post:read")
     */
    private $nom_objet;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Veuillez saisir la description")
     *
     * @Groups("post:read")
     */
    private $details;

    /**
     * @ORM\ManyToOne(targetEntity=Marque::class, inversedBy="produits")
     */
    private $marques;





    public function getId(): ?int
    {
        return $this->id;
    }

    public function getStatus(): ?string
    {
        return $this->Status;
    }

    public function setStatus(string $Status): self
    {
        $this->Status = $Status;

        return $this;
    }

    public function getAdress(): ?string
    {
        return $this->Adress;
    }

    public function setAdress(?string $Adress): self
    {
        $this->Adress = $Adress;

        return $this;
    }
    public function getCategorie(): ?Category
    {
        return $this->Categorie;
    }

    public function setCategorie(?Category $Categorie): self
    {
        $this->Categorie = $Categorie;

        return $this;
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



    public function getCarteType(): ?string
    {
        return $this->CarteType;
    }

    public function setCarteType(?string $CarteType): self
    {
        $this->CarteType = $CarteType;

        return $this;
    }


    public function getBank(): ?string
    {
        return $this->Bank;
    }

    public function setBank(?string $Bank): self
    {
        $this->Bank = $Bank;

        return $this;
    }

    /**
     * @return mixed
     */
    public function getDate()
    {
        return $this->Date;
    }

    /**
     * @param mixed $Date
     */
    public function setDate($Date): void
    {
        $this->Date = $Date;
    }




    public function getOwnerName(): ?string
    {
        return $this->owner_name;
    }

    public function setOwnerName(?string $owner_name): self
    {
        $this->owner_name = $owner_name;

        return $this;
    }

    public function getOwnerLn(): ?string
    {
        return $this->owner_ln;
    }

    public function setOwnerLn(?string $owner_ln): self
    {
        $this->owner_ln = $owner_ln;

        return $this;
    }

    public function getMarque(): ?string
    {
        return $this->marque;
    }

    public function setMarque(?string $marque): self
    {
        $this->marque = $marque;

        return $this;
    }

    public function getPhoto(): ?string
    {
        return $this->photo;
    }

    public function setPhoto(?string $photo): self
    {
        $this->photo = $photo;

        return $this;
    }

    public function getColeur(): ?string
    {
        return $this->coleur;
    }

    public function setColeur(?string $coleur): self
    {
        $this->coleur = $coleur;

        return $this;
    }

    public function getAdressSupp(): ?string
    {
        return $this->adress_supp;
    }

    public function setAdressSupp(?string $adress_supp): self
    {
        $this->adress_supp = $adress_supp;

        return $this;
    }

    public function getNationalite(): ?string
    {
        return $this->nationalite;
    }

    public function setNationalite(?string $nationalite): self
    {
        $this->nationalite = $nationalite;

        return $this;
    }

    public function getObjetStatus(): ?string
    {
        return $this->objet_status;
    }

    public function setObjetStatus(?string $objet_status): self
    {
        $this->objet_status = $objet_status;

        return $this;
    }

    public function getQuestionSupp(): ?string
    {
        return $this->question_supp;
    }

    public function setQuestionSupp(?string $question_supp): self
    {
        $this->question_supp = $question_supp;

        return $this;
    }

    public function getNomObjet(): ?string
    {
        return $this->nom_objet;
    }

    public function setNomObjet(?string $nom_objet): self
    {
        $this->nom_objet = $nom_objet;

        return $this;
    }

    public function getDetails(): ?string
    {
        return $this->details;
    }

    public function setDetails(?string $details): self
    {
        $this->details = $details;

        return $this;
    }


    public function getPersonnes(): ?Personne
    {
        return $this->Personnes;
    }

    public function setPersonnes(?Personne $Personnes): self
    {
        $this->Personnes = $Personnes;

        return $this;
    }


    public function __construct()
    {
        $this->produits = new ArrayCollection();
    }

    public function getMarques(): ?Marque
    {
        return $this->marques;
    }

    public function setMarques(?Marque $marques): self
    {
        $this->marques = $marques;

        return $this;
    }

}
