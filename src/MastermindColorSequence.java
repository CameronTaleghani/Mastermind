import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MastermindColorSequence
{
    List<Integer> fColorSequence;
    int[] fGuessAnalysis = {9, 9, 9, 9};

    public MastermindColorSequence()
    {
        fColorSequence = new ArrayList<>();
        fColorSequence.add(0);
        fColorSequence.add(0);
        fColorSequence.add(0);
        fColorSequence.add(0);
    }

    /**
     * Sets the list to a random color sequence.
     * By Cameron
     */
    public void generateRandomColorSequence()
    {
        Random random = new Random();

        for(int i = 0; i < 4; i++)
        {
            fColorSequence.set(i, random.nextInt(5) + 1);
        }
    }

    /**
     * Sets the instance sequence to the color sequence.
     * @param colorSequence The color sequence the instance sequence will be set to.
     * By Cameron
     */
    public void setColorSequence(List<Integer> colorSequence)
    {
        fColorSequence = colorSequence;
    }

    /**
     * Gets the color sequence
     * @return An int array representing the color sequence.
     * By Cameron
     */
    public List<Integer> getColorSequence()
    {
        return fColorSequence;
    }

    /**
     * Gets the guess analysis.
     * @return The guess analysis, how the guess compares to the correct sequence.
     * By Sam
     */
    public int[] getGuessAnalysis()
    {
        return fGuessAnalysis;
    }

    /**
     * Resets the color sequence list so that all of the colors are 0
     * By Cameron
     */
    public void resetColorSequence()
    {
        fColorSequence = new ArrayList<>(4);
    }

    /**
     * Changes a color at a specific point in the sequence.
     * @param index The index at which the list is being set to the color value.
     * @param color The color being changed to.
     * By Cameron
     */
    public void setColors(int index, int color)
    {
        fColorSequence.set(index, color);
    }

    /**
     * Compares the user's color sequence guess to the correct computer generated sequence.
     *
     * @param computerSequence The color sequence generated by the computer.
     * @return An int array representing the comparison between the correct color sequence and guessed color sequence.
     * 7 indicates that a color is in the sequence and that the color is in the correct position.
     * 8 indicates that a color is in the sequence but not in the correct position.
     * 9 indicates that no color is in the sequence.
     * By Cameron
     */
    public int[] checkGuess(List<Integer> computerSequence)
    {
        List<Integer> guess = new ArrayList<>(fColorSequence);
        //sets ints in guessAnalysis to 7 or 8 based on the comparison
        int analysisCount = 0;
        for(int i = guess.size() - 1; i >= 0; i--)                  //iterate across the guessed sequence
        {
            if(guess.get(i).equals(computerSequence.get(i)))            //colors are the same
            {
                fGuessAnalysis[analysisCount] = 7;
                computerSequence.remove(i);
                guess.remove(i);
                analysisCount++;
            }
        }

        for(int i = guess.size() - 1; i >= 0; i--)
        {
            for(int j = computerSequence.size() - 1; j >= 0; j--)
            {
                if(guess.get(i).equals(computerSequence.get(j)))
                {
                    fGuessAnalysis[analysisCount] = 8;
                    computerSequence.remove(j);
                    guess.remove(i);
                    analysisCount++;
                    if(i != 0)
                    {
                        i--;
                    }
                }
            }
        }

        //selection sort from least to greatest
        for (int i = 0; i < fGuessAnalysis.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < fGuessAnalysis.length; j++)
                if (fGuessAnalysis[j] < fGuessAnalysis[index])
                    index = j;

            int smallerNumber = fGuessAnalysis[index];
            fGuessAnalysis[index] = fGuessAnalysis[i];
            fGuessAnalysis[i] = smallerNumber;
        }

        boolean hasWon = true;
        //Checks to see if the guess matches the computer sequence
        for(int num: fGuessAnalysis)
        {
            if(num != 7)
            {
                hasWon = false;
            }
        }

        if(hasWon)
        {
            return null;
        }

        return fGuessAnalysis;
    }

    public void printCheck()
    {
        MastermindColorSequence computerSequence = new MastermindColorSequence();
        MastermindColorSequence sequence = new MastermindColorSequence();
//        computerSequence.generateRandomColorSequence();
//        sequence.generateRandomColorSequence();
//        List<Integer> seq = new ArrayList<>();
//        List<Integer> compSeq = new ArrayList<>();
//        seq.add(3);
//        seq.add(1);
//        seq.add(5);
//        seq.add(2);
//        compSeq.add(1);
//        compSeq.add(5);
//        compSeq.add(4);
//        compSeq.add(5);
//        sequence.setColorSequence(compSeq);
//        computerSequence.setColorSequence(seq);

        for(int color: computerSequence.getColorSequence())
        {
            System.out.print(color);
        }
        System.out.println();

        for(int color: sequence.getColorSequence())
        {
            System.out.print(color);
        }
        System.out.println();

        int[] analysis = sequence.checkGuess(computerSequence.getColorSequence());

        for (int a : analysis)
        {
            System.out.print(a);
        }
        System.out.println();
    }
}
