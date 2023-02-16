package com.denghg.leecodepractise.thread;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * BlockingQueue版
 */
public class FooBarBlockQueue {
    int n = 0;
    private BlockingQueue<Integer> fooQueue = new LinkedBlockingQueue<Integer>() {{
        add(0);
    }};
    private BlockingQueue<Integer> barQueue = new LinkedBlockingQueue<Integer>();

    public FooBarBlockQueue(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            fooQueue.take();
            printFoo.run();
            barQueue.add(0);
        }

    }

    public void bar(Runnable printbar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            barQueue.take();
            printbar.run();
            fooQueue.add(0);
        }

    }
    public static void main(String[] args) {
        FooBarBlockQueue fooBar = new FooBarBlockQueue(5);
        Thread threadA = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    fooBar.foo(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print("foo");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadA.start();

        Thread threadB = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    fooBar.bar(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print("bar");

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadB.start();
    }
}
