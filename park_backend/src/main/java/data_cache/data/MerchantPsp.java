package data_cache.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Configuration of PSP for merchant
 */
public class MerchantPsp {
  @JsonProperty("merchantPspId")
  private Integer merchantPspId;
  @JsonProperty("name")
  private String name;
  @JsonProperty("touLink")
  private String touLink;
  @JsonProperty("brainTree")
  private BrainTreePSPValue brainTree;
  @JsonProperty("pgs")
  private PGSPSPValue pgs;

  public Integer getMerchantPspId() {
    return merchantPspId;
  }

  public void setMerchantPspId(Integer merchantPspId) {
    this.merchantPspId = merchantPspId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTouLink() {
    return touLink;
  }

  public void setTouLink(String touLink) {
    this.touLink = touLink;
  }

  public BrainTreePSPValue getBrainTree() {
    return brainTree;
  }

  public void setBrainTree(BrainTreePSPValue brainTree) {
    this.brainTree = brainTree;
  }

  public PGSPSPValue getPgs() {
    return pgs;
  }

  public void setPgs(PGSPSPValue pgs) {
    this.pgs = pgs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof MerchantPsp)) return false;
    MerchantPsp that = (MerchantPsp) o;
    return Objects.equals(getMerchantPspId(), that.getMerchantPspId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getTouLink(), that.getTouLink()) && Objects.equals(getBrainTree(), that.getBrainTree()) && Objects.equals(getPgs(), that.getPgs());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getMerchantPspId(), getName(), getTouLink(), getBrainTree(), getPgs());
  }

  @Override
  public String toString() {
    return "MerchantPsp{" +
            "merchantPspId=" + merchantPspId +
            ", name='" + name + '\'' +
            ", touLink='" + touLink + '\'' +
            ", brainTree=" + brainTree +
            ", pgs=" + pgs +
            '}';
  }
}

