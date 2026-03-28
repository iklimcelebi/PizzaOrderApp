package ui;
import model.*;
import javax.swing.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;


public class
PizzaSelectionFrame extends JFrame {
    private JList<Pizza> listPizzalar;
    private JLabel lblResim;
    private JTextArea txtMalzemeler;
    private DefaultListModel<String> sepetModel;
    private JList<String> listSepet;

    private JComboBox<String> cbBoyut;
    private JComboBox<String> cbHamur;

    // eKstralar yan urun
    private Ingredient patates = new Ingredient("Patates", 8);
    private Ingredient sogan = new Ingredient("Soğan Halkası", 8);
    private Ingredient mozarella = new Ingredient("Mozarella Stick", 8);

    // İçecekler
    private Ingredient ayran = new Ingredient("Ayran", 5);
    private Ingredient fanta = new Ingredient("Fanta", 7);
    private Ingredient iceTea = new Ingredient("Ice Tea", 6);
    private Ingredient gazoz = new Ingredient("Gazoz", 6);

    // İçecek kutuları
    private JCheckBox cbPatates, cbSogan, cbMozarella;
    private JCheckBox cbAyran, cbFanta, cbIceTea, cbGazoz;

    private SepetYonetici sepet = new SepetYonetici();

    public PizzaSelectionFrame() {
        setTitle("Pizza Seçimi");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        // Pizza listesi içeriği ve fiyatları
        Pizza[] pizzalar = {
                new Pizza("Sucuklu Pizza", Arrays.asList(new Ingredient("Sucuk", 7), new Ingredient("Kaşar", 6), new Ingredient("Domates Sosu", 6))),
                new Pizza("Akdeniz", Arrays.asList(new Ingredient("Zeytin",5), new Ingredient("Sucuk", 7),new Ingredient("Fesleğen", 7), new Ingredient("Domates",4), new Ingredient("Kaşar", 6), new Ingredient("Domates Sosu", 6))),
                new Pizza("Kavurmalı Pizza", Arrays.asList(new Ingredient("Kavurma", 8), new Ingredient("Biber",4), new Ingredient("Kaşar", 6), new Ingredient("Domates Sosu", 6))),
                new Pizza("Mantarlı Pizza", Arrays.asList(new Ingredient("Mantar", 5), new Ingredient("Sucuk",7), new Ingredient("Kaşar", 6), new Ingredient("Domates Sosu", 6))),
                new Pizza("Karidesli Pizza", Arrays.asList(new Ingredient("Karides", 10), new Ingredient("Kaşar", 6), new Ingredient("Domates Sosu", 6))),
                new Pizza("Turkish Pizza", Arrays.asList(new Ingredient("Pastırma", 8), new Ingredient("Sucuk",7),new Ingredient("Biber", 7),  new Ingredient("Kaşar", 6), new Ingredient("Domates Sosu", 6)))
        };

        listPizzalar = new JList<>(pizzalar);
        listPizzalar.setBounds(20, 20, 160, 250);
        add(listPizzalar);

        lblResim = new JLabel();
        lblResim.setBounds(200, 20, 200, 200);
        add(lblResim);

        txtMalzemeler = new JTextArea();
        txtMalzemeler.setEditable(false);
        txtMalzemeler.setBounds(200, 230, 200, 100);
        txtMalzemeler.setBorder(BorderFactory.createTitledBorder("Malzemeler"));
        add(txtMalzemeler);

        // Boyut seçimi kucuk orta buyuk ekstra buyuk
        JLabel lblBoyut = new JLabel("Boyut:");
        lblBoyut.setBounds(20, 280, 60, 25);
        add(lblBoyut);

        cbBoyut = new JComboBox<>(new String[]{"Küçük", "Orta", "Büyük", "Extra Büyük"});
        cbBoyut.setBounds(80, 280, 100, 25);
        add(cbBoyut);

        // Hamur seçimi klsik ince dubleks
        JLabel lblHamur = new JLabel("Hamur:");
        lblHamur.setBounds(20, 320, 60, 25);
        add(lblHamur);

        cbHamur = new JComboBox<>(new String[]{"Klasik", "İnce", "Dubleks"});
        cbHamur.setBounds(80, 320, 100, 25);
        add(cbHamur);

        // Ekstra yan urun kutucukları
        cbPatates = new JCheckBox(patates.toString());
        cbPatates.setBounds(420, 20, 150, 25);
        add(cbPatates);

        cbSogan = new JCheckBox(sogan.toString());
        cbSogan.setBounds(420, 50, 150, 25);
        add(cbSogan);

        cbMozarella = new JCheckBox(mozarella.toString());
        cbMozarella.setBounds(420, 80, 150, 25);
        add(cbMozarella);

        // İçecekler kutucukları
        cbAyran = new JCheckBox(ayran.toString());
        cbAyran.setBounds(420, 120, 150, 25);
        add(cbAyran);

        cbFanta = new JCheckBox(fanta.toString());
        cbFanta.setBounds(420, 150, 150, 25);
        add(cbFanta);

        cbIceTea = new JCheckBox(iceTea.toString());
        cbIceTea.setBounds(420, 180, 150, 25);
        add(cbIceTea);

        cbGazoz = new JCheckBox(gazoz.toString());
        cbGazoz.setBounds(420, 210, 150, 25);
        add(cbGazoz);

        // Sepet  içerik
        sepetModel = new DefaultListModel<>();
        listSepet = new JList<>(sepetModel);
        JScrollPane spSepet = new JScrollPane(listSepet);
        spSepet.setBounds(600, 20, 250, 350);
        spSepet.setBorder(BorderFactory.createTitledBorder("Sepet"));
        add(spSepet);

        // Sepete ekle butonu
        JButton btnSepeteEkle = new JButton("Sepete Ekle");
        btnSepeteEkle.setBounds(420, 260, 150, 30);
        add(btnSepeteEkle);

        // Siparişi tamamla butonu
        JButton btnSiparisiTamamla = new JButton("Siparişi Tamamla");
        btnSiparisiTamamla.setBounds(600, 390, 250, 30);
        add(btnSiparisiTamamla);

        // Pizza seçimi değiştiğinde malzemeler güncellensin
        listPizzalar.addListSelectionListener(e -> {
            Pizza secilen = listPizzalar.getSelectedValue();
            if (secilen != null) {

                // Malzemeleri alt alta gelmeli
                StringBuilder malzemeStr = new StringBuilder();
                for (Ingredient ing : secilen.getIngredients()) {
                    malzemeStr.append("- ").append(ing.getName()).append("\n");
                }
                txtMalzemeler.setText(malzemeStr.toString());
            }
        });

        // Sepete Ekle butonu
        btnSepeteEkle.addActionListener(e -> {
            Pizza secilen = listPizzalar.getSelectedValue();
            if (secilen == null) {
                JOptionPane.showMessageDialog(this, "Lütfen pizza seçiniz!");
                return;
            }
            // Hamur ve bpyut için;
            String boyut = (String) cbBoyut.getSelectedItem();
            String hamur = (String) cbHamur.getSelectedItem();

            List<Ingredient> ekstralar = new ArrayList<>();
            if (cbPatates.isSelected()) ekstralar.add(patates);
            if (cbSogan.isSelected()) ekstralar.add(sogan);
            if (cbMozarella.isSelected()) ekstralar.add(mozarella);
            if (cbAyran.isSelected()) ekstralar.add(ayran);
            if (cbFanta.isSelected()) ekstralar.add(fanta);
            if (cbIceTea.isSelected()) ekstralar.add(iceTea);
            if (cbGazoz.isSelected()) ekstralar.add(gazoz);

            double fiyat = pizzaFiyatHesapla(secilen, boyut, hamur, ekstralar);

            Siparis yeniSiparis = new Siparis(secilen, boyut, hamur, ekstralar, fiyat);
            sepet.siparisEkle(yeniSiparis);

            // Sepete eklemede listeyi güncelle
            sepetModel.addElement(yeniSiparis.toString());

            JOptionPane.showMessageDialog(this, "Sepete eklendi: " + secilen.getName());
        });

        // Siparişi Tamamla butonu
        btnSiparisiTamamla.addActionListener(e -> {
            if (sepet.getSiparisler().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Sepet boş!");
                return;
            }

            StringBuilder ozet = new StringBuilder("Sipariş Özeti:\n");
            for (Siparis s : sepet.getSiparisler()) {
                ozet.append("- ").append(s.toString()).append("\n");
            }
            ozet.append("\nToplam Tutar: ").append(sepet.toplamFiyat()).append("₺");

            JOptionPane.showMessageDialog(this, ozet.toString());
            sepet.sepetiTemizle();
            sepetModel.clear();
        });
    }

    private double pizzaFiyatHesapla(Pizza pizza, String boyut, String hamur, List<Ingredient> ekstralar) {
        double fiyat = 0;

        // Pizza malzeme fiyatları
        for (Ingredient i : pizza.getIngredients()) {
            fiyat += i.getPrice();
        }

        // Boyut fiyat farkı
        switch (boyut) {
            case "Küçük": fiyat += 10; break;
            case "Orta": fiyat += 15; break;
            case "Büyük": fiyat += 20; break;
            case "Extra Büyük": fiyat += 25; break;
        }

        // Hamur fiyat farkı
        switch (hamur) {
            case "Klasik": fiyat += 5; break;
            case "İnce": fiyat += 7; break;
            case "Dubleks": fiyat += 10; break;
        }

        // Ekstralar ve içecekler fiyatı
        for (Ingredient i : ekstralar) {
            fiyat += i.getPrice();
        }

        return fiyat;
    }
}
