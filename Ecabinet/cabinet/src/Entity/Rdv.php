<?php

namespace App\Entity;

use ApiPlatform\Core\Annotation\ApiResource;
use App\Repository\RdvRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=RdvRepository::class)
 */
#[ApiResource]
class Rdv
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="date")
     */
    private $dateRdv;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $nom1;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $obs1;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $conf1;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $nom2;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $obs2;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $conf2;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $nom3;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $obs3;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $conf3;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $nom4;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $obs4;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $conf4;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $nom5;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $obs5;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $conf5;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $nom6;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $obs6;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $conf6;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $nom7;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $obs7;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $conf7;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $nom8;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $obs8;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $conf8;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $nom9;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $obs9;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $conf9;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $nom10;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $obs10;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $conf10;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $nom11;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $obs11;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $conf11;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $nom12;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $obs12;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $conf12;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $nom13;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $obs13;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $conf13;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $nom14;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $obs14;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $conf14;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $nom15;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $obs15;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $conf15;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $nom16;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $obs16;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $conf16;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getDateRdv(): ?\DateTimeInterface
    {
        return $this->dateRdv;
    }

    public function setDateRdv(\DateTimeInterface $dateRdv): self
    {
        $this->dateRdv = $dateRdv;

        return $this;
    }

    public function getNom1(): ?string
    {
        return $this->nom1;
    }

    public function setNom1(?string $nom1): self
    {
        $this->nom1 = $nom1;

        return $this;
    }

    public function getObs1(): ?string
    {
        return $this->obs1;
    }

    public function setObs1(?string $obs1): self
    {
        $this->obs1 = $obs1;

        return $this;
    }

    public function getConf1(): ?string
    {
        return $this->conf1;
    }

    public function setConf1(?string $conf1): self
    {
        $this->conf1 = $conf1;

        return $this;
    }

    public function getNom2(): ?string
    {
        return $this->nom2;
    }

    public function setNom2(?string $nom2): self
    {
        $this->nom2 = $nom2;

        return $this;
    }

    public function getObs2(): ?string
    {
        return $this->obs2;
    }

    public function setObs2(?string $obs2): self
    {
        $this->obs2 = $obs2;

        return $this;
    }

    public function getConf2(): ?string
    {
        return $this->conf2;
    }

    public function setConf2(?string $conf2): self
    {
        $this->conf2 = $conf2;

        return $this;
    }

    public function getNom3(): ?string
    {
        return $this->nom3;
    }

    public function setNom3(?string $nom3): self
    {
        $this->nom3 = $nom3;

        return $this;
    }

    public function getObs3(): ?string
    {
        return $this->obs3;
    }

    public function setObs3(?string $obs3): self
    {
        $this->obs3 = $obs3;

        return $this;
    }

    public function getConf3(): ?string
    {
        return $this->conf3;
    }

    public function setConf3(?string $conf3): self
    {
        $this->conf3 = $conf3;

        return $this;
    }

    public function getNom4(): ?string
    {
        return $this->nom4;
    }

    public function setNom4(?string $nom4): self
    {
        $this->nom4 = $nom4;

        return $this;
    }

    public function getObs4(): ?string
    {
        return $this->obs4;
    }

    public function setObs4(?string $obs4): self
    {
        $this->obs4 = $obs4;

        return $this;
    }

    public function getConf4(): ?string
    {
        return $this->conf4;
    }

    public function setConf4(?string $conf4): self
    {
        $this->conf4 = $conf4;

        return $this;
    }

    public function getNom5(): ?string
    {
        return $this->nom5;
    }

    public function setNom5(?string $nom5): self
    {
        $this->nom5 = $nom5;

        return $this;
    }

    public function getObs5(): ?string
    {
        return $this->obs5;
    }

    public function setObs5(?string $obs5): self
    {
        $this->obs5 = $obs5;

        return $this;
    }

    public function getConf5(): ?string
    {
        return $this->conf5;
    }

    public function setConf5(?string $conf5): self
    {
        $this->conf5 = $conf5;

        return $this;
    }

    public function getNom6(): ?string
    {
        return $this->nom6;
    }

    public function setNom6(?string $nom6): self
    {
        $this->nom6 = $nom6;

        return $this;
    }

    public function getObs6(): ?string
    {
        return $this->obs6;
    }

    public function setObs6(?string $obs6): self
    {
        $this->obs6 = $obs6;

        return $this;
    }

    public function getConf6(): ?string
    {
        return $this->conf6;
    }

    public function setConf6(?string $conf6): self
    {
        $this->conf6 = $conf6;

        return $this;
    }

    public function getNom7(): ?string
    {
        return $this->nom7;
    }

    public function setNom7(?string $nom7): self
    {
        $this->nom7 = $nom7;

        return $this;
    }

    public function getObs7(): ?string
    {
        return $this->obs7;
    }

    public function setObs7(?string $obs7): self
    {
        $this->obs7 = $obs7;

        return $this;
    }

    public function getConf7(): ?string
    {
        return $this->conf7;
    }

    public function setConf7(?string $conf7): self
    {
        $this->conf7 = $conf7;

        return $this;
    }

    public function getNom8(): ?string
    {
        return $this->nom8;
    }

    public function setNom8(?string $nom8): self
    {
        $this->nom8 = $nom8;

        return $this;
    }

    public function getObs8(): ?string
    {
        return $this->obs8;
    }

    public function setObs8(?string $obs8): self
    {
        $this->obs8 = $obs8;

        return $this;
    }

    public function getConf8(): ?string
    {
        return $this->conf8;
    }

    public function setConf8(?string $conf8): self
    {
        $this->conf8 = $conf8;

        return $this;
    }

    public function getNom9(): ?string
    {
        return $this->nom9;
    }

    public function setNom9(?string $nom9): self
    {
        $this->nom9 = $nom9;

        return $this;
    }

    public function getObs9(): ?string
    {
        return $this->obs9;
    }

    public function setObs9(?string $obs9): self
    {
        $this->obs9 = $obs9;

        return $this;
    }

    public function getConf9(): ?string
    {
        return $this->conf9;
    }

    public function setConf9(?string $conf9): self
    {
        $this->conf9 = $conf9;

        return $this;
    }

    public function getNom10(): ?string
    {
        return $this->nom10;
    }

    public function setNom10(?string $nom10): self
    {
        $this->nom10 = $nom10;

        return $this;
    }

    public function getObs10(): ?string
    {
        return $this->obs10;
    }

    public function setObs10(?string $obs10): self
    {
        $this->obs10 = $obs10;

        return $this;
    }

    public function getConf10(): ?string
    {
        return $this->conf10;
    }

    public function setConf10(?string $conf10): self
    {
        $this->conf10 = $conf10;

        return $this;
    }

    public function getNom11(): ?string
    {
        return $this->nom11;
    }

    public function setNom11(?string $nom11): self
    {
        $this->nom11 = $nom11;

        return $this;
    }

    public function getObs11(): ?string
    {
        return $this->obs11;
    }

    public function setObs11(?string $obs11): self
    {
        $this->obs11 = $obs11;

        return $this;
    }

    public function getConf11(): ?string
    {
        return $this->conf11;
    }

    public function setConf11(?string $conf11): self
    {
        $this->conf11 = $conf11;

        return $this;
    }

    public function getNom12(): ?string
    {
        return $this->nom12;
    }

    public function setNom12(?string $nom12): self
    {
        $this->nom12 = $nom12;

        return $this;
    }

    public function getObs12(): ?string
    {
        return $this->obs12;
    }

    public function setObs12(?string $obs12): self
    {
        $this->obs12 = $obs12;

        return $this;
    }

    public function getConf12(): ?string
    {
        return $this->conf12;
    }

    public function setConf12(?string $conf12): self
    {
        $this->conf12 = $conf12;

        return $this;
    }

    public function getNom13(): ?string
    {
        return $this->nom13;
    }

    public function setNom13(?string $nom13): self
    {
        $this->nom13 = $nom13;

        return $this;
    }

    public function getObs13(): ?string
    {
        return $this->obs13;
    }

    public function setObs13(?string $obs13): self
    {
        $this->obs13 = $obs13;

        return $this;
    }

    public function getConf13(): ?string
    {
        return $this->conf13;
    }

    public function setConf13(?string $conf13): self
    {
        $this->conf13 = $conf13;

        return $this;
    }

    public function getNom14(): ?string
    {
        return $this->nom14;
    }

    public function setNom14(?string $nom14): self
    {
        $this->nom14 = $nom14;

        return $this;
    }

    public function getObs14(): ?string
    {
        return $this->obs14;
    }

    public function setObs14(?string $obs14): self
    {
        $this->obs14 = $obs14;

        return $this;
    }

    public function getConf14(): ?string
    {
        return $this->conf14;
    }

    public function setConf14(?string $conf14): self
    {
        $this->conf14 = $conf14;

        return $this;
    }

    public function getNom15(): ?string
    {
        return $this->nom15;
    }

    public function setNom15(?string $nom15): self
    {
        $this->nom15 = $nom15;

        return $this;
    }

    public function getObs15(): ?string
    {
        return $this->obs15;
    }

    public function setObs15(?string $obs15): self
    {
        $this->obs15 = $obs15;

        return $this;
    }

    public function getConf15(): ?string
    {
        return $this->conf15;
    }

    public function setConf15(?string $conf15): self
    {
        $this->conf15 = $conf15;

        return $this;
    }

    public function getNom16(): ?string
    {
        return $this->nom16;
    }

    public function setNom16(?string $nom16): self
    {
        $this->nom16 = $nom16;

        return $this;
    }

    public function getObs16(): ?string
    {
        return $this->obs16;
    }

    public function setObs16(?string $obs16): self
    {
        $this->obs16 = $obs16;

        return $this;
    }

    public function getConf16(): ?string
    {
        return $this->conf16;
    }

    public function setConf16(?string $conf16): self
    {
        $this->conf16 = $conf16;

        return $this;
    }
}
