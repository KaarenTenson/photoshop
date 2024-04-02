public class artur {
    /**
     *
     * @param maatriks - võtab sisendina pildimaatriksi
     * @param sisend - võtab sisendina kasutaja soovitud muudatuse vahemikus -255 kuni 255 (int tüüp)
     * @return - tagastab muudetud pildimaatriksi kuhu on rakendatud kasutaja soovitud tume/valge efekt
     */
    public static int[][] brightness(int[][] maatriks, int sisend) {
        int[][] tulemus = new int[maatriks.length][maatriks[0].length]; // loob tulemusmaatriksi, mis on sama suur kui sisestatud maatriks
        int maxValgus = 255; // määrab piirid
        int minValgus = -255;
        if (sisend < -255 || sisend > 255) { // kui on alla või ületab piiri, siis ei tee muudatusi
            System.out.println("Sisend peab olema vahemikus -255 kuni 255.");
            return maatriks; }
        sisend = Math.max(minValgus, Math.min(maxValgus, sisend)); // väärtus, mille järgi näha kui palju peab pikslit tumedamaks/heledamaks tegema
        for (int i = 0; i < maatriks.length; i++) {
            for (int j = 0; j < maatriks[i].length; j++) {
                int muudetudEl = maatriks[i][j] + sisend; // lisab väärtuse pikslile
                muudetudEl = Math.max(0, Math.min(maxValgus, muudetudEl));
                tulemus[i][j] = muudetudEl; // lisab piksli tulemusmaatriksi samale positsioonile
            }
        }
        return tulemus;
    }

    /**
     *
     * @param maatriks - võtab sisendina pildimaatriksi
     * @param sisend - võtab sisendina kasutaja soovitud muudatuse vahemikus 0 kuni 10 (double tüüp)
     * @return - tagastab muudetud pildimaatriksi kuhu on rakendatud kasutaja soovitud hägustamise efekt
     */
    public static int[][] blur(int[][] maatriks, double sisend) {
        int kõrgus = maatriks.length;
        int laius = maatriks[0].length;
        int[][] tulemus = new int[kõrgus][laius]; // tulemusmaatriks, mis on sama suur kui sisestatud maatriks
        if (sisend < 0 || sisend > 10) { // kui sisend on alla või ületab piiri, siis ei tee muudatusi
            System.out.println("Sisend peab olema vahemikus 0 kuni 10.");
            return maatriks; }
        int raadius = (int) (sisend * 2); // võtab raadiuse sellest kui palju hägustada
        for (int i = 0; i < kõrgus; i++) {
            for (int j = 0; j < laius; j++) {
                int summa = 0;
                int loendur = 0;
                for (int k = -raadius; k <= raadius; k++) { // tuli abiks StackOverflow postitus: https://stackoverflow.com/questions/29295929/java-blur-image
                    for (int l = -raadius; l <= raadius; l++) {
                        int uusKõrgus = (i + k + kõrgus) % kõrgus; // arvutab uue kõrguse ja laiuse
                        int uusLaius = (j + l + laius) % laius;
                        summa += maatriks[uusKõrgus][uusLaius];
                        loendur++;
                    }
                }
                tulemus[i][j] = summa / loendur; // lisab muudetud piksli tulemusmaatriksi samale positsioonile
            }
        }
        return tulemus;
    }

    public static void main(String[] args) { // Arturi test
        int[][] pilt = Pilt.lae("lennart.png");
        int[][] hägusta = blur(pilt, 3);
        Pilt.salvesta(hägusta, "arturtest.png");
    }
}
