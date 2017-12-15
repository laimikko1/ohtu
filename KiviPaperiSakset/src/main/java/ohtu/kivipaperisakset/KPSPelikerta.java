package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KPSPelikerta {
    private Scanner scanner;
    private Tuomari tuomari;

    public KPSPelikerta() {
        this.scanner = new Scanner(System.in);
        this.tuomari = new Tuomari();
    }

    public void pelaa() {

        while (true) {
            System.out.print("Ensimmäisen pelaajan siirto: ");
            String ekanSiirto = scanner.nextLine();
            String tokanSiirto = teeVastustajanSiirto(ekanSiirto);

            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();


            if (!(onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto))) {
                break;
            }

        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);

    }


    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    protected abstract String teeVastustajanSiirto(String ekansiirto);

}
