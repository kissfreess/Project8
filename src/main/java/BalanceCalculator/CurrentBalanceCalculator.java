package BalanceCalculator;
import BankAccount.BankAccount;

public interface CurrentBalanceCalculator {

    int calculate(BankAccount bankAccount);
}
