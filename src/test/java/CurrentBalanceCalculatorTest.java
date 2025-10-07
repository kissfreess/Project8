import BalanceCalculator.CurrentBalanceCalculatorForImpl;
import BalanceCalculator.CurrentBalanceCalculatorStreamApiImpl;
import BankAccount.BankAccount;
import Transactions.ImmutableTransaction;
import Transactions.Transaction;
import Transactions.TransactionType;
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
                ImmutableTransaction.of(1000, TransactionType.DEPOSIT),
                ImmutableTransaction.of(500, TransactionType.DEPOSIT),
                ImmutableTransaction.of(125, TransactionType.DEPOSIT)
        };
        BankAccount bankAccount = new BankAccount("Roman", transactions);
        int balance = calculator1.calculate(bankAccount);
        assertEquals(1625, balance);
    }

    @Test
    public void shouldCalculateBalanceWithWithdrawsOnly() {
        Transaction[] transactions = {
                ImmutableTransaction.of(1000, TransactionType.WITHDRAWAL),
                ImmutableTransaction.of(200, TransactionType.WITHDRAWAL),
                ImmutableTransaction.of(500, TransactionType.WITHDRAWAL)
        };
        BankAccount bankAccount = new BankAccount("Roman", transactions);
        int balance = calculator1.calculate(bankAccount);
        assertEquals(-1700, balance);
    }

    @Test
    public void shouldCalculateBalanceWithMixedDepositsAndWithdraws() {
        Transaction[] transactions = {
                ImmutableTransaction.of(1000, TransactionType.WITHDRAWAL),
                ImmutableTransaction.of(2000, TransactionType.DEPOSIT),
                ImmutableTransaction.of(500, TransactionType.WITHDRAWAL),
                ImmutableTransaction.of(100, TransactionType.DEPOSIT)
        };
        BankAccount bankAccount = new BankAccount("Roman", transactions);
        int balance = calculator1.calculate(bankAccount);
        assertEquals(600, balance);
    }
}



