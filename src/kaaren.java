public class kaaren {
    //argument: Maatriks pildi andmed maatriksina, Ratio:kui palju tehakse piksleid suuremaks
    //teeb alumised ja parempoolsed piksliks samaks vastavalt argumendile ratio
    //näiteks teeb ration neljaga järgmised neli alumist ja ülemist pikslit samaks
    //tagastab uue pildi maatriksina
    public static int[][] pixelate(int[][] maatriks, int ratio){
        int csum=1;//kontrollib, kas on piisavalt piksleid samaks tehtud
        int dsum=1; //csum on all ja paremal olevate pikslite läbikäimiseks
        int c=0;//piksli(see, mis asendab teisi) veeru indeks
        int d=0;//piksli(see, mis asendab teisi) rea indeks
        int[][] tagastatav=new int[maatriks.length][maatriks[0].length];
        for (int i = 0; i < tagastatav.length; i++) {
            for (int j = 0; j < tagastatav[0].length; j++) {
                if(csum==ratio||csum==tagastatav[0].length-c){
                    csum=1;
                    c=j;
                }
                if(dsum==ratio||dsum==tagastatav[0].length-d){
                    dsum=1;
                    d=i;
                }
                tagastatav[i][j]=maatriks[d][c];
                csum++;
            }
            dsum++;
        }
        return tagastatav;
    }
    //teeb pildile juurde suvalisi piksleid, selleks läbib tsükkel kõik maatriksi elemndid ja vastavalt intesiivsusle
    // ning Math.randomi tulemusele, kas lisab sinna vale või originaalse piksli
    public static int[][] noise(int[][] maatriks, double intensiivsus){
        for (int i = 0; i < maatriks.length; i++) {
            for (int j = 0; j < maatriks[0].length; j++) {
                if(Math.random()<intensiivsus){ //Kui Math.random on väiksem kui intensiivsus lisab pildile vale piksli
                    maatriks[i][j]=(int)(Math.random()*600);
                }
            }

        }
        return maatriks;
    }
    //võtab suvalise koha ja pikkuse pildi tüki jaoks
    public static int[][] votasuvaline(int[][] list, int[][] uus,int pikkus){
        int kohty= (int)((list.length-pikkus)*Math.random());
        int kohtx= (int)((list[0].length-pikkus)*Math.random());
        int kohtxtemp;
        int algusx= (int)((list[0].length-pikkus)*Math.random());
        int algusy= (int)((list.length-pikkus)*Math.random());
        for (int i = algusy; i <algusy+pikkus ; i++) {
            kohtxtemp=kohtx;
            for (int j = algusx; j < algusx+pikkus; j++) {
                uus[kohty][kohtxtemp]=list[i][j];
                kohtxtemp++;
            }
            kohty++;
        }
        return uus;
    }
    //võtab suvalise arvu tükke pildist suvalises kohast, suvalise suurusega ja lisab uute maatriksisse ning tagastab selle
    public static int[][] kaos(int[][] maatriks, int tukkisuurus, int tukkidearv){
        int[][] tagastatav=new int[maatriks.length][maatriks[0].length];
        int pikkus;
        for (int i = 0; i < tukkidearv; i++) {
            pikkus=(int)(tukkisuurus*Math.random());
            tagastatav=votasuvaline(maatriks,tagastatav,pikkus);

        }
        return tagastatav;
    }
}
