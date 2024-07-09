package data_cache.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Configuration of brain tree PSP for merchant
 */
public class BrainTreePSPValue {
  @JsonProperty("environment")
  private String environment;
  @JsonProperty("merchantId")
  private String merchantId;
  @JsonProperty("publicKey")
  private String publicKey;
  @JsonProperty("privateKey")
  private String privateKey;

  public String getEnvironment() {
    return environment;
  }

  public void setEnvironment(String environment) {
    this.environment = environment;
  }

  public String getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(String merchantId) {
    this.merchantId = merchantId;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  public String getPrivateKey() {
    return privateKey;
  }

  public void setPrivateKey(String privateKey) {
    this.privateKey = privateKey;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BrainTreePSPValue)) return false;
    BrainTreePSPValue that = (BrainTreePSPValue) o;
    return Objects.equals(getEnvironment(), that.getEnvironment()) && Objects.equals(getMerchantId(), that.getMerchantId()) && Objects.equals(getPublicKey(), that.getPublicKey()) && Objects.equals(getPrivateKey(), that.getPrivateKey());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getEnvironment(), getMerchantId(), getPublicKey(), getPrivateKey());
  }

  @Override
  public String toString() {
    return "BrainTreePSPValue{" +
            "environment='" + environment + '\'' +
            ", merchantId='" + merchantId + '\'' +
            ", publicKey='" + publicKey + '\'' +
            ", privateKey='" + privateKey + '\'' +
            '}';
  }
}

