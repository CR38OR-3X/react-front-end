package org.merit.bank.capstone.bankofma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.merit.bank.capstone.bankofma.models.DBA;
import org.merit.bank.capstone.bankofma.repositories.DBARepository;
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
@RequestMapping("dba/")
public class DBAController {

	@Autowired
	DBARepository dbaRepository;

	@GetMapping("all")
	public List<DBA> getDBAccounts() {
		return dbaRepository.findAll();
	}

	@PostMapping("add")
	@ResponseStatus(HttpStatus.CREATED)
	public DBA createNewDBA(@RequestBody @Valid DBA newDBA) {
		dbaRepository.save(newDBA);
		return newDBA;
	}

	@GetMapping("{id}")
	public ResponseEntity<DBA> getDBAById(@PathVariable(value = "id") Long id)
	 throws ResourceNotFoundException {
	 DBA dba = dbaRepository.findById(id)
	   .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + id));
	 return ResponseEntity.ok().body(dba);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.GONE)
	public void deleteCDA(@PathVariable Long id) {
		dbaRepository.deleteById(id);
	}
	
	 @PutMapping("/update/{id}")
	 DBA dba(@RequestBody DBA newDBA, @PathVariable Long id) {
	    
	    return dbaRepository.findById(id)
	      .map(dba -> {
	        dba.setBalance(newDBA.getBalance());
	        dba.setInterestRate(newDBA.getInterestRate());
	        dba.setTerm(newDBA.getTerm());
	        return dbaRepository.save(newDBA);
	      })
	      .orElseGet(() -> {
	        newDBA.setId(id);
	        return dbaRepository.save(newDBA);
	      });
	  }
}
