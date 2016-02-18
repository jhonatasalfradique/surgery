package fluxoconsultoria.ufrj.br.surgerynote;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import fluxoconsultoria.ufrj.br.surgerynote.model.dao.DaoFinancialData;
import fluxoconsultoria.ufrj.br.surgerynote.model.dao.DaoMedia;
import fluxoconsultoria.ufrj.br.surgerynote.model.dao.DaoSurgery;
import fluxoconsultoria.ufrj.br.surgerynote.model.entity.FinancialData;
import fluxoconsultoria.ufrj.br.surgerynote.model.entity.Media;
import fluxoconsultoria.ufrj.br.surgerynote.model.entity.Surgery;


public class NewSurgeryActivity extends ActionBarActivity {
    private TabHost tabHost;

    private Button menuButton, addMediaButton, saveButton;

    private EditText pacientNameEditText, pacientBirthEditText, dateEditText, medicalRecordEditText, hospitalEditText,
            diagnosisEditText, surgicalProcedureEditText, mainSurgeryEditText, firstAssistantEditText, secondAssistantEditText, anesthesiologistEditText,
            instrumentationTechnicianEditText, surgicalEquipmentCompanyEditText, surgicalEquipmentEditText,
            revaluationDateEditText, observationEditText, healthInsuranceEditText, procedureCodeEditText,
            mainSurgeryValueEditText, firstAssistantValueEditText, secondAssistantValueEditText, anesthesiologistValueEditText,
            instrumentationTechnicianValueEditText, paydayEditText, paymentForecastEditText;
    private Spinner pacientGenderSpinner, amountSpinner;

    private String pacientName, pacientGender, medicalRecord, hospital, diagnosis, surgicalProcedure, mainSurgery, firstAssistant, secondAssistant,
            anesthesiologist, instrumentationTechnician, surgicalEquipmentCompany, surgicalEquipment, observation, healthInsurance,
            procedureCode, amount;
    private Date pacientBirth, date, revaluationDate, payday, paymentForecast;
    private float mainSurgeryValue, firstAssistantValue, secondAssistantValue, anesthesiologistValue, instrumentationTechnicianValue;

    private List<String> genders, amounts;

    private DaoSurgery daoSurgery = new DaoSurgery();
    private DaoFinancialData daoFinancialData = new DaoFinancialData();
    private DaoMedia daoMedia = new DaoMedia();

    private List<Surgery> surgeries;
    private List<FinancialData> financialDatas;
    private List<Media> medias;

    private Bitmap bitmap;

    private Toast toast;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @SuppressWarnings("deprecation")
    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_surgery);

        this.surgeries = this.daoSurgery.getAll();
        this.financialDatas = this.daoFinancialData.getAll();

        this.medias = new ArrayList<>();

        if (this.surgeries != null) {
            Log.d("Size", String.valueOf(this.surgeries.size()));
            for (Surgery s : this.surgeries) {
                Log.d("Registered Surgeries", s.getId() + ": " + s.toString());
            }
            for (FinancialData fd : this.financialDatas) {
                Log.d("Registered Financial Datas", fd.getId() + ": " + fd.toString());
            }
        }

        this.tabHost = (TabHost) findViewById(R.id.newSurgeryTabHost);
        this.tabHost.setup();

        TabHost.TabSpec mainInformationTabSpec = tabHost.newTabSpec("tab_creation");
        mainInformationTabSpec.setIndicator(getApplicationContext().getString(R.string.main_information));
        mainInformationTabSpec.setContent(R.id.mainInformationTab);
        tabHost.addTab(mainInformationTabSpec);

        TabHost.TabSpec surgeryInformationTabSpec = tabHost.newTabSpec("tab_creation");
        surgeryInformationTabSpec.setIndicator(getApplicationContext().getString(R.string.surgery_information));
        surgeryInformationTabSpec.setContent(R.id.surgeryInformationTab);
        tabHost.addTab(surgeryInformationTabSpec);

        TabHost.TabSpec financialDataTabSpec = tabHost.newTabSpec("tab_creation");
        financialDataTabSpec.setIndicator(getApplicationContext().getString(R.string.financial_data));
        financialDataTabSpec.setContent(R.id.financialDataTab);
        tabHost.addTab(financialDataTabSpec);

        this.menuButton = (Button) findViewById(R.id.menuButton);
        this.addMediaButton = (Button) findViewById(R.id.addMediaButton);
        this.saveButton = (Button) findViewById(R.id.saveSurgeryButton);

        this.pacientNameEditText = (EditText) findViewById(R.id.pacientNameEditText);
        this.pacientBirthEditText = (EditText) findViewById(R.id.pacientBirthEditText);
        this.dateEditText = (EditText) findViewById(R.id.dateEditText);
        this.medicalRecordEditText = (EditText) findViewById(R.id.medicalRecordEditText);
        this.hospitalEditText = (EditText) findViewById(R.id.hospitalEditText);
        this.diagnosisEditText = (EditText) findViewById(R.id.diagnosisEditText);
        this.surgicalProcedureEditText = (EditText) findViewById(R.id.surgicalProcedureEditText);
        this.mainSurgeryEditText = (EditText) findViewById(R.id.mainSurgeryEditText);
        this.firstAssistantEditText = (EditText) findViewById(R.id.firstAssistantEditText);
        this.secondAssistantEditText = (EditText) findViewById(R.id.secondAssistantEditText);
        this.anesthesiologistEditText = (EditText) findViewById(R.id.anesthesiologistEditText);
        this.instrumentationTechnicianEditText = (EditText) findViewById(R.id.instrumentationTechnicianEditText);
        this.surgicalEquipmentCompanyEditText = (EditText) findViewById(R.id.surgicalEquipmentCompanyEditText);
        this.surgicalEquipmentEditText = (EditText) findViewById(R.id.surgicalEquipmentEditText);
        this.revaluationDateEditText = (EditText) findViewById(R.id.revaluationDateEditText);
        this.observationEditText = (EditText) findViewById(R.id.observationEditText);
        this.healthInsuranceEditText = (EditText) findViewById(R.id.healthInsuranceEditText);
        this.procedureCodeEditText = (EditText) findViewById(R.id.procedureCodeEditText);
        this.mainSurgeryValueEditText = (EditText) findViewById(R.id.mainSurgeryValueEditText);
        this.firstAssistantValueEditText = (EditText) findViewById(R.id.firstAssistantValueEditText);
        this.secondAssistantValueEditText = (EditText) findViewById(R.id.secondAssistantValueEditText);
        this.anesthesiologistValueEditText = (EditText) findViewById(R.id.anesthesiologistValueEditText);
        this.instrumentationTechnicianValueEditText = (EditText) findViewById(R.id.instrumentationTechnicianValueEditText);
        this.paydayEditText = (EditText) findViewById(R.id.paydayEditText);
        this.paymentForecastEditText = (EditText) findViewById(R.id.paymentForecastEditText);

        // Get genders and amount list from resources
        this.genders = Arrays.asList(getResources().getStringArray(R.array.genders_array));
        this.amounts = Arrays.asList(getResources().getStringArray(R.array.amount_array));
        int[] images = {R.drawable.green_circle, R.drawable.red_circle, R.drawable.blue_circle};

        // Create gender and amount spinner from resources
        this.pacientGenderSpinner = (Spinner) findViewById(R.id.pacientGenderSpinner);
        final ArrayAdapter<CharSequence> countryAdapter = ArrayAdapter.createFromResource(this, R.array.genders_array, R.layout.spinner_item);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countryAdapter.notifyDataSetChanged();
        this.pacientGenderSpinner.setAdapter(countryAdapter);

        this.amountSpinner = (Spinner) findViewById(R.id.amountSpinner);
        this.amountSpinner.setAdapter(new AdapterSpinner(NewSurgeryActivity.this, R.layout.row, this.amounts, images));

        this.pacientBirth = new Date();
        this.date = new Date();
        this.revaluationDate = new Date();
        this.payday =  new Date();
        this.paymentForecast = new Date();
        // Set Data from Bundle
        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.pacientName = extras.getString("PACIENT_NAME");
            this.pacientBirth = new Date(extras.getString("PACIENT_BIRTH"));
            this.pacientGender = extras.getString("PACIENT_GENDER");
            this.date = new Date(extras.getString("DATE"));
            this.medicalRecord = extras.getString("MEDICAL_RECORD");
            this.hospital = extras.getString("HOSPITAL");
            this.diagnosis = extras.getString("DIAGNOSIS");
            this.surgicalProcedure = extras.getString("SURGICAL_PROCEDURE");
            this.mainSurgery = extras.getString("MAIN_SURGERY");
            this.firstAssistant = extras.getString("FIRST_ASSISTANT");
            this.secondAssistant = extras.getString("SECOND_ASSISTANT");
            this.anesthesiologist = extras.getString("ANESTHESIOLOGIST");
            this.instrumentationTechnician = extras.getString("INSTRUMENTATION_TECHNICIAN");
            this.surgicalEquipmentCompany = extras.getString("SURGICAL_EQUIPMENT_COMPANY");
            this.surgicalEquipment = extras.getString("SURGICAL_EQUIPMENT");
            this.revaluationDate = new Date(extras.getString("REVALUATION_DATE"));
            this.observation = extras.getString("OBSERVATION");
            this.healthInsurance = extras.getString("HEALTH_INSURANCE");
            this.procedureCode = extras.getString("PROCEDURE_CODE");
            this.mainSurgeryValue = extras.getInt("MAIN_SURGERY_VALUE");
            this.firstAssistantValue = extras.getInt("FIRST_ASSISTANT_VALUE");
            this.secondAssistantValue = extras.getInt("SECOND_ASSISTANT_VALUE");
            this.anesthesiologistValue = extras.getInt("ANESTHESIOLOGIST_ASSISTANT_VALUE");
            this.instrumentationTechnicianValue = extras.getInt("INSTRUMENTATION_TECHNICIAN_VALUE");
            this.amount = extras.getString("AMOUNT");
            this.payday = new Date(extras.getString("PAYDAY"));
            this.paymentForecast = new Date(extras.getString("PAYMENT_FORECAST"));

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

            this.pacientNameEditText.setText(this.pacientName);
            this.pacientBirthEditText.setText(sdf.format(this.pacientBirth));
            this.pacientGenderSpinner.setSelection(this.genders.indexOf(this.pacientGender));
            this.dateEditText.setText(sdf.format(this.date));
            this.medicalRecordEditText.setText(this.medicalRecord);
            this.hospitalEditText.setText(this.hospital);
            this.diagnosisEditText.setText(this.diagnosis);
            this.surgicalProcedureEditText.setText(this.surgicalProcedure);
            this.mainSurgeryEditText.setText(this.mainSurgery);
            this.firstAssistantEditText.setText(this.firstAssistant);
            this.secondAssistantEditText.setText(this.secondAssistant);
            this.anesthesiologistEditText.setText(this.anesthesiologist);
            this.instrumentationTechnicianEditText.setText(this.instrumentationTechnician);
            this.surgicalEquipmentCompanyEditText.setText(this.surgicalEquipmentCompany);
            this.surgicalEquipmentEditText.setText(this.surgicalEquipment);
            this.revaluationDateEditText.setText(sdf.format(this.revaluationDate));
            this.observationEditText.setText(this.observation);
            this.healthInsuranceEditText.setText(this.healthInsurance);
            this.procedureCodeEditText.setText(this.procedureCode);
            this.mainSurgeryValueEditText.setText(String.valueOf(this.mainSurgeryValue / 100));
            this.firstAssistantValueEditText.setText(String.valueOf(this.firstAssistantValue / 100));
            this.secondAssistantValueEditText.setText(String.valueOf(this.secondAssistantValue / 100));
            this.anesthesiologistValueEditText.setText(String.valueOf(this.anesthesiologistValue / 100));
            this.instrumentationTechnicianValueEditText.setText(String.valueOf(this.instrumentationTechnicianValue / 100));
            this.amountSpinner.setSelection(this.amounts.indexOf(this.amount));
            this.paydayEditText.setText(sdf.format(this.payday));
            this.paymentForecastEditText.setText(sdf.format(this.paymentForecast));
        }

        this.menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewSurgeryActivity.this, MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        this.pacientNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().matches(Constants.NAME_REGEX)) {
                    pacientName = null;
                    if (toast != null) {
                        toast.cancel();
                    }
                    toast = Toast.makeText(getApplicationContext(), "Invalid characters for Name", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    pacientName = s.toString().trim();
                }
            }
        });

        this.pacientBirthEditText.setText((new SimpleDateFormat("MM/dd/yyyy").format(new Date())));
        this.pacientBirthEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        this.pacientBirthEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressWarnings("deprecation")
            @Override
            public void afterTextChanged(Editable s) {
                pacientBirth = new Date(s.toString());
            }
        });

        this.pacientGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pacientGenderSpinner.setSelection(position);
                pacientGender = pacientGenderSpinner.getSelectedItem().toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //SimpleDateFormat sdf = new SimpleDateFormat( "MMddyyyy" );
        //this.dateEditText.setText( sdf.format( new Date() ));
        this.dateEditText.setText((new SimpleDateFormat("MM/dd/yyyy").format(new Date())));
        this.dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        this.dateEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressWarnings("deprecation")
            @Override
            public void afterTextChanged(Editable s) {
                date = new Date(s.toString());
            }
        });

        this.medicalRecordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                medicalRecord = s.toString().trim();
            }
        });

        this.hospitalEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                hospital = s.toString().trim();
            }
        });

        this.diagnosisEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                diagnosis = s.toString().trim();
            }
        });

        this.surgicalProcedureEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                surgicalProcedure = s.toString().trim();
            }
        });

        this.mainSurgeryEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mainSurgery = s.toString().trim();
            }
        });

        this.firstAssistantEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                firstAssistant = s.toString().trim();
            }
        });

        this.secondAssistantEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                secondAssistant = s.toString().trim();
            }
        });

        this.anesthesiologistEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                anesthesiologist = s.toString().trim();
            }
        });

        this.instrumentationTechnicianEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                instrumentationTechnician = s.toString().trim();
            }
        });

        this.surgicalEquipmentCompanyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                surgicalEquipmentCompany = s.toString().trim();
            }
        });

        this.surgicalEquipmentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                surgicalEquipment = s.toString().trim();
            }
        });

        //SimpleDateFormat sdf2 = new SimpleDateFormat( "dd/MM/yyyy" );
        this.revaluationDateEditText.setText((new SimpleDateFormat("MM/dd/yyyy").format(new Date())));
        this.revaluationDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        this.revaluationDateEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressWarnings("deprecation")
            @Override
            public void afterTextChanged(Editable s) {
                revaluationDate = new Date(s.toString());
            }
        });

        this.observationEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                observation = s.toString().trim();
            }
        });

        this.healthInsuranceEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                healthInsurance = s.toString().trim();
            }
        });

        this.procedureCodeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().equals("")) {
                    procedureCode = null;
                    if (toast != null) {
                        toast.cancel();
                    }
                    toast = Toast.makeText(getApplicationContext(), "Invalid characters for Procedure Code", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    procedureCode = s.toString().trim();
                }
            }
        });

        this.mainSurgeryValueEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    mainSurgeryValue = Float.valueOf(s.toString()) * 100;
                } else {
                    mainSurgeryValue = 0;
                }
            }
        });

        this.firstAssistantValueEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    firstAssistantValue = Float.valueOf(s.toString()) * 100;
                } else {
                    firstAssistantValue = 0;
                }
            }
        });

        this.secondAssistantValueEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    secondAssistantValue = Float.valueOf(s.toString()) * 100;
                } else {
                    secondAssistantValue = 0;
                }
            }
        });

        this.anesthesiologistValueEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    anesthesiologistValue = Float.valueOf(s.toString()) * 100;
                } else {
                    anesthesiologistValue = 0;
                }
            }
        });

        this.instrumentationTechnicianValueEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    instrumentationTechnicianValue = Float.valueOf(s.toString()) * 100;

                } else {
                    instrumentationTechnicianValue = 0;
                }
            }
        });

        this.amountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                amountSpinner.setSelection(position);
                amount = amountSpinner.getSelectedItem().toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.paydayEditText.setText((new SimpleDateFormat("MM/dd/yyyy").format(new Date())));
        this.paydayEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        this.paydayEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressWarnings("deprecation")
            @Override
            public void afterTextChanged(Editable s) {
                payday = new Date(s.toString());
            }
        });
        this.paymentForecastEditText.setText((new SimpleDateFormat("MM/dd/yyyy").format(new Date())));
        this.paymentForecastEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        this.paymentForecastEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressWarnings("deprecation")
            @Override
            public void afterTextChanged(Editable s) {
                paymentForecast = new Date(s.toString());
            }
        });

        this.addMediaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Build galleryIntent
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryIntent.setType("image/*");

                Intent chooser = new Intent(Intent.ACTION_CHOOSER);
                chooser.putExtra(Intent.EXTRA_INTENT, galleryIntent);
                chooser.putExtra(Intent.EXTRA_TITLE, "Select application to get the photo");

                startActivityForResult(chooser, 1);
            }
        });

        this.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Surgery surgery = new Surgery();
                FinancialData financialData = new FinancialData();

                try {
                    if (extras != null) {
                        surgery.setId(extras.getInt("SURGERY_ID"));
                    }
                    surgery.setPacientName(pacientName);
                    surgery.setPacientBirth(pacientBirth);
                    surgery.setPacientGender(pacientGender);
                    surgery.setDate(date);
                    surgery.setMedicalRecord(medicalRecord);
                    surgery.setHospital(hospital);
                    surgery.setDiagnosis(diagnosis);
                    surgery.setSurgicalProcedure(surgicalProcedure);
                    surgery.setMainSurgery(mainSurgery);
                    surgery.setFirstAssistant(firstAssistant);
                    surgery.setSecondAssistant(secondAssistant);
                    surgery.setAnesthesiologist(anesthesiologist);
                    surgery.setInstrumentationTechnician(instrumentationTechnician);
                    surgery.setSurgicalEquipmentCompany(surgicalEquipmentCompany);
                    surgery.setSurgicalEquipment(surgicalEquipment);
                    surgery.setRevaluationDate(revaluationDate);
                    surgery.setObservation(observation);

                    financialData.setHealthInsurance(healthInsurance);
                    financialData.setProcedureCode(procedureCode);
                    financialData.setMainSugeryValue(mainSurgeryValue);
                    financialData.setFirstAssistantValue(firstAssistantValue);
                    financialData.setSecondAssistantValue(secondAssistantValue);
                    financialData.setAnesthesiologistValue(anesthesiologistValue);
                    financialData.setInstrumentationTechnicianValue(instrumentationTechnicianValue);
                    financialData.setAmount(amount);
                    financialData.setPayday(payday);
                    financialData.setPaymentForecast(paymentForecast);

                    boolean updateFinancialData = updateFinancialData(financialData);
                    if (!updateFinancialData) {
                        Log.d("Financial Data Saved", financialData.toString());
                        daoFinancialData.save(financialData);
                    }

                    surgery.setFinancialDataId(financialData.getId());

                    boolean updateSurgery = updateSurgery(surgery);
                    if (!updateSurgery) {
                        Log.d("Surgery Saved", surgery.toString());
                        daoSurgery.save(surgery);
                    }

                    for (Media m : medias) {
                        m.setSurgeryId(surgery.getId());
                        Log.d("Media saved", m.toString());
                        daoMedia.save(m);
                    }

                    addEvent(surgery.getPacientName(), surgery.getHospital(), surgery.getDiagnosis(), surgery.getDate());
                    addRevaluationEvent(surgery.getPacientName(), surgery.getHospital(), surgery.getDiagnosis(), surgery.getRevaluationDate());

                    toast = Toast.makeText(getApplicationContext(), "Surgery Saved Sucessfully!", Toast.LENGTH_SHORT);
                    toast.show();

                    Intent intent = new Intent(NewSurgeryActivity.this, MenuActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                } catch (InvalidDataException ide) {
                    ide.printStackTrace();
                    toast = Toast.makeText(getApplicationContext(), "Invalid field " + ide.getLocation() + ". Please fill all the dates.", Toast.LENGTH_SHORT);
                    toast.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void addEvent(String pacientName, String hospital, String diagnosis, Date surgeryDate) {
        ContentValues values = new ContentValues();
        values.put(CalendarProvider.COLOR, Event.COLOR_RED);
        values.put(CalendarProvider.DESCRIPTION, "Diagnosis: " + diagnosis);
        values.put(CalendarProvider.LOCATION, "Hospital: " + hospital);
        values.put(CalendarProvider.EVENT, "Surgery Pacient: " + pacientName);

        Calendar cal = Calendar.getInstance();
        TimeZone tz = TimeZone.getDefault();

        cal.setTime(surgeryDate);

        int julianDay = Time.getJulianDay(cal.getTimeInMillis(), TimeUnit.MILLISECONDS.toSeconds(tz.getOffset(cal.getTimeInMillis())));

        values.put(CalendarProvider.START, cal.getTimeInMillis());
        values.put(CalendarProvider.START_DAY, julianDay);

        cal.setTime(surgeryDate);

        int endDayJulian = Time.getJulianDay(cal.getTimeInMillis(), TimeUnit.MILLISECONDS.toSeconds(tz.getOffset(cal.getTimeInMillis())));

        values.put(CalendarProvider.END, cal.getTimeInMillis());
        values.put(CalendarProvider.END_DAY, endDayJulian);

        Uri uri = getContentResolver().insert(CalendarProvider.CONTENT_URI, values);
    }

    private void addRevaluationEvent(String pacientName, String hospital, String diagnosis, Date revaluationDate) {
        ContentValues values = new ContentValues();
        values.put(CalendarProvider.COLOR, Event.COLOR_YELLOW);
        values.put(CalendarProvider.DESCRIPTION, "Diagnosis: " + diagnosis);
        values.put(CalendarProvider.LOCATION, "Hospital: " + hospital);
        values.put(CalendarProvider.EVENT, "Reavalution Pacient: " + pacientName);

        Calendar cal = Calendar.getInstance();
        TimeZone tz = TimeZone.getDefault();

        cal.setTime(revaluationDate);

        int julianDay = Time.getJulianDay(cal.getTimeInMillis(), TimeUnit.MILLISECONDS.toSeconds(tz.getOffset(cal.getTimeInMillis())));

        values.put(CalendarProvider.START, cal.getTimeInMillis());
        values.put(CalendarProvider.START_DAY, julianDay);

        cal.setTime(revaluationDate);

        int endDayJulian = Time.getJulianDay(cal.getTimeInMillis(), TimeUnit.MILLISECONDS.toSeconds(tz.getOffset(cal.getTimeInMillis())));

        values.put(CalendarProvider.END, cal.getTimeInMillis());
        values.put(CalendarProvider.END_DAY, endDayJulian);

        Uri uri = getContentResolver().insert(CalendarProvider.CONTENT_URI, values);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            if (data.getData() != null) {
                try {
                    if (bitmap != null) {
                        bitmap.recycle();
                    }

                    InputStream stream = getContentResolver().openInputStream(data.getData());
                    bitmap = BitmapFactory.decodeStream(stream);
                    stream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                bitmap = (Bitmap) data.getExtras().get("data");
            }

            String filePath = storeImage(bitmap);
            Log.d("FilePath", filePath);

            Media media = new Media();
            media.setPath(filePath);

            medias.add(media);

            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private String storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();

        if (pictureFile == null) {
            Log.d("Create MediaFile Error", "Error creating media file, check storage permissions");
            return null;
        }

        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pictureFile.getAbsolutePath();
    }

    private File getOutputMediaFile() {

        File mediaStorageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/Android/data/" +
                getApplicationContext().getPackageName() +
                "/Files");

        Log.d("Test", getApplicationContext().getPackageName());

        // Creates the storage dir if it does not exists
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Log.d("Test", "External Storage is " + Environment.getExternalStorageState());
            if (!mediaStorageDir.exists()) {
                Log.d("Test", "Dir does not exists");
                if (!mediaStorageDir.mkdirs()) {
                    Log.d("Test", "Failed to create dir");
                    return null;
                }
            }
        }

        String timestamp = new SimpleDateFormat("MMddyyyy_HHmmss").format(new Date());
        String dateString = new SimpleDateFormat("MMddyyyy").format(date);
        String fileName = "MI_" + dateString + "_" + pacientName + "_" + timestamp + ".jpg";
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + fileName);

        return mediaFile;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_surgery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment(v);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private boolean updateFinancialData(FinancialData financialData) {
        if (financialDatas != null) {
            for (FinancialData fd : financialDatas) {
                // Update Financial Data
                if (fd.getId() == financialData.getId()) {
                    Log.d("Financial Data Updated", financialData.toString());
                    daoFinancialData.update(financialData);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private boolean updateSurgery(Surgery surgery) {
        if (surgeries != null) {
            for (Surgery s : surgeries) {
                if (s.getId() == surgery.getId()) {
                    Log.d("Surgery Updated", surgery.toString());
                    daoSurgery.update(surgery);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "NewSurgery Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://fluxoconsultoria.ufrj.br.surgerynote/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "NewSurgery Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://fluxoconsultoria.ufrj.br.surgerynote/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
