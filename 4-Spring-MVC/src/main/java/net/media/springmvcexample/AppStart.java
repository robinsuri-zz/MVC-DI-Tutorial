package net.media.springmvcexample;

import org.apache.log4j.PropertyConfigurator;
import org.apache.tomcat.InstanceManager;
import org.apache.tomcat.SimpleInstanceManager;
import org.eclipse.jetty.annotations.ServletContainerInitializersStarter;
import org.eclipse.jetty.apache.jsp.JettyJasperInitializer;
import org.eclipse.jetty.plus.annotation.ContainerInitializer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppStart {
    private int port;
    public static Server server;
    private static final Logger logger = Logger.getLogger(AppStart.class.getName());
    private static Properties props;

    public static void main(String[] args) throws Exception {
        String env = System.getProperty("WEB_APP_CONFIG");
        int port = 0;
        try {
            InputStream in = new FileInputStream(env);
            props = new Properties();
            props.load(in);
            in.close();
            PropertyConfigurator.configure(props);
            port = Integer.parseInt(props.getProperty("port"));
            logger.log(Level.INFO, "Port : {0}", new Object[]{String.valueOf(port)});
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Error reading port number from " + env, e);
            System.exit(1);
        }

        AppStart appStart = new AppStart(port);
        try {
            appStart.start();
            appStart.waitForInterrupt();
        } finally {
            appStart.stop();
        }
        System.exit(1);
    }

    public AppStart(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        server = new Server();
        ServerConnector connector = connector();
        server.addConnector(connector);
        WebAppContext webAppContext = getWebAppContext();
        server.setHandler(webAppContext);
        server.start();
    }

    private ServerConnector connector() {
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        return connector;
    }

    private WebAppContext getWebAppContext() throws IOException {
        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setDescriptor("webapp/WEB-INF/web.xml");
        context.setAttribute("javax.servlet.context.tempdir", getScratchDir());
        String webapp = "src";
        if ("src".equals(webapp)) {
            String userDir = System.getProperty("user.dir");
            URL url = new URL(new File(userDir).toURI().toURL(), "src/main/webapp");
            String srcWebappPath = url.toString();
            context.setResourceBase(srcWebappPath);
        } else {
            context.setResourceBase(new ClassPathResource("webapp").getURI().toString());
        }
        context.setAttribute("org.eclipse.jetty.containerInitializers", jspInitializers());
        context.setAttribute(InstanceManager.class.getName(), new SimpleInstanceManager());
        context.addBean(new ServletContainerInitializersStarter(context), true);
        context.setClassLoader(getUrlClassLoader(context));
        return context;
    }

    private List<ContainerInitializer> jspInitializers() {
        JettyJasperInitializer sci = new JettyJasperInitializer();
        ContainerInitializer initializer = new ContainerInitializer(sci, null);
        List<ContainerInitializer> initializers = new ArrayList<ContainerInitializer>();
        initializers.add(initializer);
        return initializers;
    }

    private WebAppClassLoader getUrlClassLoader(WebAppContext context) throws IOException {
        return new WebAppClassLoader(getClass().getClassLoader(), context);
    }

    public void stop() throws Exception {
        if (server != null) {
            server.stop();
        }
    }

    public void waitForInterrupt() throws InterruptedException {
        if (server != null) {
            server.join();
        }
    }

    private File getScratchDir() throws IOException {
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        File scratchDir = new File(tempDir.toString(), "spring-mvc");

        if (!scratchDir.exists()) {
            if (!scratchDir.mkdirs()) {
                throw new IOException("Unable to create scratch directory: " + scratchDir);
            }
        }
        return scratchDir;
    }
}
