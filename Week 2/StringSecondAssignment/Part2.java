
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {

    public int howMany(String stringa, String stringb)
    {
        int count = 0;
        int ind = 0;
        while(ind < stringb.length())
        {
            if((stringb.indexOf(stringa, ind)) == -1)
            {
                return count;
            }
            else
            {
                count += 1;
            }
            ind = stringb.indexOf(stringa, ind) + stringa.length();
        }
        return count;
    }
    
    public void testHowMany()
    {
        System.out.println(howMany("AA","ATAAAA"));
        System.out.println(howMany("GAA","ATGAACGAATTGAATC"));
    }
    
}
