import CurrentBalance.CurrentBalanceCalculator;
import Domain.*;
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
        Transaction transaction = new TransactionFactory().createDepositTransaction(100);
        boolean result = approver.approve(bankAccount, transaction);

        assertTrue(result);
    }

    @Test
    public void shouldApproveWithdrawAllTransactionWhenBalanceSufficient(){

        BankAccount bankAccount = new BankAccount("Roman", new Transaction[0]);
        Transaction transaction = new TransactionFactory().createWithdrawAllTransaction(500, ExpenseCategory.FOOD);
        when(calculator.calculate(bankAccount)).thenReturn(1000);
        boolean result = approver.approve(bankAccount, transaction);

        assertTrue(result);
    }

    @Test
    public void shouldRejectWithdrawAllTransactionWhenBalanceInSufficient(){

        BankAccount bankAccount = new BankAccount("Roman", 100, new Transaction[0]);
        Transaction transaction = new TransactionFactory().createWithdrawAllTransaction(700, ExpenseCategory.FOOD);
        when(calculator.calculate(bankAccount)).thenReturn(500);
        boolean result = approver.approve(bankAccount, transaction);

        assertFalse(result);
    }

    @Test
    public void shouldApproveWithdrawAllTransactionWhenBalancePlusCreditLimitEqualTransaction(){

        BankAccount bankAccount = new BankAccount("Roman", 100, new Transaction[0]);
        Transaction transaction = new TransactionFactory().createWithdrawAllTransaction(500, ExpenseCategory.FOOD);
        when(calculator.calculate(bankAccount)).thenReturn(400);
        boolean result = approver.approve(bankAccount, transaction);

        assertTrue(result);
    }


}

