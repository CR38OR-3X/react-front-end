package org.merit.bank.capstone.bankofma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.merit.bank.capstone.bankofma.models.AccountHolder;
import org.merit.bank.capstone.bankofma.models.CDA;
import org.merit.bank.capstone.bankofma.repositories.CDARepository;
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
@RequestMapping("cda/")
public class CDAController {

	@Autowired
	CDARepository cdaRepository;

	@GetMapping("all")
	public List<CDA> getCDAccounts() {
		return cdaRepository.findAll();
	}

	@PostMapping("add")
	@ResponseStatus(HttpStatus.CREATED)
	public CDA createNewCDA(@RequestBody @Valid CDA newCDA) {
		cdaRepository.save(newCDA);
		return newCDA;
	}

	@GetMapping("{id}")
	public ResponseEntity<CDA> getCDAById(@PathVariable(value = "id") Long id)
	 throws ResourceNotFoundException {
	 CDA cda = cdaRepository.findById(id)
	   .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + id));
	 return ResponseEntity.ok().body(cda);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.GONE)
	public void deleteCDA(@PathVariable Long id) {
		cdaRepository.deleteById(id);
	}
	
	 @PutMapping("/update/{id}")
	  CDA cda(@RequestBody CDA newCDA, @PathVariable Long id) {
	    
	    return cdaRepository.findById(id)
	      .map(cda -> {
	        cda.setBalance(newCDA.getBalance());
	        cda.setInterestRate(newCDA.getInterestRate());
	        cda.setTerm(newCDA.getTerm());
	        return cdaRepository.save(newCDA);
	      })
	      .orElseGet(() -> {
	        newCDA.setId(id);
	        return cdaRepository.save(newCDA);
	      });
	  }
}
