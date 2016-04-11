/*
 NosuchLadderException
 Solves EE422C programming assignment #4
 @author Hari (hk8663), Kevin Yee (kjy252)
 @version 1.01 2016-03-03
 */

package assignment4;

public class NoSuchLadderException extends Exception
{
    private static final long serialVersionUID = 1L;

    public NoSuchLadderException(String message)
    {
        super(message);
    }

    public NoSuchLadderException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
