package ohtu.kivipaperisakset;

public class KPSTekoaly extends KPSPelikerta {

    private PeliTekoaly tekoaly;

    public KPSTekoaly(PeliTekoaly tekoaly) {
        this.tekoaly = tekoaly;
    }

    @Override
    protected String teeVastustajanSiirto(String ekansiirto) {
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);

        if (ekansiirto == null) {
            return tokanSiirto;
        }

        tekoaly.asetaSiirto(ekansiirto);
        return tokanSiirto;
    }
}
