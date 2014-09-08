package jug;

import net.codestory.http.WebServer;

public class Main {
    public static void main(String[] args) {
        new WebServer(routes -> routes.catchAll("Hello World")).start();
    }
}
