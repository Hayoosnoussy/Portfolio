package com.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map; 
import java.util.Optional;

import javax.servlet.ServletContext;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import javax.servlet.ServletContext;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



import exception.ResourceNotFoundException;
import model.Produit;
import repository.ProduitRepository;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProduitController {
private String h="true";
	@Autowired  ServletContext context;

	@Autowired
	private ProduitRepository produitRepository;
	@GetMapping("/produits")
	  public List<Produit> getAllProduits() {
	    System.out.println("Get all Produits...");
	 
	    List<Produit> Produits = new ArrayList<>();
	    produitRepository.findAll().forEach(Produits::add);
	 
	    return Produits;
	  }
	@GetMapping("/produits/10")
	public Long Stat() {
		Long nb = produitRepository.countByType("fiction");
	    System.out.println("Nombre de produits..."+nb);		
		return nb;}
	@GetMapping("/produits/11")
	public Long State() {
		Long nb = produitRepository.countByType("Non");
	    System.out.println("Nombre de produits..."+nb);		
		return nb;}
	@GetMapping("/produits/12")
	public Long Statr() {
		Long nb = produitRepository.countByType("Enfant");
	    System.out.println("Nombre de produits..."+nb);		
		return nb;}
	@GetMapping("/produits/13")
	public Long Statt() {
		Long nb = produitRepository.countByType("Éducation");
	    System.out.println("Nombre de produits..."+nb);		
		return nb;}
	@GetMapping("/produits/14")
	public Long Staty() {
		Long nb = produitRepository.countByType("cadeaux");
	    System.out.println("Nombre de produits..."+nb);		
		return nb;}
	
	@GetMapping("/produits/5/{categorie}")
	public List<Produit> getAllProduitsbyCateg(@PathVariable String categorie)
			 {
		List<Produit> Produits = new ArrayList<>();
		produitRepository.findAllByCategorie(categorie).forEach(Produits::add);
				return Produits;
	}
	@GetMapping("/produits/3/{cherche}")
	public List<Produit> getAllProduitsbyNom(@PathVariable String cherche)
			 {
		List<Produit> Produits = new ArrayList<>();
		produitRepository.findAllByNom(cherche).forEach(Produits::add);	
		produitRepository.findAllByVendeur(cherche).forEach(Produits::add);

				return Produits;
	}


	@GetMapping("/produits/{id}")
	public ResponseEntity<Produit> getProduitById(@PathVariable(value = "id") Long ProduitId)
			throws ResourceNotFoundException {
		Produit Produit = produitRepository.findById(ProduitId)
				.orElseThrow(() -> new ResourceNotFoundException("Produit not found for this id :: " + ProduitId));
		return ResponseEntity.ok().body(Produit);
	}
	
	 @GetMapping(path="/Imgproduit/{id}")
	 public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
		 Produit Produit   = produitRepository.findById(id).get();
		 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+Produit.getFileName()));
	 }
	@PostMapping("/produits")
	 public ResponseEntity<Object> createProduit (@RequestParam("file") MultipartFile file,
			 @RequestParam("produit") String produit) throws JsonParseException , JsonMappingException , Exception
	 {
		
		 System.out.println("Ok .............");
      Produit arti = new ObjectMapper().readValue(produit, Produit.class);
 
      boolean isExit = new File(context.getRealPath("/Images/")).exists();
      if (!isExit)
      {
      	new File (context.getRealPath("/Images/")).mkdir();
      	System.out.println("mk dir.............");
      }
      String filename = file.getOriginalFilename();
      String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
      File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
      try
      {
      	System.out.println("Image");
      	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
      	 
      }catch(Exception e) {
      	e.printStackTrace();
      }
      if((arti.getPrix()) < 1) {
			Map<String,Object> body = new LinkedHashMap<>();
			body.put("message","Prix incorrect ");
			System.out.println("soldee");
			return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
		}
     
      arti.setFileName(newFileName);
		return new ResponseEntity<>(produitRepository.save(arti),HttpStatus.OK);
	 }

	@DeleteMapping("/produits/{id}")
	public Map<String, Boolean> deleteProduit(@PathVariable(value = "id") Long ProduitId)
			throws ResourceNotFoundException {
		Produit Produit = produitRepository.findById(ProduitId)
				.orElseThrow(() -> new ResourceNotFoundException("Produit not found  id :: " + ProduitId));

		produitRepository.delete(Produit);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/produits/delete")
	  public ResponseEntity<String> deleteAllProduits() {
	    System.out.println("Delete All Produits...");
	 
	    produitRepository.deleteAll();
	 
	    return new ResponseEntity<>("All Produits have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/produits/{id}")
	  public ResponseEntity<Produit> updateProduit(@PathVariable("id") long id, @RequestBody Produit Produit) {
	    System.out.println("Update Produit with ID = " + id + "...");
	 
	    Optional<Produit> ProduitInfo = produitRepository.findById(id);
	 
	    if (ProduitInfo.isPresent()) {
	    	Produit produit = ProduitInfo.get();
	           produit.setNom(Produit.getNom());
	           produit.setLangue(Produit.getLangue());
	           produit.setType(Produit.getType());
	           produit.setVendeur(Produit.getVendeur());
	           produit.setPrix(Produit.getPrix());

	         
	           
	           
	      return new ResponseEntity<>(produitRepository.save(Produit), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    } 
	  }

	  
	  @PutMapping("/produits/8/{id}")
	  public ResponseEntity<Produit> updateeProduit(@PathVariable("id") long id, @RequestBody Produit Produit) {
	    System.out.println("Update Produit with ID = " + id + "..."+ h);
	 
	    Optional<Produit> ProduitInfo = produitRepository.findById(id);
	 
	    if (ProduitInfo.isPresent()) {
	    	Produit produit = ProduitInfo.get();
	   	    System.out.println("hello  " + produit.getStatut() );

	           produit.setStatut(h);
	   	    System.out.println("voilaaa         " + produit.getStatut() );

	      return new ResponseEntity<>(produitRepository.save(produit), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    } 
	  }
	  
}
