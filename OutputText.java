import java.io.File;

public class OutputText extends Thread {
	private MainWindow frame;
	private File file;

	public OutputText(MainWindow frame1, File f) {
		this.frame = frame1;
		this.file = f;
	}

	public void run() {
		int i;
		while (true) {
			i = (int) this.file.length();
			this.frame.txtReceived.append("" + (int) this.file.length() + "\n");
			try {
				Thread.currentThread();
				Thread.sleep(100);
				i = (int) this.file.length();
			} catch (InterruptedException ex) {

			}
			if (i == (int) this.file.length())
				break;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
