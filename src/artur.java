public class artur {
    public static int[][] brightness(int[][] maatriks, int sisend) {
        int[][] tulemus = new int[maatriks.length][maatriks[0].length];
        int maxValgus = 255;
        int minValgus = -255;
        if (sisend < -255 || sisend > 255) {
            System.out.println("Sisend peab olema vahemikus -255 kuni 255.");
            return maatriks; }
        sisend = Math.max(minValgus, Math.min(maxValgus, sisend));
        for (int i = 0; i < maatriks.length; i++) {
            for (int j = 0; j < maatriks[i].length; j++) {
                int muudetudEl = maatriks[i][j] + sisend;
                muudetudEl = Math.max(0, Math.min(maxValgus, muudetudEl));
                tulemus[i][j] = muudetudEl;
            }
        }
        return tulemus;
    }

    private static int[][] blur(int[][] maatriks, double sisend) {
        int kõrgus = maatriks.length;
        int laius = maatriks[0].length;
        int[][] tulemus = new int[kõrgus][laius];
        if (sisend < 0 || sisend > 10) {
            System.out.println("Sisend peab olema vahemikus 0 kuni 10.");
            return maatriks; }
        int raadius = (int) (sisend * 2);
        for (int i = 0; i < kõrgus; i++) {
            for (int j = 0; j < laius; j++) {
                int summa = 0;
                int loendur = 0;
                for (int k = -raadius; k <= raadius; k++) { // tuli abiks StackOverflow postitus: https://stackoverflow.com/questions/29295929/java-blur-image
                    for (int l = -raadius; l <= raadius; l++) {
                        int uusKõrgus = (i + k + kõrgus) % kõrgus;
                        int uusLaius = (j + l + laius) % laius;
                        summa += maatriks[uusKõrgus][uusLaius];
                        loendur++;
                    }
                }
                tulemus[i][j] = summa / loendur;
            }
        }
        return tulemus;
    }

    public static void main(String[] args) {
        int[][] pilt = Pilt.lae("lennart.png");
        int[][] hägusta = blur(pilt, 3);
        Pilt.salvesta(hägusta, "arturtest.png");
    }
}
