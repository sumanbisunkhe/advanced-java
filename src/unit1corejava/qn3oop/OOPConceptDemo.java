package unit1corejava.qn3oop;

// Abstraction: abstract class
abstract class Animal {
    // Encapsulation: private data
    private String name;
    private int age;

    // Constructor
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Encapsulation: getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }

    // Abstraction: abstract method
    public abstract void sound();

    // Common method
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

// Inheritance: Dog inherits Animal
class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);
    }

    // Polymorphism: method overriding
    @Override
    public void sound() {
        System.out.println(getName() + " says: Woof Woof!");
    }
}

// Inheritance: Cat inherits Animal
class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
    }

    // Polymorphism: method overriding
    @Override
    public void sound() {
        System.out.println(getName() + " says: Meow Meow!");
    }
}

public class OOPConceptDemo {
    public static void main(String[] args) {

        // Creating objects
        Dog dog = new Dog("Tommy", 3);
        Cat cat = new Cat("Kitty", 2);

        // Encapsulation in action
        dog.setAge(4);
        cat.setName("Snowy");

        // Display details
        System.out.println("Dog Details:");
        dog.displayInfo();
        dog.sound();

        System.out.println("\nCat Details:");
        cat.displayInfo();
        cat.sound();

        // Polymorphism using parent reference
        System.out.println("\nPolymorphism Example:");
        Animal a1 = new Dog("Bruno", 5);
        Animal a2 = new Cat("Mimi", 1);

        a1.sound();
        a2.sound();
    }
}