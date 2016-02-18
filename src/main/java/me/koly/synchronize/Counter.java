package me.koly.synchronize;

class Counter{
    private int count = 0;

    public synchronized void increase() {
        count = count + 1;
    }

    public int getCount() {
        return this.count;
    }
}
