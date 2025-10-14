import DepositAmount.DepositAmountCalculatorImpl;
import Domain.BankAccount;
import Domain.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DepositAmountCalculatorImplTest  {

    @Mock
    private BankAccount bankAccount;

    @Mock
    private Transaction transaction1, transaction2, transaction3;

    @InjectMocks
    private DepositAmountCalculatorImpl calculator;

    @Test
    public void shouldReturnZero_WhenNoTransactions() {
        when(bankAccount.getTransactions()).thenReturn(new Transaction[0]);
        int zeroAmmount = calculator.calculate(bankAccount);

        assertEquals(0, zeroAmmount);
    }

    @Test
    public void shouldReturnSum_WhenOnlyDepositTransactions() {
        when(transaction1.isDeposit()).thenReturn(true);
        when(transaction2.isDeposit()).thenReturn(true);
        when(transaction1.sum()).thenReturn(100);
        when(transaction2.sum()).thenReturn(200);

        Transaction[] transactions = {transaction1, transaction2};
        when(bankAccount.getTransactions()).thenReturn(transactions);
        int depositAmount = calculator.calculate(bankAccount);

        assertEquals(300, depositAmount);
    }

    @Test
    public void shouldReturnSumOnlyDeposits_WhenMixedTransactions() {
        when(transaction1.isDeposit()).thenReturn(true);
        when(transaction2.isDeposit()).thenReturn(true);
        when(transaction3.isWithdrawAll()).thenReturn(true);
        when(transaction1.sum()).thenReturn(100);
        when(transaction2.sum()).thenReturn(200);
        when(transaction3.sum()).thenReturn(100);

        Transaction[] transactions = {transaction1, transaction2, transaction3};
        when(bankAccount.getTransactions()).thenReturn(transactions);
        int depositAmount = calculator.calculate(bankAccount);

        assertEquals(300, depositAmount);
    }

    @Test
    public void shouldReturnZero_WhenOnlyWithdrawAllTransactions() {
        when(transaction1.isWithdrawAll()).thenReturn(true);
        when(transaction2.isWithdrawAll()).thenReturn(true);
        when(transaction3.isWithdrawAll()).thenReturn(true);
        when(transaction1.sum()).thenReturn(100);
        when(transaction2.sum()).thenReturn(200);
        when(transaction3.sum()).thenReturn(100);

        Transaction[] transactions = {transaction1, transaction2, transaction3};
        when(bankAccount.getTransactions()).thenReturn(transactions);
        int zeroAmount = calculator.calculate(bankAccount);

        assertEquals(0, zeroAmount);
    }
}

