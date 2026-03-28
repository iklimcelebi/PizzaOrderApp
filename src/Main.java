import ui.PizzaSelectionFrame;
public class Main {
    public static void main(String[] args) {
        // Swing arayüzü ana iş parçacığı dışında başlatılmalı
        javax.swing.SwingUtilities.invokeLater(() -> {
            new PizzaSelectionFrame().setVisible(true);
        });
        System.out.println("Uygulama başlatıldı çalışıyor mu");

    }
}
