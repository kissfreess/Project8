package Transactions;

import BankAccount.BankAccount;

public interface BankAccountTransactionApprover {
    public boolean approve(BankAccount account, Transaction transaction);
    }

