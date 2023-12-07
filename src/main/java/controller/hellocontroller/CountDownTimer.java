package controller.hellocontroller;

import javafx.scene.layout.Pane;

public class CountDownTimer {
    private long start = -1;

    private long amount;

    public CountDownTimer() {

    }

    public CountDownTimer(long amount) {
        this.amount = amount;
    }

    public void start() {
        start = System.currentTimeMillis();
    }

    public boolean isStart() {
        return start != -1;
    }

    public boolean isEnd() {
        if (!isStart()) {
            throw new RuntimeException("Timer hasn't been started!");
        }
        long end = System.currentTimeMillis();
        return end - start > amount;
    }

    public void reset() {
        start = -1;
    }
}
