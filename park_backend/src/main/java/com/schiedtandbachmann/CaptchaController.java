package com.schiedtandbachmann;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import dataUtils.CaptchaResponseDto;
import dataUtils.CaptchaValidationResponseDto;


@RestController


public class CaptchaController {
    
    private static final Logger LOGGER =  Logger.getLogger(CaptchaController.class.getName());

    @Value("6Lfnq2sbAAAAAIdDFjuJlU7MEvzI4FpUqX1Otyod")
    private String recaptchaSecret;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/validate-captcha")
    public ResponseEntity<Map<String, Object>> validateCaptcha(@RequestBody CaptchaResponseDto captchaResponseDto) {

        // Validate the reCAPTCHA response
        CaptchaValidationResponseDto responseDto = validateRecaptcha(captchaResponseDto.getResponse());
        LOGGER.info("reCAPTCHA validation response - success: " + responseDto.isSuccess() + ", error codes: " + responseDto.getErrorCodes());

        // Build the response object
        Map<String, Object> responseObject = new HashMap<>();
        if (responseDto.isSuccess()) {
            responseObject.put("success", true);
        } else {
            responseObject.put("success", false);
            responseObject.put("errors", responseDto.getErrorCodes());
        }

        // Return the response
        return ResponseEntity.ok(responseObject);
    }

    private CaptchaValidationResponseDto validateRecaptcha(String captchaResponse) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("secret", recaptchaSecret);
        map.add("response", captchaResponse);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        String url = "https://www.google.com/recaptcha/api/siteverify";

        return restTemplate.postForObject(url, request, CaptchaValidationResponseDto.class);
    }
}
