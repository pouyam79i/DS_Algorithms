import algorithm.Search;

public class Main {
    public static void main(String[] args) {

        int[] array = new int[]{
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        };

        System.out.println(
                Search.binarySearch(array, 1)
        );
    }
}
