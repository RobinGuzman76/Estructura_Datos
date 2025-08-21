public class TestMyArray {
    public static void main(String[] args) {
        MyArrayList myArray = new MyArrayList();
        myArray.add("Objeto1");
        myArray.add("Objeto2");
        myArray.add("Objeto3");
        myArray.add("Objeto4");
        myArray.add("Objeto5");
        myArray.add("Objeto6");
        myArray.add("Objeto7");
        myArray.add("Objeto8");
        myArray.add("Objeto9");
        myArray.add("Objeto10");
        System.out.println("Contenido del array: " + myArray);
        myArray.delete(4);
        System.out.println("Contenido del array después de eliminar el índice 4: " + myArray);

        System.out.println(myArray.get(6));
    }
}