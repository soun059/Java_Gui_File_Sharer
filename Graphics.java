import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Graphics{
    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                JFrame frame = new MainFrame("FileSharer");
                frame.setVisible(true);
            }
        });
    }
}
