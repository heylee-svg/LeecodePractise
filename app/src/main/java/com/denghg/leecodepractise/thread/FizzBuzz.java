package com.denghg.leecodepractise.thread;


import java.util.concurrent.atomic.AtomicInteger;

class FizzBuzz {
    int n = 0;
    Object lock = new Object();
    AtomicInteger current = new AtomicInteger(1);

    public FizzBuzz(int n) {
        this.n = n;
    }            // constructor

    public void fizz(Runnable printFizz) {
        while (current.get() <= n) {
        synchronized (lock) {
            if (current.get() % 3 == 0 && current.get() % 5 != 0 && current.get() <= n) {
                printFizz.run();
                current.incrementAndGet();
            } else {
                Thread.yield();
            }
        }

        }
    }          // only output "fizz"

    public void buzz(Runnable printBuzz) {
        while (current.get() < n) {
            synchronized (lock) {

                if (current.get() % 5 == 0 && current.get() % 3 != 0 && current.get() <=n) {
                    printBuzz.run();
                    current.incrementAndGet();
                } else {
                    Thread.yield();
                }
            }
        }

    }          // only output "buzz"

    public void fizzbuzz(Runnable printFizzBuzz) {

        while (current.get() <= n) {
            synchronized (lock) {

                if (current.get() % 3 == 0 && current.get() % 5 == 0 && current.get() <= n) {
                    printFizzBuzz.run();
                    current.incrementAndGet();
                } else {
                    Thread.yield();
                }
            }

        }
    }  // only output "fizzbuzz"

    public void number(Runnable printNumber) {
        while (current.get() <= n) {
            synchronized (lock) {
                if (current.get() % 3 != 0 && current.get() % 5 != 0 && current.get() <= n) {
                    printNumber.run();
                    current.incrementAndGet();
                } else {
                    Thread.yield();
                }
            }

        }
    }
    // only output the numbers


    public static void main(String[] args) {
        FizzBuzz fooBar = new FizzBuzz(100);

        Thread threadA = new Thread(new Runnable() {

            @Override
            public void run() {

                fooBar.fizz(new Runnable() {
                    @Override
                    public void run() {
                        System.out.print("fizz ");
                    }
                });

            }
        }, "A");
        threadA.start();

        Thread threadB = new Thread(new Runnable() {

            @Override
            public void run() {

                fooBar.buzz(new Runnable() {
                    @Override
                    public void run() {
                        System.out.print("buzz ");

                    }
                });

            }
        }, "B");
        threadB.start();

        Thread threadC = new Thread(new Runnable() {

            @Override
            public void run() {

                fooBar.fizzbuzz(new Runnable() {
                    @Override
                    public void run() {
                        System.out.print("fizzbuzz ");
                    }
                });

            }
        }, "c");
        threadC.start();

        Thread threadD = new Thread(new Runnable() {

            @Override
            public void run() {
                fooBar.number(new Runnable() {
                    @Override
                    public void run() {
                        System.out.print("" + fooBar.current + " ");
                    }
                });

            }
        }, "D");
        threadD.start();
    }
}



