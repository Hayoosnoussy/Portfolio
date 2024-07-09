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
 * BookingWSDataDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-31T15:56:35.474500200+01:00[Africa/Tunis]")
public class BookingWSDataDTO  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("clientId")
  private String clientId;

  @JsonProperty("transactionId")
  private String transactionId;

  @JsonProperty("status")
  private String status;

  @JsonProperty("carParkName")
  private String carParkName;

  @JsonProperty("carParkLocale")
  private String carParkLocale;

  @JsonProperty("operatorAddress")
  private String operatorAddress;

  @JsonProperty("amount")
  private String amount;

  @JsonProperty("currency")
  private String currency;

  @JsonProperty("currencyDecimal")
  private Integer currencyDecimal;

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("tariffTimeStart")
  private String tariffTimeStart;

  @JsonProperty("tariffTimeEnd")
  private String tariffTimeEnd;

  @JsonProperty("articleShortText")
  private String articleShortText;

  @JsonProperty("message")
  private String message;

  @JsonProperty("mediumType")
  private String mediumType;

  @JsonProperty("mediumValue")
  private String mediumValue;

  public BookingWSDataDTO clientId(String clientId) {
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

  public BookingWSDataDTO transactionId(String transactionId) {
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

  public BookingWSDataDTO status(String status) {
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

  public BookingWSDataDTO carParkName(String carParkName) {
    this.carParkName = carParkName;
    return this;
  }

  /**
   * Name of car park / facility
   * @return carParkName
  */
  @ApiModelProperty(example = "Big Car park House", value = "Name of car park / facility")


  public String getCarParkName() {
    return carParkName;
  }

  public void setCarParkName(String carParkName) {
    this.carParkName = carParkName;
  }

  public BookingWSDataDTO carParkLocale(String carParkLocale) {
    this.carParkLocale = carParkLocale;
    return this;
  }

  /**
   * car park Locale
   * @return carParkLocale
  */
  @ApiModelProperty(example = "de_DE", value = "car park Locale")


  public String getCarParkLocale() {
    return carParkLocale;
  }

  public void setCarParkLocale(String carParkLocale) {
    this.carParkLocale = carParkLocale;
  }

  public BookingWSDataDTO operatorAddress(String operatorAddress) {
    this.operatorAddress = operatorAddress;
    return this;
  }

  /**
   * Car park operator Address
   * @return operatorAddress
  */
  @ApiModelProperty(example = "Breitestrasse . 7a 40468, MG", value = "Car park operator Address")


  public String getOperatorAddress() {
    return operatorAddress;
  }

  public void setOperatorAddress(String operatorAddress) {
    this.operatorAddress = operatorAddress;
  }

  public BookingWSDataDTO amount(String amount) {
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

  public BookingWSDataDTO currency(String currency) {
    this.currency = currency;
    return this;
  }

  /**
   * the currency key to be used by the 3rd party application (driven by entervo configuration)
   * @return currency
  */
  @ApiModelProperty(example = "EUR", value = "the currency key to be used by the 3rd party application (driven by entervo configuration)")


  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public BookingWSDataDTO currencyDecimal(Integer currencyDecimal) {
    this.currencyDecimal = currencyDecimal;
    return this;
  }

  /**
   * count of decimal for currency
   * @return currencyDecimal
  */
  @ApiModelProperty(example = "2", value = "count of decimal for currency")


  public Integer getCurrencyDecimal() {
    return currencyDecimal;
  }

  public void setCurrencyDecimal(Integer currencyDecimal) {
    this.currencyDecimal = currencyDecimal;
  }

  public BookingWSDataDTO duration(String duration) {
    this.duration = duration;
    return this;
  }

  /**
   * duration of the parking transaction (days-hours-minutes)
   * @return duration
  */
  @ApiModelProperty(example = "00-02-14", value = "duration of the parking transaction (days-hours-minutes)")


  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public BookingWSDataDTO tariffTimeStart(String tariffTimeStart) {
    this.tariffTimeStart = tariffTimeStart;
    return this;
  }

  /**
   * Get tariffTimeStart
   * @return tariffTimeStart
  */
  @ApiModelProperty(example = "2020-06-16T14:04:53.000+01:00", value = "")


  public String getTariffTimeStart() {
    return tariffTimeStart;
  }

  public void setTariffTimeStart(String tariffTimeStart) {
    this.tariffTimeStart = tariffTimeStart;
  }

  public BookingWSDataDTO tariffTimeEnd(String tariffTimeEnd) {
    this.tariffTimeEnd = tariffTimeEnd;
    return this;
  }

  /**
   * Get tariffTimeEnd
   * @return tariffTimeEnd
  */
  @ApiModelProperty(example = "2020-06-16T14:27:16.000+01:00", value = "")


  public String getTariffTimeEnd() {
    return tariffTimeEnd;
  }

  public void setTariffTimeEnd(String tariffTimeEnd) {
    this.tariffTimeEnd = tariffTimeEnd;
  }

  public BookingWSDataDTO articleShortText(String articleShortText) {
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

  public BookingWSDataDTO message(String message) {
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

  public BookingWSDataDTO mediumType(String mediumType) {
    this.mediumType = mediumType;
    return this;
  }

  /**
   * the medium type
   * @return mediumType
  */
  @ApiModelProperty(value = "the medium type")


  public String getMediumType() {
    return mediumType;
  }

  public void setMediumType(String mediumType) {
    this.mediumType = mediumType;
  }

  public BookingWSDataDTO mediumValue(String mediumValue) {
    this.mediumValue = mediumValue;
    return this;
  }

  /**
   * the medium value
   * @return mediumValue
  */
  @ApiModelProperty(value = "the medium value")


  public String getMediumValue() {
    return mediumValue;
  }

  public void setMediumValue(String mediumValue) {
    this.mediumValue = mediumValue;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookingWSDataDTO bookingWSData = (BookingWSDataDTO) o;
    return Objects.equals(this.clientId, bookingWSData.clientId) &&
        Objects.equals(this.transactionId, bookingWSData.transactionId) &&
        Objects.equals(this.status, bookingWSData.status) &&
        Objects.equals(this.carParkName, bookingWSData.carParkName) &&
        Objects.equals(this.carParkLocale, bookingWSData.carParkLocale) &&
        Objects.equals(this.operatorAddress, bookingWSData.operatorAddress) &&
        Objects.equals(this.amount, bookingWSData.amount) &&
        Objects.equals(this.currency, bookingWSData.currency) &&
        Objects.equals(this.currencyDecimal, bookingWSData.currencyDecimal) &&
        Objects.equals(this.duration, bookingWSData.duration) &&
        Objects.equals(this.tariffTimeStart, bookingWSData.tariffTimeStart) &&
        Objects.equals(this.tariffTimeEnd, bookingWSData.tariffTimeEnd) &&
        Objects.equals(this.articleShortText, bookingWSData.articleShortText) &&
        Objects.equals(this.message, bookingWSData.message) &&
        Objects.equals(this.mediumType, bookingWSData.mediumType) &&
        Objects.equals(this.mediumValue, bookingWSData.mediumValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientId, transactionId, status, carParkName, carParkLocale, operatorAddress, amount, currency, currencyDecimal, duration, tariffTimeStart, tariffTimeEnd, articleShortText, message, mediumType, mediumValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BookingWSDataDTO {\n");
    
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    carParkName: ").append(toIndentedString(carParkName)).append("\n");
    sb.append("    carParkLocale: ").append(toIndentedString(carParkLocale)).append("\n");
    sb.append("    operatorAddress: ").append(toIndentedString(operatorAddress)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    currencyDecimal: ").append(toIndentedString(currencyDecimal)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    tariffTimeStart: ").append(toIndentedString(tariffTimeStart)).append("\n");
    sb.append("    tariffTimeEnd: ").append(toIndentedString(tariffTimeEnd)).append("\n");
    sb.append("    articleShortText: ").append(toIndentedString(articleShortText)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    mediumType: ").append(toIndentedString(mediumType)).append("\n");
    sb.append("    mediumValue: ").append(toIndentedString(mediumValue)).append("\n");
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

