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
 * TransactionKafkaDataDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-31T15:56:35.474500200+01:00[Africa/Tunis]")
public class TransactionKafkaDataDTO  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("clientId")
  private String clientId;

  @JsonProperty("transactionId")
  private String transactionId;

  @JsonProperty("mediumType")
  private String mediumType = "barcode";

  @JsonProperty("mediumValue")
  private String mediumValue = "";

  @JsonProperty("licensePlate")
  private String licensePlate = "";

  @JsonProperty("transactionType")
  private String transactionType;

  @JsonProperty("epan")
  private String epan;

  @JsonProperty("operator")
  private Integer operator;

  @JsonProperty("operatorName")
  private String operatorName;

  @JsonProperty("cellComputer")
  private Integer cellComputer;

  @JsonProperty("facilityId")
  private String facilityId;

  @JsonProperty("amount")
  private String amount;

  @JsonProperty("currency")
  private String currency;

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("tariffTimeStart")
  private String tariffTimeStart;

  @JsonProperty("tariffTimeEnd")
  private String tariffTimeEnd;

  @JsonProperty("tenant")
  private String tenant;

  @JsonProperty("vatType")
  private String vatType;

  @JsonProperty("vatValue")
  private String vatValue;

  public TransactionKafkaDataDTO clientId(String clientId) {
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

  public TransactionKafkaDataDTO transactionId(String transactionId) {
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

  public TransactionKafkaDataDTO mediumType(String mediumType) {
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

  public TransactionKafkaDataDTO mediumValue(String mediumValue) {
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

  public TransactionKafkaDataDTO licensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
    return this;
  }

  /**
   * associated lpn medium in case mediumType is barcode as input medium
   * @return licensePlate
  */
  @ApiModelProperty(example = "MG-123SB", value = "associated lpn medium in case mediumType is barcode as input medium")


  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public TransactionKafkaDataDTO transactionType(String transactionType) {
    this.transactionType = transactionType;
    return this;
  }

  /**
   * type of transaction, allowed types: present, latePayment
   * @return transactionType
  */
  @ApiModelProperty(example = "present", value = "type of transaction, allowed types: present, latePayment")


  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public TransactionKafkaDataDTO epan(String epan) {
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

  public TransactionKafkaDataDTO operator(Integer operator) {
    this.operator = operator;
    return this;
  }

  /**
   * operator id
   * @return operator
  */
  @ApiModelProperty(value = "operator id")


  public Integer getOperator() {
    return operator;
  }

  public void setOperator(Integer operator) {
    this.operator = operator;
  }

  public TransactionKafkaDataDTO operatorName(String operatorName) {
    this.operatorName = operatorName;
    return this;
  }

  /**
   * operator name
   * @return operatorName
  */
  @ApiModelProperty(example = "test operator", value = "operator name")


  public String getOperatorName() {
    return operatorName;
  }

  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }

  public TransactionKafkaDataDTO cellComputer(Integer cellComputer) {
    this.cellComputer = cellComputer;
    return this;
  }

  /**
   * cell computer id
   * @return cellComputer
  */
  @ApiModelProperty(value = "cell computer id")


  public Integer getCellComputer() {
    return cellComputer;
  }

  public void setCellComputer(Integer cellComputer) {
    this.cellComputer = cellComputer;
  }

  public TransactionKafkaDataDTO facilityId(String facilityId) {
    this.facilityId = facilityId;
    return this;
  }

  /**
   * facilityId on cell computer field13
   * @return facilityId
  */
  @ApiModelProperty(example = "2112", value = "facilityId on cell computer field13")


  public String getFacilityId() {
    return facilityId;
  }

  public void setFacilityId(String facilityId) {
    this.facilityId = facilityId;
  }

  public TransactionKafkaDataDTO amount(String amount) {
    this.amount = amount;
    return this;
  }

  /**
   * the amount due (NET + VAT) calculated by entervo, with discounts
   * @return amount
  */
  @ApiModelProperty(example = "1500", value = "the amount due (NET + VAT) calculated by entervo, with discounts")


  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public TransactionKafkaDataDTO currency(String currency) {
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

  public TransactionKafkaDataDTO duration(String duration) {
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

  public TransactionKafkaDataDTO tariffTimeStart(String tariffTimeStart) {
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

  public TransactionKafkaDataDTO tariffTimeEnd(String tariffTimeEnd) {
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

  public TransactionKafkaDataDTO tenant(String tenant) {
    this.tenant = tenant;
    return this;
  }

  /**
   * tenant name of merchant
   * @return tenant
  */
  @ApiModelProperty(example = "pgs-testPayVision", value = "tenant name of merchant")


  public String getTenant() {
    return tenant;
  }

  public void setTenant(String tenant) {
    this.tenant = tenant;
  }

  public TransactionKafkaDataDTO vatType(String vatType) {
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

  public TransactionKafkaDataDTO vatValue(String vatValue) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionKafkaDataDTO transactionKafkaData = (TransactionKafkaDataDTO) o;
    return Objects.equals(this.clientId, transactionKafkaData.clientId) &&
        Objects.equals(this.transactionId, transactionKafkaData.transactionId) &&
        Objects.equals(this.mediumType, transactionKafkaData.mediumType) &&
        Objects.equals(this.mediumValue, transactionKafkaData.mediumValue) &&
        Objects.equals(this.licensePlate, transactionKafkaData.licensePlate) &&
        Objects.equals(this.transactionType, transactionKafkaData.transactionType) &&
        Objects.equals(this.epan, transactionKafkaData.epan) &&
        Objects.equals(this.operator, transactionKafkaData.operator) &&
        Objects.equals(this.operatorName, transactionKafkaData.operatorName) &&
        Objects.equals(this.cellComputer, transactionKafkaData.cellComputer) &&
        Objects.equals(this.facilityId, transactionKafkaData.facilityId) &&
        Objects.equals(this.amount, transactionKafkaData.amount) &&
        Objects.equals(this.currency, transactionKafkaData.currency) &&
        Objects.equals(this.duration, transactionKafkaData.duration) &&
        Objects.equals(this.tariffTimeStart, transactionKafkaData.tariffTimeStart) &&
        Objects.equals(this.tariffTimeEnd, transactionKafkaData.tariffTimeEnd) &&
        Objects.equals(this.tenant, transactionKafkaData.tenant) &&
        Objects.equals(this.vatType, transactionKafkaData.vatType) &&
        Objects.equals(this.vatValue, transactionKafkaData.vatValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientId, transactionId, mediumType, mediumValue, licensePlate, transactionType, epan, operator, operatorName, cellComputer, facilityId, amount, currency, duration, tariffTimeStart, tariffTimeEnd, tenant, vatType, vatValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransactionKafkaDataDTO {\n");
    
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    mediumType: ").append(toIndentedString(mediumType)).append("\n");
    sb.append("    mediumValue: ").append(toIndentedString(mediumValue)).append("\n");
    sb.append("    licensePlate: ").append(toIndentedString(licensePlate)).append("\n");
    sb.append("    transactionType: ").append(toIndentedString(transactionType)).append("\n");
    sb.append("    epan: ").append(toIndentedString(epan)).append("\n");
    sb.append("    operator: ").append(toIndentedString(operator)).append("\n");
    sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
    sb.append("    cellComputer: ").append(toIndentedString(cellComputer)).append("\n");
    sb.append("    facilityId: ").append(toIndentedString(facilityId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    tariffTimeStart: ").append(toIndentedString(tariffTimeStart)).append("\n");
    sb.append("    tariffTimeEnd: ").append(toIndentedString(tariffTimeEnd)).append("\n");
    sb.append("    tenant: ").append(toIndentedString(tenant)).append("\n");
    sb.append("    vatType: ").append(toIndentedString(vatType)).append("\n");
    sb.append("    vatValue: ").append(toIndentedString(vatValue)).append("\n");
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

