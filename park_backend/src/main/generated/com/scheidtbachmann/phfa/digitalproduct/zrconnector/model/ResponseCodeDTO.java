package com.scheidtbachmann.phfa.digitalproduct.zrconnector.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.scheidtbachmann.phfa.digitalproduct.zrconnector.model.ErrorCodeDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ResponseCodeDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-31T15:56:35.474500200+01:00[Africa/Tunis]")
public class ResponseCodeDTO  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("code")
  private ErrorCodeDTO code;

  @JsonProperty("message")
  private String message;

  @JsonProperty("timeStamp")
  private String timeStamp;

  public ResponseCodeDTO code(ErrorCodeDTO code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
  */
  @ApiModelProperty(value = "")

  @Valid

  public ErrorCodeDTO getCode() {
    return code;
  }

  public void setCode(ErrorCodeDTO code) {
    this.code = code;
  }

  public ResponseCodeDTO message(String message) {
    this.message = message;
    return this;
  }

  /**
   * a human-readable message indicated the cause of a failure
   * @return message
  */
  @ApiModelProperty(example = "text message of the action/error", value = "a human-readable message indicated the cause of a failure")


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ResponseCodeDTO timeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
    return this;
  }

  /**
   * time stamp of error code
   * @return timeStamp
  */
  @ApiModelProperty(example = "2021-08-23T16:08:27.000[GMT]", value = "time stamp of error code")


  public String getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseCodeDTO responseCode = (ResponseCodeDTO) o;
    return Objects.equals(this.code, responseCode.code) &&
        Objects.equals(this.message, responseCode.message) &&
        Objects.equals(this.timeStamp, responseCode.timeStamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, timeStamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseCodeDTO {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
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

