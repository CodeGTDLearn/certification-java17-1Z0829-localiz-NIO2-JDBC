import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class LocaleFormattingDateTime {

  public static void main(String[] args) {

    Locale.setDefault(new Locale("en", "AU"));
    Date date = new Date();
    System.out.println("Date.toString() = " + date);

    // Use method to print date/time in different ways
    //    printDateMessageFormat(date);

//    printDateSimpleDateFormat(date);

        creatingDateTimeVariables(date);
  }

  public static void printDateMessageFormat(Date date) {
    //    System.out.println(
    //         MessageFormat.format("{0}  = {1, date}",
    //                              "date",
    //                              date));
    //
    //    System.out.println(
    //         MessageFormat.format("{0}  = {1, date, full}",
    //                              "date-full",
    //                              date));
    //
    //    System.out.println(
    //         MessageFormat.format("{0}  = {1, date, yyy-MMM-ddd}",
    //                              "date|yyy-MMM-ddd",
    //                              date));

    //    System.out.println(
    //         MessageFormat.format("{0}  = {1, time}",
    //                              "time",
    //                              date));
    //
    //    System.out.println(
    //         MessageFormat.format("{0}  = {1, time, short}",
    //                              "time, short",
    //                              date));
    //
    //    System.out.println(
    //         MessageFormat.format("{0}  = {1, time, h:mm a}",
    //                              "time - h:mm a",
    //                              date));
  }

  public static void printDateSimpleDateFormat(Date date) {
    DateFormat dateFormatter = new SimpleDateFormat("MM DD YY");
    System.out.println(
         MessageFormat
              .format("{0}  = {1}",
                      "SimpleDateFormat(\"MM DD YY\")",
                      dateFormatter.format(date)));

    dateFormatter = new SimpleDateFormat("MM dd YY");
    System.out.println(
         MessageFormat
              .format("{0}  = {1}",
                      "SimpleDateFormat(\"MM dd YY\")",
                      dateFormatter.format(date)));

    // a represents am/pm
    // zz represents time zone
    dateFormatter = new SimpleDateFormat("MM dd YY hh:mm a zz");
    // Setting time zone.
    dateFormatter.setTimeZone(TimeZone.getTimeZone("EST"));
    System.out.println(
         MessageFormat
              .format("{0}  = {1}",
                      "SimpleDateFormat(\"MM/dd/YY hh:mm zz\")",
                      dateFormatter.format(date)));
  }

  // Using LocalDateTime.of, LocalDate.of and LocalTime.of to create
  // Date/Time variables
  public static void creatingDateTimeVariables(Date date) {
    LocalDateTime specificDateTime =
         LocalDateTime.of(2020, Month.SEPTEMBER, 17,
                          10, 30);

    LocalDate specificDate = LocalDate.ofYearDay(2025, 182);

    DateTimeFormatter dformatter = DateTimeFormatter.ofPattern("yyy-MMM-dd", Locale.US);
    System.out.println(dformatter.format(specificDate));
    System.out.println(specificDateTime.format(DateTimeFormatter.ISO_WEEK_DATE));

    dformatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    System.out.println(dformatter.format(specificDateTime));

    dformatter = DateTimeFormatter.ISO_LOCAL_DATE;
    System.out.println(dformatter.format(specificDateTime));

  }
}