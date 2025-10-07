import BalanceCalculator.CurrentBalanceCalculator;
import BalanceCalculator.CurrentBalanceCalculatorForImpl;
import BankAccount.BankAccount;
import Transactions.BankAccountTransactionApproverImpl;
import Transactions.ImmutableTransaction;
import Transactions.Transaction;
import Transactions.TransactionType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BankAccountTransactionApproverImplTest  {

    private BankAccountTransactionApproverImpl approver;
    private CurrentBalanceCalculator calculator;

    @Before
    public void setUp() {
        calculator = new CurrentBalanceCalculatorForImpl();
        approver = new BankAccountTransactionApproverImpl(calculator);
    }

    @Test
    public void shouldApproveDepositTransaction() {
        BankAccount bankAccount = new BankAccount("Roman", null);
        ImmutableTransaction transaction = ImmutableTransaction.of(100, TransactionType.DEPOSIT);
        boolean result = approver.approve(bankAccount, transaction);

        assertTrue(result);
    }

    @Test
    public void shouldApproveWithdrawAllTransactionWhenBalanceSufficient(){
        Transaction[] transactions = {
                ImmutableTransaction.of(1000, TransactionType.DEPOSIT),
                ImmutableTransaction.of(2000, TransactionType.DEPOSIT),
                ImmutableTransaction.of(1500, TransactionType.WITHDRAWAL)
        };
        BankAccount bankAccount = new BankAccount("Roman", transactions);
        ImmutableTransaction transaction = ImmutableTransaction.of(500, TransactionType.WITHDRAWAL);
        boolean result = approver.approve(bankAccount, transaction);

        assertTrue(result);
    }

    @Test
    public void shouldRejectWithdrawAllTransactionWhenBalanceInSufficient(){
        Transaction[] transactions = {
                ImmutableTransaction.of(1000, TransactionType.DEPOSIT),
                ImmutableTransaction.of(2000, TransactionType.DEPOSIT),
                ImmutableTransaction.of(3000, TransactionType.WITHDRAWAL)
        };
        BankAccount bankAccount = new BankAccount("Roman", transactions);
        ImmutableTransaction transaction = ImmutableTransaction.of(500, TransactionType.WITHDRAWAL);
        boolean result = approver.approve(bankAccount, transaction);

        assertFalse(result);
    }


}