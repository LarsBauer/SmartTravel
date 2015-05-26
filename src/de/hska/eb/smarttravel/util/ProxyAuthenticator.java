package de.hska.eb.smarttravel.util;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * Diese Klasse dient der Authentifizierung gegenueber den HS-Proxy
 * 
 * @author Lars
 * 
 */
public class ProxyAuthenticator extends Authenticator {

	private String user;
	private String password;

	/**
	 * Konstruktor
	 * 
	 * @param user
	 *            Benutzername
	 * @param password
	 *            Passwort
	 */
	public ProxyAuthenticator(String user, String password) {
		this.user = user;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, password.toCharArray());
	}
}
