package me.koly.synchronize;

class CounterThread extends Thread {
    private Counter counter;

    public CounterThread(Counter counter){
        this.counter = counter;
    }

    public void run() {
        for ( int i = 0; i < 1000; i++) {
            this.counter.increase();
        }
    }

    public static void main(String[] args) {
        // here is the race condition, two threads are using one same resource
        // the counter object is the resource
        Counter counter = new Counter();
        CounterThread counterThread1 = new CounterThread(counter);
        CounterThread counterThread2 = new CounterThread(counter);
        counterThread1.start();
        counterThread2.start();

        try {
            counterThread1.join();
            counterThread2.join();
            System.out.println(counter.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
