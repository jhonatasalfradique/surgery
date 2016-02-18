package fluxoconsultoria.ufrj.br.surgerynote;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import fluxoconsultoria.ufrj.br.surgerynote.model.dao.DaoFinancialData;
import fluxoconsultoria.ufrj.br.surgerynote.model.dao.DaoMedia;
import fluxoconsultoria.ufrj.br.surgerynote.model.dao.DaoProfile;
import fluxoconsultoria.ufrj.br.surgerynote.model.dao.DaoSurgery;
import fluxoconsultoria.ufrj.br.surgerynote.model.entity.FinancialData;
import fluxoconsultoria.ufrj.br.surgerynote.model.entity.Media;
import fluxoconsultoria.ufrj.br.surgerynote.model.entity.Profile;
import fluxoconsultoria.ufrj.br.surgerynote.model.entity.Surgery;


public class SurgeryActivity extends ActionBarActivity
{
    private Button menuButton, uploadButton, showMediaButton;

    private EditText searchEditText;

    private TabHost tabHost;

    private ListView alphabeticalOrderListView, chronologicalOrderListView;
    private ImageView iconImageView;

    private DaoSurgery daoSurgery = new DaoSurgery();
    private DaoFinancialData daoFinancialData = new DaoFinancialData();
    private DaoMedia daoMedia = new DaoMedia();
    private DaoProfile daoProfile = new DaoProfile();

    private List<Surgery> surgeries, selectedSurgeries;
    private List<FinancialData> financialDatas;
    private List<ItemListView> itens;
    private List<Integer> selectedSurgeriesIndexes;
    private List<Media> medias;

    private Profile profile;

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surgery);

        Intent intent = getIntent();

        if (intent.getBooleanExtra("FROM_CALENDAR", false))
        {
            Object[] aux = (Object[]) intent.getSerializableExtra("SELECTED_SURGERIES");
            this.surgeries = Arrays.asList(Arrays.copyOf(aux, aux.length, Surgery[].class));
        }
        else
        {
            this.surgeries = this.daoSurgery.getAll();
        }
        this.financialDatas = this.daoFinancialData.getAll();

        this.tabHost = (TabHost) findViewById(R.id.newSurgeryTabHost);
        this.tabHost.setup();

        TabHost.TabSpec alphabeticalOrderTabSpec = tabHost.newTabSpec("tab_creation");
        alphabeticalOrderTabSpec.setIndicator(getApplicationContext().getString(R.string.alphabetical_order));
        alphabeticalOrderTabSpec.setContent(R.id.alphabeticalOrderTab);
        tabHost.addTab(alphabeticalOrderTabSpec);

        TabHost.TabSpec chronologicalOrderTabSpec = tabHost.newTabSpec("tab_creation");
        chronologicalOrderTabSpec.setIndicator(getApplicationContext().getString(R.string.chronological_order));
        chronologicalOrderTabSpec.setContent(R.id.chronologicalOrderTab);
        tabHost.addTab(chronologicalOrderTabSpec);

        this.alphabeticalOrderListView = (ListView) findViewById(R.id.alphabeticalOrderListView);
        this.chronologicalOrderListView = (ListView) findViewById(R.id.chronologicalOrderListView);

        this.menuButton = (Button) findViewById(R.id.menuButton);
        this.uploadButton = (Button) findViewById(R.id.uploadButton);
        this.showMediaButton = (Button) findViewById(R.id.showMediaButton);

        this.searchEditText = (EditText) findViewById(R.id.searchEditText);

        if (intent.getBooleanExtra("UPLOAD_MODE", false))
        {
            this.uploadButton.setVisibility(View.VISIBLE);
        }

        if (intent.getBooleanExtra("MEDIA_MODE", false))
        {
            this.showMediaButton.setVisibility(View.VISIBLE);
        }

        populateItens("");

        this.selectedSurgeries = new ArrayList<>();
        this.selectedSurgeriesIndexes = new ArrayList<>();

        this.menuButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(SurgeryActivity.this, MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        this.searchEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                populateItens(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        this.alphabeticalOrderListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Surgery s = surgeries.get(position);
                int financialDataPosition = s.getFinancialDataId() - 1;
                FinancialData fd = financialDatas.get(financialDataPosition);

                Intent intent = new Intent(SurgeryActivity.this, NewSurgeryActivity.class);

                Bundle extras = new Bundle();
                extras.putString("PACIENT_NAME", s.getPacientName());
                extras.putString("PACIENT_BIRTH", s.getPacientBirthString());
                extras.putString("PACIENT_GENDER", s.getPacientGender());
                extras.putString("DATE", s.getDateString());
                extras.putString("MEDICAL_RECORD", s.getMedicalRecord());
                extras.putString("HOSPITAL", s.getHospital());
                extras.putString("DIAGNOSIS", s.getDiagnosis());
                extras.putString("SURGICAL_PROCEDURE", s.getSurgicalProcedure());
                extras.putString("MAIN_SURGERY", s.getMainSurgery());
                extras.putString("FIRST_ASSISTANT", s.getFirstAssistant());
                extras.putString("SECOND_ASSISTANT", s.getSecondAssistant());
                extras.putString("ANESTHESIOLOGIST", s.getAnesthesiologist());
                extras.putString("INSTRUMENTATION_TECHNICIAN", s.getInstrumentationTechnician());
                extras.putString("SURGICAL_EQUIPMENT_COMPANY", s.getSurgicalEquipmentCompany());
                extras.putString("SURGICAL_EQUIPMENT", s.getSurgicalEquipment());
                extras.putString("REVALUATION_DATE", s.getRevaluationDateString());
                extras.putString("OBSERVATION", s.getObservation());
                extras.putString("HEALTH_INSURANCE", fd.getHealthInsurance());
                extras.putString("PROCEDURE_CODE", fd.getProcedureCode());
                extras.putInt("MAIN_SURGERY_VALUE", fd.getMainSugeryValue());
                extras.putInt("FIRST_ASSISTANT_VALUE", fd.getFirstAssistantValue());
                extras.putInt("SECOND_ASSISTANT_VALUE", fd.getSecondAssistantValue());
                extras.putInt("ANESTHESIOLOGIST_VALUE", fd.getAnesthesiologistValue());
                extras.putInt("INSTRUMENTATION_TECHNICIAN_VALUE", fd.getInstrumentationTechnicianValue());
                extras.putString("AMOUNT", fd.getAmount());
                extras.putString("PAYDAY", fd.getPaydayString());
                extras.putString("PAYMENT_FORECAST", fd.getPaymentForecastString());

                extras.putInt("SURGERY_ID", s.getId());

                intent.putExtras(extras);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        this.alphabeticalOrderListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                int color = view.getDrawingCacheBackgroundColor();

                if (!selectedSurgeriesIndexes.contains(i))
                {
                    view.setBackgroundColor(getResources().getColor(R.color.light_blue));
                    selectedSurgeriesIndexes.add(i);
                }
                else
                {
                    view.setBackgroundColor(color);
                    selectedSurgeriesIndexes.remove(i);
                }
                return true;
            }
        });

        this.chronologicalOrderListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Surgery s = surgeries.get(position);
                int financialDataPosition = s.getFinancialDataId() - 1;
                FinancialData fd = financialDatas.get(financialDataPosition);

                Intent intent = new Intent(SurgeryActivity.this, NewSurgeryActivity.class);

                Bundle extras = new Bundle();
                extras.putString("PACIENT_NAME", s.getPacientName());
                extras.putString("PACIENT_BIRTH", s.getPacientBirthString());
                extras.putString("PACIENT_GENDER", s.getPacientGender());
                extras.putString("DATE", s.getDateString());
                extras.putString("MEDICAL_RECORD", s.getMedicalRecord());
                extras.putString("HOSPITAL", s.getHospital());
                extras.putString("DIAGNOSIS", s.getDiagnosis());
                extras.putString("SURGICAL_PROCEDURE", s.getSurgicalProcedure());
                extras.putString("MAIN_SURGERY", s.getMainSurgery());
                extras.putString("FIRST_ASSISTANT", s.getFirstAssistant());
                extras.putString("SECOND_ASSISTANT", s.getSecondAssistant());
                extras.putString("ANESTHESIOLOGIST", s.getAnesthesiologist());
                extras.putString("INSTRUMENTATION_TECHNICIAN", s.getInstrumentationTechnician());
                extras.putString("SURGICAL_EQUIPMENT_COMPANY", s.getSurgicalEquipmentCompany());
                extras.putString("SURGICAL_EQUIPMENT", s.getSurgicalEquipment());
                extras.putString("REVALUATION_DATE", s.getRevaluationDateString());
                extras.putString("OBSERVATION", s.getObservation());
                extras.putString("HEALTH_INSURANCE", fd.getHealthInsurance());
                extras.putString("PROCEDURE_CODE", fd.getProcedureCode());
                extras.putInt("MAIN_SURGERY_VALUE", fd.getMainSugeryValue());
                extras.putInt("FIRST_ASSISTANT_VALUE", fd.getFirstAssistantValue());
                extras.putInt("SECOND_ASSISTANT_VALUE", fd.getSecondAssistantValue());
                extras.putInt("ANESTHESIOLOGIST_VALUE", fd.getAnesthesiologistValue());
                extras.putInt("INSTRUMENTATION_TECHNICIAN_VALUE", fd.getInstrumentationTechnicianValue());
                extras.putString("AMOUNT", fd.getAmount());
                extras.putString("PAYDAY", fd.getPaydayString());
                extras.putString("PAYMENT_FORECAST", fd.getPaymentForecastString());

                extras.putInt("SURGERY_ID", s.getId());

                intent.putExtras(extras);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        this.chronologicalOrderListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                int color = view.getDrawingCacheBackgroundColor();

                if (!selectedSurgeriesIndexes.contains(i))
                {
                    view.setBackgroundColor(getResources().getColor(R.color.light_blue));
                    selectedSurgeriesIndexes.add(i);
                }
                else
                {
                    view.setBackgroundColor(color);
                    selectedSurgeriesIndexes.remove(i);
                }
                return true;
            }
        });

        this.uploadButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                for (Integer i : selectedSurgeriesIndexes)
                {
                    selectedSurgeries.add(surgeries.get(i));
                }

                if (selectedSurgeries.size() == 0)
                {
                    if (toast != null)
                    {
                        toast.cancel();
                    }
                    toast = Toast.makeText(getApplicationContext(), "Please select at least one surgery", Toast.LENGTH_LONG);
                    toast.show();
                }
                else
                {
                    medias = new ArrayList<Media>();
                    profile = new Profile();

                    profile = daoProfile.getProfile();

                    Log.d("Test", profile.getEmail());

                    StringBuilder sb = new StringBuilder();

                    for (Surgery s : selectedSurgeries)
                    {
                        List<Media> ml = daoMedia.getBySurgery(s);
                        if (ml != null)
                        {
                            medias.addAll(ml);
                        }

                        sb.append("Surgery:\n\n");
                        sb.append("Pacient Name: " + s.getPacientName() + "\n");
                        sb.append("Pacient Birth: " + s.getPacientBirthString() + "\n");
                        sb.append("Pacient Gender: " + s.getPacientGender() + "\n");
                        sb.append("Surgery Date: " + s.getDateString() + "\n");
                        sb.append("Medical Record: " + s.getMedicalRecord() + "\n");
                        sb.append("Hospital: " + s.getHospital() + "\n");
                        sb.append("Diagnosis: " + s.getDiagnosis() + "\n");
                        sb.append("Surgical Procedure: " + s.getSurgicalProcedure() + "\n");
                        sb.append("Main Surgeon: " + s.getMainSurgery() + "\n");
                        sb.append("First Assistant: " + s.getFirstAssistant() + "\n");
                        sb.append("Second Assistant: " + s.getSecondAssistant() + "\n");
                        sb.append("Anesthesiologist: " + s.getAnesthesiologist() + "\n");
                        sb.append("Instrumentation Technician: " + s.getInstrumentationTechnician() + "\n");
                        sb.append("Surgical Equipment Company: " + s.getSurgicalEquipmentCompany() + "\n");
                        sb.append("Surgical Equipment: " + s.getSurgicalEquipment() + "\n");
                        sb.append("Revaluation Date: " + s.getRevaluationDateString() + "\n");
                        sb.append("Observation: " + s.getObservation() + "\n\n");
                        sb.append("------------------------------------------------------------\n\n");
                    }

                    Intent sendIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                    sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{profile.getEmail()});
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT, "[SurgeryNote] Uploaded Surgery");
                    sendIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());

                    ArrayList<Uri> uris = new ArrayList<Uri>();
                    for (Media m : medias)
                    {
                        uris.add(Uri.parse("file://" + m.getPath()));
                    }
                    sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);


                    sendIntent.setType("image/jpeg");

                    try
                    {
                        startActivity(Intent.createChooser(sendIntent, "Send Mail"));
                    }
                    catch (android.content.ActivityNotFoundException ex)
                    {
                        Toast.makeText(SurgeryActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        this.showMediaButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                selectedSurgeries = new ArrayList<Surgery>();
                for (Integer i : selectedSurgeriesIndexes)
                {
                    selectedSurgeries.add(surgeries.get(i));
                }

                if (selectedSurgeries.size() == 0)
                {
                    if (toast != null)
                    {
                        toast.cancel();
                    }
                    toast = Toast.makeText(getApplicationContext(), "Please select at least one surgery", Toast.LENGTH_LONG);
                    toast.show();
                }
                else
                {
                    medias = new ArrayList<Media>();
                    for (Surgery s : selectedSurgeries)
                    {
                        List<Media> ml = daoMedia.getBySurgery(s);
                        if (ml != null)
                        {
                            medias.addAll(ml);
                        }
                    }

                    ArrayList<String> imagePaths = new ArrayList<String>();
                    for (Media m : medias)
                    {
                        imagePaths.add(m.getPath());
                    }

           //         Intent intent = new Intent(SurgeryActivity.this, GalleryActivity.class);
                    Intent intent = new Intent(SurgeryActivity.this, GalleryGridActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.putStringArrayListExtra("PATHS", imagePaths);
                    startActivity(intent);
                }

            }
        });
    }

    private void populateItens(String searchedWord)
    {
        this.itens = new ArrayList<ItemListView>();

        if (this.surgeries != null)
        {
            for (Surgery s : this.surgeries)
            {
                int financialDataPosition = s.getFinancialDataId() - 1;
                FinancialData fd = this.financialDatas.get(financialDataPosition);

                int iconId = 0;
                switch (fd.getAmount())
                {
                    case "Amount Paid":
                        iconId = R.drawable.green_circle;
                        break;
                    case "Not Paid":
                        iconId = R.drawable.red_circle;
                        break;
                    case "Not Aplicable":
                        iconId = R.drawable.blue_circle;
                        break;
                    default:
                        Log.d("Defaul Case", "Amount Error");
                }

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

                String pacientName, date, diagnostic;
                pacientName = "Pacient Name: " + s.getPacientName();
                date = "Date: " + sdf.format(s.getDate());
                diagnostic = "Diagnostic: " + s.getDiagnosis();

                ItemListView item = new ItemListView(pacientName, date, diagnostic, iconId);

                if (s.getPacientName().toLowerCase().contains(searchedWord.toLowerCase()) || s.getDiagnosis().toLowerCase().contains(searchedWord.toLowerCase()))
                {
                    Log.d("Test", searchedWord);
                    this.itens.add(item);
                }

                AdapterListView adapter = new AdapterListView(this, this.itens);
                this.alphabeticalOrderListView.setAdapter(adapter);
                this.chronologicalOrderListView.setAdapter(adapter);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_surgery, menu);
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
