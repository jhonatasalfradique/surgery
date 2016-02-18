package fluxoconsultoria.ufrj.br.surgerynote.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fluxoconsultoria.ufrj.br.surgerynote.DatabaseHelper;
import fluxoconsultoria.ufrj.br.surgerynote.DatabaseSingleton;
import fluxoconsultoria.ufrj.br.surgerynote.model.entity.FinancialData;
import fluxoconsultoria.ufrj.br.surgerynote.model.entity.Surgery;

/**
 * Created by ericreis on 7/13/15.
 */
public class DaoFinancialData implements DaoGenerics<FinancialData>
{
    private DatabaseHelper dbH;

    public DaoFinancialData()
    {
        this.dbH = DatabaseSingleton.getInstance();
    }

    @Override
    public long save(FinancialData financialData)
    {
        SQLiteDatabase db = this.dbH.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.FINANCIAL_DATA_KEY_HEALTH_INSURANCE, financialData.getHealthInsurance());
        values.put(DatabaseHelper.FINANCIAL_DATA_KEY_PROCEDURE_CODE, financialData.getProcedureCode());
        values.put(DatabaseHelper.FINANCIAL_DATA_KEY_MAIN_SURGERY_VALUE, financialData.getMainSugeryValue());
        values.put(DatabaseHelper.FINANCIAL_DATA_KEY_FIRST_ASSISTANT_VALUE, financialData.getFirstAssistantValue());
        values.put(DatabaseHelper.FINANCIAL_DATA_KEY_SECOND_ASSISTANT_VALUE, financialData.getSecondAssistantValue());
        values.put(DatabaseHelper.FINANCIAL_DATA_KEY_ANESTHESIOLOGIST_VALUE, financialData.getAnesthesiologistValue());
        values.put(DatabaseHelper.FINANCIAL_DATA_KEY_INSTRUMENTATION_TECHNICIAN_VALUE, financialData.getInstrumentationTechnicianValue());
        values.put(DatabaseHelper.FINANCIAL_DATA_KEY_AMOUNT, financialData.getAmount());
        values.put(DatabaseHelper.FINANCIAL_DATA_KEY_PAYDAY, financialData.getPaydayString());
        values.put(DatabaseHelper.FINANCIAL_DATA_KEY_PAYMENT_FORECAST, financialData.getPaymentForecastString());

        long id = db.insert(DatabaseHelper.TABLE_FINANCIAL_DATA, null, values);
        financialData.setId(id);
        this.update(financialData);

        return id;
    }

    @Override
    public boolean update(FinancialData financialData)
    {
        SQLiteDatabase db = this.dbH.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.FINANCIAL_DATA_KEY_HEALTH_INSURANCE, financialData.getHealthInsurance());
        values.put(DatabaseHelper.FINANCIAL_DATA_KEY_PROCEDURE_CODE, financialData.getProcedureCode());
        values.put(DatabaseHelper.FINANCIAL_DATA_KEY_MAIN_SURGERY_VALUE, financialData.getMainSugeryValue());
        values.put(DatabaseHelper.FINANCIAL_DATA_KEY_FIRST_ASSISTANT_VALUE, financialData.getFirstAssistantValue());
        values.put(DatabaseHelper.FINANCIAL_DATA_KEY_SECOND_ASSISTANT_VALUE, financialData.getSecondAssistantValue());
        values.put(DatabaseHelper.FINANCIAL_DATA_KEY_ANESTHESIOLOGIST_VALUE, financialData.getAnesthesiologistValue());
        values.put(DatabaseHelper.FINANCIAL_DATA_KEY_INSTRUMENTATION_TECHNICIAN_VALUE, financialData.getInstrumentationTechnicianValue());
        values.put(DatabaseHelper.FINANCIAL_DATA_KEY_AMOUNT, financialData.getAmount());
        values.put(DatabaseHelper.FINANCIAL_DATA_KEY_PAYDAY, financialData.getPaydayString());
        values.put(DatabaseHelper.FINANCIAL_DATA_KEY_PAYMENT_FORECAST, financialData.getPaymentForecastString());

        db.update(DatabaseHelper.TABLE_FINANCIAL_DATA, values, DatabaseHelper.KEY_ID + " = ?",
                  new String[]{String.valueOf(financialData.getId())});

        return true;
    }

    @Override
    public boolean delete(FinancialData financialData)
    {
        SQLiteDatabase db = this.dbH.getWritableDatabase();

        db.delete(DatabaseHelper.TABLE_FINANCIAL_DATA, DatabaseHelper.KEY_ID + " = ?",
                  new String[]{String.valueOf(financialData.getId())});

        return true;
    }

    public ArrayList<FinancialData> getAll()
    {
        ArrayList<FinancialData> financialDatas = new ArrayList<FinancialData>();

        SQLiteDatabase db = this.dbH.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_FINANCIAL_DATA;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst())
        {
            try
            {
                do
                {
                    FinancialData financialData = new FinancialData();

                    financialData.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                    financialData.setHealthInsurance(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FINANCIAL_DATA_KEY_HEALTH_INSURANCE)));
                    financialData.setProcedureCode(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FINANCIAL_DATA_KEY_PROCEDURE_CODE)));
                    financialData.setMainSugeryValue(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.FINANCIAL_DATA_KEY_MAIN_SURGERY_VALUE)));
                    financialData.setFirstAssistantValue(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.FINANCIAL_DATA_KEY_FIRST_ASSISTANT_VALUE)));
                    financialData.setSecondAssistantValue(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.FINANCIAL_DATA_KEY_SECOND_ASSISTANT_VALUE)));
                    financialData.setAnesthesiologistValue(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.FINANCIAL_DATA_KEY_ANESTHESIOLOGIST_VALUE)));
                    financialData.setInstrumentationTechnicianValue(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.FINANCIAL_DATA_KEY_INSTRUMENTATION_TECHNICIAN_VALUE)));
                    financialData.setAmount(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FINANCIAL_DATA_KEY_AMOUNT)));
                    financialData.setPaydayFromSQLite(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FINANCIAL_DATA_KEY_PAYDAY)));
                    financialData.setPaymentForecastFromSQLite(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FINANCIAL_DATA_KEY_PAYMENT_FORECAST)));

                    financialDatas.add(financialData);
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

        return financialDatas;
    }
}
