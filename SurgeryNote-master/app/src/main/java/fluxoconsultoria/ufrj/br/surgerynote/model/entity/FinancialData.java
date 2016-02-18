package fluxoconsultoria.ufrj.br.surgerynote.model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fluxoconsultoria.ufrj.br.surgerynote.Constants;
import fluxoconsultoria.ufrj.br.surgerynote.InvalidDataException;

/**
 * Created by ericreis on 7/11/15.
 */
public class FinancialData extends Object
{
    private int id, mainSurgeryValue, firstAssistantValue, secondAssistantValue, anesthesiologistValue, instrumentationTechnicianValue;
    private String healthInsurance, procedureCode, amount;
    private Date payday, paymentForecast;

    public FinancialData()
    {

    }

    @Override
    public String toString() throws NullPointerException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        String attr = healthInsurance + ", " + procedureCode + ", " + mainSurgeryValue + ", " + firstAssistantValue +
                ", " + secondAssistantValue + ", " + anesthesiologistValue + ", " + instrumentationTechnicianValue +
                ", " + amount + ", " + sdf.format(payday) + ", " + sdf.format(paymentForecast);
        return attr;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setId(long id)
    {
        this.id = (int) id;
    }

    public int getMainSugeryValue()
    {
        return mainSurgeryValue;
    }

    public void setMainSugeryValue(int mainSugeryValue) throws InvalidDataException
    {
        this.mainSurgeryValue = mainSugeryValue;
    }

    public void setMainSugeryValue(float mainSugeryValue) throws InvalidDataException
    {
        this.mainSurgeryValue = (int) mainSugeryValue;
    }

    public int getFirstAssistantValue()
    {
        return firstAssistantValue;
    }

    public void setFirstAssistantValue(int firstAssistantValue)
    {
        this.firstAssistantValue = firstAssistantValue;
    }

    public void setFirstAssistantValue(float firstAssistantValue)
    {
        this.firstAssistantValue = (int) firstAssistantValue;
    }

    public int getSecondAssistantValue()
    {
        return secondAssistantValue;
    }

    public void setSecondAssistantValue(int secondAssistantValue)
    {
        this.secondAssistantValue = secondAssistantValue;
    }

    public void setSecondAssistantValue(float secondAssistantValue)
    {
        this.secondAssistantValue = (int) secondAssistantValue;
    }

    public int getAnesthesiologistValue()
    {
        return anesthesiologistValue;
    }

    public void setAnesthesiologistValue(int anesthesiologistValue)
    {
        this.anesthesiologistValue = anesthesiologistValue;
    }

    public void setAnesthesiologistValue(float anesthesiologistValue)
    {
        this.anesthesiologistValue = (int) anesthesiologistValue;
    }

    public int getInstrumentationTechnicianValue()
    {
        return instrumentationTechnicianValue;
    }

    public void setInstrumentationTechnicianValue(int instrumentationTechnicianValue)
    {
        this.instrumentationTechnicianValue = instrumentationTechnicianValue;
    }

    public void setInstrumentationTechnicianValue(float instrumentationTechnicianValue)
    {
        this.instrumentationTechnicianValue = (int) instrumentationTechnicianValue;
    }

    public String getHealthInsurance()
    {
        return healthInsurance;
    }

    public void setHealthInsurance(String healthInsurance) throws InvalidDataException
    {
//        if (healthInsurance == null) throw new InvalidDataException("Health Insurance", new NullPointerException());
        this.healthInsurance = healthInsurance;
    }

    public String getProcedureCode()
    {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) throws InvalidDataException
    {
//        if (procedureCode == null) throw new InvalidDataException("Procedure Code", new NullPointerException());
        this.procedureCode = procedureCode;
    }

    public String getAmount()
    {
        if (amount != null)
        return amount;

        else return "";
    }

    public void setAmount(String amount) throws InvalidDataException
    {
//        if (amount == null) throw new InvalidDataException("Amount", new NullPointerException());
        this.amount = amount;
    }

    public Date getPayday()
    {
        return payday;
    }

    public String getPaydayString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        return sdf.format(this.payday);
    }

    public void setPayday(Date payday) throws InvalidDataException
    {
        if (payday == null) throw new InvalidDataException("Payday", new NullPointerException());
        this.payday = payday;
    }

    @SuppressWarnings("deprecation")
    public void setPaydayFromString(String birth) throws InvalidDataException
    {
        if (birth == null) throw new InvalidDataException("Payday", new NullPointerException());
        StringBuilder sb = new StringBuilder(birth);
        Date date = new Date(Integer.valueOf(sb.substring(6)) - Constants.YEAR_OFFSET, Integer.valueOf(sb.substring(0,2)), Integer.valueOf(sb.substring(3,5)));
        this.payday = date;
    }

    @SuppressWarnings("deprecation")
    public void setPaydayFromSQLite(String birth) throws InvalidDataException
    {
        if (birth == null) throw new InvalidDataException("Payday", new NullPointerException());
        StringBuilder sb = new StringBuilder(birth);
        Date date = new Date(Integer.valueOf(sb.substring(6)) - Constants.YEAR_OFFSET, Integer.valueOf(sb.substring(0,2)) - 1, Integer.valueOf(sb.substring(3,5)));
        this.payday = date;
    }

    public Date getPaymentForecast()
    {
        return paymentForecast;
    }
    public String getPaymentForecastString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        return sdf.format(this.payday);
    }

    public void setPaymentForecast(Date paymentForecast) throws InvalidDataException
    {
        if (paymentForecast == null) throw new InvalidDataException("Payment Forecast", new NullPointerException());
        this.paymentForecast = paymentForecast;
    }

    @SuppressWarnings("deprecation")
    public void setPaymentForecastFromString(String birth) throws InvalidDataException
    {
        if (birth == null) throw new InvalidDataException("Payment Forecast", new NullPointerException());
        StringBuilder sb = new StringBuilder(birth);
        Date date = new Date(Integer.valueOf(sb.substring(6)) - Constants.YEAR_OFFSET, Integer.valueOf(sb.substring(0,2)), Integer.valueOf(sb.substring(3,5)));
        this.paymentForecast = date;
    }

    @SuppressWarnings("deprecation")
    public void setPaymentForecastFromSQLite(String birth) throws InvalidDataException
    {
        if (birth == null) throw new InvalidDataException("Payment Forecast", new NullPointerException());
        StringBuilder sb = new StringBuilder(birth);
        Date date = new Date(Integer.valueOf(sb.substring(6)) - Constants.YEAR_OFFSET, Integer.valueOf(sb.substring(0,2)) - 1, Integer.valueOf(sb.substring(3,5)));
        this.paymentForecast = date;
    }
}
