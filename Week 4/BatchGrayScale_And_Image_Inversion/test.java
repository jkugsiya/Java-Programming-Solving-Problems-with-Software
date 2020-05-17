import edu.duke.*;
import java.io.*;
public class test {

    public void createAllFilesGray()
    {
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            ImageResource inImage = new ImageResource(f);
            String cName = inImage.getFileName();
            String nName = "gray-" + cName;
            for(Pixel px : inImage.pixels())
            {
                int avg = (px.getRed() + px.getBlue() + px.getGreen())/3;
                px.setRed(avg);
                px.setGreen(avg);
                px.setBlue(avg);
            }
            inImage.setFileName("Gray/" + nName);
            inImage.draw();
            inImage.save();
        }
    }
    
    
    public void createInvertedImages()
    {
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            ImageResource image = new ImageResource(f);
            String cName = image.getFileName();
            String newName = "inverted-" + cName;
            for(Pixel px : image.pixels())
            {
                px.setRed(255-px.getRed());
                px.setGreen(255-px.getGreen());
                px.setBlue(255-px.getBlue());
            }
            
            image.setFileName("Inverted/" + newName);
            image.draw();
            image.save();
        }
    }
    
}
