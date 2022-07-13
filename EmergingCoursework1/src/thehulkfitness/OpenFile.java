/*
 * @author Ardent Sharma 20049385 
 * @author Ashma Maharjan 20048835 
 * @author Bhishan Khatiwoda 20049401 
 * @author Jeena Dahal 20048904 
 * @version 1.1.1
 */

package thehulkfitness;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OpenFile {
    //instantiate private variables for rowlist and table
    private ArrayList<String[]> rowList;
    private JTable myTable;
    
    //constructor that instantiates arraylist and table
    public OpenFile(JTable myTable)
    {
        rowList = new ArrayList<>();
        this.myTable = myTable;
    }
    
    //read the contents of file passed and store it in the arraylist
    public void readFile(File file)
    {
        //try to read the file
        try
        {
            //get the current row count
            int rowCount = myTable.getRowCount();
            int maxValue = 0;
            //get the mas value of member id from the pre existing values of table
            for (int i = 0; i <rowCount; i++) {
                int memberId = Integer.parseInt((String)myTable.getValueAt(i, 0));
                if (memberId > maxValue) {
                    maxValue = memberId;
                }
            }
            //create instance of reader to read file
            BufferedReader fileReader = new BufferedReader(new FileReader(file)); 
            String row;
            //read file until it has new line
            while ((row = fileReader.readLine())!= null)
            {
                //split the single line of string from file into multiple values for each columns
                String[] initialData = row.split("-");
                //create array with one more length to add id
                String[] finalData = new String[initialData.length + 1];
                //add id  as the first value of finalData which is the maxValue of currentTable plus one
                finalData[0] = String.valueOf(maxValue+1);
                //copy all the value taken from file i.e. initialData to the finalData
                System.arraycopy(initialData, 0, finalData, 1, initialData.length);
                //add the row data to arrayList
                rowList.add(finalData);
                //increase counter of max value
                maxValue++;
            }
        }//throw exception if could not read file
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    //add the read file to table
    public void addFileToTable()
    {
        //only run if a file has been readen and rowList is not null 
        if (rowList != null && !rowList.isEmpty())
        {
            //get the default tabel model of the table
            DefaultTableModel myModel = (DefaultTableModel) myTable.getModel();
            //for elements in the arrayList add row to table
            for (String[] row : rowList) {
                myModel.addRow(row); 
            }
        }
        //file not read
        else {
            JOptionPane.showMessageDialog(null, "invalid file", "ok",1);
        }
    }
}
