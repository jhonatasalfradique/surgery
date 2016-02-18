package fluxoconsultoria.ufrj.br.surgerynote;

import android.graphics.Bitmap;

/**
 * Created by ericreis on 7/20/15.
 */
public class ItemGallery
{
    private Bitmap bitmap;
    private String text;
    private String path;

    public ItemGallery()
    {
        this(null, "","");
    }

    public ItemGallery(Bitmap bitmap, String text, String path)
    {
        this.bitmap = bitmap;
        this.text = text;
        this.path = path;
    }

    public Bitmap getBitmap()
    {
        return this.bitmap;
    }

    public void setBitmap(Bitmap bitmap)
    {
        this.bitmap = bitmap;
    }

    public void setPath(String path) { this.path = path;}
    public String getText()
    {
        return this.text;
    }
    public String getPath()
    {
        return this.path;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}
