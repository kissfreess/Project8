package BalanceCalculator;

import BankAccount.BankAccount;
import Transactions.Transaction;

import java.lang.reflect.Array;
import java.util.Arrays;

public class WithdrawalAmountCalculatorImpl implements WithdrawalAmountCalculator{
    @Override
    public int calculate(BankAccount bankAccount) {

        return Arrays.stream(bankAccount.getTransactions())
                .filter(Transaction::isWithdrawAll)
                .map(Transaction::sum)
                .reduce(0, Integer::sum);

    }
}
