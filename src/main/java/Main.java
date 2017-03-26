import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

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