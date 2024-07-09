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
 * VersionDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-31T15:56:35.474500200+01:00[Africa/Tunis]")
public class VersionDTO  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("service-version")
  private String serviceVersion = "1.0.0";

  @JsonProperty("interface-version")
  private String interfaceVersion = "0.1.6";

  public VersionDTO serviceVersion(String serviceVersion) {
    this.serviceVersion = serviceVersion;
    return this;
  }

  /**
   * Get serviceVersion
   * @return serviceVersion
  */
  @ApiModelProperty(value = "")


  public String getServiceVersion() {
    return serviceVersion;
  }

  public void setServiceVersion(String serviceVersion) {
    this.serviceVersion = serviceVersion;
  }

  public VersionDTO interfaceVersion(String interfaceVersion) {
    this.interfaceVersion = interfaceVersion;
    return this;
  }

  /**
   * Get interfaceVersion
   * @return interfaceVersion
  */
  @ApiModelProperty(value = "")


  public String getInterfaceVersion() {
    return interfaceVersion;
  }

  public void setInterfaceVersion(String interfaceVersion) {
    this.interfaceVersion = interfaceVersion;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VersionDTO version = (VersionDTO) o;
    return Objects.equals(this.serviceVersion, version.serviceVersion) &&
        Objects.equals(this.interfaceVersion, version.interfaceVersion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serviceVersion, interfaceVersion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VersionDTO {\n");
    
    sb.append("    serviceVersion: ").append(toIndentedString(serviceVersion)).append("\n");
    sb.append("    interfaceVersion: ").append(toIndentedString(interfaceVersion)).append("\n");
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

