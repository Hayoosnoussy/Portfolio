package com.esprit.examen.controllers;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.esprit.examen.entities.PostDto;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.services.IReglementService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "Gestion des reglements")
@RequestMapping("/reglement")
public class ReglementRestController {

    @Autowired
    IReglementService reglementService;
    
    @Autowired
    private ModelMapper modelMapper;

    // http://localhost:8089/SpringMVC/reglement/add-reglement
    @PostMapping("/add-reglement")
    @ResponseBody
    public ResponseEntity<PostDto> addReglement(@RequestBody PostDto r) {
		Reglement regpost = modelMapper.map(r, Reglement.class);
		Reglement reglement = reglementService.addReglement(regpost);
		PostDto factureResponse = modelMapper.map(reglement, PostDto.class);
		return new ResponseEntity<>(factureResponse, HttpStatus.CREATED);
    }

    @GetMapping("/retrieve-all-reglements")
    @ResponseBody
    public List<Reglement> getReglement() {      
        return reglementService.retrieveAllReglements();
        
    }

    // http://localhost:8089/SpringMVC/reglement/retrieve-reglement/8
    @GetMapping("/retrieve-reglement/{reglement-id}")
    @ResponseBody
    public Reglement retrieveReglement(@PathVariable("reglement-id") Long reglementId) {
    	
        return reglementService.retrieveReglement(reglementId);
    }
    
    
    // http://localhost:8089/SpringMVC/reglement/retrieveReglementByFacture/8
    @GetMapping("/retrieveReglementByFacture/{facture-id}")
    @ResponseBody
    public List<Reglement> retrieveReglementByFacture(@PathVariable("facture-id") Long factureId) {
        return reglementService.retrieveReglementByFacture(factureId);
    }
        
 
    @GetMapping(value = "/getChiffreAffaireEntreDeuxDate/{startDate}/{endDate}")
    public float getChiffreAffaireEntreDeuxDate(
            @PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        try {
            return reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);
        } catch (Exception e) {
            return 0;
        }
    }
}
