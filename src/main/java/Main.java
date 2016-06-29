
import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	// log出力
	static final Logger logger = LoggerFactory.getLogger(Main.class);

	static final URI BASE_URI = UriBuilder.fromUri("http://localhost/").port(8090).build();

	public static void main(String[] args) throws IOException {

		logger.info("start server");

		try {
			HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI,
					ResourceConfig.forApplicationClass(RsResourceConfig.class));

			Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));

			System.in.read();

			logger.info("stop server");
			System.exit(0);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	public static class RsResourceConfig extends ResourceConfig {

		public RsResourceConfig() {

			packages("rs,provider");

		}
	}
}