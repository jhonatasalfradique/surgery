package fluxoconsultoria.ufrj.br.surgerynote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ericreis on 7/13/15.
 */
public class AdapterListView extends BaseAdapter
{
    private LayoutInflater mInflater;
    private List<ItemListView> itens;

    ItemListView tempValues = null;

    public AdapterListView(Context context, List<ItemListView> itens)
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
            convertView = mInflater.inflate(R.layout.listview_item, null);

            itemHolder = new ItemSupport();
            itemHolder.pacientNameTextView = (TextView) convertView.findViewById(R.id.pacientNameTextView);
            itemHolder.dateTextView = (TextView) convertView.findViewById(R.id.dateTextView);
            itemHolder.diagnosticTextView = (TextView) convertView.findViewById(R.id.diagnosticTextView);
            itemHolder.iconImageView = (ImageView) convertView.findViewById(R.id.iconImageView);

            convertView.setTag(itemHolder);
        }
        else
        {
            itemHolder = (ItemSupport) convertView.getTag();
        }

        ItemListView item = this.itens.get(position);
        itemHolder.pacientNameTextView.setText(item.getPacientName());
        itemHolder.dateTextView.setText(item.getDate());
        itemHolder.diagnosticTextView.setText(item.getDiagnostic());
        itemHolder.iconImageView.setImageResource(item.getIconId());

        return convertView;
    }

    private class ItemSupport
    {
        TextView pacientNameTextView, dateTextView, diagnosticTextView;
        ImageView iconImageView;
    }

}
