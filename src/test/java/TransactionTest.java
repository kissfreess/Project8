import Domain.ExpenseCategory;
import Domain.Transaction;
import Domain.TransactionFactory;
import Domain.TransactionType;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TransactionTest {

    @Test
    public void shouldCreateTransactionUsingOfMethod(){
        Transaction transaction = new TransactionFactory().createDepositTransaction(1000);
        assertEquals(1000, transaction.sum());
        assertEquals(TransactionType.DEPOSIT, transaction.transactionType());

    }
    @Test
    public void shouldBeEqualForSameValues() {
        Transaction transaction1 = new TransactionFactory().createWithdrawAllTransaction(500, ExpenseCategory.FOOD);
        Transaction transaction2 = new TransactionFactory().createWithdrawAllTransaction(500, ExpenseCategory.FOOD);

        assertEquals(transaction1, transaction2);
    }

}