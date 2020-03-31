package SolidPrinciples;

import java.util.*;

interface GenerateChallan{

    public void fetchDetails(DriverDetails dd);
    public void postChallan(DriverDetails dd);
    public void sendMessage(DriverDetails dd);

}

interface IDrunkAndDrive{

    public int BreathAnalysis(DriverDetails dd);
    public void Counselling(DriverDetails dd);

}

interface IDangerousDriving{

    public int getSpeedData(DriverDetails dd);

}

class FetchDriverDetails{
    private HashMap<String,String> hm = new HashMap<>();

    void setDetails(){
        hm.put("8498995522","Kaushik',Hyderabad,kausik.kompella@gmail.com");
        hm.put("9494940152","srinivas',Hyderabad,srinivas@gmail.com");
        hm.put("7382816283","nikhil',Hyderabad,nikhil@gmail.com");
    }

    String getDetails(String phno){
        return hm.get(phno);
    }

}

class FetchVehicleDetails{
   private HashMap<String,String> carDetails = new HashMap<>();
    private HashMap<String,String> bikeDetails = new HashMap<>();

    void setDetails(){
        carDetails.put("TS09EA1000","Kaushik',Hyderabad,kausik.kompella@gmail.com");
        carDetails.put("TS09EA4545","srinivas',Hyderabad,srinivas@gmail.com");
        carDetails.put("TS09EA1220","nikhil',Hyderabad,nikhil@gmail.com");
        bikeDetails.put("TS09EA1000","Kaushik',Hyderabad,kausik.kompella@gmail.com");
        bikeDetails.put("TS09EA4545","srinivas',Hyderabad,srinivas@gmail.com");
        bikeDetails.put("TS09EA1220","nikhil',Hyderabad,nikhil@gmail.com");
    }

    String getCarDetails(String regNo){
        return carDetails.get(regNo);
    }

    String getTwoWheelerDetails(String regNO){
        return bikeDetails.get(regNO);
    }

}

class DriverDetails{

    private String name;
    private String licenceNumber;
    private String phoneNumber;
    private String felony;

    public String getName() {
        return name;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFelony() {
        return felony;
    }

    public DriverDetails(String name, String licenceNumber, String phoneNumber, String felony) {
        this.name = name;
        this.licenceNumber = licenceNumber;
        this.phoneNumber = phoneNumber;
        this.felony = felony;
    }
}

class VehicleDetails{

    private String VehicleClass;
    private String RegistrationNumber;
    private String felony;
    VehicleDetails(String vehicleClass,String registrationNumber,String felony){

        this.VehicleClass = vehicleClass;
        this.RegistrationNumber = registrationNumber;
        this.felony = felony;
    }

    public String getVehicleClass() {
        return VehicleClass;
    }

    public String getRegistrationNumber() {
        return RegistrationNumber;
    }
}

class GeneralFelony implements GenerateChallan{


    @Override
    public void fetchDetails(DriverDetails dd) {
       FetchDriverDetails f = new FetchDriverDetails();
       f.getDetails(dd.getPhoneNumber());
       System.out.println(f.getDetails(dd.getPhoneNumber()));
    }

    @Override
    public void postChallan(DriverDetails dd) {
        System.out.println("posting challan");
    }

    @Override
    public void sendMessage(DriverDetails dd) {
        System.out.println("sending message to the accountable person");
    }
}

class DrunkandDrive implements GenerateChallan, IDrunkAndDrive{


    @Override
    public void fetchDetails(DriverDetails dd) {
        System.out.println("fetching details");
    }

    @Override
    public void postChallan(DriverDetails dd) {
        int value = BreathAnalysis(dd);
        System.out.println("posting challan where breath analysis value is "+ value);
    }

    @Override
    public void sendMessage(DriverDetails dd) {
        System.out.println("sending message to the accountable person");
    }

    @Override
    public int BreathAnalysis(DriverDetails dd) {
        return 30;
    }

    @Override
    public void Counselling(DriverDetails dd) {
        System.out.println("call for counselling");
    }
}

class DangerousDriving implements GenerateChallan, IDangerousDriving{


    @Override
    public void fetchDetails(DriverDetails dd) {
        System.out.println("fetching details");
    }

    @Override
    public void postChallan(DriverDetails dd) {
        int value = getSpeedData(dd);
        System.out.println("posting challan where breath analysis value is "+ value);
    }

    @Override
    public void sendMessage(DriverDetails dd) {
        System.out.println("sending message to the accountable person");
    }


    @Override
    public int getSpeedData(DriverDetails dd) {
        return 120;
    }
}

class Challan {

    void assignChallan(DriverDetails dd){

        if(dd.getFelony().equals("DrunkandDrive")){
            generateChallan(new DrunkandDrive(),dd);
        }
        else if(dd.getFelony().equals("DangerousDriving")){
            generateChallan(new DangerousDriving(),dd);
        }
        else
            generateChallan(new GeneralFelony(),dd);

    }
    private void generateChallan(GenerateChallan g,DriverDetails dd){
        g.fetchDetails(dd);
        g.postChallan(dd);
        g.sendMessage(dd);
    }

}

public class TrafficChallan {

    public static void main(String[] args){
        VehicleDetails vd = new VehicleDetails("FourWheeler","TS09EA4545","DrunkandDrive");
        DriverDetails dd =new DriverDetails("Srinivas","TS092UD8329","9494940152","DrunkandDrive");
        Challan c = new Challan();
        c.assignChallan(dd);

    }

}
