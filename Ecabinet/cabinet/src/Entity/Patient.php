<?php

namespace App\Entity;
use ApiPlatform\Core\Annotation\ApiResource;
use App\Repository\PatientRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=PatientRepository::class)
 * @ApiResource
 */

class Patient
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
    private $nom;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $prenom;

    /**
     * @ORM\Column(type="date")
     */
    private $dateNaissance;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $adresse;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $ville;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $tel;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $gsm;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $genre;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $etatCivil;

    /**
     * @ORM\Column(type="integer")
     */
    private $nbrEnfant;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $nomConjoint;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $lienParente;

    /**
     * @ORM\Column(type="float")
     */
    private $taille;

    /**
     * @ORM\Column(type="integer")
     */
    private $poids;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $grSanguin;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $profession;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $identUnique;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $priseEncharge;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $medecin;

    /**
     * @ORM\Column(type="date", nullable=true)
     */
    private $datepCons;

    /**
     * @ORM\Column(type="date", nullable=true)
     */
    private $dateDcons;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $motcles;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $codeApci;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $regimeCnam;

    /**
     * @ORM\Column(type="date", nullable=true)
     */
    private $datevalidite;

    /**
     * @ORM\ManyToOne(targetEntity=Nationalite::class)
     * @ORM\JoinColumn(nullable=false)
     */
    private $nationalite;

    /**
     * @ORM\ManyToOne(targetEntity=Domaine::class)
     */
    private $domaine;

    /**
     * @ORM\ManyToOne(targetEntity=Assureur::class)
     */
    private $assureur;

  
    /**
     * @ORM\OneToMany(targetEntity=Consultation::class, mappedBy="patient", orphanRemoval=true)
     */
    private $consultations;

    /**
     * @ORM\OneToMany(targetEntity=Reglement::class, mappedBy="patient", orphanRemoval=true)
     */
    private $reglements;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $code;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $lieu;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $email;

   

    public function getId(): ?int
    {
        return $this->id;
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

    public function getDateNaissance(): ?\DateTimeInterface
    {
        return $this->dateNaissance;
    }

    public function setDateNaissance(\DateTimeInterface $dateNaissance): self
    {
        $this->dateNaissance = $dateNaissance;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(string $adresse): self
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getVille(): ?string
    {
        return $this->ville;
    }

    public function setVille(string $ville): self
    {
        $this->ville = $ville;

        return $this;
    }

    public function getTel(): ?string
    {
        return $this->tel;
    }

    public function setTel(string $tel): self
    {
        $this->tel = $tel;

        return $this;
    }

    public function getGsm(): ?string
    {
        return $this->gsm;
    }

    public function setGsm(string $gsm): self
    {
        $this->gsm = $gsm;

        return $this;
    }

    public function getGenre(): ?string
    {
        return $this->genre;
    }

    public function setGenre(string $genre): self
    {
        $this->genre = $genre;

        return $this;
    }

    public function getEtatCivil(): ?string
    {
        return $this->etatCivil;
    }

    public function setEtatCivil(string $etatCivil): self
    {
        $this->etatCivil = $etatCivil;

        return $this;
    }

    public function getNbrEnfant(): ?int
    {
        return $this->nbrEnfant;
    }

    public function setNbrEnfant(int $nbrEnfant): self
    {
        $this->nbrEnfant = $nbrEnfant;

        return $this;
    }

    public function getNomConjoint(): ?string
    {
        return $this->nomConjoint;
    }

    public function setNomConjoint(string $nomConjoint): self
    {
        $this->nomConjoint = $nomConjoint;

        return $this;
    }

    public function getLienParente(): ?string
    {
        return $this->lienParente;
    }

    public function setLienParente(string $lienParente): self
    {
        $this->lienParente = $lienParente;

        return $this;
    }

    public function getTaille(): ?float
    {
        return $this->taille;
    }

    public function setTaille(float $taille): self
    {
        $this->taille = $taille;

        return $this;
    }

    public function getPoids(): ?int
    {
        return $this->poids;
    }

    public function setPoids(int $poids): self
    {
        $this->poids = $poids;

        return $this;
    }

    public function getGrSanguin(): ?string
    {
        return $this->grSanguin;
    }

    public function setGrSanguin(string $grSanguin): self
    {
        $this->grSanguin = $grSanguin;

        return $this;
    }

    public function getProfession(): ?string
    {
        return $this->profession;
    }

    public function setProfession(?string $profession): self
    {
        $this->profession = $profession;

        return $this;
    }

    public function getIdentUnique(): ?string
    {
        return $this->identUnique;
    }

    public function setIdentUnique(?string $identUnique): self
    {
        $this->identUnique = $identUnique;

        return $this;
    }

    public function getPriseEncharge(): ?string
    {
        return $this->priseEncharge;
    }

    public function setPriseEncharge(string $priseEncharge): self
    {
        $this->priseEncharge = $priseEncharge;

        return $this;
    }

    public function getMedecin(): ?string
    {
        return $this->medecin;
    }

    public function setMedecin(?string $medecin): self
    {
        $this->medecin = $medecin;

        return $this;
    }

    public function getDatepCons(): ?\DateTimeInterface
    {
        return $this->datepCons;
    }

    public function setDatepCons(?\DateTimeInterface $datepCons): self
    {
        $this->datepCons = $datepCons;

        return $this;
    }

    public function getDateDcons(): ?\DateTimeInterface
    {
        return $this->dateDcons;
    }

    public function setDateDcons(?\DateTimeInterface $dateDcons): self
    {
        $this->dateDcons = $dateDcons;

        return $this;
    }

    public function getMotcles(): ?string
    {
        return $this->motcles;
    }

    public function setMotcles(?string $motcles): self
    {
        $this->motcles = $motcles;

        return $this;
    }

    public function getCodeApci(): ?string
    {
        return $this->codeApci;
    }

    public function setCodeApci(?string $codeApci): self
    {
        $this->codeApci = $codeApci;

        return $this;
    }

    public function getRegimeCnam(): ?string
    {
        return $this->regimeCnam;
    }

    public function setRegimeCnam(string $regimeCnam): self
    {
        $this->regimeCnam = $regimeCnam;

        return $this;
    }

    public function getDatevalidite(): ?\DateTimeInterface
    {
        return $this->datevalidite;
    }

    public function setDatevalidite(?\DateTimeInterface $datevalidite): self
    {
        $this->datevalidite = $datevalidite;

        return $this;
    }

    public function getNationalite(): ?Nationalite
    {
        return $this->nationalite;
    }

    public function setNationalite(?Nationalite $nationalite): self
    {
        $this->nationalite = $nationalite;

        return $this;
    }

    public function getDomaine(): ?Domaine
    {
        return $this->domaine;
    }

    public function setDomaine(?Domaine $domaine): self
    {
        $this->domaine = $domaine;

        return $this;
    }

    public function getAssureur(): ?Assureur
    {
        return $this->assureur;
    }

    public function setAssureur(?Assureur $assureur): self
    {
        $this->assureur = $assureur;

        return $this;
    }

   

    /**
     * @return Collection|Consultation[]
     */
    public function getConsultations(): Collection
    {
        return $this->consultations;
    }

    public function addConsultation(Consultation $consultation): self
    {
        if (!$this->consultations->contains($consultation)) {
            $this->consultations[] = $consultation;
            $consultation->setPatient($this);
        }

        return $this;
    }

    public function removeConsultation(Consultation $consultation): self
    {
        if ($this->consultations->removeElement($consultation)) {
            // set the owning side to null (unless already changed)
            if ($consultation->getPatient() === $this) {
                $consultation->setPatient(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|Reglement[]
     */
    public function getReglements(): Collection
    {
        return $this->reglements;
    }

    public function addReglement(Reglement $reglement): self
    {
        if (!$this->reglements->contains($reglement)) {
            $this->reglements[] = $reglement;
            $reglement->setPatient($this);
        }

        return $this;
    }

    public function removeReglement(Reglement $reglement): self
    {
        if ($this->reglements->removeElement($reglement)) {
            // set the owning side to null (unless already changed)
            if ($reglement->getPatient() === $this) {
                $reglement->setPatient(null);
            }
        }

        return $this;
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

    public function getLieu(): ?string
    {
        return $this->lieu;
    }

    public function setLieu(string $lieu): self
    {
        $this->lieu = $lieu;

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


}
