package com.schiedtandbachmann;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api")
public class CityController {

	
    /// les cities no doubles 
    
    @GetMapping("/unusedcitiesnodoubles")
    public ResponseEntity<List<String>> getCellComputerCitiesRegion(String appId) {
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
        Set<String> citiesRegion = new HashSet<>(); // Use Set to eliminate duplicates
        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode cellComputersNode = root.get("cellComputers");
            if (cellComputersNode != null && cellComputersNode.isArray()) {
                for (JsonNode node : cellComputersNode) {
                    JsonNode citiesRegionNode = node.get("cityRegion");
                    if (citiesRegionNode != null) {
                        citiesRegion.add(citiesRegionNode.asText());
                    }
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(citiesRegion), HttpStatus.OK);
    }

    
    /// with key and value my broooo 
    
    @GetMapping("/unusedcountrynodoublesandwithkey")
    public ResponseEntity<Map<String, List<Map<String, String>>>> getCellComputerCitiesRegions(String appId) {
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
        Set<String> citiesRegion = new HashSet<>(); // Use Set to eliminate duplicates
        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode cellComputersNode = root.get("cellComputers");
            if (cellComputersNode != null && cellComputersNode.isArray()) {
                for (JsonNode node : cellComputersNode) {
                    JsonNode citiesRegionNode = node.get("cityRegion");
                    if (citiesRegionNode != null) {
                        citiesRegion.add(citiesRegionNode.asText());
                    }
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Map<String, List<Map<String, String>>> responseMap = new HashMap<>();
        List<Map<String, String>> citiesList = new ArrayList<>();
        for (String cityCode : citiesRegion) {
            Map<String, String> cityMap = new HashMap<>();
            cityMap.put("code", cityCode);
            citiesList.add(cityMap);
        }
        responseMap.put("cities", citiesList);

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    
    /// Final bro before caching :DD 
    //@GetMapping("/cities-list")
    private ResponseEntity<Map<String, List<Map<String, String>>>> getCitiessRegion(
        @RequestParam("code") String code) {
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
        Set<String> citiesRegion = new HashSet<>(); // Use Set to eliminate duplicates
        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode cellComputersNode = root.get("cellComputers");
            if (cellComputersNode != null && cellComputersNode.isArray()) {
                for (JsonNode node : cellComputersNode) {
                    JsonNode countryCodeNode = node.get("countryCode");
                    JsonNode citiesRegionNode = node.get("cityRegion");
                    if (countryCodeNode != null && citiesRegionNode != null &&
                            countryCodeNode.asText().equalsIgnoreCase(code)) {
                        citiesRegion.add(citiesRegionNode.asText());
                    }
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Map<String, List<Map<String, String>>> responseMap = new HashMap<>();
        List<Map<String, String>> citiesList = new ArrayList<>();
        for (String cityCode : citiesRegion) {
            Map<String, String> cityMap = new HashMap<>();
            cityMap.put("code", cityCode);
            citiesList.add(cityMap);
        }
        responseMap.put("cities", citiesList);

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
    
    //try for rediiss and it worked HMD :DDD
    @GetMapping("/cities-list")
    @Cacheable(cacheNames = "cachedCities")
    public Map<String, List<Map<String, String>>> getCachedCountries(@RequestParam("code") String code) {
    	ResponseEntity<Map<String, List<Map<String, String>>>> responseEntity = getCitiessRegion(code);
        return responseEntity.getBody();
    }
    
}

