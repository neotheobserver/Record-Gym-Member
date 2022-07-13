/*
 * @author Ardent Sharma 20049385 
 * @author Ashma Maharjan 20048835 
 * @author Bhishan Khatiwoda 20049401 
 * @author Jeena Dahal 20048904 
 * @version 1.1.1
 */
package thehulkfitness;

import javax.swing.JOptionPane;
import javax.swing.JTable;

/* Class that implements binary search */
public class Search {
    
    //create instance of MergeSort class
    MergeSort mergeSort = new MergeSort();
    
    //method that takes the table and provides sorted 2d array based on requirement
    public String[][] getSortedRecord(JTable table, String sortBy){
        //call method in MergeSort class that sorts record based on Name if sortyBy is name
        if (sortBy.equals("NAME"))
            return mergeSort.getSortedRecordByName(table);
        //call method in MergeSort class that sorts record based on Mobile if sortyBy is mobile
        else 
            return mergeSort.getSortedRecordByMobile(table);
    }
    
    //method that reutrns the list of names in the table
    public String[] getListOfNames(String[][] sortedRecord){
        //instantiate array for the length of input records
        String[] list = new String[sortedRecord.length];
        //iterate over the length
        for(int i = 0; i < sortedRecord.length; i++)
            //insert the name into the array after converting to uppercase
            list[i] = sortedRecord[i][1].toUpperCase();
        //return the array that contains list of names
        return list;
    }
    
    //method that returns the list of mobile number in the table
    public long[] getListOfMobile(String[][] sortedRecord){
        //instantiate array for the length of input records
        long[] list = new long[sortedRecord.length];
        //iterate over the length
        for(int i = 0; i < sortedRecord.length; i++)
            //insert the phonenumber into the array of long
            list[i] = Long.parseLong(sortedRecord[i][4]);
        //return the array that contains the list of phone number
        return list;
    }
    
    //method that caluclates the index/row of table where the target name was found i.e.it searches string using compareTo() method
    public int getNameIndex(String[] sortedItemList, String target, int left, int right){
       //get the mid point of the array
       int middle = (left + right)/2;
       //if the right index has become less than left i.e. end of the list return -1
       if (right < left)
           return -1;
       //if the match has been found return the index 
       if (sortedItemList[middle].equals(target))
           return middle;
       //if the current middle value is greater than target value only search the left part of the array i.e. compareTo() returned +ve value
       else if (sortedItemList[middle].compareTo(target) > 0)
           return getNameIndex(sortedItemList, target, left, middle-1);
       //if the current middle value is less than target value only search the right part of the array i.e. the only remaining case
       else
           return getNameIndex(sortedItemList, target, middle+1, right);
   } 
    
    //method that caluclates the index/row of table where the target phone number was found i.e.it searches long value 
    public int getMobileIndex(long[] sortedItemList, long target, int left, int right){
       //get the mid point of the array
       int middle = (left + right)/2;
       //if the right index has become less than left i.e. end of the list return -1
       if (right < left)
           return -1;
       //if the match has been found return the index
       if (sortedItemList[middle] == target)
           return middle;
       //if the current middle value is greater than target value only search the left part of the array
       else if (sortedItemList[middle] > target)
           return getMobileIndex(sortedItemList, target, left, middle-1);
       //if the current middle value is less than target value only search the right part of the array
       else
           return getMobileIndex(sortedItemList, target, middle+1, right);
   }
    
   //method that displays the details regarding the item that has been found
   public void displayFoundRecord(String[][] sortedRecords, int rowPosition){
        JOptionPane.showMessageDialog(null, "Item Found!!! The details are:\n"
                    +"\t"+ "Member Id: "+sortedRecords[rowPosition][0]+"\n"
                    +"\t"+"Name: "+sortedRecords[rowPosition][1]+"\n"
                    +"\t"+"Age: "+sortedRecords[rowPosition][2]+"\n"
                    +"\t"+"Address: "+sortedRecords[rowPosition][3]+"\n"
                    +"\t"+"Phone No: "+sortedRecords[rowPosition][4]+"\n"
                    +"\t"+"Gender: "+sortedRecords[rowPosition][5]+"\n"
                    +"\t"+"Joined Date: "+sortedRecords[rowPosition][6]+"\n"
                    +"\t"+"Activities: "+sortedRecords[rowPosition][7]+"\n"
                    +"\t"+"Fees: "+sortedRecords[rowPosition][8]+"\n"
                    +"\t"+"BMI: "+sortedRecords[rowPosition][9]+"\n"
                    +"\t"+"Target: "+sortedRecords[rowPosition][10]+"\n"
                    ,"Found", 1);
    
    }
   
   //method that display error message if the item could not be found
   public void displayRecordNotFound(){
       JOptionPane.showMessageDialog(null, "Sorry!!! There are no item you have entered!!!\n","Not Found", 1);
   }
}
