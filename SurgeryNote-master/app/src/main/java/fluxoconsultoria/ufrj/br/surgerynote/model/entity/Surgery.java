package fluxoconsultoria.ufrj.br.surgerynote.model.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fluxoconsultoria.ufrj.br.surgerynote.Constants;
import fluxoconsultoria.ufrj.br.surgerynote.InvalidDataException;

/**
 * Created by ericreis on 6/24/15.
 */
public class Surgery extends Object implements Serializable
{
    private int id, mediaId, financialDataId;
    private String pacientName, pacientGender, medicalRecord, hospital, diagnosis, surgicalProcedure, mainSurgery, firstAssistant, secondAssistant,
            anesthesiologist, instrumentationTechnician, surgicalEquipmentCompany, surgicalEquipment, observation;
    private Date pacientBirth, date, revaluationDate;

    public Surgery()
    {

    }

    @Override
    public String toString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        String attr = pacientName + ", " + sdf.format(pacientBirth) + ", " + pacientGender + ", " + sdf.format(date) +  ", " + medicalRecord +
                ", " + hospital + ", " + diagnosis + ", " + surgicalProcedure + ", " + mainSurgery + ", " + firstAssistant + ", " + secondAssistant +
                ", " + anesthesiologist + ", " + instrumentationTechnician + ", " + surgicalEquipmentCompany + ", " + surgicalEquipment +
                ", " + sdf.format(revaluationDate) + ", " +  observation + ", " +  mediaId + ", " +  financialDataId;
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

    public int getMediaId()
    {
        return mediaId;
    }

    public void setMediaId(int mediaId)
    {
        this.mediaId = mediaId;
    }

    public void setMediaId(long mediaId)
    {
        this.mediaId = (int) mediaId;
    }

    public int getFinancialDataId()
    {
        return financialDataId;
    }

    public void setFinancialDataId(int financialDataId)
    {
        this.financialDataId = financialDataId;
    }

    public void setFinancialDataId(long financialDataId)
    {
        this.financialDataId = (int) financialDataId;
    }

    public String getPacientName()
    {
        return pacientName;
    }

    public void setPacientName(String pacientName) throws InvalidDataException
    {
        if (pacientName == null) throw new InvalidDataException("Pacient Name", new NullPointerException());
        this.pacientName = pacientName;
    }

    public String getPacientGender()
    {
        return pacientGender;
    }

    public void setPacientGender(String pacientGender) throws InvalidDataException
    {
//        if (pacientGender == null) throw new InvalidDataException("Pacient Gender", new NullPointerException());
        this.pacientGender = pacientGender;
    }

    public String getMedicalRecord()
    {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) throws InvalidDataException
    {
//        if (medicalRecord == null) throw new InvalidDataException("Medical Record", new NullPointerException());
        this.medicalRecord = medicalRecord;
    }

    public String getHospital()
    {
        return hospital;
    }

    public void setHospital(String hospital) throws InvalidDataException
    {
//        if (hospital == null) throw new InvalidDataException("Hospital", new NullPointerException());
        this.hospital = hospital;
    }

    public String getDiagnosis()
    {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) throws InvalidDataException
    {
        if (diagnosis == null) throw new InvalidDataException("Diagnosis", new NullPointerException());
        this.diagnosis = diagnosis;
    }

    public String getSurgicalProcedure()
    {
        return this.surgicalProcedure;
    }

    public void setSurgicalProcedure(String surgicalProcedure) throws InvalidDataException
    {
//        if (surgicalProcedure == null) throw new InvalidDataException("Surgical Procedure", new NullPointerException());
        this.surgicalProcedure = surgicalProcedure;
    }

    public String getMainSurgery()
    {
        return mainSurgery;
    }

    public void setMainSurgery(String mainSurgery) throws InvalidDataException
    {
//        if (mainSurgery == null) throw new InvalidDataException("Main Surgery", new NullPointerException());
        this.mainSurgery = mainSurgery;
    }

    public String getFirstAssistant()
    {
        return firstAssistant;
    }

    public void setFirstAssistant(String firstAssistant) throws InvalidDataException
    {
//        if (firstAssistant == null) throw new InvalidDataException("First Assistant", new NullPointerException());
        this.firstAssistant = firstAssistant;
    }

    public String getSecondAssistant()
    {
        return secondAssistant;
    }

    public void setSecondAssistant(String secondAssistant) throws InvalidDataException
    {
//        if (secondAssistant == null) throw new InvalidDataException("Second Assistant", new NullPointerException());
        this.secondAssistant = secondAssistant;
    }

    public String getAnesthesiologist()
    {
        return anesthesiologist;
    }

    public void setAnesthesiologist(String anesthesiologist) throws InvalidDataException
    {
//        if (anesthesiologist == null) throw new InvalidDataException("Anesthesiologist", new NullPointerException());
        this.anesthesiologist = anesthesiologist;
    }

    public String getInstrumentationTechnician()
    {
        return instrumentationTechnician;
    }

    public void setInstrumentationTechnician(String instrumentationTechnician) throws InvalidDataException
    {
//        if (instrumentationTechnician == null) throw new InvalidDataException("Instrumentation Technician", new NullPointerException());
        this.instrumentationTechnician = instrumentationTechnician;
    }

    public String getSurgicalEquipmentCompany()
    {
        return surgicalEquipmentCompany;
    }

    public void setSurgicalEquipmentCompany(String surgicalEquipmentCompany) throws InvalidDataException
    {
//        if (surgicalEquipmentCompany == null) throw new InvalidDataException("Surgical Equipment Company", new NullPointerException());
        this.surgicalEquipmentCompany = surgicalEquipmentCompany;
    }

    public String getSurgicalEquipment()
    {
        return surgicalEquipment;
    }

    public void setSurgicalEquipment(String surgicalEquipment) throws InvalidDataException
    {
//        if (surgicalEquipment == null) throw new InvalidDataException("Surgical Equipment", new NullPointerException());
        this.surgicalEquipment = surgicalEquipment;
    }

    public String getObservation()
    {
        return observation;
    }

    public void setObservation(String observation) throws InvalidDataException
    {
//        if (observation == null) throw new InvalidDataException("Observation", new NullPointerException());
        this.observation = observation;
    }

    public Date getPacientBirth()
    {
        return pacientBirth;
    }

    public String getPacientBirthString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        return sdf.format(this.pacientBirth);
    }

    public void setPacientBirth(Date pacientBirth) throws InvalidDataException
    {
        if (pacientBirth == null) throw new InvalidDataException("Pacient Birth", new NullPointerException());
        this.pacientBirth = pacientBirth;
    }

    @SuppressWarnings("deprecation")
    public void setPacientBirthFromString(String pacientBirth) throws InvalidDataException
    {
        if (pacientBirth == null) throw new InvalidDataException("Date", new NullPointerException());
        StringBuilder sb = new StringBuilder(pacientBirth);
        Date auxDate = new Date(Integer.valueOf(sb.substring(6)) - Constants.YEAR_OFFSET, Integer.valueOf(sb.substring(0,2)), Integer.valueOf(sb.substring(3,5)));
        this.pacientBirth = auxDate;
    }

    @SuppressWarnings("deprecation")
    public void setPacientBirthFromSQLite(String pacientBirth) throws InvalidDataException
    {
        if (pacientBirth == null) throw new InvalidDataException("Date", new NullPointerException());
        StringBuilder sb = new StringBuilder(pacientBirth);
        Date auxDate = new Date(Integer.valueOf(sb.substring(6)) - Constants.YEAR_OFFSET, Integer.valueOf(sb.substring(0,2)) - 1, Integer.valueOf(sb.substring(3,5)));
        this.pacientBirth = auxDate;
    }

    public Date getDate()
    {
        return date;
    }

    public String getDateString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        return sdf.format(this.date);
    }

    public void setDate(Date date) throws InvalidDataException
    {
        if (date == null) throw new InvalidDataException("Date", new NullPointerException());
        this.date = date;
    }

    @SuppressWarnings("deprecation")
    public void setDateFromString(String date) throws InvalidDataException
    {
        if (date == null) throw new InvalidDataException("Date", new NullPointerException());
        StringBuilder sb = new StringBuilder(date);
        Date auxDate = new Date(Integer.valueOf(sb.substring(6)) - Constants.YEAR_OFFSET, Integer.valueOf(sb.substring(0,2)), Integer.valueOf(sb.substring(3,5)));
        this.date = auxDate;
    }

    @SuppressWarnings("deprecation")
    public void setDateFromSQLite(String date) throws InvalidDataException
    {
        if (date == null) throw new InvalidDataException("Date", new NullPointerException());
        StringBuilder sb = new StringBuilder(date);
        Date auxDate = new Date(Integer.valueOf(sb.substring(6)) - Constants.YEAR_OFFSET, Integer.valueOf(sb.substring(0,2)) - 1, Integer.valueOf(sb.substring(3,5)));
        this.date = auxDate;
    }

    public Date getRevaluationDate()
    {
        return revaluationDate;
    }

    public String getRevaluationDateString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        return sdf.format(this.revaluationDate);
    }

    public void setRevaluationDate(Date revaluationDate) throws InvalidDataException
    {
        if (revaluationDate == null) throw new InvalidDataException("Revaluation Date", new NullPointerException());
        this.revaluationDate = revaluationDate;
    }

    @SuppressWarnings("deprecation")
    public void setRevaluationDateFromString(String date) throws InvalidDataException
    {
        if (date == null) throw new InvalidDataException("Revaluation Date", new NullPointerException());
        StringBuilder sb = new StringBuilder(date);
        Date auxDate = new Date(Integer.valueOf(sb.substring(6)) - Constants.YEAR_OFFSET, Integer.valueOf(sb.substring(0,2)), Integer.valueOf(sb.substring(3,5)));
        this.revaluationDate = auxDate;
    }

    @SuppressWarnings("deprecation")
    public void setRevaluationDateFromSQLite(String date) throws InvalidDataException
    {
        if (date == null) throw new InvalidDataException("Revaluation Date", new NullPointerException());
        StringBuilder sb = new StringBuilder(date);
        Date auxDate = new Date(Integer.valueOf(sb.substring(6)) - Constants.YEAR_OFFSET, Integer.valueOf(sb.substring(0,2)) - 1, Integer.valueOf(sb.substring(3,5)));
        this.revaluationDate = auxDate;
    }
}
