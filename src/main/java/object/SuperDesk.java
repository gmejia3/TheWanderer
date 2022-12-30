package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class SuperDesk extends SuperObject{

    public SuperDesk() {

        name = "SuperDesk";
        try {

            image = ImageIO.read(getClass().getResourceAsStream("/objects/BigDesk.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;

    }
}