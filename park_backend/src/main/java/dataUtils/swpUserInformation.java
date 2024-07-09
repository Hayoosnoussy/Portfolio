package dataUtils;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Data
@JsonInclude(value=Include.NON_NULL) //les attribus null ne s'affiche pas 
public class swpUserInformation {
	
	

	@JsonProperty("mediumValue")
	    @NotNull
	    @NotEmpty
	    private String mediumValue;
	@JsonProperty("mediumType")
	@NotNull
    @NotEmpty
	    private String mediumType = "barcode";
	@JsonProperty("clientId")
	@NotNull
    @NotEmpty
	    private String clientId;
	@JsonProperty("country")
	    private String country;
	@JsonProperty("city")
	    private String city;
	@JsonProperty("parking")
	    private String parking;
	@JsonProperty("E")
	    private String E;
	@JsonProperty("barcode")
	    private String barcode;
	@JsonProperty("captchaToken")
	@NotNull
    @NotEmpty
	    private String captchaToken;
	@JsonProperty("latePayment")
	    @NotNull
	    private Boolean latePayment;
	@JsonProperty("present")
	    @NotNull
	    private Boolean present;

	    //private Boolean reload;
	@JsonProperty("from")
	    @NotBlank
	    private String from;

	    


	  
	    
	    public boolean isValidd(swpUserInformation data) {

	    	if (!(data instanceof swpUserInformation)) return false;
	    	
  captchaToken= data.getCaptchaToken();

	        //// Validate the captcha token ;)
	       CaptchaValidationResponseDto responseDto = validateCaptchaToken(captchaToken);
	        return responseDto != null && responseDto.isSuccess();
	       
	    }

	    private CaptchaValidationResponseDto validateCaptchaToken(String captchaToken) {
	        String recaptchaSecret = "6Lfnq2sbAAAAAIdDFjuJlU7MEvzI4FpUqX1Otyod";
	        RestTemplate restTemplate = new RestTemplate();

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
	        map.add("secret", recaptchaSecret);
	        map.add("response", captchaToken);

	        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

	        String url = "https://www.google.com/recaptcha/api/siteverify";

	        return restTemplate.postForObject(url, request, CaptchaValidationResponseDto.class);
	    }
	    
	    
	    
}


