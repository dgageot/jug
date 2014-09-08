package jug;

import com.github.jknack.handlebars.ValueResolver;
import net.codestory.http.WebServer;
import net.codestory.http.templating.ModelAndView;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;

public class Main {
  public static void main(String[] args) {
    new Main().start();
  }

  private void start() {
    new WebServer(routes -> routes
        .addHandleBarsResolver(new HostResolver())
        .get("/color/blue", () -> {
          blue();
          return ModelAndView.of("pass");
        })
        .get("/color/yellow", () -> {
          yellow();
          return ModelAndView.of("die");
        })
    ).start();
  }

  private void blue() {
    System.out.println("BLUE");
  }

  private void yellow() {
    System.out.println("YELLOW");
  }

  static class HostResolver implements ValueResolver {
    private final String hostname = hostname();

    @Override
    public Object resolve(Object context, String name) {
      return "host".equals(name) ? hostname : null;
    }

    @Override
    public Set<Map.Entry<String, Object>> propertySet(Object context) {
      return null;
    }

    private static String hostname() {
      try {
        return InetAddress.getLocalHost().getHostName();
      } catch (UnknownHostException e) {
        return "<UNKNOWN>";
      }
    }
  }
}
