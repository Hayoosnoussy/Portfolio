package dataUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class transactions {
	
	@JsonProperty("transaction_id")
	private String transaction_id;
	@JsonProperty("locale")
	private String locale;

}
