package org.merit.bank.capstone.bankofma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.merit.bank.capstone.bankofma.models.CheckingAccount;
import org.merit.bank.capstone.bankofma.repositories.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("checking/")
public class CheckingAccountController {

	@Autowired
	CheckingRepository checkingRepository;
	
	@GetMapping("all")
	public List<CheckingAccount> getCheckingAccounts(){
		return checkingRepository.findAll();
				
	}
	
	@PostMapping("add")
	@ResponseStatus(HttpStatus.CREATED)
	public CheckingAccount createNewChecking(@RequestBody @Valid CheckingAccount newChecking) {
		checkingRepository.save(newChecking);
		return newChecking;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CheckingAccount> getCheckingyId(@PathVariable(value = "id") Long id)
	 throws ResourceNotFoundException {
	 CheckingAccount checkingAccount = checkingRepository.findById(id)
	   .orElseThrow(() -> new ResourceNotFoundException("Member not found for this id :: " + id));
	 return ResponseEntity.ok().body(checkingAccount);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.GONE)
	public void deleteChecking(@PathVariable Long id) {
		checkingRepository.deleteById(id);
	}
	
	@PutMapping("/update/{id}")
	  CheckingAccount checkingccount(@RequestBody CheckingAccount newChecking, @PathVariable Long id) {
	    
	    return checkingRepository.findById(id)
	      .map(checkingAccount -> {
	        checkingAccount.setBalance(newChecking.getBalance());
	        checkingAccount.setInterestRate(newChecking.getInterestRate());
	        checkingAccount.setTerm(newChecking.getTerm());
	        return checkingRepository.save(newChecking);
	      })
	      .orElseGet(() -> {
	        newChecking.setId(id);
	        return checkingRepository.save(newChecking);
	      });
	  }
}
