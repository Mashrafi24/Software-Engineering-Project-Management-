package campusboot;
import campusboot.annotation.Route;
import java.lang.reflect.*;
import java.util.Map;
public class Router {
 public static String dispatch(Container c, String path) {
  for (Map.Entry<String,Object> entry : c.getBeans().entrySet())
   for (Method m : entry.getValue().getClass().getDeclaredMethods()) {
    Route r = m.getAnnotation(Route.class);
    if (r == null || !r.path().equals(path)) continue;
    if (m.getParameterCount() != 0 || m.getReturnType() != String.class)
     throw new IllegalStateException("@Route method must take no parameters and return String: " + m.getName());
    try { m.setAccessible(true); return (String)m.invoke(entry.getValue()); }
    catch (IllegalAccessException | InvocationTargetException e) { throw new IllegalStateException("Route invocation failed: " + path, e); }
   }
  return "404 Not Found: " + path;
 }
}
