package org.merit.bank.capstone.bankofma.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "checking")
public class CheckingAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "date_opened")
	private Date openedOn;

	@Column(name = "balance")
	private double balance;

	@Column(name = "interest_rate")
	private double interestRate;

	@Column(name = "term")
	private double term;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "member_id", referencedColumnName = "id")
	private AccountHolder accountHolder;

	protected CheckingAccount() {
	}

	public CheckingAccount(double balance, double interestRate, int term) {
		this.balance = balance;
		this.interestRate = interestRate;
		this.term = term;
	}

	public AccountHolder getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}

	public long getId() {
		return id;
	}

	public Date getOpenedOn() {
		return openedOn;
	}

	public double getBalance() {
		return balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public double getTerm() {
		return term;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setOpenedOn(Date openedOn) {
		this.openedOn = openedOn;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public void setTerm(double term) {
		this.term = term;
	}

}
