import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class coldCSV {

    public CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord coldest = null;
        for(CSVRecord record : parser)
        {
            if(coldest == null)
            {
                coldest = record;
            }
            else
            {
                double currTemp = Double.parseDouble(record.get("TemperatureF"));
                double coldestTillNow = Double.parseDouble(coldest.get("TemperatureF"));
                if(currTemp < coldestTillNow && currTemp > -200)
                {
                    coldestTillNow = currTemp;
                    coldest = record;
                }
            }
        }
        return coldest;
    }
    
    
    public void testColdestHourInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("Time : " + coldest.get("DateUTC") +
                    " Temperature: " + coldest.get("TemperatureF"));
    }
    
    public String fileWithColdestTemperature()
    {
        double coldestSoFar = -9999;
        String coldestFile = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
           
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            double currentTemp = Double.parseDouble(current.get("TemperatureF"));
            if(coldestSoFar == -9999)
            {
                coldestSoFar = currentTemp;
            }
            else
            {
                if(currentTemp < coldestSoFar)
                {
                    coldestSoFar = currentTemp;
                    coldestFile = f.getName();
                }
            }
        }
        return coldestFile;
    }
    
    public void testFileWithColdestTemperature()
    {
        String fileWithColdestTemperature = fileWithColdestTemperature();
        FileResource fr = new FileResource("nc_weather/2014/" + fileWithColdestTemperature);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRecord = coldestHourInFile(parser);
        System.out.println("Coldest day was in file: " + fileWithColdestTemperature);
        System.out.println("Coldest temperature on that day was: " + coldestRecord.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were: ");
        for(CSVRecord record : fr.getCSVParser())
        {
            System.out.println(record.get("DateUTC") + " " + record.get("TemperatureF"));
        }
        System.out.println("Done");
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser)
    {
        CSVRecord lowestRecord = null;
        double currHumidity = 9999;
        double lowestHumidity = 9999;
        for(CSVRecord record : parser)
        {
            if(lowestRecord == null)
            {
                lowestRecord = record;
            }
            else
            {
                String lowestHumidityString = lowestRecord.get("Humidity");
                String currHumidityString = record.get("Humidity");
                if(lowestHumidityString.equals("N/A"))
                {
                    lowestHumidity = 9999;
                }
                else
                {
                    lowestHumidity = Double.parseDouble(lowestHumidityString);
                }
                if(currHumidityString.equals("N/A"))
                {
                    currHumidity = 9999;
                }
                else
                {
                    currHumidity = Double.parseDouble(currHumidityString);
                }
                if(currHumidity < lowestHumidity)
                {
                    lowestHumidity = currHumidity;
                    lowestRecord = record;
                }
                
            }
        }
        return lowestRecord;
    }
    
    public void testLowestHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.print("Lowest Humidity was " + csv.get("Humidity") +
                        " at " + csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles()
    {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowest = null;
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVRecord curr = lowestHumidityInFile(fr.getCSVParser());
            if(lowest == null)
            {
                lowest = curr;
            }
            double lowestHumidity = Double.parseDouble(lowest.get("Humidity"));
            double currHumidity = Double.parseDouble(curr.get("Humidity"));
            if(currHumidity < lowestHumidity)
            {
                lowestHumidity = currHumidity;
                lowest = curr;
            }
        }
        return lowest;
    }
    
    public void testLowestHumidityInManyfiles()
    {
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " +
                    csv.get("Humidity") + " at " +
                    csv.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser)
    {
        double sumOfTemp = 0;
        int noOfTemp = 0;
        for(CSVRecord record: parser)
        {
            double temp = Double.parseDouble(record.get("TemperatureF"));
            if(temp < -200)
            {
                temp = 0;
            }
            sumOfTemp += temp;
            noOfTemp += 1;
        }
        return (sumOfTemp/noOfTemp);
    }
    
    public void testAverageTemperatureInFile()
    {
        FileResource fr = new FileResource();
        double avgTemp = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + avgTemp);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value)
    {
        double sumOfTemp = 0;
        int noOfTemp = 0;
        double humidity = 0;
        for(CSVRecord record: parser)
        {
            double temp = Double.parseDouble(record.get("TemperatureF"));
            String humidityString = record.get("Humidity");
            if(temp < -200)
            {
                temp = 0;
            }
            if(humidityString.equals("N/A"))
            {
                humidity = 0;
            }
            else
            {
                humidity = Double.parseDouble(humidityString);
            }
            if(humidity >= value)
            {
                sumOfTemp += temp;
                noOfTemp += 1;
            }
            
        }
        if(noOfTemp == 0)
        {
            return -1;
        }
        else
        {
            return (sumOfTemp/noOfTemp);
        }
    }
    
    public void testAverageTemperatureWithHighHumidityInFile()
    {
        FileResource fr = new FileResource();
        double avgTempWithHighHumidity = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if(avgTempWithHighHumidity == -1)
        {
            System.out.println("No temperatures with that humidity");
        }
        else
        {
            System.out.println("Average temperature with that humidity: " + 
                        avgTempWithHighHumidity);
        }
    }
    
}

