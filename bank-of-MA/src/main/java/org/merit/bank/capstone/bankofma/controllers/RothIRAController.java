package org.merit.bank.capstone.bankofma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.merit.bank.capstone.bankofma.models.RothIRA;
import org.merit.bank.capstone.bankofma.repositories.RothIRARepository;
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
@RequestMapping("roth/")
public class RothIRAController {

	@Autowired
	RothIRARepository rothIRARepository;
	
	@GetMapping("all")
	public List<RothIRA> getRothIRA(){
		return rothIRARepository.findAll();
				
	}
	
	@PostMapping("add")
	@ResponseStatus(HttpStatus.CREATED)
	public RothIRA createNewRoth(@RequestBody @Valid RothIRA newRoth) {
		rothIRARepository.save(newRoth);
		return newRoth;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<RothIRA> getRothId(@PathVariable(value = "id") Long id)
	 throws ResourceNotFoundException {
	 RothIRA rothIRA = rothIRARepository.findById(id)
	   .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + id));
	 return ResponseEntity.ok().body(rothIRA);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.GONE)
	public void deleteRoth(@PathVariable Long id) {
		rothIRARepository.deleteById(id);
	}
	
	@PutMapping("/update/{id}")
	  RothIRA rothIRA (@RequestBody RothIRA newRothIRA, @PathVariable Long id) {
	    
	    return rothIRARepository.findById(id)
	      .map(rothIRA -> {
	    	  rothIRA.setBalance(newRothIRA.getBalance());
	    	  rothIRA.setInterestRate(newRothIRA.getInterestRate());
	    	  rothIRA.setTerm(newRothIRA.getTerm());
	        return rothIRARepository.save(newRothIRA);
	      })
	      .orElseGet(() -> {
	        newRothIRA.setId(id);
	        return rothIRARepository.save(newRothIRA);
	      });
	  }
	
}
