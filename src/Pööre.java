public class Pööre {
    public static int leiakaugus(int rida, int veerg, int ridapikkus, int veerupikkus){
        // Otstarve: leida vähim kaugus äärest
        // Antud: rea ja veeru indeks, rea ja veeru pikkus
        // Tulemus: vähim kaugus äärest intina
        return Math.min(Math.min(rida,veerg),Math.min(ridapikkus-1-rida,veerupikkus-1-veerg));
    }
    public static int[] leialähimkülg(int rida, int veerg, int ridapikkus, int veerupikkus){
        // Otstarve: leida, mis küljele on elemnt kõige lähemal
        // Antud:  rea ja veeru indeks, rea ja veeru pikkus
        // Tulemus: tagastab kõik küljed, mis on kõige lähemal äärele 1 ülemine, 2 vasak, 3 alumine, 4 parem
        int[]küljed=new int[4]; //masiivi kõikide kõlgede jaoks
        int ideks=0;
        int kaugus=leiakaugus(rida,veerg,ridapikkus,veerupikkus); //kaugus äärtest
        if(rida==kaugus){ //vaatab, kas ülemine äär on kõige lähedamal
            küljed[ideks]=1;
            ideks+=1;
        }
        if(veerg==kaugus){ //vaatab, kas vasak äär on kõige lähedamal
            küljed[ideks]=2;
            ideks+=1;
        }
        if(veerupikkus-veerg-1==kaugus){ //vaatab, kas parem äär on kõige lähedamal
            küljed[ideks]=4;
            ideks+=1;
        }
        if(ridapikkus-rida-1==kaugus){ //vaatab, kas alumine äär on kõige lähedamal
            küljed[ideks]=3;
        }
        return küljed; //tagastab ääred masiivina
    }
    public static int[] arvutakoht(int rida, int veerg, int pikkus, int ridapikkus, int veerupikkus){
        // Otstarve: leida, uue koha indeksid elemdile, vastu päeva liikumisel
        // Antud:  rea ja veeru indeks, rea ja veeru pikkus, mitu sammu tehakse(pikkus)
        // Tulemus: tagastab elemendi uue koha indeksid
        int[] koordinaat=new int[2]; //masiivi uute kohtade salvestamise jaoks
        int[] külg=leialähimkülg(rida,veerg,ridapikkus,veerupikkus);
        int kaugus=leiakaugus(rida, veerg, ridapikkus, veerupikkus);
        if(külg[3]!=0){ //vaatab, kas elemendil on kõik küljed samal kaugel, ning kui on tagastab indeksid
            koordinaat[0]=rida;
            koordinaat[1]=veerg;
            return koordinaat;
        }
        int algus=Math.max(külg[0],külg[1]); //leiab külje, mida mööda edasi minna
        while (pikkus<0){
            if((külg[0]==1&&külg[1]==4)||(külg[1]==1&&külg[0]==4)){ //arvestab 1,4 äärega, sest max metood nendega ei tööta
                algus=1;
            }
            if(külg[0]==1&&külg[1]==2&&külg[2]==3&&veerg==kaugus){ //vaatab, kas element on äärde jõudnud riba puhul
                veerg=veerupikkus-1-kaugus;
            }
            else if(külg[0]==2&&külg[1]==4&&külg[2]==3&&rida==kaugus){ //vaatab, kas element on äärde jõudnud riba puhul
                rida=ridapikkus-1-kaugus;
            }
            else if ((külg[0]==1)&&(külg[1]==3||külg[2]==3)) { //vaatab, kas elemnt kuulub rippa
                veerg-=1;}
            else if ((külg[0]==2||külg[1]==2)&&(külg[1]==4||külg[2]==4)) { //vaatab, kas elemnt kuulub rippa
                rida-=1;
            }
            else{
                switch (algus){ //muudab indekseid vastavalt õigele äärele
                    case 1:
                        veerg-=1;
                        break;
                    case 2:
                        rida+=1;
                        break;
                    case 3:
                        veerg+=1;
                        break;
                    case 4:
                        rida-=1;
                        break;
                }}
            külg=leialähimkülg(rida,veerg,ridapikkus,veerupikkus); //leiab uue ääre, kus alustada
            algus=Math.max(Math.max(külg[0],külg[1]),külg[2]);
            pikkus++;
        }

        koordinaat[0]=rida; //tagastab tulemuse masiivina
        koordinaat[1]=veerg;
        return koordinaat;
    }
    public static int[] arvutakohtpos(int rida, int veerg, int pikkus, int ridapikkus, int veerupikkus){
        // Otstarve: leida, uue koha indeksid elemdile, päripäeva liikumisel
        // Antud:  rea ja veeru indeks, rea ja veeru pikkus, mitu sammu tehakse(pikkus)
        // Tulemus: tagastab elemendi uue koha indeksid
        int[] koordinaat=new int[2]; //sama eelmisele metoodile
        int[] külg=leialähimkülg(rida,veerg,ridapikkus,veerupikkus);
        int kaugus=leiakaugus(rida,veerg,ridapikkus,veerupikkus);
        int algus;
        if(külg[3]!=0){ //sama eelmisele metoodile
            koordinaat[0]=rida;
            koordinaat[1]=veerg;
            return koordinaat;
        }
        while (pikkus>0){ //sama eelmisele metoodile
            külg=leialähimkülg(rida,veerg,ridapikkus,veerupikkus); //leiab lähimad ääred
            if(külg[1]!=0&&külg[2]!=0){ //leiab koha, kust alustada elemendi indeksite muutmist
                algus=Math.min(Math.min(külg[0],külg[1]),külg[2]);}
            else if(külg[1]!=0){
                algus=Math.min(külg[0],külg[1]);
            }
            else{
                algus=külg[0];
            }
            if((külg[0]==1&&külg[1]==4)||(külg[1]==1&&külg[0]==4)){ //arvestab 1,4 ääre kokku sattumisega
                algus=4;
            }
            if(külg[0]==1&&külg[1]==4&&külg[2]==3&&veerg==veerupikkus-1-kaugus){ //leiab, kas on jõutud riba äärde
                veerg=kaugus;
            }
            else if(külg[0]==2&&külg[1]==4&&külg[2]==3&&rida==ridapikkus-1-kaugus){ //leiab, kas on jõutud riba äärde
                rida=kaugus;
            }
            else if (külg[0]==1&&(külg[1]==3||külg[2]==3)) { //liikumine ribas
                veerg+=1;}
            else if ((külg[0]==2||külg[1]==2)&&(külg[1]==4||külg[2]==4)) { //liikumine ribas
                rida+=1;}
            else{
                switch (algus){ //sama eelmisele metoodile
                    case 1:
                        veerg+=1;
                        break;
                    case 2:
                        rida-=1;
                        break;
                    case 3:
                        veerg-=1;
                        break;
                    case 4:
                        rida+=1;
                        break;
                }}
            pikkus--;}

        koordinaat[0]=rida; //sama eelmisele metoodile
        koordinaat[1]=veerg;
        return koordinaat;
    }

    public static int[][] pööre(int[][] a, int k){
        // Otstarve: pöörata maatriksi kõik elemdid vastu päeva või päripäeva
        // Antud:  maatriks ja mitu sammu selle elemnte pööratakse
        // Tulemus: pööratud elemtidega maatriks
        int[]kohad;
        int[][] tagastatav=new int[a.length][a[0].length];//uus masiiv tagastamiseks
        for (int i = 0; i < a.length; i++) { //täidab tagastava masiivi
            for (int j = 0; j < a[0].length; j++) {
                if(k<0){ //kontrollid, kas liigutakse päri või vastas päeva
                    kohad=arvutakoht(i,j,k, a.length,a[0].length); //leiab masiivi uue kohaga vastas päeva liikumisega
                }
                else{
                    kohad=arvutakohtpos(i,j,k, a.length,a[0].length); //leiab masiivi uue kohaga päripäeva liikumisega
                }
                tagastatav[kohad[0]][kohad[1]]=a[i][j];//lisab elemtid uute kohtadega masiivi
            }
        }
        return tagastatav;
    }
}
