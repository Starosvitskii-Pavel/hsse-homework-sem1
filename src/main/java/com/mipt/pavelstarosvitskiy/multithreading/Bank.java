package com.mipt.pavelstarosvitskiy.multithreading;

public class Bank {
    public void sendToAccountDeadlock(BankAccount from, BankAccount to, int amount) throws IllegalArgumentException {
        if (from == null || to == null) throw new IllegalArgumentException("Account cannot be null");
        if (from.getBalance() < amount) throw new IllegalArgumentException("Not enough money on balance.");

        synchronized (from) {
            synchronized (to) {
                from.withdraw(amount);
                to.deposit(amount);
            }
        }
    }

    public void sendToAccount(BankAccount from, BankAccount to, int amount) throws IllegalArgumentException {
        if (from == null || to == null) throw new IllegalArgumentException("Account cannot be null");
        if (from.getBalance() < amount) throw new IllegalArgumentException("Not enough money on balance.");

        BankAccount first = (from.getId().compareTo(to.getId()) < 0) ? to : from;
        BankAccount second = (first == to) ? from : to;

        synchronized (first) {
            synchronized (second) {
                from.withdraw(amount);
                to.deposit(amount);
            }
        }
    }
}
