import Transactions.ImmutableTransaction;
import Transactions.TransactionType.TransactionType;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TransactionTest {

    @Test
    public void shouldCreateTransactionUsingOfMethod(){
        ImmutableTransaction transaction = ImmutableTransaction.of(1000, TransactionType.DEPOSIT);
        assertEquals(1000, transaction.sum());
        assertEquals(TransactionType.DEPOSIT, transaction.transactionType());

    }
    @Test
    public void shouldBeEqualForSameValues() {
        ImmutableTransaction transaction1 = ImmutableTransaction.of(500, TransactionType.WITHDRAWAL);
        ImmutableTransaction transaction2 = ImmutableTransaction.of(500, TransactionType.WITHDRAWAL);
        assertEquals(transaction1, transaction2);
    }

}