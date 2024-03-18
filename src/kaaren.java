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
}
