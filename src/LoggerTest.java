import com.logger.LogLevel;
import com.logger.Logger;
import com.logger.sink.SinkConfig;
import com.logger.sink.SinkType;
import com.logger.sink.TextFileSinkConfig;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LoggerTest {
    public static void main(String[] args) {
        SinkConfig sinkConfig = new TextFileSinkConfig("yy:MM:dd: HH:mm:ss Z", SinkType.TextFile.value,
                Arrays.asList(LogLevel.DEBUG, LogLevel.INFO, LogLevel.FATAL),
                "C:\\Users\\Akash Chaudhary\\IdeaProjects\\LoggerLibrary\\Logs\\application.log", 1000);
        SinkConfig sinkConfig2 = new SinkConfig("yy:MM:dd: HH:mm:ss Z",
                SinkType.Console.value, Collections.singletonList(LogLevel.FATAL));

        List<SinkConfig> configs = Arrays.asList(sinkConfig, sinkConfig2);

        Logger.configure(configs);
        Logger logger = Logger.getInstance();
        logger.addGlobalContext("hostId", "testHostID123");

        Thread thread1 = new Thread(() -> {
            logger.addThreadContext("trackingId", "thread1 tracking id");
            for (int i=0; i <= 10; i++) {
                logger.log("fatal log count" + i, LogLevel.FATAL, "LoggerTest");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            logger.removeThreadContext("trackingId");
        });

        Thread thread2 = new Thread(() -> {
            logger.addThreadContext("trackingId", "thread2 tracking id");
            for (int i=0; i <= 10; i++) {
                logger.log("fatal log count" + i, LogLevel.FATAL, "LoggerTest");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            logger.removeThreadContext("trackingId");
        });

        thread1.start();
        thread2.start();
    }
}
