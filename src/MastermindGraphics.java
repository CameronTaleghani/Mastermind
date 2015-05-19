import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MastermindGraphics extends JComponent
{
    private int fWidth;
    private int fHeight;

    private boolean isGameRunning;
    //choose to use an int here because there are three states the game can be in: won, lost, or neither
    private int isGameWon;
    private int[] userGuess;
    private int[] guessAnalysis;
    private int colorSet;
    private int turnCounter;

    /**
     * For the colors, I'm using the order that I have them listed here
     * for the number code, or, in other words:
     * 1 = orange
     * 2 = red
     * 3 = green
     * 4 = blue
     * 5 = yellow
     * 6 = magenta
     * Zero I have left out so that we can use that for uncolored
     */
    private EasyButton fEasyButton;
    private MediumButton fMediumButton;
    private HardButton fHardButton;
    private OrangeButton fOrangeButton;
    private RedButton fRedButton;
    private GreenButton fGreenButton;
    private BlueButton fBlueButton;
    private YellowButton fYellowButton;
    private MagentaButton fMagentaButton;
    private CheckGuessButton fCheckGuess;
    private BackButton fBackButton;


    public MastermindGraphics(int width, int height)
    {
        fWidth = width;
        fHeight = height;
        isGameRunning = false;
        init();
    }

    public void init()
    {
        setSize( fWidth, fHeight );

        fEasyButton = new EasyButton();
        fEasyButton.setBounds(225, 400, 400, 36);
        add( fEasyButton );

        fMediumButton = new MediumButton();
        fMediumButton.setBounds(225, 456, 400, 36);
        add( fMediumButton );

        fHardButton = new HardButton();
        fHardButton.setBounds(225, 512, 400, 36);
        add( fHardButton );

        fOrangeButton = new OrangeButton();
        fOrangeButton.setBounds(20, 40, 100, 100);
        add( fOrangeButton );

        fRedButton = new RedButton();
        fRedButton.setBounds(20, 150, 100, 100);
        add( fRedButton );

        fGreenButton = new GreenButton();
        fGreenButton.setBounds(20, 260, 100, 100);
        add( fGreenButton );

        fBlueButton = new BlueButton();
        fBlueButton.setBounds(20, 370, 100, 100);
        add( fBlueButton );

        fYellowButton = new YellowButton();
        fYellowButton.setBounds(20, 480, 100, 100);
        add( fYellowButton );

        fMagentaButton = new MagentaButton();
        fMagentaButton.setBounds(20, 590, 100, 100);
        add( fMagentaButton );

        fCheckGuess = new CheckGuessButton();
        fCheckGuess.setBounds(300, 620, 100, 36);
        add( fCheckGuess );

    }

    public void paintComponent(Graphics g)
    {
        if (!isGameRunning)
        {
            LevelSelectConstants(g);
        }
        else
        {
            GameScreenConstants(g);
        }

        repaint();
    }

    public void LevelSelectConstants(Graphics g)
    {
        fEasyButton.setVisible(true);
        fEasyButton.setEnabled(true);

        fMediumButton.setVisible(true);
        fMediumButton.setEnabled(true);

        fHardButton.setVisible(true);
        fHardButton.setEnabled(true);

        fOrangeButton.setVisible(false);
        fRedButton.setVisible(false);
        fGreenButton.setVisible(false);
        fBlueButton.setVisible(false);
        fYellowButton.setVisible(false);
        fMagentaButton.setVisible(false);

        fOrangeButton.setEnabled(false);
        fRedButton.setEnabled(false);
        fGreenButton.setEnabled(false);
        fBlueButton.setEnabled(false);
        fYellowButton.setEnabled(false);
        fMagentaButton.setEnabled(false);

//        fCheckGuess.setVisible(false);
//        fCheckGuess.setEnabled(false);
//
//        fBackButton.setVisible(false);
//        fBackButton.setEnabled(false);
    }

    public void GameScreenConstants(Graphics g)
    {
        fEasyButton.setVisible(false);
        fEasyButton.setEnabled(false);

        fMediumButton.setVisible(false);
        fMediumButton.setEnabled(false);

        fHardButton.setVisible(false);
        fHardButton.setEnabled(false);

        fOrangeButton.setVisible(true);
        fRedButton.setVisible(true);
        fGreenButton.setVisible(true);
        fBlueButton.setVisible(true);
        fYellowButton.setVisible(true);
        fMagentaButton.setVisible(true);

        fOrangeButton.setEnabled(true);
        fRedButton.setEnabled(true);
        fGreenButton.setEnabled(true);
        fBlueButton.setEnabled(true);
        fYellowButton.setEnabled(true);
        fMagentaButton.setEnabled(true);

//        fCheckGuess.setVisible(true);
//        fCheckGuess.setEnabled(true);
    }

    private class EasyButton extends JButton implements ActionListener
    {
        //standard button code, gotten from game of life and such
        public EasyButton()
        {
            super("Easy Level: Allows 12 turns for you to guess the sequence!");
            addActionListener(this);
        }

        /**
         * React to the button press.
         *
         * @param e the action
         */
        public void actionPerformed(ActionEvent e)
        {
            turnCounter = 12;
            isGameRunning = true;

            repaint();
        }
    }

    private class MediumButton extends JButton implements ActionListener
    {
        //standard button code, gotten from game of life and such
        public MediumButton()
        {
            super("Medium Level: Allows 10 turns to guess the sequence.");
            addActionListener(this);
        }

        /**
         * React to the button press.
         *
         * @param e the action
         */
        public void actionPerformed(ActionEvent e)
        {
            turnCounter = 10;
            isGameRunning = true;

            repaint();
        }
    }

    private class HardButton extends JButton implements ActionListener
    {
        //standard button code, gotten from game of life and such
        public HardButton()
        {
            super("Hard Level: Allows only 8 turns for you to guess the sequence!");
            addActionListener(this);
        }

        /**
         * React to the button press.
         *
         * @param e the action
         */
        public void actionPerformed(ActionEvent e)
        {
            turnCounter = 8;
            isGameRunning = true;

            repaint();
        }
    }

    private class OrangeButton extends JButton implements ActionListener
    {
        //standard button code, gotten from game of life and such
        public OrangeButton()
        {
            super("Orange");
            addActionListener(this);
        }

        /**
         * React to the button press.
         *
         * @param e the action
         */
        public void actionPerformed(ActionEvent e)
        {
            colorSet = 1;

            repaint();
        }
    }

    private class RedButton extends JButton implements ActionListener
    {
        //standard button code, gotten from game of life and such
        public RedButton()
        {
            super("Red");
            addActionListener(this);
        }

        /**
         * React to the button press.
         *
         * @param e the action
         */
        public void actionPerformed(ActionEvent e)
        {
            colorSet = 2;

            repaint();
        }
    }

    private class GreenButton extends JButton implements ActionListener
    {
        //standard button code, gotten from game of life and such
        public GreenButton()
        {
            super("Green");
            addActionListener(this);
        }

        /**
         * React to the button press.
         *
         * @param e the action
         */
        public void actionPerformed(ActionEvent e)
        {
            colorSet = 3;

            repaint();
        }
    }

    private class BlueButton extends JButton implements ActionListener
    {
        //standard button code, gotten from game of life and such
        public BlueButton()
        {
            super("Blue");
            addActionListener(this);
        }

        /**
         * React to the button press.
         *
         * @param e the action
         */
        public void actionPerformed(ActionEvent e)
        {
            colorSet = 4;

            repaint();
        }
    }

    private class YellowButton extends JButton implements ActionListener
    {
        //standard button code, gotten from game of life and such
        public YellowButton()
        {
            super("Yellow");
            addActionListener(this);
        }

        /**
         * React to the button press.
         *
         * @param e the action
         */
        public void actionPerformed(ActionEvent e)
        {
            colorSet = 5;

            repaint();
        }
    }

    private class MagentaButton extends JButton implements ActionListener
    {
        //standard button code, gotten from game of life and such
        public MagentaButton()
        {
            super("Magenta");
            addActionListener(this);
        }

        /**
         * React to the button press.
         *
         * @param e the action
         */
        public void actionPerformed(ActionEvent e)
        {
            colorSet = 6;

            repaint();
        }
    }

    private class CheckGuessButton extends JButton implements ActionListener
    {
        //standard button code, gotten from game of life and such
        public CheckGuessButton()
        {
            super("Check Guess!");
            addActionListener(this);
        }

        /**
         * React to the button press.
         *
         * @param e the action
         */
        public void actionPerformed(ActionEvent e)
        {

            repaint();
        }
    }

    private class BackButton extends JButton implements ActionListener
    {
        //standard button code, gotten from game of life and such
        public BackButton()
        {
            super("Back");
            addActionListener(this);
        }

        /**
         * React to the button press.
         *
         * @param e the action
         */
        public void actionPerformed(ActionEvent e)
        {
            isGameRunning = false;

            repaint();
        }
    }
}
