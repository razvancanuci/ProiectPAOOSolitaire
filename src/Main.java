import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main
{
    /**
     * Main.java
     * The main class of this program
     * The Main class is the driver class for Solitaire.
     * It initializes the game frame and panel and initially paints the screen.
     */
    private static JFrame    gameFrame;
    private static GamePanel gamePanel;

    private static final int FRAME_WIDTH  = 800;
    private static final int FRAME_HEIGHT = 700;

    /**
     * The buttons of this game
     */
    private static JButton showRulesButton;
    private static JButton selectLevel1Button;
    private static JButton selectLevel2Button;

    private static class ShowRules implements ActionListener
    {
        @Override
        /**
         * if the showRulesButton is pushed, There will be opened a new window with the rules wirtten
         */
        public void actionPerformed(ActionEvent e)
        {
            JDialog ruleFrame = new JDialog(gameFrame, true);
            ruleFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            ruleFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT-500);
            JScrollPane scroll;
            JEditorPane rulesTextPane = new JEditorPane("text/html", "");
            rulesTextPane.setEditable(false);
            String rulesText="Pentru a castiga acest joc trebuie mutate toate cartile in cele patru spatii goale. Fiecare pereche are cate un spatiu rezervat pentru toate cartile: A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q , K. Trebuie completate toate perechiile: trefla (♣), romb (♦), inima (♥) si frunza (♠)." +
                    "<br><br> Cateva reguli legate de joc:<br>" +
                    " - jocul porneste automat cu nivelul 1.<br>" +
                    " - daca se doreste resetarea, doar apasati butonul nivelului pe care doriti sa il jucati.<br>" +
                    " - cand scorul ajunge la 0, ati cam pierdut jocul.<br>";
            rulesTextPane.setText(rulesText);
            ruleFrame.add(scroll = new JScrollPane(rulesTextPane));
            ruleFrame.setVisible(true);
        }
    }

    private static class SelectLevel1 implements ActionListener
    {
        @Override
        /**
         * if the selectLevel1Button is pushed, there will be initialized a new game at level 1
         */
        public void actionPerformed(ActionEvent e)
        {
            gameFrame.dispose();
            loadGame("level1");
        }
    }
    private static class SelectLevel2 implements ActionListener
    {
        @Override
        /**
         * if the selectLevel1Button is pushed, there will be initialized a new game at level 2
         */
        public void actionPerformed(ActionEvent e)
        {
            gameFrame.dispose();
            loadGame("level2");
        }
    }
    /**
     * Initializes game objects
     */
    private static void loadGame(String argument)
    {
        gameFrame = new JFrame("Klondike Solitaire");
        gamePanel = new GamePanel(argument);
        showRulesButton=new JButton("Show Rules");
        showRulesButton.setBounds(120, FRAME_HEIGHT - 70, 120, 30);
        showRulesButton.addActionListener(new ShowRules());
        selectLevel1Button=new JButton("Level 1");
        selectLevel1Button.setBounds(240, FRAME_HEIGHT-70, 120, 30);
        selectLevel1Button.addActionListener(new SelectLevel1());
        selectLevel2Button=new JButton("Level 2");
        selectLevel2Button.setBounds(360, FRAME_HEIGHT-70, 120, 30);
        selectLevel2Button.addActionListener(new SelectLevel2());



        gamePanel.add(showRulesButton);
        gamePanel.add(selectLevel1Button);
        gamePanel.add(selectLevel2Button);
        gamePanel.repaint();
        gameFrame.add(gamePanel);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }
    public static void main(String[] args)
    {
        loadGame("level1");
    }
}


