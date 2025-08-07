public class Bird implements Animal {
    // Bird specific attributes
    private String species;
    private double wingspan;
    private boolean canFly;
    private int age;

    // Constructor
    public Bird(String species, double wingspan, boolean canFly, int age) {
        this.species = species;
        this.wingspan = wingspan;
        this.canFly = canFly;
        this.age = age;
    }

    // Implementation of eat() from Animal
    @Override
    public void eat() {
        System.out.println(species + " eats seeds and insects.");
    }

    // Implementation of sleep() from Animal
    @Override
    public void sleep() {
        System.out.println(species + " sleeps perched on a branch.");
    }

    // Implementation of makeSound() from Animal
    @Override
    public void makeSound() {
        System.out.println(species + " sings a beautiful song.");
    }

    // Implementation of move() from Animal
    @Override
    public void move() {
        if (canFly) {
            System.out.println(species + " flies gracefully with a wingspan of " + wingspan + " meters.");
        } else {
            System.out.println(species + " walks or hops on the ground.");
        }
    }

    // Implementation of getSpecies() from Animal
    @Override
    public String getSpecies() {
        return species;
    }

    // Implementation of getAge() from Animal
    @Override
    public int getAge() {
        return age;
    }

    // Unique method
    public void layEgg() {
        System.out.println(species + " lays an egg.");
    }
    // Método específico para Bird
    public void fly() {
        if (canFly) {
            System.out.println(species + " flies high in the sky!");
        } else {
            System.out.println(species + " cannot fly.");
        }
    }
}
