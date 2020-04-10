import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Client {
    private static void clientRun(String host, int port) throws IOException, ClassNotFoundException {
        Socket client = new Socket();
        client.connect(new InetSocketAddress(host, port));

        Random rand = new Random();
        int len = Constants.ARR_SIZE;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++)
            arr[i] = rand.nextInt(100);
        
        InsertionSort insertionSort = new InsertionSort(arr);

        ObjectOutputStream dataOut = new ObjectOutputStream(client.getOutputStream());
        dataOut.writeObject(insertionSort);

        ObjectInputStream dataIn = new ObjectInputStream(client.getInputStream());
        InsertionSort resultSort = (InsertionSort) dataIn.readObject();
        int[] resArray = resultSort.getArray();
        System.out.println(String.format("[%s] Sorted array: %s", Constants.getCurrentTime(), Arrays.toString(resArray)));

        dataOut.close();
        dataIn.close();
        client.close();
    }

    public static void main(String[] args) {
        System.out.println("Press any key to run client...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        try {
            String host = Constants.HOST;
            int port = Constants.PORT;
            clientRun(host, port);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
