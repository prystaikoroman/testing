package util;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public final class DSInstance {
    private static DSInstance instance;
    DataSource ds = null;
    private final static Logger logger = Logger.getLogger(DSInstance.class);
    private DSInstance(){
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/TestDB");
        } catch (NamingException e) {
            logger.error(e.getMessage());
        }
        logger.info("ds ==> " + ds);
    }
    public static DSInstance getInstance(){
        // датасорс зробити сінглтоном як поле в окремому класі
        DSInstance result = instance;
        if (result != null) {
            return result;
        }
        synchronized(DSInstance.class) {
            if (instance == null) {
                instance = new DSInstance();
            }
            return instance;
        }
    }

    public DataSource getDs() {
        return ds;
    }
}
