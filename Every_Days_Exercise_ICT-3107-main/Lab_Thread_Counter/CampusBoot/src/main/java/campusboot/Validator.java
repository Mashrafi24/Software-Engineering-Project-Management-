package campusboot;
import campusboot.annotation.Range;
import java.lang.reflect.Field;
import java.util.*;
public class Validator {
 public static List<String> validate(Object obj) {
  List<String> errors = new ArrayList<>();
  if (obj == null) { errors.add("Object to validate must not be null"); return errors; }
  for (Field f : obj.getClass().getDeclaredFields()) {
   Range r = f.getAnnotation(Range.class);
   if (r == null || (f.getType() != int.class && f.getType() != Integer.class)) continue;
   try {
    f.setAccessible(true); int actual = f.getInt(obj);
    if (actual < r.min() || actual > r.max())
     errors.add(f.getName() + " - " + r.message() + " (actual: " + actual + ")");
   } catch (IllegalAccessException e) { throw new IllegalStateException(e); }
  }
  return errors;
 }
}
