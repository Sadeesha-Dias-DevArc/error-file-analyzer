import javax.swing.JDialog;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.Timer;

public class ShowToastMessage {
    public static void toastMessage(String message, int duration) {
        JDialog dialog = new JDialog();
        dialog.setUndecorated(true);
        dialog.setLayout(new GridBagLayout());
        dialog.add(new JLabel(message));
        dialog.setSize(200, 100);
        dialog.setLocationRelativeTo(null);

        Timer timer = new Timer(duration, e -> dialog.setVisible(false));
        timer.setRepeats(false);
        timer.start();

        dialog.setVisible(true);
        
    }
    
}
