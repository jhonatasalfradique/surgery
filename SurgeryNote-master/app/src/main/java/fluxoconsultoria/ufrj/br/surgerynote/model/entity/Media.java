package fluxoconsultoria.ufrj.br.surgerynote.model.entity;


/**
 * Created by ericreis on 8/19/15.
 */
public class Media extends Object
{
    private int id, surgeryId;
    private String path;

    public Media()
    {

    }

    @Override
    public String toString() throws NullPointerException
    {
        String attr = this.path + ", " + this.surgeryId;
        return attr;
    }

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setId(long id)
    {
        this.id = (int) id;
    }

    public int getSurgeryId()
    {
        return this.surgeryId;
    }

    public void setSurgeryId(int surgeryId)
    {
        this.surgeryId = surgeryId;
    }

    public String getPath()
    {
        return this.path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }
}
