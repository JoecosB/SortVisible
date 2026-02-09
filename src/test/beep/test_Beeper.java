package beep;

import beep.Beeper;

public class test_Beeper {
    public static void main(String[] args) {
        Beeper beeper = new Beeper();
        beeper.beep(2000);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
