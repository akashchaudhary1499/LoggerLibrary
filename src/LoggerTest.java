import com.logger.LogLevel;
import com.logger.Logger;
import com.logger.sink.SinkConfig;
import com.logger.sink.SinkType;
import com.logger.sink.TextFileSinkConfig;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LoggerTest {
    public static void main(String[] args) throws IOException {
        SinkConfig sinkConfig = new TextFileSinkConfig("yy:MM:dd: HH:mm:ss Z", SinkType.TextFile.value,
                Arrays.asList(LogLevel.DEBUG, LogLevel.INFO, LogLevel.FATAL),
                "C:\\Users\\Akash Chaudhary\\IdeaProjects\\LoggerLibrary\\Logs\\application.log", 1000);
        SinkConfig sinkConfig2 = new SinkConfig("yy:MM:dd: HH:mm:ss Z",
                SinkType.Console.value, Collections.singletonList(LogLevel.FATAL));

        List<SinkConfig> configs = Arrays.asList(sinkConfig, sinkConfig2);

        Logger.configure(configs);
        Logger logger = Logger.getInstance();

        for(int i = 0;i < 100;i++) {
            logger.log("debug log count" + i, LogLevel.DEBUG, "LoggerTest");
            logger.log("info log count" + i, LogLevel.INFO, "LoggerTest");
            logger.log("fatal log count" + i, LogLevel.FATAL, "LoggerTest");
        }
    }
}
