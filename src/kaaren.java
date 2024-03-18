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
    public static int[][] noise(int[][] maatriks){
        for (int i = 0; i < maatriks.length; i++) {
            for (int j = 0; j < maatriks[0].length; j++) {
                if(Math.random()>0.8){
                    maatriks[i][j]=(int)(Math.random()*600);
                }
            }

        }
        return maatriks;
    }

    public static void main(String[] args) {
        int[][] pilt=Pilt.lae("lennart.png");
        pilt=Pööre.pööre(pilt,-500);
        Pilt.salvesta(pilt,"pilt.png");
    }
}
