package BalanceCalculator;

import BankAccount.BankAccount;

public interface DepositAmountCalculator {
    public int calculate(BankAccount bankAccount);
}
