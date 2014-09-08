package jug;

import com.google.common.base.Supplier;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.google.common.base.Suppliers.memoize;

public class Host {
  private static final Supplier<String> hostname = memoize(Host::hostname);

  public static String get() {
    return hostname.get();
  }

  private static String hostname() {
    try {
      return InetAddress.getLocalHost().getHostName();
    } catch (UnknownHostException e) {
      return "<UNKNOWN>";
    }
  }
}
