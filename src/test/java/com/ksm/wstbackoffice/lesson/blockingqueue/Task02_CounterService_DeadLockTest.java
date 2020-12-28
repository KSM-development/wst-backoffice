package com.ksm.wstbackoffice.lesson.blockingqueue;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Task02_CounterService_DeadLockTest {

    @Test
    public void testCounters() throws InterruptedException {
        Task02_CounterService_DeadLock counterService = new Task02_CounterService_DeadLock();

        Thread longIncrementalThread = new Thread(() -> IntStream.range(0, 10000).forEach(i -> counterService.incrementLong(1)));
        Thread shortDecrementalThread = new Thread(() -> IntStream.range(0, 10000).forEach(i -> counterService.incrementShort(1)));

        long before = System.currentTimeMillis();
        longIncrementalThread.start();
        shortDecrementalThread.start();

        longIncrementalThread.join();
        shortDecrementalThread.join();

        long after = System.currentTimeMillis();
        System.out.println(format("Operation took %s ms", (after - before)));

        //assertThat("Long count should be 0", counterService.getLongCount(), equalTo((long) 0));
        assertThat("Short count should be 0", counterService.getShortCount(), equalTo(0));
    }
}