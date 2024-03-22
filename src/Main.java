import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static int salvestamisTingimus = 0;

    public static void main(String[] args) {
        int[][] pilt;
        Scanner myObj = new Scanner(System.in);
        int tegu;
        System.out.println("Sisesta faili nimi:");
        String path = myObj.nextLine();  // Read user input
        pilt = Pilt.lae(path);
        while (true) {
            if (salvestamisTingimus == 1) {
                System.out.println("Sisestage uue pildi nimi: ");
                String nimi = myObj.nextLine();
                nimi += ".png";
                Pilt.salvesta(pilt, nimi);
                break;
            }
            System.out.println("Monteerimise valikud");
            System.out.println("--------------------------");
            System.out.println("1 - brightness");
            System.out.println("2 - blur");
            System.out.println("3 - pixelate");
            System.out.println("4 - noise");
            System.out.println("5 - chaos");
            System.out.println("6 - sharpen");
            System.out.println("7 - peegelda");
            System.out.println("8 - keera");
            System.out.println("Sisesta valik: ");
            tegu = myObj.nextInt();
            myObj.nextLine();
            switch (tegu) {
                case 1:
                    System.out.println("Sisesta täisarv vahemikus -255 kuni 255: ");
                    int sisend = myObj.nextInt();
                    myObj.nextLine();
                    pilt=artur.brightness(pilt, sisend);
                    jätka(myObj);
                    break;
                case 2:
                    System.out.println("aaa");
                    break;
                case 3:
                    System.out.println("Sisesta intensiivsus");
                    int intesiivsus=myObj.nextInt();
                    myObj.nextLine();
                    pilt=kaaren.pixelate(pilt,intesiivsus);
                    jätka(myObj);
                    break;
                case 4:
                    System.out.println("Sisesta intensiivsus");
                    int intesiivsusnoise=myObj.nextInt();
                    pilt=kaaren.noise(pilt,intesiivsusnoise);
                    jätka(myObj);
                    break;
                case 5:
                    System.out.println("Sisesta tükkisuurus");
                    int tükkisuurus=myObj.nextInt();
                    myObj.nextLine();
                    System.out.println("sisesta tükkide arv");
                    int tükkidearv=myObj.nextInt();
                    myObj.nextLine();
                    pilt=kaaren.kaos(pilt,tükkisuurus,tükkidearv);
                    jätka(myObj);
                    break;
            }

        }
    }
    public static void jätka(Scanner myObj) {
        while (true) {
            System.out.println("Kas soovid jätkata (jah/ei)?");
            String tingimus = myObj.nextLine();
            if (tingimus.equals("jah")) {
                break;
            } else if (tingimus.equals("ei")) {
                salvestamisTingimus++;
                break;
            }
        }
    }
}