package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Coffee extends SuperObject{

    public Coffee() {

        name = "Coffee";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Coffee.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}