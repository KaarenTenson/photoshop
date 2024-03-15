import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

/**
 * Pilditöötluse abiklass
 * <p>
 * Pakub meetodeid piltide maatriksina sisse laadimiseks ja maatriksite piltideks salvestamiseks.
 * Maatriksi väärtused on int tüüpi vahemikust 0-255 ja tähistavad iga pildil oleva piksli esimese värvikanali väärtust.
 * Mustvalgete piltide puhul ongi vaid üks varvikanal, ning väärtus määrab piksli heleduse.
 * Värviliste piltide puhul on kanaleid rohkem ja saadud väärtused sõltuvad suuresti formaadist.
 */
public class Pilt { // Tõnis Hendrik Hlebnikov 2023

    /**
     * Laeb pildifaili maatriksiks
     * NB! värvilise pildi puhul laeb ainul esimese värvikanali andmed!
     *
     * @param tee failitee pildini
     * @return pildi kõrgus x laius int tüüpi maatriks
     */
    public static int[][] lae(String tee) {
        BufferedImage pilt;
        try {
            pilt = ImageIO.read(new File(tee));
        } catch (IOException e) {
            throw new RuntimeException("Faili " + new File(tee).getAbsolutePath() + " lugemine ebaõnnestus", e);
        }
        int kõrgus = pilt.getHeight();
        int laius = pilt.getWidth();
        int[][] piltMaatriksina = new int[kõrgus][laius];
        DataBuffer pildiAndmed = pilt.getRaster().getDataBuffer();
        SampleModel sampler = pilt.getSampleModel();
        for (int i = 0; i < kõrgus; i++) {
            for (int j = 0; j < laius; j++) {
                piltMaatriksina[i][j] = sampler.getSample(j, i, 0, pildiAndmed);
            }
        }
        return piltMaatriksina;
    }

    /**
     * Salvestab etteantud maatriksi mustvalgeks PNG tüüpi pildiks
     *
     * @param andmed int[][] maatriks pildi andmetega
     * @param tee    kaustatee ja failinimi kuhu salvestada
     */
    public static void salvesta(int[][] andmed, String tee) {
        int kõrgus = andmed.length;
        int laius = andmed[0].length;
        BufferedImage pildiAndmed = new BufferedImage(laius, kõrgus, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster pildiRaster = pildiAndmed.getRaster();
        for (int i = 0; i < kõrgus; i++) {
            for (int j = 0; j < laius; j++) {
                pildiRaster.setSample(j, i, 0, andmed[i][j]);
            }
        }
        try {
            ImageIO.write(pildiAndmed, "png", new File(tee));
        } catch (IOException e) {
            throw new RuntimeException("Pildifaili kirjutamine ebaõnnestus", e);
        }
    }
}