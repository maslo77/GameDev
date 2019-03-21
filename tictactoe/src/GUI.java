import javax.swing.*;
import java.awt.*;

public class GUI  extends JFrame {
    private Container panel;
    private String NazwaGracza;
    private JButton[][] b;
    private boolean wygrany;
    private JMenuBar menuGry;
    private JMenu menu;
    private JMenuItem NowaGra;
    private JMenuItem wyjscie;

    public GUI() {
        private void InitGUI(){}
        public void wygrany()
        {
            for (int row = 0; row<3; row++)
                if (b[row][0]==b[row][1] && b[row][1]==b[row][2]) {
                    if (b[row][0]=='x')
                    else if (b[row][0]=='o') return -1;
                }

            for (int col = 0; col<3; col++)
                if (b[0][col]==b[1][col] && b[1][col]==b[2][col]) {
                    if (b[0][col]=='x') return 1;
                    else if (b[0][col]=='o') return -1;
                }

            if (b[0][0]==b[1][1] && b[1][1]==b[2][2]) {
                if (b[0][0]=='x') return 1;
                else if (b[0][0]=='o') return -1;
            }

            if (b[0][2]==b[1][1] && b[1][1]==b[2][0]) {
                if (b[0][2]=='x') return 1;
                else if (b[0][2]=='o') return -1;
            }

            return 0;
        }

        public void reset(){}
       {

        }
    }
}
