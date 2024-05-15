import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class First extends Thread {
    private final boolean isLast;
    private final FileWriter fileWriter;
    private static int messageCount;
    private final int target;
    private final int index;
    static int currentIndex;

    private synchronized void write() {
        try {
            if (currentIndex == index) {
                if (messageCount != target) {
                    System.out.println(this.getName());
                    fileWriter.write(getName() + String.valueOf(messageCount) + "->" + String.valueOf(messageCount) + "\n");
                    fileWriter.flush();
                    currentIndex++;
                }
                if (isLast) {
                    messageCount++;
                    currentIndex = 0;
                }
            }
        } catch ( IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (messageCount < target) {
            write();
        }
    }
    public First(String name, FileWriter fileWriter, boolean isLast,int target,int index) {
        this.setName(name);
        this.fileWriter = fileWriter;
        this.isLast = isLast;
        this.target = target;
        this.index = index;
    }
}
