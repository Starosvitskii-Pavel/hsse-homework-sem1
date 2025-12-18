package com.mipt.pavelstarosvitskiy.multithreading;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {


    @Test
    public void shouldSendMoney() {
        BankAccount from = new BankAccount(100);
        BankAccount to = new BankAccount(10);
        Bank mtsBank = new Bank();

        mtsBank.sendToAccount(from, to, 100);

        assertEquals(0, from.getBalance());
        assertEquals(110, to.getBalance());
    }

    @Test
    public void shouldNotSendMoney_WhenNotEnoughMoney() {
        BankAccount from = new BankAccount(99);
        BankAccount to = new BankAccount(10);
        Bank mtsBank = new Bank();


        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> mtsBank.sendToAccount(from, to, 100));

        assertEquals("Not enough money on balance.", illegalArgumentException.getMessage());
    }

    @Test
    public void shouldNotSendMoney_WhenArgumentIsNull() {
        BankAccount from = new BankAccount(100);
        BankAccount to = new BankAccount(100);
        Bank mtsBank = new Bank();

        IllegalArgumentException firstIsNull = assertThrows(IllegalArgumentException.class,
                () -> mtsBank.sendToAccount(null, to, 100));
        assertEquals("Account cannot be null.", firstIsNull.getMessage());

        IllegalArgumentException secondIsNull = assertThrows(IllegalArgumentException.class,
                () -> mtsBank.sendToAccount(from, null, 100));
        assertEquals("Account cannot be null.", secondIsNull.getMessage());

        IllegalArgumentException bothAreNull = assertThrows(IllegalArgumentException.class,
                () -> mtsBank.sendToAccount(null, null, 100));
        assertEquals("Account cannot be null.", bothAreNull.getMessage());
    }

    @Test
    public void shouldBeDeadlocked() throws InterruptedException {
        BankAccount from = new BankAccount(1000000);
        BankAccount to = new BankAccount(1000000);
        Bank mtsBank = new Bank();

        assertTrue(isDeadlocked(mtsBank::sendToAccountDeadlock, from, to));
    }

    @Test
    public void shouldNotBeDeadlocked() throws InterruptedException {
        BankAccount from = new BankAccount(1000000);
        BankAccount to = new BankAccount(1000000);
        Bank mtsBank = new Bank();

        assertFalse(isDeadlocked(mtsBank::sendToAccount, from, to));
    }

    @FunctionalInterface
    interface BankFunction {
        void run(BankAccount first, BankAccount second, int sum);
    }

    private boolean isDeadlocked(BankFunction bankFunction, BankAccount first, BankAccount second) throws InterruptedException {
        AtomicInteger successfulOperationCount = new AtomicInteger(0);
        ArrayList<Thread> threads = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            Thread thread;

            if (i % 2 == 0) {
                thread = new Thread(
                        () -> {
                            for (int j = 0; j < 1000; j++) {
                                bankFunction.run(first, second, 10);
                                successfulOperationCount.addAndGet(1);
                            }
                        }
                );
            }

            else {
                thread = new Thread(
                        () -> {
                            for (int j = 0; j < 1000; j++) {
                                bankFunction.run(second, first, 10);
                                successfulOperationCount.addAndGet(1);
                            }
                        }
                );
            }

            threads.add(thread);
            thread.start();
        }

        Thread.sleep(1000);

        for (Thread thread : threads) thread.interrupt();

        return (successfulOperationCount.intValue() != 10000);
    }
}