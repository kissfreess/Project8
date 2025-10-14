package Domain;

public class TransactionFactory {

    public Transaction createDepositTransaction(int sum) {
        return new TransactionImpl(sum);
    }

    public Transaction createWithdrawAllTransaction(int sum, ExpenseCategory expenseCategory) {
        return new TransactionImpl(sum, expenseCategory);
    }
}
