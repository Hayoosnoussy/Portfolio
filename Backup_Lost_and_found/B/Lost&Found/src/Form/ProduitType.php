<?php

namespace App\Form;


use App\Entity\Marque;
use App\Entity\Produit;
use App\Entity\Category;
use App\Entity\SecCategory;

use App\Repository\MarqueRepository;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use App\Repository\CategoryRepository;
use App\Repository\SecCategoryRepository;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\File;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\CheckboxType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\FormEvent;
use Symfony\Component\Form\FormEvents;
use Symfony\Component\Form\FormInterface;



class ProduitType extends AbstractType
{  public function __construct(private SecCategoryRepository $secategoryRepository){}
    public function buildForm(FormBuilderInterface $builder, array $options)

    {
        $builder
            ->add('Status' , ChoiceType::class, [

                'choices'  => ['Lost'=>'Lost',
                    'Found'=>'Found' ]  ])
            ->add('Adress')

            ->add('Categorie' , EntityType::class, [
                'class' => Category::class,
                'choice_label' => 'name',
                'placeholder' => 'Category',
                'label'=>'Category',
                'query_builder' => fn (CategoryRepository $CategoryRepository) =>
                $CategoryRepository->findAllOrderedByAscNameQueryBuilder()
            ])

            ->add('marques' , EntityType::class, [
                'class' => Marque::class,
                'choice_label' => 'name',
                'placeholder' => 'Marque',
                'label'=>'Marque',
                'query_builder' => fn (MarqueRepository $MarqueRepository) =>
                $MarqueRepository->findAllOrderedByAscNameQueryBuilder()
            ])

            ->add('CarteType' , ChoiceType::class, [
                'mapped' => false,
                //    'expanded'=>true,
                'placeholder' => 'Choose',
                'choices'  => ['Carte Visa'=>'Carte Visa','Master Card'=>'Master Card'
                    ,'Carte Technologique'=>'Carte Technologique']  ])
            ->add('Bank' , ChoiceType::class, [
                'mapped' => false,
                'placeholder' => 'Choose',
                //    'expanded'=>true,
                'choices'  => ['Société Tunisienne de Banque « STB »'=>'STB',
                    'Banque Nationale Agricole « BNA »'=>'BNA',
                    'Banque de l’Habitat « BH »'=>'BH',
                    'BFPME'=>'BFPME',
                    'Banque Tunisienne de Solidarité « BTS »'=>'BTS',
                    'Banque de Tunisie et des Emirats « BTE »'=>'BTE',
                    'Banque Tuniso-Libyenne « BTL »'=>'BTL',
                    'Tunisian Saudi Bank « TSB »'=>'TSB',
                    'Banque Zitouna'=>'Banque Zitouna',
                    'Al Baraka Bank'=>'Al Baraka Bank',



                    'Al Wifak International Bank'=>'Al Wifak International Bank']  ])
            ->add('Date', null, array('label' => false) )
            ->add('owner_name')
            ->add('owner_ln')
            ->add('marque')
            ->add('photo',FileType::class, [
                // unmapped means that this field is not associated to any entity property
                'mapped' => false,
                //modification
                'required' => false,
                'constraints' => [
                    new File([
                        'mimeTypes' => [
                            'image/*',
                        ],
                        'mimeTypesMessage' => 'Veuillez ajouter une IMAGE',
                    ])
                ],])
            ->add('coleur')
            ->add('adress_supp')
            ->add('nationalite')
            ->add('objet_status')
            ->add('question_supp')
            ->add('nom_objet')
            ->add('details', TextareaType::class);

        $formModifier = function (FormInterface $form, Category $category = null) {
            $secCategory = $category === null ? [] : $this->secategoryRepository->findByCategoryOrderedByAscName($category);

            $form->add('SecCategorie', EntityType::class, [
                'class' => SecCategory::class,
                'choice_label' => 'name',

                'placeholder' => 'Choose a second',
                'choices' => $secCategory
            ]);
        };

        $builder->addEventListener(
            FormEvents::PRE_SET_DATA,
            function (FormEvent $event) use ($formModifier) {
                $data = $event->getData();

                $formModifier($event->getForm(), $data->getCategorie());
            }
        );

        $builder->get('Categorie')->addEventListener(
            FormEvents::POST_SUBMIT,
            function (FormEvent $event) use ($formModifier) {
                $category = $event->getForm()->getData();

                $formModifier($event->getForm()->getParent(),$category);
            }
        );



    }


    public function configureOptions(OptionsResolver $resolver)
    {

        $resolver->setDefaults([
            'data_class' => Produit::class,

        ]);
    }
}
