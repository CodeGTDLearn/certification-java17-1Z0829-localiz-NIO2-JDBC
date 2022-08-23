package nio2;

import java.io.*;

class Pet implements Serializable {
  private String name;     private String type; private String type2 = "xxxx";

  Pet() { System.out.println("1) nio2.Pet NO-ARGS-constructor");     }

  Pet(String name, String type) {
    this.name = name;
    this.type = type;
    System.out.println("2) nio2.Pet ARGS-constructor");        }

  public String toString() {return "nio2.Pet: " + name + "|" + type;}   }

public class SerializationExample {
  public static void main(String[] args) throws IOException, ClassNotFoundException {
    String file = "Brandy.ser";

    Pet pet = new Pet("Brandy", "Dog");
    System.out.println("Original State: " + pet);
//    try (ObjectOutputStream serialize =
//              new ObjectOutputStream(
//                   new FileOutputStream(file))) { serialize.writeObject(pet);}

    Pet deserializedPet = null;
    try (ObjectInputStream deserialize =
              new ObjectInputStream(
                   new FileInputStream(file))) {
      try {deserializedPet = (Pet) deserialize.readObject();
          } catch (EOFException e) {    }                       }
    System.out.println("Deserialized State: " + deserializedPet);     }  }