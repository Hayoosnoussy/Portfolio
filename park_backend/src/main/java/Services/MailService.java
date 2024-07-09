package Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
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

@Service
public class MailService {
	
	
	@Autowired
    MessageSource messageSource;
	
	@Autowired
    TemplateEngine templateEngine;
	
	@Autowired
    LocalParseService parse;
	
    @Autowired
    JavaMailSender mailSender;
    
    
//Hafelii 
    public boolean sendEmail(@RequestBody Map<String, Object> data) {
        SimpleMailMessage message = new SimpleMailMessage();
        try {
            mailSender.send(message);
              return true;
            }catch(MailException exception){
            	return false;
            }        
    }
    
    public boolean sendEmail2(@RequestBody Map<String, Object> data, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        String locale = (String) data.get("locale");
        Locale userLocale = parse.parseLocale(locale);
        System.out.println("ssssssssssssss");
        System.out.println(locale);
        String title = messageSource.getMessage("Payment_receipt", null, userLocale);
        message.setSubject(title);
        message.setText(text);
        message.setTo((String) data.get("email"));
        
        try {
        mailSender.send(message);
        return true;
        }catch(MailException exception){
        	
        	return false;
        }
    }
    
   
    public boolean sendEmails(SimpleMailMessage message ) {
        try {
        mailSender.send(message);
        return true;
        }catch(MailException exception){	
        	return false;
        }
    }
    
    
//    
//    public Map<String, Object> sendEmail(MimeMessage message) {
//        Map<String, Object> result = new HashMap<>();
//        try {
//        	mailSender.send(message);
//            result.put("status", "yes");
//        } catch (MailException exception) {
//            result.put("status", "non");
//            result.put("errorCode", -999); 
//            result.put("message", "successOKPaymentNotSend.");
//            result.put("description", exception.getMessage());
//        }
//        return result;
//    }
//
   
   /// Mail when payment succesful
   
     
//    ///////////////////////////////
//    
//    
//    
//   
//    @PutMapping("/sendMails") 
//    public Map<String, Object> sendPaymentReceiptEmailss(Map<String, Object> data) throws MessagingException {
//        try {
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
//            String title = messageSource.getMessage("payment_receipt", null, new Locale((String) data.get("locale")));
//            helper.setSubject(title);
//            helper.setFrom(adminMail, "Entervo Smart Web Pay");
//            helper.setTo((String) data.get("email"));
//           
//
//            ByteArrayOutputStream pdfOutputStream = generatePDFVide(data);
//
//            String receiptDate = ((ZonedDateTime) data.get("receiptDate")).format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm"));
//String fileName = "Entervo Smart Web Pay Receipt - " + receiptDate + ".pdf";
//
// helper.addAttachment(fileName, new ByteArrayResource(pdfOutputStream.toByteArray()), "application/pdf");
//
//            System.out.println("Successful pre send");
//            return sendEmail(message);
//        } catch (Exception e) {
//            Map<String, Object> errorResponse = new HashMap<>();
//            errorResponse.put("errorCode", -999);
//            errorResponse.put("message", "successOKPaymentNotSend");
//            errorResponse.put("description", e.getMessage());
//
//            return errorResponse;
//        }
//    }
//    
//    
//    private ByteArrayOutputStream generatePDFVide(Map<String, Object> data) throws Exception {
//        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
//
//        // Création d'un document PDF
//        Document document = new Document();
//        PdfWriter.getInstance(document, pdfOutputStream);
//        document.open();
//
//        // Ajout de contenu au document
//        Paragraph paragraph = new Paragraph("Contenu du PDF basé sur les données reçues:");
//        document.add(paragraph);
//
//        for (Map.Entry<String, Object> entry : data.entrySet()) {
//            String key = entry.getKey();
//            Object value = entry.getValue();
//            document.add(new Paragraph(key + ": " + value));
//        }
//
//        document.close();
//
//        return pdfOutputStream;
//   }
//  
}
 
