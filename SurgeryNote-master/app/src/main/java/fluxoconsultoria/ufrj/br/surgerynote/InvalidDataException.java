package fluxoconsultoria.ufrj.br.surgerynote;

/**
 * Created by ericreis on 6/12/15.
 */
public class InvalidDataException extends Exception
{
    private String location;

    public InvalidDataException(String loc, NullPointerException npe)
    {
        super(npe);
        this.location = loc;
    }

    public String getLocation()
    {
        return this.location;
    }
}
