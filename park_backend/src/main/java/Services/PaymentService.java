package Services;


import java.io.StringWriter;
import java.util.HashMap;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schiedtandbachmann.CommonInfoHelper;

import org.springframework.http.HttpHeaders;



import DBEntity.ConsultTransaction;
import DBEntity.PaymentTransactions;
import dataUtils.transactions;
import data_cache.data.CellComputerDetail;
import lombok.extern.java.Log;
import repository.ConsultTransactionRepository;
import repository.PaymentTransactionRepository;

import static com.schiedtandbachmann.ParkApplication.LOGGER_NAME;

@Service
public class PaymentService {

	@Autowired
    ConsultTransactionRepository consultTransactionRep;
	@Autowired
    PaymentTransactionRepository paymentTransactionRep;
	@Autowired
    CommonInfoHelper commonInfoHelper;
	
	@Autowired
    private RestTemplate restTemplate;
	private static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + ":PaymentService");
	    @Value("${success-payment}")
	    private String successCallbackUrl;

	    @Value("${apiPayment}")
	    private String apiPayment;
	    
	    @Value("${failure-payment}")
	    private String failureCallbackUrl;
	    
	    @Value("$(pgs.user)")
        private String pgsUser;
	    
	    @Value("$(pgs.pwd)")
        private String pgsPwd;
	    
	    private String configTenant;
	    private double amount;
	    private String currency;
	  
	
	/// OnlinePayment
	public Map<String, Object> onlinePayment(List<transactions>transactions, String locale) {
        
        
        

        for (transactions transaction : transactions) {
        	String transactionId = transaction.getTransaction_id();
            ConsultTransaction consultTransaction = consultTransactionRep.findOneByTransactionIdOrderByIdDesc(transactionId);

            if (consultTransaction != null && !List.of("failed", "success", "refunded")
                    .contains(consultTransaction.getStatus())) {
                amount += consultTransaction.getAmount();
                currency = consultTransaction.getCurrency();
                configTenant = consultTransaction.getTenant();
            }
        }
        String reference = generateRandom(12);
        Map<String, Object> data = new HashMap<>();
        data.put("requestor", "shop-entervo-smart-web-pay");
        data.put("correlationId", generateRandom(6));
        data.put("amount", amount);
        data.put("currency", currency);
        data.put("reason", "entervo smart web Pay");
        data.put("reference", "myshop-entervo-smart-pay-" + reference);
        data.put("successCallbackUrl", successCallbackUrl);
        data.put("failureCallbackUrl", failureCallbackUrl);

        if (amount > 0) {
            try {
                return createShoppingCart(data, transactions);
            } catch (HttpClientErrorException | HttpServerErrorException exception) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("errorCode", exception.getRawStatusCode());
                errorResponse.put("message", exception.getMessage());
                return errorResponse;
            }
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("errorCode", 400);
            errorResponse.put("message", "Amount 0");
            return errorResponse;
        }
    }

	///AvailablePaymentTypes
	public Map<String, Object> getAvailablePaymentTypes(String tenant, String cartId, String correlationId) {
        
       
        String locale = "en-EN";
        

        String url = apiPayment + "/payment/paymenttypes/"
                + tenant + "/" + cartId + "/" + correlationId
                + "/shop-entervo-smart-web-pay/"
                + locale + "?imageColor=true";

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(pgsUser,pgsPwd);
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException httpException) {
	          
	           Map<String, Object> results = new HashMap<>();
	           results.put("errorCode", httpException.getRawStatusCode());
	           results.put("message", httpException.getStatusText());

	           logger.error("An HTTP error occurred:", httpException);

	           return results;
        }
    }

	

	    
	    ///verifyIfTransactionsHaveSameOperator2
	    public boolean verifyIfTransactionsHaveSameOperator(List<transactions> transactions) {
	        Set<String> listOfOperatorIds = new HashSet<>();

	        for (transactions transaction : transactions) {
	        	String transactionId = transaction.getTransaction_id();
	            ConsultTransaction consultTransaction = consultTransactionRep.findOneByTransactionIdOrderByIdDesc(transactionId);

	            if (consultTransaction != null) {
	                if (listOfOperatorIds.isEmpty()) {
	                    listOfOperatorIds.add(consultTransaction.getOperatorId());
	                } else {
	                    if (!listOfOperatorIds.contains(consultTransaction.getOperatorId())) {
	                        return true;
	                    }
	                }
	            }
	        }

	        return false;
	    }
	    
	    //GenerateRANDOM
	    private String generateRandom(int length) {
	        StringBuilder randomNumber = new StringBuilder();
	        int count = 0;
	        
	        while (count < length) {
	            int randomDigit = (int) (Math.random() * 10);
	            randomNumber.append(randomDigit);
	            count++;
	        }

	        return randomNumber.toString();
	    }
	    
	    //CreateShopingCart
	    //// SAYEEEEEEEEEEEE :))) 
	    public Map<String, Object> createShoppingCart(Map<String, Object> data, List<transactions> transactions) {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        String pgsUser = "testexport";
	        String pgsPwd = pgsUser;
	        
	        String url=apiPayment+"/payment/paymentcart/{configTenant}";
	        headers.setBasicAuth(pgsUser, pgsPwd);

	        HttpEntity<Object> requestEntity = new HttpEntity<>(data, headers);

	        try {
	        	ResponseEntity<String> response = restTemplate.exchange(
	        		    url,
	        		    HttpMethod.PUT,
	        		    requestEntity,
	        		    String.class,
	        		    configTenant
	        		);
	            String responseBody = response.getBody();
	            ObjectMapper objectMapper = new ObjectMapper();
	            JsonNode jsonNode = objectMapper.readTree(responseBody);
	            
	            String cardId = jsonNode.get("cartId").asText();
	         // flush n persist PaymentTransactions entity
	            PaymentTransactions payment = new PaymentTransactions();
	            payment.setStatus(false);
	            payment.setCorrelationId((String) data.get("correlationId"));
	            payment.setShoppingCartUuid(cardId);
	            payment.setReference((String) data.get("reference"));
	            payment.setEmailIsSent(false); 
	            paymentTransactionRep.save(payment);
	            /// assigne null to ids
	            String operatorID = null;
	            String cellComputerID = null;
	            for (transactions transaction : transactions) 
	            {
	             String transactionId = transaction.getTransaction_id();
	             ConsultTransaction consultTransaction = consultTransactionRep.findOneByTransactionIdOrderByIdDesc(transactionId);
	             if (consultTransaction != null)
	               {
	            	consultTransaction.setPaymentTransactions(payment);
	            	if (operatorID == null)
	            	  {
	            		operatorID = consultTransaction.getOperatorId();
	            		cellComputerID = Integer.toString(consultTransaction.getCellComputerId());
	            	  }
	            	payment.addTransaction(consultTransaction);
	               }
	            }
	            	paymentTransactionRep.save(payment);
	            	String locationId = operatorID + cellComputerID;
	            	CellComputerDetail infoCarPark = commonInfoHelper.getCellComputerByLocationId(locationId);
	            	Map<String, Object> paymentTypes = getAvailablePaymentTypes(
	                        configTenant,
	                        cardId,
	                        (String) data.get("correlationId")
	                    );
	            	
	                String linkTermAndCondition = infoCarPark.getMerchantPspData().getTouLink();
	                return Map.of(
	                        "types", paymentTypes,
	                        "linkTermAndCondition", linkTermAndCondition
	                    );
	             
	            
	            
	          
	       } catch (HttpClientErrorException | HttpServerErrorException httpException) {
	          
	           Map<String, Object> results = new HashMap<>();
	           results.put("errorCode", httpException.getRawStatusCode());
	           results.put("message", httpException.getStatusText());

	           logger.error("An HTTP error occurred:", httpException);

	           return results;
	       } catch (Exception exception) {
	           Map<String, Object> results = new HashMap<>();
	           
	           
	           results.put("errorCode", 404);
	           results.put("message", exception.getMessage());

	           logger.error("An error occurred:", exception);

	           return results;
	       }
	    }
	    
}
