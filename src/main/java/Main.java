import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.FileInputStream;
import java.security.KeyStore;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.util.ssl.SslContextFactory;

/**
 * Main class.
 *
 */
public class Main {
	
	public static final String BASE_URI = "http://localhost:8080/";
	
	public static void main(String[] args) throws Exception{
        Server server = startServer();
        server.start();
        server.join();
        
        Server server2 = new Server();
        int port = 12000;

        // Setup SSL
        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath(System.getProperty("jetty.keystore.path","C:/keystore.jks"));
        sslContextFactory.setKeyStorePassword(System.getProperty("jetty.keystore.password","password"));
        sslContextFactory.setKeyManagerPassword(System.getProperty("jetty.keymanager.password","password"));

        // Setup HTTP Configuration
        HttpConfiguration httpConf = new HttpConfiguration();
        httpConf.setSecurePort(port);
        httpConf.setSecureScheme("https");
        httpConf.addCustomizer(new SecureRequestCustomizer());

        ServerConnector serverConnector = new ServerConnector(server2,
            new SslConnectionFactory(sslContextFactory,"http/1.1"),
            new HttpConnectionFactory(httpConf)); // <-- use it!
        serverConnector.setPort(port);

        server2.setConnectors(new Connector[]
        { serverConnector });

        final WebAppContext root = new WebAppContext();
        root.setContextPath("/");
        root.setParentLoaderPriority(true);
        final String webappDirLocation = "src/main/webapp/";
        root.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
        root.setResourceBase(webappDirLocation);
        server2.setHandler(root);
        server2.start();
        server2.join();
    }
	
	public static Server startServer() throws Exception {
		
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
        	webPort = "8081";
        }
        
        final Server server = new Server(Integer.valueOf(webPort));
        final WebAppContext root = new WebAppContext();
        root.setContextPath("/");
        root.setParentLoaderPriority(true);
        final String webappDirLocation = "src/main/webapp/";
        root.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
        root.setResourceBase(webappDirLocation);
        server.setHandler(root);

        return server;
	}
        
	
}