package ohtu.Komennot;

import ohtu.IO;
import ohtu.Komento;
import ohtu.Sovelluslogiikka;

public class Nollaa implements Komento {
    private Sovelluslogiikka sovelluslogiikka;
    private IO io;
    private int edellinenArvo;

    public Nollaa(Sovelluslogiikka sovelluslogiikka, IO io) {
        this.sovelluslogiikka = sovelluslogiikka;
        this.edellinenArvo = 0;
        this.io = io;
    }


    @Override
    public void suorita() {
        this.edellinenArvo = io.getTuloskentta();
        io.setTuloskentta(0);
        io.setSyotekentta(0);
        sovelluslogiikka.nollaa();
    }

    @Override
    public void peru() {
        io.setTuloskentta(edellinenArvo);
    }


}
