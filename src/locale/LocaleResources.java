package locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleResources {
  public static void main(String[] args) {
    Locale.setDefault(new Locale("en", "AU"));
    testProperties(Locale.getDefault());     }

  public static void testProperties(Locale locale) {
    ResourceBundle bundle = ResourceBundle.getBundle("firstBundle", locale);

    System.out.println("\tInternat: 'yes': "+ bundle.getString("yes") +  locale );
    System.out.println("\t---Key/Values in properties file---");

    // Iterate through keys and do something
    bundle
         .keySet()
         .forEach((s) -> System.out.println("\t" + s + ":" + bundle.getObject(s)));  } }