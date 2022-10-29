package nio2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FileIOExample {
  private static void writeRandomNumbersToFile(String fileName) throws IOException {

    Random r = new Random();
    String numbersList;

    try (FileWriter stream = new FileWriter(fileName)) {
      for (int i = 0; i < 100; i++) {
        numbersList = r.ints(25, 1, 100)
                       .mapToObj((s) -> String.valueOf(s))
                       .reduce("", (string, element) -> String.join(" ", string, element));
        System.out.println(numbersList);
        stream.write(numbersList + "\n");
      }
    }
  }

  private static List<Integer> readRandomNumbersFromFile(String fileName) throws IOException {

    List<Integer> numbers = new ArrayList<>();

    try (BufferedReader stream = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = stream.readLine()) != null) {
        Arrays.asList(line.split("\\s"))
              .forEach((s) -> {
                try {
                  numbers.add(Integer.parseInt(s));
                } catch (Exception e) {
                }
              });
      }
    }

    System.out.println("Total: " + numbers.size());
    return numbers;
  }

  private static void writeDataStream(String file, List<Integer> list) throws IOException {
    try (DataOutputStream outputStream =
              new DataOutputStream(new BufferedOutputStream(
                   new FileOutputStream(file)))
        )
    {
      list.forEach((s) -> {
        try {  outputStream.writeInt(s);  }
        catch (IOException e) { e.printStackTrace();  }  });
    }
  }

  private static List<Integer> readDataStream(String file) throws IOException {
    List<Integer> numbers = new ArrayList<>();

    try (DataInputStream inStream =
              new DataInputStream(
                   new BufferedInputStream(
                        new FileInputStream(file)))
        )
    {
       try {
         while (true) {  numbers.add(inStream.readInt());  }
       } catch (EOFException e) {       }
    }
    System.out.println("Total : " + numbers.size());
    return numbers;
  }

  public static void main(String[] args) throws IOException {

    writeRandomNumbersToFile("characterData.txt");
//    List<Integer> randomList =
//         readRandomNumbersFromFile("characterData.txt");
//    writeDataStream("binaryIOData.bin", randomList);
//    List<Integer> dataList =
//         readDataStream("binaryIOData.bin");
//    System.out.println("Are lists equal? " + randomList.equals(dataList));
  }
}