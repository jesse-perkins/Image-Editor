import java.io.BufferedReader;
import java.io.File;
import java.util.Scanner;

/**
 * Created by Jesse on 1/11/2017.
 */

public class Image {
    Image (){};
    private Pixel[][] imageArray;
    private int width;
    private int height;

    public void load(String fileName)
    {
        try //p3 width heigth magic number
        {
            Scanner input = new Scanner(new File(fileName)).useDelimiter("((#[^\\n]*\\n)|(\\s+))+");
            input.next();
            width = input.nextInt();
            height = input.nextInt();
            input.nextInt();
            imageArray = new Pixel[height][width];
            for(int i = 0; i < height; i++)
            {
                for(int j = 0; j<width; j++)
                {
                    imageArray[i][j]= new Pixel(input.nextInt(), input.nextInt(),input.nextInt());
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public String toString()
    {
        StringBuilder output = new StringBuilder("P3\n" + width + " " + height + '\n'+ "255\n");
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j<width; j++)
            {
                output.append(Integer.toString(imageArray[i][j].getRed())+ '\n'
                        + Integer.toString(imageArray[i][j].getGreen())+ '\n'
                        + Integer.toString(imageArray[i][j].getBlue())+ '\n');
            }
        }
        return output.toString();
    }
    public String invert()
    {
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j<width; j++)
            {
                imageArray[i][j].invert();
            }
        }
        return this.toString();
    }

    public String grayScale()
    {
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j<width; j++)
            {
                imageArray[i][j].greyScale();
            }
        }
        return this.toString();
    }

    public String motionBlur(int blurLength)
    {
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j<width; j++)
            {
                int actualBlurLength = blurLength;
                if(width - j < blurLength )
                {
                    actualBlurLength = width-j;
                }
                int actualRed = 0, actualGreen = 0, actualBlue = 0;
                for (int k = 0; k< actualBlurLength; k++)
                {
                    actualRed += imageArray[i][j+k].getRed();
                    actualGreen += imageArray[i][j+k].getGreen();
                    actualBlue += imageArray[i][j+k].getBlue();
                }
                actualRed /= actualBlurLength;
                actualGreen /= actualBlurLength;
                actualBlue /= actualBlurLength;
                imageArray[i][j].setRed(actualRed);
                imageArray[i][j].setGreen(actualGreen);
                imageArray[i][j].setBlue(actualBlue);
            }
        }
        return this.toString();
    }

    public String emboss()
    {
        for(int i = height -1; i >= 0 ; i--)
        {
            for(int j = width -1; j >= 0; j--)
            {
                if( i == 0 || j == 0)
                {
                    imageArray[i][j].setRed(128);
                    imageArray[i][j].setGreen(128);
                    imageArray[i][j].setBlue(128);
                }
                else
                {
                    int newValue = embossHelper(imageArray[i][j], imageArray[i-1][j-1]);
                    imageArray[i][j].setRed(newValue);
                    imageArray[i][j].setGreen(newValue);
                    imageArray[i][j].setBlue(newValue);
                }
            }
        }
        return this.toString();
    }

    public int embossHelper(Pixel current, Pixel topLeft)
    {
        int newValue = 0;
        int redDiff, greenDiff, blueDiff;

        redDiff = current.getRed() - topLeft.getRed();
        greenDiff = current.getGreen() - topLeft.getGreen();
        blueDiff = current.getBlue() - topLeft.getBlue();

        if(Math.abs(redDiff) >= Math.abs(greenDiff) && Math.abs(redDiff) >= Math.abs(blueDiff))
        {
            newValue = redDiff +128;
        }
        else if(Math.abs(greenDiff) >= Math.abs(blueDiff))
        {
            newValue = greenDiff +128;
        }
        else
        {
            newValue = blueDiff + 128;
        }

        if(newValue < 0) {newValue = 0;}
        if(newValue > 255) {newValue = 255;}
        return newValue;
    }
}
