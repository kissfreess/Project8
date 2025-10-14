package Domain;

public interface BankAccountTransactionApprover {
    public boolean approve(BankAccount account, Transaction transaction);
    }

