package kafka;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BookingRequest {
	@JsonProperty("status")
    private String status;
	@JsonProperty("transactionId")

    private String transactionId;
	@JsonProperty("date")

    private LocalDateTime date;
	@JsonProperty("receiptNumber")

    private String receiptNumber;
	@JsonProperty("message")

    private String message;
	@JsonProperty("bookingId")

	private String bookingId;
	@JsonProperty("vatType")

	private String vatType;
	@JsonProperty("vatValue")

	private String vatValue;
	@JsonProperty("originalAmount")

	private String originalAmount;
	@JsonProperty("amount")

	private String amount;
	@JsonProperty("clientId")

	private String clientId;
	@JsonProperty("articleRef")

	private String articleRef;
	@JsonProperty("articleShortText")

	private String articleShortText;
}
