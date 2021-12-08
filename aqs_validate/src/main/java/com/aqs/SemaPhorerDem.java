package com.aqs;

import java.util.concurrent.*;

public class SemaPhorerDem {

    static Semaphore semaphore2 = new Semaphore(5);
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,20,300, TimeUnit.SECONDS,new LinkedBlockingDeque());

    public static void main(String[] args) throws Exception{
        //simpleSeamPhorer();
        //
        for(;;){
            Thread.sleep(100);
            threadPoolExecutor.execute(()->exec());
        }

    }

    /**
     * 例如卖票,同时开放三个窗口(信号量),简单的限流,相当于有三个共享资源，
     * 当线程0,1,2拿到时,已经没有信号量可拿,线程3,4只有等前面三个线程中
     * 的某些线程执行完后释放了信号量(相当于释放了锁,),后面线程才能继续去
     * 竞争释放的信号量，拿到后才能进行执行
     */
    public static void simpleSeamPhorer(){
        Semaphore semaphore = new Semaphore(3);
            for (int i = 0; i < 5; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //加锁
                            semaphore.acquire();
                            System.out.println(Thread.currentThread().getName()+": "+"到窗口开始买票");
                            Thread.sleep(3000);
                            System.out.println(Thread.currentThread().getName()+": "+"买票完成");

                        }catch (Exception e){}
                        //释放锁
                        semaphore.release();
                    }
                }).start();
            }
    }

    public static void exec(){
        try {
            //加锁
            semaphore2.acquire(1);
            System.out.println("执行exec");
            Thread.sleep(5000);
        }catch (Exception e){}
        //释放锁
        semaphore2.release(1);
    }
}
