import edu.princeton.cs.algs4.StdAudio;
import es.datastructur.synthesizer.GuitarString;

import java.util.HashMap;

public class GuitarHero {

    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        HashMap<Character, GuitarString> m = new HashMap<>();
        for (int i = 0; i < keyboard.length(); i++) {
            char key = keyboard.charAt(i);
            double frequency = 440 * (Math.pow(2, (i - 24) / 12));
            m.put((Character) key, new GuitarString(frequency));
        }

        while (true) {
            double sample = 0.0;
            if (StdDraw.hasNextKeyTyped()) {
                char keyType = StdDraw.nextKeyTyped();
                for (Character k : m.keySet()) {
                    if ((Character) keyType == k) {
                        m.get(keyType).pluck();
                        break;
                    }
                }
            }

            for (Character k : m.keySet()) {
                sample += m.get(k).sample();
            }

            StdAudio.play(sample);

            for (Character k : m.keySet()) {
                m.get(k).tic();
            }
        }
    }
}
