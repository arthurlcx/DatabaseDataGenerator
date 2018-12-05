package databasegenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class TaskAssignment {
    private String taskAssignmentId;
    private LocalDate date;
    private String status;
    private String shelterId;
    private String staffId;
    private String taskId;
    
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
        return "INSERT INTO task_assignment VALUES ('" +
                this.taskAssignmentId + "', TO_DATE('" +
                convertLocalDate(this.date) + "', 'dd-MON-yyyy'), '" + 
                this.status + "', '" + 
                this.shelterId + "', '" + 
                this.staffId + "', '" +
                this.taskId + "');\n";
    }
     
     @Override
    public String toString() {
        return "TaskAssignment{" + "taskAssignmentId=" + taskAssignmentId + ", date=" + date + ", status=" + status + ", shelterId=" + shelterId + ", staffId=" + staffId + ", taskId=" + taskId + '}';
    }

    public String getTaskAssignmentId() {
        return taskAssignmentId;
    }

    public void setTaskAssignmentId(String taskAssignmentId) {
        this.taskAssignmentId = taskAssignmentId;
    }

    public LocalDate getDate() {
        return date;
    }

    

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShelterId() {
        return shelterId;
    }

    public void setShelterId(String shelterId) {
        this.shelterId = shelterId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public TaskAssignment(String taskAssignmentId, LocalDate date, String status, String shelterId, String staffId, String taskId) {
        this.taskAssignmentId = taskAssignmentId;
        this.date = date;
        this.status = status;
        this.shelterId = shelterId;
        this.staffId = staffId;
        this.taskId = taskId;
    }
}
