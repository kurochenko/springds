package net.kurochenko.springds.app;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;
import java.util.Properties;


/**
 * Starts web application using embedded Jetty server. Binds Springs DispatcherServlet to request handlers
 *
 * @author kurochenko
 */
public class Start {

    private static final String CONFIG_LOCATION = "net.kurochenko.springds.ui";
    private static Properties props;

    /**
     * Starts Jetty server. Expects first argument as profile name. If not profile is specified, default is used
     */
    public static void main(String[] args) throws Exception {
        String profile = (args.length > 0) ? args[0] : ProfileProperties.PROFILE_DEFAULT;

        try {
            props = ProfileProperties.getProps(profile);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        Server server = new Server(Integer.valueOf(props.getProperty("port")));
        server.setHandler(getDispatcherServletContext(getWebConfigContext()));
        server.start();
        server.join();
    }

    private static ServletContextHandler getDispatcherServletContext(AnnotationConfigWebApplicationContext context) throws IOException {
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath(props.getProperty("contextPath"));
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
