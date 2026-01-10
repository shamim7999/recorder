package com.example.threads;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

public class AudioThread implements Callable<Void> {

    private final File outputFile = new File("/home/shamim/Music/VoiceRecordsByProgram/recorded.wav");
    private volatile boolean running = true;



    @Override
    public Void call() throws Exception {
        AudioFormat format = new AudioFormat(
                16000,  // Sample Rate
                16,     // Sample Size in bits
                1,      // Channels (mono)
                true,   // Signed
                false   // Little Endian
        );

        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        if (!AudioSystem.isLineSupported(info)) {
            throw new IllegalStateException("Audio line not supported!");
        }

        TargetDataLine microphone = (TargetDataLine) AudioSystem.getLine(info);
        microphone.open(format);
        microphone.start();

        System.out.println("Recording...");

        try (AudioInputStream ais = new AudioInputStream(microphone)) {
            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, outputFile);

            // Loop until stopped
            while (running) {
                Thread.sleep(100);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            microphone.stop();
            microphone.close();
            System.out.println("Recording stopped.");
        }

        return null;
    }

    public void stopRecording() {
        running = false;
    }
}
