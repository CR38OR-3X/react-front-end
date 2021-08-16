package org.merit.bank.capstone.bankofma.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.merit.bank.capstone.bankofma.models.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsRepository extends JpaRepository<SavingsAccount, Long>{
	//List<SavingsAccount> savings = new ArrayList<SavingsAccount>();
	
	//List<SavingsAccount> findAll();
	
	//Optional<SavingsAccount> findById(Long id);
}
