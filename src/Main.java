import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int[][] pilt;
        Scanner myObj = new Scanner(System.in);
        int tegu;
        while (true){
              // Create a Scanner object
            System.out.println("Sisesta faili nimi:");
            String paht = myObj.nextLine();  // Read user input
            pilt=Pilt.lae(paht);
            tegu=myObj.nextInt();
            switch (tegu){
                case 1:
                    break;
            }
        }
    }
}