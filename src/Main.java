import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        search();
    }

 // This is the method "searcData()" which will search CSV file and store it in details variable. 
    public static List<String> searchData(int searchColumnIndex, String searchString) throws IOException {
        List<String> result_Row = new ArrayList<>();
        BufferedReader bu = new BufferedReader(new FileReader("src/ProgrammingAssignment.csv"));
        String details;
        while ((details = bu.readLine()) != null) {
            String[] data = details.split(",");
            if (data[searchColumnIndex].equals(searchString)) {
                result_Row.add(details);
            }
        }
        bu.close();
        return result_Row;
    }

 // This is the method "search()" which will search data according to the given input.
    public static void search() throws IOException {
    	System.out.println("Select one option");
        System.out.println("Enter 1 to Search by column name: ");
        System.out.println("Enter 2 to Search by city and state: ");
    	Scanner sr = new Scanner(System.in);
        int searchInput = sr.nextInt();
        if (searchInput == 1) {
            System.out.println("Enter the column name:");
            sr.nextLine();
            String columnName = sr.nextLine();
            System.out.println("Enter the search string:");
            String searchString = sr.nextLine();
            List<String> result = new ArrayList<>();
            switch (columnName.toLowerCase()) {
                case "bank name":
                	result = searchData(1, searchString);
                    break;
                case "type":
                    result = searchData(2, searchString);
                    break;
                case "city":
                    result = searchData(3, searchString);
                    break;
                case "state":
                    result = searchData(4, searchString);
                    break;
                case "zip code":
                    result = searchData(5, searchString);
                    break;
            }
            print(result);
        } else if(searchInput == 2){
            System.out.println("Enter the city name:");
            sr.nextLine();
            String city = sr.nextLine();
            System.out.println("Enter the state:");
            String state = sr.nextLine();
            List<String> result = searchData(3, city);
            Iterator<String> iterator = result.listIterator();
            while(iterator.hasNext()){
                if(!iterator.next().contains(state)){
                    iterator.remove();
                }
            }
            print(result);
        }
        sr.close();
    }
    
// This is the method "print()" which will print the result of the final data.
    public static void print(List<String> result) {
    	System.out.println("Final Result: ");
    	if(result.size() != 0) {
    	 for(int i = 0; i<result.size();i++) {
         	System.out.println(result.get(i));
    		 }
    	 }  else {
			 System.out.println("No Data present for given input in CSV file");
		 }
    }
    
}