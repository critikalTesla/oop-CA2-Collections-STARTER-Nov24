package org.example;

public class Sample {
    private int count;
    private boolean finished;

    public Sample() {
        this.count = 0;
        this.finished = false;
    }

    public boolean getStatus() {
        return this.finished;
    }
}
