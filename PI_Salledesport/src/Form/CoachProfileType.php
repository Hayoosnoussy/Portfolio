<?php

namespace App\Form;

use App\Entity\Coach;
use phpDocumentor\Reflection\Types\Integer;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\OptionsResolver\OptionsResolver;

class CoachProfileType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('image',FileType::class ,array('data_class' => null))
            ->add('age')
            ->add('Poid', IntegerType::class, [
                'attr'=>[
                    'placeholder' => 'Prenom',
                    'class' => 'form-control'
                ]
            ])

            ->add('Hauteur')
            ->add('facebook')
            ->add('insta')
            ->add('gmail')
            ->add('bio')


        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Coach::class,
        ]);
    }
}
