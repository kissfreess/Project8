package Domain;

public interface Transaction {

    int sum();
    TransactionType transactionType();
    ExpenseCategory expenseCategory();

    public default boolean isDeposit(){

        return transactionType() == TransactionType.DEPOSIT;
    }

    public default boolean isWithdrawAll(){

        return transactionType() == TransactionType.WITHDRAWAL;
    }

}
