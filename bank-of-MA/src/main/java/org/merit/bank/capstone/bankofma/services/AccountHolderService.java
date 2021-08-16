package org.merit.bank.capstone.bankofma.services;

import java.util.List;
import java.util.Optional;

import org.merit.bank.capstone.bankofma.models.AccountHolder;

public interface AccountHolderService {

	List<AccountHolder> findAll();
	
	void save(AccountHolder accountHolder);
	
	Optional<AccountHolder> findById(Long id);
	
	void delete(long id);
}
