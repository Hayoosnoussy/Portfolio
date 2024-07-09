package com.schiedtandbachmann;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import dataUtils.swpUserInformation;
@RestController
@RequestMapping("/api")
public class ZRConnectorHelper {

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired 
    Car_parkController infoHelper;
    
    @Autowired
    CommonInfoHelper commonHelper;
	//when it was public so i can test it @PutMapping("/info")
    private Map<String, Object> getTransactionBody(
            @RequestBody @Valid swpUserInformation userInformation
            // juste hata nwali n3ayetlha men TransactionController
          ,  List<String> presentParkings
          ,   List<String> latePaymentParkings
             ) {
//		String country = userInformation.getCountry();
//		String city = userInformation.getCity();
//		System.out.println("city = "+ city);
    	
    	//used when testin this method alone
		//List<String> presentParkings = new ArrayList<>();
       // List<String> latePaymentParkings = new ArrayList<>();
		
		

        if (userInformation.getMediumType().equals("lpn")
                && userInformation.getBarcode() != null
                && (userInformation.getMediumValue().equals("*") || ((String) userInformation.getMediumValue()).startsWith("#"))) {
        	System.out.println("bizzarrrrrrrrrr");
        	userInformation.setMediumType("barcode");
            userInformation.setMediumValue(userInformation.getBarcode());
        }

        if (!userInformation.getParking().equals("ALL") && !((String) userInformation.getParking()).isEmpty()) {
            if ((boolean) userInformation.getLatePayment()) {	
                if (!commonHelper.getParkingsHasSamePgsTenant((String) userInformation.getParking()).isEmpty())  {
                	System.out.println("bizzarrrrrrrrrrPGS");
                    latePaymentParkings = commonHelper.getParkingsHasSamePgsTenant((String) userInformation.getParking());
                } else {
                	System.out.println("bizzarrrrrrrrrrSING");
                    latePaymentParkings = Collections.singletonList((String)  userInformation.getParking());
                }
            }
            if ((boolean) userInformation.getPresent()) {
                presentParkings = Collections.singletonList((String)  userInformation.getParking());
            }

            return Map.of(
                    "mediumType", userInformation.getMediumType(),
                    "mediumValue", userInformation.getMediumValue(),
                    "latePayments", latePaymentParkings,
                    "present", presentParkings,
                    "clientId", userInformation.getClientId()
            );
        } else {
            if ((boolean) userInformation.getLatePayment()) {
                latePaymentParkings = infoHelper.getCityParkingsUse(userInformation.getCountry(), userInformation.getCity());
                System.out.println("bizzarrrrrrrrrrCIty555");
            }
            if ((boolean) userInformation.getPresent()) {
            	if ((boolean) userInformation.getLatePayment())
            	{
            		//System.out.println(presentParkings);
            		presentParkings.addAll(latePaymentParkings);
              //presentParkings = latePaymentParkings;
            	}
            	else {
            		presentParkings.addAll(infoHelper.getCityParkingsUse(userInformation.getCountry(), userInformation.getCity()));
                System.out.println("bizzarrrrrrrrrrCIty255");
                     }
            }
            return Map.of(
            		"mediumType", userInformation.getMediumType(),
                    "mediumValue", userInformation.getMediumValue(),
                    "latePayments", latePaymentParkings,
                    "present", presentParkings,
                    "clientId", userInformation.getClientId()
            );
        }
    }
	//// The IsSend Method
	public boolean isTransactionsSend(swpUserInformation data, List<String> presentParkings, List<String> latePaymentParkings) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("correlationId", "10203");
        headers.set("X-SampB-SelfCheckout-App", "SampB-SelfCheckout-2021");
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    String url = "https://kong.dev.parking.scheidt-bachmann.net/zrconnector-service/v1/getTransactions/";
	    Map<String, Object> body = getTransactionBody(data, presentParkings, latePaymentParkings);

	    try {
	        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(body, headers), Object.class);
	        if (response.getStatusCode().is2xxSuccessful()) {
	        	System.out.println("hayoo made it");
	            return true;
	        }
	    } catch (Exception e) {	       
	        e.printStackTrace();
	    }

	    return false;
	}

	


	
	

}
