package databasegenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class ShelterAllocation {
    private LocalDate enterDate;
    private String victimId;
    private String shelterId;
    private String disasterId;
    
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
    
    public String generateQuery(){
        return "INSERT INTO shelter_allocation VALUES (TO_DATE('" +
                convertLocalDate(this.enterDate) + "', 'dd-MON-yyyy'), '" + 
                this.victimId + "', '" + 
                this.shelterId + "', '" + 
                this.disasterId + "');\n";
    }

    @Override
    public String toString() {
        return "ShelterAllocation{" + "enterDate=" + enterDate + ", victimId=" + victimId + ", shelterId=" + shelterId + ", disasterId=" + disasterId + '}';
    }

    public LocalDate getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(LocalDate enterDate) {
        this.enterDate = enterDate;
    }

    public String getVictimId() {
        return victimId;
    }

    public void setVictimId(String victimId) {
        this.victimId = victimId;
    }

    public String getShelterId() {
        return shelterId;
    }

    public void setShelterId(String shelterId) {
        this.shelterId = shelterId;
    }

    public String getDisasterId() {
        return disasterId;
    }

    public void setDisasterId(String disasterId) {
        this.disasterId = disasterId;
    }

    public ShelterAllocation(LocalDate enterDate, String victimId, String shelterId, String disasterId) {
        this.enterDate = enterDate;
        this.victimId = victimId;
        this.shelterId = shelterId;
        this.disasterId = disasterId;
    }
}
