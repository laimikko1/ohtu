package ohtu;

import ohtu.Sovelluslogiikka;

import javax.swing.*;

public class IO {
    private Sovelluslogiikka sovelluslogiikka;
    private JTextField syotekentta;
    private JTextField tuloskentta;

    public IO(JTextField syotekentta, JTextField tuloskentta) {
        this.sovelluslogiikka = sovelluslogiikka;
        this.syotekentta = syotekentta;
        this.tuloskentta = tuloskentta;
    }


    public int getSyotekentta() {
        return Integer.parseInt(syotekentta.getText());
    }

    public int getTuloskentta() {
        return Integer.parseInt(tuloskentta.getText());
    }

    public void setTuloskentta(int tulos) {
        this.tuloskentta.setText("" + tulos);
    }

    public void setSyotekentta(int tulos) {
        this.syotekentta.setText("" + tulos);
    }
}
