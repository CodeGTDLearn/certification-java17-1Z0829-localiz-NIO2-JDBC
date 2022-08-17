import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class LocaleFormattingNumbers {

  public static void main(String[] args) throws ParseException {

    //    parseNumbers(Locale.GERMANY);
    testCustomNumberFormats(Locale.GERMANY);
  }

  public static void parseNumbers(Locale local) throws ParseException {

    double value = 10.55;

    String defaultDecimal = NumberFormat.getInstance()
                                        .format(value);

    System.out.println(
         MessageFormat
              .format("{0}({1}) -> parse -> {2}({3})",
                      Locale.getDefault(),
                      defaultDecimal,
                      local,
                      NumberFormat.getInstance(local)
                                  .parse(defaultDecimal)
                     ));
  }

  // Formatting Numbers using patterns
  public static void testCustomNumberFormats(Locale local) {

    double value = 5.5;
    DecimalFormat localFormat = (DecimalFormat) NumberFormat.getInstance(local);
    localFormat.applyPattern("###.###");

    //    System.out.println(
    //         MessageFormat
    //              .format("{0}({1}) : {2} ",
    //                      "Pattern(\"###.###\")",
    //                      local,
    //                      localFormat.format(value)));
    //
    //    System.out.println(
    //         MessageFormat
    //              .format("{0}({1}) : {2} ",
    //                      "Pattern(\"##.00\")",
    //                      local,
    //                      new DecimalFormat("##.00").format(value)));


    System.out.println(
         MessageFormat.format("{0}({1}) : {2, number, percent} ",
                              "{percent}  ",
                              local,
                              value));

    System.out.println(
         MessageFormat.format("{0}({1}) : {2, number, currency} ",
                      "{currency} ", local,
                      value));

    System.out.println(
         MessageFormat.format("{0}({1}) : {2, number, #.00} ",
                      "{#.00} ",
                      local,
                      value));

  }
}