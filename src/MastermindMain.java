import javax.swing.*;

/**
 * This is the main class to run all the mastermind stuff from. This has
 * all the graphics running code in it. Code was taken from various previous
 * graphics projects from the class.
 */
public class MastermindMain
{
    //size was determined through experimentation
    static final int DISPLAY_WIDTH  = 885;
    static final int DISPLAY_HEIGHT = 800;

    public static void main(String[] args)
    {
        JFrame f = new JFrame();

        f.setSize( DISPLAY_WIDTH, DISPLAY_HEIGHT );

        MastermindGraphics display = new MastermindGraphics( DISPLAY_WIDTH, DISPLAY_HEIGHT );

        f.setLayout( null );

        f.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        f.setTitle( "MasterMind" );

        f.add( display );

        f.setVisible( true );
    }
}
