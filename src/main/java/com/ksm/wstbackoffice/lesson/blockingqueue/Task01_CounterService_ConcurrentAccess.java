package com.ksm.wstbackoffice.lesson.blockingqueue;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@Slf4j
public class Task01_CounterService_ConcurrentAccess {
    private boolean shouldLog = false;
    private int count = 0;
//    private AtomicInteger count = new AtomicInteger(0);

    public void increment(int value) {
        if (shouldLog) {
            log.info("Increment operation called");
        }
        if (value < 0) {
            throw new ArithmeticException("Value should not be less then 1 on increment operation");
        }

//        count.incrementAndGet();

        synchronized (this) {
            count = count + value;
        }
    }

    public void decrement(int value) {
        if (shouldLog) {
            log.info("Decrement operation called");
        }
        if (value < 0) {
            throw new ArithmeticException("Value should not be less then 1 on the decrement operation");
        }

//        count.decrementAndGet();

        synchronized (this) {
            count = count - value;
        }
    }
}
