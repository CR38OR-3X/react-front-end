package org.merit.bank.capstone.bankofma.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "members")
public class AccountHolder implements Comparable<AccountHolder> {

	@Override
	public String toString() {
		return "AccountHolder [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", ssn=" + ssn
				+ ", savingsAccount=" + savingsAccount + ", checkingAccount=" + checkingAccount + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "ssn")
	private String ssn;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "member_savings_acct")
	private SavingsAccount savingsAccount;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "member_checking_acct")
	private CheckingAccount checkingAccount;
	
	/*@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "member_cda")
	private CDAccount cdAccount;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "member_dba")
	private DBAccount dbAccount;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "member_ira")
	private IRAccount irAccount;*/
	
	
	public AccountHolder() {}

	public AccountHolder(String firstName, String lastName, String ssn) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
	}
		
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getSSN() {
		return ssn;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setSSN(String ssn) {
		this.ssn = ssn;
	}

	public Long getId() {
		return id;
	}

	public SavingsAccount getSavingsAccount() {
		return savingsAccount;
	}

	public void setSavingsAccount(SavingsAccount savingsAccount) {
		this.savingsAccount = savingsAccount;
	}

	
	public CheckingAccount getCheckingAccount() {
		return checkingAccount;
	}

	public void setCheckingAccount(CheckingAccount checkingAccount) {
		this.checkingAccount = checkingAccount;
	}

	/*public CDAccount getCdAccount() {
		return cdAccount;
	}

	public void setCdAccount(CDAccount cdAccount) {
		this.cdAccount = cdAccount;
	}

	public DBAccount getDbAccount() {
		return dbAccount;
	}

	public void setDbAccount(DBAccount dbAccount) {
		this.dbAccount = dbAccount;
	}

	public IRAccount getIrAccount() {
		return irAccount;
	}

	public void setIrAccount(IRAccount irAccount) {
		this.irAccount = irAccount;
	}*/

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int compareTo(AccountHolder o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
