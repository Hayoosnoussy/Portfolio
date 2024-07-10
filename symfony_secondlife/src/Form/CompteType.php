<?php

namespace App\Form;

use App\Entity\Compte;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\File;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\NotBlank;


class CompteType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    { //tnajem taamel if lahné tamel b variable f args
        $builder
            ->add('login')
         //   ->add('password')
         ->add('plainPassword', PasswordType::class, [
             // instead of being set onto the object directly,
             // this is read and encoded in the controller
             'mapped' => false,
             'constraints' => [
                 new NotBlank([
                     'message' => 'Please enter a password',
                 ]),
                 new Length([
                     'min' => 6,
                     'minMessage' => 'Your password should be at least {{ limit }} characters',
                     // max length allowed by Symfony for security reasons
                     'max' => 4096,
                 ]),
             ],
         ])
            ->add('nom')
            ->add('prenom')
            ->add('age')
            ->add('telephone')
       //     ->add('imgurl')
        ->add('imgurl', FileType::class, [
            'label' => 'Photo ',
            // unmapped means that this field is not associated to any entity property
            'mapped' => false,
            // make it optional so you don't have to re-upload the PDF file
            // every time you edit the Product details
            'required' => false,
            // unmapped fields can't define their validation using annotations
            // in the associated entity, so you can use the PHP constraint classes
            'constraints' => [
                new File([
                    'maxSize' => '1024k',
                    'mimeTypes' => [
                        'image/png',
                        'image/jpeg',
                        'image/jpg',
                        'image/x-jpg',
                        'image/x-jpeg'
                    ],
                    'mimeTypesMessage' => 'Please upload a valid Image',
                ])
            ],
        ])
            ->add('aboutme')
            ->add('ville')
            ->add('adresse')
            ->add('email')
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Compte::class,
        ]);
    }
}
