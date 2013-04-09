import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketProg {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(5555);
			System.out.println("Server is loaded..");
			Socket s = ss.accept();
			DataInputStream dis = new DataInputStream(s.getInputStream());
			String filename = dis.readUTF();
			File file = new File(filename);
			System.out.println("Copying of " + filename + " initiated!");
			int size = (int) dis.readLong();
			System.out.println("File size: " + size);
			byte[] b = new byte[size];
			dis.read(b);
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(
					file));
			dos.write(b);
			System.out.println("Copying of " + filename + " finished!");
			dis.close();
			dos.close();
			ss.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
