package locale;

import java.util.Arrays;
import java.util.Locale;

public class LocaleReview {
  public static void main(String[] args) {

    System.out.println("Number of Locales: " +
                            Locale.getAvailableLocales().length);

    System.out.println("------Hong Kong Locales ------");
    Arrays.stream(Locale.getAvailableLocales())
          .filter((s) -> s.getCountry().equals("HK"))
          .forEach((s) -> System.out.println(s + " : " + s.getDisplayName()));

    System.out.println("------ Language only ------");
    Arrays.stream(Locale.getAvailableLocales())
          .filter((s) -> s.getCountry()
                          .length() == 0 && s.getLanguage()
                                             .length() > 0)
          .limit(7)
          .forEach((s) -> System.out.println(s + " : " +
                                                  s.getDisplayName()));

    System.out.println("------ Locales with variants ------");
    Arrays.stream(Locale.getAvailableLocales())
          .filter((s) -> s.getVariant()
                          .length() > 0)
          .forEach((s) -> System.out.println(s + " : " +
                                                  s.getDisplayName() + " : " + s.getVariant()));

    System.out.println("------ Locales with extensions ------");
    Arrays.stream(Locale.getAvailableLocales())
          //.filter((s) -> s.getCountry().equals("US"))
          .filter((s) -> s.getExtensionKeys()
                          .size() > 0)
          .forEach((s) -> System.out.println(s + " : " +
                                                  s.getDisplayName() + " : " + s.getExtensionKeys()));

    System.out.println("--------- Static fields for common Locales --------");

    System.out.println("Locale.ENGLISH = " + Locale.ENGLISH);
    System.out.println("Locale.FRENCH = " + Locale.FRENCH);
    System.out.println("Locale.FRANCE = " + Locale.FRANCE);
    System.out.println("Locale.CANADA = " + Locale.CANADA);
    System.out.println("Locale.CANADA_FRENCH = " + Locale.CANADA_FRENCH);
    System.out.println("--------------------------");

    System.out.println("Default Locale: " + Locale.getDefault());

    System.out.println("DisplayName of default locale = " +
                            Locale.getDefault()
                                  .getDisplayName());

    // User friendly Display Name can be displayed using a different locale
    System.out.println("DisplayName of default locale for locale.FRENCH = " +
                            Locale.getDefault()
                                  .getDisplayName(Locale.FRENCH));
  }
}