import Domain.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountReportTest  {

    @Mock
    private BankAccount bankAccount;

    @InjectMocks
    private BankAccountReport bankAccountReport;

    @Test
    public void shouldReturnCorrectBankAccountReport() {
        Transaction[] transactions = {new TransactionFactory().createDepositTransaction(200),
                new TransactionFactory().createWithdrawAllTransaction(100, ExpenseCategory.FOOD),
                new TransactionFactory().createDepositTransaction(100),
                new TransactionFactory().createWithdrawAllTransaction(100, ExpenseCategory.LOANS)};
        when(bankAccount.getTransactions()).thenReturn(transactions);
        String report = bankAccountReport.bankAccountReport(bankAccount);

        assertTrue(report.contains("Domain.Transaction list:"));
        assertTrue(report.contains("DEPOSIT 200"));
        assertTrue(report.contains("WITHDRAWAL 100"));
        assertTrue(report.contains("DEPOSIT 100"));
        assertTrue(report.contains("Summary:"));
        assertTrue(report.contains("Starting balance: 0"));
        assertTrue(report.contains("Current Balance: 100"));
        assertTrue(report.contains("Deposit sum: 300"));
        assertTrue(report.contains("Withdrawal sum: 200"));
        assertTrue(report.contains("Max balance: 200"));
        assertTrue(report.contains("Min balance: 0"));

    }


}