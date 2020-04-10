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
    private static void serverRun(String host, int port) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(host, port));

        System.out.println(String.format("[%s] Socket binds to %s", Constants.getCurrentTime(), server.getLocalSocketAddress()));
        System.out.println(String.format("[%s] Server is waiting for clients", Constants.getCurrentTime()));

        Socket socket = server.accept();
        System.out.println(String.format("[%s] Server connected to %s", Constants.getCurrentTime(), socket.getLocalSocketAddress()));

        ObjectInputStream dataIn = new ObjectInputStream(socket.getInputStream());
        Object receivedObject = dataIn.readObject();
        Class cls = Class.forName(receivedObject.getClass().getName());
        Method getArray = cls.getDeclaredMethod("getArray");
        int[] array = (int[]) getArray.invoke(receivedObject);
        System.out.println(String.format("[%s] Received array from client: %s", Constants.getCurrentTime(), Arrays.toString(array)));
        Method sort = cls.getDeclaredMethod("sort");
        sort.invoke(receivedObject);
        ObjectOutputStream dataOut = new ObjectOutputStream(socket.getOutputStream());
        dataOut.writeObject(receivedObject);

        dataIn.close();
        dataOut.close();
        socket.close();
        server.close();
    }

    public static void main(String[] args) {
        System.out.println("Press any key to run server...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        try {
            String host = Constants.HOST;
            int port = Constants.PORT;
            serverRun(host, port);
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
