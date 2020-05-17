import edu.duke.*; 
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
                         currentGene.length();
        }
    }
    
    public StorageResource getAllGenes(String dna)
    {
        int startIndex = 0;
        StorageResource store = new StorageResource();
        while(true)
        {
            String currentGene = findGene(dna,startIndex);
            if(currentGene.isEmpty())
            {
                break;
            }
            store.add(currentGene);
            
            startIndex = dna.indexOf(currentGene, startIndex) + 
                         currentGene.length();
        }
        
        return store;
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
    
    public float cgRatio(String dna)
    {
        int c = howMany("C", dna);
        int g = howMany("G", dna);
        float rat = (float)(c+g)/dna.length();
        return rat;
    }
    
    public int countCTG(String dna)
    {
        return howMany("CTG",dna);
    }
    
    public void processGenes(StorageResource sr)
    {
        int long9 = 0;
        int cgRatioGreaterThan_point35 = 0;
        int longestGene = 0;
        for(String s : sr.data())
        {
            if(s.length() > 60)
            {
                long9 += 1;
                System.out.println(s);
            }
            if(cgRatio(s) > 0.35)
            {
                cgRatioGreaterThan_point35 += 1;
                System.out.println(s);
            }
            if(s.length() > longestGene)
            {
                longestGene = s.length();
            }
        }
        System.out.println("Strings Longer than 60 characters: "+long9);
        System.out.println("Strings whose C-G ratio is higher "+
                            "than 0.35: " + cgRatioGreaterThan_point35);
        System.out.println("Length of longest gene: " + longestGene);                   
    }
    
    public void testProcessGenes()
    {
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        
        StorageResource genes = new StorageResource();
        genes = getAllGenes(dna.toUpperCase());
        
        processGenes(genes);
        System.out.println("CTG count: " + countCTG(dna));
    }
    
    public void testCGRatio()
    {
        System.out.println(cgRatio("ATGCCATAG"));
    }
    
    public void testGAG()
    {
        StorageResource list = new StorageResource();
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        list = getAllGenes(dna);
        int count = 0;
        for(String s: list.data())
        {
            System.out.println(s);
            count += 1;
        }
        System.out.println(count);
    }
    

}
