import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class MastermindGraphics extends JComponent
{
    private int fWidth;
    private int fHeight;

    private boolean isGameRunning;
    //choose to use an int here because there are three states the game can be in: won, lost, or neither
    //2 is a victory, 3 is a loss, 1 is neither
    private int isGameWon;
    private MastermindColorSequence userGuess;
    private MastermindColorSequence computerSequence;
    int [] guessAnalysis;
    private List<MastermindColorSequence> userGuessHistory;
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
     * 7 = Black
     * 8 = White
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
    private Slot1Button fSlot1;
    private Slot2Button fSlot2;
    private Slot3Button fSlot3;
    private Slot4Button fSlot4;
    private CheckGuessButton fCheckGuess;
    private BackButton fBackButton;

    /**
     * Constructor for component class. Sets everything up.
     * @param width screen width
     * @param height screen height
     *
     * Method by Sam
     */
    public MastermindGraphics(int width, int height)
    {
        fWidth = width;
        fHeight = height;
        isGameRunning = false;
        isGameWon = 1;
        userGuess = new MastermindColorSequence();
        computerSequence = new MastermindColorSequence();
        computerSequence.generateRandomColorSequence();
        guessAnalysis = userGuess.getGuessAnalysis();
        userGuessHistory = new ArrayList<>();
        init();
    }

    /**
     * Method containing all the button initializations. Keeps them out of the way
     *
     * Method by Sam
     */
    public void init()
    {
        setSize( fWidth, fHeight );

        fEasyButton = new EasyButton();
        fEasyButton.setBounds(225, 500, 400, 36);
        add( fEasyButton );

        fMediumButton = new MediumButton();
        fMediumButton.setBounds(225, 556, 400, 36);
        add( fMediumButton );

        fHardButton = new HardButton();
        fHardButton.setBounds(225, 612, 400, 36);
        add( fHardButton );

        fOrangeButton = new OrangeButton();
        fOrangeButton.setBounds(20, 40, 100, 100);
        fOrangeButton.setBackground(Color.ORANGE);
        add( fOrangeButton );

        fRedButton = new RedButton();
        fRedButton.setBounds(20, 150, 100, 100);
        fRedButton.setBackground(Color.RED);
        add(fRedButton);

        fGreenButton = new GreenButton();
        fGreenButton.setBounds(20, 260, 100, 100);
        fGreenButton.setBackground(Color.GREEN);
        add(fGreenButton);

        fBlueButton = new BlueButton();
        fBlueButton.setBounds(20, 370, 100, 100);
        fBlueButton.setBackground(Color.BLUE);
        add(fBlueButton);

        fYellowButton = new YellowButton();
        fYellowButton.setBounds(20, 480, 100, 100);
        fYellowButton.setBackground(Color.YELLOW);
        add(fYellowButton);

        fMagentaButton = new MagentaButton();
        fMagentaButton.setBounds(20, 590, 100, 100);
        fMagentaButton.setBackground(Color.MAGENTA);
        add(fMagentaButton);

        fCheckGuess = new CheckGuessButton();
        fCheckGuess.setBounds(365, 620, 150, 36);
        add( fCheckGuess );

        fSlot1 = new Slot1Button();
        fSlot1.setBounds(160, 450, 130, 130);
        fSlot1.setBackground(Color.GRAY);
        add(fSlot1);

        fSlot2 = new Slot2Button();
        fSlot2.setBounds(300, 450, 130, 130);
        fSlot2.setBackground(Color.GRAY);
        add( fSlot2 );

        fSlot3 = new Slot3Button();
        fSlot3.setBounds(440, 450, 130, 130);
        fSlot3.setBackground(Color.GRAY);
        add( fSlot3 );

        fSlot4 = new Slot4Button();
        fSlot4.setBounds(580, 450, 130, 130);
        fSlot4.setBackground(Color.GRAY);
        add( fSlot4 );

        fBackButton = new BackButton();
        fBackButton.setBounds(400, 300, 100, 35);
        add( fBackButton );
    }

    /**
     * paintComponent class. Creates the screen. Has a boolean flag to determine if
     * the level select screen is up or game screen is up. Then draws dependent on that.
     *
     * @param g graphics
     *
     * Method by Sam
     */
    public void paintComponent(Graphics g)
    {
        if (!isGameRunning)
        {
            LevelSelectConstants(g);
        }
        else
        {
            if (isGameWon == 2 || isGameWon == 3)
            {
                fBackButton.setVisible(true);
                fBackButton.setEnabled(true);

                fCheckGuess.setVisible(false);
                fCheckGuess.setEnabled(false);

                fOrangeButton.setEnabled(false);
                fRedButton.setEnabled(false);
                fGreenButton.setEnabled(false);
                fBlueButton.setEnabled(false);
                fYellowButton.setEnabled(false);
                fMagentaButton.setEnabled(false);

                fSlot1.setEnabled(false);
                fSlot2.setEnabled(false);
                fSlot3.setEnabled(false);
                fSlot4.setEnabled(false);

                g.setColor(Color.DARK_GRAY);
                g.fillRect(0, 0, 885, 800);


                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.PLAIN, 120));
                if (isGameWon == 2)
                {
                    g.drawString("Victory!", 250, 200);
                }
                else
                {
                    g.drawString("You lose...", 200, 200);
                }
            }
            else
            {
                GameScreenConstants(g);
                for (int index = 0; index < guessAnalysis.length; index++)
                {
                    int feedback = guessAnalysis[index];
                    switch (feedback)
                    {
                        case 7:
                        {
                            g.setColor(Color.BLACK);
                            break;
                        }
                        case 8:
                        {
                            g.setColor(Color.WHITE);
                            break;
                        }
                        case 9:
                        {
                            g.setColor(Color.DARK_GRAY);
                            break;
                        }
                    }
                    if (index == 0)
                    {
                        g.fillOval(720, 450, 60, 60);
                    }
                    else if (index == 1)
                    {
                        g.fillOval(785, 450, 60, 60);
                    }
                    else if (index == 2)
                    {
                        g.fillOval(720, 515, 60, 60);
                    }
                    else
                    {
                        g.fillOval(785, 515, 60, 60);
                    }
                }
            }
        }

        repaint();
    }

    /**
     * Method containing the things that are always redrawn every time the level select screen is shown.
     * Helps to keep them from cluttering up the paintComponent method
     * @param g graphics
     *
     * Method by Sam
     */
    public void LevelSelectConstants(Graphics g)
    {
        fEasyButton.setVisible(true);
        fEasyButton.setEnabled(true);

        fMediumButton.setVisible(true);
        fMediumButton.setEnabled(true);

        fHardButton.setVisible(true);
        fHardButton.setEnabled(true);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 885, 800);

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

        fCheckGuess.setVisible(false);
        fCheckGuess.setEnabled(false);

        fSlot1.setVisible(false);
        fSlot1.setEnabled(false);

        fSlot2.setVisible(false);
        fSlot2.setEnabled(false);

        fSlot3.setVisible(false);
        fSlot3.setEnabled(false);

        fSlot4.setVisible(false);
        fSlot4.setEnabled(false);

        fBackButton.setVisible(false);
        fBackButton.setEnabled(false);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 72));
        g.drawString("Mastermind", 220, 100);

        //The entire freaking instruction paragraph, formatted to fit the screen.
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Instructions: Mastermind is a logic brain puzzle game, where you have a certain number of ", 20, 200);
        g.drawString("guesses to crack the color code generated by the computer. The computer creates a sequence ", 20, 220);
        g.drawString("of four random colors out of six possible colors. Repeats in a sequence are allowed. Your ", 20, 240);
        g.drawString("job is to figure out the sequence before you run out of guesses. On the far right hand side ", 20, 260);
        g.drawString("of the screen will be the color choices. The bottom center of the screen will have the four ", 20, 280);
        g.drawString("sequence placement choices. Select a color, then select a sequence placement. Then, click the ", 20, 300);
        g.drawString("\"Check Guess\" button. The computer will then give feedback about the color sequence you ", 20, 320);
        g.drawString("guessed in the form of black and white pegs in a square on the right of your guessed sequence. ", 20, 340);
        g.drawString("A black peg means that one of your guessed colors is both correct, and in the right spot in the ", 20, 360);
        g.drawString("sequence. A white peg means that there is a correct color in your sequence, but not in the right ", 20, 380);
        g.drawString("spot. No peg means that a color in your sequence is not in it at all. THE ORDER OF THE PEGS ", 20, 400);
        g.drawString("DOES NOT CORRESPOND IN ANY WAY TO WHICH COLORS ARE CORRECT OR NOT. All ", 20, 420);
        g.drawString("a black peg means is that some color in the sequence is in the right spot, and does not tell ", 20, 440);
        g.drawString("you anything of which one that color is. That is the challenge of the game. Good luck, player!", 20, 460);
    }

    /**
     * Method containing the things that are always redrawn every time the game screen is drawn.
     * Helps to keep them from cluttering up the paintComponent method
     * @param g graphics
     *
     * Method by Sam
     */
    public void GameScreenConstants(Graphics g)
    {
        fEasyButton.setVisible(false);
        fEasyButton.setEnabled(false);

        fMediumButton.setVisible(false);
        fMediumButton.setEnabled(false);

        fHardButton.setVisible(false);
        fHardButton.setEnabled(false);

        fBackButton.setVisible(false);
        fBackButton.setEnabled(false);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 885, 800);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Guesses Remaining: " + turnCounter, 275, 720);

        fOrangeButton.setEnabled(true);
        fRedButton.setEnabled(true);
        fGreenButton.setEnabled(true);
        fBlueButton.setEnabled(true);
        fYellowButton.setEnabled(true);
        fMagentaButton.setEnabled(true);

        fOrangeButton.setVisible(true);
        fRedButton.setVisible(true);
        fGreenButton.setVisible(true);
        fBlueButton.setVisible(true);
        fYellowButton.setVisible(true);
        fMagentaButton.setVisible(true);

        fCheckGuess.setVisible(true);
        fCheckGuess.setEnabled(true);

        fSlot1.setEnabled(true);
        fSlot1.setVisible(true);

        fSlot2.setEnabled(true);
        fSlot2.setVisible(true);

        fSlot3.setEnabled(true);
        fSlot3.setVisible(true);

        fSlot4.setEnabled(true);
        fSlot4.setVisible(true);
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
            isGameWon = 1;

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
            isGameWon = 1;

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
            isGameWon = 1;

            repaint();
        }
    }

    private class OrangeButton extends JButton implements ActionListener
    {
        //standard button code, gotten from game of life and such
        public OrangeButton()
        {
            super("");
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
            System.out.print(colorSet);

            repaint();
        }
    }

    private class RedButton extends JButton implements ActionListener
    {
        //standard button code, gotten from game of life and such
        public RedButton()
        {
            super("");
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
            super("");
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
            super("");
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
            super("");
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
            super("");
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
            guessAnalysis = userGuess.checkGuess(computerSequence.getColorSequence());
            if (guessAnalysis == null)
            {
                isGameWon = 2;
            }
            else if (turnCounter <= 0)
            {
                isGameWon = 3;
            }
            else
            {
                isGameWon = 1;
            }
            userGuessHistory.add(userGuess);
            turnCounter--;

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

    private class Slot1Button extends JButton implements ActionListener
    {
        //standard button code, gotten from game of life and such
        public Slot1Button()
        {
            super("");
            addActionListener(this);
        }

        /**
         * React to the button press.
         *
         * @param e the action
         */
        public void actionPerformed(ActionEvent e)
        {
            userGuess.setColors(0, colorSet);
            switch (userGuess.getColorSequence()[0])
            {
                case 0:
                {
                    this.setBackground(Color.GRAY);
                    break;
                }
                case 1:
                {
                    this.setBackground(Color.ORANGE);
                    break;
                }
                case 2:
                {
                    this.setBackground(Color.RED);
                    break;
                }
                case 3:
                {
                    this.setBackground(Color.GREEN);
                    break;
                }
                case 4:
                {
                    this.setBackground(Color.BLUE);
                    break;
                }
                case 5:
                {
                    this.setBackground(Color.YELLOW);
                    break;
                }
                case 6:
                {
                    this.setBackground(Color.MAGENTA);
                    break;
                }
            }

            repaint();
        }
    }

    private class Slot2Button extends JButton implements ActionListener
    {
        //standard button code, gotten from game of life and such
        public Slot2Button()
        {
            super("");
            addActionListener(this);
        }

        /**
         * React to the button press.
         *
         * @param e the action
         */
        public void actionPerformed(ActionEvent e)
        {
            userGuess.setColors(1, colorSet);
            switch (userGuess.getColorSequence()[1])
            {
                case 0:
                {
                    this.setBackground(Color.GRAY);
                    break;
                }
                case 1:
                {
                    this.setBackground(Color.ORANGE);
                    break;
                }
                case 2:
                {
                    this.setBackground(Color.RED);
                    break;
                }
                case 3:
                {
                    this.setBackground(Color.GREEN);
                    break;
                }
                case 4:
                {
                    this.setBackground(Color.BLUE);
                    break;
                }
                case 5:
                {
                    this.setBackground(Color.YELLOW);
                    break;
                }
                case 6:
                {
                    this.setBackground(Color.MAGENTA);
                    break;
                }
            }

            repaint();
        }
    }

    private class Slot3Button extends JButton implements ActionListener
    {
        //standard button code, gotten from game of life and such
        public Slot3Button()
        {
            super("");
            addActionListener(this);
        }

        /**
         * React to the button press.
         *
         * @param e the action
         */
        public void actionPerformed(ActionEvent e)
        {
            userGuess.setColors(2, colorSet);
            switch (userGuess.getColorSequence()[2])
            {
                case 0:
                {
                    this.setBackground(Color.GRAY);
                    break;
                }
                case 1:
                {
                    this.setBackground(Color.ORANGE);
                    break;
                }
                case 2:
                {
                    this.setBackground(Color.RED);
                    break;
                }
                case 3:
                {
                    this.setBackground(Color.GREEN);
                    break;
                }
                case 4:
                {
                    this.setBackground(Color.BLUE);
                    break;
                }
                case 5:
                {
                    this.setBackground(Color.YELLOW);
                    break;
                }
                case 6:
                {
                    this.setBackground(Color.MAGENTA);
                    break;
                }
            }

            repaint();
        }
    }

    private class Slot4Button extends JButton implements ActionListener
    {
        //standard button code, gotten from game of life and such
        public Slot4Button()
        {
            super("");
            addActionListener(this);
        }

        /**
         * React to the button press.
         *
         * @param e the action
         */
        public void actionPerformed(ActionEvent e)
        {
            userGuess.setColors(3, colorSet);
            switch (userGuess.getColorSequence()[3])
            {
                case 0:
                {
                    this.setBackground(Color.GRAY);
                    break;
                }
                case 1:
                {
                    this.setBackground(Color.ORANGE);
                    break;
                }
                case 2:
                {
                    this.setBackground(Color.RED);
                    break;
                }
                case 3:
                {
                    this.setBackground(Color.GREEN);
                    break;
                }
                case 4:
                {
                    this.setBackground(Color.BLUE);
                    break;
                }
                case 5:
                {
                    this.setBackground(Color.YELLOW);
                    break;
                }
                case 6:
                {
                    this.setBackground(Color.MAGENTA);
                    break;
                }
            }

            repaint();
        }
    }
}
