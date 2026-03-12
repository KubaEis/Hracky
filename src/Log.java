public class Log {
    protected static synchronized void log(String message) {
        System.out.println(message);
    }
}