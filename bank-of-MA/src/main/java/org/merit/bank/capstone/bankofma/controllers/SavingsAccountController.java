package org.merit.bank.capstone.bankofma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.merit.bank.capstone.bankofma.models.AccountHolder;
import org.merit.bank.capstone.bankofma.models.CDA;
import org.merit.bank.capstone.bankofma.models.SavingsAccount;
import org.merit.bank.capstone.bankofma.repositories.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("savings/")
public class SavingsAccountController {
	
	@Autowired
	SavingsRepository savingsRepository;
	
	@GetMapping("all")
	public List<SavingsAccount> getSavingsAccounts(){
		return savingsRepository.findAll();
	}
	
	@PostMapping("add")
	public SavingsAccount createNewSavings(@RequestBody @Valid SavingsAccount newSavings) {
		savingsRepository.save(newSavings);
		return newSavings;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<SavingsAccount> getSavingsById(@PathVariable(value = "id") Long id)
	 throws ResourceNotFoundException {
	 SavingsAccount savingsAccount = savingsRepository.findById(id)
	   .orElseThrow(() -> new ResourceNotFoundException("Member not found for this id :: " + id));
	 return ResponseEntity.ok().body(savingsAccount);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.GONE)
	public void deleteSavings(@PathVariable Long id) {
		savingsRepository.deleteById(id);
	}
	
	@PutMapping("/update/{id}")
	  SavingsAccount savingsAccount(@RequestBody SavingsAccount newSavings, @PathVariable Long id) {
	    
	    return savingsRepository.findById(id)
	      .map(savingsAccount -> {
	        savingsAccount.setBalance(newSavings.getBalance());
	        savingsAccount.setInterestRate(newSavings.getInterestRate());
	        savingsAccount.setTerm(newSavings.getTerm());
	        return savingsRepository.save(newSavings);
	      })
	      .orElseGet(() -> {
	        newSavings.setId(id);
	        return savingsRepository.save(newSavings);
	      });
	  }
}
