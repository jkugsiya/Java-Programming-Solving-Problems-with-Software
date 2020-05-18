public class Part3 {
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
    
    public int countGenes(String dna)
    {
        int count = 0;
        int startIndex = 0;
        while(true)
        {
            String currentGene = findGene(dna,startIndex);
            if(currentGene.isEmpty())
            {
                break;
            }
            count += 1;
            startIndex = dna.indexOf(currentGene, startIndex) + 
                         currentGene.length();;
        }
        return count;
    }
    
    public void testCountGenes()
    {
        System.out.println();
    }
}
