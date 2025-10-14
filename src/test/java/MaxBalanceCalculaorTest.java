import Domain.*;
import MaxBalance.MaxBalanceCalculaorForImpl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaxBalanceCalculaorTest {

    private MaxBalanceCalculaorForImpl calculatorMaxBalance;

    @Before
    public void setUp(){
        calculatorMaxBalance = new MaxBalanceCalculaorForImpl();
    }

    @Test
    public void shouldCalculateZeroBalanceForEmptyTransactions() {
        Transaction[] transactions = new Transaction[0];
        BankAccount bankAccount = new BankAccount("Roman", transactions);

        int balance = calculatorMaxBalance.calculateMaxBalance(bankAccount);
        assertEquals(0, balance);
    }

    @Test
    public void shouldCalculateZeroBalanceForNullTransactions() {
        BankAccount bankAccount = new BankAccount("Roman", null);

        int balance = calculatorMaxBalance.calculateMaxBalance(bankAccount);
        assertEquals(0, balance);
    }

    @Test
    public void shouldCalculateMaxBalanceWithDepositsOnly() {
        Transaction[] transactions = {
                new TransactionFactory().createDepositTransaction(1000),
                new TransactionFactory().createDepositTransaction(500),
                new TransactionFactory().createDepositTransaction(125)
        };
        BankAccount bankAccount = new BankAccount("Roman", transactions);
        int MaxBalance = calculatorMaxBalance.calculateMaxBalance(bankAccount);
        assertEquals(1625, MaxBalance);
    }

    @Test
    public void shouldCalculateZeroBalanceWithWithdrawsOnly() {
        Transaction[] transactions = {
                new TransactionFactory().createWithdrawAllTransaction(1000, ExpenseCategory.FOOD),
                new TransactionFactory().createWithdrawAllTransaction(500, ExpenseCategory.FOOD),
                new TransactionFactory().createWithdrawAllTransaction(125, ExpenseCategory.FOOD)
        };
        BankAccount bankAccount = new BankAccount("Roman", transactions);
        int MaxBalance = calculatorMaxBalance.calculateMaxBalance(bankAccount);
        assertEquals(0, MaxBalance);
    }

    @Test
    public void shouldCalculateMaxBalanceWithMixedDepositsAndWithdraws() {
        Transaction[] transactions = {
                new TransactionFactory().createDepositTransaction(1000),
                new TransactionFactory().createWithdrawAllTransaction(500, ExpenseCategory.FOOD),
                new TransactionFactory().createDepositTransaction(2000),
                new TransactionFactory().createWithdrawAllTransaction(500, ExpenseCategory.FOOD),
                new TransactionFactory().createDepositTransaction(500)
        };
        BankAccount bankAccount = new BankAccount("Roman", transactions);
        int MaxBalance = calculatorMaxBalance.calculateMaxBalance(bankAccount);
        assertEquals(2500, MaxBalance);
    }




}