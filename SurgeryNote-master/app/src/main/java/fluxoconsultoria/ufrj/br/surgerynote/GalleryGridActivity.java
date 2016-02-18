package fluxoconsultoria.ufrj.br.surgerynote;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GalleryGridActivity extends Activity
{


    private List<ItemGallery> images;
    private List<Bitmap> bitmaps;

    private ArrayList<String> imagePaths;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_gallery_grid);



        Intent intent = getIntent();

        if (intent.getStringArrayListExtra("PATHS") != null)
        {
            imagePaths = intent.getStringArrayListExtra("PATHS");
        }
        else
        {
            imagePaths = new ArrayList<>();
        }

        //this.menuButton = (Button) findViewById(R.id.menuButton);

    //        this.gallery = (HorizontalListView) findViewById(R.id.gallery);

        this.bitmaps = new ArrayList<>();
        this.images = new ArrayList<>();

        for (String imagePath : imagePaths)
        {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;

            Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
            this.bitmaps.add(bitmap);
            this.images.add(new ItemGallery(bitmap, "Test", imagePath));
        }

        final MyAdapter MyAdapter = new MyAdapter(context, this.images);

        GridView gridView = (GridView)findViewById(R.id.gridview);
        gridView.setAdapter(MyAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String BitmapPath = MyAdapter.itemsGallery.get(position).getPath();//MyAdapter.items.get(position).drawableId;
                /*Bitmap bitmap = BitmapFactory.decodeResource(getResources(), MyAdapter.items.get(position).name);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] b = baos.toByteArray();


                String fileName = "Image.png";

                // To dismiss the dialog

                try {
                    FileOutputStream fileOutStream = openFileOutput(fileName, MODE_PRIVATE);
                    fileOutStream.write(b);  //b is byte array
                    //(used if you have your picture downloaded
                    // from the *Web* or got it from the *devices camera*)
                    //otherwise this technique is useless
                    fileOutStream.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }*/

                Intent intent = new Intent(GalleryGridActivity.this, ZoomActivity.class);
                intent.putExtra("ImagePath", BitmapPath);

                startActivity(intent);

            }
        });

    }

    private class MyAdapter extends BaseAdapter
    {
        //private List<Item> items = new ArrayList<Item>();
        private List<ItemGallery> itemsGallery = new ArrayList<ItemGallery>();

        private LayoutInflater inflater;

        public MyAdapter(Context context, List<ItemGallery> item)
        {

            inflater = LayoutInflater.from(context);

            for (int i = 0 ; i< item.size(); i++){

                itemsGallery.add(new ItemGallery(item.get(i).getBitmap(), "", item.get(i).getPath()));

            }
            //items.add(new Item("Image 1", R.drawable.nature1));
//            items.add(new Item("Image 2", R.drawable.nature2));
//            items.add(new Item("Image 3", R.drawable.tree1));
//            items.add(new Item("Image 4", R.drawable.nature3));
//            items.add(new Item("Image 5", R.drawable.tree2));
        }

        @Override
        public int getCount() {
            return itemsGallery.size();
        }

        @Override
        public Object getItem(int i)
        {
            return itemsGallery.get(i);
        }

        @Override
        public long getItemId(int i)
        {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View v = view;
            ImageView picture;
            TextView name;

            if(v == null)
            {
                v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
                v.setTag(R.id.picture, v.findViewById(R.id.picture));
                v.setTag(R.id.text, v.findViewById(R.id.text));
            }

            picture = (ImageView)v.getTag(R.id.picture);
            name = (TextView)v.getTag(R.id.text);

            //Item item = (Item)getItem(i);

            picture.setImageBitmap(itemsGallery.get(i).getBitmap());
            name.setText(itemsGallery.get(i).getText());

            return v;
        }

        private class Item
        {
            final String name;
            final int drawableId;

            Item(String name, int drawableId)
            {
                this.name = name;
                this.drawableId = drawableId;
            }
        }
    }

}
