import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

public class Main {

    public static void main(String[] args) {

        String ACCESS_TOKEN = "rAOAz0GQupIAAAAAAAAAAcSo7bufdPePTBptG4VU6qLy7E7iF9bUvXDVuolvr10t";

        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/Java-intensive-20201010").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);


       while(true)
        {
            MyThread thread = new MyThread(client);
            thread.start();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }




    }
}
