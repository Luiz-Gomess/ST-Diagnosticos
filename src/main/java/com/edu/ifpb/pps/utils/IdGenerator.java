package com.edu.ifpb.pps.utils;

public class IdGenerator {
    private static IdGenerator instance;
    private int counter;

    private IdGenerator() {
        this.counter = 0;
    }

    public static synchronized IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    public synchronized int getNextId() {
        return ++counter;
    }
}
