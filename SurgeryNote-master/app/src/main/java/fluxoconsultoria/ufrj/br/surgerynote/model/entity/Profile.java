package fluxoconsultoria.ufrj.br.surgerynote.model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fluxoconsultoria.ufrj.br.surgerynote.Constants;
import fluxoconsultoria.ufrj.br.surgerynote.InvalidDataException;

/**
 * Created by ericreis on 6/12/15.
 */
public class Profile extends Object
{
    private int id;
    private String name, lastname, gender, country, state, city, email, specialty, professionalId;
    private Date birth;
    private boolean subscribed;

    public Profile()
    {

    }

    @Override
    public String toString() throws NullPointerException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        String attr = name + ", " + lastname + ", " + sdf.format(birth) + ", " + gender + ", " + country +
                      ", " + state + ", " + city + ", " + email + ", " + specialty + ", " + professionalId +
                      ", " + String.valueOf(subscribed);
        return attr;
    }

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setId(long id)
    {
        this.id = (int) id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name) throws InvalidDataException
    {
        if (name == null) throw new InvalidDataException("Name", new NullPointerException());
        this.name = name;
    }

    public String getLastname()
    {
        return this.lastname;
    }

    public void setLastname(String lastname) throws InvalidDataException
    {
        if (lastname == null) throw new InvalidDataException("Last Name", new NullPointerException());
        this.lastname = lastname;
    }

    public String getGender()
    {
        return this.gender;
    }

    public void setGender(String gender) throws InvalidDataException
    {
        if (gender == null) throw new InvalidDataException("Gender", new NullPointerException());
        this.gender = gender;
    }

    public String getCountry()
    {
        return this.country;
    }

    public void setCountry(String country) throws InvalidDataException
    {
        if (country == null) throw new InvalidDataException("Country", new NullPointerException());
        this.country = country;
    }

    public String getState()
    {
        return this.state;
    }

    public void setState(String state) throws InvalidDataException
    {
        if (state == null) throw new InvalidDataException("State", new NullPointerException());
        this.state = state;
    }

    public String getCity()
    {
        return this.city;
    }

    public void setCity(String city) throws InvalidDataException
    {
        if (city == null) throw new InvalidDataException("City", new NullPointerException());
        this.city = city;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email) throws InvalidDataException
    {
        if (email == null || !email.matches(Constants.EMAIL_VALIDATION)) throw new InvalidDataException("E-mail", new NullPointerException());
        this.email = email;
    }

    public String getSpeciality()
    {
        return this.specialty;
    }

    public void setSpeciality(String specialty) throws InvalidDataException
    {
        if (specialty == null) throw new InvalidDataException("Medical Specialty", new NullPointerException());
        this.specialty = specialty;
    }

    public String getProfessionalId()
    {
        return this.professionalId;
    }

    public void setProfessionalId(String professionalId) throws InvalidDataException
    {
        if (professionalId == null) throw new InvalidDataException("Professional Id", new NullPointerException());
        this.professionalId = professionalId;
    }

    public boolean isSubscribed()
    {
        return this.subscribed;
    }

    public void setSubscribed(boolean subscribed)
    {
        this.subscribed = subscribed;
    }

    public void setSubscribed(int subscribed)
    {
        if (subscribed == 1)
            this.subscribed = true;
        else
            this.subscribed = false;
    }

    public Date getBirth()
    {
        return this.birth;
    }

    public String getBirthString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        return sdf.format(this.birth);
    }

    public void setBirth(Date birth) throws InvalidDataException
    {
        if (birth == null) throw new InvalidDataException("Birth", new NullPointerException());
        this.birth = birth;
    }

    @SuppressWarnings("deprecation")
    public void setBirthFromString(String birth) throws InvalidDataException
    {
        if (birth == null) throw new InvalidDataException("Birth", new NullPointerException());
        StringBuilder sb = new StringBuilder(birth);
        Date date = new Date(Integer.valueOf(sb.substring(6)) - Constants.YEAR_OFFSET, Integer.valueOf(sb.substring(0,2)), Integer.valueOf(sb.substring(3,5)));
        this.birth = date;
    }

    @SuppressWarnings("deprecation")
    public void setBirthFromSQLite(String birth) throws InvalidDataException
    {
        if (birth == null) throw new InvalidDataException("Birth", new NullPointerException());
        StringBuilder sb = new StringBuilder(birth);
        Date date = new Date(Integer.valueOf(sb.substring(6)) - Constants.YEAR_OFFSET, Integer.valueOf(sb.substring(0,2)) - 1, Integer.valueOf(sb.substring(3,5)));
        this.birth = date;
    }
}
