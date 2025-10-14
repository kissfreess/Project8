package DepositAmount;

import Domain.Transaction;

import java.util.Arrays;

public class DepositAmountCalculatorImpl implements DepositAmountCalculator{

    @Override
    public int calculate(Domain.BankAccount bankAccount) {

        return Arrays.stream(bankAccount.getTransactions())
                .filter(Transaction::isDeposit)
                .map(Transaction::sum)
                .reduce(0, Integer::sum);
    }
}
