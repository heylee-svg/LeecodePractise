package com.denghg.leecodepractise.thread;

/**
 * yeild版
 */
class FooBarYeild {
    private int n;
    //直接使用bollean类型就好，true的时候允许A线程打印，false的时候线程B打印。但是标识需要在线程之间保持及时的可见效所以我们使用volatile修饰一下就好。
    private volatile static boolean flag = true;
    boolean fooExec = true;

    public FooBarYeild(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; ) {
            if (fooExec) {
                printFoo.run();
                fooExec = false;
                i++;
            } else {
                Thread.yield();
            }

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; ) {
            if (!fooExec) {
                printBar.run();
                fooExec = true;
                i++;
            } else {
                Thread.yield();
            }

        }
    }


    public static void main(String[] args) {
        FooBarYeild fooBar = new FooBarYeild(5);
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