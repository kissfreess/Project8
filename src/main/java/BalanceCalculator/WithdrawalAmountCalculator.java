package BalanceCalculator;

import BankAccount.BankAccount;

public interface WithdrawalAmountCalculator {
    public int calculate(BankAccount bankAccount);
}
