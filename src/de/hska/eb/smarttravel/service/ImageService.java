package de.hska.eb.smarttravel.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

import de.hska.eb.smarttravel.model.Comment;
import de.hska.eb.smarttravel.model.Image;
import de.hska.eb.smarttravel.model.Rating;
import de.hska.eb.smarttravel.model.User;
import de.hska.eb.smarttravel.util.ImageHelper;
import de.hska.eb.smarttravel.util.Util;
import de.hska.eb.smarttravel.util.YourHandsUpInTheAirException;

/**
 * Diese Klasse stellt Methoden bereit, die dazu dienen, ein Image-Object zu
 * verarbeiten.
 * 
 * @author Andi
 * @author Lars
 * 
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class ImageService {

	@PersistenceContext(unitName = "SmartTravelPU")
	private EntityManager em;

	@EJB
	private ImageHelper helper;

	/**
	 * Diese Methode legt ein neues Image-Object an und fuegt es einem User
	 * hinzu.
	 * 
	 * @param user
	 *            Der Benutzer
	 * @param bytezz
	 *            Das Byte-Array mit den Bilddaten.
	 * @throws IOException
	 */
	public User setImage(User user, UploadedFile uploadedImage,
			double latitude, double longitude) throws IOException {
		Date time = new Date();
		String extension = "." + uploadedImage.getFileName().split("\\.")[1];
		String filename = user.getUsername() + "_" + time.getTime() + extension;
		byte[] bytezzz = IOUtils.toByteArray(uploadedImage.getInputstream());

		Image image = new Image();
		image.setTime(time);
		image.setBytes(bytezzz);
		image.setFilename(filename);
		image.setLatitude(latitude);
		image.setLongitude(longitude);

		user.getImages().add(image);
		image.setUser(user);

		User updatedUser = em.merge(user);

		helper.store(image);

		return updatedUser;

	} /* setImage */

	/**
	 * Findet alle Images in der Datenbank.
	 * 
	 * @return Liste mit allen Image-Objekten in aus der DB.
	 */
	public List<Image> findAllImages() {
		List<Image> res = new ArrayList<Image>();
		res = em.createNamedQuery(Util.FIND_ALL_IMAGES, Image.class)
				.getResultList();
		return res;
	} /* findAllImages */

	/**
	 * Findet ein Image in der DB anhand ihres Dateinamens.
	 * 
	 * @param filename
	 *            Der Dateiname als String
	 * @return Das Image-Objekt aus der Datenbank.
	 */
	public Image findImageByFilename(String filename) {

		Image res = new Image();
		res = em.createNamedQuery(Util.FIND_IMAGE_BY_FILENAME, Image.class)
				.setParameter("filename", filename).getSingleResult();

		return res;
	} /* findImageByFilename */

	/**
	 * Diese Methode fuegt ein Rating zu einem Image hinzu.
	 * 
	 * @param user
	 *            Der Benutzer, der das Rating abgibt.
	 * @param score
	 *            Der Score des Ratings.
	 * @param image
	 *            Das Image-Objekt welches gerated werden soll.
	 * @return Das aktualisierte Image-Object
	 * @throws YourHandsUpInTheAirException
	 */
	public Image rateImage(User user, Integer score, Image image)
			throws YourHandsUpInTheAirException {
		Rating rating = new Rating();
		rating.setImage(image);
		rating.setScore(score);
		rating.setUser(user);

		for (int i = 0; i < image.getRatings().size(); ++i) {
			if (image.getRatings().get(i).getUser().getId() == user.getId()) {
				System.out.println("Schon gevoted!");
				throw new YourHandsUpInTheAirException();
			}
		}

		image.getRatings().add(rating);

		Image updatedImage = em.merge(image);

		return updatedImage;
	} /* rateImage */

	/**
	 * Fuegt einen Kommentar zu einem Image-Objekt hinzu.
	 * 
	 * @param user
	 *            Der User, der den Kommentar abgibt.
	 * @param image
	 *            Das Image, welches kommentiert wird.
	 * @param content
	 *            Der Inhalt des Kommentars als String.
	 * @return Das aktualisierte Image-Objekt.
	 */
	public Image submitComment(User user, Image image, String content) {
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setImage(image);
		comment.setTimestamp(new Date());
		comment.setUser(user);

		image.getComments().add(comment);

		Image updatedImage = em.merge(image);

		return updatedImage;
	} /* submitComment */

	/**
	 * Loescht ein Image-Objekt aus der Datenbank
	 * 
	 * @param image
	 *            Das Image-Objekt welches geloescht werden soll.
	 */
	public void deleteImage(Integer id) {
		System.out.println("is.deleteImage");
		Image img = em.find(Image.class, id);
		if (img == null) {
			return;
		}
        em.remove(img);
		System.out.println("removed?!");
	} /* deleteImage */
}
