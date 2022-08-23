package nio2;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class ConsoleExamples {
  public static void main(String[] args) throws IOException {
    Console console;
    LocalDateTime date = LocalDateTime.now();
    String name;

    if ((console = System.console()) != null) {
      console.printf("Interacting with user via Console\n");
      name = console.readLine("What is your name? ");
      console.writer().println("Your input is appreciated, " + name);
      console.format("Date is: %1$tF %1$tr", date);  }
    else {
      System.out.println("Interacting with user via standard input/output streams");
      System.out.println("What is your name? ");
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      name = reader.readLine();
      System.out.println("Your input is appreciated, " + name);
      System.out.println(String.format("Date is: %1$tF %1$tr", date));  }} }