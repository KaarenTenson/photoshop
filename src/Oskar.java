public class Oskar { // Jaan Janno 2023 - jaan.janno@ut.ee

    public static void main(String[] args) {
        // lisa pildifail "lennart.png" enne jooksutamist oma projekti juurkausta! (mitte src sisse!)
        int[][] pilt = Pilt.lae("C:\\Users\\manni\\IdeaProjects\\OOP_projekt\\lennart.png"); // loeme pildifailist arvumaatriksi.
        // iga element kirjeldab ühe pildi punkti ehk piksli heleduse.
        // anname pildi töötle meetodile argumendiks.
        int[][] uus = sharpen(pilt, 200);
        Pilt.salvesta(uus, "uus.png");
        // kahevärvilise tulemuse salvestame uude faili "uus.png", otsi juurkaustas!
    }


    public static int[][] sharpen(int[][] pilt, int intensiivsus) {
        int[][] kernel = {{0, -1, 0}, {-1, intensiivsus, -1}, {0, -1, 0}};
        int[][] teravustatudPilt = new int[pilt.length][pilt[0].length];

        for (int rida = 1; rida < pilt.length -1; rida++){
            for (int veerg = 1; veerg < pilt[0].length -1 ; veerg++) {
                int sumR = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        sumR += pilt[rida + i][veerg + j] * kernel[i + 1][j + 1];
                    }
                }
                teravustatudPilt[rida][veerg] = Math.min(255, Math.max(0, sumR));
            }
        }
        return teravustatudPilt;
    }


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
    public static int[][] muudaresulutsiooni(int[][] maatriks,double arv){
        int[][] uusmaatriks=new int[(int)(maatriks.length*arv)][(int)(maatriks[0].length*arv)];
        int arv2=1;
        double arv3=1/(arv-1);
        double ridabit=0;
        int veerubit=0;
        for (int i = 0; i < maatriks.length; i++) {
            veerubit=0;
            ridabit+=arv;
            for (int j = 0; j < maatriks[0].length; j++) {
                if(arv2<arv3){
                    arv2+=1;
                    uusmaatriks[i+(int)ridabit][j+veerubit]=maatriks[i][j];
                }
                else{
                    if(arv3<1){
                        for (int k = 0; k < 1/arv3; k++) {
                            for (int l = 0; l < 1/arv3; l++) {
                                uusmaatriks[i+k+(int)ridabit][j+l+veerubit]=maatriks[i][j];}
                            veerubit+=1;
                        }
                    }
                    else {
                        System.out.println(j+veerubit);
                        System.out.println(j);
                        System.out.println();
                        if(j+veerubit<maatriks[0].length&&(i+(int)ridabit<maatriks.length)){
                        uusmaatriks[i+(int)ridabit][j+veerubit]=maatriks[i][j];
                        uusmaatriks[i+(int)ridabit][j+veerubit+1]=maatriks[i][j];
                        uusmaatriks[i+(int)ridabit+1][j+veerubit+1]=maatriks[i][j];
                        uusmaatriks[i+(int)ridabit+1][j+veerubit]=maatriks[i][j];
                        veerubit+=1;}
                    }
                }
            }

        }
        return uusmaatriks;
    }

}
