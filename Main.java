import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        File[] files = new File("./songs").listFiles();
        while (true) {
            String fileName = files[random.nextInt(files.length) - 1].getName();
            ProcessBuilder pb = new ProcessBuilder("ffplay", "-nodisp", "-loglevel", "quiet", "-autoexit", "-volume", "10", "songs\\" + fileName);
            Process process = null;

            try {
                process = pb.inheritIO().start();
                System.out.println("Now playing: " + fileName);
                process.waitFor();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }

            if (process != null) {
                process.destroy();
            }
        }
    }
}
