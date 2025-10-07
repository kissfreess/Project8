package BalanceCalculator;
import BankAccount.BankAccount;
import Transactions.Transaction;

public class CurrentBalanceCalculatorForImpl implements CurrentBalanceCalculator{
    @Override
    public int calculate(BankAccount bankAccount) {
        int balance = 0;
        if (bankAccount.getTransactions() == null){
            return balance;
        }
        for (Transaction transaction : bankAccount.getTransactions()) {
            if (transaction.isDeposit()){
                balance+= transaction.sum();
            } else if (transaction.isWithdrawAll()) {
                balance-=transaction.sum();
            }
        }
        return balance;
    }

}
