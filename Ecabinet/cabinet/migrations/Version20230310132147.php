<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230310132147 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE assureur (id INT AUTO_INCREMENT NOT NULL, code VARCHAR(255) NOT NULL, libelle VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE categorie (id INT AUTO_INCREMENT NOT NULL, code VARCHAR(255) NOT NULL, libelle VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE compteur (id INT AUTO_INCREMENT NOT NULL, annee INT NOT NULL, numconsultation INT NOT NULL, numrdv INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE consultation (id INT AUTO_INCREMENT NOT NULL, patient_id INT NOT NULL, numero INT NOT NULL, annee INT NOT NULL, date_consultation DATE NOT NULL, observation VARCHAR(255) NOT NULL, montant DOUBLE PRECISION NOT NULL, INDEX IDX_964685A66B899279 (patient_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE domaine (id INT AUTO_INCREMENT NOT NULL, code VARCHAR(255) NOT NULL, libelle VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE horaire (id INT AUTO_INCREMENT NOT NULL, code VARCHAR(255) NOT NULL, hd TIME NOT NULL, hf TIME NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE medecin (id INT AUTO_INCREMENT NOT NULL, specialite_id INT NOT NULL, categorie_id INT NOT NULL, nationalite_id INT DEFAULT NULL, matricule VARCHAR(255) NOT NULL, nom VARCHAR(255) NOT NULL, prenom VARCHAR(255) NOT NULL, date_naissance DATE DEFAULT NULL, adresse VARCHAR(255) DEFAULT NULL, ville VARCHAR(255) DEFAULT NULL, tel VARCHAR(255) NOT NULL, gsm VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL, fax VARCHAR(255) DEFAULT NULL, genre VARCHAR(255) NOT NULL, INDEX IDX_1BDA53C62195E0F0 (specialite_id), INDEX IDX_1BDA53C6BCF5E72D (categorie_id), INDEX IDX_1BDA53C61B063272 (nationalite_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE medicament (id INT AUTO_INCREMENT NOT NULL, code VARCHAR(255) NOT NULL, libelle VARCHAR(255) NOT NULL, duree VARCHAR(255) NOT NULL, utilisation VARCHAR(255) NOT NULL, ctr_indication VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE modereg (id INT AUTO_INCREMENT NOT NULL, code VARCHAR(255) NOT NULL, libelle VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE nationalite (id INT AUTO_INCREMENT NOT NULL, code VARCHAR(255) NOT NULL, libelle VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE ordonance (id INT AUTO_INCREMENT NOT NULL, consultation_id INT DEFAULT NULL, medicament_id INT DEFAULT NULL, code VARCHAR(255) NOT NULL, nbr INT NOT NULL, duree VARCHAR(255) NOT NULL, utilisation VARCHAR(255) NOT NULL, INDEX IDX_99240B9C62FF6CDF (consultation_id), INDEX IDX_99240B9CAB0D61F7 (medicament_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE patient (id INT AUTO_INCREMENT NOT NULL, nationalite_id INT NOT NULL, domaine_id INT DEFAULT NULL, assureur_id INT DEFAULT NULL, nom VARCHAR(255) NOT NULL, prenom VARCHAR(255) NOT NULL, date_naissance DATE NOT NULL, adresse VARCHAR(255) NOT NULL, ville VARCHAR(255) NOT NULL, tel VARCHAR(255) NOT NULL, gsm VARCHAR(255) NOT NULL, genre VARCHAR(255) NOT NULL, etat_civil VARCHAR(255) NOT NULL, nbr_enfant INT NOT NULL, nom_conjoint VARCHAR(255) NOT NULL, lien_parente VARCHAR(255) NOT NULL, taille DOUBLE PRECISION NOT NULL, poids INT NOT NULL, gr_sanguin VARCHAR(255) NOT NULL, profession VARCHAR(255) DEFAULT NULL, ident_unique VARCHAR(255) DEFAULT NULL, prise_encharge VARCHAR(255) NOT NULL, medecin VARCHAR(255) DEFAULT NULL, datep_cons DATE DEFAULT NULL, date_dcons DATE DEFAULT NULL, motcles VARCHAR(255) DEFAULT NULL, code_apci VARCHAR(255) DEFAULT NULL, regime_cnam VARCHAR(255) NOT NULL, datevalidite DATE DEFAULT NULL, code VARCHAR(255) NOT NULL, lieu VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL, INDEX IDX_1ADAD7EB1B063272 (nationalite_id), INDEX IDX_1ADAD7EB4272FC9F (domaine_id), INDEX IDX_1ADAD7EB80F7E20A (assureur_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE rdv (id INT AUTO_INCREMENT NOT NULL, date_rdv DATE NOT NULL, nom1 VARCHAR(255) DEFAULT NULL, obs1 VARCHAR(255) DEFAULT NULL, conf1 VARCHAR(255) DEFAULT NULL, nom2 VARCHAR(255) DEFAULT NULL, obs2 VARCHAR(255) DEFAULT NULL, conf2 VARCHAR(255) DEFAULT NULL, nom3 VARCHAR(255) DEFAULT NULL, obs3 VARCHAR(255) DEFAULT NULL, conf3 VARCHAR(255) DEFAULT NULL, nom4 VARCHAR(255) DEFAULT NULL, obs4 VARCHAR(255) DEFAULT NULL, conf4 VARCHAR(255) DEFAULT NULL, nom5 VARCHAR(255) DEFAULT NULL, obs5 VARCHAR(255) DEFAULT NULL, conf5 VARCHAR(255) DEFAULT NULL, nom6 VARCHAR(255) DEFAULT NULL, obs6 VARCHAR(255) DEFAULT NULL, conf6 VARCHAR(255) DEFAULT NULL, nom7 VARCHAR(255) DEFAULT NULL, obs7 VARCHAR(255) DEFAULT NULL, conf7 VARCHAR(255) DEFAULT NULL, nom8 VARCHAR(255) DEFAULT NULL, obs8 VARCHAR(255) DEFAULT NULL, conf8 VARCHAR(255) DEFAULT NULL, nom9 VARCHAR(255) DEFAULT NULL, obs9 VARCHAR(255) DEFAULT NULL, conf9 VARCHAR(255) DEFAULT NULL, nom10 VARCHAR(255) DEFAULT NULL, obs10 VARCHAR(255) DEFAULT NULL, conf10 VARCHAR(255) DEFAULT NULL, nom11 VARCHAR(255) DEFAULT NULL, obs11 VARCHAR(255) DEFAULT NULL, conf11 VARCHAR(255) DEFAULT NULL, nom12 VARCHAR(255) DEFAULT NULL, obs12 VARCHAR(255) DEFAULT NULL, conf12 VARCHAR(255) DEFAULT NULL, nom13 VARCHAR(255) DEFAULT NULL, obs13 VARCHAR(255) DEFAULT NULL, conf13 VARCHAR(255) DEFAULT NULL, nom14 VARCHAR(255) DEFAULT NULL, obs14 VARCHAR(255) DEFAULT NULL, conf14 VARCHAR(255) DEFAULT NULL, nom15 VARCHAR(255) DEFAULT NULL, obs15 VARCHAR(255) DEFAULT NULL, conf15 VARCHAR(255) DEFAULT NULL, nom16 VARCHAR(255) DEFAULT NULL, obs16 VARCHAR(255) DEFAULT NULL, conf16 VARCHAR(255) DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reglement (id INT AUTO_INCREMENT NOT NULL, patient_id INT NOT NULL, modereg_id INT NOT NULL, numero INT NOT NULL, annee INT NOT NULL, date_reglement DATE NOT NULL, mod_reglement VARCHAR(255) NOT NULL, num_piece VARCHAR(255) DEFAULT NULL, montant DOUBLE PRECISION NOT NULL, observation VARCHAR(255) NOT NULL, INDEX IDX_EBE4C14C6B899279 (patient_id), INDEX IDX_EBE4C14CE1AFE3AF (modereg_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE specialite (id INT AUTO_INCREMENT NOT NULL, code VARCHAR(255) NOT NULL, libelle VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE user (id INT AUTO_INCREMENT NOT NULL, email VARCHAR(180) NOT NULL, roles JSON NOT NULL, password VARCHAR(255) NOT NULL, UNIQUE INDEX UNIQ_8D93D649E7927C74 (email), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE consultation ADD CONSTRAINT FK_964685A66B899279 FOREIGN KEY (patient_id) REFERENCES patient (id)');
        $this->addSql('ALTER TABLE medecin ADD CONSTRAINT FK_1BDA53C62195E0F0 FOREIGN KEY (specialite_id) REFERENCES specialite (id)');
        $this->addSql('ALTER TABLE medecin ADD CONSTRAINT FK_1BDA53C6BCF5E72D FOREIGN KEY (categorie_id) REFERENCES categorie (id)');
        $this->addSql('ALTER TABLE medecin ADD CONSTRAINT FK_1BDA53C61B063272 FOREIGN KEY (nationalite_id) REFERENCES nationalite (id)');
        $this->addSql('ALTER TABLE ordonance ADD CONSTRAINT FK_99240B9C62FF6CDF FOREIGN KEY (consultation_id) REFERENCES consultation (id)');
        $this->addSql('ALTER TABLE ordonance ADD CONSTRAINT FK_99240B9CAB0D61F7 FOREIGN KEY (medicament_id) REFERENCES medicament (id)');
        $this->addSql('ALTER TABLE patient ADD CONSTRAINT FK_1ADAD7EB1B063272 FOREIGN KEY (nationalite_id) REFERENCES nationalite (id)');
        $this->addSql('ALTER TABLE patient ADD CONSTRAINT FK_1ADAD7EB4272FC9F FOREIGN KEY (domaine_id) REFERENCES domaine (id)');
        $this->addSql('ALTER TABLE patient ADD CONSTRAINT FK_1ADAD7EB80F7E20A FOREIGN KEY (assureur_id) REFERENCES assureur (id)');
        $this->addSql('ALTER TABLE reglement ADD CONSTRAINT FK_EBE4C14C6B899279 FOREIGN KEY (patient_id) REFERENCES patient (id)');
        $this->addSql('ALTER TABLE reglement ADD CONSTRAINT FK_EBE4C14CE1AFE3AF FOREIGN KEY (modereg_id) REFERENCES modereg (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE patient DROP FOREIGN KEY FK_1ADAD7EB80F7E20A');
        $this->addSql('ALTER TABLE medecin DROP FOREIGN KEY FK_1BDA53C6BCF5E72D');
        $this->addSql('ALTER TABLE ordonance DROP FOREIGN KEY FK_99240B9C62FF6CDF');
        $this->addSql('ALTER TABLE patient DROP FOREIGN KEY FK_1ADAD7EB4272FC9F');
        $this->addSql('ALTER TABLE ordonance DROP FOREIGN KEY FK_99240B9CAB0D61F7');
        $this->addSql('ALTER TABLE reglement DROP FOREIGN KEY FK_EBE4C14CE1AFE3AF');
        $this->addSql('ALTER TABLE medecin DROP FOREIGN KEY FK_1BDA53C61B063272');
        $this->addSql('ALTER TABLE patient DROP FOREIGN KEY FK_1ADAD7EB1B063272');
        $this->addSql('ALTER TABLE consultation DROP FOREIGN KEY FK_964685A66B899279');
        $this->addSql('ALTER TABLE reglement DROP FOREIGN KEY FK_EBE4C14C6B899279');
        $this->addSql('ALTER TABLE medecin DROP FOREIGN KEY FK_1BDA53C62195E0F0');
        $this->addSql('DROP TABLE assureur');
        $this->addSql('DROP TABLE categorie');
        $this->addSql('DROP TABLE compteur');
        $this->addSql('DROP TABLE consultation');
        $this->addSql('DROP TABLE domaine');
        $this->addSql('DROP TABLE horaire');
        $this->addSql('DROP TABLE medecin');
        $this->addSql('DROP TABLE medicament');
        $this->addSql('DROP TABLE modereg');
        $this->addSql('DROP TABLE nationalite');
        $this->addSql('DROP TABLE ordonance');
        $this->addSql('DROP TABLE patient');
        $this->addSql('DROP TABLE rdv');
        $this->addSql('DROP TABLE reglement');
        $this->addSql('DROP TABLE specialite');
        $this->addSql('DROP TABLE user');
    }
}
