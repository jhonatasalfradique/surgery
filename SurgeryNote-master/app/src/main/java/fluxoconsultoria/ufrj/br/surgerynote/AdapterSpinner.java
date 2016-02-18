package fluxoconsultoria.ufrj.br.surgerynote;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ericreis on 9/28/15.
 */
public class AdapterSpinner extends ArrayAdapter<String>
{
    private Context context;
    private List<String> amounts;
    private int[] images;

    public AdapterSpinner(Context context, int textViewResourceId, List<String> amounts, int[] images)
    {
        super(context, textViewResourceId, amounts);
        this.context = context;
        this.amounts = amounts;
        this.images = images;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        return getCustomView(position, convertView, parent, true);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return getCustomView(position, convertView, parent, false);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent, boolean dropedDown)
    {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.row, parent, false);
        TextView label = (TextView) row.findViewById(R.id.company);
        label.setText(this.amounts.get(position));
        if (dropedDown)
        {
            label.setTextColor(this.context.getResources().getColor(R.color.white));
            row.setPadding(5, 5, 5, 30);
        }


        ImageView icon = (ImageView) row.findViewById(R.id.image);
        icon.setImageResource(this.images[position]);

        return row;
    }
}
