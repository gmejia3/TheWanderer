package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class ComputerDesk extends SuperObject{

    public ComputerDesk() {

        name = "Desk";
        try {

            image = ImageIO.read(getClass().getResourceAsStream("/objects/desk2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;

    }
}
