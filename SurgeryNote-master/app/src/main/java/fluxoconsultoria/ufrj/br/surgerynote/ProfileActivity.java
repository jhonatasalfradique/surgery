package fluxoconsultoria.ufrj.br.surgerynote;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import fluxoconsultoria.ufrj.br.surgerynote.model.dao.DaoProfile;
import fluxoconsultoria.ufrj.br.surgerynote.model.entity.Media;
import fluxoconsultoria.ufrj.br.surgerynote.model.entity.Profile;
import fluxoconsultoria.ufrj.br.surgerynote.model.entity.Surgery;


public class ProfileActivity extends ActionBarActivity
{
    private Button menuButton, saveButton;
    private EditText nameEditText, lastnameEditText, birthDatePickerEditText, stateEditText, cityEditText,
                     emailEditText, specialtyEditText, professionalIdEditText;

    private Spinner genderSpinner, countrySpinner;

    private String name, lastname, gender, country, state, city, email, specialty, professionalId;
    private Date birth;
    private boolean subscribed;

    private List<String> genders, countries;

    private Profile profile;

    private DaoProfile daoProfile = new DaoProfile();

    private boolean emptyProfile = false;

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.profile = daoProfile.getProfile();

        if (profile == null)
        {
            emptyProfile = true;
        }

        this.menuButton = (Button) findViewById(R.id.menuButton);
        this.saveButton = (Button) findViewById(R.id.saveProfileButton);

        this.nameEditText = (EditText) findViewById(R.id.nameEditText);
        this.lastnameEditText = (EditText) findViewById(R.id.lastnameEditText);
        this.birthDatePickerEditText = (EditText) findViewById(R.id.birthDatePickerEditText);
        this.stateEditText = (EditText) findViewById(R.id.stateEditText);
        this.cityEditText = (EditText) findViewById(R.id.cityEditText);
        this.emailEditText = (EditText) findViewById(R.id.emailEditText);
        this.specialtyEditText = (EditText) findViewById(R.id.specialtyEditText);
        this.professionalIdEditText = (EditText) findViewById(R.id.professionalIdEditText);

        // Get countries list from resources
        this.countries = Arrays.asList(getResources().getStringArray(R.array.countries_array));
        this.genders = Arrays.asList(getResources().getStringArray(R.array.genders_array));

        // Create country spinner from resources
        this.countrySpinner = (Spinner) findViewById(R.id.countrySpinner);
        final ArrayAdapter<CharSequence> countryAdapter = ArrayAdapter.createFromResource(this, R.array.countries_array, R.layout.spinner_item);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countryAdapter.notifyDataSetChanged();
        this.countrySpinner.setAdapter(countryAdapter);

        // Create gender spinner from resources
        this.genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        final ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this, R.array.genders_array, R.layout.spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countryAdapter.notifyDataSetChanged();
        this.genderSpinner.setAdapter(genderAdapter);

        // Set profile if already edited
        if (this.profile != null)
        {
            this.nameEditText.setText(this.profile.getName());
            this.lastnameEditText.setText(this.profile.getLastname());
            this.birthDatePickerEditText.setText(this.profile.getBirthString());
            this.genderSpinner.setSelection(this.genders.indexOf(this.profile.getGender()));
            this.countrySpinner.setSelection(this.countries.indexOf(this.profile.getCountry()));
            this.stateEditText.setText(this.profile.getState());
            this.cityEditText.setText(this.profile.getCity());
            this.emailEditText.setText(this.profile.getEmail());
            this.specialtyEditText.setText(this.profile.getSpeciality());
            this.professionalIdEditText.setText(this.profile.getProfessionalId());

            this.name = this.profile.getName();
            this.lastname = this.profile.getLastname();
            this.birth = this.profile.getBirth();
            this.gender = this.profile.getGender();
            this.country = this.profile.getCountry();
            this.state = this.profile.getState();
            this.city = this.profile.getCity();
            this.email = this.profile.getEmail();
            this.specialty = this.profile.getSpeciality();
            this.professionalId = this.profile.getProfessionalId();
            this.subscribed = this.profile.isSubscribed();
        }

        this.menuButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ProfileActivity.this, MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        this.nameEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (!s.toString().trim().matches(Constants.NAME_REGEX))
                {
                    name = null;
                    if (toast != null)
                    {
                        toast.cancel();
                    }
                    toast = Toast.makeText(getApplicationContext(), "Invalid characters for Name", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    name = s.toString().trim();
                }
            }
        });

        this.lastnameEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (!s.toString().trim().matches(Constants.NAME_REGEX))
                {
                    lastname = null;
                    if (toast != null)
                    {
                        toast.cancel();
                    }
                    toast = Toast.makeText(getApplicationContext(), "Invalid characters for Last Name", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    lastname = s.toString().trim();
                }
            }
        });

        this.birthDatePickerEditText.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showDatePickerDialog(v);
            }
        });

        this.birthDatePickerEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @SuppressWarnings("deprecation")
            @Override
            public void afterTextChanged(Editable s)
            {
                birth = new Date(s.toString());
            }
        });

        this.genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                genderSpinner.setSelection(position);
                gender = genderSpinner.getSelectedItem().toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        this.countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                countrySpinner.setSelection(position);
                country = countrySpinner.getSelectedItem().toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        this.stateEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (!s.toString().trim().matches(Constants.NAME_REGEX))
                {
                    state = null;
                    if (toast != null)
                    {
                        toast.cancel();
                    }
                    toast = Toast.makeText(getApplicationContext(), "Invalid characters for State", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    state = s.toString().trim();
                }
            }
        });

        this.cityEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (!s.toString().trim().matches(Constants.NAME_REGEX))
                {
                    city = null;
                    if (toast != null)
                    {
                        toast.cancel();
                    }
                    toast = Toast.makeText(getApplicationContext(), "Invalid characters for City", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    city = s.toString().trim();
                }
            }
        });



        this.emailEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                email = s.toString().trim();
            }
        });

        this.specialtyEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (!s.toString().trim().matches(Constants.SPECIALITY_REGEX))
                {
                    specialty = null;
                    if (toast != null)
                    {
                        toast.cancel();
                    }
                    toast = Toast.makeText(getApplicationContext(), "Invalid characters for Speciality", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    specialty = s.toString().trim();
                }
            }
        });

        this.professionalIdEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (!s.toString().trim().equals("") && !s.toString().trim().matches(Constants.PROFESSIONAL_ID_REGEX))
                {
                    professionalId = null;
                    if (toast != null)
                    {
                        toast.cancel();
                    }
                    toast = Toast.makeText(getApplicationContext(), "Invalid characters for Professional ID", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    professionalId = s.toString().trim();
                }
            }
        });

        this.saveButton.setOnClickListener(new View.OnClickListener()
        {
            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View v)
            {
                if (emptyProfile)
                {
                    profile = new Profile();
                }

                try
                {
                    if (professionalIdEditText.getText().toString().trim().equals(""))
                    {
                        professionalId = "";
                    }

                    profile.setName(name);
                    profile.setLastname(lastname);
                    profile.setBirth(birth);
                    profile.setGender(gender);
                    profile.setCountry(country);
                    profile.setState(state);
                    profile.setCity(city);
                    profile.setEmail(email);
                    profile.setSpeciality(specialty);
                    profile.setProfessionalId(professionalId);
                    profile.setSubscribed(false);

                    StringBuilder sb = new StringBuilder();

                    if (emptyProfile)
                    {
                        daoProfile.save(profile);
                        sb.append("New Profile Created:\n\n");
                    }
                    else
                    {
                        daoProfile.update(profile);
                        sb.append(profile.getName() + "'s Profile Updated:\n\n");
                    }

                    sb.append("Name: " + profile.getName() + "\n");
                    sb.append("Last Name: " + profile.getLastname() + "\n");
                    sb.append("Birth: " + profile.getBirthString() + "\n");
                    sb.append("Gender: " + profile.getGender() + "\n");
                    sb.append("Country: " + profile.getCountry() + "\n");
                    sb.append("State: " + profile.getState() + "\n");
                    sb.append("City: " + profile.getCity() + "\n");
                    sb.append("E-mail: " + profile.getEmail() + "\n");
                    sb.append("Speciality: " + profile.getSpeciality() + "\n");
                    sb.append("Professional ID: " + profile.getProfessionalId() + "\n\n");

                    if (!profile.isSubscribed())
                    {
                        sb.append("You are not subscribed yet. To change that buy our app from Google Play.");
                    }
                    else
                    {
                        sb.append("Thank you for purchasing our app. If you have any trouble please contact us: surgerynoteapp@gmail.com.");
                    }

                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"surgerynoteapp@gmail.com", profile.getEmail()});
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT, "[SurgeryNote] Register Completed");
                    sendIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());

                    sendIntent.setType("message/rfc822");

                    try
                    {
                        startActivityForResult(Intent.createChooser(sendIntent, "Send Mail"), 1);
                    }
                    catch (android.content.ActivityNotFoundException ex)
                    {
                        Toast.makeText(ProfileActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (InvalidDataException ide)
                {
                    ide.printStackTrace();
                    toast = Toast.makeText(getApplicationContext(), "Invalid field " + ide.getLocation(), Toast.LENGTH_SHORT);
                    toast.show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        toast = Toast.makeText(getApplicationContext(), "Profile Saved Sucessfully!", Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(ProfileActivity.this, MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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

    public void showDatePickerDialog(View v)
    {
        DialogFragment newFragment = new DatePickerFragment(v);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
