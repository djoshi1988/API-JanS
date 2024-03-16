package com.bank.cedrus.controller;

 
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.cedrus.model.ClaimDetails;
import com.bank.cedrus.service.impl.ClaimDetailsService;



@RestController
@Validated
@RequestMapping("/api")
public class ClaimController {
	
    private final ClaimDetailsService claimDetailsService;
    
    @Autowired
    public ClaimController(ClaimDetailsService claimDetailsService) {
        this.claimDetailsService = claimDetailsService;
     }



    @PostMapping("/pushClaimDetails")
    public ResponseEntity<String> processClaim(@Valid @RequestBody ClaimDetails claimForm, BindingResult bindingResult) {        
 
         if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation errors: " + bindingResult.getAllErrors());
        }

        claimDetailsService.saveClaimDetails(claimForm);

        return ResponseEntity.ok("Claim processed successfully");
    }
}

