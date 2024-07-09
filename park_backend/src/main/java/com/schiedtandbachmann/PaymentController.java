package com.schiedtandbachmann;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import DBEntity.ConsultTransaction;
import Services.LocalParseService;
import Services.PaymentService;
import dataUtils.transactions;

@RestController
public class PaymentController {

	@Autowired
	MessageSource messageSource;
    @Autowired
    PaymentService paymentService;

    @Autowired
    LocalParseService parse;
    @PostMapping("/paymentPGS")
    public ResponseEntity<Object> paymentPGS(@RequestBody Map<String, Object> requests) {
        Map<String, Object> data = new HashMap<>();
        HttpStatus httpStatus = HttpStatus.OK;

        String error = verifyCredentials(requests);

        if (error != null) {
            data.put("code", 400);
            data.put("errorCode", "20001");
            data.put("errorMessage", error);
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            List<transactions> transactions = (List<transactions>) requests.get("transactions");
            String locale = (String) requests.get("locale");

            data = paymentService.onlinePayment(transactions, locale);

            if (data.containsKey("errorCode")) {
            	int errorCode=Integer.parseInt((String)(data.get("errorCode")));
                data.put("errorMessage", (String) data.get("message"));
                return ResponseEntity.status(errorCode).body(data);
            }
        }

        return new ResponseEntity<>(data, httpStatus);
    }

//    @GetMapping("/error")
    private String verifyCredentials(@RequestBody Map<String, Object> requestData) {
    	String locale = (String) requestData.get("locale");
        Locale userLocale = parse.parseLocale(locale);
        if (!requestData.containsKey("transactions")
            || requestData.get("transactions") == null
            || !(requestData.get("transactions") instanceof List)
            || ((List<?>) requestData.get("transactions")).isEmpty()) {
            // Invalid List of transactions
        	System.out.println(userLocale);
        	String Response = messageSource.getMessage("invalidTransactions", null, userLocale);

            return Response;
            }

        List<transactions> transactions = (List<transactions>) requestData.get("transactions");
        if (transactions != null
            && !transactions.isEmpty()
            && paymentService.verifyIfTransactionsHaveSameOperator(transactions)) {
        	String Response = messageSource.getMessage("notSameOperator", null, userLocale);
            return Response;
        }

        return null;
    }
    
   
}
