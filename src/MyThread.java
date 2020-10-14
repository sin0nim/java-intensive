import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread
{
    private DbxClientV2 client;

    public MyThread(DbxClientV2 clientV2)
    {
        this.client = clientV2;
    }

    @Override
    public void run()
    {
        //TODO: print date in format: 20201008_174908
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date now = new Date();

        String nowDateTime =  formatter.format(now);

        try {
            //TODO: creaate screenshot
            Robot robot = new Robot();
            Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage image = robot.createScreenCapture(area);

            //TODO: convert it to InputStream
            ByteArrayOutputStream myOutputStrream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", myOutputStrream);
            InputStream myInputStream = new ByteArrayInputStream(myOutputStrream.toByteArray());

            //TODO: upload to Dropbox
            client.files().uploadBuilder("/" + nowDateTime+".png").uploadAndFinish(myInputStream);
        } catch (AWTException e) {
            e.printStackTrace();

        } catch (DbxException e) {
            System.out.println("DbxException -" + e.toString());

        } catch (IOException e) {
            System.out.println("IOException -" + e.toString());
        }

        System.out.println(nowDateTime);
    }
}
