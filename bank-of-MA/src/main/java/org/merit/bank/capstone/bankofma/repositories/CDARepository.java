package org.merit.bank.capstone.bankofma.repositories;

import org.merit.bank.capstone.bankofma.models.CDA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CDARepository extends JpaRepository<CDA, Long> {

}
