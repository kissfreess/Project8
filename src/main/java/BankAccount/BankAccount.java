package BankAccount;

import Transactions.Transaction;
// Lombok  юзать не получается т.к. потом не видны геттеры в тестах
public class BankAccount {

    private String ownerFullName;
    private Transaction[] transactions;
    private int creditLimit;

    public BankAccount(String ownerFullName, Transaction[] transactions) {
        this.ownerFullName = ownerFullName;
        this.transactions = transactions;
    }

    public BankAccount(String ownerFullName, int creditLimit, Transaction[] transactions) {
        this.ownerFullName = ownerFullName;
        this.creditLimit = creditLimit;
        this.transactions = transactions;
    }

    public boolean hasNoTransactions() {
        return transactions == null || transactions.length == 0;
    }

    public Transaction[] getTransactions() {
        return transactions;
    }

    public int getCreditLimit() {
        return creditLimit;
    }
}
