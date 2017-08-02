import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.InetAddress;
import java.net.ServerSocket;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtIP;
	private JTextField txtPort;
	JTextArea txtReceived;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		
		JButton btnSendFile = new JButton("Send File");
		
		JScrollPane scrollPane = new JScrollPane();
		
		final JTextArea txtIpPort = new JTextArea();
		scrollPane.setColumnHeaderView(txtIpPort);
		
		JButton btnStartserver = new JButton("StartServer");
		btnStartserver.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				TransferMain.startServer();				
				try {
					InetAddress i = InetAddress.getLocalHost();
					txtIpPort.setText("ipaddress:\n" + i.getHostAddress() + "\n" + "Enter 1 to download 1. \n" + "Enter 2 to download 2. \n");
				} catch (Exception e) {
					System.out.println("Error" + e);
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(12)
							.addComponent(btnSendFile)))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnStartserver)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(btnSendFile)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addComponent(btnStartserver))
		);
		panel.setLayout(gl_panel);
		btnSendFile.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				TransferMain.sendData();
				
				try {
					InetAddress i = InetAddress.getLocalHost();
					txtIpPort.setText("ipaddress:\n" + i.getHostAddress() + "\n");
				} catch (Exception e) {
					System.out.println("Error" + e);
				}
				
			}
		});
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		
		JButton btnReceiveFile = new JButton("Receive File");
		
		JLabel lblIp = new JLabel("Server IP：");
		
		txtIP = new JTextField();
		txtIP.setText("127.0.0.1");
		txtIP.setToolTipText("");
		txtIP.setColumns(10);
		
		JLabel lblPort = new JLabel("Port:");
		
		txtPort = new JTextField();
		txtPort.setText("8080");
		txtPort.setToolTipText("");
		txtPort.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JButton btnStartclient = new JButton("StartClient");
		btnStartclient.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				TransferMain.startClient();
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(29)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnReceiveFile)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(lblIp)
									.addGap(12)
									.addComponent(txtIP, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(lblPort)
									.addGap(49)
									.addComponent(txtPort, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(14)
							.addComponent(btnStartclient)))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(5)
							.addComponent(lblIp))
						.addComponent(txtIP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(5)
							.addComponent(lblPort))
						.addComponent(txtPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnReceiveFile)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
					.addComponent(btnStartclient))
		);
		
		txtReceived = new JTextArea();
		txtReceived.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		
		txtReceived.setText("The file has not been received completely. Please wait... \n");
		
		scrollPane_1.setViewportView(txtReceived);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblTransition = new JLabel("Transmission");
		lblTransition.setForeground(Color.MAGENTA);
		lblTransition.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(lblTransition);
		btnReceiveFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FileDialog fd = new FileDialog(new MainWindow(),"fileName",FileDialog.SAVE);
				fd.setVisible(true);
				File file = new File(fd.getDirectory(),fd.getFile());				
				TransferMain.receiveData(file, txtIP.getText(), Integer.parseInt(txtPort.getText()));
			}
		});
	}
	
}
