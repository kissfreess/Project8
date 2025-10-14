import CurrentBalance.CurrentBalanceCalculatorForImpl;
import CurrentBalance.CurrentBalanceCalculatorStreamApiImpl;
import Domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CurrentBalanceCalculatorTest {

    private CurrentBalanceCalculatorForImpl calculator;
    private CurrentBalanceCalculatorStreamApiImpl calculator1;

    @Before
    public void setUp() {
        calculator = new CurrentBalanceCalculatorForImpl();
        calculator1 = new CurrentBalanceCalculatorStreamApiImpl();


    }

    @Test
    public void shouldCalculateZeroBalanceForEmptyTransactions() {
        Transaction[] transactions = new Transaction[0];
        BankAccount bankAccount = new BankAccount("Roman", transactions);

        int balance = calculator1.calculate(bankAccount);
        assertEquals(0, balance);
    }

    @Test
    public void shouldCalculateZeroBalanceForNullTransactions() {
        BankAccount bankAccount = new BankAccount("Roman", null);

        int balance = calculator1.calculate(bankAccount);
        assertEquals(0, balance);
    }

    @Test
    public void shouldCalculateBalanceWithDepositsOnly() {
        Transaction[] transactions = {
                new TransactionFactory().createDepositTransaction(1000),
                new TransactionFactory().createDepositTransaction(500),
                new TransactionFactory().createDepositTransaction(125)
        };
        BankAccount bankAccount = new BankAccount("Roman", transactions);
        int balance = calculator1.calculate(bankAccount);
        assertEquals(1625, balance);
    }

    @Test
    public void shouldCalculateBalanceWithWithdrawsOnly() {
        Transaction[] transactions = {
                new TransactionFactory().createWithdrawAllTransaction(1000, ExpenseCategory.FOOD),
                new TransactionFactory().createWithdrawAllTransaction(500, ExpenseCategory.FOOD),
                new TransactionFactory().createWithdrawAllTransaction(200, ExpenseCategory.FOOD)
        };
        BankAccount bankAccount = new BankAccount("Roman", transactions);
        int balance = calculator1.calculate(bankAccount);
        assertEquals(-1700, balance);
    }

    @Test
    public void shouldCalculateBalanceWithMixedDepositsAndWithdraws() {
        Transaction[] transactions = {
                new TransactionFactory().createWithdrawAllTransaction(1000, ExpenseCategory.FOOD),
                new TransactionFactory().createDepositTransaction(2000),
                new TransactionFactory().createWithdrawAllTransaction(500, ExpenseCategory.FOOD),
                new TransactionFactory().createDepositTransaction(100)
        };
        BankAccount bankAccount = new BankAccount("Roman", transactions);
        int balance = calculator1.calculate(bankAccount);
        assertEquals(600, balance);
    }
}



