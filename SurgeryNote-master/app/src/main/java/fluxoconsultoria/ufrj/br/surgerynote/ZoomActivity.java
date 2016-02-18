package fluxoconsultoria.ufrj.br.surgerynote;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ZoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  ProgressDialog progress = new ProgressDialog(ZoomActivity.this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.show();*/

        Bundle extras = getIntent().getExtras();
        String imagePath = extras.getString("ImagePath");

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
        //File filePath = getFileStreamPath(fileName);
        //Drawable d = Drawable.createFromPath(filePath.toString());

        TouchImageView img = new TouchImageView(this);
        img.setImageBitmap(bitmap);
        img.setMaxZoom(4f);
        //progress.dismiss();
        setContentView(img);
    }

}
