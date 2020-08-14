package Coursework;

import java.util.ArrayList;
import java.util.Collections;

public class TourCompany
{
  ArrayList<TourGuide> tourGuideArrayList = new ArrayList<TourGuide>();
  
  //Method for adding a new object of previous class to the list.
  public void addTourGuide(String tourGuide,int dailyRate){
    tourGuideArrayList.add(new TourGuide(tourGuide,dailyRate));
    }
    
    //Method for removing object from the Array List.
    public void removeTourGuide(int indexNumber){
        try{
            tourGuideArrayList.remove(indexNumber);
        }
        catch(IndexOutOfBoundsException ex){
           System.out.println("Invalid index number of tour guide."); 
        }
    }
    
    //Method for booking /hiring or storing records for customer of the tour guide.
    
    public void bookTourGuide(int indexNumber,String customerName,String customerCountry,String tourDestination,String hireDate,int noOfDays,float downPayment){
    
       try{
           TourGuide info =tourGuideArrayList.get(indexNumber);
           info.forBooking(customerName,customerCountry,hireDate,tourDestination,noOfDays,downPayment);
    }
       catch(IndexOutOfBoundsException ex){
        System.out.println("Sorry,invalid index number!!");
    }
}
   
//Method for for making tour guide available to book/hire for its customer.

 public void freeTourGuide(int indexNumber){
    try{
         TourGuide info=tourGuideArrayList.get(indexNumber);
         info.makingAvailable();
    }
    catch(IndexOutOfBoundsException ex){
         System.out.println("Sorry,index number is invalid!!");
    }
    }
    
 /*Method for  for listing all the objects of previous class in the array list which are
currently available for booking or hiring.*/

public void displayTourGuideList(){
    for(TourGuide info : tourGuideArrayList){
        if (info.getAvailableStatus()){
            System.out.println("Index Number=" + tourGuideArrayList.indexOf(info));
            info.displayDescription();
        }
    
    }
}
    
    /*Method to display the customers’ names and country along with the names of the
guide in ascending order.*/

public void displayInAscendingOrder(){

    Collections.sort(tourGuideArrayList,new Sort());
    for(TourGuide info:tourGuideArrayList){
        if(!(info.getAvailableStatus())){
            System.out.println(info.getCustomerName());
            System.out.println(info.getCustomerCountry());
            info.displayDescription();
        }
    
    }
    
}
}

