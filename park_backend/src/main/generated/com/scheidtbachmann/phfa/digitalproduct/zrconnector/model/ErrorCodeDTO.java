package com.scheidtbachmann.phfa.digitalproduct.zrconnector.model;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Unique error code of transaction
 */
public enum ErrorCodeDTO {
  
  NO_ERROR("NO_ERROR"),
  
  NO_DATA("NO_DATA"),
  
  ERR_GENERAL("ERR_GENERAL"),
  
  ERR_BAD_DATA("ERR_BAD_DATA"),
  
  ERR_NORESOURCE_FOUND("ERR_NORESOURCE_FOUND");

  private String value;

  ErrorCodeDTO(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ErrorCodeDTO fromValue(String value) {
    for (ErrorCodeDTO b : ErrorCodeDTO.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

