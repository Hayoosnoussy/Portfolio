 package com.schiedtandbachmann;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;





@RestController
@RequestMapping("/api")
public class Car_parkController {

//	
//	@Value("${appConfig.baseUrl}")
//    private String appConfigBaseUrl;
//
//    @Value("${appConfig.appId}")
//    private String appConfigAppId;
//	

		   ////// Car Park now 
		    @GetMapping("/unusedcarpark")
		    public ResponseEntity<List<Map<String, String>>> getCellComputerDetails(String appId) {
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
		        List<Map<String, String>> cellComputerDetails = new ArrayList<>();
		        try {
		            JsonNode root = mapper.readTree(response.getBody());
		            JsonNode cellComputersNode = root.get("cellComputers");
		            if (cellComputersNode != null && cellComputersNode.isArray()) {
		                for (JsonNode node : cellComputersNode) {
		                    Map<String, String> details = new HashMap<>();
		                    details.put("id", node.get("cellComputer").asText());
		                    details.put("name", node.get("hostName").asText());
		                    details.put("city", node.get("cityRegion").asText());
		                    details.put("country", node.get("countryCode").asText());
		                    details.put("latepay", node.get("latePaymentSupport").asText());
		                    details.put("operatorData", node.get("operatorData").asText());
		                    
		                    JsonNode operatorDataNode = node.get("operatorData");
		                    if (operatorDataNode != null) {
		                        details.put("operator_id", operatorDataNode.get("operatorId").asText());
		                        details.put("operator_name", operatorDataNode.get("name").asText());
		                        details.put("address", operatorDataNode.get("address").asText());
		                    }
		                    
		                    cellComputerDetails.add(details);
		                }
		            }
		        } catch (JsonProcessingException e) {
		            e.printStackTrace();
		        }

		        return new ResponseEntity<>(cellComputerDetails, HttpStatus.OK);
		    }
		

/// second one 
		    

		    @GetMapping("/unusedparkingfornow")
		    public ResponseEntity<Map<String, List<Map<String, Object>>>> getCellComputerDetailss(String appId) {
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
		        List<Map<String, Object>> carParks = new ArrayList<>();
		        try {
		            JsonNode root = mapper.readTree(response.getBody());
		            JsonNode cellComputersNode = root.get("cellComputers");
		            if (cellComputersNode != null && cellComputersNode.isArray()) {
		                for (JsonNode node : cellComputersNode) {
		                    Map<String, Object> carPark = new HashMap<>();
		                    carPark.put("operator_name", node.get("operatorData").get("name").asText());
		                    carPark.put("country", node.get("countryCode").asText());
		                    carPark.put("address", node.get("operatorData").get("address").asText());
		                    carPark.put("linkTermAndCondition", node.get("merchantPspData").get("touLink").asText());
		                    carPark.put("city", node.get("cityRegion").asText());
		                    carPark.put("operator_id", node.get("operatorData").get("operatorId").asInt());
		                    carPark.put("name", node.get("hostName").asText());
		                    carPark.put("id", node.get("cellComputer").asInt());
		                    carPark.put("latepay", node.get("latePaymentSupport").asText());

		                    carParks.add(carPark);
		                }
		            }
		        } catch (JsonProcessingException e) {
		            e.printStackTrace();
		        }

		        Map<String, List<Map<String, Object>>> result = new HashMap<>();
		        result.put("car_parks", carParks);

		        return new ResponseEntity<>(result, HttpStatus.OK);
		    }
		    
		    /// final before cache
		  
		    @GetMapping("/parks-lists")
		    public ResponseEntity<Map<String, List<Map<String, Object>>>> getCarParkDetails(
		            @RequestParam String code,
		            @RequestParam String city) { 

		       String  appId = "13";
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
		        List<Map<String, Object>> carParks = new ArrayList<>();
		        try {
		            JsonNode root = mapper.readTree(response.getBody());
		            JsonNode cellComputersNode = root.get("cellComputers");
		            if (cellComputersNode != null && cellComputersNode.isArray()) {
		                for (JsonNode node : cellComputersNode) {
		                    String nodeCountryCode = node.get("countryCode").asText();
		                    String nodeCityCode = node.get("cityRegion").asText();
		                    if ((code == null || code.equals(nodeCountryCode)) &&
		                            (city == null || city.equals(nodeCityCode))) {
		                        Map<String, Object> carPark = new HashMap<>();
		                        carPark.put("operator_name", node.get("operatorData").get("name").asText());
		                        carPark.put("country", nodeCountryCode);
		                        carPark.put("address", node.get("operatorAddress").asText());
		                       
		                        JsonNode touLinkNode = node.get("merchantPspData").get("touLink");
		                        String linkTermAndCondition = (touLinkNode != null && !touLinkNode.isNull()) ? touLinkNode.asText() : null;

		                        carPark.put("linkTermAndCondition", linkTermAndCondition);
		                 
		                        carPark.put("city", nodeCityCode);
		                        carPark.put("operator_id", node.get("operatorData").get("operatorId").asInt());
		                        carPark.put("name", node.get("hostName").asText());
		                        carPark.put("id", node.get("cellComputer").asInt());
		                        if (node.get("latePaymentSupport").asText().equals("true") )
		                        carPark.put("latepay",  Boolean.TRUE);
		                        else 
                                carPark.put("latepay", Boolean.FALSE);
		                        carParks.add(carPark);
		                    }
		                }
		            }
		        } catch (JsonProcessingException e) {
		            e.printStackTrace();
		        }
		        Map<String, List<Map<String, Object>>> result = new HashMap<>();
		        result.put("car_parks", carParks);

		        return new ResponseEntity<>(result, HttpStatus.OK);
		    }
		    
		    
		  //try for rediiss nd it works :s now this is the final
		    @GetMapping("/parks-list")
		    @Cacheable(cacheNames = "cellComputerCache")
		    public Map<String, List<Map<String, Object>>> getCarParkDetailss(
		            @RequestParam String code,
		            @RequestParam String city) { 

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
		        List<Map<String, Object>> carParks = new ArrayList<>();
		        try {
		            JsonNode root = mapper.readTree(response.getBody());
		            JsonNode cellComputersNode = root.get("cellComputers");
		            if (cellComputersNode != null && cellComputersNode.isArray()) {
		                for (JsonNode node : cellComputersNode) {
		                    String nodeCountryCode = node.get("countryCode").asText();
		                    String nodeCityCode = node.get("cityRegion").asText();
		                    if ((code == null || code.equals(nodeCountryCode)) &&
		                            (city == null || city.equals(nodeCityCode))) {
		                        Map<String, Object> carPark = new HashMap<>();
		                        carPark.put("operator_name", node.get("operatorData").get("name").asText());
		                        carPark.put("country", nodeCountryCode);
		                        carPark.put("address", node.get("operatorAddress").asText());
		                        JsonNode touLinkNode = node.get("merchantPspData").get("touLink");
		                        String linkTermAndCondition = (touLinkNode != null && !touLinkNode.isNull()) ? touLinkNode.asText() : null;
		                        carPark.put("linkTermAndCondition", linkTermAndCondition);
		                        carPark.put("city", nodeCityCode);
		                        JsonNode operatorData = node.get("operatorData").get("operatorId");
		                        int operator_id = (operatorData != null && !operatorData.isNull()) ? operatorData.asInt() : null;
		                        carPark.put("operator_id", operator_id);
		                        carPark.put("name", node.get("hostName").asText());
		                        carPark.put("id", node.get("cellComputer").asInt());
		                        if (node.get("latePaymentSupport").asText().equals("true"))
		                            carPark.put("latepay",  Boolean.TRUE);
		                        else 
		                            carPark.put("latepay", Boolean.FALSE);
		                        carParks.add(carPark);
		                    }
		                }
		            }
		        } catch (JsonProcessingException e) {
		            e.printStackTrace();
		        }
		        Map<String, List<Map<String, Object>>> result = new HashMap<>();
		        result.put("car_parks", carParks);

		        return result;
		    }



		    //// extract the operator_id @ id all of em in one array for use late
		    //// not usefull 
		    @GetMapping("/parks-list/details")
			public ResponseEntity<List<String>> getCityParkings(
			        @RequestParam String code,
			        @RequestParam String city
			        	) {
				
			   
			    ResponseEntity<Map<String, List<Map<String, Object>>>> responseEntity = getCarParkDetails(code, city);
			    Map<String, List<Map<String, Object>>> responseBody = responseEntity.getBody();

			    List<Map<String, Object>> carParks = responseBody.get("car_parks");
			    List<String> carParkIds = new ArrayList<>();

			    for (Map<String, Object> carPark : carParks) {
			        String carParkId = carPark.get("operator_id").toString() + "@" + carPark.get("id").toString();
			        carParkIds.add(carParkId);
			    }

			    return new ResponseEntity<>(carParkIds, HttpStatus.OK);
			}
		    
		    
		   ///// not a response entity because i need a list
		   @Cacheable(cacheNames = "citiesUseCache")
		  @GetMapping("/parks-list/details1")
		    public List<String> getCityParkingsUse(
		            @RequestParam String code,
		            @RequestParam String city
		    ) {
		        
		        ResponseEntity<Map<String, List<Map<String, Object>>>> responseEntity = getCarParkDetails(code, city);
		        Map<String, List<Map<String, Object>>> responseBody = responseEntity.getBody();

		        List<Map<String, Object>> carParks = responseBody.get("car_parks");
		        List<String> carParkIds = new ArrayList<>();

		        for (Map<String, Object> carPark : carParks) {
		            String carParkId = carPark.get("operator_id").toString() + "@" + carPark.get("id").toString();
		            carParkIds.add(carParkId);
		        }

		        return carParkIds;
		    }

		    
 /// final for deploy shiha juste les valeurs mezelo
//	the only line that changes     
//		        String apiUrl = appConfigBaseUrl + "/cellComputersDetailList/" + appConfigAppId;

		}



