package fluxoconsultoria.ufrj.br.surgerynote.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import fluxoconsultoria.ufrj.br.surgerynote.DatabaseHelper;
import fluxoconsultoria.ufrj.br.surgerynote.DatabaseSingleton;
import fluxoconsultoria.ufrj.br.surgerynote.model.entity.Surgery;

/**
 * Created by ericreis on 6/24/15.
 */
public class DaoSurgery implements DaoGenerics<Surgery>
{
    private DatabaseHelper dbH;

    public DaoSurgery()
    {
        this.dbH = DatabaseSingleton.getInstance();
    }

    @Override
    public long save(Surgery surgery)
    {
        SQLiteDatabase db = this.dbH.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.SURGERY_KEY_PACIENT_NAME, surgery.getPacientName());
        values.put(DatabaseHelper.SURGERY_KEY_PACIENT_BIRTH, surgery.getPacientBirthString());
        values.put(DatabaseHelper.SURGERY_KEY_PACIENT_GENDER, surgery.getPacientGender());
        values.put(DatabaseHelper.SURGERY_KEY_DATE, surgery.getDateString());
        values.put(DatabaseHelper.SURGERY_KEY_MEDICAL_RECORD, surgery.getMedicalRecord());
        values.put(DatabaseHelper.SURGERY_KEY_HOSPITAL, surgery.getHospital());
        values.put(DatabaseHelper.SURGERY_KEY_DIAGNOSIS, surgery.getDiagnosis());
        values.put(DatabaseHelper.SURGERY_KEY_SURGICAL_PROCEDURE, surgery.getSurgicalProcedure());
        values.put(DatabaseHelper.SURGERY_KEY_MAIN_SURGERY, surgery.getMainSurgery());
        values.put(DatabaseHelper.SURGERY_KEY_FIRST_ASSISTANT, surgery.getFirstAssistant());
        values.put(DatabaseHelper.SURGERY_KEY_SECOND_ASSISTANT, surgery.getSecondAssistant());
        values.put(DatabaseHelper.SURGERY_KEY_ANESTHESIOLOGIST, surgery.getAnesthesiologist());
        values.put(DatabaseHelper.SURGERY_KEY_INSTRUMENTATION_TECHNICIAN, surgery.getInstrumentationTechnician());
        values.put(DatabaseHelper.SURGERY_KEY_SURGICAL_EQUIPMENT_COMPANY, surgery.getSurgicalEquipmentCompany());
        values.put(DatabaseHelper.SURGERY_KEY_SURGICAL_EQUIPMENT, surgery.getSurgicalEquipment());
        values.put(DatabaseHelper.SURGERY_KEY_REVALUATION_DATE, surgery.getRevaluationDateString());
        values.put(DatabaseHelper.SURGERY_KEY_OBSERVATION, surgery.getObservation());
        values.put(DatabaseHelper.SURGERY_KEY_MEDIA_ID, surgery.getMediaId());
        values.put(DatabaseHelper.SURGERY_KEY_FINANCIAL_DATA_ID, surgery.getFinancialDataId());

        Log.d("Financial ID", String.valueOf(surgery.getFinancialDataId()));

        long id = db.insert(DatabaseHelper.TABLE_SURGERY, null, values);
        surgery.setId(id);
        this.update(surgery);

        return id;
    }

    @Override
    public boolean update(Surgery surgery)
    {
        SQLiteDatabase db = this.dbH.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.SURGERY_KEY_PACIENT_NAME, surgery.getPacientName());
        values.put(DatabaseHelper.SURGERY_KEY_PACIENT_BIRTH, surgery.getPacientBirthString());
        values.put(DatabaseHelper.SURGERY_KEY_PACIENT_GENDER, surgery.getPacientGender());
        values.put(DatabaseHelper.SURGERY_KEY_DATE, surgery.getDateString());
        values.put(DatabaseHelper.SURGERY_KEY_MEDICAL_RECORD, surgery.getMedicalRecord());
        values.put(DatabaseHelper.SURGERY_KEY_HOSPITAL, surgery.getHospital());
        values.put(DatabaseHelper.SURGERY_KEY_DIAGNOSIS, surgery.getDiagnosis());
        values.put(DatabaseHelper.SURGERY_KEY_SURGICAL_PROCEDURE, surgery.getSurgicalProcedure());
        values.put(DatabaseHelper.SURGERY_KEY_MAIN_SURGERY, surgery.getMainSurgery());
        values.put(DatabaseHelper.SURGERY_KEY_FIRST_ASSISTANT, surgery.getFirstAssistant());
        values.put(DatabaseHelper.SURGERY_KEY_SECOND_ASSISTANT, surgery.getSecondAssistant());
        values.put(DatabaseHelper.SURGERY_KEY_ANESTHESIOLOGIST, surgery.getAnesthesiologist());
        values.put(DatabaseHelper.SURGERY_KEY_INSTRUMENTATION_TECHNICIAN, surgery.getInstrumentationTechnician());
        values.put(DatabaseHelper.SURGERY_KEY_SURGICAL_EQUIPMENT_COMPANY, surgery.getSurgicalEquipmentCompany());
        values.put(DatabaseHelper.SURGERY_KEY_SURGICAL_EQUIPMENT, surgery.getSurgicalEquipment());
        values.put(DatabaseHelper.SURGERY_KEY_REVALUATION_DATE, surgery.getRevaluationDateString());
        values.put(DatabaseHelper.SURGERY_KEY_OBSERVATION, surgery.getObservation());
        values.put(DatabaseHelper.SURGERY_KEY_MEDIA_ID, surgery.getMediaId());
        values.put(DatabaseHelper.SURGERY_KEY_FINANCIAL_DATA_ID, surgery.getFinancialDataId());

        db.update(DatabaseHelper.TABLE_SURGERY, values, DatabaseHelper.KEY_ID + " = ?",
                  new String[]{String.valueOf(surgery.getId())});

        return true;
    }

    @Override
    public boolean delete(Surgery surgery)
    {
        SQLiteDatabase db = this.dbH.getWritableDatabase();

        db.delete(DatabaseHelper.TABLE_PROFILE, DatabaseHelper.KEY_ID + " = ?",
                  new String[]{String.valueOf(surgery.getId())});

        return true;
    }

    public ArrayList<Surgery> getAll()
    {
        ArrayList<Surgery> surgeries = new ArrayList<Surgery>();

        SQLiteDatabase db = this.dbH.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_SURGERY;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst())
        {
            try
            {
                do
                {
                    Surgery surgery = new Surgery();

                    surgery.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                    surgery.setPacientName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SURGERY_KEY_PACIENT_NAME)));
                    surgery.setPacientBirthFromSQLite(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SURGERY_KEY_PACIENT_BIRTH)));
                    surgery.setPacientGender(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SURGERY_KEY_PACIENT_GENDER)));
                    surgery.setDateFromSQLite(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SURGERY_KEY_DATE)));
                    surgery.setMedicalRecord(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SURGERY_KEY_MEDICAL_RECORD)));
                    surgery.setHospital(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SURGERY_KEY_HOSPITAL)));
                    surgery.setDiagnosis(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SURGERY_KEY_DIAGNOSIS)));
                    surgery.setSurgicalProcedure(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SURGERY_KEY_SURGICAL_PROCEDURE)));
                    surgery.setMainSurgery(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SURGERY_KEY_MAIN_SURGERY)));
                    surgery.setFirstAssistant(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SURGERY_KEY_FIRST_ASSISTANT)));
                    surgery.setSecondAssistant(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SURGERY_KEY_SECOND_ASSISTANT)));
                    surgery.setAnesthesiologist(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SURGERY_KEY_ANESTHESIOLOGIST)));
                    surgery.setInstrumentationTechnician(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SURGERY_KEY_INSTRUMENTATION_TECHNICIAN)));
                    surgery.setSurgicalEquipmentCompany(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SURGERY_KEY_SURGICAL_EQUIPMENT_COMPANY)));
                    surgery.setSurgicalEquipment(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SURGERY_KEY_SURGICAL_EQUIPMENT)));
                    surgery.setRevaluationDateFromSQLite(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SURGERY_KEY_REVALUATION_DATE)));
                    surgery.setObservation(cursor.getString(cursor.getColumnIndex(DatabaseHelper.SURGERY_KEY_OBSERVATION)));
                    surgery.setMediaId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.SURGERY_KEY_MEDIA_ID)));
                    surgery.setFinancialDataId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.SURGERY_KEY_FINANCIAL_DATA_ID)));

                    surgeries.add(surgery);
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

        return surgeries;
    }
}
