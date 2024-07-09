package com.schiedtandbachmann;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import data_cache.data.CellComputerDetail;
@RestController
@RequestMapping("/api")
public class CommonInfoHelper {
	
@Autowired
Car_parkController infoHelper;

@Autowired
RestTemplate restTemplate;

@Value("${APP.CONFIG.URL}")
private String baseUrl;


/////////////////////////////////////////////
@GetMapping("/pgs")
public ResponseEntity<List<Map<String, Object>>> getCarParkPgs() {
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
                Map<String, Object> carPark = new HashMap<>();
                carPark.put("countryCode", node.get("countryCode").asText());
                carPark.put("cityRegion", node.get("cityRegion").asText());
                JsonNode operatorDataNode = node.get("operatorData");
                if (operatorDataNode != null && operatorDataNode.has("operatorId")) {
                    carPark.put("operator_id", operatorDataNode.get("operatorId").asInt());
                }
                JsonNode merchantPspDataNode = node.get("merchantPspData");
                if (merchantPspDataNode != null && merchantPspDataNode.has("pgs")) {
                    JsonNode pgsNode = merchantPspDataNode.get("pgs");
                    if (pgsNode != null && pgsNode.has("tenant")) {
                        carPark.put("tenant", pgsNode.get("tenant").asText());
                    }
                }
                carPark.put("id", node.get("cellComputer").asInt());
                carParks.add(carPark);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().build();
    }

    return ResponseEntity.ok(carParks);
}

@GetMapping("/parkings/samePgsTenant")
@Cacheable(cacheNames = "PgsCache")
public List<String> getParkingsHasSamePgsTenant(@RequestParam String itemParking) {
    String city = "";
    String country = "";
    String tenant = "";
    ResponseEntity<List<Map<String, Object>>> responseEntity = getCarParkPgs();
    List<Map<String, Object>> parkings = responseEntity.getBody();

    for (Map<String, Object> itemCarPark : parkings) {
        String operatorId = itemCarPark.get("operator_id").toString();
        String id = itemCarPark.get("id").toString();
        if ((operatorId + "@" + id).equals(itemParking)) {
            city = itemCarPark.get("cityRegion").toString();
            country = itemCarPark.get("countryCode").toString();
            if (itemCarPark.containsKey("tenant"))
            tenant = itemCarPark.get("tenant").toString();
        }
    }

    return formatParkingsHasSamePgsTenant(parkings, city, country, tenant);
}

private List<String> formatParkingsHasSamePgsTenant(List<Map<String, Object>> parkings, String city, String country, String tenant) {
    List<String> res = new ArrayList<>();
    
    if (!city.isEmpty() && !country.isEmpty() && !tenant.isEmpty()) {
    	System.out.println("GG");
        for (Map<String, Object> itemCP : parkings) {
            String parkingCity = itemCP.get("cityRegion").toString();
            String parkingCountry = itemCP.get("countryCode").toString();
            if (itemCP.containsKey("tenant"))
            {
            String parkingTenant = itemCP.get("tenant").toString();
            if (parkingCity.equals(city) && parkingCountry.equals(country) && parkingTenant.equals(tenant)) {
                String operatorId = itemCP.get("operator_id").toString();
                String id = itemCP.get("id").toString();
                res.add(operatorId + "@" + id);
            }
        }
        }
    }

    return res;
}
//// Utiliiiis
private ResponseEntity<List<CellComputerDetail>> getCellComputer() {
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

    ObjectMapper mapper = new ObjectMapper();
    List<CellComputerDetail> cellComputers = new ArrayList<>();
    try {
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode cellComputersNode = root.get("cellComputers");
        if (cellComputersNode != null && cellComputersNode.isArray()) {
            for (JsonNode node : cellComputersNode) {
                CellComputerDetail cellComputer = mapper.treeToValue(node, CellComputerDetail.class);
                cellComputers.add(cellComputer);
            }
        }
    } catch (JsonProcessingException e) {
        e.printStackTrace();
    }

    return new ResponseEntity<>(cellComputers, HttpStatus.OK);
}

//@GetMapping("/location")
@Cacheable(cacheNames = "cellsCache")
public CellComputerDetail getCellComputerByLocationId(/*@RequestParam*/ String locationId) {
    ResponseEntity<List<CellComputerDetail>> cellComputerResponse = getCellComputer();
    List<CellComputerDetail> cellComputers = cellComputerResponse.getBody();

    if (cellComputers != null) {
        for (CellComputerDetail cellComputer : cellComputers) {
            String uniqueIdentifier = Integer.toString(cellComputer.getOperatorData().getOperatorId()) + Integer.toString(cellComputer.getCellComputer());
            if (locationId.equals(uniqueIdentifier)) {
                return cellComputer;
            }
        }
    }
    return null;
}

	 ////////////// Fetch LOGO 
//@GetMapping("/logo")
private String fetchLogoOperator(/*@RequestParam*/ String pspMerchantId) {
    String url = baseUrl + "/logo/" + pspMerchantId;

    try {
      
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-SampB-SelfCheckout-App", "SampB-SelfCheckout-2021");
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                String.class
        );

        String responseBody = response.getBody();
        if (responseBody != null) {
            Map<String, Object> responseMap = new ObjectMapper().readValue(responseBody, new TypeReference<Map<String, Object>>() {});
            if (responseMap.containsKey("image")) {
                return responseMap.get("image").toString();
            } 
        }
    } catch (Exception e) {
        e.printStackTrace(); 
        
    }
    return null;
    
}

	
@GetMapping("/location1")
@Cacheable(cacheNames = "fetchCache")
public Map<String,Object> fetchCarParkByLocationId(/*@RequestParam*/ String locationId) {
  ResponseEntity<List<CellComputerDetail>> cellComputerResponse = getCellComputer();
  List<CellComputerDetail> cellComputers = cellComputerResponse.getBody();

  if (cellComputers != null) {
      for (CellComputerDetail cellComputer : cellComputers) {
          String uniqueIdentifier = Integer.toString(cellComputer.getOperatorData().getOperatorId()) + Integer.toString(cellComputer.getCellComputer());
          if (locationId.equals(uniqueIdentifier)) {
              Map<String, Object> resultMap = new HashMap<>();
              resultMap.put("name", cellComputer.getHostName());
              resultMap.put("address", cellComputer.getOperatorAddress());
              //!= null ? cell.get("offsetUTC") : ""
              resultMap.put("operatorDataAddress", cellComputer.getOperatorData().getAddress() != null ? cellComputer.getOperatorData().getAddress() : "");
              if (cellComputer.getMerchantPspData() != null && cellComputer.getMerchantPspData().getMerchantPspId() != null) {
                  String logo = fetchLogoOperator(String.valueOf(cellComputer.getMerchantPspData().getMerchantPspId()));
                  resultMap.put("operatorLogo", logo);
              }else {
            	  resultMap.put("operatorLogo", "");
              }
              resultMap.put("id", cellComputer.getCellComputer());
              resultMap.put("operator_id", cellComputer.getOperatorData().getOperatorId());
              resultMap.put("operator_name", cellComputer.getOperatorData().getName());
              resultMap.put("currency", cellComputer.getCurrencyCode()!= null ? cellComputer.getCurrencyCode() : "");
              resultMap.put("ip4address", cellComputer.getIp4address());
              resultMap.put("port", cellComputer.getPort());
              resultMap.put("username", cellComputer.getUserName());
              resultMap.put("password", cellComputer.getPassword());
              resultMap.put("country", cellComputer.getCountryCode() != null ? cellComputer.getCountryCode() : "");
              resultMap.put("city", cellComputer.getCityRegion()!= null ? cellComputer.getCityRegion() : "");
              resultMap.put("deviceId", cellComputer.getDeviceId()!= null ? cellComputer.getDeviceId() : "");
              resultMap.put("cashierContractId", cellComputer.getCashierContractId() != null ? cellComputer.getCashierContractId() : "");
              resultMap.put("cashierConsumerId", cellComputer.getCashierConsumerId()!= null ? cellComputer.getCashierConsumerId() : "");
              resultMap.put("vatNumber", cellComputer.getRateCode() != null ? (int) cellComputer.getRateCode() : 0);
              resultMap.put("linkTermAndCondition", cellComputer.getMerchantPspData().getTouLink());
              resultMap.put("tenant", getTenant(cellComputer));
              resultMap.put("email", cellComputer.getOperatorData().getEmail());
              resultMap.put("phone", cellComputer.getOperatorData().getPhone());
              resultMap.put("offsetUTC", cellComputer.getOffsetUTC());
              resultMap.put("financialInfo", cellComputer.getFinancialInfo() != null ? cellComputer.getFinancialInfo() : "");
              resultMap.put("currencyDecimal", cellComputer.getCurrencyDecimal() != null ? cellComputer.getCurrencyDecimal() : "");
              resultMap.put("language", cellComputer.getLanguages() != null ? cellComputer.getLanguages() : "" );
              
              


              return resultMap;
          }
      }
  }
  return null;
}

private String getTenant(CellComputerDetail cell) {
    if (cell != null && cell.getMerchantPspData() != null &&
        cell.getMerchantPspData().getPgs() != null &&
        cell.getMerchantPspData().getPgs().getTenant() != null) {

        return cell.getMerchantPspData().getPgs().getTenant();
    }

    return "";
}

}
