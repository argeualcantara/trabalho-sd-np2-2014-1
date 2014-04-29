package interfaces;

import java.io.IOException;

public interface Connector {

	/**
	 * Primeiro m�todo a ser iniciado do lado do cliente.
	 * @param ip - IP para conex�o
	 * @param port - Porta para conex�o
	 */
	public Connection connect(String ip, int port);
	
	
	/**
	 * Primeiro m�todo a ser iniciado para o lado do servidor.
	 * @param port
	 * @return
	 * @throws IOException
	 */
	public Connection startServer(int port) throws IOException;
	
	
}
