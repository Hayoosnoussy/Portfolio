package com.schiedtandbachmann;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import data_cache.data.CellComputerDetail;


@RestController
@RequestMapping("/api")

public class CountryController {

////this one with all the country codes in the cell computers 
	    @GetMapping("/unused/")
	    public ResponseEntity<List<String>> getCellComputerCountryCodes(String appId) {
	    	appId="13";
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

	        ObjectMapper mapper = new ObjectMapper();
	        List<String> countryCodes = new ArrayList<>();
	        try {
	            JsonNode root = mapper.readTree(response.getBody());
	            JsonNode cellComputersNode = root.get("cellComputers");
	            if (cellComputersNode != null && cellComputersNode.isArray()) {
	                for (JsonNode node : cellComputersNode) {
	                    CellComputerDetail cellComputer = mapper.treeToValue(node, CellComputerDetail.class);
	                    String countryCode = cellComputer.getCountryCode();
	                    countryCodes.add(countryCode);
	                }
	            }
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }

	        return new ResponseEntity<>(countryCodes, HttpStatus.OK);
	    }
    
    //Les pays sans redondance 
    @GetMapping("/unusedcountrynodoubles")
    public ResponseEntity<List<String>> getCellComputerCountryCodess(String appId) {
    	appId="13";
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

        ObjectMapper mapper = new ObjectMapper();
        Set<String> countryCodes = new HashSet<>(); // Use Set to eliminate duplicates
        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode cellComputersNode = root.get("cellComputers");
            if (cellComputersNode != null && cellComputersNode.isArray()) {
                for (JsonNode node : cellComputersNode) {
                    JsonNode countryCodeNode = node.get("countryCode");
                    if (countryCodeNode != null) {
                        countryCodes.add(countryCodeNode.asText());
                    }
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(countryCodes), HttpStatus.OK);
    }
/////////// 
    /// les pays avec les cités 
    
    @GetMapping("/unusecountrywithcities")
    public ResponseEntity<Map<String, List<String>>> getCountryCodes(String appId) {
        appId = "13";
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

        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<String>> countryCityMap = new HashMap<>(); // Map to store country-city pairs
        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode cellComputersNode = root.get("cellComputers");
            if (cellComputersNode != null && cellComputersNode.isArray()) {
                for (JsonNode node : cellComputersNode) {
                    JsonNode countryCodeNode = node.get("countryCode");
                    JsonNode cityRegionNode = node.get("cityRegion");
                    if (countryCodeNode != null && cityRegionNode != null) {
                        String countryCode = countryCodeNode.asText();
                        String city = cityRegionNode.asText();
                        List<String> cities = countryCityMap.getOrDefault(countryCode, new ArrayList<>());
                        cities.add(city);
                        countryCityMap.put(countryCode, cities);
                    }
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(countryCityMap, HttpStatus.OK);
    }

    // les pays avec les cités sans redondance 
    
    
    @GetMapping("/unusedcountryandcitiesnodoubles")
    public ResponseEntity<Map<String, List<String>>> getCountryCodesAndCities(String appId) {
        appId = "13";
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

        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<String>> countryCityMap = new HashMap<>(); // Map to store country-city pairs

        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode cellComputersNode = root.get("cellComputers");
            if (cellComputersNode != null && cellComputersNode.isArray()) {
                for (JsonNode node : cellComputersNode) {
                    JsonNode countryCodeNode = node.get("countryCode");
                    JsonNode cityRegionNode = node.get("cityRegion");
                    if (countryCodeNode != null && cityRegionNode != null) {
                        String countryCode = countryCodeNode.asText();
                        String cityRegion = cityRegionNode.asText();
                        if (!countryCityMap.containsKey(countryCode)) {
                            countryCityMap.put(countryCode, new ArrayList<>());
                        }
                        List<String> cities = countryCityMap.get(countryCode);
                        if (!cities.contains(cityRegion)) {
                            cities.add(cityRegion);
                        }
                    }
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(countryCityMap, HttpStatus.OK);
    }

   
	////// final avec le clé et le valeur de map
    private ResponseEntity<Map<String, Object>> getFinal() {
        String appId = "13";
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

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Map<String, Object>> countryMap = new HashMap<>(); // Map to store country objects

        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode cellComputersNode = root.get("cellComputers");
            if (cellComputersNode != null && cellComputersNode.isArray()) {
                for (JsonNode node : cellComputersNode) {
                    JsonNode countryCodeNode = node.get("countryCode");
                    JsonNode cityRegionNode = node.get("cityRegion");
                    if (countryCodeNode != null && cityRegionNode != null) {
                        String countryCode = countryCodeNode.asText();
                        String cityRegion = cityRegionNode.asText();
                        if (!countryMap.containsKey(countryCode)) {
                            Map<String, Object> countryObject = new HashMap<>();
                            countryObject.put("code", countryCode);
                            countryObject.put("cities", new ArrayList<String>());
//                            String countryName;
//                            if (countryCode.equals("DE")) {
//                                countryName = "Germany";
//                            } else if (countryCode.equals("SK")) {
//                                countryName = "Slovakia";
//                            }
//                            else if (countryCode.equals("TN")) {
//                                countryName = "Tunisia";
//                            }else {
//                                countryName = countryCode;
//                            }
//                            countryObject.put("name", countryName);
                            countryObject.put("name", getCountryName(countryCode));
                            
                         
                            countryMap.put(countryCode, countryObject);
                        }
                        Map<String, Object> countryObject = countryMap.get(countryCode);
                        List<String> cities = (List<String>) countryObject.get("cities");
                        if (!cities.contains(cityRegion)) {
                            cities.add(cityRegion);
                        }
                        
                    }
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("countries", countryMap);
        return ResponseEntity.ok().body(responseMap);
    }
  //welyeeey rediiss nd it worked
    @GetMapping("/countries-list")
    @Cacheable(cacheNames = "cachedCountries")
    public Map<String, Object> getCachedCountries() {
        ResponseEntity<Map<String, Object>> responseEntity = getFinal();
        return responseEntity.getBody();
    }
 // Helper method to get the country name based on the country code
    private String getCountryName(String countryCode) {
        switch (countryCode) {
            case "DE":
                return "Germany";
            case "SK":
                return "Slovakia";
            case "TN":
                return "Tunisia";
            default:
                return countryCode;
        }
    }
}
