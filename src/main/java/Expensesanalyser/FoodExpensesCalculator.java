package Expensesanalyser;

import Domain.BankAccount;
import Domain.ExpenseCategory;
import Domain.Transaction;

import java.util.Arrays;

public class FoodExpensesCalculator {
    public int calculateExpensesAmount(BankAccount bankAccount) {
        return Arrays.stream(bankAccount.getTransactions())
                .filter(Transaction::isWithdrawAll)
                .filter(t -> t.expenseCategory() == ExpenseCategory.FOOD)
                .mapToInt(Transaction::sum)
                .sum();
    }
}
