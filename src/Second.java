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
            int count = 0;
            while (count <= m) {
                synchronized (lock) {
                    while (currentIndex != index) {
                        lock.wait();
                    }
//                    //System.out.println(currentIndex);
//                    if (Second.count <= m) {
//                        if (output == null) {
//                            output = this.getName().repeat(count);
//                            // System.out.println( "name:" + this.getName() + "output: " + output);
//                        } else {
//                            output += this.getName().repeat(count);
//                        }
//                        output += "\n";
//                        fileWriter.write(output);
//                        Second.count++;
//                    } else {
//                        currentIndex++;
//                        Second.count = 0;
//                        lock.notifyAll();
//                    }
//                    System.out.println( "output: " + output);
//                    // fileWriter.flush();
                    if ()
                    lock.notifyAll();
                }
            }
        } catch (InterruptedException /*| IOException */e) {
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
