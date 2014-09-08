package jug;

import net.codestory.http.WebServer;
import net.codestory.http.templating.ModelAndView;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
  private final Logs logs;
  private final Executor executor;

  public Main() {
    this.logs = new Logs();
    this.executor = Executors.newSingleThreadExecutor();
  }

  public static void main(String[] args) {
    new Main().start();
  }

  private void start() {
    new WebServer(routes -> routes
        .addHandleBarsResolver(new HostResolver())
        .get("/color/blue", () -> {
          log("blue");
          return ModelAndView.of("pass");
        })
        .get("/color/yellow", () -> {
          log("yellow");
          return ModelAndView.of("die");
        })
    ).start();
  }

  private void log(String color) {
    System.out.println(color);

    executor.execute(() -> logs.insert(Host.get(), color));
  }
}
