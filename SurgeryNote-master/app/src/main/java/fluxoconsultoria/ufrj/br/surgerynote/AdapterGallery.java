package fluxoconsultoria.ufrj.br.surgerynote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by ericreis on 7/20/15.
 */
public class AdapterGallery extends BaseAdapter
{
    private LayoutInflater mInflater;
    private List<ItemGallery> itens;
    private PhotoViewAttacher attacher;

    ItemGallery tempValues = null;

    public AdapterGallery(Context context, List<ItemGallery> itens)
    {
        this.mInflater = LayoutInflater.from(context);
        this.itens = itens;
    }

    @Override
    public int getCount()
    {
        return this.itens.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.itens.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ItemSupport itemHolder;

        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.gallery_item, null);

            itemHolder = new ItemSupport();
            itemHolder.galleryImageView = (ImageView) convertView.findViewById(R.id.galleryImageView);
            itemHolder.galleryTextView = (TextView) convertView.findViewById(R.id.galleryTextView);

            convertView.setTag(itemHolder);
        }
        else
        {
            itemHolder = (ItemSupport) convertView.getTag();
        }

        ItemGallery item = this.itens.get(position);
        itemHolder.galleryImageView.setImageBitmap(item.getBitmap());
        itemHolder.galleryTextView.setText(item.getText());

        attacher = new PhotoViewAttacher(itemHolder.galleryImageView);

        return convertView;
    }

    private class ItemSupport
    {
        ImageView galleryImageView;
        TextView galleryTextView;
    }
}
