package nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FilesManagement {
  public static void main(String[] args) {
    try {
      Path pathFile = Path.of("myfile1" + ".txt");

      // 1) Write bytes to file
      //    1.1) CREATING+WRITING if the file not exists
      //    1.2) TRUNCATE/REPLACE+WRITING if the file not exists
      Files.write(pathFile, "List Ingredients\n".getBytes(),
                  StandardOpenOption.CREATE
                 ,StandardOpenOption.TRUNCATE_EXISTING // REPLACE
//                 ,StandardOpenOption.APPEND
//                 ,StandardOpenOption.WRITE // OVER-WRITING
                 );

      Files.writeString(pathFile, "Ingredient One\n",
                        StandardOpenOption.APPEND );

      Files.writeString(pathFile, "Ingredient Two\n",
                        StandardOpenOption.APPEND);

    } catch (IOException io) {   System.out.println(io);   }   }   }