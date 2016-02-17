package me.koly.lock;

import java.util.Random;

public class Philosopher extends Thread {
    private Chopstick left, right;
    private Random random;
    private String name;

    public Philosopher(String name, Chopstick first, Chopstick second) {
        this.name = name;
        this.left = first;
        this.right = second;
        this.random = new Random();
    }

    public void run() {
        try {
            while(true) {
                Thread.sleep(this.random.nextInt(1));
                synchronized (left) {
                    synchronized (right) {
                        System.out.printf(this.name + " eating...");
                        Thread.sleep(this.random.nextInt(1));
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Chopstick one = new Chopstick(1);
        Chopstick two = new Chopstick(2);
        Chopstick three = new Chopstick(3);
        Chopstick four = new Chopstick(4);
        Chopstick five = new Chopstick(5);

        Philosopher philosopher1 = new Philosopher("one", one, two);
        Philosopher philosopher2 = new Philosopher("two", two, three);
        Philosopher philosopher3 = new Philosopher("three", three, four);
        Philosopher philosopher4 = new Philosopher("four", four, five);
        Philosopher philosopher5 = new Philosopher("five", five, one);

        philosopher1.start();
        philosopher2.start();
        philosopher3.start();
        philosopher4.start();
        philosopher5.start();

        try {
            philosopher1.join();
            philosopher2.join();
            philosopher3.join();
            philosopher4.join();
            philosopher5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
