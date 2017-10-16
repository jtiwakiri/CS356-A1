import java.util.Random;

/**
 * Created by Joshua on 10/12/2017.
 */
public enum MultipleChoiceOption {
    A, B, C, D, E, F, G;

    private static final MultipleChoiceOption[] VALUES = values();
    private static final int SIZE = VALUES.length;

    /**
     * @return A random multiple choice option.
     */
    public static MultipleChoiceOption getRandomOption()  {
        return VALUES[(int) (Math.random() * SIZE)];
    }
}
