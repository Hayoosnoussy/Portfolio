<?php

namespace App\Repository;

use App\Entity\Modereg;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Modereg|null find($id, $lockMode = null, $lockVersion = null)
 * @method Modereg|null findOneBy(array $criteria, array $orderBy = null)
 * @method Modereg[]    findAll()
 * @method Modereg[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ModeregRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Modereg::class);
    }

    // /**
    //  * @return Modereg[] Returns an array of Modereg objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('m')
            ->andWhere('m.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('m.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Modereg
    {
        return $this->createQueryBuilder('m')
            ->andWhere('m.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
