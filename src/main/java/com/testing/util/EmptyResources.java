package com.testing.util;

import org.slf4j.Logger;

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
