<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Exception;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * Compte
 *
 * @ORM\Table(name="compte", uniqueConstraints={@ORM\UniqueConstraint(name="email_UNIQUE", columns={"email"}), @ORM\UniqueConstraint(name="login", columns={"login"})})
 * @ORM\Entity
 * @UniqueEntity(fields={"email"}, message="There is already an account with this email")
 */
class Compte implements UserInterface
{

    /**
     * @ORM\Column(type="string", unique=true, nullable=true)
     */
     private $apiToken;

    /**
     * @return mixed
     */
    public function getApiToken()
    {
        return $this->apiToken;
    }

    /**
     * @param mixed $apiToken
     */
    public function setApiToken($apiToken): void
    {
        $this->apiToken = $apiToken;
    }


    /**
     * @var int
     * @Groups ("post:read")
     * @ORM\Column(name="idcompte", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idcompte;

    /**
     * @var string
     * @Groups ("post:read")
     * @ORM\Column(name="login", type="string", length=20, nullable=false)
     * @Assert\Length(
     * min = 4,
     * max = 25,
     * minMessage = "Le login doit comporter minimum {{ limit }} caractères",
     * maxMessage = "Le login doit comporter au plus {{ limit }} caractères"
     * )
     */
    private $login;

    /**
     * @var string The hashed password
     * @Groups ("post:read")
     * @ORM\Column(name="password", type="string", nullable=false)
     */
    private $password;

    /**
     * @var string
     * @Groups ("post:read")
     * @ORM\Column(name="nom", type="string", length=255, nullable=false)
     * @Assert\Regex(
     * pattern = "/^([^0-9]*)$/",
     * match = true,
     * message = "Nom erroné"
     * )
     */
    private $nom;

    /**
     * @var string
     * @Groups ("post:read")
     * @ORM\Column(name="prenom", type="string", length=255, nullable=false)
     * @Assert\Regex(
     * pattern = "/^([^0-9]*)$/",
     * match = true,
     * message = "PreNom erroné"
     * )
     */
    private $prenom;

    /**
     * @var int
     * @Groups ("post:read")
     * @ORM\Column(name="age", type="integer", nullable=false)
     * @Assert\Positive(
     * message="Age erroné"
     * )
     */
    private $age;

    /**
     * @var string|null
     * @Groups ("post:read")
     * @ORM\Column(name="telephone", type="string", length=12, nullable=true)
     * @Assert\Positive(
     * message = "telephone erroné"
     * )
     */
    private $telephone;

    /**
     * @var string|null
     * @Groups ("post:read")
     * @ORM\Column(name="imgUrl", type="string", length=255, nullable=true)
     */
    private $imgurl;

    /**
     * @var string|null
     * @Groups ("post:read")
     * @ORM\Column(name="aboutMe", type="string", length=255, nullable=true, options={"default"="Vide"})
     */
    private $aboutme = 'Vide';

    /**
     * @var string|null
     * @Groups ("post:read")
     * @ORM\Column(name="ville", type="string", length=20, nullable=true, options={"default"="Vide"})
     */
    private $ville = 'Vide';

    /**
     * @var string|null
     * @Groups ("post:read")
     * @ORM\Column(name="adresse", type="string", length=255, nullable=true, options={"default"="Vide"})
     */
    private $adresse = 'Vide';

    /**
     * @var bool
     * @Groups ("post:read")
     * @ORM\Column(name="isClosed", type="boolean", nullable=false)
     */
    private $isclosed = '0';

    /**
     * @var string
     * @Groups ("post:read")
     * @ORM\Column(name="email", type="string", length=225, nullable=false)
     * @Assert\Email(
     * message = "Email erroné"
     * )
     */
    private $email;

    /**
     * @Groups ("post:read")
     * @ORM\Column(type="json")
     */
    private $roles ;

    public function getIdcompte(): ?int
    {
        return $this->idcompte;
    }
    public function setIdcompte(int $id): ?int
    {
        return $this->idcompte;
    }

    public function getLogin(): ?string
    {
        return $this->login;
    }

    public function setLogin(string $login): self
    {
        $this->login = $login;

        return $this;
    }

    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

        return $this;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;

        return $this;
    }

    public function getAge(): ?int
    {
        return $this->age;
    }

    public function setAge(int $age): self
    {
        $this->age = $age;

        return $this;
    }

    public function getTelephone(): ?string
    {
        return $this->telephone;
    }

    public function setTelephone(?string $telephone): self
    {
        $this->telephone = $telephone;

        return $this;
    }

    public function getImgurl(): ?string
    {
        return $this->imgurl;
    }

    public function setImgurl(?string $imgurl): self
    {
        $this->imgurl = $imgurl;

        return $this;
    }

    public function getAboutme(): ?string
    {
        return $this->aboutme;
    }

    public function setAboutme(?string $aboutme): self
    {
        $this->aboutme = $aboutme;

        return $this;
    }

    public function getVille(): ?string
    {
        return $this->ville;
    }

    public function setVille(?string $ville): self
    {
        $this->ville = $ville;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(?string $adresse): self
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getIsclosed(): ?bool
    {
        return $this->isclosed;
    }

    public function setIsclosed(bool $isclosed): self
    {
        $this->isclosed = $isclosed;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    public function __toString()
    {
        return $this->login;
    }

    public function getSalt()
    {
        // TODO: Implement getSalt() method.
        return null;
    }

    public function getUsername(): ?string
    {
        return $this->login;
    }

    public function eraseCredentials()
    {
        // TODO: Implement eraseCredentials() method.
    }
    /**
     * @see UserInterface
     */
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

    public function beAdmin(){
        $this->roles = 'ROLE_ADMIN' ;
    }



}
