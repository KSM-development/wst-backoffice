package com.ksm.wstbackoffice.lesson.blockingqueue;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Task01_CounterService_ConcurrentAccessTest {

    @Test
    public void testCounter() throws InterruptedException {
        Task01_CounterService_ConcurrentAccess counterService = new Task01_CounterService_ConcurrentAccess();

        Thread incrementalThread = new Thread(() -> IntStream.range(0, 1000000).forEach(i -> counterService.increment(1)));
        Thread decrementalThread = new Thread(() -> IntStream.range(0, 1000000).forEach(i -> counterService.decrement(1)));

        long before = System.currentTimeMillis();
        incrementalThread.start();
        decrementalThread.start();

        incrementalThread.join();
        decrementalThread.join();

        long after = System.currentTimeMillis();
        System.out.println(format("Operation took %s ms", (after - before)));

        assertThat("Count should be 0", counterService.getCount(), equalTo(0));
    }

}