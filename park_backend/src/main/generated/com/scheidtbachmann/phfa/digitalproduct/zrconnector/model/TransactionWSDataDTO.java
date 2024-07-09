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
 * TransactionWSDataDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-31T15:56:35.474500200+01:00[Africa/Tunis]")
public class TransactionWSDataDTO  implements Serializable {
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

  @JsonProperty("operator")
  private Integer operator;

  @JsonProperty("operatorName")
  private String operatorName;

  @JsonProperty("cellComputer")
  private Integer cellComputer;

  @JsonProperty("carParkAddress")
  private String carParkAddress;

  @JsonProperty("carParkName")
  private String carParkName;

  @JsonProperty("carParkLocale")
  private String carParkLocale;

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

  @JsonProperty("tenant")
  private String tenant;

  public TransactionWSDataDTO clientId(String clientId) {
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

  public TransactionWSDataDTO transactionId(String transactionId) {
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

  public TransactionWSDataDTO mediumType(String mediumType) {
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

  public TransactionWSDataDTO mediumValue(String mediumValue) {
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

  public TransactionWSDataDTO licensePlate(String licensePlate) {
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

  public TransactionWSDataDTO transactionType(String transactionType) {
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

  public TransactionWSDataDTO operator(Integer operator) {
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

  public TransactionWSDataDTO operatorName(String operatorName) {
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

  public TransactionWSDataDTO cellComputer(Integer cellComputer) {
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

  public TransactionWSDataDTO carParkAddress(String carParkAddress) {
    this.carParkAddress = carParkAddress;
    return this;
  }

  /**
   * Car park Address, (field5)
   * @return carParkAddress
  */
  @ApiModelProperty(example = "Breitestrasse . 7a 40468, MG", value = "Car park Address, (field5)")


  public String getCarParkAddress() {
    return carParkAddress;
  }

  public void setCarParkAddress(String carParkAddress) {
    this.carParkAddress = carParkAddress;
  }

  public TransactionWSDataDTO carParkName(String carParkName) {
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

  public TransactionWSDataDTO carParkLocale(String carParkLocale) {
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

  public TransactionWSDataDTO amount(String amount) {
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

  public TransactionWSDataDTO currency(String currency) {
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

  public TransactionWSDataDTO currencyDecimal(Integer currencyDecimal) {
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

  public TransactionWSDataDTO duration(String duration) {
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

  public TransactionWSDataDTO tariffTimeStart(String tariffTimeStart) {
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

  public TransactionWSDataDTO tariffTimeEnd(String tariffTimeEnd) {
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

  public TransactionWSDataDTO tenant(String tenant) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionWSDataDTO transactionWSData = (TransactionWSDataDTO) o;
    return Objects.equals(this.clientId, transactionWSData.clientId) &&
        Objects.equals(this.transactionId, transactionWSData.transactionId) &&
        Objects.equals(this.mediumType, transactionWSData.mediumType) &&
        Objects.equals(this.mediumValue, transactionWSData.mediumValue) &&
        Objects.equals(this.licensePlate, transactionWSData.licensePlate) &&
        Objects.equals(this.transactionType, transactionWSData.transactionType) &&
        Objects.equals(this.operator, transactionWSData.operator) &&
        Objects.equals(this.operatorName, transactionWSData.operatorName) &&
        Objects.equals(this.cellComputer, transactionWSData.cellComputer) &&
        Objects.equals(this.carParkAddress, transactionWSData.carParkAddress) &&
        Objects.equals(this.carParkName, transactionWSData.carParkName) &&
        Objects.equals(this.carParkLocale, transactionWSData.carParkLocale) &&
        Objects.equals(this.amount, transactionWSData.amount) &&
        Objects.equals(this.currency, transactionWSData.currency) &&
        Objects.equals(this.currencyDecimal, transactionWSData.currencyDecimal) &&
        Objects.equals(this.duration, transactionWSData.duration) &&
        Objects.equals(this.tariffTimeStart, transactionWSData.tariffTimeStart) &&
        Objects.equals(this.tariffTimeEnd, transactionWSData.tariffTimeEnd) &&
        Objects.equals(this.tenant, transactionWSData.tenant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientId, transactionId, mediumType, mediumValue, licensePlate, transactionType, operator, operatorName, cellComputer, carParkAddress, carParkName, carParkLocale, amount, currency, currencyDecimal, duration, tariffTimeStart, tariffTimeEnd, tenant);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransactionWSDataDTO {\n");
    
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    mediumType: ").append(toIndentedString(mediumType)).append("\n");
    sb.append("    mediumValue: ").append(toIndentedString(mediumValue)).append("\n");
    sb.append("    licensePlate: ").append(toIndentedString(licensePlate)).append("\n");
    sb.append("    transactionType: ").append(toIndentedString(transactionType)).append("\n");
    sb.append("    operator: ").append(toIndentedString(operator)).append("\n");
    sb.append("    operatorName: ").append(toIndentedString(operatorName)).append("\n");
    sb.append("    cellComputer: ").append(toIndentedString(cellComputer)).append("\n");
    sb.append("    carParkAddress: ").append(toIndentedString(carParkAddress)).append("\n");
    sb.append("    carParkName: ").append(toIndentedString(carParkName)).append("\n");
    sb.append("    carParkLocale: ").append(toIndentedString(carParkLocale)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    currencyDecimal: ").append(toIndentedString(currencyDecimal)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    tariffTimeStart: ").append(toIndentedString(tariffTimeStart)).append("\n");
    sb.append("    tariffTimeEnd: ").append(toIndentedString(tariffTimeEnd)).append("\n");
    sb.append("    tenant: ").append(toIndentedString(tenant)).append("\n");
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

