import Domain.*;
import MinBalance.MinBalanceCalculatorForImpl;

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
                new TransactionFactory().createDepositTransaction(1000),
                new TransactionFactory().createDepositTransaction(500),
                new TransactionFactory().createDepositTransaction(125)
        };
        BankAccount bankAccount = new BankAccount("Roman", transactions);
        int minBalance = calculatorMinBalance.calculateMinBalance(bankAccount);
        assertEquals(0, minBalance);
    }

    @Test
    public void shouldCalculateMinBalanceWithWithdrawsOnly() {
        Transaction[] transactions = {
                new TransactionFactory().createWithdrawAllTransaction(1000, ExpenseCategory.FOOD),
                new TransactionFactory().createWithdrawAllTransaction(500, ExpenseCategory.FOOD),
                new TransactionFactory().createWithdrawAllTransaction(125, ExpenseCategory.FOOD)
        };
        BankAccount bankAccount = new BankAccount("Roman", transactions);
        int minBalance = calculatorMinBalance.calculateMinBalance(bankAccount);
        assertEquals(-1625, minBalance);
    }

    @Test
    public void shouldCalculateMinBalanceWithMixedDepositsAndWithdraws() {
        Transaction[] transactions = {
                new TransactionFactory().createDepositTransaction(1000),
                new TransactionFactory().createWithdrawAllTransaction(500, ExpenseCategory.FOOD),
                new TransactionFactory().createDepositTransaction(2000),
                new TransactionFactory().createWithdrawAllTransaction(500, ExpenseCategory.FOOD),
                new TransactionFactory().createDepositTransaction(500),
                new TransactionFactory().createWithdrawAllTransaction(500, ExpenseCategory.FOOD),
        };
        BankAccount bankAccount = new BankAccount("Roman", transactions);
        int minBalance = calculatorMinBalance.calculateMinBalance(bankAccount);
        assertEquals(-500, minBalance);
    }
}
