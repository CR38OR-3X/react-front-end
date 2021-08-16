package org.merit.bank.capstone.bankofma;

import java.util.Optional;

import org.merit.bank.capstone.bankofma.models.AccountHolder;
import org.merit.bank.capstone.bankofma.repositories.AcctRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class BankOfMaApplication implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(BankOfMaApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(BankOfMaApplication.class, args);
	}

	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

	};

	@Bean
	public CommandLineRunner demo(AcctRepository acctRepository) {
		return (args) -> {
			acctRepository.save(new AccountHolder("Rahman", "Jones", "rajo@gmail.com"));
			acctRepository.save(new AccountHolder("Gumball", "Waterson", "gumball@gmail.com"));
			acctRepository.save(new AccountHolder("Darwin", "Waterson", "darwin@gmail.com"));

			// fetch all accountHolders
			log.info("AccountHolders found with findAll():");
			log.info("-------------------------------");
			for (AccountHolder accountHolder : acctRepository.findAll()) {
				log.info(accountHolder.toString());
			}
			log.info("");

			// fetch an individual accountHolder by ID
			/*AccountHolder accountHolder = acctRepository.findById(1L);
			log.info("accountHolder found with findById(1L):");
			log.info("--------------------------------");
			log.info(accountHolder.toString());
			log.info("");*/

			// fetch accountHolders by last name
			log.info("accountHolder found with findByLastName('Jones'):");
			log.info("--------------------------------------------");
			acctRepository.findByLastName("Jones").forEach(jones -> {
				log.info(jones.toString());
			});
			for (AccountHolder Jones : acctRepository.findByLastName("Jones")) {
				log.info(Jones.toString());
			}
			log.info("");
		};
	}

}
