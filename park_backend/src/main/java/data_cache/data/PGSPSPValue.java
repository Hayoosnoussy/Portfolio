package data_cache.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Configuration of SB-PGS PSP for merchant
 */
public class PGSPSPValue {
  @JsonProperty("tenant")
  private String tenant;
  @JsonProperty("requestor")
  private String requestor;

  public String getTenant() {
    return tenant;
  }

  public void setTenant(String tenant) {
    this.tenant = tenant;
  }

  public String getRequestor() {
    return requestor;
  }

  public void setRequestor(String requestor) {
    this.requestor = requestor;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PGSPSPValue)) return false;
    PGSPSPValue that = (PGSPSPValue) o;
    return Objects.equals(getTenant(), that.getTenant()) && Objects.equals(getRequestor(), that.getRequestor());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTenant(), getRequestor());
  }

  @Override
  public String toString() {
    return "PGSPSPValue{" +
            "tenant='" + tenant + '\'' +
            ", requestor='" + requestor + '\'' +
            '}';
  }
}

