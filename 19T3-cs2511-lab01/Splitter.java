import java.util.Scanner;

public class Splitter {

    public static void main(String[] args){
        System.out.println("Enter a sentence specified by spaces only: ");
        Scanner Sc = new Scanner(System.in);
        String line = Sc.nextLine();
        System.out.println(line);
        String [] array = line.split(" ");
        
        for (int i = 0; i < array.length; i++) {
        	System.out.print(array[i] + "\n");
        }
        Sc.close();
    }
}
