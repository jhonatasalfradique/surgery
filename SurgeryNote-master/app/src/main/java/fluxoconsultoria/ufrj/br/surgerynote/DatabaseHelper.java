package fluxoconsultoria.ufrj.br.surgerynote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ericreis on 6/15/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SurgeryNoteDatabase";

    // TABLES
    public static final String TABLE_PROFILE = "Profile";
    public static final String TABLE_SURGERY = "Surgery";
    public static final String TABLE_MEDIA = "Media";
    public static final String TABLE_FINANCIAL_DATA = "FinancialData";
    public static final String TABLE_ALERT = "Alert";

    // Common columns
    public static final String KEY_ID = "Id";

    // PROFILE TABLE
    public static final String PROFILE_KEY_NAME = "Name";
    public static final String PROFILE_KEY_LASTNAME = "Lastname";
    public static final String PROFILE_KEY_BIRTH = "Birth";
    public static final String PROFILE_KEY_GENDER = "Gender";
    public static final String PROFILE_KEY_COUNTRY = "Country";
    public static final String PROFILE_KEY_STATE = "State";
    public static final String PROFILE_KEY_CITY = "City";
    public static final String PROFILE_KEY_EMAIL = "Email";
    public static final String PROFILE_KEY_SPECIALTY = "Specialty";
    public static final String PROFILE_KEY_PROFESSIONAL_ID = "ProfessionalId";
    public static final String PROFILE_KEY_SUBSCRIBED = "Subscribed";

    // SURGERY TABLE
    public static final String SURGERY_KEY_PACIENT_NAME = "PacientName";
    public static final String SURGERY_KEY_PACIENT_BIRTH = "PacientBirth";
    public static final String SURGERY_KEY_PACIENT_GENDER = "PacientGender";
    public static final String SURGERY_KEY_DATE = "Date";
    public static final String SURGERY_KEY_MEDICAL_RECORD = "MedicalRecord";
    public static final String SURGERY_KEY_HOSPITAL = "Hospital";
    public static final String SURGERY_KEY_DIAGNOSIS = "Diagnosis";
    public static final String SURGERY_KEY_SURGICAL_PROCEDURE = "SurgicalProcedure";
    public static final String SURGERY_KEY_MAIN_SURGERY = "MainSurgery";
    public static final String SURGERY_KEY_FIRST_ASSISTANT = "FirstAssistant";
    public static final String SURGERY_KEY_SECOND_ASSISTANT = "SecondAssistant";
    public static final String SURGERY_KEY_ANESTHESIOLOGIST = "Anesthesiologist";
    public static final String SURGERY_KEY_INSTRUMENTATION_TECHNICIAN = "InstrumentationTechnician";
    public static final String SURGERY_KEY_SURGICAL_EQUIPMENT_COMPANY = "SurgicalEquipmentCompany";
    public static final String SURGERY_KEY_SURGICAL_EQUIPMENT = "SurgicalEquipment";
    public static final String SURGERY_KEY_REVALUATION_DATE = "RevaluationDate";
    public static final String SURGERY_KEY_OBSERVATION = "Observation";
    public static final String SURGERY_KEY_MEDIA_ID = "MediaId";
    public static final String SURGERY_KEY_FINANCIAL_DATA_ID = "FinancialDataId";

    // FINANCIAL DATA TABLE
    public static final String FINANCIAL_DATA_KEY_HEALTH_INSURANCE = "HealthInsurance";
    public static final String FINANCIAL_DATA_KEY_PROCEDURE_CODE = "ProcedureCode";
    public static final String FINANCIAL_DATA_KEY_MAIN_SURGERY_VALUE = "MainSurgeryValue";
    public static final String FINANCIAL_DATA_KEY_FIRST_ASSISTANT_VALUE = "FirstAssistantValue";
    public static final String FINANCIAL_DATA_KEY_SECOND_ASSISTANT_VALUE = "SecondAssistantValue";
    public static final String FINANCIAL_DATA_KEY_ANESTHESIOLOGIST_VALUE = "AnesthesiologistValue";
    public static final String FINANCIAL_DATA_KEY_INSTRUMENTATION_TECHNICIAN_VALUE = "InstrumentationTechnicianValue";
    public static final String FINANCIAL_DATA_KEY_AMOUNT = "Amount";
    public static final String FINANCIAL_DATA_KEY_PAYDAY = "Payday";
    public static final String FINANCIAL_DATA_KEY_PAYMENT_FORECAST = "PaymentForecast";

    // MEDIA TABLE
    public static final String MEDIA_KEY_PATH = "Path";
    public static final String MEDIA_KEY_SURGERY_ID = "SurgeryId";

    private static final String CREATE_TABLE_PROFILE = "CREATE TABLE " + TABLE_PROFILE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + PROFILE_KEY_NAME +
                                                       " TEXT," + PROFILE_KEY_LASTNAME + " TEXT," + PROFILE_KEY_BIRTH + " DATETIME," + PROFILE_KEY_GENDER +
                                                       " TEXT," + PROFILE_KEY_COUNTRY + " TEXT," + PROFILE_KEY_STATE + " TEXT," + PROFILE_KEY_CITY +
                                                       " TEXT," + PROFILE_KEY_EMAIL + " TEXT," + PROFILE_KEY_SPECIALTY + " TEXT," + PROFILE_KEY_PROFESSIONAL_ID +
                                                       " TEXT," + PROFILE_KEY_SUBSCRIBED + " INTEGER" + ")";

    private static final String CREATE_TABLE_SURGERY = "CREATE TABLE " + TABLE_SURGERY + "(" + KEY_ID + " INTEGER PRIMARY KEY," + SURGERY_KEY_PACIENT_NAME +
                                                       " TEXT," + SURGERY_KEY_PACIENT_BIRTH + " DATETIME," + SURGERY_KEY_PACIENT_GENDER + " DATETIME," + SURGERY_KEY_DATE +
                                                       " DATETIME," + SURGERY_KEY_MEDICAL_RECORD + " TEXT," + SURGERY_KEY_HOSPITAL + " TEXT," + SURGERY_KEY_DIAGNOSIS +
                                                       " TEXT," + SURGERY_KEY_SURGICAL_PROCEDURE + " TEXT," + SURGERY_KEY_MAIN_SURGERY + " TEXT," + SURGERY_KEY_FIRST_ASSISTANT +
                                                       " TEXT," + SURGERY_KEY_SECOND_ASSISTANT + " TEXT," + SURGERY_KEY_ANESTHESIOLOGIST + " TEXT," + SURGERY_KEY_INSTRUMENTATION_TECHNICIAN +
                                                       " TEXT," + SURGERY_KEY_SURGICAL_EQUIPMENT_COMPANY + " TEXT," + SURGERY_KEY_SURGICAL_EQUIPMENT + " TEXT," + SURGERY_KEY_REVALUATION_DATE +
                                                       " TEXT," + SURGERY_KEY_OBSERVATION + " TEXT," + SURGERY_KEY_MEDIA_ID + " INTEGER," + SURGERY_KEY_FINANCIAL_DATA_ID + " INTEGER" + ")";

    private static final String CREATE_TABLE_FINANCIAL_DATA = "CREATE TABLE " + TABLE_FINANCIAL_DATA + "(" + KEY_ID + " INTEGER PRIMARY KEY," + FINANCIAL_DATA_KEY_HEALTH_INSURANCE +
                                                              " TEXT," + FINANCIAL_DATA_KEY_PROCEDURE_CODE + " TEXT," + FINANCIAL_DATA_KEY_MAIN_SURGERY_VALUE +
                                                              " INTEGER," + FINANCIAL_DATA_KEY_FIRST_ASSISTANT_VALUE + " INTEGER," + FINANCIAL_DATA_KEY_SECOND_ASSISTANT_VALUE +
                                                              " INTEGER," + FINANCIAL_DATA_KEY_ANESTHESIOLOGIST_VALUE + " INTEGER," + FINANCIAL_DATA_KEY_INSTRUMENTATION_TECHNICIAN_VALUE +
                                                              " INTEGER," + FINANCIAL_DATA_KEY_AMOUNT + " TEXT," + FINANCIAL_DATA_KEY_PAYDAY + " DATETIME," + FINANCIAL_DATA_KEY_PAYMENT_FORECAST +
                                                              " TEXT" + ")";

    private static final String CREATE_TABLE_MEDIA = "CREATE TABLE " + TABLE_MEDIA + "(" + KEY_ID + " INTEGER PRIMARY KEY," + MEDIA_KEY_PATH + " TEXT," + MEDIA_KEY_SURGERY_ID + " INTEGER" + ")";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_PROFILE);
        db.execSQL(CREATE_TABLE_SURGERY);
        db.execSQL(CREATE_TABLE_FINANCIAL_DATA);
        db.execSQL(CREATE_TABLE_MEDIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onCreate(db);
    }
}
