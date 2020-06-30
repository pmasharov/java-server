import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServer {

  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(8080)) {
      System.out.println("Server started!");

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("Client connected!");
        try (
            BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter output = new PrintWriter(socket.getOutputStream())) {
          while (!input.ready())
            ;

          System.out.println();
          while (input.ready()) {
            System.out.println(input.readLine());
          }

          output.println("HTTP/1.1 200 OK");
          output.println("Content-Type: text/html; charset=utf-8");
          output.println();
          output.println(
              "<h1>Дратути</h1><h3>Хренак-хренак и готово</h3><input type='text' name='name' /><br><button name='button'>Жмак нах</button>");
          output.flush();

          System.out.println("Client disconnected!");
        }
      }
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}