import java.text.SimpleDateFormat;
import java.util.Date;

// Class for storing constants and other useful functions
public class Configuration {
    // Hostname for listen
    public static final String HOST = "localhost";
    //Port number for listen
    public static final int PORT = 8888;
    //Size of sorted array
    public static final int ARR_SIZE = 10;
    // Datetime formatter
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss:SSS");

    // Method for current time obtaining in human-readable format
    static String getCurrentTime() {
        return timeFormat.format(new Date());
    }
}
