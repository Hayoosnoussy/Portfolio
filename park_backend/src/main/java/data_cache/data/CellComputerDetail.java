package data_cache.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CellComputerDetail  implements Cloneable {
  @JsonProperty("cellComputer")
  private Integer cellComputer;

  @JsonProperty("hostName")
  private String hostName;

  @JsonProperty("ip4address")
  private String ip4address;

  @JsonProperty("port")
  private Integer port;

  @JsonProperty("userName")
  private String userName;

  @JsonProperty("password")
  private String password;

  @JsonProperty("lprSupported")
  private Boolean lprSupported;

  @JsonProperty("mobileIdSupported")
  private Boolean mobileIdSupported;

  @JsonProperty("deviceId")
  private Integer deviceId;

  @JsonProperty("cashierContractId")
  private Integer cashierContractId;

  @JsonProperty("cashierConsumerId")
  private Integer cashierConsumerId;

  @JsonProperty("rateCode")
  private Integer rateCode;

  @JsonProperty("operatorAddress")
  private String operatorAddress;

  @JsonProperty("financialInfo")
  private String financialInfo;

  @JsonProperty("resourceBundle")
  private String resourceBundle;

  @JsonProperty("languages")
  private String languages;

  @JsonProperty("currencyCode")
  private String currencyCode;

  @JsonProperty("offsetUTC")
  private String offsetUTC;

  @JsonProperty("mobileIdRef")
  private Integer mobileIdRef;

  @JsonProperty("sbLocationId")
  private String sbLocationId;

  @JsonProperty("facilityId")
  private String facilityId;

  @JsonProperty("cityRegion")
  private String cityRegion;

  @JsonProperty("merchantName")
  private String merchantName;

  @JsonProperty("lfkSupported")
  private Boolean lfkSupported;

  @JsonProperty("qrCode")
  private Boolean qrCode;

  @JsonProperty("voucherHandling")
  private Integer voucherHandling;

  @JsonProperty("countryCode")
  private String countryCode;

  @JsonProperty("prepaymentSupported")
  private Boolean prepaymentSupported;

  @JsonProperty("topUpSupported")
  private Boolean topUpSupported;

  @JsonProperty("latePaymentSupport")
  private Boolean latePaymentSupport;

  @JsonProperty("ticketClassification")
  private String ticketClassification;

  @JsonProperty("shiftHandlingTime")
  private String shiftHandlingTime;

  @JsonProperty("shiftId")
  private Long shiftId;

  @JsonProperty("online")
  private Boolean online;

  @JsonProperty("url")
  private String url;

  @JsonProperty("paymentUI")
  private String paymentUI;

  @JsonProperty("deviceUI")
  private String deviceUI;

  @JsonProperty("differenceCountingUI")
  private String differenceCountingUI;

  @JsonProperty("rabatteUI")
  private String rabatteUI;

  @JsonProperty("currencyDecimal")
  private Integer currencyDecimal;

  @JsonProperty("shiftNo")
  private Long shiftNo;

  @JsonProperty("operatorData")
  private Operator operatorData;

  @JsonProperty("merchantPspData")
  private MerchantPsp merchantPspData;

  public void CellComputerDetail() {
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  public Integer getCellComputer() {
    return cellComputer;
  }

  public void setCellComputer(Integer cellComputer) {
    this.cellComputer = cellComputer;
  }

  public String getHostName() {
    return hostName;
  }

  public void setHostName(String hostName) {
    this.hostName = hostName;
  }

  public String getIp4address() {
    return ip4address;
  }

  public void setIp4address(String ip4address) {
    this.ip4address = ip4address;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getLprSupported() {
    return lprSupported;
  }

  public void setLprSupported(Boolean lprSupported) {
    this.lprSupported = lprSupported;
  }

  public Boolean getMobileIdSupported() {
    return mobileIdSupported;
  }

  public void setMobileIdSupported(Boolean mobileIdSupported) {
    this.mobileIdSupported = mobileIdSupported;
  }

  public Integer getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(Integer deviceId) {
    this.deviceId = deviceId;
  }

  public Integer getCashierContractId() {
    return cashierContractId;
  }

  public void setCashierContractId(Integer cashierContractId) {
    this.cashierContractId = cashierContractId;
  }

  public Integer getCashierConsumerId() {
    return cashierConsumerId;
  }

  public void setCashierConsumerId(Integer cashierConsumerId) {
    this.cashierConsumerId = cashierConsumerId;
  }

  public Integer getRateCode() {
    return rateCode;
  }

  public void setRateCode(Integer rateCode) {
    this.rateCode = rateCode;
  }

  public String getOperatorAddress() {
    return operatorAddress;
  }

  public void setOperatorAddress(String operatorAddress) {
    this.operatorAddress = operatorAddress;
  }

  public String getFinancialInfo() {
    return financialInfo;
  }

  public void setFinancialInfo(String financialInfo) {
    this.financialInfo = financialInfo;
  }

  public String getResourceBundle() {
    return resourceBundle;
  }

  public void setResourceBundle(String resourceBundle) {
    this.resourceBundle = resourceBundle;
  }

  public String getLanguages() {
    return languages;
  }

  public void setLanguages(String languages) {
    this.languages = languages;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public String getOffsetUTC() {
    return offsetUTC;
  }

  public void setOffsetUTC(String offsetUTC) {
    this.offsetUTC = offsetUTC;
  }

  public Integer getMobileIdRef() {
    return mobileIdRef;
  }

  public void setMobileIdRef(Integer mobileIdRef) {
    this.mobileIdRef = mobileIdRef;
  }

  public String getSbLocationId() {
    return sbLocationId;
  }

  public void setSbLocationId(String sbLocationId) {
    this.sbLocationId = sbLocationId;
  }

  public String getFacilityId() {
    return facilityId;
  }

  public void setFacilityId(String facilityId) {
    this.facilityId = facilityId;
  }

  public String getCityRegion() {
    return cityRegion;
  }

  public void setCityRegion(String cityRegion) {
    this.cityRegion = cityRegion;
  }

  public String getMerchantName() {
    return merchantName;
  }

  public void setMerchantName(String merchantName) {
    this.merchantName = merchantName;
  }
 
  public Boolean getLfkSupported() {
    return lfkSupported;
  }

  public void setLfkSupported(Boolean lfkSupported) {
    this.lfkSupported = lfkSupported;
  }

  public Boolean getQrCode() {
    return qrCode;
  }

  public void setQrCode(Boolean qrCode) {
    this.qrCode = qrCode;
  }

  public Integer getVoucherHandling() {
    return voucherHandling;
  }

  public void setVoucherHandling(Integer voucherHandling) {
    this.voucherHandling = voucherHandling;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public Boolean getPrepaymentSupported() {
    return prepaymentSupported;
  }

  public void setPrepaymentSupported(Boolean prepaymentSupported) {
    this.prepaymentSupported = prepaymentSupported;
  }

  public Boolean getTopUpSupported() {
    return topUpSupported;
  }

  public void setTopUpSupported(Boolean topUpSupported) {
    this.topUpSupported = topUpSupported;
  }

  public Boolean getLatePaymentSupport() {
    return latePaymentSupport;
  }

  public void setLatePaymentSupport(Boolean latePaymentSupport) {
    this.latePaymentSupport = latePaymentSupport;
  }

  public String getTicketClassification() {
    return ticketClassification;
  }

  public void setTicketClassification(String ticketClassification) {
    this.ticketClassification = ticketClassification;
  }

  public String getShiftHandlingTime() {
    return shiftHandlingTime;
  }

  public void setShiftHandlingTime(String shiftHandlingTime) {
    this.shiftHandlingTime = shiftHandlingTime;
  }

  public Long getShiftId() {
    return shiftId;
  }

  public void setShiftId(Long shiftId) {
    this.shiftId = shiftId;
  }

  public Boolean getOnline() {
    return online;
  }

  public void setOnline(Boolean online) {
    this.online = online;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getPaymentUI() {
    return paymentUI;
  }

  public void setPaymentUI(String paymentUI) {
    this.paymentUI = paymentUI;
  }

  public String getDeviceUI() {
    return deviceUI;
  }

  public void setDeviceUI(String deviceUI) {
    this.deviceUI = deviceUI;
  }

  public String getDifferenceCountingUI() {
    return differenceCountingUI;
  }

  public void setDifferenceCountingUI(String differenceCountingUI) {
    this.differenceCountingUI = differenceCountingUI;
  }

  public String getRabatteUI() {
    return rabatteUI;
  }

  public void setRabatteUI(String rabatteUI) {
    this.rabatteUI = rabatteUI;
  }

  public Integer getCurrencyDecimal() {
    return currencyDecimal;
  }

  public void setCurrencyDecimal(Integer currencyDecimal) {
    this.currencyDecimal = currencyDecimal;
  }


  public Long getShiftNo() {
    return shiftNo;
  }

  public void setShiftNo(Long shiftNo) {
    this.shiftNo = shiftNo;
  }

  public Operator getOperatorData() {
    return operatorData;
  }

  public void setOperatorData(Operator operatorData) {
    this.operatorData = operatorData;
  }

  public MerchantPsp getMerchantPspData() {
    return merchantPspData;
  }

  public void setMerchantPspData(MerchantPsp merchantPspData) {
    this.merchantPspData = merchantPspData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CellComputerDetail)) return false;
    CellComputerDetail that = (CellComputerDetail) o;
    return Objects.equals(getCellComputer(), that.getCellComputer()) && getOperatorData().equals(that.getOperatorData());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCellComputer(), getOperatorData());
  }

  @Override
  public String toString() {
    return "CellComputer{" +
            "operator=" + (operatorData != null ? operatorData.getOperatorId() : "null") +
            ", cellComputer=" + cellComputer +
            ", hostName='" + hostName + '\'' +
            ", ip4address='" + ip4address + '\'' +
            ", port=" + port +
            ", userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            '}';
  }

}
