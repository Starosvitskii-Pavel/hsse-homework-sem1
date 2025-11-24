package com.mipt.pavelstarosvitskiy.multithreading;

import java.util.UUID;

public class BankAccount {
    private int balance;
    UUID id;

    public BankAccount(int balance) {
        this.balance = balance;
        this.id = UUID.randomUUID();
    }

    public int getBalance() {
        return balance;
    }

    public UUID getId() {
        return id;
    }

    public void deposit(int sum) {
        balance += sum;
    }

    public void withdraw(int sum) {
        balance -= sum;
    }
}
