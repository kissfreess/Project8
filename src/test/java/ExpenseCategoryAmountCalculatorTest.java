import Domain.BankAccount;
import Domain.ExpenseCategory;
import Domain.Transaction;
import Domain.TransactionFactory;
import Expensesanalyser.ExpenseCategoryAmountCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseCategoryAmountCalculatorTest {

    @Mock
    private BankAccount bankAccount;

    @InjectMocks
    private ExpenseCategoryAmountCalculator calculator;

    @Test
    public void shouldCalculateAmountsForAllExpenseCategories() {
        Transaction transaction1 = new TransactionFactory().createWithdrawAllTransaction(100, ExpenseCategory.FOOD);
        Transaction transaction2 = new TransactionFactory().createWithdrawAllTransaction(300, ExpenseCategory.ENTERTAINMENT);
        Transaction transaction3 = new TransactionFactory().createWithdrawAllTransaction(300, ExpenseCategory.COMMUNAL_PAYMENTS);
        Transaction transaction4 = new TransactionFactory().createDepositTransaction(300);
        Transaction transaction5 = new TransactionFactory().createWithdrawAllTransaction(200, ExpenseCategory.FOOD);

        Transaction[] transactions = {transaction1, transaction2, transaction3, transaction4, transaction5};

        when(bankAccount.getTransactions()).thenReturn(transactions);

        Map<ExpenseCategory, Integer> result = calculator.calculate(bankAccount);


        assertAll(
                () -> assertEquals(3, result.size()),
                () ->assertEquals(300, result.get(ExpenseCategory.FOOD)),
                () ->assertEquals(300, result.get(ExpenseCategory.ENTERTAINMENT)),
                () ->assertEquals(300, result.get(ExpenseCategory.COMMUNAL_PAYMENTS))
        );


    }
}