package org.merit.bank.capstone.bankofma.repositories;

import java.util.List;
import java.util.Optional;

import org.merit.bank.capstone.bankofma.models.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcctRepository extends JpaRepository<AccountHolder, Long> {

	Iterable<AccountHolder> findByLastName(String string);

	Optional<AccountHolder> findById(Long id);


}
