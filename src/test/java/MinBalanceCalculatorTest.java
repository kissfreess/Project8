import BalanceCalculator.MinBalanceCalculatorForImpl;
import BankAccount.BankAccount;
import Transactions.ImmutableTransaction;
import Transactions.Transaction;
import Transactions.TransactionType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinBalanceCalculatorTest {
    private MinBalanceCalculatorForImpl calculatorMinBalance;

    @Before
    public void setUp(){
        calculatorMinBalance = new MinBalanceCalculatorForImpl();
    }

    @Test
    public void shouldCalculateZeroBalanceForEmptyTransactions() {
        Transaction[] transactions = new Transaction[0];
        BankAccount bankAccount = new BankAccount("Roman", transactions);

        int balance = calculatorMinBalance.calculateMinBalance(bankAccount);
        assertEquals(0, balance);
    }

    @Test
    public void shouldCalculateZeroBalanceForNullTransactions() {
        BankAccount bankAccount = new BankAccount("Roman", null);

        int balance = calculatorMinBalance.calculateMinBalance(bankAccount);
        assertEquals(0, balance);
    }

    @Test
    public void shouldCalculateZeroBalanceWithDepositsOnly() {
        Transaction[] transactions = {
                ImmutableTransaction.of(1000, TransactionType.DEPOSIT),
                ImmutableTransaction.of(500, TransactionType.DEPOSIT),
                ImmutableTransaction.of(125, TransactionType.DEPOSIT)
        };
        BankAccount bankAccount = new BankAccount("Roman", transactions);
        int minBalance = calculatorMinBalance.calculateMinBalance(bankAccount);
        assertEquals(0, minBalance);
    }

    @Test
    public void shouldCalculateMinBalanceWithWithdrawsOnly() {
        Transaction[] transactions = {
                ImmutableTransaction.of(1000, TransactionType.WITHDRAWAL),
                ImmutableTransaction.of(500, TransactionType.WITHDRAWAL),
                ImmutableTransaction.of(125, TransactionType.WITHDRAWAL)
        };
        BankAccount bankAccount = new BankAccount("Roman", transactions);
        int minBalance = calculatorMinBalance.calculateMinBalance(bankAccount);
        assertEquals(-1625, minBalance);
    }

    @Test
    public void shouldCalculateMinBalanceWithMixedDepositsAndWithdraws() {
        Transaction[] transactions = {
                ImmutableTransaction.of(1000, TransactionType.DEPOSIT),
                ImmutableTransaction.of(500, TransactionType.WITHDRAWAL),
                ImmutableTransaction.of(2000, TransactionType.DEPOSIT),
                ImmutableTransaction.of(500, TransactionType.WITHDRAWAL),
                ImmutableTransaction.of(500, TransactionType.WITHDRAWAL),
                ImmutableTransaction.of(2000, TransactionType.WITHDRAWAL),
        };
        BankAccount bankAccount = new BankAccount("Roman", transactions);
        int minBalance = calculatorMinBalance.calculateMinBalance(bankAccount);
        assertEquals(-500, minBalance);
    }
}
