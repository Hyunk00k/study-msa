package com.example.dev.api;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Worker implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int rand = (int) (Math.random() * 100) + 1;
            try {
                Thread.sleep(rand);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("run {} times, during {}", i, rand);
        }
    }
}