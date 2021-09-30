/**
 * Created by Jesse on 1/11/2017.
 */
public class Pixel {
    Pixel(){};
    Pixel(int R, int G, int B){red = R; green = G;blue = B;};
    private int red;
    private int green;
    private int blue;

    public void setRed(int R)
    {
        red = R;
    }
    public void setGreen(int G) { green = G; }
    public void setBlue(int B)
    {
        blue = B;
    }

    public final int getRed(){return red;}
    public final int getGreen(){return green;}
    public final int getBlue(){return blue;}

    public void invert()
    {
        red = 255 - red;
        green = 255 - green;
        blue = 255 - blue;
    }

    public void greyScale()
    {
        red = (red + blue + green)/3;
        green = red;
        blue = red;
    }
}
