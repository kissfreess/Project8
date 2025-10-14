package WithdrawallAmount;

import Domain.Transaction;

import java.util.Arrays;

public class WithdrawalAmountCalculatorImpl implements WithdrawalAmountCalculator{
    @Override
    public int calculate(Domain.BankAccount bankAccount) {

        return Arrays.stream(bankAccount.getTransactions())
                .filter(Transaction::isWithdrawAll)
                .map(Transaction::sum)
                .reduce(0, Integer::sum);

    }
}
