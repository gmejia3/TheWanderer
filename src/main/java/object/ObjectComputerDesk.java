package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class ObjectComputerDesk extends SuperObject{

    public ObjectComputerDesk() {

        name = "Desk";
        try {

            image = ImageIO.read(getClass().getResourceAsStream("/objects/desk2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
