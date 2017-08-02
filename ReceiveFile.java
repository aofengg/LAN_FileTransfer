import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;

public class ReceiveFile extends Thread{
	private File file = null;
	private String ipAddress = "";
	private int port = 6000;
	
	public ReceiveFile (File filename, String ip_address, int port_num) {
		this.file = filename;
		this.ipAddress = ip_address;
		this.port = port_num;
	}

	public void run(){
		try {			
			//Socket socket = new Socket("127.0.0.1", 4700);
			//Socket socket = new Socket("149.125.30.94", 8080);
			
			Socket socket = new Socket(this.ipAddress, this.port);
			if (socket.isConnected()) {
				System.out.println("Connection failed.");
			}
			DataInputStream dos = null;

			//File file = new File(file_name);
			FileOutputStream fis = new FileOutputStream(this.file);

			int length;
			byte[] sendBytes = null;
			sendBytes = new byte[1024];
			float receivedsum = 0;
			dos = new DataInputStream(socket.getInputStream());
			
			while ((length = dos.read(sendBytes, 0, sendBytes.length)) > 0) {
				fis.write(sendBytes, 0, length);
				receivedsum += ((float)length) / (1024 * 1024);
				
				System.out.println("The client has received "+ receivedsum + "M data from the server.");
				String s = "The client has received "+ receivedsum + "M data from the server.\n";			
			}
			TransferMain.output("The file has been received.\n");
			socket.close();
			

		} catch (Exception e) {
			System.out.println("Error" + e);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
