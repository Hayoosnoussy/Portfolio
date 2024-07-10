package com.esprit.examen.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.PostDto;
import com.esprit.examen.services.IFactureService;
import org.modelmapper.ModelMapper;
import io.swagger.annotations.Api;


@RestController
@Api(tags = "Gestion des factures")
@RequestMapping("/facture")
public class FactureRestController {

    @Autowired
    IFactureService factureService;
    @Autowired
    private ModelMapper modelMapper;
	
    public FactureRestController(IFactureService factureService) {
		super();
		this.factureService = factureService;
	}

    // http://localhost:8089/SpringMVC/facture/retrieve-all-factures
    @GetMapping("/retrieve-all-factures")
    @ResponseBody
    public List<Facture> getFactures() {
    	return factureService.retrieveAllFactures();
        
    }

    // http://localhost:8089/SpringMVC/facture/retrieve-facture/8
    @GetMapping("/retrieve-facture/{facture-id}")
    @ResponseBody
    public Facture retrieveFacture(@PathVariable("facture-id") Long factureId) {
        return factureService.retrieveFacture(factureId);
    }

    @PostMapping("/add-facture")
    @ResponseBody
    public ResponseEntity<PostDto> addFacture(@RequestBody PostDto f) {
		Facture op = modelMapper.map(f, Facture.class);
		Facture facture = factureService.addFacture(op);
		PostDto factureResponse = modelMapper.map(facture, PostDto.class);
		return new ResponseEntity<>(factureResponse, HttpStatus.CREATED);
    }

    /*
     * une facture peut etre annulé si elle a été saisie par erreur Pour ce
     * faire, il suffit de mettre le champs active à false
     */
  
    @PutMapping("/cancel-facture/{facture-id}")
    @ResponseBody
    public void deleteFacture(@PathVariable("facture-id") Long factureId) {
        factureService.deleteFacture(factureId);
    }


	// http://localhost:8089/SpringMVC/operateur/modify-operateur
	@PutMapping("/modify-facture")
	@ResponseBody
	public ResponseEntity<PostDto> modifyOperateur(@RequestBody PostDto f) {
		
		Facture op = modelMapper.map(f, Facture.class);
		Facture facture = factureService.addFacture(op);
		PostDto factureResponse = modelMapper.map(facture, PostDto.class);
		return new ResponseEntity<>(factureResponse, HttpStatus.ACCEPTED);
	}

    }

