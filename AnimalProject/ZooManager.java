public class ZooManager {
    // Finds the oldest animal in the array
    public Animal findOldestAnimal(Animal[] animals) {
        if (animals == null || animals.length == 0) return null;
        Animal oldest = animals[0];
        for (Animal animal : animals) {
            if (animal.getAge() > oldest.getAge()) {
                oldest = animal;
            }
        }
        return oldest;
    }
    // Feeds all animals
    public void feedAllAnimals(Animal[] animals) {
        for (Animal animal : animals) {
            animal.eat();
        }
    }

    // Makes all animals sleep
    public void nightTime(Animal[] animals) {
        for (Animal animal : animals) {
            animal.sleep();
        }
    }

    // Morning routine: eat, move, make sound
    public void morningRoutine(Animal[] animals) {
        for (Animal animal : animals) {
            animal.eat();
            animal.move();
            animal.makeSound();
        }
    }
}
