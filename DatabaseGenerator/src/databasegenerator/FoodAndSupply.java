/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package databasegenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Arthur
 */
public class FoodAndSupply {
    private String id;
    private String type;
    private String description;
    private LocalDate date;
    private int amount;
    private String purchaseCost;
    private LocalDate expDate;
    private String donorId;
    
    public FoodAndSupply(String id, String type, String description, LocalDate date, int amount, String purchaseCost, LocalDate expDate, String donorId) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.purchaseCost = purchaseCost;
        this.expDate = expDate;
        this.donorId = donorId;
    }
    
     public static LocalDate randomDate(int sDate[], int eDate[]){
        LocalDate startDate = LocalDate.of(sDate[0], sDate[1], sDate[2]); //start date
        long start = startDate.toEpochDay();
        
        LocalDate endDate = LocalDate.of(eDate[0], eDate[1], eDate[2]); //end date
        long end = endDate.toEpochDay();
        
        long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
                
        return LocalDate.ofEpochDay(randomEpochDay);
    }
    
    public static LocalDate randomExpDate(){
        LocalDate startDate = LocalDate.of(2016, 1, 1); //start date
        long start = startDate.toEpochDay();
        
        LocalDate endDate = LocalDate.of(2025, 12, 31); //end date
        long end = endDate.toEpochDay();
        
        long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
           
        return LocalDate.ofEpochDay(randomEpochDay);
    }
    
    public String convertLocalDate(LocalDate date){
        return DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH).format(date);
    }
    
    public static String randomType(){
        String[] types = {"Food", "Drink", "Medicine", "Wearable", "Daily Supplies", "Others"};
        
        Random rand = new Random();
                       
        return types[rand.nextInt(6)];
    }
    
    public static String randomDescription(String type){
        
        String[] description = null;
        
        if (type.equals("Food")){
            description =  new String []{"Rice","Biscuit","Bread","Cup Noodle","Instant Noodle" ,"Snacks",
                "Can Food" ,"Fruits","Meal Box" ,"Oat"};
        }else if (type.equals("Drink")){
            description =  new String []{"Drinking Water","Can Drinks" ,"Fruit Juice","MILO" ,"Energy Drinks",
                "Health Beverage" ,"Choco Drinks","Coffee" ,"Milk","Tea"};
        }else if (type.equals("Medicine")){
            description =  new String []{"Vitamins","Antiseptic" ,"Cotton","Plasters" ,"Medical Box","Ointment" ,
                "Medical Box" ,"Disinfectant" ,"AntiPruritic","Painkiller"};
        }else if (type.equals("Wearable")){
            description =  new String []{"Children Clothes","Adult Clothes" ,"Cloth","Jacket" ,"Glove","Towel" ,
                "Blanket","Sleeping Bag" ,"Disposal Inner Wear","Diapers"};
        }else if (type.equals("Daily Supplies")){
            description =  new String []{"Toothbrush Set","Toiletry" ,"Tissues","Detergent" ,"Shampoo","Lotion" ,"Wet Tissue",
                "Hand Santizer" ,"Cleaning Sets","Soap"};
        }else{
            description =  new String []{"Pails","Electric Generator" ,"Toolbox","Electric Gadgets" ,"Mosquito Repellent",
                "Insecticide" ,"Mosquito Net","Batteries" ,"Torch Light","Baskets"};
        }
        
        Random rand = new Random();
        
        return description[rand.nextInt(10)];
    }
    
   
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
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
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public String getPurchaseCost() {
        return purchaseCost;
    }
    
    public void setPurchaseCost(String purchaseCost) {
        this.purchaseCost = purchaseCost;
    }
    
    public LocalDate getExpDate() {
        return expDate;
    }
    
    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }
    
    public String getDonorId() {
        return donorId;
    }
    
    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }
    
    @Override
    public String toString() {
        return "FoodAndSupply{" + "id=" + id + ", type=" + type + ", description=" + description + ", date=" + date + ", amount=" + amount + ", purchaseCost=" + purchaseCost + ", expDate=" + expDate + ", donorId=" + donorId + '}';
    }
    
     public String generateQuery(){
         String expDate;
         
         if(this.expDate == null){
             expDate = "NULL";
         } else {
             expDate = convertLocalDate(this.expDate);
         }
         
        return "INSERT INTO food_supply VALUES ('" + this.id + "', '" + this.type + "', '" + this.description + "', TO_DATE('" +
                convertLocalDate(this.date) + "', 'dd-MON-yyyy'), '" + this.amount + "', '" + this.amount + "', '" + this.purchaseCost + "', TO_DATE('" +
                expDate + "', 'dd-MON-yyyy'), '" + this.donorId + "');\n";
    }
     
     public String generateQueryNULLexp(){
         String expDate;
         
         if(this.expDate == null){
             expDate = "NULL";
         } else {
             expDate = convertLocalDate(this.expDate);
         }
         
        return "INSERT INTO food_supply VALUES ('" + this.id + "', '" + this.type + "', '" + this.description + "', TO_DATE('" +
                convertLocalDate(this.date) + "', 'dd-MON-yyyy'), '" + this.amount + "', '" + this.amount + "', '" + this.purchaseCost + "', '" +
                expDate + "', '" + this.donorId + "');\n";
    }
    
    
}
