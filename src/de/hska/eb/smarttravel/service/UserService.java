package de.hska.eb.smarttravel.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.hska.eb.smarttravel.model.User;
import de.hska.eb.smarttravel.util.Util;

@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class UserService {

	@PersistenceContext(unitName = "SmartTravelPU")
	private EntityManager em;

	/**
	 * Diese Methode dient dazu, einen Benutzer anhand seines Benutzernamens zu
	 * finden.
	 * 
	 * @param username
	 *            Der Benutzername als String.
	 * @return Das gesuchte User-Object.
	 */
	public User findUserByUsername(String username) {
		User user = null;

		try {
			user = em.createNamedQuery(Util.FIND_USER_BY_USERNAME, User.class)
					.setParameter("username", username).getSingleResult();
		} catch (Exception e) {
			System.out.println(e);
			user = null;
		}

		return user;
	} /* findUserByUsername */

	/**
	 * Legt einen neuen User in der Datenbank an.
	 * 
	 * @param username
	 *            Der Benutername als String.
	 * @param password
	 *            Das Passwort als String.
	 * @return Das neu erzeugte User-Objekt.
	 */
	public User createUser(String username, String password) {
		User user = new User();

		// does the user already exist?
		User tmp = findUserByUsername(username);

		if (tmp == null) {

			user = new User();
			user.setUsername(username);
			user.setPassword(password);

			em.persist(user);

		} else {
			user = null;
		}

		return user;
	} /* createUser */

}
