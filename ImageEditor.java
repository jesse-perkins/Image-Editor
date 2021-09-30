import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * Created by Jesse on 1/11/2017.
 */
public class ImageEditor {
    public ImageEditor(){}
    private Image currentImage = new Image();
    public void load(String readIn)
    {
        try{
            currentImage.load(readIn);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public String invert()
    {
        return currentImage.invert();
    }
    public String grayScale()
    {
        return currentImage.grayScale();
    }
    public String motionBlur(int blurLength)
    {
        return currentImage.motionBlur(blurLength);
    }
    public String emboss()
    {
        return currentImage.emboss();
    }

    public static void main(String[] args)
    {
        boolean bail = false;
        if(args == null || args.length < 3)
        {
            bail = true;
        }
        else if(!args[2].equals("grayscale") && !args[2].equals("invert") && !args[2].equals("emboss") && !args[2].equals("motionblur"))
        {
            bail = true;
        }
        else if(!args[2].equals("motionblur") && args.length > 3 )
        {
            bail = true;
        }
        else if(args[2].equals("motionblur"))
        {
            if(args.length != 4)
            {
                bail = true;
            }
            else
            {
                try{
                    Integer.parseInt(args[3]);
                }
                catch(Exception e)
                {
                    bail = true;
                }
            }

        }
        if(bail)
        {
            System.out.println("\nUSAGE: java ImageEditor in-file out-file (grayscale|invert|emboss|motionblur motion-blur-length)");
            return;
        }

        try {
            ImageEditor current = new ImageEditor();
            current.load(args[0]);
            PrintWriter outFile = new PrintWriter(args[1]);
            switch(args[2]){
                case "grayscale":
                    outFile.print(current.grayScale());
                    break;
                case "invert":
                    outFile.print(current.invert());
                    break;
                case "emboss":
                    outFile.print(current.emboss());
                    break;
                case "motionblur":
                    outFile.print(current.motionBlur(Integer.parseInt(args[3])));
                    break;
            }
            outFile.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
