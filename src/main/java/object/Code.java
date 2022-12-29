package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Code extends SuperObject{

    public Code() {

        name = "Code";
        try {

            image = ImageIO.read(getClass().getResourceAsStream("/objects/code.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;

    }
}