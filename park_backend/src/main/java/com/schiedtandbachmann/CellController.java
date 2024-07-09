package com.schiedtandbachmann;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import data_cache.data.CellComputerDetail;


@RestController
@RequestMapping("/api")
public class CellController {
	
	
	 @Autowired
	    private RestTemplate restTemplate;
	
	 /// les données lkol 
	 
	    @GetMapping("/cellComputersDetailList/{appId}")
	    public ResponseEntity<String> getCellComputers1() {
	    	String appId="13";
	        String apiUrl = "https://kong.dev.parking.scheidt-bachmann.net/appconfig-service/v1/cellComputersDetailList/" + appId;
	        RestTemplate restTemplate = new RestTemplate();
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("correlationId", "10203");
	        headers.set("X-SampB-SelfCheckout-App", "SampB-SelfCheckout-2021");
	      
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> entity = new HttpEntity<>(headers);
	        ResponseEntity<String> response = restTemplate.exchange(
	                apiUrl,
	                HttpMethod.GET,
	                entity,
	                String.class
	        );
	        return response;
	    }
	    
		   
	    
	    
	   
	    
	    
	   
	    

	    
	    
}


