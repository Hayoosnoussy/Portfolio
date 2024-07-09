package com.schiedtandbachmann;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Services.ConvertBarcodeToEpanService;
import dataUtils.swpUserInformation;
@RestController
@RequestMapping("/api/v1/information")
public class TransactionController {
	@Autowired
	ConvertBarcodeToEpanService convertBarcodeToEpanService;	
	@Autowired
	ZRConnectorHelper connectorHelper;
    @PutMapping
    @MessageMapping("/LatePayAPI")
    @SendTo("/topic/information")
    public ResponseEntity<?> validateUserInformation(@RequestBody @Valid swpUserInformation userInformation) {   	
    	//RECAPTCHA 
          if (!userInformation.isValidd(userInformation)) {
      	   System.out.println("LOGGssasdadadaERRR");
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
//           //for test if yetaada lhne wale 
            System.out.println("LOGGERRR");
             List<String> presentParkings = new ArrayList<>();
             List<String> latePaymentParkings = new ArrayList<>();
if (userInformation.getMediumType().equalsIgnoreCase("barcode") && userInformation.getPresent() == true) {	
	Map<String, Object> resultEpan = convertBarcodeToEpanService.epanGeneratedByBarcode(
			userInformation.getMediumValue(),
			userInformation.getParking(),
			userInformation.getE()
        );
        if (resultEpan.containsKey("epan") && !resultEpan.get("epan").toString().isEmpty()) {
        	System.out.println(resultEpan);
            presentParkings.add(userInformation.getParking());
        } else {
            return ResponseEntity.badRequest()
                .body(Map.of(
                    "code", 400,
                    "errorCode", "20001",
                    "errorMessage", resultEpan));
        }
    }
	if (userInformation.getBarcode() != null ) {
	Map<String, Object> resultEpan = convertBarcodeToEpanService.epanGeneratedByBarcode(
			userInformation.getBarcode(),
			userInformation.getParking(),
			userInformation.getE()
        );
	System.out.println("LOGGERRR22222");
    if (resultEpan.containsKey("epan") && !resultEpan.get("epan").toString().isEmpty()) {
    	System.out.println(resultEpan);
        presentParkings.add(userInformation.getParking());
    } else {
        return ResponseEntity.badRequest()
            .body(Map.of(
                "code", 400,
                "errorCode", "20001",
                "errorMessage", resultEpan));
    }
}
    boolean isTransactionsSent = connectorHelper.isTransactionsSend(userInformation, presentParkings, latePaymentParkings);
    if (!isTransactionsSent) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
}
    }