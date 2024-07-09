package Services;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;

import com.schiedtandbachmann.CommonInfoHelper;

import DBEntity.RefundHistory;
import data_cache.data.CellComputerDetail;
import repository.RefundHistoryRepository;

import static com.schiedtandbachmann.ParkApplication.LOGGER_NAME;

import java.time.LocalDateTime;
import java.util.Map;
import static com.schiedtandbachmann.ParkApplication.LOGGER_NAME;

@Service
public class RefundService {
	private static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + ":RefundService");

    @Autowired
    CommonInfoHelper commonInfoHelper;

    @Autowired
    RestTemplate restTemplate; 

    @Value("$(pgs.user)")
    private String pgsUser;
    
    @Value("$(pgs.pwd)")
    private String pgsPwd;
    
    @Value("${apiPayment}")
    private String apiPayment;

    @Autowired
    RefundHistoryRepository refundHistoryRepository;

    public int refund(String tenant, String cardId, String reason, String amount, String correlationId) {
    	
    	CellComputerDetail infoCarPark = commonInfoHelper.getCellComputerByLocationId(tenant);
        tenant = infoCarPark.getMerchantPspData().getPgs().getTenant();
    	
        String url = apiPayment + "/payment/refund/partial/" + tenant + "/" + cardId + "/" + reason + "/" + amount;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        try {
        	 HttpHeaders headers = new HttpHeaders();
             headers.setBasicAuth(pgsUser,pgsPwd);
        	
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
            		 uriBuilder.toUriString(),
                HttpMethod.PUT,
                entity,
                String.class
            );

            int status = response.getStatusCodeValue();


            logger.info("service_pgs", response.getBody());
            
            saveRefundHistory(tenant, correlationId, cardId, amount, status);
            return status;
            
        } catch (HttpServerErrorException e) {
            logger.error("service_pgs", e.getMessage());
            int status = e.getRawStatusCode();
            saveRefundHistory(tenant, correlationId, cardId, amount, status);
            return status;
        } catch (Exception e) {
            logger.error("service_pgs", e.getMessage());
            int status = 500;
            saveRefundHistory(tenant, correlationId, cardId, amount, status);
            return status;
        } 
        

    }
    
    /// Saving REFUND to call back in refund method 
    private void saveRefundHistory(String tenant, String correlationId, String cardId, String amount, int status) {
    	RefundHistory refundHistory = new RefundHistory();
        refundHistory.setTenant(tenant);
        refundHistory.setCorrelationId(correlationId); // payId (we change correlationId by payId)
        refundHistory.setShoppingCartUuid(cardId);
        refundHistory.setAmount(amount);
        refundHistory.setRequestStatus(String.valueOf(status));
        refundHistory.setCreatedAt(LocalDateTime.now());
        refundHistoryRepository.save(refundHistory);
    }
    
public int refundPgs(String tenant, String cardId, String reason, String amount, String payId) {
        String url = apiPayment + "/payment/refund/partial/" + tenant + "/" + cardId + "/" + reason + "/" + amount;
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        try {
        	 HttpHeaders headers = new HttpHeaders();
             headers.setBasicAuth(pgsUser,pgsPwd);
        	
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
            		 uriBuilder.toUriString(),
                HttpMethod.PUT,
                entity,
                String.class
            );

            int status = response.getStatusCodeValue();


            logger.info("service_pgs", response.getBody());
            
            saveRefundHistory(tenant, payId, cardId, amount, status);
            return status;
            
        } catch (HttpServerErrorException e) {
            logger.error("service_pgs", e.getMessage());
            int status = e.getRawStatusCode();
            saveRefundHistory(tenant, payId, cardId, amount, status);
            return status;
        } catch (Exception e) {
            logger.error("service_pgs", e.getMessage());
            int status = 500;
            saveRefundHistory(tenant, payId, cardId, amount, status);
            return status;
        } 
    }
    



}
