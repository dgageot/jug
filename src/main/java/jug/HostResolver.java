package jug;

import com.github.jknack.handlebars.ValueResolver;

import java.util.Map;
import java.util.Set;

public class HostResolver implements ValueResolver {
  @Override
  public Object resolve(Object context, String name) {
    return "host".equals(name) ? Host.get() : null;
  }

  @Override
  public Set<Map.Entry<String, Object>> propertySet(Object context) {
    return null;
  }
}
