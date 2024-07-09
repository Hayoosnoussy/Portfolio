package data_cache.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.Objects;

/**
 * Configuration of operator
 */
@Data
public class Operator {
  @JsonProperty("operatorId")
  private Integer operatorId;

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  @JsonProperty("email")
  private String email;

  @JsonProperty("phone")
  private String phone;
  
  @JsonProperty("address")
  private String address;

  public Operator operatorId(Integer operatorId) {
    this.operatorId = operatorId;
    return this;
  }

 

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Operator operatorConfiguration = (Operator) o;
    return Objects.equals(this.operatorId, operatorConfiguration.operatorId) &&
        Objects.equals(this.name, operatorConfiguration.name) &&
        Objects.equals(this.description, operatorConfiguration.description) &&
        Objects.equals(this.email, operatorConfiguration.email) &&
        Objects.equals(this.phone, operatorConfiguration.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operatorId, name, description, email, phone);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OperatorConfigurationDTO {\n");
    
    sb.append("    operatorId: ").append(toIndentedString(operatorId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
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

