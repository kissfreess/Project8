package Expensesanalyser;

import Domain.BankAccount;
import Domain.ExpenseCategory;
import Domain.Transaction;

import java.util.Arrays;

public class ExpensesCalculator {
    public int calculateExpensesAmount(BankAccount bankAccount, ExpenseCategory category) {
        return Arrays.stream(bankAccount.getTransactions())
                .filter(Transaction::isWithdrawAll)
                .filter(t -> t.expenseCategory() == category)
                .mapToInt(Transaction::sum)
                .sum();
    }
}
