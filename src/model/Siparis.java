package model;
import java.util.List;

public class Siparis {
    private Pizza pizza;
    private String boyut;
    private String hamur;
    private List<Ingredient> ekstralar;
    private double fiyat;

    public Siparis(Pizza pizza, String boyut, String hamur, List<Ingredient> ekstralar, double fiyat) {
        this.pizza = pizza;
        this.boyut = boyut;
        this.hamur = hamur;
        this.ekstralar = ekstralar;
        this.fiyat = fiyat;
    } // constructer

    public double getFiyat() { return fiyat; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(pizza.getName());
        if (!ekstralar.isEmpty()) {
            sb.append(" - Ekstralar:\n");
            for (Ingredient ekstra : ekstralar) {
                sb.append("   • ").append(ekstra.getName()).append("\n");
            }
        }
        return sb.toString();
    }
}
