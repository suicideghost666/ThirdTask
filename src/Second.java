import java.io.FileWriter;
import java.io.IOException;

public class Second extends Thread {
    private final int index;
    private final int m;
    private static int count;
    private final FileWriter fileWriter;
    private static int currentIndex;
    private static final Object lock = new Object();
    static String output;
    @Override

    public void run() {
        try {
            synchronized (lock) {
                for (int i = 0; i < m; i++) {
                    while (currentIndex != index) {
                        lock.wait();
                    }
                    if (output == null) {
                        output = this.getName();
                    } else {
                        output += this.getName();
                    }
                    fileWriter.write(output);
                    fileWriter.write("\n");
                    fileWriter.flush();

                }
                currentIndex++;
                lock.notifyAll();
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Second(String name, FileWriter fileWriter, int m, int index, int rows) {
        this.setName(name);
        this.fileWriter = fileWriter;
        this.m = m;
        this.index = index;
    }
}
