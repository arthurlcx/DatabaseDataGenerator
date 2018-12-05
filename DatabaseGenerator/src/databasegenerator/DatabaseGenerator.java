package databasegenerator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseGenerator {
    
    public static int option = -1;
    
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        Scanner scannerStr = new Scanner (System.in);
        Random rand = new Random();
        String finalQuery;
        int loop;
        Donation[] donation = new Donation[100];
        FoodAndSupply[] foodAndSupply = new FoodAndSupply[100];
        ShelterAllocation [] shelterAllocation = new ShelterAllocation[50];
        TaskAssignment[] taskAssignment = new TaskAssignment[100];
        MedicalReport[] medicalReport = new MedicalReport[100];
        
        
        while (option != 0) {
            finalQuery = "";
            System.out.println("Database Insert Query Generator");
            System.out.println("Table to generate: \n"
                    + "1. Donation \n2. Food & Supplies \n3. Shelter Allocation \n4. Task Allocation \n5. Medical Report");
            option = scanner.nextInt();
            
            switch(option){
                case 1:
                    System.out.print("Number of queries: ");
                    loop = scanner.nextInt();
                    
                    int idDonationCount;
                    String idDonation;
                    int donationAmount;
                    int randId;
                    String donorId;
                    LocalDate date = LocalDate.of(2009, 1, 13);
                    
                    for (int i = 0; i < loop; i++){
                        idDonationCount = i+1;
                        donationAmount = (rand.nextInt(50)+1) * 100;
                        randId = rand.nextInt(200) + 1;
                        
                        if(idDonationCount < 10){
                            idDonation = "DNT000" + idDonationCount;
                        } else if (idDonationCount < 100){
                            idDonation = "DNT00" + idDonationCount;
                        } else if (idDonationCount < 1000) {
                            idDonation = "DNT0" + idDonationCount;
                        } else {
                            idDonation = "DNT" + idDonationCount;
                        }
                        
                        if(randId < 10){
                            donorId = "D000" + randId;
                        } else if (randId < 100){
                            donorId = "D00" + randId;
                        } else if (randId < 1000) {
                            donorId = "D0" + randId;
                        } else {
                            donorId = "D" + randId;
                        }
                        
                        if(i > 0){
                            LocalDate temp = randomDate();
                            long daysBetween = DAYS.between(donation[i-1].getDoantionDate(), temp);
                            boolean beforeDate = (temp).isBefore(donation[i-1].getDoantionDate());
                            while(daysBetween  > 80 || beforeDate){
                                temp = randomDate();
                                daysBetween = DAYS.between(donation[i-1].getDoantionDate(), temp);
                                beforeDate = (temp).isBefore(donation[i-1].getDoantionDate());
                            };
                            
                            date = temp;
                            
                        }
                        
//                        System.out.println(date);
                        
                        donation[i] = new Donation(idDonation, date, donationAmount, donorId);
                        
                        finalQuery += donation[i].generateQuery();
                        
                    }
                    break;
                    
                case 2:
                    System.out.print("Food and Supply ID begins at F-");
                    int idFSCount = scanner.nextInt(); 
                    System.out.print("Number of queries: ");
                    loop = scanner.nextInt();
                    
                    int[] startDateFS = new int[3];
                    int[] endDateFS = new int[3];
                    
                    System.out.print("Disaster begins (year): ");
                    startDateFS[0] = scanner.nextInt();
                    System.out.print("Disaster begins (month): ");
                    startDateFS[1] = scanner.nextInt();
                    System.out.print("Disaster begins (day): ");
                    startDateFS[2] = scanner.nextInt();
                    
                    System.out.println("");
                    
                    System.out.print("Disaster end (year): ");
                    endDateFS[0] = scanner.nextInt();
                    System.out.print("Disaster end (month): ");
                    endDateFS[1] = scanner.nextInt();
                    System.out.print("Disaster end (day): ");
                    endDateFS[2] = scanner.nextInt();
                    
                    
                    String idFS;
                    String typeFS;
                    String descriptionFS;
                    LocalDate dateFS;
                    int amountFS;
                    String costFS = "NULL";
                    LocalDate expDate;
                    String donorFS = "NULL";
                    
                    for (int i = 0; i < loop; i++){
                        typeFS = FoodAndSupply.randomType();
                        descriptionFS = FoodAndSupply.randomDescription(typeFS);
                        
                        if(idFSCount < 10){
                            idFS = "FS000" + idFSCount;
                        } else if (idFSCount < 100){
                            idFS = "FS00" + idFSCount;
                        } else if (idFSCount < 1000) {
                            idFS = "FS0" + idFSCount;
                        } else {
                            idFS = "FS" + idFSCount;
                        }
                        
                        idFSCount++;
                        
                        if(typeFS.equals("Food") || typeFS.equals("Drink") || typeFS.equals("Medicine")){
                            expDate = FoodAndSupply.randomExpDate();
                        }else{
                            expDate = null;
                        }
                        
                        dateFS = FoodAndSupply.randomDate(startDateFS, endDateFS);
                        
                        if(i > 0){
                            LocalDate temp = FoodAndSupply.randomDate(startDateFS, endDateFS);
                            long daysBetween = DAYS.between(foodAndSupply[i-1].getDate(), temp);
                            boolean beforeDate = (temp).isBefore(foodAndSupply[i-1].getDate());
                            while(daysBetween  > 3 || beforeDate){
                                temp = FoodAndSupply.randomDate(startDateFS, endDateFS);
                                daysBetween = DAYS.between(foodAndSupply[i-1].getDate(), temp);
                                beforeDate = (temp).isBefore(foodAndSupply[i-1].getDate());
                            };
                            
                            dateFS = temp;
                            
                        }
                        
                        System.out.println(dateFS);
                        
                        amountFS = (rand.nextInt(10)+1) * 100;
                        
                        int p = rand.nextInt(2) + 0;
                        System.out.println(p);
                        if(p == 1){
                            costFS = "NULL";
                            
                            randId = rand.nextInt(200) + 1;
                            if(randId < 10){
                                donorId = "D000" + randId;
                            } else if (randId < 100){
                                donorId = "D00" + randId;
                            } else if (randId < 1000) {
                                donorId = "D0" + randId;
                            } else {
                                donorId = "D" + randId;
                            }
                            
                            for(int j = 0; j < donation.length; j++){
                                while (donorId.equals(donation[i].getDonorId())){
                                    randId = rand.nextInt(200) + 1;
                                    if(randId < 10){
                                        donorId = "D000" + randId;
                                    } else if (randId < 100){
                                        donorId = "D00" + randId;
                                    } else if (randId < 1000) {
                                        donorId = "D0" + randId;
                                    } else {
                                        donorId = "D" + randId;
                                    }
                                };
                                donorFS = donorId;
                            }
                        }else {
                            int n = rand.nextInt(999)+1;
                            costFS = Integer.toString(n) + ".00";
                            donorFS = "NULL";
                        }
                        
                        foodAndSupply[i] = new FoodAndSupply(idFS, typeFS, descriptionFS, dateFS,
                                amountFS, costFS, expDate, donorFS);
                        
                        if (foodAndSupply[i].getExpDate() == null){
                            finalQuery += foodAndSupply[i].generateQueryNULLexp();
                        } else {
                            finalQuery += foodAndSupply[i].generateQuery();
                        }                        
                    }
                        System.out.println(finalQuery);

                    break;
                    
                case 3:
                    int[] startDate = new int[3];
                    int[] endDate = new int[3];
                    
                    System.out.print("Disaster begins (year): ");
                    startDate[0] = scanner.nextInt();
                    System.out.print("Disaster begins (month): ");
                    startDate[1] = scanner.nextInt();
                    System.out.print("Disaster begins (day): ");
                    startDate[2] = scanner.nextInt();
                    
                    System.out.println("");
                    
                    System.out.print("Disaster end (year): ");
                    endDate[0] = scanner.nextInt();
                    System.out.print("Disaster end (month): ");
                    endDate[1] = scanner.nextInt();
                    System.out.print("Disaster end (day): ");
                    endDate[2] = scanner.nextInt();
                    
                    System.out.print("\nNumber of victims: ");
                    loop = scanner.nextInt();
                    
                    System.out.print("\nVictim ID strating from V-");
                    int victimIdStartIndex = scanner.nextInt();
                    
                    System.out.print("\nShelter ID for the allocation: ");
                    String shelterId = scannerStr.nextLine();
                    
                    System.out.print("\nDisaster ID for the allocation: ");
                    String disasterId = scannerStr.nextLine();
                    
                    LocalDate enterDate;
                    String victimId = "";
                                        
                    for (int k = 0; k < loop; k++){
                       enterDate = ShelterAllocation.randomDate(startDate, endDate);
                       
                       if(k > 0){
                            LocalDate temp = ShelterAllocation.randomDate(startDate, endDate);
                            long daysBetween = DAYS.between(shelterAllocation[k-1].getEnterDate(), temp);
                            boolean beforeDate = (temp).isBefore(shelterAllocation[k-1].getEnterDate());
                            while(daysBetween  > 7 || beforeDate){
                                temp = ShelterAllocation.randomDate(startDate, endDate);
                                daysBetween = DAYS.between(shelterAllocation[k-1].getEnterDate(), temp);
                                beforeDate = (temp).isBefore(shelterAllocation[k-1].getEnterDate());
                            };
                            
                            enterDate = temp;
                            
                        }
                        
                        
                       if(victimIdStartIndex < 10){
                                victimId = "v000" + victimIdStartIndex;
                            } else if (victimIdStartIndex < 100){
                                victimId = "V00" + victimIdStartIndex;
                            } else if (victimIdStartIndex < 1000) {
                                victimId = "V0" + victimIdStartIndex;
                            } else {
                                victimId = "V" + victimIdStartIndex;
                            }
                       victimIdStartIndex++;
//                       System.out.println(victimId);
//                       System.out.println(enterDate);
                                            
                       shelterAllocation[k] = new ShelterAllocation(enterDate, victimId, shelterId, disasterId);

                       finalQuery += shelterAllocation[k].generateQuery();
                    }
                    System.out.println(finalQuery);
                    
                    break;
                    
                case 4:
                    System.out.print("Task Assignment ID begins at TA-");
                    int idTACount = scanner.nextInt(); 
                    System.out.print("Number of queries: ");
                    loop = scanner.nextInt();
                    
                    int[] startDateTA = new int[3];
                    int[] endDateTA = new int[3];
                    
                    System.out.print("Disaster begins (year): ");
                    startDateTA[0] = scanner.nextInt();
                    System.out.print("Disaster begins (month): ");
                    startDateTA[1] = scanner.nextInt();
                    System.out.print("Disaster begins (day): ");
                    startDateTA[2] = scanner.nextInt();
                    
                    System.out.println("");
                    
                    System.out.print("Disaster end (year): ");
                    endDateTA[0] = scanner.nextInt();
                    System.out.print("Disaster end (month): ");
                    endDateTA[1] = scanner.nextInt();
                    System.out.print("Disaster end (day): ");
                    endDateTA[2] = scanner.nextInt();
                    
                    System.out.print("\nRandom from shelter ST-: ");
                    int startShelter = scanner.nextInt();
                    System.out.print("to shelter ST-: ");
                    int endShelter = scanner.nextInt();
                    
                                            
                    LocalDate taskDate;
                    String taId = "";
                    String staffId = "";
                    String taskId = "";
                    String statusTa = "";
                    String shelterTA = "";
                                        
                    for (int k = 0; k < loop; k++){
                       taskDate = TaskAssignment.randomDate(startDateTA, endDateTA);
                       
                       if(k > 0){
                            LocalDate temp = TaskAssignment.randomDate(startDateTA, endDateTA);
                            long daysBetween = DAYS.between(taskAssignment[k-1].getDate(), temp);
                            boolean beforeDate = (temp).isBefore(taskAssignment[k-1].getDate());
                            while(daysBetween  > 1 || beforeDate){
                                temp = TaskAssignment.randomDate(startDateTA, endDateTA);
                                daysBetween = DAYS.between(taskAssignment[k-1].getDate(), temp);
                                beforeDate = (temp).isBefore(taskAssignment[k-1].getDate());
                            };
                            
                            taskDate = temp;
                            
                        }
                                                
                       if(idTACount < 10){
                                taId = "TA000" + idTACount;
                            } else if (idTACount < 100){
                                taId = "TA00" + idTACount;
                            } else if (idTACount < 1000) {
                                taId = "TA0" + idTACount;
                            } else {
                                taId = "TA" + idTACount;
                            }
                       idTACount++;
                       
                       int staffIdRand = rand.nextInt(400 - 2 + 1)+2;
                       
                       if(staffIdRand < 10){
                                staffId = "S000" + staffIdRand;
                            } else if (staffIdRand < 100){
                                staffId = "S00" + staffIdRand;
                            } else if (staffIdRand < 1000) {
                                staffId = "S0" + staffIdRand;
                            } else {
                                staffId = "S" + staffIdRand;
                            }
                       
                       int taskIdRand = rand.nextInt(7)+1;
                       
                       if(taskIdRand < 10){
                                taskId = "T00" + taskIdRand;
                            } else if (taskIdRand < 100){
                                taskId = "T0" + taskIdRand;
                            } else if (taskIdRand < 1000) {
                                taskId = "T" + taskIdRand;
                            } else {
                                taskId = "T" + taskIdRand;
                            }
                       
//                       int statusRand = rand.nextInt(2)+1;
                        int statusRand = 0;
                       if (statusRand == 1){
                           statusTa = "PENDING";
                        } else {
                           statusTa = "COMPLETED";
                       }
                       
                       int shelterRand = rand.nextInt(endShelter - startShelter + 1) + startShelter;
                       
                       if(shelterRand < 10){
                                shelterTA = "ST00" + shelterRand;
                            } else if (shelterRand < 100){
                                shelterTA = "ST0" + shelterRand;
                            } else if (shelterRand < 1000) {
                                shelterTA = "ST" + shelterRand;
                            } else {
                                shelterTA = "ST" + shelterRand;
                            }
                       
                       taskAssignment[k] = new TaskAssignment (taId, taskDate, statusTa, shelterTA, staffId, taskId);
                       
                       finalQuery += taskAssignment[k].generateQuery();
                    }
                    
                    System.out.println(finalQuery);
                       
                    break;
                    
                case 5:
                    System.out.print("Medical Report ID begins at MR-");
                    int idMRCount = scanner.nextInt(); 
                    
                    int loopMR = rand.nextInt(15-10 + 1) + 10;
                    
                    int[] startDateMR = new int[3];
                    int[] endDateMR = new int[3];
                    
                    System.out.print("Entering shelter at (year): ");
                    startDateMR[0] = scanner.nextInt();
                    System.out.print("Entering shelter at (month): ");
                    startDateMR[1] = scanner.nextInt();
                    System.out.print("Entering shelter at (day): ");
                    startDateMR[2] = scanner.nextInt();
                    
                    System.out.println("");
                    
                    System.out.print("Leaving shelter at (year): ");
                    endDateMR[0] = scanner.nextInt();
                    System.out.print("Leaving shelter at (month): ");
                    endDateMR[1] = scanner.nextInt();
                    System.out.print("Leaving shelter at (day): ");
                    endDateMR[2] = scanner.nextInt();
                    
                    System.out.print("\nRandom from victim V-: ");
                    int startVictim = scanner.nextInt();
                    System.out.print("to victim V-: ");
                    int endVictim = scanner.nextInt();
                    
                    String MedicalReportId = "";
                    LocalDate dateMr;
                    String victimIdMr = "";
                    String staffIdMr = "";
                    
                    
                    for (int i = 0; i < loopMR; i++){
                        if(idMRCount < 10){
                                MedicalReportId = "MR000" + idMRCount;
                            } else if (idMRCount < 100){
                                MedicalReportId = "MR00" + idMRCount;
                            } else if (idMRCount < 1000) {
                                MedicalReportId = "MR0" + idMRCount;
                            } else {
                                MedicalReportId = "MR" + idMRCount;
                            }
                        
                        idMRCount++;
                        
                        dateMr = MedicalReport.randomDate(startDateMR, endDateMR);
                        
                        if(i > 0){
                            LocalDate temp = MedicalReport.randomDate(startDateMR, endDateMR);
                            long daysBetween = DAYS.between(medicalReport[i-1].getDate(), temp);
                            boolean beforeDate = (temp).isBefore(medicalReport[i-1].getDate());
                            while(daysBetween  > 1 || beforeDate){
                                temp = MedicalReport.randomDate(startDateMR, endDateMR);
                                daysBetween = DAYS.between(medicalReport[i-1].getDate(), temp);
                                beforeDate = (temp).isBefore(medicalReport[i-1].getDate());
                            };
                            
                            dateMr = temp;
                            
                        }
                        
                        int victimRandom = rand.nextInt(endVictim - startVictim + 1) + startVictim;
                        
                        if(victimRandom < 10){
                                victimIdMr = "V000" + victimRandom;
                            } else if (victimRandom < 100){
                                victimIdMr = "V00" + victimRandom;
                            } else if (victimRandom < 1000) {
                                victimIdMr = "V0" + victimRandom;
                            } else {
                                victimIdMr = "V" + victimRandom;
                            }
                        
                        int staffRandom = rand.nextInt(391 - 18 + 1) + 18;
                        
                        if(staffRandom < 10){
                                staffIdMr = "S000" + staffRandom;
                            } else if (staffRandom < 100){
                                staffIdMr = "S00" + staffRandom;
                            } else if (staffRandom < 1000) {
                                staffIdMr = "S0" + staffRandom;
                            } else {
                                staffIdMr = "S" + staffRandom;
                            }
                        
                        int sick1 = rand.nextInt(20) + 1;
                        int sick2 = rand.nextInt(4) + 1;
                       
                        String sickness = "S" + sick1 + "V" + sick2;
                        
                        String typeMr = MedicalReport.randomType();
                        String descMr = MedicalReport.randomDesc();
                        String statusMr = MedicalReport.randomStatus();
                        
                        medicalReport[i] = new MedicalReport(MedicalReportId, dateMr, typeMr, descMr, statusMr, victimIdMr, staffIdMr, sickness);
                        
                        finalQuery += medicalReport[i].generateQuery();
                    }
                    
                    System.out.println(finalQuery);
                
                
                case 0:
                    System.out.println("Exiting Program...");
                    System.exit(0);
                    break;
            }
            
            writeToFile(finalQuery);
        }
    };
    
    
    public static LocalDate randomDate(){
        LocalDate startDate = LocalDate.of(2009, 1, 13); //start date
        long start = startDate.toEpochDay();
        
        LocalDate endDate = LocalDate.now(); //end date
        long end = endDate.toEpochDay();
        
        long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
        
        return LocalDate.ofEpochDay(randomEpochDay);
    }
    
    public static void writeToFile (String finalQuery){
        try {
            
            PrintWriter writer = null;
            
            if (option == 1){
                writer = new PrintWriter("DonationInsert.txt", "UTF-8");
            } else if (option == 2){
                writer = new PrintWriter("FoodAndSupplyInsert.txt", "UTF-8");
            } else if (option == 3){
                writer = new PrintWriter("ShelterAllocationInsert.txt", "UTF-8");
            } else if (option == 4){
                writer = new PrintWriter("TaskAssignmentInsert.txt", "UTF-8");
            }
            
            writer.println(finalQuery);
            writer.close();
            
            System.out.println("Write Successfull\n\n");
            
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(DatabaseGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
