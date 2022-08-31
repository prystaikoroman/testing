package util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public final class DSInstance {
    private static DSInstance instance;
    DataSource ds = null;
    private final static Logger logger = LoggerFactory.getLogger(DSInstance.class);
    private DSInstance(){
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/TestDB");
            logger.trace("context.setAttribute 'dataSource': {}", ds.getClass().getName());
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
