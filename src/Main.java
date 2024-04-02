import java.util.Scanner;

public class Main {
    public static int salvestamisTingimus = 0;

    public static void main(String[] args) {
        int[][] pilt;
        Scanner myObj = new Scanner(System.in);
        int tegu;
        System.out.print("Sisesta faili nimi: ");
        String path = myObj.nextLine();
        pilt = Pilt.lae(path);
        while (true) {
            if (salvestamisTingimus == 1) {
                System.out.print("Sisestage uue pildi nimi: ");
                String nimi = myObj.nextLine();
                if (nimi.length() > 5) { // kontrollib kas sisestati failitüübi lõpp või mitte
                    String kasOnFailiTüüp = nimi.substring(nimi.length() - 5);
                    if (kasOnFailiTüüp.contains(".")) { } //ei tee midagi, kui on failitüübi lõpp, siis ei tee muudatusi
                    else {
                        nimi += ".png"; } } // kui pole failitüübi lõppu, siis automaatselt teeb .png failitüübiks
                else {
                    nimi += ".png"; }
                Pilt.salvesta(pilt, nimi);
                break;
            }
            System.out.println("Monteerimise valikud"); //annab kasutajale ülevaate võimalikest valikutest
            System.out.println("--------------------------");
            System.out.println("1 - brightness");
            System.out.println("2 - blur");
            System.out.println("3 - pixelate");
            System.out.println("4 - noise");
            System.out.println("5 - chaos");
            System.out.println("6 - sharpen");
            System.out.println("7 - peegelda");
            System.out.println("8 - pööra 90 kraadi");
            System.out.println("9 - muuda resolutsiooni");
            System.out.println("10 - pööra maatriksi kõiki elemente");
            System.out.print("Sisesta valik: ");
            try{ tegu = myObj.nextInt();}
            catch (java.util.InputMismatchException e) { //kui sisestati vigane valik, siis annab sellest kasutajale teada ja palub tal uuesti proovida
                System.out.print("Sisestasite vigase valiku. Proovige uuesti. ");
                break;
            }
            myObj.nextLine();
            switch (tegu) { //muudab pilti vastavalt kasutaja sisestatud arvule
                case 1:
                    System.out.println("Sisesta täisarv vahemikus -255 kuni 255: ");
                    int sisend = myObj.nextInt();
                    myObj.nextLine();
                    pilt=artur.brightness(pilt, sisend);
                    jätka(myObj);
                    break;
                case 2:
                    System.out.println("Sisesta ujukomaarv vahemikus 0 kuni 10: ");
                    double hägusus = myObj.nextDouble();
                    myObj.nextLine();
                    pilt=artur.blur(pilt, hägusus);
                    jätka(myObj);
                    break;
                case 3:
                    System.out.println("Sisesta täisarv vahemikus 2 kuni 50:");
                    int intesiivsus=myObj.nextInt();
                    myObj.nextLine();
                    pilt=kaaren.pixelate(pilt,intesiivsus);
                    jätka(myObj);
                    break;
                case 4:
                    System.out.println("Sisesta ujukomaarv vahemikus 0 kuni 1:");
                    int intesiivsusnoise=myObj.nextInt();
                    myObj.nextLine();
                    pilt=kaaren.noise(pilt,intesiivsusnoise);
                    jätka(myObj);
                    break;
                case 5:
                    System.out.println("Sisesta tükisuurus, mis soovitavalt vähemalt 10:");
                    int tükkisuurus=myObj.nextInt();
                    myObj.nextLine();
                    System.out.println("Sisesta tükkide arv:");
                    int tükkidearv=myObj.nextInt();
                    myObj.nextLine();
                    pilt=kaaren.kaos(pilt,tükkisuurus,tükkidearv);
                    jätka(myObj);
                    break;
                case 6:
                    System.out.println("Sisesta sharpening intensiivsus ujukomaarvuna vahemikus 4.5 kuni 5.2: ");
                    double intensiivus=myObj.nextDouble();
                    myObj.nextLine();
                    pilt = Oskar.sharpen(pilt, intensiivus);
                    jätka(myObj);
                    break;
                case 7:
                    System.out.println("Kas soovid antud pilti peegeldada horisontaalselt, vertikaalselt või peadiagonaali suhtes?");
                    System.out.println("Sisesta vastavalt kas horisontaalne, vertikaalne või peadiagonaal ");
                    String sõna = myObj.next();
                    myObj.nextLine();
                    if (sõna.equals("horisontaalne")){
                        pilt = Oskar.peegeldamineHorisontaalselt(pilt);
                    }
                    else if (sõna.equals("vertikaalne")){
                        pilt = Oskar.peegeldamineVerikaalselt(pilt);

                    }
                    else if (sõna.equals("peadiagonaal")){
                        pilt = Oskar.peegeldaminePeadiagonaal(pilt);
                    }
                    jätka(myObj);
                    break;
                case 8:
                    System.out.println("Kas soovid pilti pöörata vastu- või päripäeva?");
                    System.out.println("Sisesta vastavalt vastupäev või päripäev: ");
                    String sõna2 = myObj.next();
                    myObj.nextLine();
                    if (sõna2.equals("vastupäev")){
                        pilt = Oskar.pööra90kraadipp(pilt);
                        jätka(myObj);
                        break;
                    }
                    if (sõna2.equals("päripäev")) {
                        pilt = Oskar.pööra90kraadivp(pilt);
                        jätka(myObj);
                        break;
                    }
                case 9:
                    System.out.println("Resolutsiooni muutmiseks sisesta ujukomaarv, mis lõppeb 5-ga (nt 2,5) või täisarv vahemikus 0.5 kuni 4: ");
                    double arv=myObj.nextDouble();
                    myObj.nextLine();
                    pilt = Oskar.muudaresulutsiooni(pilt, arv);
                    jätka(myObj);
                    break;
                case 10:
                    System.out.println("Sisesta arv maatriksi elementide (pikslite) pööramiseks: ");
                    int arv2=myObj.nextInt();
                    myObj.nextLine();
                    pilt = Pööre.pööre(pilt, arv2);
                    jätka(myObj);
                    break;

            }

        }
    }
    public static void jätka(Scanner myObj) { //kontrollib, kas kasutaja soovib jätkata pildi töötlemisega või lõpetada
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