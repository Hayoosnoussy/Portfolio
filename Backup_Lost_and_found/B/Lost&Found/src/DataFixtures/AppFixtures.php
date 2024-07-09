<?php

namespace App\DataFixtures;

use App\Entity\Category;
use App\Entity\SecCategory;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Persistence\ObjectManager;

class AppFixtures extends Fixture
{
    public function load(ObjectManager $manager)
    {
        //creation des cat et sous cat $wallet
        // WALLET
        $wallet = new Category();
        $wallet->setName('Wallet, Credit card & money');

        $cartbleu = new SecCategory();
        $cartbleu->setName('Credit card');
        $cartbleu->setCategory($wallet);

        $argent = new SecCategory();
        $argent->setName('Money , currency');
        $argent->setCategory($wallet);

        $portefeuil_monnai = new SecCategory();
        $portefeuil_monnai->setName('Wallet');
        $portefeuil_monnai->setCategory($wallet);

        $chequiers = new SecCategory();
        $chequiers->setName('ChequeBooks');
        $chequiers->setCategory($wallet);

        // Documents
        $doc = new Category();
        $doc->setName('Identity document');

        $cin = new SecCategory();
        $cin->setName('ID Card');
        $cin->setCategory($doc);

        $passport = new SecCategory();
        $passport->setName('Passport');
        $passport->setCategory($doc);

        $permis = new SecCategory();
        $permis->setName('Driving license');
        $permis->setCategory($doc);

        $fdc = new SecCategory();
        $fdc->setName('Fidelity card');
        $fdc->setCategory($doc);

        $stc = new SecCategory();
        $stc->setName('Student card');
        $stc->setCategory($doc);

        $health = new SecCategory();
        $health->setName('Health docs');
        $health->setCategory($doc);

        $transport = new SecCategory();
        $transport->setName('Transport card');
        $transport->setCategory($doc);

        $other = new SecCategory();
        $other->setName('Other personal card');
        $other->setCategory($doc);

        //BAGS

        //final perssist
        $manager->persist($wallet);
        $manager->persist($cartbleu);
        $manager->persist($argent);
        $manager->persist($portefeuil_monnai);
        $manager->persist($chequiers);
        $manager->persist($doc);
        $manager->persist($cin);
        $manager->persist($passport);
        $manager->persist($permis);
        $manager->persist($fdc);
        $manager->persist($stc);
        $manager->persist($health);
        $manager->persist($transport);
        $manager->persist($other);











        $manager->flush();
    }
}
