import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //firstSubTask();
        secondSubTask();
    }
    public static void firstSubTask() {
        System.out.println("1-е подзадание:\n\n\n");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество потоков: ");
        int threads = scanner.nextInt();
        ArrayList<String> names = new ArrayList<String>();
        System.out.println("Введите имена потоков:");
        for (int i = 0; i < threads; i++) {
            System.out.print((i+1)+"-й: ");
            names.add(scanner.next());
        }
        System.out.print("Введите количество сообщений: ");
        int m = scanner.nextInt();
        ArrayList<First> firsts = new ArrayList<>();
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("text.txt", StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boolean isLast = false;
        for (int i = 0; i < threads; i++) {
            if (i == threads-1) {
                isLast = !isLast;
            }
            firsts.add(new First(names.get(i), fileWriter,isLast,m,i));

        }
        for (First f : firsts) {
            f.start();
        }
        for (First f : firsts) {
            try {
                f.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void secondSubTask() {
        System.out.println("2-е подзадание:\n\n\n");
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("text2.txt",true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Second> seconds = new ArrayList<Second>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество потоков: ");
        int threads = scanner.nextInt();
        ArrayList<String> names = new ArrayList<String>();
        System.out.println("Введите имена потоков:");
        for (int i = 0; i < threads; i++) {
            System.out.print((i+1)+"-й: ");
            names.add(scanner.next());
        }
        System.out.print("Введите количество сообщений: ");
        int m = scanner.nextInt();
        int row = m * threads;
        for (int i = 0; i < threads; i++) {
            seconds.add(new Second(names.get(i), fileWriter, m, i, row));
        }
        for (Second s : seconds) {
            s.start();
        }
        for(Second s : seconds) {
            try {
                s.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}