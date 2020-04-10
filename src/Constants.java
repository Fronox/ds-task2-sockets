import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {
    public static final String HOST = "localhost";
    public static final int PORT = 8888;
    public static final int ARR_SIZE = 10;
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss:SSS");

    static String getCurrentTime() {
        return timeFormat.format(new Date());
    }
}
