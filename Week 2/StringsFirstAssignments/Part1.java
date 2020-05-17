
public class Part1 {
    public String findSimpleGene(String dna)
    {
        int startIndex = dna.indexOf("ATG");
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
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
        String dna5 = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println(dna1);
        System.out.println(findSimpleGene(dna1));
        System.out.println(dna2);
        System.out.println(findSimpleGene(dna2));
        System.out.println(dna3);
        System.out.println(findSimpleGene(dna3));
        System.out.println(dna4);
        System.out.println(findSimpleGene(dna4));
        System.out.println(dna5);
        System.out.println(findSimpleGene(dna5));
    }
}
