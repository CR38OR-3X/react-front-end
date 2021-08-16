package org.merit.bank.capstone.bankofma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.merit.bank.capstone.bankofma.models.RollOverIRA;
import org.merit.bank.capstone.bankofma.repositories.RollOverRepository;
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
@RequestMapping("rollover/")
public class RollOverIRAController {

	@Autowired
	RollOverRepository rollOverRepository;
	
	@GetMapping("all")
	public List<RollOverIRA> getROIRAS(){
		return rollOverRepository.findAll();
				
	}
	
	@PostMapping("add")
	@ResponseStatus(HttpStatus.CREATED)
	public RollOverIRA createNewROIRA(@RequestBody @Valid RollOverIRA newROIRA) {
		rollOverRepository.save(newROIRA);
		return newROIRA;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<RollOverIRA> getROIRABYId(@PathVariable(value = "id") Long id)
	 throws ResourceNotFoundException {
	 RollOverIRA rollOverIRA = rollOverRepository.findById(id)
	   .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + id));
	 return ResponseEntity.ok().body(rollOverIRA);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.GONE)
	public void deleteROIRA(@PathVariable Long id) {
		rollOverRepository.deleteById(id);
	}
	
	@PutMapping("/update/{id}")
	RollOverIRA rollOverIRA(@RequestBody RollOverIRA newROIRA, @PathVariable Long id) {
	    
	    return rollOverRepository.findById(id)
	      .map(rollOverIRA -> {
	        rollOverIRA.setBalance(newROIRA.getBalance());
	        rollOverIRA.setInterestRate(newROIRA.getInterestRate());
	        rollOverIRA.setTerm(newROIRA.getTerm());
	        return rollOverRepository.save(newROIRA);
	      })
	      .orElseGet(() -> {
	        newROIRA.setId(id);
	        return rollOverRepository.save(newROIRA);
	      });
	}
}
