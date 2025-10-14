package Domain;

class TransactionImpl implements Transaction{
    private int sum;
    private TransactionType transactionType;
    private ExpenseCategory expenseCategory;

    public TransactionImpl(int sum) {
        this.transactionType = TransactionType.DEPOSIT;
        this.sum = sum;
        this.expenseCategory = null;
    }

    public TransactionImpl(int sum, ExpenseCategory expenseCategory) {
        this.sum = sum;
        this.transactionType = TransactionType.WITHDRAWAL;
        this.expenseCategory = expenseCategory;
    }

    @Override
    public int sum() {
        return this.sum;
    }

    @Override
    public TransactionType transactionType() {
        return this.transactionType;
    }

    @Override
    public ExpenseCategory expenseCategory() {
        return this.expenseCategory;
    }

}
