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
 * BackofficeKafkaDataDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-31T15:56:35.474500200+01:00[Africa/Tunis]")
public class BackofficeKafkaDataDTO  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("transactionId")
  private String transactionId;

  @JsonProperty("appId")
  private Integer appId;

  @JsonProperty("mediumType")
  private String mediumType = "";

  @JsonProperty("mediumValue")
  private String mediumValue = "";

  @JsonProperty("type")
  private String type;

  @JsonProperty("status")
  private String status;

  @JsonProperty("epan")
  private String epan;

  @JsonProperty("operatorId")
  private Integer operatorId;

  @JsonProperty("cellComputerId")
  private Integer cellComputerId;

  @JsonProperty("amount")
  private String amount;

  @JsonProperty("currency")
  private String currency;

  @JsonProperty("currencyDecimal")
  private Integer currencyDecimal;

  @JsonProperty("vatType")
  private String vatType;

  @JsonProperty("vatValue")
  private String vatValue;

  @JsonProperty("receiptNumber")
  private String receiptNumber;

  @JsonProperty("entervoBookingId")
  private String entervoBookingId;

  @JsonProperty("paymentType")
  private String paymentType;

  public BackofficeKafkaDataDTO transactionId(String transactionId) {
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

  public BackofficeKafkaDataDTO appId(Integer appId) {
    this.appId = appId;
    return this;
  }

  /**
   * Id of application
   * @return appId
  */
  @ApiModelProperty(example = "123", value = "Id of application")


  public Integer getAppId() {
    return appId;
  }

  public void setAppId(Integer appId) {
    this.appId = appId;
  }

  public BackofficeKafkaDataDTO mediumType(String mediumType) {
    this.mediumType = mediumType;
    return this;
  }

  /**
   * type of medium, allowed types: barcode, lpn, mobileid. Default is barcode
   * @return mediumType
  */
  @ApiModelProperty(example = "lpn", value = "type of medium, allowed types: barcode, lpn, mobileid. Default is barcode")


  public String getMediumType() {
    return mediumType;
  }

  public void setMediumType(String mediumType) {
    this.mediumType = mediumType;
  }

  public BackofficeKafkaDataDTO mediumValue(String mediumValue) {
    this.mediumValue = mediumValue;
    return this;
  }

  /**
   * medium
   * @return mediumValue
  */
  @ApiModelProperty(example = "MG-123SB", value = "medium")


  public String getMediumValue() {
    return mediumValue;
  }

  public void setMediumValue(String mediumValue) {
    this.mediumValue = mediumValue;
  }

  public BackofficeKafkaDataDTO type(String type) {
    this.type = type;
    return this;
  }

  /**
   * type of transaction, allowed types: present, late
   * @return type
  */
  @ApiModelProperty(example = "late", value = "type of transaction, allowed types: present, late")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BackofficeKafkaDataDTO status(String status) {
    this.status = status;
    return this;
  }

  /**
   * status of transaction, can be for booked or just checked
   * @return status
  */
  @ApiModelProperty(example = "found, notfound, failed, success", value = "status of transaction, can be for booked or just checked")


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public BackofficeKafkaDataDTO epan(String epan) {
    this.epan = epan;
    return this;
  }

  /**
   * epan of the transaction
   * @return epan
  */
  @ApiModelProperty(example = "2112", value = "epan of the transaction")


  public String getEpan() {
    return epan;
  }

  public void setEpan(String epan) {
    this.epan = epan;
  }

  public BackofficeKafkaDataDTO operatorId(Integer operatorId) {
    this.operatorId = operatorId;
    return this;
  }

  /**
   * operator id
   * @return operatorId
  */
  @ApiModelProperty(value = "operator id")


  public Integer getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(Integer operatorId) {
    this.operatorId = operatorId;
  }

  public BackofficeKafkaDataDTO cellComputerId(Integer cellComputerId) {
    this.cellComputerId = cellComputerId;
    return this;
  }

  /**
   * cell computer id
   * @return cellComputerId
  */
  @ApiModelProperty(value = "cell computer id")


  public Integer getCellComputerId() {
    return cellComputerId;
  }

  public void setCellComputerId(Integer cellComputerId) {
    this.cellComputerId = cellComputerId;
  }

  public BackofficeKafkaDataDTO amount(String amount) {
    this.amount = amount;
    return this;
  }

  /**
   * the total amount due (NET + VAT) calculated by entervo
   * @return amount
  */
  @ApiModelProperty(example = "1500", value = "the total amount due (NET + VAT) calculated by entervo")


  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public BackofficeKafkaDataDTO currency(String currency) {
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

  public BackofficeKafkaDataDTO currencyDecimal(Integer currencyDecimal) {
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

  public BackofficeKafkaDataDTO vatType(String vatType) {
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

  public BackofficeKafkaDataDTO vatValue(String vatValue) {
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

  public BackofficeKafkaDataDTO receiptNumber(String receiptNumber) {
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

  public BackofficeKafkaDataDTO entervoBookingId(String entervoBookingId) {
    this.entervoBookingId = entervoBookingId;
    return this;
  }

  /**
   * unique booking Id from cell
   * @return entervoBookingId
  */
  @ApiModelProperty(example = "1234", value = "unique booking Id from cell")


  public String getEntervoBookingId() {
    return entervoBookingId;
  }

  public void setEntervoBookingId(String entervoBookingId) {
    this.entervoBookingId = entervoBookingId;
  }

  public BackofficeKafkaDataDTO paymentType(String paymentType) {
    this.paymentType = paymentType;
    return this;
  }

  /**
   * type of payment
   * @return paymentType
  */
  @ApiModelProperty(example = "VISA", value = "type of payment")


  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BackofficeKafkaDataDTO backofficeKafkaData = (BackofficeKafkaDataDTO) o;
    return Objects.equals(this.transactionId, backofficeKafkaData.transactionId) &&
        Objects.equals(this.appId, backofficeKafkaData.appId) &&
        Objects.equals(this.mediumType, backofficeKafkaData.mediumType) &&
        Objects.equals(this.mediumValue, backofficeKafkaData.mediumValue) &&
        Objects.equals(this.type, backofficeKafkaData.type) &&
        Objects.equals(this.status, backofficeKafkaData.status) &&
        Objects.equals(this.epan, backofficeKafkaData.epan) &&
        Objects.equals(this.operatorId, backofficeKafkaData.operatorId) &&
        Objects.equals(this.cellComputerId, backofficeKafkaData.cellComputerId) &&
        Objects.equals(this.amount, backofficeKafkaData.amount) &&
        Objects.equals(this.currency, backofficeKafkaData.currency) &&
        Objects.equals(this.currencyDecimal, backofficeKafkaData.currencyDecimal) &&
        Objects.equals(this.vatType, backofficeKafkaData.vatType) &&
        Objects.equals(this.vatValue, backofficeKafkaData.vatValue) &&
        Objects.equals(this.receiptNumber, backofficeKafkaData.receiptNumber) &&
        Objects.equals(this.entervoBookingId, backofficeKafkaData.entervoBookingId) &&
        Objects.equals(this.paymentType, backofficeKafkaData.paymentType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionId, appId, mediumType, mediumValue, type, status, epan, operatorId, cellComputerId, amount, currency, currencyDecimal, vatType, vatValue, receiptNumber, entervoBookingId, paymentType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BackofficeKafkaDataDTO {\n");
    
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    appId: ").append(toIndentedString(appId)).append("\n");
    sb.append("    mediumType: ").append(toIndentedString(mediumType)).append("\n");
    sb.append("    mediumValue: ").append(toIndentedString(mediumValue)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    epan: ").append(toIndentedString(epan)).append("\n");
    sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
    sb.append("    cellComputerId: ").append(toIndentedString(cellComputerId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    currencyDecimal: ").append(toIndentedString(currencyDecimal)).append("\n");
    sb.append("    vatType: ").append(toIndentedString(vatType)).append("\n");
    sb.append("    vatValue: ").append(toIndentedString(vatValue)).append("\n");
    sb.append("    receiptNumber: ").append(toIndentedString(receiptNumber)).append("\n");
    sb.append("    entervoBookingId: ").append(toIndentedString(entervoBookingId)).append("\n");
    sb.append("    paymentType: ").append(toIndentedString(paymentType)).append("\n");
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

