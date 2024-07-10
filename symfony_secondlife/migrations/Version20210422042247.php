<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210422042247 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE but CHANGE idBut idBut INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE compte ADD api_token VARCHAR(255) DEFAULT NULL, CHANGE isClosed isClosed TINYINT(1) NOT NULL');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_CFF652607BA2F5EB ON compte (api_token)');
        $this->addSql('ALTER TABLE compteadmin DROP FOREIGN KEY roleAdminFK');
        $this->addSql('DROP INDEX roleAdminFK_idx ON compteadmin');
        $this->addSql('ALTER TABLE compteadmin CHANGE compteLogin compteLogin INT DEFAULT NULL');
        $this->addSql('ALTER TABLE compteadmin ADD CONSTRAINT FK_8D1379F16ED49093 FOREIGN KEY (compteLogin) REFERENCES compte (idcompte)');
        $this->addSql('ALTER TABLE compteclient DROP FOREIGN KEY compteLogin');
        $this->addSql('DROP INDEX compteLogin_idx ON compteclient');
        $this->addSql('ALTER TABLE compteclient CHANGE compteLogin compteLogin INT DEFAULT NULL, CHANGE coachLogin coachLogin INT DEFAULT NULL');
        $this->addSql('ALTER TABLE compteclient ADD CONSTRAINT FK_B49D0FA26ED49093 FOREIGN KEY (compteLogin) REFERENCES compte (idcompte)');
        $this->addSql('ALTER TABLE comptecoach DROP FOREIGN KEY compteLoginCoach');
        $this->addSql('DROP INDEX roelCoachFK_idx ON comptecoach');
        $this->addSql('ALTER TABLE comptecoach CHANGE valide valide TINYINT(1) DEFAULT NULL, CHANGE compteLogin compteLogin INT DEFAULT NULL');
        $this->addSql('ALTER TABLE comptecoach ADD CONSTRAINT FK_3A44194B6ED49093 FOREIGN KEY (compteLogin) REFERENCES compte (idcompte)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE but CHANGE idBut idBut INT NOT NULL');
        $this->addSql('DROP INDEX UNIQ_CFF652607BA2F5EB ON compte');
        $this->addSql('ALTER TABLE compte DROP api_token, CHANGE isClosed isClosed TINYINT(1) DEFAULT \'0\' NOT NULL');
        $this->addSql('ALTER TABLE compteadmin DROP FOREIGN KEY FK_8D1379F16ED49093');
        $this->addSql('ALTER TABLE compteadmin CHANGE compteLogin compteLogin INT NOT NULL');
        $this->addSql('ALTER TABLE compteadmin ADD CONSTRAINT roleAdminFK FOREIGN KEY (compteLogin) REFERENCES compte (idcompte) ON UPDATE NO ACTION ON DELETE CASCADE');
        $this->addSql('CREATE INDEX roleAdminFK_idx ON compteadmin (compteLogin)');
        $this->addSql('ALTER TABLE compteclient DROP FOREIGN KEY FK_B49D0FA26ED49093');
        $this->addSql('ALTER TABLE compteclient CHANGE coachLogin coachLogin INT DEFAULT 0, CHANGE compteLogin compteLogin INT NOT NULL');
        $this->addSql('ALTER TABLE compteclient ADD CONSTRAINT compteLogin FOREIGN KEY (compteLogin) REFERENCES compte (idcompte) ON UPDATE NO ACTION ON DELETE CASCADE');
        $this->addSql('CREATE INDEX compteLogin_idx ON compteclient (compteLogin)');
        $this->addSql('ALTER TABLE comptecoach DROP FOREIGN KEY FK_3A44194B6ED49093');
        $this->addSql('ALTER TABLE comptecoach CHANGE valide valide TINYINT(1) DEFAULT \'0\', CHANGE compteLogin compteLogin INT NOT NULL');
        $this->addSql('ALTER TABLE comptecoach ADD CONSTRAINT compteLoginCoach FOREIGN KEY (compteLogin) REFERENCES compte (idcompte) ON UPDATE NO ACTION ON DELETE CASCADE');
        $this->addSql('CREATE INDEX roelCoachFK_idx ON comptecoach (compteLogin)');
    }
}
