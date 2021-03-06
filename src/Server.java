import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Server {

    private static final Scanner scanner = new Scanner(System.in);

    // Server start method
    private static void serverRun(String host, int port) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(host, port));

        System.out.println(String.format("[%s] Socket binds to %s", Configuration.getCurrentTime(), server.getLocalSocketAddress()));
        System.out.println(String.format("[%s] Server is waiting for clients", Configuration.getCurrentTime()));

        Socket socket = server.accept();
        System.out.println(String.format("[%s] New client connected to %s", Configuration.getCurrentTime(), socket.getLocalSocketAddress()));

        ObjectInputStream dataIn = new ObjectInputStream(socket.getInputStream());
        Object receivedObject = dataIn.readObject();

        Class cls = Class.forName(receivedObject.getClass().getName());
        Method getArray = cls.getDeclaredMethod("getArray");
        int[] array = (int[]) getArray.invoke(receivedObject);

        System.out.println(String.format("[%s] Received array from client: %s", Configuration.getCurrentTime(), Arrays.toString(array)));
        Method sort = cls.getDeclaredMethod("sort");
        sort.invoke(receivedObject);
        int[] sortedArray = (int[]) getArray.invoke(receivedObject);

        System.out.println(String.format("[%s] Sorted array: %s", Configuration.getCurrentTime(), Arrays.toString(sortedArray)));
        System.out.println(String.format("[%s] Press enter key to send data back", Configuration.getCurrentTime()));
        scanner.nextLine();

        System.out.println(String.format("[%s] Sent data back to client", Configuration.getCurrentTime()));
        ObjectOutputStream dataOut = new ObjectOutputStream(socket.getOutputStream());
        dataOut.writeObject(receivedObject);

        dataIn.close();
        dataOut.close();
        socket.close();
        server.close();
    }

    // Main server method
    public static void main(String[] args) {
        System.out.println("Press enter key to run server...");
        scanner.nextLine();
        try {
            String host = Configuration.HOST;
            int port = Configuration.PORT;
            serverRun(host, port);
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
