package fluxoconsultoria.ufrj.br.surgerynote;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class GalleryActivity extends ActionBarActivity
{
    private Button menuButton;

    private HorizontalListView gallery;

    private List<ItemGallery> images;
    private List<Bitmap> bitmaps;

    private ArrayList<String> imagePaths;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Intent intent = getIntent();

        if (intent.getStringArrayListExtra("PATHS") != null)
        {
            imagePaths = intent.getStringArrayListExtra("PATHS");
        }
        else
        {
            imagePaths = new ArrayList<>();
        }

        this.menuButton = (Button) findViewById(R.id.menuButton);

        this.gallery = (HorizontalListView) findViewById(R.id.gallery);

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

        AdapterGallery adapter = new AdapterGallery(this, images);
        this.gallery.setAdapter(adapter);

        this.menuButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(GalleryActivity.this, MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        this.gallery.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.d("Click", images.get(position).getText());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gallery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
