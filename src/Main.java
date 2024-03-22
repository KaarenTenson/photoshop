import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        while (true){
              // Create a Scanner object
            System.out.println("Enter username");

            String userName = myObj.nextLine();  // Read user input
            System.out.println("Username is: " + userName);  // Output user input
        }
    }
}