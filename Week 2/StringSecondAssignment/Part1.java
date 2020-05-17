
public class Part1 {

    public int findStopCodon(String dna, int startIndex, String stopCodon)
    {
        int currIndex = dna.indexOf(stopCodon, startIndex+1);
        while(currIndex != -1)
        {
            if((currIndex - startIndex) % 3 == 0)
            {
                return currIndex;
            }
            currIndex = dna.indexOf(stopCodon, currIndex + 3);
        }
        return dna.length();
    }
    
    public String findGene(String dna,int where)
    {
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1)
        {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if(minIndex == dna.length())
            return "";
        return dna.substring(startIndex, minIndex + 3);
        
    }
    
    public void printAllGenes(String dna)
    {
        int startIndex = 0;
        while(true)
        {
            String currentGene = findGene(dna,startIndex);
            if(currentGene.isEmpty())
            {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + 
                         currentGene.length();;
        }
    }
    
    public void testFindStopCodon()
    {            
        System.out.println(findStopCodon("ATGETFASTAAAAAFDFGA",0,"TAA"));
    }
    
    public void testFindGene()
    {
        String dna1 = "FADFLADHUGOADFTAAJFALS";
        String dna2 = "ADFKAJKATGJHUAHUATDTAAKJAOHDATAG";
        String dna3 = "ASDFJLKJATGLKJNMHDJUTAG";
        String dna4 = "AGLALKJATGALKOIHGAIJOIGJ";
        String dna5 = "AAHHDAHATGJAYKUGSIYAJATAGTAA";
        printAllGenes(dna1+dna2+dna3+dna4+dna5);
    }
    
}
