/**
 *program that prints command line argument
 *
 */

class Printargs {
	public static void main (String[] args) {
		System.out.println("Method 1 - normal for loop");
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
		System.out.println("Method 2 - foreach loop");
		for (String s : args) {
        	System.out.println(s);
        }
		System.out.println("Method 3 - normal while loop");
		int i = 0;
		while (i < args.length) {
			System.out.println(args[i]);
			i++;
		}
		// a do while loop executes first, then checks the condition
	}
}
