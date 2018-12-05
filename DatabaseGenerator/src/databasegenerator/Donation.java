package databasegenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Donation {
    private String donationId;
    private LocalDate doantionDate;
    private int donationAmount;
    private String donorId;

    @Override
    public String toString() {
        return "Donation{" + "donationId=" + donationId + ", doantionDate=" + doantionDate + ", donationAmount=" + donationAmount + ", donorId=" + donorId + '}';
    }

    public String getDonationId() {
        return donationId;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

    public LocalDate getDoantionDate() {
        return doantionDate;
    }

    public void setDoantionDate(LocalDate doantionDate) {
        this.doantionDate = doantionDate;
    }

    public int getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(int donationAmount) {
        this.donationAmount = donationAmount;
    }

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public Donation(String donationId, LocalDate doantionDate, int donationAmount, String donorId) {
        this.donationId = donationId;
        this.doantionDate = doantionDate;
        this.donationAmount = donationAmount;
        this.donorId = donorId;
    }
    
    public String generateQuery(){
        return "INSERT INTO donation VALUES ('" + this.donationId + "', TO_DATE('" + convertLocalDate(this.doantionDate) + "', 'dd-MON-yyyy'), " + 
                this.donationAmount + ".00, '" + this.donorId + "'); \n";
    }
    
    public String convertLocalDate(LocalDate date){
        return DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH).format(date);
    }
}
