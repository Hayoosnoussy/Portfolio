package repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import model.Produit;


public interface ProduitRepository extends JpaRepository<Produit , Long>{


	Iterable<Produit> findAllByNom(String cherche);

	Iterable<Produit> findAllByCategorie(String categorie);

	Iterable<Produit> findAllByVendeur(String cherche);


Long countByType(String type);

}
