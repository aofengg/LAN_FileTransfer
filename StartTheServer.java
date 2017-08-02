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

public class StartTheServer extends Thread {
	public void run() {

		File file = new File("/Users/aofengg/Movies/temp");

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

			String line = "Enter 1 or 2. /n";

			PrintWriter os = new PrintWriter(socket.getOutputStream());
			os.println(line);
			os.flush();
			while (true) {
				BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				String s1 = is.readLine();
				System.out.println("The server seceive: " + s1);
				if (s1.equals("1")) {
					System.out.println("Here!/n");
					System.out.println("Server:" + s1);
					file = new File("/Users/aofengg/Movies/1.jpg");

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
					break;
				}
				if (s1.equals("2")) {
					System.out.println("Server:" + s1);
					file = new File("/Users/aofengg/Movies/2.jpg");

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
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Error" + e);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
