import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFileChooser;

public class SendFile extends Thread {
	public void run() {

		JFileChooser chooser = new JFileChooser();
		int v = chooser.showOpenDialog(null);
		if (v == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();

			try {
				ServerSocket server = null;
				// get server IP address
				InetAddress i = InetAddress.getLocalHost();
				System.out.println(i.toString()); // 获取当前主机的主机名和IP地址
				System.out.println("ipaddress:" + i.getHostAddress()); // 获取本机ip地址。
				System.out.println("hostname:" + i.getHostName()); // 获取本机主机名。

				try {
					server = new ServerSocket(8080);
				} catch (Exception e) {
					System.out.println("Can not be listen to:" + e);
				}

				Socket socket = null;
				try {
					socket = server.accept();
				} catch (Exception e) {
					System.out.println("Error." + e);
				}
				
				int length = 0;
				byte[] sendBytes = null;
				DataOutputStream dos = null;
				// FileOutputStream dos = null;
				FileInputStream fis = null;
				dos = new DataOutputStream(socket.getOutputStream());
				fis = new FileInputStream(file);
				dos.flush();
				// FileOutputStream dos1 = new
				// FileOutputStream(socket.getOutputStream());

				sendBytes = new byte[1024];

				// send video
				while (fis.available() != 0) {
					length = fis.read(sendBytes, 0, sendBytes.length);
					dos.write(sendBytes, 0, length);
					dos.flush();
				}

				socket.close();
				server.close();
				fis.close();
				dos.close();

			} catch (Exception e) {
				System.out.println("Error" + e);
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
