package jug;

import net.codestory.http.WebServer;
import net.codestory.http.templating.ModelAndView;

public class Main {
  public static void main(String[] args) {
    new Main().start();
  }

  private void start() {
    new WebServer(routes -> {
      routes.get("/color/blue", () -> {
        blue();
        return ModelAndView.of("pass");
      });
      routes.get("/color/yellow", () -> {
        yellow();
        return ModelAndView.of("die");
      });
    }).start();
  }

  private void blue() {
    System.out.println("BLUE");
  }

  private void yellow() {
    System.out.println("YELLOW");
  }
}
