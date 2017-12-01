
package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla.
    private int poistettavanIndeksi;

    public IntJoukko() {
        ljono = new int[KAPASITEETTI];
        alustaTaulukko();
        alustaMuuttujat();

    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        ljono = new int[kapasiteetti];
        alustaTaulukko();
        alustaMuuttujat();

    }

    private void alustaMuuttujat() {
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
        this.poistettavanIndeksi = -1;
    }


    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 && kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetti ja kasvatuskoko eivät voi olla pienempiä kuin 0");//heitin vaan jotain :D
        }
        ljono = new int[kapasiteetti];
        alustaTaulukko();
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    private void alustaTaulukko() {
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            josEnemmanAlkioitaKuinTilaa();
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            return true;
        }
        return false;
    }

    private void josEnemmanAlkioitaKuinTilaa() {
        if (alkioidenLkm + 1 > ljono.length) {
            ljono = Arrays.copyOf(ljono, ljono.length + kasvatuskoko);
        }
    }


    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (ljono[i] == luku) {
                this.poistettavanIndeksi = i;
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        if (!kuuluu(luku)) {
            return false;
        }
        alustaUudelleen();
        return true;
    }

    private void alustaUudelleen() {
        int[] uusi = new int[ljono.length - 1];
        System.arraycopy(ljono, 0, uusi, 0, poistettavanIndeksi);
        System.arraycopy(ljono, poistettavanIndeksi + 1, uusi, poistettavanIndeksi, ljono.length - poistettavanIndeksi - 1);
        ljono = uusi;
        alkioidenLkm--;
    }


    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        int[] toString = new int[alkioidenLkm];
        for (int i = 0; i < ljono.length; i++) {
            if (ljono[i] != 0) {
                toString[i] = ljono[i];
            }
        }
        return korvaaSulkeet(Arrays.toString(toString));
    }

    private String korvaaSulkeet(String stringi) {
        stringi = stringi.replace("[", "{");
        stringi = stringi.replace("]", "}");
        return stringi;
    }


    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }


    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        int[] uusi = new int[a.ljono.length + b.ljono.length];
        System.arraycopy(a.ljono, 0, uusi, 0, a.ljono.length);
        System.arraycopy(b.ljono, 0, uusi, a.ljono.length, b.ljono.length);
        IntJoukko j = new IntJoukko();
        j.ljono = uusi;
        return j;
    }

    private void setLjono(int[] uusi) {
        ljono = uusi;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        for (int alkio : a.ljono) {
            if (b.kuuluu(alkio))
                y.lisaa(alkio);
        }
        return y;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        for (int i = 0; i < b.ljono.length; i++) {
            if (!b.kuuluu(a.ljono[i]))
                z.lisaa(a.ljono[i]);
        }
        return z;
    }

}