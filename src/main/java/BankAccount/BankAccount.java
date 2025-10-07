package BankAccount;

import Transactions.Transaction;

public class BankAccount {

    private String ownerFullName;
    private Transaction[] transactions;

    public BankAccount(String ownerFullName, Transaction[] transactions) {
        this.ownerFullName = ownerFullName;
        this.transactions = transactions;
    }

    public String getOwnerFullName() {
        return ownerFullName;
    }

    public Transaction[] getTransactions() {
        return transactions;
    }
}
