package com.sqb.blog.util.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by vic
 * Create time : 2017/2/8 下午6:36
 */
public class MyTest {


    public static void main(String[] args) {
/*
        CountDownLatch end = new CountDownLatch(10);
        final CountDownLatch begin = new CountDownLatch(1);


        for (int i = 1; i < 10; i++) {
            new Thread(new Work(begin, end, i)).start();
        }
        try {
            begin.countDown();
            end.await();
            System.out.println("ok");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        List<String> list = Collections.synchronizedList(new ArrayList<String>());
*/

        AtomicInteger index = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(10);
        latch.countDown();
        int resut = index.incrementAndGet();
        System.out.println(resut);


        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 1; i < 10; i++) {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "正在执行。。。");
                }
            });
            executorService.execute(thread);
        }


    }

    static class Work implements Runnable {

        private CountDownLatch b;
        private CountDownLatch e;
        private int beginIndex;


        public Work(CountDownLatch b, CountDownLatch e, int beginIndex) {
            this.b = b;
            this.e = e;
            this.beginIndex = beginIndex;
        }

        @Override
        public void run() {
            try {
                b.await();
                beginIndex = (beginIndex - 1) * 10 + 1;
                for (int i = beginIndex; i < beginIndex + 10; i++) {
                    System.out.println(i);
                }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } finally {
                e.countDown();
            }

        }


    }


}
