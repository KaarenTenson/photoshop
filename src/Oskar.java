public class Oskar {

    /**
     *
     * @param pilt võtab sisendiks maatriksina esitatud pildi
     * @param intensiivsus võtab teiseks sisendiks ujukomaarvu vahemikus 4.5 kuni 5.2
     * @return tagastab teravustatud pildi
     */
    public static int[][] sharpen(int[][] pilt, double intensiivsus) { //sain koodi osas natuke inspiratsiooni internetist
        double[][] kernel = {{0, -1, 0}, {-1, intensiivsus, -1}, {0, -1, 0}}; //loob "kerneli" mille abil muudetakse pikslite väärtuseid
        int[][] teravustatudPilt = new int[pilt.length][pilt[0].length]; //loon uue massiivi kuhu hakkan teravustatud pilti salvestama

        for (int rida = 1; rida < pilt.length -1; rida++){ //käin kahemõõtmelise maatriksi piksli haaval läbi
            for (int veerg = 1; veerg < pilt[0].length -1 ; veerg++) {
                int sumR = 0;
                //kuna kernel on 3x3 maatriks, siis käime kõik pikslid sellega üle ja muudame vajadusel väärtuseid
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        sumR += pilt[rida + i][veerg + j] * kernel[i + 1][j + 1];
                    }
                }
                //pikslite väärtus saab olla 0-st 255-ni
                teravustatudPilt[rida][veerg] = Math.min(255, Math.max(0, sumR));
            }
        }
        return teravustatudPilt;
    }

    /**
     * Kood on inspireeritud "Programmeerimine 2" kursuse praktikumis tehtud tööst
     * @param pilt võtab sisendiks maatriksina esitatud pildi
     * @return tagastab vertikaalselt peegeldatud pildi
     */
    static int[][] peegeldamineVerikaalselt(int[][] pilt){
        int N=pilt.length,M=pilt[0].length;
        int[][] uus=new int[N][M]; //uus maatriks, kuhu salvestatakse uus pööratud pilt
        for(int rida=0;rida<N;rida++){ // käib läbi kõik read ja veerud ja muudab pildi pikslid vastavalt nii, et see oleks lõpuks vertikaalselt peegeldatud
            for(int veerg=0;veerg<M;veerg++){
                uus[rida][M-veerg-1]=pilt[rida][veerg];
            }
        }
        return uus;
    }
    /**
     * Kood on inspireeritud "Programmeerimine 2" kursuse praktikumis tehtud tööst
     * @param pilt võtab sisendiks maatriksina esitatud pildi
     * @return tagastab horisontaalselt peegeldatud pildi
     */
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
    /**
     * Kood on inspireeritud "Programmeerimine 2" kursuse praktikumis tehtud tööst
     * @param pilt võtab sisendiks maatriksina esitatud pildi
     * @return tagastab peadiagonaali suhtes peegeldatud pildi
     */
    static int[][] peegeldaminePeadiagonaal(int[][] pilt){
        return peegeldamineHorisontaalselt(peegeldamineVerikaalselt(pilt)); //rakendab nii horisontaalselt kui ka vertikaalselt peegeldamise meetodeid
    }
    /**
     * @param pilt võtab sisendiks maatriksina esitatud pildi
     * @return tagastab 90 kraadi päripäeva pööratud pildi
     */

    static int[][] pööra90kraadipp(int[][] pilt){
        int N=pilt.length,M=pilt[0].length;
        int[][] uus=new int[M][N]; //uus maatriks, kuhu salvestatakse uus pööratud pilt
        for(int rida=0;rida<N;rida++){ //käib läbi kõik read ja veerud ja muudab pildi pikslid vastavalt nii, et see oleks lõpuks 90 kraadi päripäeva pööratud
            for(int veerg=0;veerg<M;veerg++){
                uus[veerg][N-1-rida]=pilt[rida][veerg];
            }
        }
        return uus;
    }
    /**
     *
     * @param pilt võtab sisendiks maatriksina esitatud pildi
     * @return tagastab 90 kraadi vastupäeva pööratud pildi
     */
    static int[][] pööra90kraadivp(int[][] pilt){
        return pööra90kraadipp(pööra90kraadipp(pööra90kraadipp(pilt))); //rakendab 2 korda päripäeva pööramise funktsiooni
    }

    /**
     *
     * @param maatriks võtab sisendiks maatriksina esitatud pildi
     * @param arv võtab teiseks sisendiks ujukomaarvu mille järgi muudetakse resolutsiooni
     * @return tagastab muudetud resolutsiooniga pildi
     */
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
                 if (arv3<0) { //kui tehakse pildi väiksemaks
                    if(arv2==1||i==maatriks.length-1||maatriks[0].length-1==j){
                        if(j+veerubit<maatriks[0].length*0.5-1&&(i+(int)ridabit<maatriks.length*0.5-1&&ridabit%1==0)){
                        uusmaatriks[i+(int)(ridabit)][j+veerubit]=maatriks[i][j];
                        veerubit-=1;}
                        arv2=3;
                    }
                    arv2-=1;
                    
                } else{
                    if(arv3<1){ //kui tehakse pilti rohkem kui 2 korda suuremaks
                        for (int k = 0; k < 1/arv3+1; k++) {
                            for (int l = 0; l < 1/arv3+1; l++) {
                                if(j+l+veerubit<maatriks[0].length*arv-1&&(i+k+(int)ridabit<maatriks.length*arv-1)){
                                uusmaatriks[i+k+(int)ridabit][j+l+veerubit]=maatriks[i][j];}}
                            veerubit+=1;
                        }
                        veerubit-=1;
                    }
                    else {//kui tehakse pilti kaks kordfa suuremaks
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
