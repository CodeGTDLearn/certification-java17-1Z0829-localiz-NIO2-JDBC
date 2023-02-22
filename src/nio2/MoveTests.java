package nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MoveTests {
  public static void main(String[] args) {

    try {
      Path p1 = Paths.get("testA\\testB\\testC\\testFile.txt");
      Path p2 = Paths.get("testA\\testB\\testFile.txt");
      Files.move(p1, p2, StandardCopyOption.REPLACE_EXISTING);

    }
    catch (IOException io) {
      System.out.println(io);
      io.getMessage();
    }
  }
}