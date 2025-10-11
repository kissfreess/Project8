package BalanceCalculator;

import BankAccount.BankAccount;
import Transactions.Transaction;

import java.util.Arrays;

public class DepositAmountCalculatorImpl implements DepositAmountCalculator{

    @Override
    public int calculate(BankAccount bankAccount) {

        return Arrays.stream(bankAccount.getTransactions())
                .filter(Transaction::isDeposit)
                .map(Transaction::sum)
                .reduce(0, Integer::sum);
    }
}
