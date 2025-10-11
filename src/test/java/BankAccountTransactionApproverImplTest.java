import BalanceCalculator.CurrentBalanceCalculator;
import BalanceCalculator.CurrentBalanceCalculatorForImpl;
import BankAccount.BankAccount;
import Transactions.BankAccountTransactionApproverImpl;
import Transactions.ImmutableTransaction;
import Transactions.Transaction;
import Transactions.TransactionType.TransactionType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountTransactionApproverImplTest  {

    @Mock
    private CurrentBalanceCalculator calculator;

    @InjectMocks
    private BankAccountTransactionApproverImpl approver;

    @Test
    public void shouldApproveDepositTransaction() {

        BankAccount bankAccount = new BankAccount("Roman", null);
        ImmutableTransaction transaction = ImmutableTransaction.of(100, TransactionType.DEPOSIT);
        boolean result = approver.approve(bankAccount, transaction);

        assertTrue(result);
    }

    @Test
    public void shouldApproveWithdrawAllTransactionWhenBalanceSufficient(){

        BankAccount bankAccount = new BankAccount("Roman", new Transaction[0]);
        ImmutableTransaction transaction = ImmutableTransaction.of(500, TransactionType.WITHDRAWAL);
        when(calculator.calculate(bankAccount)).thenReturn(1000);
        boolean result = approver.approve(bankAccount, transaction);

        assertTrue(result);
    }

    @Test
    public void shouldRejectWithdrawAllTransactionWhenBalanceInSufficient(){

        BankAccount bankAccount = new BankAccount("Roman", 100, new Transaction[0]);
        ImmutableTransaction transaction = ImmutableTransaction.of(700, TransactionType.WITHDRAWAL);
        when(calculator.calculate(bankAccount)).thenReturn(500);
        boolean result = approver.approve(bankAccount, transaction);

        assertFalse(result);
    }

    @Test
    public void shouldApproveWithdrawAllTransactionWhenBalancePlusCreditLimitEqualTransaction(){

        BankAccount bankAccount = new BankAccount("Roman", 100, new Transaction[0]);
        ImmutableTransaction transaction = ImmutableTransaction.of(500, TransactionType.WITHDRAWAL);
        when(calculator.calculate(bankAccount)).thenReturn(400);
        boolean result = approver.approve(bankAccount, transaction);

        assertTrue(result);
    }


}

