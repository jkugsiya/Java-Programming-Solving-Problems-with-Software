
public class Part3 {
    public Boolean twoOccurrences(String stringa, String stringb)
    {
        int firstOc = stringb.indexOf(stringa);
        int secOc = stringb.indexOf(stringa, firstOc+stringa.length());
        if(firstOc == -1 || secOc == -1)
            return false;
        else
            return true;
    }
    
    public String lastPart(String stringa, String stringb)
    {
        int oc = stringb.indexOf(stringa);
        if(oc == -1)
        {
            return stringb;
        }
        String ret = stringb.substring(stringa.length()+oc, stringb.length());
        return ret; 
    }
    
    public void testing()
    {
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(twoOccurrences("atg", "ctgtatgta"));
        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
    }
}
