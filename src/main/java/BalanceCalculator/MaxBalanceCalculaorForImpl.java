package BalanceCalculator;

import BankAccount.BankAccount;
import Transactions.Transaction;

public class MaxBalanceCalculaorForImpl implements MaxBalanceCalculaor {
    @Override
    public int calculateMaxBalance(BankAccount bankAccount) {
        int currentBalance = 0;
        int maxBalance = 0;

        if (bankAccount.getTransactions() == null){
            return 0;
        }

        Transaction[] transactions = bankAccount.getTransactions();

        for (Transaction transaction : transactions) {
            if (transaction.isDeposit()){
                currentBalance += transaction.sum();
            } else if (transaction.isWithdrawAll()) {
                currentBalance -= transaction.sum();
            }
            if (currentBalance > maxBalance){
                maxBalance = currentBalance;
            }
        }
        return maxBalance;
    }
}
