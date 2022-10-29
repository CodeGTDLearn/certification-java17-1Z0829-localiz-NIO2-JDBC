package nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PathMethods {
  public static void main(String[] args) {

    //            testNormalize();
    //            testRelativize();
    //    testResolve();
    //    testResolveSibling();
    //    showData("caracterData.txt");
        checkPath();
  }

  public static void checkPath() {

    final Path path = Paths.get("c:\\", "test.txt");
    System.out.println(path);

  }

  // Print Information about a Path object using path methods
  public static void showData(String pathName) {

    System.out.println("---- Path data for '" + pathName + "' ----");

    // Use the Paths.get factory method to obtain a Path object
    // and use toAbsolutePath method to get info
    Path path = Paths.get(pathName)
                     .toAbsolutePath();

    // Print out absolute path
    System.out.println("Absolute Path: " + path.toString() +
                            "   [toString()]");

    // Get filename, in this case, current directory
    System.out.println("File/Dir of Path object: " +
                            path.getFileName() + "   [getFileName()]");

    // Parent directory returned as a Path
    System.out.println("Parent Directory: " + path.getParent()
                            + "   [getParent()]");

    // Get part of the path, using names
    System.out.println("Getting subpath: " + path.subpath(0, 2)
                            + "   [subpath(0,2)]");

    // Number of sub directories in the path
    System.out.println("Number of Sub-Directories: " +
                            path.getNameCount() + "   [getNameCount()]");


    System.out.format("%s %n %s %n", "Root Tree (NameCount)", path.getRoot());
    for (int i = 0; i < path.getNameCount(); i++) {
      for (int j = 0; j <= i; j++) System.out.print("\t");
      System.out.println(path.getName(i) + " [" + i + "]");
    }

    System.out.format("%s %n %s %n", "Root Tree (iterator)", path.getRoot());
    int i = 0;
    for (Path treeNodeDirectory : path) {
      for (int j = 0; j <= i; j++) System.out.print("\t");
      System.out.println(treeNodeDirectory);
      i++;
    }
  }

  public static void testNormalize() {

    Path xpath = Path.of("a/../../../b/./../c");
    System.out.println("Normalize transforms \n\t" + xpath.toString()
                            + "\n to: \n\t" + xpath.normalize() + "\n---------------");

    xpath = Path.of("/a/../../../b/./../c");
    System.out.println("Normalize transforms \n\t" + xpath.toString()
                            + "\n to: \n\t" + xpath.normalize() + "\n---------------");
  }

  public static void testRelativize() {

    System.out.println("--- Results for the p1.relativize(p2) method ---");

    Path p1 = Path.of("LearningAcademyOrg/IOProject/out/production");
    Path p2 = Path.of("LearningAcademyOrg/ParallelStreams");

    // Relativize p1.relativize(p2)
    System.out.println("Relativize transforms \n\t"
                            + p1.toString() + "\n\t" + p2.toString()
                            + "\n to: \n\t" + p1.relativize(p2)
                            + "\n---------------");

    // Relativize p2.relativize(p1)
    System.out.println("Relativize transforms \n\t"
                            + p2.toString() + "\n\t" + p1.toString()
                            + "\n to: \n\t" + p2.relativize(p1)
                            + "\n---------------");

    p1 = Path.of("garden/fruit/apple");
    p2 = Path.of("pear");
    // Relativize p1.relativize(p2)
    System.out.println("Relativize transforms \n\t" +
                            p1.toString() + "\n\t" + p2.toString()
                            + "\n to: \n\t" + p1.relativize(p2) +
                            "\n---------------");

    // Relativize p2.relativize(p1)
    System.out.println("Relativize transforms \n\t"
                            + p2.toString() + "\n\t" + p1.toString()
                            + "\n to: \n\t" + p2.relativize(p1) +
                            "\n---------------");

    p1 = Path.of("/a/b");
    p2 = Path.of("a/b");
    try {
      // Relativize p1.relativize(p2)
      System.out.println("Relativize transforms \n\t" +
                              p1.toString() + "\n\t" + p2.toString()
                              + "\n to: \n\t" + p1.relativize(p2) +
                              "\n---------------");
    } catch (IllegalArgumentException ise) {
      System.out.println(ise);
    }
    try {
      // Relativize p2.relativize(p1)
      System.out.println("Relativize transforms \n\t" +
                              p2.toString() + "\n\t" + p1.toString()
                              + "\n to: \n\t" + p2.relativize(p1) +
                              "\n---------------");
    } catch (IllegalArgumentException ise) {
      System.out.println(ise);
    }
  }

  public static void testResolve() {

    // Set up test data
    Path pIOProject = Path.of("IOProject");
    String pLearning = "/LearningAcademyOrg";
    Path p2Learning = Path.of(pLearning);

    System.out.println("--- Results for the pIOProject.resolve(p2Learning) method ---");

    // If Path argument (other) is absolute, method returns passed arg
    System.out.println("resolve transforms \n\t"
                            + pIOProject.toString() + "\n\t" + p2Learning.toString()
                            + "\n to: \n\t" + pIOProject.resolve(p2Learning) +
                            "\n---------------");

    // If Path argument (other) does not have root component,
    // joins the given path to this path
    System.out.println("resolve transforms \n\t"
                            + p2Learning.toString() + "\n\t" + pIOProject.toString()
                            + "\n to: \n\t" + p2Learning.resolve(pIOProject) +
                            "\n---------------");

    // If Path argument (other) is empty path, method returns self reference.
    // Note that resolve accepts a String or Path, for other path
    pLearning = "";
    System.out.println("resolve transforms \n\t"
                            + pIOProject.toString() + "\n\tempty Path/String"
                            + "\n to: \n\t" + pIOProject.resolve(pLearning) +
                            "\n---------------");

    // Two relative paths..
    pLearning = "out/production";
    System.out.println("resolve transforms \n\t"
                            + pIOProject.toString() + "\n\t" + pLearning
                            + "\n to: \n\t" + pIOProject.resolve(pLearning) +
                            "\n---------------");
  }

  public static void testResolveSibling() {

    Path IoPRoject = Path.of("IOProject/src");

    String pLearning = "/LearningAcademyOrg";
    Path p2 = Path.of(pLearning);

    System.out.println(
         "--- Results for the IoPRoject.resolveSibling(p2) method ---");

    // If Path argument (other) is absolute, method returns passed arg
    System.out.println("1 resolveSibling transforms \n\t"
                            + IoPRoject.toString() + "\n\t" + p2.toString()
                            + "\n to: \n\t" + IoPRoject.resolveSibling(p2) +
                            "\n---------------");

    // If Path argument (other) does not have root component,
    // joins the given path to the parent's path
    p2 = Path.of("/LearningAcademyOrg/IOProject");
    System.out.println("2 resolveSibling transforms \n\t"
                            + p2.toString() + "\n\t" + IoPRoject.toString()
                            + "\n to: \n\t" + p2.resolveSibling(IoPRoject) +
                            "\n---------------");

    // If Path argument (other) is empty path, method returns
    // parent's path
    pLearning = "";
    System.out.println("3 resolveSibling transforms \n\t"
                            + IoPRoject.toString() + "\n\tempty Path/String"
                            + "\n to: \n\t" + IoPRoject.resolveSibling(pLearning) +
                            "\n---------------");

    // Two relative paths..
    pLearning = "out/production";
    System.out.println("4 resolveSibling transforms \n\t"
                            + IoPRoject.toString() + "\n\t" + pLearning
                            + "\n to: \n\t" + IoPRoject.resolveSibling(pLearning) +
                            "\n---------------");

    // Two relative path, parent of current Path is null.
    IoPRoject = Path.of("fruit");
    System.out.println("Parent = " + IoPRoject.getParent());
    pLearning = "fruit/apple";
    System.out.println("5 resolveSibling transforms \n\t"
                            + IoPRoject.toString() + "\n\t" + pLearning
                            + "\n to: \n\t" + IoPRoject.resolveSibling(pLearning) +
                            "\n---------------");
  }

  private static void getInformation(Path p) {

    System.out.println("isReadable: " + Files.isReadable(p));
    System.out.println("isWritable: " + Files.isWritable(p));
    System.out.println("isDirectory: " + Files.isDirectory(p));
    System.out.println("isExecutable: " + Files.isExecutable(p));
    System.out.println("isRegularFile: " + Files.isRegularFile(p));
    System.out.println("isSymbolicLink: " + Files.isSymbolicLink(p));

    try {
      System.out.println("size: " + Files.size(p));
      System.out.println("getLastModifiedTime: " + Files.getLastModifiedTime(p));

      LocalDateTime ldt =
           LocalDateTime.ofInstant(
                ((FileTime) Files.getAttribute(p, "lastAccessTime")).toInstant(),
                ZoneId.systemDefault()
                                  );

      System.out.println("lastAccessTime: " +
                              ldt.format(DateTimeFormatter.ofPattern("y-MM-dd:hh:mm:ss a")));
    } catch (IOException io) {
      io.printStackTrace();
    }
  }

  private static void copyAndMove() {

    try {
// Copy the testC directory+content to testD (testD will be created)
    Path source = Path.of("testA/testB/testC");
    Path target = Path.of("testA/testB/testD");
Path run1 = Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

// Copy testFile.txt from  testC to  testD, keeping the same filename
    source = Path.of("testA/testB/testC/testFile.txt");
    target = Path.of("testA/testB/testD/testFile.txt");
Path run2 = Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

// Copy from testC directory+content to testE (testE be created)
    source = Path.of("testA/testB/testC");
    target = Path.of("testA/testB/testE");
Path run3 = Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);

// Copy testFile.txt from testD to testE, changing the name
    source = Path.of("testA/testB/testD/testFile.txt");
    target = Path.of("testA/testB/testE/aNewFile.txt");
Path run4 = Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);

    } catch (IOException io) {
      System.out.println("A.  Unable to copy Directory to source: " + io);}

  }
}