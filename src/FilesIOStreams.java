import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FilesIOStreams {
  public static void main(String[] args) {

//    Path testFile = Path.of("TestingFile.txt");
//    readingAFile(testFile);
      try {
          Path newFile = Path.of("NewFile.txt");
          writingAFile(newFile);
      } catch (IOException e) {
          System.out.println(e);
      }

  }

  private static void readingAFile(Path file) {
    String line = null;

    try (BufferedReader reader = new BufferedReader(new FileReader(file.toString())))
    { while ((line = reader.readLine()) != null) { System.out.println(line); } }
    catch (IOException io) {   System.out.println(io);   }


    try (InputStream in = Files.newInputStream(file);
         BufferedReader reader = new BufferedReader(new InputStreamReader(in)))
    {  while ((line = reader.readLine()) != null) {  System.out.println(line); } }
    catch (IOException io) {   System.out.println(io);   }


    try (BufferedReader reader = Files.newBufferedReader(file))
    { while ((line = reader.readLine()) != null) {  System.out.println(line); }}
    catch (IOException io) {   System.out.println(io);   }
  }


  private static void writingAFile(Path testFile) throws IOException {
    String line = "Hello Janet";
    String fileName = testFile.toString();

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
      writer.write(line);
      writer.newLine();   }
    catch (IOException io) {    System.err.println(io);    }


    byte data[] = line.getBytes();
    try (OutputStream out = new BufferedOutputStream(
         Files.newOutputStream(testFile, StandardOpenOption.CREATE)))
    {   out.write(data, 0, data.length);   }
    catch (IOException io) {   System.err.println(io);   }


    try (BufferedWriter out = Files.newBufferedWriter(testFile)) {
      out.write(line);
      out.newLine();   }
    catch (IOException io) {     System.out.println(io);    }     }    }