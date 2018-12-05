package databasegenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MedicalReport {
    private String medicalReportId;
    private LocalDate date;
    private String type;
    private String description;
    private String status;
    private String victimId;
    private String staffId;
    private String sicknessId;
    
     public static LocalDate randomDate(int sDate[], int eDate[]){
        LocalDate startDate = LocalDate.of(sDate[0], sDate[1], sDate[2]); //start date
        long start = startDate.toEpochDay();
        
        LocalDate endDate = LocalDate.of(eDate[0], eDate[1], eDate[2]); //end date
        long end = endDate.toEpochDay();
        
        long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
                
        return LocalDate.ofEpochDay(randomEpochDay);
    }
    
    public String convertLocalDate(LocalDate date){
        return DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH).format(date);
    }
    
    public static String randomType(){
        String[] type = {"Check-up", "Operation", "Dead", "ICU", "Transfer to Hospital"};
        
        Random random = new Random();
        
        return type[random.nextInt(5)];
    }
    
    public static String randomDesc(){
        String[] desc = {"Good", "Normal", "Bad"};
        
        Random random = new Random();
        
        return desc[random.nextInt(3)];
    }
    
    public static String randomStatus(){
        String [] status = {"Completed", "Incomplete", "Transfered"};
        
        Random random = new Random();
        
        return status[random.nextInt(3)];
    }
    
    public String generateQuery(){
        return "INSERT INTO medical_report VALUES ('" +
                this.medicalReportId + "', TO_DATE('" +
                convertLocalDate(this.date) + "', 'dd-MON-yyyy'), '" + 
                this.type + "', '" + 
                this.description + "', '" + 
                this.status + "', '" +
                this.victimId + "', '" +
                this.staffId + "', '" +
                this.sicknessId + "');\n";
    }

    @Override
    public String toString() {
        return "MedicalReport{" + "medicalReportId=" + medicalReportId + ", date=" + date + ", type=" + type + ", description=" + description + ", status=" + status + ", victimId=" + victimId + ", staffId=" + staffId + ", sicknessId=" + sicknessId + '}';
    }

    public String getMedicalReportId() {
        return medicalReportId;
    }

    public void setMedicalReportId(String medicalReportId) {
        this.medicalReportId = medicalReportId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVictimId() {
        return victimId;
    }

    public void setVictimId(String victimId) {
        this.victimId = victimId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getSicknessId() {
        return sicknessId;
    }

    public void setSicknessId(String sicknessId) {
        this.sicknessId = sicknessId;
    }

    public MedicalReport(String medicalReportId, LocalDate date, String type, String description, String status, String victimId, String staffId, String sicknessId) {
        this.medicalReportId = medicalReportId;
        this.date = date;
        this.type = type;
        this.description = description;
        this.status = status;
        this.victimId = victimId;
        this.staffId = staffId;
        this.sicknessId = sicknessId;
    }
}
