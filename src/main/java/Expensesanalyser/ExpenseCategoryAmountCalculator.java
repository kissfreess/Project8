package Expensesanalyser;

import Domain.BankAccount;
import Domain.ExpenseCategory;
import Domain.Transaction;
import Domain.TransactionFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ExpenseCategoryAmountCalculator {
    public Map<ExpenseCategory, Integer> calculate(BankAccount bankAccount) {

        return Arrays.stream(bankAccount.getTransactions())
                .filter(Transaction::isWithdrawAll)
                .collect(Collectors.toMap(Transaction::expenseCategory, Transaction::sum, Integer::sum));
    }
}

