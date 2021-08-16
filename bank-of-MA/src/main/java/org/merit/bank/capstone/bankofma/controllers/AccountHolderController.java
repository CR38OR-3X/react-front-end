package org.merit.bank.capstone.bankofma.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.merit.bank.capstone.bankofma.models.AccountHolder;
import org.merit.bank.capstone.bankofma.repositories.AcctRepository;
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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("member/")
public class AccountHolderController {

	@Autowired
	AcctRepository acctRepository;

	@GetMapping("all")
	public List<AccountHolder> getAccountHolders() {
		return acctRepository.findAll();
	}

	@PostMapping("add")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolder createNewMember(@RequestBody @Valid AccountHolder newMember) {
		acctRepository.save(newMember);
		return newMember;
	}

	@GetMapping("{id}")
	public ResponseEntity<AccountHolder> getAHById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		AccountHolder accountHolder = acctRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Member not found for this id :: " + id));
		return ResponseEntity.ok().body(accountHolder);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.GONE)
	public void deleteMember(@PathVariable Long id) {
		acctRepository.deleteById(id);
	}

	 @PutMapping("/update/{id}")
	  AccountHolder accountHolder(@RequestBody AccountHolder newMember, @PathVariable Long id) {
	    
	    return acctRepository.findById(id)
	      .map(accountHolder -> {
	        accountHolder.setFirstName(newMember.getFirstName());
	        accountHolder.setLastName(newMember.getLastName());
	        accountHolder.setSSN(newMember.getSSN());
	        accountHolder.setSavingsAccount(newMember.getSavingsAccount());	       
	        return acctRepository.save(accountHolder);
	      })
	      .orElseGet(() -> {
	        newMember.setId(id);
	        return acctRepository.save(newMember);
	      });
	  }

}
