package ohtu.Komennot;

import ohtu.IO;
import ohtu.Komento;
import ohtu.Sovelluslogiikka;

public class Miinus implements Komento {

    private IO io;
    private Sovelluslogiikka sovelluslogiikka;
    private int edellinenArvo;

    public Miinus(Sovelluslogiikka sovelluslogiikka, IO io) {
        this.sovelluslogiikka = sovelluslogiikka;
        this.edellinenArvo = 0;
        this.io = io;
    }


    @Override
    public void suorita() {
        this.edellinenArvo = io.getTuloskentta();
        sovelluslogiikka.miinus(io.getSyotekentta());
        io.setTuloskentta(sovelluslogiikka.tulos());


    }

    @Override
    public void peru() {
        io.setTuloskentta(edellinenArvo);
        sovelluslogiikka.nollaa();
        sovelluslogiikka.plus(edellinenArvo);
    }


}
