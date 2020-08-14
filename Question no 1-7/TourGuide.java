package Coursework;

public class TourGuide
{
    //Instance Variables
    private String tourGuide;
    private String customerName;
    private String customerCountry;
    private String tourDestination;
    private String hireDate;
    private int noOfDays;
    private int dailyRate;
    private float downPayment;
    private boolean availableStatus;
    
    //Constructor
    public TourGuide(String tourGuide, int dailyRate ){
    
        this.tourGuide=tourGuide;
        this.dailyRate=dailyRate;
        this.customerName="";
        this.customerCountry="";
        this.tourDestination="";
        this.hireDate="";
        this.noOfDays=0;
        this.downPayment=0.0f;
        this.availableStatus=true;
           
    }
    
    //Getter Methods
    public String getTourGuide(){
    return this.tourGuide;
    }
    public String getCustomerName(){
    return this.customerName;
    }
    public String getCustomerCountry(){
    return this.customerCountry;
    }
    public String getTourDestination(){
    return this.tourDestination;
    }
    public String getHireDate(){
    return this.hireDate;
    }
    public int getNoOfDays(){
    return this.noOfDays;
    }
    public int getDailyRate(){
    return this.dailyRate;
    }
    public float getDownPayment(){
    return this.downPayment;
    }
    public boolean getAvailableStatus(){
    return this.availableStatus;
    }
    
    //Setter Methods
    public void setDailyRate(int dailyRate,int noOfDays){
    this.dailyRate=dailyRate;
    }
    public void setNoOfDays(){
    this.noOfDays=noOfDays;
    }
    
    //Booking Methods
    
    public void forBooking(String customerName,String customerCountry,String hireDate,String tourDestination,int noOfDays,float downPayment){
    if (availableStatus==false){
    System.out.println("The game station is unavailable for "+this.hireDate+"and is not available for "+this.noOfDays+"days.");
    }
    else{
    this.customerName=customerName;
    this.customerCountry=customerCountry;
    this.hireDate=hireDate;
    this.tourDestination=tourDestination;
    this.noOfDays=noOfDays;
    this.downPayment=downPayment;
    this.availableStatus=false;
    }
    }
    
    //Method for making the tour guide available.
    public void makingAvailable(){
       if (availableStatus==true){
        System.out.println("The tour guide is currently available.");
        }
        else{
        this.customerName="";
        this.customerCountry="";
        this.noOfDays=0;
        this.tourDestination="";
        this.hireDate="";
        this.downPayment=0.0f;
        this.availableStatus=true;
        }
    }
    
    //Final method for description of the tour guide and the daily rate.
    public void displayDescription(){
    System.out.println("The tour guide booked is "+this.tourGuide);
    System.out.println("The daily rate of the tour guide is "+this.dailyRate);
    }
    
}
