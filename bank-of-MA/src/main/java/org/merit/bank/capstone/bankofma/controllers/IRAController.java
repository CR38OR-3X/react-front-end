package org.merit.bank.capstone.bankofma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.merit.bank.capstone.bankofma.models.IRA;
import org.merit.bank.capstone.bankofma.repositories.IRARepository;
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
@RequestMapping("ira/")
public class IRAController {

	@Autowired
	IRARepository iraRepository;
	
	@GetMapping("all")
	public List<IRA> getIRAS(){
		return iraRepository.findAll();
				
	}
	
	@PostMapping("add")
	@ResponseStatus(HttpStatus.CREATED)
	public IRA createNewIRA(@RequestBody @Valid IRA newIRA) {
		iraRepository.save(newIRA);
		return newIRA;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<IRA> getIRABYId(@PathVariable(value = "id") Long id)
	 throws ResourceNotFoundException {
	 IRA ira = iraRepository.findById(id)
	   .orElseThrow(() -> new ResourceNotFoundException("Member not found for this id :: " + id));
	 return ResponseEntity.ok().body(ira);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.GONE)
	public void deleteChecking(@PathVariable Long id) {
		iraRepository.deleteById(id);
	}
	
	@PutMapping("/update/{id}")
	IRA ira(@RequestBody IRA newIRA, @PathVariable Long id) {
	    
	    return iraRepository.findById(id)
	      .map(ira -> {
	        ira.setBalance(newIRA.getBalance());
	        ira.setInterestRate(newIRA.getInterestRate());
	        ira.setTerm(newIRA.getTerm());
	        return iraRepository.save(newIRA);
	      })
	      .orElseGet(() -> {
	        newIRA.setId(id);
	        return iraRepository.save(newIRA);
	      });
	}
}
