package ohtu.kivipaperisakset;

public class KPSPelisovellus {
    private PeliTekoaly tekoaly;

    public void luoKaksinpeli() {
        new KPSPelaajaVsPelaaja().pelaa();
    }

    public void luoTekoalyPeli() {
        new KPSTekoaly(new Tekoaly()).pelaa();
    }

    public void luoParempiTekoalyPeli() {
        new KPSTekoaly(new TekoalyParannettu(20)).pelaa();
    }

}
