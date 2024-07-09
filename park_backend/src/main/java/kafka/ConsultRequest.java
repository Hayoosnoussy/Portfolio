package kafka;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class ConsultRequest {
	
	@JsonProperty("clientId")
    private String clientId;
    
    @JsonProperty("transactionId")
    private String transactionId;
    
    @JsonProperty("mediumType")
    private String mediumType;
    
    @JsonProperty("mediumValue")
    private String mediumValue;
    
    @JsonProperty("licensePlate")
    private String licensePlate;
    
    @JsonProperty("transactionType")
    private String transactionType;
    
    @JsonProperty("operator")
    private String operator;
    
    @JsonProperty("cellComputer")
    private int cellComputer;
    
    @JsonProperty("carParkAddress")
    private String carParkAddress;
    
    @JsonProperty("carParkName")
    private String carParkName;
    
    @JsonProperty("amount")
    private int amount;
    
    @JsonProperty("currency")
    private String currency;
    
    @JsonProperty("duration")
    private String duration;
    
    @JsonProperty("tariffTimeStart")
    private String tariffTimeStart;
    
    @JsonProperty("tariffTimeEnd")
    private String tariffTimeEnd;
    
    @JsonProperty("articleShortText")
    private String articleShortText;
    
    @JsonProperty("tenant")
    private String tenant;
    
    @JsonProperty("facilityId")
    private int facilityId;
    
    @JsonProperty("epan")
    private String epan;
    
    @JsonProperty("vatType")
    private String vatType;
    
    @JsonProperty("vatValue")
    private String vatValue;

}
