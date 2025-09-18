package devops.demo.hello;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class HelloServer {
  public static void main(String[] args) throws Exception {
    int port = Integer.parseInt(System.getenv().getOrDefault("SERVER_PORT", "8080"));
    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    server.createContext("/", new HelloHandler());
    server.setExecutor(null);
    server.start();
    System.out.println("Server started on http://localhost:" + port);
  }

  static class HelloHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange ex) throws IOException {
      String html = "<!DOCTYPE html>\r\n"
      		+ "<html lang=\"en\">\r\n"
      		+ "<head>\r\n"
      		+ "    <meta charset=\"UTF-8\">\r\n"
      		+ "    <title>DevOps Demo</title>\r\n"
      		+ "    <style>\r\n"
      		+ "        body {\r\n"
      		+ "            font-family: Arial, sans-serif;\r\n"
      		+ "            background-color: #f9fafc;\r\n"
      		+ "            color: #333;\r\n"
      		+ "            text-align: center;\r\n"
      		+ "            padding: 50px;\r\n"
      		+ "        }\r\n"
      		+ "        h1 {\r\n"
      		+ "            color: #2a6592;\r\n"
      		+ "        }\r\n"
      		+ "        p {\r\n"
      		+ "            font-size: 18px;\r\n"
      		+ "        }\r\n"
      		+ "        .footer {\r\n"
      		+ "            margin-top: 40px;\r\n"
      		+ "            font-size: 14px;\r\n"
      		+ "            color: #777;\r\n"
      		+ "        }\r\n"
      		+ "    </style>\r\n"
      		+ "</head>\r\n"
      		+ "<body>\r\n"
      		+ "    <h1>Hello, DevOps!</h1>\r\n"
      		+ "    <p>Welcome to our first DevOps demo application.</p>\r\n"
      		+ "    <p>This page is generated to verify the build and deployment pipeline.</p>\r\n"
      		+ "    \r\n"
      		+ "    <div class=\"footer\">\r\n"
      		+ "        © DevOps Demo | Computer Science - Bo Guo\r\n"
      		+ "    </div>\r\n"
      		+ "</body>\r\n"
      		+ "</html>";
      byte[] bytes = html.getBytes(StandardCharsets.UTF_8);
      ex.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
      ex.sendResponseHeaders(200, bytes.length);
      try (OutputStream os = ex.getResponseBody()) { os.write(bytes); }
    }
  }
}

