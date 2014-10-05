package jug;

import net.codestory.http.WebServer;
import net.codestory.http.templating.ModelAndView;

public class Main {
    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        new WebServer(routes -> routes
                .addHandleBarsResolver(new HostResolver())
                .get("/color/blue", ModelAndView.of("pass"))
                .get("/color/yellow", ModelAndView.of("die"))
        ).start();
    }
}
