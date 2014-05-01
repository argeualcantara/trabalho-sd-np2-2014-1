package interfaces;

import java.io.IOException;

public interface Connection {
	/**
	 * 
	 * @param message: Mensagem a ser enviada
	 * @param ip: endere�o IP da origem
	 * @return
	 */
	public String send(String message, String ip, int port);
	
	/**
	 * Espera uma resposta do servidor e geralmente � usado ap�s o send.
	 * @return A mensagem recebida
	 */
	public String receive(String ip);
	
	/**
	 * Aceita uma conex�o do lado do servidor e deve ser usado ap�s o m�todo startServer
	 * @return O endere�o de IP da origem.
	 * @throws IOException
	 */
	public Conn acceptClient() throws IOException;
	
	/**
	 * Encerra a conex�o.
	 */
	public void close();
}
