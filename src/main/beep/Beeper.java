package beep;

import javax.sound.sampled.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Beeper {

    private static final float SAMPLE_RATE = 44100;
    private static final int CHUNK_SAMPLES = 256;

    private final AtomicInteger currentFreq = new AtomicInteger(0);
    private volatile boolean running = true;

    private final Thread audioThread;

    public Beeper() {
        audioThread = new Thread(this::runAudio, "audio-engine");
        audioThread.setDaemon(true);
        audioThread.start();
    }

    public void beep(int freq) {
        currentFreq.set(freq); // 高频调用是安全的
    }

    public void shutdown() {
        running = false;
        audioThread.interrupt();
    }

    private void runAudio() {
        SourceDataLine line = null;

        try {
            AudioFormat format = new AudioFormat(
                    SAMPLE_RATE, 16, 1, true, false
            );

            line = AudioSystem.getSourceDataLine(format);
            line.open(format);
            line.start();

            byte[] buffer = new byte[CHUNK_SAMPLES * 2];
            int sampleIndex = 0;

            while (running) {

                int freq = currentFreq.get();

                if (freq <= 0) {
                    Thread.sleep(5);
                    continue;
                }

                for (int i = 0; i < CHUNK_SAMPLES; i++) {
                    double angle =
                            2.0 * Math.PI * sampleIndex * freq / SAMPLE_RATE;
                    short value =
                            (short) (Math.sin(angle) * Short.MAX_VALUE);

                    buffer[2 * i]     = (byte) (value & 0xff);
                    buffer[2 * i + 1] = (byte) ((value >> 8) & 0xff);

                    sampleIndex++;
                }

                line.write(buffer, 0, buffer.length);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (line != null) {
                line.stop();
                line.close();
            }
        }
    }
}
