package business;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

import interfaces.Connector;

public class ConnectorUDP implements Connector {

	private DatagramSocket ds = null;
	private DatagramSocket ss = null;
	private InetAddress client = null;

	@Override
	public void connect(String ip, int port) {
		// TODO Auto-generated method stub
		try {
			ds = new DatagramSocket();
			ds.connect(new InetSocketAddress(InetAddress.getLocalHost(), port));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String send(String message) {
		String retorno = "S";
		DatagramPacket pacote;
		try {
			pacote = new DatagramPacket(message.getBytes(), message.length(), ds.getInetAddress(), ds.getPort());
			ds.send(pacote);
		} catch (IOException e) {
			e.printStackTrace();
			retorno = "N";
		}
		return retorno;
	}

	@Override
	public String receive() {

		String str = "";

		byte[] buffer = new byte[1000];
		DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);
		try {
			if (ds != null) {
				ds.receive(pacote);
			} else if (ss != null) {
				ss.receive(pacote);
			}
			client = pacote.getAddress();
			str = new String(pacote.getData(), 0, pacote.getLength());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return str;
	}

	@Override
	public void close() {
		if (ds != null) {
			ds.close();
		}
	}

	@Override
	public void startServer(int port) {
		try {
			ss = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String acceptClient() throws IOException {
		// accept bloqueia enquanto n�o receber uma conex�o
		while (client == null) {
			receive();
		}
		return client.getHostAddress();

	}

}
