public class kaaren {
    public static int[][] pixelate(int[][] maatriks, int ratio){
        int csum=1;
        int dsum=1;
        int c=0;
        int d=0;
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
    public static int[][] noise(int[][] maatriks, double intensiivsus){
        for (int i = 0; i < maatriks.length; i++) {
            for (int j = 0; j < maatriks[0].length; j++) {
                if(Math.random()>intensiivsus){
                    maatriks[i][j]=(int)(Math.random()*600);
                }
            }

        }
        return maatriks;
    }
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
    public static int[][] kaos(int[][] maatriks, int tukkisuurus, int tukkidearv){//võtab suvalise arvu tükke suvalises kohast, suvalise suurusega ja lisab uute maatriksisse
        int[][] tagastatav=new int[maatriks.length][maatriks[0].length];
        int pikkus;
        for (int i = 0; i < tukkidearv; i++) {
            pikkus=(int)(tukkisuurus*Math.random());
            tagastatav=votasuvaline(maatriks,tagastatav,pikkus);

        }
        return tagastatav;
    }

    public static void main(String[] args) {
        int[][] pilt=Pilt.lae("lennart.png");
        pilt= Oskar.muudaresulutsiooni(pilt,4.5);
        Pilt.salvesta(pilt,"pilt.png");
    }
}
