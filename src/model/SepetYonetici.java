package model;
import java.util.ArrayList;
import java.util.List;

public class SepetYonetici {
    private List<Siparis> siparisler = new ArrayList<>(); // yeni listte tanımı

    public void siparisEkle(Siparis s) {
        siparisler.add(s);
    }

    public List<Siparis> getSiparisler() {
        return siparisler;
    }

    public double toplamFiyat() {
        double toplam = 0;
        for (Siparis s : siparisler) {
            toplam += s.getFiyat();
        }
        return toplam;
    }

    public void sepetiTemizle() {
        siparisler.clear();
    }
}
