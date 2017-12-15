package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KPSPelikerta {
    private Scanner scanner;

    public KPSPelaajaVsPelaaja() {
        this.scanner = new Scanner(System.in);
    }


    @Override
    protected String teeVastustajanSiirto(String ekansiirto) {
        System.out.print("Toisen pelaajan siirto: ");
        return scanner.nextLine();

    }

}