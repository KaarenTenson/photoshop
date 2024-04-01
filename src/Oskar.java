public class Oskar {

    public static void main(String[] args) {
        int[][] pilt = Pilt.lae("lennart.png"); // loeme pildifailist arvumaatriksi.
        int[][] uus = sharpen(pilt, 4.5);
        Pilt.salvesta(uus, "uus.png");
    }


    public static int[][] sharpen(int[][] pilt, double intensiivsus) {
        double[][] kernel = {{0, -1, 0}, {-1, intensiivsus, -1}, {0, -1, 0}};
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

    //
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
    //muudab pildi resulutsiooni (arv saab olla ainult täisarv või arv, mille %0.5=0)
    public static int[][] muudaresulutsiooni(int[][] maatriks,double arv){
        if(arv%1!=0&&arv>1){ //kontrollib, kas tegemist on ujukomaarvuga, mis ei ole väiksem kui 1
            System.out.println(arv);
            return muudaresulutsiooni(muudaresulutsiooni(maatriks,arv*2),0.5);//arvutab näiteks 1,5 suurendusega välja, nii et suurendab alguses 3 korda ning siis teeb 2 korda väiksemaks
        }
        int[][] uusmaatriks=new int[(int)(maatriks.length*arv)][(int)(maatriks[0].length*arv)]; //uus maatriksi, kuhu mahub uue relsulatsiooniga pildi pikslite andmed
        int arv2;
        double arv3=1/(arv-1);
        double ridabit=0;
        int veerubit;
        for (int i = 0; i < maatriks.length; i++) {
            veerubit=0;
            arv2=1;
            if(i!=0||arv==2){ridabit+=arv-1;}
            for (int j = 0; j < maatriks[0].length; j++) {
                 if (arv3<0) {
                    if(arv2==1||i==maatriks.length-1||maatriks[0].length-1==j){
                        if(j+veerubit<maatriks[0].length*0.5-1&&(i+(int)ridabit<maatriks.length*0.5-1&&ridabit%1==0)){
                        uusmaatriks[i+(int)(ridabit)][j+veerubit]=maatriks[i][j];
                        veerubit-=1;}
                        arv2=3;
                    }
                    arv2-=1;
                    
                } else{
                    if(arv3<1){
                        for (int k = 0; k < 1/arv3+1; k++) {
                            for (int l = 0; l < 1/arv3+1; l++) {
                                if(j+l+veerubit<maatriks[0].length*arv-1&&(i+k+(int)ridabit<maatriks.length*arv-1)){
                                uusmaatriks[i+k+(int)ridabit][j+l+veerubit]=maatriks[i][j];}}
                            veerubit+=1;
                        }
                        veerubit-=1;
                    }
                    else {
                        if(j+veerubit<maatriks[0].length*2-1&&(i+(int)ridabit<maatriks.length*2-1)){
                        uusmaatriks[i+(int)ridabit][j+veerubit]=maatriks[i][j];
                        uusmaatriks[i+(int)ridabit][j+veerubit+1]=maatriks[i][j];
                        uusmaatriks[i+(int)ridabit+1][j+veerubit+1]=maatriks[i][j];
                        uusmaatriks[i+(int)ridabit+1][j+veerubit]=maatriks[i][j];
                        veerubit+=1;
                        arv2=0;}
                    }
                }
            }

        }
        return uusmaatriks;
    }

}
