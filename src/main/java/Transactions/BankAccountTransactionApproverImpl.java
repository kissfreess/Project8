package Transactions;

import BalanceCalculator.CurrentBalanceCalculator;
import BankAccount.BankAccount;

public class BankAccountTransactionApproverImpl implements BankAccountTransactionApprover{

    private CurrentBalanceCalculator calculator;

    public BankAccountTransactionApproverImpl(CurrentBalanceCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public boolean approve(BankAccount account, Transaction transaction) {
        int currentBalance = calculator.calculate(account);

        if (transaction.isDeposit()){
            return true;
        }

        if (transaction.isWithdrawAll()){
            return currentBalance >= transaction.sum();
        }
        return false;
    }
}
