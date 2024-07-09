package com.scheidtbachmann.phfa.digitalproduct.zrconnector.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BookingDataDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-31T15:56:35.474500200+01:00[Africa/Tunis]")
public class BookingDataDTO  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("clientId")
  private String clientId;

  @JsonProperty("mediaType")
  private String mediaType;

  @JsonProperty("payId")
  private String payId;

  @JsonProperty("mediumType")
  private String mediumType = "barcode";

  @JsonProperty("mediumValue")
  private String mediumValue = "";

  @JsonProperty("transactions")
  @Valid
  private List<String> transactions = null;

  public BookingDataDTO clientId(String clientId) {
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

  public BookingDataDTO mediaType(String mediaType) {
    this.mediaType = mediaType;
    return this;
  }

  /**
   * type of payment medium
   * @return mediaType
  */
  @ApiModelProperty(example = "VISA", value = "type of payment medium")


  public String getMediaType() {
    return mediaType;
  }

  public void setMediaType(String mediaType) {
    this.mediaType = mediaType;
  }

  public BookingDataDTO payId(String payId) {
    this.payId = payId;
    return this;
  }

  /**
   * Id of payment transaction from PGS
   * @return payId
  */
  @ApiModelProperty(example = "C-4f7ee476-1224-49f2-9b33-73173904e13e", value = "Id of payment transaction from PGS")


  public String getPayId() {
    return payId;
  }

  public void setPayId(String payId) {
    this.payId = payId;
  }

  public BookingDataDTO mediumType(String mediumType) {
    this.mediumType = mediumType;
    return this;
  }

  /**
   * type of medium, allowed types: barcode, lpn. Default is barcode
   * @return mediumType
  */
  @ApiModelProperty(example = "lpn", value = "type of medium, allowed types: barcode, lpn. Default is barcode")


  public String getMediumType() {
    return mediumType;
  }

  public void setMediumType(String mediumType) {
    this.mediumType = mediumType;
  }

  public BookingDataDTO mediumValue(String mediumValue) {
    this.mediumValue = mediumValue;
    return this;
  }

  /**
   * medium value, lpn or barcode
   * @return mediumValue
  */
  @ApiModelProperty(example = "MG-123SB", value = "medium value, lpn or barcode")


  public String getMediumValue() {
    return mediumValue;
  }

  public void setMediumValue(String mediumValue) {
    this.mediumValue = mediumValue;
  }

  public BookingDataDTO transactions(List<String> transactions) {
    this.transactions = transactions;
    return this;
  }

  public BookingDataDTO addTransactionsItem(String transactionsItem) {
    if (this.transactions == null) {
      this.transactions = new ArrayList<>();
    }
    this.transactions.add(transactionsItem);
    return this;
  }

  /**
   * list of transactionsIds for booking
   * @return transactions
  */
  @ApiModelProperty(value = "list of transactionsIds for booking")


  public List<String> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<String> transactions) {
    this.transactions = transactions;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookingDataDTO bookingData = (BookingDataDTO) o;
    return Objects.equals(this.clientId, bookingData.clientId) &&
        Objects.equals(this.mediaType, bookingData.mediaType) &&
        Objects.equals(this.payId, bookingData.payId) &&
        Objects.equals(this.mediumType, bookingData.mediumType) &&
        Objects.equals(this.mediumValue, bookingData.mediumValue) &&
        Objects.equals(this.transactions, bookingData.transactions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientId, mediaType, payId, mediumType, mediumValue, transactions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BookingDataDTO {\n");
    
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    mediaType: ").append(toIndentedString(mediaType)).append("\n");
    sb.append("    payId: ").append(toIndentedString(payId)).append("\n");
    sb.append("    mediumType: ").append(toIndentedString(mediumType)).append("\n");
    sb.append("    mediumValue: ").append(toIndentedString(mediumValue)).append("\n");
    sb.append("    transactions: ").append(toIndentedString(transactions)).append("\n");
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

