public class Main {
    public static void main(String[] args) {
        System.out.println("=== Welcome to the Animal Kingdom! ===\n");
        
        // Creating specific animal objects
        Rabbit myRabbit = new Rabbit("white", 15.5, 2);
        Cat myCat = new Cat("Persian", 3);
        Dog myDog = new Dog("Golden Retriever", "Buddy", 5, 2); // Ajusta los parámetros según el constructor
        Bird myBird = new Bird("Parrot", 0.5, true, 1); // Ajusta los parámetros según el constructor
        
        // Part 1: Using objects with their specific types
        System.out.println("--- Part 1: Specific Animal Behaviors ---");
        
        System.out.println("Rabbit Actions:");
        myRabbit.eat();
        myRabbit.move();
        myRabbit.wiggleEars(); // Rabbit-specific method
        
        System.out.println("\nCat Actions:");
        myCat.eat();
        myCat.move();
        myCat.purr(); // Cat-specific method

        System.out.println("\nDog Actions:");
        myDog.eat();
        myDog.move();
        myDog.bark(); // Dog-specific method

        System.out.println("\nBird Actions:");
        myBird.eat();
        myBird.move();
        myBird.fly(); // Bird-specific method
        
        // Part 2: Demonstrating Polymorphism
        System.out.println("\n--- Part 2: Polymorphism Magic! ---");
        
        // Here's the magic: We can treat both as Animals!
        Animal animal1 = myRabbit;  // A Rabbit IS an Animal
        Animal animal2 = myCat;     // A Cat IS an Animal
        Animal animal3 = myDog;     // A Dog IS an Animal
        Animal animal4 = myBird;    // A Bird IS an Animal
        
        // We can call interface methods on both
        System.out.println("\nTreating them as generic Animals:");
        makeAnimalDoThings(animal1);
        makeAnimalDoThings(animal2);
        makeAnimalDoThings(animal3);
        makeAnimalDoThings(animal4);
        
        // Part 3: Array of different animals
        System.out.println("\n--- Part 3: Animal Array ---");
        
        Animal[] zoo = new Animal[8];
        zoo[0] = new Rabbit("brown", 12.0, 1);
        zoo[1] = new Cat("Siamese", 4);
        zoo[2] = new Rabbit("gray", 14.5, 5);
        zoo[3] = new Cat("Tabby", 2);
        zoo[4] = new Dog("Beagle", "Max", 3, 2);
        zoo[5] = new Bird("Sparrow", 0.5, true, 1);
        zoo[6] = new Dog("Bulldog", "Rocky", 4, 3);
        zoo[7] = new Bird("Eagle", 2.0, true, 3);
        
        System.out.println("Making all animals in the zoo make sounds:");
        for (Animal animal : zoo) {
            System.out.print(animal.getSpecies() + ": ");
            animal.makeSound();
        }
        
        // Part 4: Demonstrating type checking and casting
        System.out.println("\n--- Part 4: Type Checking ---");
        
        for (Animal animal : zoo) {
            System.out.println("\nChecking animal type:");
            if (animal instanceof Rabbit) {
                System.out.println("This is a Rabbit!");
                Rabbit rabbit = (Rabbit) animal;
                rabbit.dig();
            } else if (animal instanceof Cat) {
                System.out.println("This is a Cat!");
                Cat cat = (Cat) animal;
                if (cat.huntMouse()) {
                    System.out.println("The hunt was successful!");
                } else {
                    System.out.println("The mouse got away!");
                }
            } else if (animal instanceof Dog) {
                System.out.println("This is a Dog!");
                Dog dog = (Dog) animal;
            } else if (animal instanceof Bird) {
                System.out.println("This is a Bird!");
                Bird bird = (Bird) animal;
            }
        }
        }
        
    // Helper method that works with ANY Animal
    public static void makeAnimalDoThings(Animal animal) {
        System.out.println("\n" + animal.getSpecies() + " daily routine:");
        animal.eat();
        animal.move();
        animal.makeSound();
        animal.sleep();
    }
}