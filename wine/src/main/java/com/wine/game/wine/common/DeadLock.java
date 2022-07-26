package com.wine.game.wine.common;
/**
 * @description: 
 
 #　　　Codes are far away from bugs with the animal protecting　　　
 #　　　　　　　        神兽保佑,代码无bug　
 
 * @author: zeno fung
 *
 * @create: 2022-07-26 10:25
 */
public class DeadLock {
    private static Integer resource1 = 1 ;
    private static Integer resource2 = 2 ;
    static class Thread1 implements Runnable{
        @Override
        public void run() {
            synchronized (resource1){
                try {
                    System.out.println(getClass().getName()+" obtains the lock of resource1!");
                    Thread.sleep(500);
                    synchronized (resource2) {
                        System.out.println(getClass().getName()+" obtains the lock of resource2!");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static class Thread2 implements Runnable{
        @Override
        public void run() {
            synchronized (resource2){
                try {
                    System.out.println(getClass().getName()+" obtains the lock of resource2!");
                    Thread.sleep(500);
                    synchronized (resource1) {
                        System.out.println(getClass().getName()+" obtains the lock of resource1!");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        new Thread(new Thread2()).start();
    }

}

