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
 * RequestDataDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-31T15:56:35.474500200+01:00[Africa/Tunis]")
public class RequestDataDTO  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("clientId")
  private String clientId;

  @JsonProperty("mediumType")
  private String mediumType = "barcode";

  @JsonProperty("mediumValue")
  private String mediumValue = "";

  @JsonProperty("licensePlate")
  private String licensePlate = "";

  @JsonProperty("present")
  @Valid
  private List<String> present = null;

  @JsonProperty("latePayments")
  @Valid
  private List<String> latePayments = null;

  public RequestDataDTO clientId(String clientId) {
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

  public RequestDataDTO mediumType(String mediumType) {
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

  public RequestDataDTO mediumValue(String mediumValue) {
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

  public RequestDataDTO licensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
    return this;
  }

  /**
   * license plate for late paymnet, if is empty, chceck the license plate from barcode in mediumValue (MVP 2.3)
   * @return licensePlate
  */
  @ApiModelProperty(example = "MG-123SB", value = "license plate for late paymnet, if is empty, chceck the license plate from barcode in mediumValue (MVP 2.3)")


  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public RequestDataDTO present(List<String> present) {
    this.present = present;
    return this;
  }

  public RequestDataDTO addPresentItem(String presentItem) {
    if (this.present == null) {
      this.present = new ArrayList<>();
    }
    this.present.add(presentItem);
    return this;
  }

  /**
   * list of car parks for check present transactions
   * @return present
  */
  @ApiModelProperty(value = "list of car parks for check present transactions")


  public List<String> getPresent() {
    return present;
  }

  public void setPresent(List<String> present) {
    this.present = present;
  }

  public RequestDataDTO latePayments(List<String> latePayments) {
    this.latePayments = latePayments;
    return this;
  }

  public RequestDataDTO addLatePaymentsItem(String latePaymentsItem) {
    if (this.latePayments == null) {
      this.latePayments = new ArrayList<>();
    }
    this.latePayments.add(latePaymentsItem);
    return this;
  }

  /**
   * list of car parks for check late payment transactions
   * @return latePayments
  */
  @ApiModelProperty(value = "list of car parks for check late payment transactions")


  public List<String> getLatePayments() {
    return latePayments;
  }

  public void setLatePayments(List<String> latePayments) {
    this.latePayments = latePayments;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequestDataDTO requestData = (RequestDataDTO) o;
    return Objects.equals(this.clientId, requestData.clientId) &&
        Objects.equals(this.mediumType, requestData.mediumType) &&
        Objects.equals(this.mediumValue, requestData.mediumValue) &&
        Objects.equals(this.licensePlate, requestData.licensePlate) &&
        Objects.equals(this.present, requestData.present) &&
        Objects.equals(this.latePayments, requestData.latePayments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientId, mediumType, mediumValue, licensePlate, present, latePayments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequestDataDTO {\n");
    
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    mediumType: ").append(toIndentedString(mediumType)).append("\n");
    sb.append("    mediumValue: ").append(toIndentedString(mediumValue)).append("\n");
    sb.append("    licensePlate: ").append(toIndentedString(licensePlate)).append("\n");
    sb.append("    present: ").append(toIndentedString(present)).append("\n");
    sb.append("    latePayments: ").append(toIndentedString(latePayments)).append("\n");
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

