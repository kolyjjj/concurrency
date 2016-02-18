package me.koly.synchronize;

class CounterThread extends Thread {
    private Counter counter;
    private String name;

    public CounterThread(Counter counter, String name){
        this.counter = counter;
        this.name = name;
    }

    public void run() {
        for ( int i = 0; i < 1000; i++) {
//            System.out.println(name + " is running...");
                this.counter.increase();
//            try {
//                Thread.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    public static void main(String[] args) {
        // here is the race condition, two threads are using one same resource
        // the counter object is the resource
        Counter counter = new Counter();
        CounterThread counterThread1 = new CounterThread(counter, "one");
        CounterThread counterThread2 = new CounterThread(counter, "two");
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
