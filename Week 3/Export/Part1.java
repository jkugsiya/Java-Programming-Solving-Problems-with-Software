import edu.duke.*;
import org.apache.commons.csv.*;
public class Part1 {

    public void tester()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //System.out.println(numberOfExporters(parser,"cocoa"));
        bigExporters(parser, "$999,999,999,999");
    }
    
    public String countryInfo(CSVParser parser, String country)
    {
        for(CSVRecord record : parser)
        {
            String countries = record.get("Country");
            if(countries.contains(country))
            {
                String export = record.get("Exports");
                String value = record.get("Value (dollars)");
                String ret = country + ": " + export + ": " + value;
                return ret;
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1,
                                              String exportItem2)
    {
        int no = 0;
        for(CSVRecord record : parser)
        {
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2))
            {
                System.out.println(record.get("Country"));
                no += 1;
            }
        }
        if(no == 0)
            System.out.println("0");
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem)
    {
        int no = 0;
        for(CSVRecord record : parser)
        {
            String export = record.get("Exports");
            if(export.contains(exportItem))
            {
                no += 1;
            }
        }
        return no;
    }
    
    public void bigExporters(CSVParser parser, String amount)
    {
        for(CSVRecord record : parser)
        {
            String value = record.get("Value (dollars)");
            if(value.length() > amount.length())
            {
                System.out.print(record.get("Country") + " ");
                System.out.println(value);
            }
        }
    }
    
}
