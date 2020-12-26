package com.ksm.wstbackoffice.lesson.blockingqueue;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@Slf4j
public class Task02_CounterService_DeadLock {
    AtomicInteger

    private boolean shouldLog = false;
    private final Object longMonitor = new Object();
    private final Object shortMonitor = new Object();

    private List<Long> longCount = new ArrayList<>();
    private List<Integer> shortCount = new ArrayList<>();

    public void addLong(long value) {
        if (shouldLog) {
            log.info("Increment operation called");
        }
        if (value < 0) {
            throw new ArithmeticException("Value should not be less then 1 on increment operation");
        }

        synchronized (longMonitor) {
            List<Integer> currOtherCount = getShortCount();
            if (currOtherCount.size() > 100000) {
                log.warn("CAREFUL - shortCount is already too big " + currOtherCount.size());
            }
            longCount.add(value);
        }
    }

    public void removeLong(long value) {
        if (shouldLog) {
            log.info("Decrement operation called");
        }
        if (value < 0) {
            throw new ArithmeticException("Value should not be less then 1 on the decrement operation");
        }

        synchronized (longMonitor) {
            longCount.remove(value);
        }
    }

    public void addShort(int value) {
        if (shouldLog) {
            log.info("Increment operation called");
        }
        if (value < 0) {
            throw new ArithmeticException("Value should not be less then 1 on increment operation");
        }

        synchronized (shortMonitor) {
            List<Long> currOtherCount = getLongCount();
            if (currOtherCount.size() > 100000) {
                log.warn("CAREFUL - longCount is already too big " + currOtherCount.size());
            }
            shortCount.add(value);
        }
    }

    public void removeShort(int value) {
        if (shouldLog) {
            log.info("Decrement operation called");
        }
        if (value < 0) {
            throw new ArithmeticException("Value should not be less then 1 on the decrement operation");
        }

        synchronized (shortMonitor) {
            shortCount.remove(value);
        }
    }

    public List<Long> getLongCount() {
        synchronized (longMonitor) {
            return longCount;
        }
    }

    public List<Integer> getShortCount() {
        synchronized (shortMonitor) {
            return shortCount;
        }
    }
}
