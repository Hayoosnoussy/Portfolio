package Services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schiedtandbachmann.CommonInfoHelper;

import DBEntity.ConsultTransaction;
import DBEntity.PaymentTransactions;
import antlr.Utils;
import data_cache.data.CellComputerDetail;

@Service
public class EmailDataService {
	
	@Autowired
	CommonInfoHelper commonInfoHelper;
	
	@Autowired
	UtillsService useful;
	
	public Map<String,Object> getEmailData(PaymentTransactions paymentTransactions) {
        try {
            String lpn = null;
            Map<String , Object> transactionsToSend = new HashMap<>();
            double ttAmount = 0;
            double totalDiscount = 0;
            double ttVatAmount = 0;
            double decimalAmount = 2;
            String currency = "";
            String mediumType = "";
            String mediumValue = "";
            String cellComputerLocale = "en-US";
         // for percentage
            double ttVatAmountPercentage = 0;
            double ttAmountPercentage = 0;
            String operatorEmail = "";
            String operatorLogo = "";
            String operatorPhone = "";
            String operatorAddress = "";
            String operatorName = "";
            String financialInfo = "";
            
            for (ConsultTransaction transaction : paymentTransactions.getTransactions()) {
                Map<String , Object> infoCarPark = commonInfoHelper.fetchCarParkByLocationId(
                        transaction.getOperatorId() + transaction.getCellComputerId());
                
                if (infoCarPark != null) {
                	/**/Object currencyObject = infoCarPark.get("currency");
                	currency = (currencyObject != null) ? currencyObject.toString() : "";                    
                	operatorEmail =  infoCarPark.get("email").toString();
                    operatorPhone = infoCarPark.get("phone").toString();
                    operatorAddress = infoCarPark.get("operatorDataAddress").toString();
                    operatorName = infoCarPark.get("operator_name").toString();
                    financialInfo = infoCarPark.get("financialInfo").toString();
                    /**/Object logo = infoCarPark.get("operatorLogo"); 
                    operatorLogo = (logo != null) ? logo.toString() : "";  
                    
                    
                }
                
                if ("success".equals(transaction.getStatus())) {

                	 if (Integer.parseInt(transaction.getVatValue()) > 0) {
                         ttAmountPercentage += transaction.getAmount();
                         ttVatAmountPercentage += Integer.parseInt(transaction.getVatValue());
                     }


                     ttAmount += transaction.getAmount();
                     ttVatAmount += Integer.parseInt(transaction.getVatValue());
                     lpn = transaction.getMediumValue();
                     decimalAmount = (int) Math.pow(10, (int) infoCarPark.get("currencyDecimal"));
                     cellComputerLocale = ((String) infoCarPark.get("language")).replace('-', '_');

                     mediumValue = transaction.getMediumValue();
                     mediumType = transaction.getMediumType();
                     totalDiscount += (Integer.parseInt(transaction.getOriginalAmount()) > transaction.getAmount()) ?
                             ((Integer.parseInt(transaction.getOriginalAmount()) - transaction.getAmount()) / decimalAmount) : 0;
                     
                     transactionsToSend.putAll(getTransactionData(transaction));
	
                }
            }

            double vatPercentage = ((ttAmountPercentage - ttVatAmountPercentage) > 0) ?
                    Math.round((ttVatAmountPercentage / (ttAmountPercentage - ttVatAmountPercentage)) * decimalAmount) : 0;
	
            Map<String, Object> emailDataMap = new HashMap<>();
            emailDataMap.put("email", paymentTransactions.getEmail());
            emailDataMap.put("payId", paymentTransactions.getPayId());
            emailDataMap.put("transactionsToSend", transactionsToSend);
            emailDataMap.put("lpn", lpn);
            emailDataMap.put("locale", paymentTransactions.getLocale());
            emailDataMap.put("localeFormat", paymentTransactions.getLocaleFormat());
            emailDataMap.put("mediumType", mediumType);
            emailDataMap.put("mediumValue", mediumValue);
            emailDataMap.put("ttAmount", ttAmount);
            emailDataMap.put("totalDiscount", totalDiscount);
            emailDataMap.put("vatPercentage", vatPercentage);
            emailDataMap.put("ttVatAmount", ttVatAmount);
            emailDataMap.put("createdAt", paymentTransactions.getCreatedAt());
            emailDataMap.put("decimalAmount", decimalAmount);
            emailDataMap.put("cellComputerLocale", cellComputerLocale);
            emailDataMap.put("currency", currency);
            emailDataMap.put("operatorAddress", operatorAddress);
            emailDataMap.put("operatorEmail", operatorEmail);
            emailDataMap.put("operatorPhone",  operatorPhone);
            emailDataMap.put("operatorName", operatorName);
            emailDataMap.put("financialInfo", financialInfo);
            emailDataMap.put("operatorLogo", operatorLogo);
            emailDataMap.put("maskedMediaId", paymentTransactions.getMaskedMediaId());
            emailDataMap.put("mediaType", paymentTransactions.getMediaType());

            return emailDataMap;
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }

        return null; 
    }
	
//en cours
    public Map<String,Object> getTransactionData(ConsultTransaction transaction) {
        try {
            Map<String, Object> infoCarPark = commonInfoHelper.fetchCarParkByLocationId(
                    transaction.getOperatorId() + transaction.getCellComputerId());
            int decimalAmount = (int) Math.pow(10, (int) infoCarPark.get("currencyDecimal"));
            String tariffTimeStart = transaction.getTariffTimeStart(); 
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            LocalDateTime startTariffTime = LocalDateTime.parse(tariffTimeStart, formatter);
            String endtariffTime = transaction.getTariffTimeEnd();
            LocalDateTime endTimetariff = LocalDateTime.parse(endtariffTime, formatter);

            double vatValue = Integer.parseInt(transaction.getVatValue());
            String vat = "VAT " + ((vatValue > 0) ? vatValue : 0) + "%";
            double amount = transaction.getAmount();
            double vatPercentage = 0;
            if (vatValue > 0 && (amount - vatValue) > 0) {
                vatPercentage = Math.round((vatValue / (amount - vatValue)) * decimalAmount);
            }
            double originalAmount = Integer.parseInt(transaction.getOriginalAmount());
            double discount = (originalAmount > amount) ? ((originalAmount - amount) / decimalAmount) : 0;
          
            Map <String,Object> TransactionData = new HashMap<>();
            TransactionData.put("paymentTransactionsDate", transaction.getPaymentDate());
            TransactionData.put("cellComputerId", transaction.getCellComputerId());
            TransactionData.put("namePark",(String) infoCarPark.get("name"));
            TransactionData.put("duration", useful.formatDurationHumanReadable(transaction.getDuration(),transaction.getPaymentTransactions().getLocale()));
            TransactionData.put("articleShortText", transaction.getArticleShortText());
            TransactionData.put("articleRef", transaction.getArticleRef());
            TransactionData.put("epan", transaction.getEpan());
            TransactionData.put("endtariffTime", endTimetariff);
            TransactionData.put("vat", vat);
            TransactionData.put("valuevat", Integer.parseInt(transaction.getVatValue())> 0 ? Integer.parseInt(transaction.getVatValue()) : 0);
            TransactionData.put("vatPourcentage", vatPercentage);
            TransactionData.put("amountVat", vatValue > 0 ? vatValue / decimalAmount : 0 );
            TransactionData.put("amount", amount / decimalAmount);
            TransactionData.put("email",(String) infoCarPark.get("email"));
            TransactionData.put("operatorAddress",(String) infoCarPark.get("address"));
            TransactionData.put("parkingPriceInfo",(String) infoCarPark.get("vatNumber"));
            TransactionData.put("financialInfo",(String) infoCarPark.get("financialInfo"));
            TransactionData.put("lpn",transaction.getMediumValue());
            TransactionData.put("type",transaction.getType());
            TransactionData.put("discount",discount);
            TransactionData.put("receiptNo",transaction.getPaymentTransactions().getReceiptNumber());

           
            return TransactionData;
        } catch (Exception e) {
   System.out.println(e.getMessage());       
   }
        return null; 
    }


}
	

