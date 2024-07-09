package com.schiedtandbachmann;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class IdentityRest {
	 @Autowired
	    private String applicationIdentifier;

	    @GetMapping("/identifier")
	    public String getIdentifier() {
	        return applicationIdentifier;
	    }
	}