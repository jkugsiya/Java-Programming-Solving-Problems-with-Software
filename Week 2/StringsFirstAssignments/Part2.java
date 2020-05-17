
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon)
    {
        int cases = -1;
        if(dna.equals(dna.toUpperCase()))
        {
            cases = 1;
        }
        else if(dna.equals(dna.toLowerCase()))
        {
            cases = 0;
        }
        else
        {
            return "";
        }
        int startIndex = dna.indexOf(startCodon);
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        if(startIndex == -1 || stopIndex == -1)
        {
            return "";
        }
        if((stopIndex - startIndex) % 3 != 0)
        {
            return "";
        }
        String dnaStrand = dna.substring(startIndex, stopIndex+3);
        return dnaStrand;
    }
    
    public void testSimpleGene()
    {
        String dna1 = "ADFDATGATASDFDAGTAYGDATAAFASTF";
        String dna2 = "AFDHAHDFHHJIUYFTAA";
        String dna3 = "ATGAFDHAHDFHHJIUYF";
        String dna4 = "ATGATASDDFDAGTAYGDATAA";
        String dna5 = "LASDHFPOYFHHGAPFDN";
        System.out.println(dna1);
        System.out.println(findSimpleGene(dna1, "ATG", "TAA"));
        System.out.println(dna2);
        System.out.println(findSimpleGene(dna2, "ATG", "TAA"));
        System.out.println(dna3);
        System.out.println(findSimpleGene(dna3, "ATG", "TAA"));
        System.out.println(dna4);
        System.out.println(findSimpleGene(dna4, "ATG", "TAA"));
        System.out.println(dna5);
        System.out.println(findSimpleGene(dna5, "ATG", "TAA"));
    }
}
