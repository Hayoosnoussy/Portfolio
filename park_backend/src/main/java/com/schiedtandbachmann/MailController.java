package com.schiedtandbachmann;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Services.LocalParseService;
import Services.MailService;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.io.ByteArrayResource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@RestController
public class MailController {
	
	@Autowired
    MessageSource messageSource;
    @Autowired
    MailService emailService;
    @Autowired 
    LocalParseService parse;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${link-front}")
    private String linkfront;
	@Value("${admin.mail}")
    private String adminMail;
    @Autowired
    JavaMailSender mailSender;
    
   
    @GetMapping("/locale")
    public Locale getUserLocale() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return RequestContextUtils.getLocale(request);
    }

// njareb f traduction o baraa     
    @GetMapping("/send-email")
    public Object sendEmail(@RequestBody Map<String, Object> data ,String  text) {
      try {
    	if (!emailService.sendEmail2(data,"Hello, this is a test email!")) {

            return "non";	
    	}
       else {
    	   return "yes";
       }
    }catch (Exception ex) {
    	String locale = (String) data.get("locale");
        Locale userLocale = parse.parseLocale(locale);
        System.out.println("ssssssssssssss");
        System.out.println(locale);
        String message = messageSource.getMessage("successOKPaymentNotSend", null, userLocale);
        
    	 Map<String, Object> resultMap = new HashMap<>();
         resultMap.put("errorCode", "-999");
         resultMap.put("message", message);
         resultMap.put("description", ex.getMessage());

         return resultMap;
    }
 }
    
    @GetMapping("/send-emailss")
    public Object sendEmailss(SimpleMailMessage message) {
      try {
    	if (!emailService.sendEmails(message)) {

            return "non";	
    	}
       else {
    	   return "yes";
       }
    }catch (Exception ex) {
    	 Map<String, Object> resultMap = new HashMap<>();
         resultMap.put("errorCode", "-999");
         resultMap.put("message", message);
         resultMap.put("description", ex.getMessage());

         return resultMap;
    }
 }
    ///////////////////////////////// Hedhi for yes  or no 
    @GetMapping("/mailing")
      public Object sendEmail2(@RequestBody Map<String, Object> data) {
          try {
        	if (!emailService.sendEmail(data)) {

                return "non";	
        	}
           else {
        	   return "yes";
           }
        }catch (Exception ex) {
        	String locale = (String) data.get("locale");
            Locale userLocale = parse.parseLocale(locale);
            System.out.println("ssssssssssssss");
            System.out.println(locale);
            String message = messageSource.getMessage("successOKPaymentNotSend", null, userLocale);
            
        	 Map<String, Object> resultMap = new HashMap<>();
             resultMap.put("errorCode", "-999");
             resultMap.put("message", message);
             resultMap.put("description", ex.getMessage());

             return resultMap;
        }
}
    ////////////////////////////////////////////////////////////////HEEEERREE 
//    public Map<String, Object> sendPaymentReceiptEmail(Map<String, Object> data) throws MessagingException {
//        try {
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
//            String locale = (String) data.get("locale");
//            Locale userLocale = parse.parseLocale(locale);
//            System.out.println("ssssssssssssss");
//            System.out.println(locale);
//            String title = messageSource.getMessage("Payment_receipt", null, userLocale);
//            message.setSubject(title);
//            helper.setFrom(username, "Entervo Smart Web Pay");
//            helper.setTo((String) data.get("email"));
//            BigDecimal ttAmount = (BigDecimal) data.get("ttAmount");
//            BigDecimal ttVatAmount = (BigDecimal) data.get("ttVatAmount");
//            BigDecimal decimalAmount = (BigDecimal) data.get("decimalAmount");
//            BigDecimal netAmount = ttAmount.subtract(ttVatAmount).divide(decimalAmount);
//            BigDecimal ttamountData = ttAmount.divide(decimalAmount);
//            BigDecimal ttVatAmountData = ttVatAmount.divide(decimalAmount);
//            String mediaType = (data.get("mediaType") != null && !((String) data.get("mediaType")).isEmpty()) ?
//                    (String) data.get("mediaType") :
//                    "";
//            List<String> transactions = (List<String>) data.get("transactionsToSend");
//            Context context = new Context(Locale.forLanguageTag(locale));
//            Map<String,Object> dataFinal = new HashMap<>();
//            dataFinal.put("message", "Merci");
//            dataFinal.put("transaction", transactions);
//           dataFinal.put("payId", (String) data.get("payId"));
//            dataFinal.put("locale", (String) data.get("locale"));
//          dataFinal.put("localeFormat", (String) data.get("localeFormat"));
//            dataFinal.put("linkFront", linkfront);
//            dataFinal.put("licencePlate", (String) data.get("lpn"));
//            dataFinal.put("mediumType", (String) data.get("mediumType"));
//            dataFinal.put("mediumValue", (String) data.get("mediumValue"));
//            dataFinal.put("netAmount", netAmount);
//            dataFinal.put("vatPercentage", (String) data.get("vatPercentage"));
//            dataFinal.put("ttamount", ttamountData);
//            dataFinal.put("totalDiscount", (String) data.get("totalDiscount"));
//            dataFinal.put("ttVatamount", ttVatAmountData);
//            dataFinal.put("receiptDate", (String) data.get("receiptDate"));
//            dataFinal.put("cellComputerLocale", (String) data.get("cellComputerLocale"));
//            dataFinal.put("currency", (String) data.get("currency"));
//            dataFinal.put("operatorAddress", (String) data.get("operatorAddress"));
//            dataFinal.put("operatorEmail", (String) data.get("operatorEmail"));
//            dataFinal.put("operatorPhone", (String) data.get("operatorPhone"));
//            dataFinal.put("operatorName", (String) data.get("operatorName"));
//            dataFinal.put("maskedMediaId", (String) data.get("maskedMediaId"));
//            dataFinal.put("mediaType", mediaType);
//            dataFinal.put("financialInfo", (String) data.get("financialInfo"));
//            dataFinal.put("operatorLogo", (String) data.get("operatorLogo"));
//
//            context.setVariables(dataFinal);
//            
//            String receiptDate = ((ZonedDateTime) data.get("receiptDate")).format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm"));
//            String fileName = "Entervo Smart Web Pay Receipt - " + receiptDate + ".pdf";
//            
//            String htmlContent = templateEngine.process("mailTemplate/body", context);
//            helper.setText(htmlContent, true);
//
//            ByteArrayOutputStream pdfOutputStream = generatePDF(data);
//            helper.addAttachment("payment_receipt.pdf", new ByteArrayResource(pdfOutputStream.toByteArray()), "application/pdf");
//
//            System.out.println("succesful pre send");
//            return sendEmail2(message);
//	} catch (Exception e) {
//		   Map<String, Object> errorResponse = new HashMap<>();
//		    errorResponse.put("errorCode", -999);
//		    errorResponse.put("message", "successOKPaymentNotSend");
//		    errorResponse.put("description", e.getMessage());
//		    
//		    return errorResponse;
//		}
//    }
//
//    private ByteArrayOutputStream generatePDF(Map<String, Object> data) throws Exception {
//        String locale = (String) data.get("locale");
//        Context context = new Context(Locale.forLanguageTag(locale));
//        context.setVariables(data);
//        
//   String htmlContent = templateEngine.process("mailTemplate/invoice", context);
//
//        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
//        ITextRenderer renderer = new ITextRenderer();
//        renderer.setDocumentFromString(htmlContent);
//        renderer.layout();
//        renderer.createPDF(pdfOutputStream);
//
//        return pdfOutputStream;
//    }

    
    
    
    
    
    
    
    
    }