package util;

import org.apache.log4j.Logger;

public class EmptyResources {
    public static void close(AutoCloseable closable, Logger logger) {
        if (closable != null) {
            try {
                closable.close();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }
}
