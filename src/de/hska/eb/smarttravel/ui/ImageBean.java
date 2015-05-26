package de.hska.eb.smarttravel.ui;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import de.hska.eb.smarttravel.model.Image;
import de.hska.eb.smarttravel.service.ImageService;
import de.hska.eb.smarttravel.util.ImageHelper;
import de.hska.eb.smarttravel.util.YourHandsUpInTheAirException;

@ManagedBean
@SessionScoped
public class ImageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public ImageBean() {
		super();
	}

	@EJB
	ImageService is;

	@EJB
	ImageHelper helper;

	@ManagedProperty("#{userBean}")
	UserBean userBean;

	private Image curImage;
	private String comment;
	private Integer rating;

	/**
	 * Methode, die im Anschluss an den Konstruktor aufgerufen wird.
	 * 
	 */
	@PostConstruct
	public void onPostConstruct() {
		// this.rating = curImage.getAvgRating();
	}/* onPostConstruct */

	/**
	 * Methode zum Darsetellen eines Bildes
	 * 
	 * @return String fuer die Navigation
	 */
	public void displayImage() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String filename = params.get("filename");
		curImage = is.findImageByFilename(filename);
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/SmartTravelWS/faces/image.xhtml");
		} catch (IOException e) {
			System.out.println(e);
		}
	}/* displayImage */

	/**
	 * Fuegt ein Kommentar zu einem Image hinzu. Wenn die Laenge des Kommentares
	 * 0 ist (TextArea ist leer), wird eine Fehlermeldung an den Benutzer
	 * weitergegeben.
	 * 
	 */
	public void addComment() {
		if (comment.length() == 0) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Fehler",
							"Dein Kommentar ist leer."));
		} else {
			this.curImage = is.submitComment(userBean.getCurrentUser(),
					curImage, comment);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg!",
							"Danke für deinen Kommentar."));
		}
		this.comment = "";
	} /* addComment */

	/**
	 * Uerberwacht anderungen an der Dropdown-Liste der Ratings.
	 * 
	 * @param event
	 *            Das Event, welches durch eine Aenderung ausgeloest wird.
	 */
	public void ratingChangeListener(ValueChangeEvent event) {
		if (event.getNewValue() != null) {
			Integer rating = (Integer) event.getNewValue();
			try {
				this.curImage = is.rateImage(userBean.getCurrentUser(), rating,
						curImage);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, rating
								+ " Sterne!", "Danke für dein Voting!"));
			} catch (YourHandsUpInTheAirException e) {
				System.out.println(e);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Voting Fehler", "Du hast bereits gevoted!"));
			}
		} else {
			return;
		}
	}/* ratingChangeListener */

	/**
	 * Loescht das aktuelle Bild aus der Datenbank.
	 * 
	 */
	public void deleteImage() {
		try {
			System.out.println("deleteImage");
			is.deleteImage(curImage.getId());
			curImage = null;
			System.out.println("redirect");
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/SmartTravelWS/faces/map.xhtml");
		} catch (Exception e) {
			System.out.println(e);
		}
	} /* deleteImage */

	public Image getCurImage() {
		return curImage;
	}

	public void setCurImage(Image curImage) {
		this.curImage = curImage;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getRating() {
		this.rating = curImage.getAvgRating().intValue();
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
}
