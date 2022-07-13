/*
 * @author Ardent Sharma 20049385 
 * @author Ashma Maharjan 20048835 
 * @author Bhishan Khatiwoda 20049401 
 * @author Jeena Dahal 20048904 
 * @verion 1.1.1
 */
package thehulkfitness;

import javax.swing.JOptionPane;
import javax.swing.JTable;

/* Class that has methods to sort an array using merge sort*/
public class MergeSort {
    
    // method that takes in the input array and sorts it recurcively using merge sort
    public void mergeSort(String[][] inputArray, String flag, int columnPosition){
        //get the length of input array
        int inputLength = inputArray.length;
        //if the input array has only 1 element return
        if (inputLength < 2){
            return;
        }
        //get the midIndex to divide into two different left and right array and initialize them
        int midIndex = inputLength/2;
        String[][] leftArray = new String[midIndex][11];
        String[][] rightArray = new String[inputLength - midIndex][11];
        
        //populate the left array
        for (int i = 0; i < midIndex; i++){
            leftArray [i] = inputArray[i];
        }
        //populate the right array
        for (int i = midIndex; i < inputLength; i++){
            rightArray[i - midIndex] = inputArray[i];
        }
        //call merge sort recursively on both right and left array
        mergeSort(leftArray, flag, columnPosition);
        mergeSort(rightArray, flag, columnPosition);
        //combine the sorted values together
        merge(inputArray, leftArray, rightArray, flag, columnPosition);
   
    }
    
    //method used by mergesort to merge two array in a sorted manner
    public void merge (String[][] resultArray, String[][] leftArray, String[][] rightArray, String flag, int columnPosition){
        //determine the size of left and right array
        int leftSize = leftArray.length;
        int rightSize = rightArray.length;
        //initialize count to keep track of index of all three arrays passed
        int i = 0, j = 0, k = 0;
        //run loop until either the index of leftSize or rightSize has reached its limit
        while (i < leftSize && j < rightSize){
            //check values based on the flag passed
            switch (flag){
                //if number convert to float and compare values
                case "number":
                    //if value at left array is smaller or equals to right array insert the value from left into the main array
                    if(Float.valueOf(leftArray[i][columnPosition - 1]) <= Float.valueOf(rightArray[j][columnPosition - 1]))
                        resultArray[k++] = leftArray[i++];
                    // else insert value from right array into the main array
                    else
                        resultArray[k++] = rightArray[j++];
                    break;
                //if string compare value using compareTo() method
                case "string":
                    if(leftArray[i][columnPosition - 1].compareTo(rightArray[j][columnPosition - 1]) < 0)
                        resultArray[k++] = leftArray[i++];
                    else
                        resultArray[k++] = rightArray[j++];
                    break;
                //if phonenumber i.e. large number convert to long and compare values
                case "phonenumber":
                    if(Long.parseLong(leftArray[i][columnPosition - 1]) <= Long.parseLong(rightArray[j][columnPosition - 1]))
                        resultArray[k++] = leftArray[i++];
                    else
                        resultArray[k++] = rightArray[j++];
                    break;
           }
        }
        if (i < leftSize)
            System.out.println(leftArray[i][columnPosition - 1]);
        if (j <rightSize)
            System.out.println(rightArray[j][columnPosition - 1]);
        
        //in case there are elements remaining on left array
        while (i < leftSize)
            resultArray[k++] = leftArray[i++];
        //in case there are elements remaining on right array
        while (j < rightSize)
            resultArray[k++] = rightArray[j++]; 
    }
    
    //method to calculate and return every element in the table as 2D string array
    public String[][] generate2DArrayFrom(JTable table){
        //get the row and column count of the table
        int rowCount = table.getRowCount();
        int colCount = table.getColumnCount();
        //initialize 2D array based on row and column count
        String[][] records = new String[rowCount][colCount];
        //loop over both row and column
        for (int i = 0; i < rowCount; i++){
            for (int j=0; j< colCount; j++)
                //add value to the 2D array
                records[i][j] = (String)table.getValueAt(i, j);
        }
        //return the array
        return records;
    }
    
    //method to sort a table by the name of the member
    public void sortByName(JTable table){
        //call the generate2DArrayFrom method to get all the data from table
        String[][] records = generate2DArrayFrom(table);
        //if the table had no values show error and return 
        if (records.length == 0) {
            showNoDataError();
            return;
        }
        //call the mergeSort method on the above 2D array
        mergeSort(records, "string", 2);
        //insert the sorted 2D array into the tables
        insertIntoTable(table, records);
    }
    
    //method to sort a table by the age of the member
    public void sortByAge(JTable table){
        //call the generate2DArrayFrom method to get all the data from table
        String[][] records = generate2DArrayFrom(table);
        //if the table had no values show error and return 
        if (records.length == 0) {
            showNoDataError();
            return;
        }
        //call the mergeSort method on the above 2D array
        mergeSort(records, "number", 3);
        //insert the sorted 2D array into the tables
        insertIntoTable(table, records);
    }
    
    //method to sort a table by the age of the bmi of the member
    public void sortByBMI(JTable table){
        //call the generate2DArrayFrom method to get all the data from table
        String[][] records = generate2DArrayFrom(table);
        //if the table had no values show error and return 
        if (records.length == 0) {
            showNoDataError();
            return;
        }
        //call the mergeSort method on the above 2D array
        mergeSort(records, "number", 10);
        //insert the sorted 2D array into the tables
        insertIntoTable(table, records);
    }
    
    //method to sort a table by the fee paid by the member
    public void sortByFees(JTable table){
        //call the generate2DArrayFrom method and pass the table to get all the data
        String[][] records = generate2DArrayFrom(table);
        //if the table had no values show error and return
        if (records.length == 0) {
            showNoDataError();
            return;
        }
        //call the mergeSort method on the above 2D array
        mergeSort(records, "number", 9);
        //insert the sorted 2D array into the tables
        insertIntoTable(table, records); 
    }
    
    //method to insert data into the tbale
    public void insertIntoTable(JTable table, String[][] records){
        //loop over the 2D array records
        for(int i = 0; i < records.length; i++){
            for (int j = 0; j < records[i].length; j++)
                //insert value at current i,j index into the table
                table.setValueAt(records[i][j], i, j); 
        }
    }
    
    //method that return 2D string array of table data sorted by names
    public String[][] getSortedRecordByName(JTable table){
        //call the genereate2DArrayFrom method and pass the table to get all the data
        String[][] records = generate2DArrayFrom(table);
        //apply merge sort to the above array
        mergeSort(records, "string", 2);
        //return the sorted records
        return records;
    }
    
    //method that return 2D string array of table data sorted by mobile
    public String[][] getSortedRecordByMobile(JTable table){
        //call the genereate2DArrayFrom method and pass the table to get all the data
        String[][] records = generate2DArrayFrom(table);
        //apply merge sort to the above array
        mergeSort(records, "phonenumber", 5);
        //return the sorted records
        return records;
    }
    
    //method to show error in case there is no data to sort
    public void showNoDataError() {
        JOptionPane.showMessageDialog(null, "No data to sort from", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
