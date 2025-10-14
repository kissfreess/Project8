import Domain.BankAccount;
import Domain.ExpenseCategory;
import Domain.Transaction;
import Domain.TransactionFactory;
import Expensesanalyser.ExpensesCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExpensesCalculatorTest {
    @Mock
    private BankAccount bankAccount;

    @InjectMocks
    private ExpensesCalculator calculator;

    @Test
    public void shouldCalculateFoodExpensesForMixedTransactions(){
        Transaction transaction1 = new TransactionFactory().createDepositTransaction(500);
        Transaction transaction2 = new TransactionFactory().createWithdrawAllTransaction(100, ExpenseCategory.FOOD);
        Transaction transaction3 = new TransactionFactory().createWithdrawAllTransaction(50, ExpenseCategory.COMMUNAL_PAYMENTS);
        Transaction transaction4 = new TransactionFactory().createWithdrawAllTransaction(50, ExpenseCategory.FOOD);
        Transaction transaction5 = new TransactionFactory().createWithdrawAllTransaction(100, ExpenseCategory.LOANS);
        Transaction[] transactions = {transaction1, transaction2, transaction3, transaction4, transaction5};

        when(bankAccount.getTransactions()).thenReturn(transactions);

        int result = calculator.calculateExpensesAmount(bankAccount, ExpenseCategory.FOOD);

        assertEquals(150, result);
    }

    @Test
    public void shouldCalculateEntertainmentExpensesForMixedTransactions(){
        Transaction transaction1 = new TransactionFactory().createDepositTransaction(500);
        Transaction transaction2 = new TransactionFactory().createWithdrawAllTransaction(100, ExpenseCategory.ENTERTAINMENT);
        Transaction transaction3 = new TransactionFactory().createWithdrawAllTransaction(50, ExpenseCategory.COMMUNAL_PAYMENTS);
        Transaction transaction4 = new TransactionFactory().createWithdrawAllTransaction(50, ExpenseCategory.ENTERTAINMENT);
        Transaction transaction5 = new TransactionFactory().createWithdrawAllTransaction(100, ExpenseCategory.LOANS);
        Transaction[] transactions = {transaction1, transaction2, transaction3, transaction4, transaction5};

        when(bankAccount.getTransactions()).thenReturn(transactions);

        int result = calculator.calculateExpensesAmount(bankAccount, ExpenseCategory.ENTERTAINMENT);

        assertEquals(150, result);
    }

    @Test
    public void shouldReturnZeroWhenNoWithdrawAllTransactionsForFood(){
        Transaction transaction1 = new TransactionFactory().createDepositTransaction(500);
        Transaction transaction2 = new TransactionFactory().createWithdrawAllTransaction(100, ExpenseCategory.COMMUNAL_PAYMENTS);
        Transaction transaction3 = new TransactionFactory().createWithdrawAllTransaction(50, ExpenseCategory.COMMUNAL_PAYMENTS);
        Transaction transaction4 = new TransactionFactory().createWithdrawAllTransaction(50, ExpenseCategory.COMMUNAL_PAYMENTS);
        Transaction transaction5 = new TransactionFactory().createWithdrawAllTransaction(100, ExpenseCategory.LOANS);
        Transaction[] transactions = {transaction1, transaction2, transaction3, transaction4, transaction5};

        when(bankAccount.getTransactions()).thenReturn(transactions);

        int result = calculator.calculateExpensesAmount(bankAccount, ExpenseCategory.FOOD);

        assertEquals(0, result);
    }

}