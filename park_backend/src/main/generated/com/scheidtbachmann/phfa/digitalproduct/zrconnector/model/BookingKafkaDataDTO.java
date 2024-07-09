package com.scheidtbachmann.phfa.digitalproduct.zrconnector.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BookingKafkaDataDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-31T15:56:35.474500200+01:00[Africa/Tunis]")
public class BookingKafkaDataDTO  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("clientId")
  private String clientId;

  @JsonProperty("transactionId")
  private String transactionId;

  @JsonProperty("status")
  private String status;

  @JsonProperty("bookingId")
  private String bookingId;

  @JsonProperty("receiptNumber")
  private String receiptNumber;

  @JsonProperty("date")
  private String date;

  @JsonProperty("vatType")
  private String vatType;

  @JsonProperty("vatValue")
  private String vatValue;

  @JsonProperty("message")
  private String message;

  @JsonProperty("amount")
  private String amount;

  @JsonProperty("originalAmount")
  private String originalAmount;

  @JsonProperty("articleRef")
  private String articleRef;

  @JsonProperty("articleShortText")
  private String articleShortText;

  public BookingKafkaDataDTO clientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

  /**
   * unique request/client id of the request/client
   * @return clientId
  */
  @ApiModelProperty(example = "1234-12asd-fdfsc", value = "unique request/client id of the request/client")


  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public BookingKafkaDataDTO transactionId(String transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  /**
   * unique transactionId
   * @return transactionId
  */
  @ApiModelProperty(example = "1234-12asd-fdfsc", value = "unique transactionId")


  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public BookingKafkaDataDTO status(String status) {
    this.status = status;
    return this;
  }

  /**
   * status of the booking (failed, success, refunded)
   * @return status
  */
  @ApiModelProperty(example = "success", value = "status of the booking (failed, success, refunded)")


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public BookingKafkaDataDTO bookingId(String bookingId) {
    this.bookingId = bookingId;
    return this;
  }

  /**
   * unique booking Id from cell
   * @return bookingId
  */
  @ApiModelProperty(example = "1234", value = "unique booking Id from cell")


  public String getBookingId() {
    return bookingId;
  }

  public void setBookingId(String bookingId) {
    this.bookingId = bookingId;
  }

  public BookingKafkaDataDTO receiptNumber(String receiptNumber) {
    this.receiptNumber = receiptNumber;
    return this;
  }

  /**
   * unique receiptNumber
   * @return receiptNumber
  */
  @ApiModelProperty(example = "1234-12asd-fdfsc", value = "unique receiptNumber")


  public String getReceiptNumber() {
    return receiptNumber;
  }

  public void setReceiptNumber(String receiptNumber) {
    this.receiptNumber = receiptNumber;
  }

  public BookingKafkaDataDTO date(String date) {
    this.date = date;
    return this;
  }

  /**
   * date of booking
   * @return date
  */
  @ApiModelProperty(example = "2022-05-26 15:31:31", value = "date of booking")


  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public BookingKafkaDataDTO vatType(String vatType) {
    this.vatType = vatType;
    return this;
  }

  /**
   * vat type of the transaction
   * @return vatType
  */
  @ApiModelProperty(example = "A", value = "vat type of the transaction")


  public String getVatType() {
    return vatType;
  }

  public void setVatType(String vatType) {
    this.vatType = vatType;
  }

  public BookingKafkaDataDTO vatValue(String vatValue) {
    this.vatValue = vatValue;
    return this;
  }

  /**
   * vat value of the transaction
   * @return vatValue
  */
  @ApiModelProperty(example = "20", value = "vat value of the transaction")


  public String getVatValue() {
    return vatValue;
  }

  public void setVatValue(String vatValue) {
    this.vatValue = vatValue;
  }

  public BookingKafkaDataDTO message(String message) {
    this.message = message;
    return this;
  }

  /**
   * message of the booking
   * @return message
  */
  @ApiModelProperty(example = "boking successfully", value = "message of the booking")


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public BookingKafkaDataDTO amount(String amount) {
    this.amount = amount;
    return this;
  }

  /**
   * the total amount due (NET + VAT) calculated by entervo, with discount
   * @return amount
  */
  @ApiModelProperty(example = "1500", value = "the total amount due (NET + VAT) calculated by entervo, with discount")


  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public BookingKafkaDataDTO originalAmount(String originalAmount) {
    this.originalAmount = originalAmount;
    return this;
  }

  /**
   * the total amount due (NET + VAT) calculated by entervo
   * @return originalAmount
  */
  @ApiModelProperty(example = "2000", value = "the total amount due (NET + VAT) calculated by entervo")


  public String getOriginalAmount() {
    return originalAmount;
  }

  public void setOriginalAmount(String originalAmount) {
    this.originalAmount = originalAmount;
  }

  public BookingKafkaDataDTO articleRef(String articleRef) {
    this.articleRef = articleRef;
    return this;
  }

  /**
   * id of paid article
   * @return articleRef
  */
  @ApiModelProperty(example = "10014", value = "id of paid article")


  public String getArticleRef() {
    return articleRef;
  }

  public void setArticleRef(String articleRef) {
    this.articleRef = articleRef;
  }

  public BookingKafkaDataDTO articleShortText(String articleShortText) {
    this.articleShortText = articleShortText;
    return this;
  }

  /**
   * Get articleShortText
   * @return articleShortText
  */
  @ApiModelProperty(example = "Late P. Shortterm", value = "")


  public String getArticleShortText() {
    return articleShortText;
  }

  public void setArticleShortText(String articleShortText) {
    this.articleShortText = articleShortText;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookingKafkaDataDTO bookingKafkaData = (BookingKafkaDataDTO) o;
    return Objects.equals(this.clientId, bookingKafkaData.clientId) &&
        Objects.equals(this.transactionId, bookingKafkaData.transactionId) &&
        Objects.equals(this.status, bookingKafkaData.status) &&
        Objects.equals(this.bookingId, bookingKafkaData.bookingId) &&
        Objects.equals(this.receiptNumber, bookingKafkaData.receiptNumber) &&
        Objects.equals(this.date, bookingKafkaData.date) &&
        Objects.equals(this.vatType, bookingKafkaData.vatType) &&
        Objects.equals(this.vatValue, bookingKafkaData.vatValue) &&
        Objects.equals(this.message, bookingKafkaData.message) &&
        Objects.equals(this.amount, bookingKafkaData.amount) &&
        Objects.equals(this.originalAmount, bookingKafkaData.originalAmount) &&
        Objects.equals(this.articleRef, bookingKafkaData.articleRef) &&
        Objects.equals(this.articleShortText, bookingKafkaData.articleShortText);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientId, transactionId, status, bookingId, receiptNumber, date, vatType, vatValue, message, amount, originalAmount, articleRef, articleShortText);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BookingKafkaDataDTO {\n");
    
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    bookingId: ").append(toIndentedString(bookingId)).append("\n");
    sb.append("    receiptNumber: ").append(toIndentedString(receiptNumber)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    vatType: ").append(toIndentedString(vatType)).append("\n");
    sb.append("    vatValue: ").append(toIndentedString(vatValue)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    originalAmount: ").append(toIndentedString(originalAmount)).append("\n");
    sb.append("    articleRef: ").append(toIndentedString(articleRef)).append("\n");
    sb.append("    articleShortText: ").append(toIndentedString(articleShortText)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

