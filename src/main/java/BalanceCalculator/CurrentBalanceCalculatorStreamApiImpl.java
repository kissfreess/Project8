package BalanceCalculator;

import BankAccount.BankAccount;
import Transactions.TransactionType.TransactionType;

import java.util.Arrays;

public class CurrentBalanceCalculatorStreamApiImpl implements CurrentBalanceCalculator{

    @Override
    public int calculate(BankAccount bankAccount) {

        if (bankAccount.hasNoTransactions()){

            return 0;
        }

        int balance = Arrays.stream(bankAccount.getTransactions())
                .mapToInt(t -> t.transactionType() == TransactionType.DEPOSIT ? t.sum() : -t.sum())
                .sum();

        return balance;
    }

}
