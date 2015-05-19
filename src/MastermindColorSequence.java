import java.util.Random;

public class MastermindColorSequence
{
    int[] fColorSequence;

    public MastermindColorSequence()
    {
        fColorSequence = new int[4];
    }

    public MastermindColorSequence(int[] colorSequence)
    {
        fColorSequence = colorSequence;
    }

    public void generateRandomColorSequence()
    {
        Random random = new Random();

        for(int i = 0; i < fColorSequence.length; i++)
        {
            fColorSequence[i] = random.nextInt(5) + 1;
        }
    }

    public void setColors(int index, int color)
    {
        fColorSequence[index] = color;
    }
}
