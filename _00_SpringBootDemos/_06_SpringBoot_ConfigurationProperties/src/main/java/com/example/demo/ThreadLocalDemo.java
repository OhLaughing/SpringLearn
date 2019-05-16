package com.example.demo;

import java.util.concurrent.TimeUnit;

/**
 * Created by gustaov on 2019/4/27.
 */
public class ThreadLocalDemo {
    static ThreadLocal<User> local = new ThreadLocal<User>(){
        @Override
        protected User initialValue() {
            return new User("kobe", "12");
        }
    };

    public static void main(String[] args) throws InterruptedException {
        local.set(new User("james", "23"));
        new Thread(new MyRynnable()).start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(Thread.currentThread().getName() + ": "+local.get());

    }
    static class MyRynnable implements Runnable{

        @Override
        public void run() {
            local.set(new User("owen", "11"));
            System.out.println(Thread.currentThread().getName() + ": "+local.get());
        }
    }
}
