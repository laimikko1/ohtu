package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class KauppaTest {
    Kauppa kauppa;
    Ostoskori ostoskori;
    Pankki pankki;

    Viitegeneraattori viite;
    Varasto varasto;
    Kirjanpito kirjanpito;

    @Before
    public void setUp() {
        ostoskori = mock(Ostoskori.class);
        pankki = mock(Pankki.class);
        varasto = mock(Varasto.class);
        viite = mock(Viitegeneraattori.class);

    }

    @Test
    public void kunOstetaanYksiTilisiirtoOikein() {
        setUpVarastoJaViite(new Boolean(false));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("Mikko", "123-456");

        verify(pankki).tilisiirto("Mikko", 1, "123-456", "33333-44455", 2);

    }

    @Test
    public void kunOstetaanKaksiEriTilisiirtoOikein() {
        setUpVarastoJaViite(new Boolean(false));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);

        kauppa.tilimaksu("Mikko", "123-456");

        verify(pankki).tilisiirto("Mikko", 1, "123-456", "33333-44455", 7);

    }


    @Test
    public void kunOstetaanKaksiSamaaTiliSiirtoOikein() {
        setUpVarastoJaViite(new Boolean(false));

        kauppa = new Kauppa(varasto, pankki, viite);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);

        kauppa.tilimaksu("Mikko", "123-456");

        verify(pankki).tilisiirto("Mikko", 1, "123-456", "33333-44455", 4);
    }

    @Test
    public void kunOstetaanKaksiEriJoistaToinenLoppuVainYksiVeloitetaan() {
        setUpVarastoJaViite(new Boolean(true));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);

        kauppa.tilimaksu("Mikko", "123-456");

        verify(pankki).tilisiirto("Mikko", 1, "123-456", "33333-44455", 2);
    }

    @Test
    public void aloitaAsiointiNollaaEdellisetOstoksetJaAntaaUudenViitteen() {
        setUpVarastoJaViite(new Boolean(false));
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);

        kauppa.tilimaksu("Mikko", "123-456");

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("Mikko", "123-456");
        verify(pankki).tilisiirto("Mikko", 2, "123-456", "33333-44455", 5);

    }

    @Test
    public void kunPoistetaanTuotePalautetaan() {
        Tuote t = new Tuote(1, "Porkkana", 2);

        when(viite.uusi()).thenReturn(1);
        when(varasto.haeTuote(1)).thenReturn(t);

        kauppa = new Kauppa(varasto, pankki, viite);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.poistaKorista(1);
        verify(varasto).palautaVarastoon(t);
    }

    private void setUpVarastoJaViite(Boolean onkoTyhja) {
        when(viite.uusi()).thenReturn(1)
                .thenReturn(2);

        when(varasto.saldo(1)).thenReturn(2);

        if (onkoTyhja) {
            when(varasto.saldo(2)).thenReturn(0);
        } else {
            when(varasto.saldo(2)).thenReturn(2);
        }

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Porkkana", 2));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "Maito", 5));
        kauppa = new Kauppa(varasto, pankki, viite);
    }


}
