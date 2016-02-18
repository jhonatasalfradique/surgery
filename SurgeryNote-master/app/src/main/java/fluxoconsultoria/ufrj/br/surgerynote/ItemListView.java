package fluxoconsultoria.ufrj.br.surgerynote;

/**
 * Created by ericreis on 7/13/15.
 */
public class ItemListView
{
    private String pacientName, date, diagnostic;
    private int iconId;

    public ItemListView()
    {
        this("", "", "", -1);
    }

    public ItemListView(String pacientName, String date, String diagnostic, int iconId)
    {
        this.pacientName = pacientName;
        this.date = date;
        this.diagnostic = diagnostic;
        this.iconId = iconId;
    }

    public String getPacientName()
    {
        return this.pacientName;
    }

    public void setPacientName(String pacientName)
    {
        this.pacientName = pacientName;
    }

    public String getDate()
    {
        return this.date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getDiagnostic()
    {
        return this.diagnostic;
    }

    public void setDiagnostic(String diagnostic)
    {
        this.diagnostic = diagnostic;
    }

    public int getIconId()
    {
        return this.iconId;
    }

    public void setIconId(int iconId)
    {
        this.iconId = iconId;
    }
}
