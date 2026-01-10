package com.example;



import com.example.threads.AudioThread;
import com.example.threads.ScreenShotThread;

import java.util.concurrent.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Callable<Void> screenShotThread = new ScreenShotThread();
        Callable<Void> audioThread = new AudioThread();
        executorService.submit(screenShotThread);
        executorService.submit(audioThread);
        executorService.shutdown();
        executorService.awaitTermination(30, TimeUnit.SECONDS);
    }
}