package com.testing.Listener;

import javax.naming.Context;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionListener;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.testing.util.DSInstance;

@WebListener
public class ContextListener implements ServletContextListener, HttpSessionListener {
	private static final Logger LOG = LoggerFactory.getLogger(ContextListener.class);

	// bootstrap of the application 
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		LOG.debug("Start context initialization");
		ServletContext context = sce.getServletContext();
		initDatasource(context);
		LOG.debug("DataSource initialized");;
	}

	private void initDatasource(ServletContext context) throws IllegalStateException {
		String dataSourceName = context.getInitParameter("dataSource");
		Context jndiContext = null;

		DataSource dataSource =  DSInstance.getInstance().getDs();
		context.setAttribute("dataSource", dataSource);
		LOG.trace("context.setAttribute 'dataSource': {}",dataSource.getClass().getName());
	}
}
