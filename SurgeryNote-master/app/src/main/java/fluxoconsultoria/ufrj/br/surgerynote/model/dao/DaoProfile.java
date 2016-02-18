package fluxoconsultoria.ufrj.br.surgerynote.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Date;

import fluxoconsultoria.ufrj.br.surgerynote.DatabaseHelper;
import fluxoconsultoria.ufrj.br.surgerynote.DatabaseSingleton;
import fluxoconsultoria.ufrj.br.surgerynote.model.entity.Profile;

/**
 * Created by ericreis on 6/15/15.
 */
public class DaoProfile implements DaoGenerics<Profile>
{
    private DatabaseHelper dbH;

    public DaoProfile()
    {
        this.dbH = DatabaseSingleton.getInstance();
    }

    @Override
    public long save(Profile profile)
    {
        SQLiteDatabase db = this.dbH.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.PROFILE_KEY_NAME, profile.getName());
        values.put(DatabaseHelper.PROFILE_KEY_LASTNAME, profile.getLastname());
        values.put(DatabaseHelper.PROFILE_KEY_BIRTH, profile.getBirthString());
        values.put(DatabaseHelper.PROFILE_KEY_GENDER, profile.getGender());
        values.put(DatabaseHelper.PROFILE_KEY_COUNTRY, profile.getCountry());
        values.put(DatabaseHelper.PROFILE_KEY_STATE, profile.getState());
        values.put(DatabaseHelper.PROFILE_KEY_CITY, profile.getCity());
        values.put(DatabaseHelper.PROFILE_KEY_EMAIL, profile.getEmail());
        values.put(DatabaseHelper.PROFILE_KEY_SPECIALTY, profile.getSpeciality());
        values.put(DatabaseHelper.PROFILE_KEY_PROFESSIONAL_ID, profile.getProfessionalId());
        values.put(DatabaseHelper.PROFILE_KEY_SUBSCRIBED, profile.isSubscribed());

        long id = db.insert(DatabaseHelper.TABLE_PROFILE, null, values);
        profile.setId(id);
        this.update(profile);

        return id;
    }

    @Override
    public boolean update(Profile profile)
    {
        SQLiteDatabase db = this.dbH.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.PROFILE_KEY_NAME, profile.getName());
        values.put(DatabaseHelper.PROFILE_KEY_LASTNAME, profile.getLastname());
        values.put(DatabaseHelper.PROFILE_KEY_BIRTH, profile.getBirthString());
        values.put(DatabaseHelper.PROFILE_KEY_GENDER, profile.getGender());
        values.put(DatabaseHelper.PROFILE_KEY_COUNTRY, profile.getCountry());
        values.put(DatabaseHelper.PROFILE_KEY_STATE, profile.getCity());
        values.put(DatabaseHelper.PROFILE_KEY_EMAIL, profile.getEmail());
        values.put(DatabaseHelper.PROFILE_KEY_SPECIALTY, profile.getSpeciality());
        values.put(DatabaseHelper.PROFILE_KEY_PROFESSIONAL_ID, profile.getProfessionalId());
        values.put(DatabaseHelper.PROFILE_KEY_SUBSCRIBED, profile.isSubscribed());

        db.update(DatabaseHelper.TABLE_PROFILE, values, DatabaseHelper.KEY_ID + " = ?",
                  new String[] { String.valueOf(profile.getId()) });

        return true;
    }

    @Override
    public boolean delete(Profile profile)
    {
        SQLiteDatabase db = this.dbH.getWritableDatabase();

        db.delete(DatabaseHelper.TABLE_PROFILE, DatabaseHelper.KEY_ID + " = ?",
                  new String[]{String.valueOf(profile.getId())});

        return true;
    }

    @SuppressWarnings("deprecation")
    public Profile getProfile()
    {
        SQLiteDatabase db = this.dbH.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_PROFILE;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
        {
            Profile profile = new Profile();
            try
            {
                profile.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                profile.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_KEY_NAME)));
                profile.setLastname(cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_KEY_LASTNAME)));
                profile.setBirthFromSQLite(cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_KEY_BIRTH)));
                profile.setGender(cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_KEY_GENDER)));
                profile.setCountry(cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_KEY_COUNTRY)));
                profile.setState(cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_KEY_STATE)));
                profile.setCity(cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_KEY_CITY)));
                profile.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_KEY_EMAIL)));
                profile.setSpeciality(cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_KEY_SPECIALTY)));
                profile.setProfessionalId(cursor.getString(cursor.getColumnIndex(DatabaseHelper.PROFILE_KEY_PROFESSIONAL_ID)));
                profile.setSubscribed(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PROFILE_KEY_SUBSCRIBED)));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return profile;
        }

        cursor.close();

        return null;
    }
}
