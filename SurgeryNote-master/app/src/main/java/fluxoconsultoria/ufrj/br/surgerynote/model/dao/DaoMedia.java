package fluxoconsultoria.ufrj.br.surgerynote.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fluxoconsultoria.ufrj.br.surgerynote.DatabaseHelper;
import fluxoconsultoria.ufrj.br.surgerynote.DatabaseSingleton;
import fluxoconsultoria.ufrj.br.surgerynote.model.entity.Media;
import fluxoconsultoria.ufrj.br.surgerynote.model.entity.Surgery;

/**
 * Created by ericreis on 8/19/15.
 */
public class DaoMedia implements DaoGenerics<Media>
{
    private DatabaseHelper dbH;

    public DaoMedia()
    {
        this.dbH = DatabaseSingleton.getInstance();
    }

    @Override
    public long save(Media media)
    {
        SQLiteDatabase db = this.dbH.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.MEDIA_KEY_PATH, media.getPath());
        values.put(DatabaseHelper.MEDIA_KEY_SURGERY_ID, media.getSurgeryId());

        long id = db.insert(DatabaseHelper.TABLE_MEDIA, null, values);
        media.setId(id);
        this.update(media);

        return id;
    }

    @Override
    public boolean update(Media media)
    {
        SQLiteDatabase db = this.dbH.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.MEDIA_KEY_PATH, media.getPath());
        values.put(DatabaseHelper.MEDIA_KEY_SURGERY_ID, media.getSurgeryId());

        db.update(DatabaseHelper.TABLE_MEDIA, values, DatabaseHelper.KEY_ID + " = ?",
                  new String[]{String.valueOf(media.getId())});

        return true;
    }

    @Override
    public boolean delete(Media media)
    {
        SQLiteDatabase db = this.dbH.getWritableDatabase();

        db.delete(DatabaseHelper.TABLE_MEDIA, DatabaseHelper.KEY_ID + " = ?",
                  new String[]{String.valueOf(media.getId())});

        return true;
    }

    public ArrayList<Media> getBySurgery(Surgery surgery)
    {
        ArrayList<Media> medias = new ArrayList<>();

        SQLiteDatabase db = this.dbH.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_MEDIA +
                             " WHERE " + DatabaseHelper.MEDIA_KEY_SURGERY_ID + " = ?";

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(surgery.getId())});

        if(cursor.moveToFirst())
        {
            try
            {
                do
                {
                    Media media = new Media();

                    media.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                    media.setPath(cursor.getString(cursor.getColumnIndex(DatabaseHelper.MEDIA_KEY_PATH)));
                    media.setSurgeryId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.MEDIA_KEY_SURGERY_ID)));

                    medias.add(media);
                }
                while(cursor.moveToNext());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            return null;
        }

        return medias;
    }
}
