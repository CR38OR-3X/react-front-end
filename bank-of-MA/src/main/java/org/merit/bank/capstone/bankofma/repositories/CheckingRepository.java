package org.merit.bank.capstone.bankofma.repositories;

import org.merit.bank.capstone.bankofma.models.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends JpaRepository<CheckingAccount, Long>{

}
