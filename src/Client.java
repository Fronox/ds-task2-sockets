import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Client {
    private static final Scanner scanner = new Scanner(System.in);

    // Client start method
    private static void clientRun(String host, int port) throws IOException, ClassNotFoundException {
        Socket client = new Socket();
        client.connect(new InetSocketAddress(host, port));
        System.out.println(String.format("[%s] Connected to: %s", Configuration.getCurrentTime(), client.getRemoteSocketAddress()));
        Random rand = new Random();
        int len = Configuration.ARR_SIZE;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++)
            arr[i] = rand.nextInt(100);

        System.out.println(String.format("[%s] Generated array: %s", Configuration.getCurrentTime(), Arrays.toString(arr)));

        InsertionSort insertionSort = new InsertionSort(arr);

        ObjectOutputStream dataOut = new ObjectOutputStream(client.getOutputStream());
        dataOut.writeObject(insertionSort);

        System.out.println(String.format("[%s] Wait for data from server...", Configuration.getCurrentTime()));
        ObjectInputStream dataIn = new ObjectInputStream(client.getInputStream());
        InsertionSort resultSort = (InsertionSort) dataIn.readObject();
        int[] resArray = resultSort.getArray();
        System.out.println(String.format("[%s] Data is received", Configuration.getCurrentTime()));
        System.out.println(String.format("[%s] Sorted array: %s", Configuration.getCurrentTime(), Arrays.toString(resArray)));

        dataOut.close();
        dataIn.close();
        client.close();
    }

    // Main client method
    public static void main(String[] args) {
        System.out.println("Press enter key to run client...");
        scanner.nextLine();
        try {
            String host = Configuration.HOST;
            int port = Configuration.PORT;
            clientRun(host, port);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
