<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Serializer\Annotation\Groups;






/**
 * Personne
 * @ORM\Table(name="personne", uniqueConstraints={@ORM\UniqueConstraint(name="email", columns={"email"}), @ORM\UniqueConstraint(name="username", columns={"username"})})
 * @ORM\Entity(repositoryClass=PersonneRepository::class)
 * @UniqueEntity(
 * fields= {"email"},
 * message="L'email que vous avez indiqué est déjà utilisé !"
 * )
 * * @UniqueEntity(
 * fields= {"username"},
 * message="Le Nom d'utilisateur que vous avez indiqué est déjà utilisé !"
 * )
 * @ORM\Entity
 */
class Personne implements UserInterface
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=30, nullable=false)
     * @Assert\NotBlank(message="Veuillez saisir le nom de produit")
     * @Assert\Regex(
     *     pattern = "/^[a-zA-Z]+([a-zA-Z0-9_ .,()%]*)$/i",
     *     htmlPattern = "[a-zA-Z]+([a-zA-Z0-9_ .,()%]*)", message="Le nom de produit {{ value }} ne peut pas être de cette façon"
     * )
     * @Groups("post:read")
     */

    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=30, nullable=false)
     * @Assert\NotBlank(message="Veuillez saisir le nom de produit")
     * @Assert\Regex(
     *     pattern = "/^[a-zA-Z]+([a-zA-Z0-9_ .,()%]*)$/i",
     *     htmlPattern = "[a-zA-Z]+([a-zA-Z0-9_ .,()%]*)", message="Le nom de produit {{ value }} ne peut pas être de cette façon"
     * )
     * @Groups("post:read")
     */

    private $prenom;

    /**
     * @var string
     *
     * @ORM\Column(name="role", type="string", length=30, nullable=false)
     */
    private $role;

    /**
     * @ORM\Column(type="json")
     */
    private $roles = [];

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=250, nullable=false)
     */
    private $email;

    /**
     * @var string
     *
     * @ORM\Column(name="username", type="string", length=100, nullable=false)
     */
    private $username;

    /**
     * @var string
     *
     * @ORM\Column(name="password", type="string", length=100, nullable=false)
     * @Assert\Length(min="6" , minMessage="Votre mot de passe doit faire minimum 6 caractéres")
     */
    private $password;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Please upload image")
     * @Assert\File(mimeTypes={"image/*"})
     */
    private $photo ;

    /**
     * @var string
     *
     * @ORM\Column(name="num_telephone", type="string", length=8, nullable=false)
     */
    private $numTelephone;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $activation_token;

    /**
     * @ORM\OneToMany(targetEntity=Produit::class, mappedBy="Personnes", orphanRemoval=true)
     */
    private $produits;


    public function construct()
    {

        $this->nom = new ArrayCollection();
        $this->prenom = new ArrayCollection();
        $this->role = new ArrayCollection();
        $this->email = new ArrayCollection();;
        $this->username = new ArrayCollection();
        $this->password = new ArrayCollection();
        $this->photo = new ArrayCollection();
        $this->numTelephone = new ArrayCollection();
        $this->produits = new ArrayCollection();
    }

    /**
     * @return int
     */
    public function getId(): int
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId(int $id): void
    {
        $this->id = $id;
    }

    /**
     * @return string
     */
    public function getNom(): ?string
    {
        return $this->nom;
    }

    /**
     * @param string $nom
     */
    public function setNom(string $nom): void
    {
        $this->nom = $nom;
    }

    /**
     * @return string
     */
    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    /**
     * @param string $prenom
     */
    public function setPrenom(string $prenom): void
    {
        $this->prenom = $prenom;
    }

    /**
     * @return string
     */
    public function getRole(): ?string
    {
        return $this->role;
    }

    /**
     * @param string $role
     */
    public function setRole(string $role): void
    {
        $this->role = $role;
    }

    /**
     * @return string
     */
    public function getEmail(): ?string
    {
        return $this->email;
    }

    /**
     * @param string $email
     */
    public function setEmail(string $email): void
    {
        $this->email = $email;
    }

    /**
     * @return string
     */
    public function getUsername(): ?string
    {
        return $this->username;
    }

    /**
     * @param string $username
     */
    public function setUsername(string $username): void
    {
        $this->username = $username;
    }

    /**
     * @return string
     */
    public function getPassword(): ?string
    {
        return $this->password;
    }

    /**
     * @param string $password
     */
    public function setPassword(string $password): void
    {
        $this->password = $password;
    }

    /**
     * @return mixed
     */
    public function getPhoto()
    {
        return $this->photo;
    }


    public function getRoles(): array
    {
        $roles = $this->roles;
        // guarantee every user at least has ROLE_USER
        $roles[] = 'ROLE_USER';

        return array_unique($roles);
    }

    public function setRoles(array $roles): self
    {
        $this->roles = $roles;

        return $this;
    }


    /**
     * @param mixed $image
     */
    public function setPhoto($photo): void
    {
        $this->photo = $photo;
    }

    /**
     * @return string
     */
    public function getNumTelephone(): ?string
    {
        return $this->numTelephone;
    }

    /**
     * @param string $numTelephone
     */
    public function setNumTelephone(string $numTelephone): void
    {
        $this->numTelephone = $numTelephone;
    }
    /**
     *@Assert\EqualTo(propertyPath="password" , message="Vous n'avez pas tapez la même mot de passe")
     */
    public $confirm_password;

    /**
     * @ORM\Column(type="boolean")
     */
    private $isVerified = false;

   /**
    public function getRoles()
    {
        return ['ROLE_USER'];
    }
    */
    public function getSalt()
    {

    }
    public function eraseCredentials()
    {

    }
    public function __construct()
    {
        $this->produits = new ArrayCollection();
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
            $produit->setPersonnes($this);
        }

        return $this;
    }

    public function removeProduit(Produit $produit): self
    {
        if ($this->produits->removeElement($produit)) {
            // set the owning side to null (unless already changed)
            if ($produit->getPersonnes() === $this) {
                $produit->setPersonnes(null);
            }
        }

        return $this;
    }

    public function __toString()
    {
        return $this->username;
    }

    public function getActivationToken(): ?string
    {
        return $this->activation_token;
    }

    public function setActivationToken(?string $activation_token): self
    {
        $this->activation_token = $activation_token;

        return $this;
    }

    /*
    public function serialize()
    {
        return serialize([
            $this->id,

            $this->username,

            $this->numTelephone,
            $this->prenom,
            $this->nom,
            $this->photo,
            $this->role,
            $this->email,
            $this->password
        ]);
    }
    public function unserialize($string)
{
List (

$this->id,

$this->username,

$this->numTelephone,
$this->prenom,
$this->nom,
$this->photo,
$this->role,
$this->email,
$this->password
) = unserialize(string ,['allowed_classes' => false]);
}

*/

    public function isVerified(): bool
    {
        return $this->isVerified;
    }

    public function setIsVerified(bool $isVerified): self
    {
        $this->isVerified = $isVerified;

        return $this;
    }
}
