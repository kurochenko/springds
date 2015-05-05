package net.kurochenko.springds.app;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;


/**
 * Starts web application using embedded Jetty server. Binds Springs DispatcherServlet to request handlers
 *
 * @author kurochenko
 */
public class Start {

    private static final String CONFIG_LOCATION = "net.kurochenko.springds.ui";
    private static final int DEFAULT_PORT = 8080;
    private static final String DEFAULT_CONTEXT_PATH = "/";

    public static void main(String[] args) throws Exception {
        Server server = new Server(DEFAULT_PORT);
        server.setHandler(getDispatcherServletContext(getWebConfigContext()));
        server.start();
        server.join();
    }

    private static ServletContextHandler getDispatcherServletContext(AnnotationConfigWebApplicationContext context) throws IOException {
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath(DEFAULT_CONTEXT_PATH);
        handler.addServlet(new ServletHolder(new DispatcherServlet(context)), "/*");
        handler.addEventListener(new ContextLoaderListener(context));
        handler.setResourceBase(new ClassPathResource("static").getURI().toString());
        return handler;
    }

    private static AnnotationConfigWebApplicationContext getWebConfigContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);
        return context;
    }

}
