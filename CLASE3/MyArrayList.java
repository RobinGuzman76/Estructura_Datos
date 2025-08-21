public class MyArrayList {
    private Object[] myArray;
    private int size;
    private static final int DEFAULT_SIZE = 10;

    public MyArrayList() {
        myArray = new Object[DEFAULT_SIZE];
        size = 0;
    }

    public void add(Object object) {
        if (size >= myArray.length) {
            resize();
        }
        myArray[size] = object;
        size++;
    }

    public void delete(int index) {
        for (int i = index; i < size; i++) {
            if (i + 1 < size) {
                myArray[i] = myArray[i + 1];
            } else {
                myArray[i] = null;
            }
        }
        size--;
    }

    public Object get(int index) {
        return this.myArray[index];
    }

    public String toString() {
        String returnString = "";
        for (int i = 0; i < myArray.length; i++) {
            returnString += myArray[i] + ",";
        }
        return returnString;
    }

    private void resize() {
        Object[] newArray = new Object[myArray.length * 2];
        for (int i = 0; i < myArray.length; i++) {
            newArray[i] = myArray[i];
        }
        myArray = newArray;
    }
}
