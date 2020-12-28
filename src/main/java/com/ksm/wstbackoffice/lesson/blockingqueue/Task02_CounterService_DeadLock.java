package com.ksm.wstbackoffice.lesson.blockingqueue;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
@Slf4j
public class Task02_CounterService_DeadLock {
    private boolean shouldLog = false;
//    private final Object longMonitor = new Object();
//    private final Object shortMonitor = new Object();
    private final ReentrantLock longLock = new ReentrantLock();
    private final ReentrantLock shortLock = new ReentrantLock();

    private long longCount = 0;
    private int shortCount = 0;
    Random random = new Random();

    public void incrementLong(long value) {
        if (shouldLog) {
            log.info("Increment operation called");
        }
        if (value < 0) {
            throw new ArithmeticException("Value should not be less then 1 on increment operation");
        }

        try {
            while (true) {
                if (longLock.tryLock() && shortLock.tryLock()) {
                    if (!shortCountAllowsToProceed()) {
                        log.warn("ShortCount does not allow to increment long counter");
                        return;
                    }
                    longCount += value;
                    return;
                } else {
                    try {
                        Thread.sleep(random.nextInt(50));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } finally {
            longLock.unlock();
            shortLock.unlock();
        }
//        synchronized (longMonitor) {
//            if (shortCountAllowsToProceed()) {
//                log.warn("CAREFUL - shortCount is already too big " + shortCount);
//                return;
//            }
//            longCount += value;
//        }
    }

    public void incrementShort(int value) {
        if (shouldLog) {
            log.info("Increment operation called");
        }
        if (value < 0) {
            throw new ArithmeticException("Value should not be less then 1 on increment operation");
        }

        try {
            while (true) {
                if (longLock.tryLock() && shortLock.tryLock()) {
                    if (!longCountAllowsToProceed()) {
                        log.warn("LongCount does not allow to increment short counter");
                        return;
                    }
                    shortCount += value;
                    return;
                } else {
                    try {
                        Thread.sleep(random.nextInt(50));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } finally {
            longLock.unlock();
            shortLock.unlock();
        }

//        synchronized (shortMonitor) {
//            if (!longCountAllowsToProceed()) {
//                log.warn("CAREFUL - longCount is already too big " + shortCount);
//                return;
//            }
//            shortCount += value;
//        }
    }

    public boolean longCountAllowsToProceed() {
//        try {
//            longLock.lock();
            if (longCount > 1000000000) {
                return false;
            } else {
                return true;
            }
//        } finally {
//            longLock.unlock();
//        }

//        synchronized (longMonitor) {
//            if (longCount > 1000000) {
//                return false;
//            } else {
//                return true;
//            }
//        }
    }

    public boolean shortCountAllowsToProceed() {
//        try {
//            shortLock.lock();
            if (shortCount > 10000000) {
                return false;
            } else {
                return true;
            }
//        } finally {
//            shortLock.unlock();
//        }

//        synchronized (shortMonitor) {
//            if (shortCount > 100000) {
//                return false;
//            } else {
//                return true;
//            }
//        }
    }
}
