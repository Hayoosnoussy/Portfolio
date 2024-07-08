<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210614001951 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE SEQUENCE assureur_id_seq INCREMENT BY 1 MINVALUE 1 START 1');
        $this->addSql('CREATE SEQUENCE categorie_id_seq INCREMENT BY 1 MINVALUE 1 START 1');
        $this->addSql('CREATE SEQUENCE compteur_id_seq INCREMENT BY 1 MINVALUE 1 START 1');
        $this->addSql('CREATE SEQUENCE consultation_id_seq INCREMENT BY 1 MINVALUE 1 START 1');
        $this->addSql('CREATE SEQUENCE domaine_id_seq INCREMENT BY 1 MINVALUE 1 START 1');
        $this->addSql('CREATE SEQUENCE horaire_id_seq INCREMENT BY 1 MINVALUE 1 START 1');
        $this->addSql('CREATE SEQUENCE medecin_id_seq INCREMENT BY 1 MINVALUE 1 START 1');
        $this->addSql('CREATE SEQUENCE medicament_id_seq INCREMENT BY 1 MINVALUE 1 START 1');
        $this->addSql('CREATE SEQUENCE modereg_id_seq INCREMENT BY 1 MINVALUE 1 START 1');
        $this->addSql('CREATE SEQUENCE nationalite_id_seq INCREMENT BY 1 MINVALUE 1 START 1');
        $this->addSql('CREATE SEQUENCE ordonance_id_seq INCREMENT BY 1 MINVALUE 1 START 1');
        $this->addSql('CREATE SEQUENCE patient_id_seq INCREMENT BY 1 MINVALUE 1 START 1');
        $this->addSql('CREATE SEQUENCE reglement_id_seq INCREMENT BY 1 MINVALUE 1 START 1');
        $this->addSql('CREATE SEQUENCE specialite_id_seq INCREMENT BY 1 MINVALUE 1 START 1');
        $this->addSql('CREATE SEQUENCE user_id_seq INCREMENT BY 1 MINVALUE 1 START 1');
        $this->addSql('CREATE TABLE assureur (id INT NOT NULL, code VARCHAR(255) NOT NULL, libelle VARCHAR(255) NOT NULL, PRIMARY KEY(id))');
        $this->addSql('CREATE TABLE categorie (id INT NOT NULL, code VARCHAR(255) NOT NULL, libelle VARCHAR(255) NOT NULL, PRIMARY KEY(id))');
        $this->addSql('CREATE TABLE compteur (id INT NOT NULL, annee INT NOT NULL, numconsultation INT NOT NULL, numrdv INT NOT NULL, PRIMARY KEY(id))');
        $this->addSql('CREATE TABLE consultation (id INT NOT NULL, patient_id INT NOT NULL, numero INT NOT NULL, annee INT NOT NULL, date_consultation DATE NOT NULL, observation VARCHAR(255) NOT NULL, montant DOUBLE PRECISION NOT NULL, PRIMARY KEY(id))');
        $this->addSql('CREATE INDEX IDX_964685A66B899279 ON consultation (patient_id)');
        $this->addSql('CREATE TABLE domaine (id INT NOT NULL, code VARCHAR(255) NOT NULL, libelle VARCHAR(255) NOT NULL, PRIMARY KEY(id))');
        $this->addSql('CREATE TABLE horaire (id INT NOT NULL, code VARCHAR(255) NOT NULL, hd TIME(0) WITHOUT TIME ZONE NOT NULL, hf TIME(0) WITHOUT TIME ZONE NOT NULL, PRIMARY KEY(id))');
        $this->addSql('CREATE TABLE medecin (id INT NOT NULL, specialite_id INT NOT NULL, categorie_id INT NOT NULL, nationalite_id INT DEFAULT NULL, matricule VARCHAR(255) NOT NULL, nom VARCHAR(255) NOT NULL, prenom VARCHAR(255) NOT NULL, date_naissance DATE DEFAULT NULL, adresse VARCHAR(255) DEFAULT NULL, ville VARCHAR(255) DEFAULT NULL, tel VARCHAR(255) NOT NULL, gsm VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL, fax VARCHAR(255) DEFAULT NULL, genre VARCHAR(255) NOT NULL, PRIMARY KEY(id))');
        $this->addSql('CREATE INDEX IDX_1BDA53C62195E0F0 ON medecin (specialite_id)');
        $this->addSql('CREATE INDEX IDX_1BDA53C6BCF5E72D ON medecin (categorie_id)');
        $this->addSql('CREATE INDEX IDX_1BDA53C61B063272 ON medecin (nationalite_id)');
        $this->addSql('CREATE TABLE medicament (id INT NOT NULL, code VARCHAR(255) NOT NULL, libelle VARCHAR(255) NOT NULL, duree VARCHAR(255) NOT NULL, utilisation VARCHAR(255) NOT NULL, ctr_indication VARCHAR(255) NOT NULL, PRIMARY KEY(id))');
        $this->addSql('CREATE TABLE modereg (id INT NOT NULL, code VARCHAR(255) NOT NULL, libelle VARCHAR(255) NOT NULL, PRIMARY KEY(id))');
        $this->addSql('CREATE TABLE nationalite (id INT NOT NULL, code VARCHAR(255) NOT NULL, libelle VARCHAR(255) NOT NULL, PRIMARY KEY(id))');
        $this->addSql('CREATE TABLE ordonance (id INT NOT NULL, consultation_id INT DEFAULT NULL, medicament_id INT DEFAULT NULL, code VARCHAR(255) NOT NULL, nbr INT NOT NULL, duree VARCHAR(255) NOT NULL, utilisation VARCHAR(255) NOT NULL, PRIMARY KEY(id))');
        $this->addSql('CREATE INDEX IDX_99240B9C62FF6CDF ON ordonance (consultation_id)');
        $this->addSql('CREATE INDEX IDX_99240B9CAB0D61F7 ON ordonance (medicament_id)');
        $this->addSql('CREATE TABLE patient (id INT NOT NULL, nationalite_id INT NOT NULL, domaine_id INT DEFAULT NULL, assureur_id INT DEFAULT NULL, nom VARCHAR(255) NOT NULL, prenom VARCHAR(255) NOT NULL, date_naissance DATE NOT NULL, adresse VARCHAR(255) NOT NULL, ville VARCHAR(255) NOT NULL, tel VARCHAR(255) NOT NULL, gsm VARCHAR(255) NOT NULL, genre VARCHAR(255) NOT NULL, etat_civil VARCHAR(255) NOT NULL, nbr_enfant INT NOT NULL, nom_conjoint VARCHAR(255) NOT NULL, lien_parente VARCHAR(255) NOT NULL, taille DOUBLE PRECISION NOT NULL, poids INT NOT NULL, gr_sanguin VARCHAR(255) NOT NULL, profession VARCHAR(255) DEFAULT NULL, ident_unique VARCHAR(255) DEFAULT NULL, prise_encharge VARCHAR(255) NOT NULL, medecin VARCHAR(255) DEFAULT NULL, datep_cons DATE DEFAULT NULL, date_dcons DATE DEFAULT NULL, motcles VARCHAR(255) DEFAULT NULL, code_apci VARCHAR(255) DEFAULT NULL, regime_cnam VARCHAR(255) NOT NULL, datevalidite DATE DEFAULT NULL, code VARCHAR(255) NOT NULL, lieu VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL, PRIMARY KEY(id))');
        $this->addSql('CREATE INDEX IDX_1ADAD7EB1B063272 ON patient (nationalite_id)');
        $this->addSql('CREATE INDEX IDX_1ADAD7EB4272FC9F ON patient (domaine_id)');
        $this->addSql('CREATE INDEX IDX_1ADAD7EB80F7E20A ON patient (assureur_id)');
        $this->addSql('CREATE TABLE reglement (id INT NOT NULL, patient_id INT NOT NULL, modereg_id INT NOT NULL, numero INT NOT NULL, annee INT NOT NULL, date_reglement DATE NOT NULL, mod_reglement VARCHAR(255) NOT NULL, num_piece VARCHAR(255) DEFAULT NULL, montant DOUBLE PRECISION NOT NULL, observation VARCHAR(255) NOT NULL, PRIMARY KEY(id))');
        $this->addSql('CREATE INDEX IDX_EBE4C14C6B899279 ON reglement (patient_id)');
        $this->addSql('CREATE INDEX IDX_EBE4C14CE1AFE3AF ON reglement (modereg_id)');
        $this->addSql('CREATE TABLE specialite (id INT NOT NULL, code VARCHAR(255) NOT NULL, libelle VARCHAR(255) NOT NULL, PRIMARY KEY(id))');
        $this->addSql('CREATE TABLE "user" (id INT NOT NULL, email VARCHAR(180) NOT NULL, roles JSON NOT NULL, password VARCHAR(255) NOT NULL, PRIMARY KEY(id))');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_8D93D649E7927C74 ON "user" (email)');
        $this->addSql('ALTER TABLE consultation ADD CONSTRAINT FK_964685A66B899279 FOREIGN KEY (patient_id) REFERENCES patient (id) NOT DEFERRABLE INITIALLY IMMEDIATE');
        $this->addSql('ALTER TABLE medecin ADD CONSTRAINT FK_1BDA53C62195E0F0 FOREIGN KEY (specialite_id) REFERENCES specialite (id) NOT DEFERRABLE INITIALLY IMMEDIATE');
        $this->addSql('ALTER TABLE medecin ADD CONSTRAINT FK_1BDA53C6BCF5E72D FOREIGN KEY (categorie_id) REFERENCES categorie (id) NOT DEFERRABLE INITIALLY IMMEDIATE');
        $this->addSql('ALTER TABLE medecin ADD CONSTRAINT FK_1BDA53C61B063272 FOREIGN KEY (nationalite_id) REFERENCES nationalite (id) NOT DEFERRABLE INITIALLY IMMEDIATE');
        $this->addSql('ALTER TABLE ordonance ADD CONSTRAINT FK_99240B9C62FF6CDF FOREIGN KEY (consultation_id) REFERENCES consultation (id) NOT DEFERRABLE INITIALLY IMMEDIATE');
        $this->addSql('ALTER TABLE ordonance ADD CONSTRAINT FK_99240B9CAB0D61F7 FOREIGN KEY (medicament_id) REFERENCES medicament (id) NOT DEFERRABLE INITIALLY IMMEDIATE');
        $this->addSql('ALTER TABLE patient ADD CONSTRAINT FK_1ADAD7EB1B063272 FOREIGN KEY (nationalite_id) REFERENCES nationalite (id) NOT DEFERRABLE INITIALLY IMMEDIATE');
        $this->addSql('ALTER TABLE patient ADD CONSTRAINT FK_1ADAD7EB4272FC9F FOREIGN KEY (domaine_id) REFERENCES domaine (id) NOT DEFERRABLE INITIALLY IMMEDIATE');
        $this->addSql('ALTER TABLE patient ADD CONSTRAINT FK_1ADAD7EB80F7E20A FOREIGN KEY (assureur_id) REFERENCES assureur (id) NOT DEFERRABLE INITIALLY IMMEDIATE');
        $this->addSql('ALTER TABLE reglement ADD CONSTRAINT FK_EBE4C14C6B899279 FOREIGN KEY (patient_id) REFERENCES patient (id) NOT DEFERRABLE INITIALLY IMMEDIATE');
        $this->addSql('ALTER TABLE reglement ADD CONSTRAINT FK_EBE4C14CE1AFE3AF FOREIGN KEY (modereg_id) REFERENCES modereg (id) NOT DEFERRABLE INITIALLY IMMEDIATE');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE SCHEMA public');
        $this->addSql('ALTER TABLE patient DROP CONSTRAINT FK_1ADAD7EB80F7E20A');
        $this->addSql('ALTER TABLE medecin DROP CONSTRAINT FK_1BDA53C6BCF5E72D');
        $this->addSql('ALTER TABLE ordonance DROP CONSTRAINT FK_99240B9C62FF6CDF');
        $this->addSql('ALTER TABLE patient DROP CONSTRAINT FK_1ADAD7EB4272FC9F');
        $this->addSql('ALTER TABLE ordonance DROP CONSTRAINT FK_99240B9CAB0D61F7');
        $this->addSql('ALTER TABLE reglement DROP CONSTRAINT FK_EBE4C14CE1AFE3AF');
        $this->addSql('ALTER TABLE medecin DROP CONSTRAINT FK_1BDA53C61B063272');
        $this->addSql('ALTER TABLE patient DROP CONSTRAINT FK_1ADAD7EB1B063272');
        $this->addSql('ALTER TABLE consultation DROP CONSTRAINT FK_964685A66B899279');
        $this->addSql('ALTER TABLE reglement DROP CONSTRAINT FK_EBE4C14C6B899279');
        $this->addSql('ALTER TABLE medecin DROP CONSTRAINT FK_1BDA53C62195E0F0');
        $this->addSql('DROP SEQUENCE assureur_id_seq CASCADE');
        $this->addSql('DROP SEQUENCE categorie_id_seq CASCADE');
        $this->addSql('DROP SEQUENCE compteur_id_seq CASCADE');
        $this->addSql('DROP SEQUENCE consultation_id_seq CASCADE');
        $this->addSql('DROP SEQUENCE domaine_id_seq CASCADE');
        $this->addSql('DROP SEQUENCE horaire_id_seq CASCADE');
        $this->addSql('DROP SEQUENCE medecin_id_seq CASCADE');
        $this->addSql('DROP SEQUENCE medicament_id_seq CASCADE');
        $this->addSql('DROP SEQUENCE modereg_id_seq CASCADE');
        $this->addSql('DROP SEQUENCE nationalite_id_seq CASCADE');
        $this->addSql('DROP SEQUENCE ordonance_id_seq CASCADE');
        $this->addSql('DROP SEQUENCE patient_id_seq CASCADE');
        $this->addSql('DROP SEQUENCE reglement_id_seq CASCADE');
        $this->addSql('DROP SEQUENCE specialite_id_seq CASCADE');
        $this->addSql('DROP SEQUENCE user_id_seq CASCADE');
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
        $this->addSql('DROP TABLE reglement');
        $this->addSql('DROP TABLE specialite');
        $this->addSql('DROP TABLE "user"');
    }
}
