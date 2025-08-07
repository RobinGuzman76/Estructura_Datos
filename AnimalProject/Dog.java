public class Dog implements Animal {
    // Dog specific attributes
    private String breed;
    private String name;
    private int size;
    private int age;

    // Constructor
    public Dog(String breed, String name, int size, int age) {
        this.breed = breed;
        this.name = name;
        this.size = size;
        this.age = age;
    }

    // Implementation of eat() from Animal
    @Override
    public void eat() {
        System.out.println(name + " the " + breed + " dog of size " + size + " eats kibble and drinks water 🦴");
    }

    // Implementation of sleep() from Animal
    @Override
    public void sleep() {
        System.out.println(name + " the dog sleeps deeply and dreams of running");
    }

    // Implementation of makeSound() from Animal
    @Override
    public void makeSound() {
        System.out.println(name + " says: Woof woof! 🐶");
    }

    // Implementation of move() from Animal
    @Override
    public void move() {
        System.out.println(name + " runs happily in the park");
    }

    // Implementation of getSpecies() from Animal
    @Override
    public String getSpecies() {
        return "Dog";
    }

    // Implementation of getAge() from Animal
    @Override
    public int getAge() {
        return age;
    }

    // Unique methods
    public void wagTail() {
        System.out.println(name + " wags its tail happily");
    }

    public void fetch() {
        System.out.println(name + " brings the ball back");
    }
    // Método específico para Dog
    public void bark() {
        System.out.println(name + " barks loudly! Woof woof!");
    }
}
