package de.hska.eb.smarttravel.util;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.hska.eb.smarttravel.model.Image;

/**
 * Diese Klasse stellt Methoden bereit, die dazu dienen, ein Image-Object zu
 * verarbeiten.
 * 
 * @author Andi
 * @author Lars
 * 
 */
@ApplicationScoped
@Stateful
public class ImageHelper {

	private transient Path directory;

	/**
	 * Diese Methode initialisiert ein Image-Helper Object. Dabei wird das
	 * Attribut directory gesetzt.
	 * 
	 */
	@PostConstruct
	private void init() {
		String appName = null;
		Context ctx = null;
		try {
			ctx = new InitialContext();
			appName = (String) ctx.lookup("java:app/AppName");
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			try {
				ctx.close();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		directory = Paths.get(System.getenv("WILDFLY_HOME"), "standalone",
				"deployments", appName + ".war", "resources", "fileDb");
	} /* init */

	/**
	 * Diese Methode speichert ein Image-Object im Filesystem des
	 * Applicationservers.
	 * 
	 * @param image
	 *            Das zu speichernde Image-Object.
	 */
	public void store(Image image) {
		if (image == null) {
			return;
		}
		String filename = image.getFilename();
		Path absoluteFilename = directory.resolve(filename);
		try {
			ByteArrayInputStream is = new ByteArrayInputStream(image.getBytes());
			Files.copy(is, absoluteFilename, REPLACE_EXISTING);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} /* store */
}
