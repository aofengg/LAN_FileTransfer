import java.awt.EventQueue;
import java.io.File;

import javax.swing.SwingWorker;

public class TransferMain {
	static MainWindow frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainWindow();
					frame.setVisible(true);
					//frame.getMediaPlayer().prepareMedia("/Users/aofengg/Movies/song.flv");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void sendData() {
		SendFile s = new SendFile();
		s.start();
		
	}
	public static void receiveData(File file, String ip_address, int port_num) {
		ReceiveFile c = new ReceiveFile(file, ip_address, port_num);
		//RunAsClient c = new RunAsClient();
		c.start();
		
		while(c.isAlive()) {

		}
		System.out.println("The file has been received.");
		
	}
	public static void output(String s) {
		frame.txtReceived.setText(s);
		
	}
	public static void startServer() {
		// TODO Auto-generated method stub
		StartTheServer s= new StartTheServer();
		s.start();
	}
	public static void startClient() {
		// TODO Auto-generated method stub
		StartTheClient s= new StartTheClient();
		s.start();
	}

}
