package ohtu.Komennot;

import ohtu.IO;
import ohtu.Komento;
import ohtu.Sovelluslogiikka;

public class Plus implements Komento {
    private Sovelluslogiikka sovelluslogiikka;
    private IO io;
    private int edellinenArvo;

    public Plus(Sovelluslogiikka sovelluslogiikka, IO io) {
        this.sovelluslogiikka = sovelluslogiikka;
        this.io = io;
        this.edellinenArvo = 0;
    }

    @Override
    public void suorita() {
        this.edellinenArvo = io.getTuloskentta();
        this.sovelluslogiikka.plus(io.getSyotekentta());
        io.setTuloskentta(this.sovelluslogiikka.tulos());
    }

    @Override
    public void peru() {
        io.setTuloskentta(edellinenArvo);
        sovelluslogiikka.nollaa();
        sovelluslogiikka.plus(edellinenArvo);
    }
}
