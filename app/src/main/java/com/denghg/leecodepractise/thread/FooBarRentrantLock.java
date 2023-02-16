package com.denghg.leecodepractise.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印forbar
 * ReentrantLock
 */
public class FooBarRentrantLock {
    private int n;

    public FooBarRentrantLock(int n) {
        this.n = n;
    }

    private ReentrantLock lock = new ReentrantLock(true);
    Boolean fooExec = true;

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; ) {
            lock.lock();
            try {
                if (fooExec) {
                    printFoo.run();
                    fooExec = false;
                    i++;
                }
            } finally {
                lock.unlock();
            }


        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; ) {
            lock.lock();
            try {
                if (!fooExec) {
                    printBar.run();
                    fooExec = true;
                    i++;
                }
            } finally {
                lock.unlock();
            }

        }
    }


    public static void main(String[] args) {
        FooBarRentrantLock fooBar = new FooBarRentrantLock(5);
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