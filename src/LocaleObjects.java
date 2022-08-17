import java.util.Locale;

public class LocaleObjects {
    public static void main(String[] args) {

        // Locale constructors
        System.out.println("Locale(fr) = " + new Locale("fr"));
        System.out.println("Locale(FR) = " + new Locale("FR"));
        System.out.println("Locale(fr,CA) = " + new Locale("fr", "CA"));
        System.out.println("Locale('',CA) = " + new Locale("", "CA"));

        // Locale Constants
        Locale l = Locale.CANADA_FRENCH;
        System.out.println("Locale.CANADA_FRENCH = " + l);
        l = Locale.US;
        System.out.println("Locale.US = " + l);

        // Locale.Builder()
        // Allow 'empty string' as language.
        // Order does not matter
        l = new Locale.Builder().setRegion("").build();
        System.out.println("Builder().setRegion('') = " + l);

        l = new Locale.Builder()
                .setLanguage("fr")
                .setRegion("CA")
                .build();
        System.out.println("Builder().setRegion(CA).setLanguage(fr) = " + l);

        // You can do this, but why would you?
        l = new Locale.Builder()
                .setRegion("CA")
                .setLanguage("fr")
                .setRegion("FR")
                .build();
        System.out.println("Builder().setRegion(CA)." +
                "setLanguage(fr).setRegion(FR) = " + l);

        // Returns a locale for the specified IETF BCP 47 language
        // tag string.  Introduced with JDK 7
        System.out.println("Locale.forLanguageTag(\"fr-CA\") =" +
                Locale.forLanguageTag("fr-CA"));

        System.out.println("Locale.forLanguageTag(\"es-SP\") =" +
                Locale.forLanguageTag("en_US"));

        l = new Locale("en", "U&S");
        System.out.println("Contructor: " + l);


        l = Locale.forLanguageTag("en-U&S");
        System.out.println("forLanguageTag: " + l);


        l = new Locale.Builder().setRegion("U&S")
                                .setLanguage("en")
                                .build();
        System.out.println("Builder: " + l);

    }
}