package nio2;

import java.io.FileWriter;

public class Test {
  public static void main(String[] args) throws Exception {

    var fw = new FileWriter("text.txt");
    //fw.write("hello");
    fw.close();
  }
}