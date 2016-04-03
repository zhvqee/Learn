import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * Created by qee on 2016/3/30.
 */
public class TimeServerHandler implements Runnable {
    private  Socket socket;
    public TimeServerHandler(Socket socket) {
        this.socket=socket;
    }

    public void run() {
        BufferedReader bufferedReader = null;
        PrintWriter printWriter =null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter= new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
            String body=null;
            while (true){
                System.out.println("print client send info");
                body =bufferedReader.readLine();
                if (body==null){
                    break;
                }
                System.out.println(body);
                printWriter.println(new Date(System.currentTimeMillis()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            if(socket !=null){
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (printWriter!=null){
                printWriter.close();
            }
        }


    }
}
