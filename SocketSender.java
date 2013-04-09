import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketSender {
	public static void main(String[] args) {
		try {
			Socket s = new Socket("localhost", 5555);
			System.out.println("Connection Established");
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter the name of the file: ");
			String name = sc.nextLine();
			File file = new File(name);
			DataInputStream dis = new DataInputStream(new FileInputStream(name));
			byte[] b = new byte[(int) file.length()];
			dis.readFully(b);
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF(name);
			dos.writeLong(file.length());
			dos.write(b);
			sc.close();
			dos.close();
			dis.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
