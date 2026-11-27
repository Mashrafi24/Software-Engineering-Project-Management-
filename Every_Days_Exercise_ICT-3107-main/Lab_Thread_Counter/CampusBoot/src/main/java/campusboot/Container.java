package campusboot;
import campusboot.annotation.*;
import java.lang.reflect.*;
import java.util.*;
public class Container {
 private final Map<String,Object> beans = new LinkedHashMap<>();
 public void scan(Class<?>... classes) {
  for (Class<?> type : classes) {
   if (!type.isAnnotationPresent(Component.class)) {
    System.out.println("[Container] Skipped: " + type.getSimpleName() + " (no @Component)");
    continue;
   }
   String name = type.getAnnotation(Component.class).value().trim();
   if (name.isEmpty()) name = Character.toLowerCase(type.getSimpleName().charAt(0)) + type.getSimpleName().substring(1);
   try {
    Constructor<?> ctor = type.getDeclaredConstructor(); ctor.setAccessible(true);
    beans.put(name, ctor.newInstance());
    System.out.println("[Container] Registered: " + name + " (" + type.getSimpleName() + ")");
   } catch (ReflectiveOperationException e) { throw new IllegalStateException("Cannot create " + type.getName(), e); }
  }
 }
 public void injectValues(Map<String,String> config) {
  for (Object bean : beans.values()) for (Field field : bean.getClass().getDeclaredFields()) {
   Value annotation = field.getAnnotation(Value.class);
   if (annotation == null) continue;
   String value = config.get(annotation.value());
   if (value == null) throw new IllegalArgumentException("Missing configuration key: " + annotation.value());
   try {
    field.setAccessible(true);
    if (field.getType() == String.class) field.set(bean, value);
    else if (field.getType() == int.class || field.getType() == Integer.class) field.set(bean, Integer.parseInt(value));
    else throw new IllegalArgumentException("Unsupported @Value field type: " + field.getType().getName());
    System.out.println("[Container] Injected: " + annotation.value() + " -> " + bean.getClass().getSimpleName() + "." + field.getName());
   } catch (ReflectiveOperationException | NumberFormatException e) { throw new IllegalStateException("Injection failed: " + field.getName(), e); }
  }
 }
 public Object getBean(String name) { return beans.get(name); }
 public Map<String,Object> getBeans() { return new LinkedHashMap<>(beans); }
}
