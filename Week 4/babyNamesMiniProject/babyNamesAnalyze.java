import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class babyNamesAnalyze {

    public void printTotalBirths(FileResource fr)
    {
        int totalBirths = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        int totalGirlNames = 0;
        int totalBoyNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            int currBirth = Integer.parseInt(rec.get(2));
            totalBirths += currBirth;
            if(rec.get(1).equals("F"))
            {
                totalGirls += currBirth;
                totalGirlNames += 1;
            }
            else
            {
                totalBoys += currBirth;
                totalBoyNames += 1;
            }
        }
        System.out.print("Total Births: " + totalBirths +
                         "\nTotal Girl Births: " + totalGirls +
                         "\nTotal Boy Births: " + totalBoys +
                         "\nNumber of Girl Names: " + totalGirlNames +
                         "\nNumber of Boy Names: " + totalBoyNames);
    }
    
    public void testPrintTotalBirths()
    {
        FileResource fr = new FileResource();
        printTotalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender)
    {
        int rank = 0;
        FileResource fr = new FileResource("us_babynames_by_year/yob"+year+".csv");
        for(CSVRecord rec : fr.getCSVParser(false))
        {
            if(rec.get(1).equals(gender))
            {
                rank += 1;
                if(rec.get(0).equals(name))
                {
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public void testGetRank()
    {
        int rank = getRank(1971, "Emily", "M");
        if(rank != -1)
            System.out.println("Rank of the given name : " + rank);
        else
            System.out.println("No such name found");
    }
    
    public String getName(int year, int rank, String gender)
    {
        int currRank = 0;
        FileResource fr = new FileResource("us_babynames_by_year/yob"+year+".csv");
        for(CSVRecord rec: fr.getCSVParser(false))
        {
           if(rec.get(1).equals(gender))
           {
               currRank += 1;
               if(currRank == rank)
               {
                   return rec.get(0);
               }
           }
        }
        return "NP NAME";
    }
    
    public void testGetName()
    {
        String name = getName(1982, 450, "M");
        System.out.println(name);
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender)
    {
        int currRank = getRank(year, name, gender);
        String newName = getName(newYear, currRank, gender);
        System.out.println(name + " born in " + year +
                            " would be " + newName +
                            " if she was born in " + newYear);
    }
    
    public void testWhatIsNameInYear()
    {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public int yearOfHighestRank(String name, String gender)
    {
        int highestRank = 0;
        int highestRankYear = -1;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.replaceAll("[^0-9]", ""));
            int currRank = getRank(year, name, gender);
            if(highestRank == 0 && currRank != -1)
            {
                highestRank = currRank;
                highestRankYear = year;
            }
            if(currRank < highestRank && currRank != -1)
            {
                highestRank = currRank;
                highestRankYear = year;
            }
        }
        return highestRankYear;
    }
    
    public void testYearOfHighestRank()
    {
        int highestRankYear = yearOfHighestRank("Mich", "M");
        System.out.println("Year of Highest Rank : " + highestRankYear);
    }
    
    public double getAverageRank(String name, String gender)
    {
        int sumOfRank = 0;
        int noOfRanks = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.replaceAll("[^0-9]", ""));
            int currRank = getRank(year, name, gender);
            if(currRank != -1)
            {
                sumOfRank += currRank;
                noOfRanks += 1;
            }
        }
        if(sumOfRank == 0)
            return -1;
        else
            return (double)sumOfRank/noOfRanks;
    }
    
    
    public void testGetAverageRank()
    {
        double avgRank = getAverageRank("Robert", "M");
        System.out.println("Average rank of the given name: " + avgRank);
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender)
    {
        int total = 0;
        int currRank = 0;
        int givenRank = getRank(year, name, gender);
        if(givenRank == -1)
        {
            System.out.println("NO Name found in the given year");
            return -1;
        }
        FileResource fr = new FileResource("us_babynames_by_year/yob"+year+".csv");
        for(CSVRecord rec: fr.getCSVParser(false))
        {
            if(rec.get(1).equals(gender))
            {
                currRank += 1;
                if(currRank == givenRank)
                {
                    return total;
                }
                total += Integer.parseInt(rec.get(2));
            }
        }
        return -1;
    }
    
    public void testGetTotalBirthsRankedHigher()
    {
        int tot = getTotalBirthsRankedHigher(1990, "Emily", "F");
        System.out.println(tot);
    }
    
}
