import java.util.Arrays;
import java.awt.Color;

public class NäitedPilt { // Jaan Janno 2023 - jaan.janno@ut.ee

    public static void main(String[] args) {
        // lisa pildifail "lennart.png" enne jooksutamist oma projekti juurkausta! (mitte src sisse!)
        int[][] pilt = Pilt.lae("C:\\Users\\manni\\IdeaProjects\\OOP_projekt\\lennart.png"); // loeme pildifailist arvumaatriksi.
        // iga element kirjeldab ühe pildi punkti ehk piksli heleduse.
        // anname pildi töötle meetodile argumendiks.
        int[][] uus = sharpen(pilt, 200);
        Pilt.salvesta(uus, "uus.png");
        // kahevärvilise tulemuse salvestame uude faili "uus.png", otsi juurkaustas!
    }

    private static int[][] sharpen(int[][] maatriks, double intensiivsus) {
        for (int i = 0; i < maatriks.length; i++) {
            for (int j = 0; j < maatriks[0].length; j++) {
                if(Math.random()>intensiivsus){
                    maatriks[i][j]=(int)(Math.random()*1000);
                }
            }

        }
        return maatriks;
    }

    /*/private static int[][] sharpen(int[][] pilt) {
        int[][] kernel = {{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
        int[][] teravustatudPilt = new int[pilt.length][pilt[0].length];

        for (int rida = 1; rida < pilt.length -1; rida++){
            for (int veerg = 1; veerg < pilt[0].length -1 ; veerg++) {
                int sumR = 0, sumG = 0, sumB = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        sumR += pilt[rida + i][veerg + j] * kernel[i + 1][j + 1];
                        sumG += pilt[rida + i][veerg + j] * kernel[i + 1][j + 1];
                        sumB += pilt[rida + i][veerg + j] * kernel[i + 1][j + 1];
                    }
                }
                teravustatudPilt[rida][veerg] = Math.min(255, Math.max(0, sumR));
            }
        }
        return teravustatudPilt;
    }/*/


    static int[][] peegeldamineVerikaalselt(int[][] pilt){
        int N=pilt.length,M=pilt[0].length;
        int[][] uus=new int[N][M];
        for(int rida=0;rida<N;rida++){
            for(int veerg=0;veerg<M;veerg++){
                uus[rida][M-veerg-1]=pilt[rida][veerg];
            }
        }
        return uus;
    }
    static int[][] peegeldamineHorisontaalselt(int[][] pilt){
        int N=pilt.length,M=pilt[0].length;
        int[][] uus=new int[N][M];
        for(int rida=0;rida<N;rida++){
            for(int veerg=0;veerg<M;veerg++){
                uus[N-rida-1][veerg]=pilt[rida][veerg];
            }
        }
        return uus;
    }
    static int[][] peegeldaminePeadiagonaal(int[][] pilt){
        return peegeldamineHorisontaalselt(peegeldamineVerikaalselt(pilt));
    }

    static int[][] pööra90kraadipp(int[][] pilt){
        int N=pilt.length,M=pilt[0].length;
        int[][] uus=new int[M][N];
        for(int rida=0;rida<N;rida++){
            for(int veerg=0;veerg<M;veerg++){
                uus[veerg][N-1-rida]=pilt[rida][veerg];
            }
        }
        return uus;
    }
    static int[][] pööra90kraadivp(int[][] pilt){
        return pööra90kraadipp(pööra90kraadipp(pööra90kraadipp(pilt)));
    }

}
