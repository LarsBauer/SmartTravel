package de.hska.eb.smarttravel.ui;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import de.hska.eb.smarttravel.model.Image;
import de.hska.eb.smarttravel.model.User;
import de.hska.eb.smarttravel.service.ImageService;
import de.hska.eb.smarttravel.service.UserService;
import de.hska.eb.smarttravel.util.ImageHelper;

/**
 * Diese Klasse bildet die Schnittstelle zwischen Anwendungslogik und UI ab.
 * 
 * @author Andi
 * 
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Standardkonstruktor
	 * 
	 */
	public UserBean() {
		super();
	}

	@EJB
	UserService us;

	@EJB
	ImageService is;
	
	@EJB
	ImageHelper helper;

	@ManagedProperty(value = "#{mapBean}")
	MapBean mapBean;

	private String username;
	private String password;
	private String passwordWdh;
	private User currentUser;
	private Boolean loggedIn = false;
	private UploadedFile uploadedImage;

	/**
	 * Diese Methode realisiert den Login eines Benutzers.
	 * 
	 */
	public void login() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.currentUser = us.findUserByUsername(this.username);
		} catch(Exception e) {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Login Fehler",
					"Bitte versuche es erneut"));
		}

		if (currentUser != null) {
			if (currentUser.getPassword().equals(this.password)) {
				this.loggedIn = true;
				for(Image img : currentUser.getImages()) {
					helper.store(img);
				}
				try {
					context.getExternalContext().redirect(
							"/SmartTravelWS/faces/start.xhtml");
				} catch (IOException e) {
					System.out.println(e);
				}
			} else {
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Login Fehler",
						"Falsches Passwort."));
				FacesContext.getCurrentInstance().getExternalContext()
						.invalidateSession();
			}

		} else {

			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Login Fehler",
					"Falscher Username."));
			FacesContext.getCurrentInstance().getExternalContext()
					.invalidateSession();
		}
	} /* Login */

	/**
	 * Diese Methode realisiert den Logout eines Benutzers.
	 * 
	 */
	public void logout() {
		// this.loggedIn = false;
		// this.username = null;
		// this.password = null;
		//
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/SmartTravelWS/faces/index.xhtml");
		} catch (IOException e) {
			System.out.println(e);
		}
	} /* Logout */

	/**
	 * Methode zum registrieren eines neuen Benutzers. Fehlermeldung an den
	 * Benutzer, wenn Benutzer bereits existiert oder wenn Passwortwiederholung
	 * falsch ist.
	 * 
	 */
	public void register() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (this.password.equals(this.passwordWdh)) {

			try {
				this.currentUser = us.createUser(username, password);
			} catch (Exception e) {
				System.out.println("");
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Registrierungs Fehler",
						"Bitte versuche es später erneut."));
			}

			if (currentUser != null) {
				this.loggedIn = true;
				try {
					context.getExternalContext().redirect(
							"/SmartTravelWS/faces/start.xhtml");
				} catch (IOException e) {
					System.out.println(e);
				}
			} else {
				context.addMessage(null, new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Registrierungs Fehler",
						"Username existiert bereits."));
			}
		} else {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Registrierungs Fehler",
					"Passwort und Passwort Wiederholung sind nicht identisch."));
		}
	}/* register */

	/**
	 * Hochladen eines Bildes
	 * 
	 */
	public void imageUpload() {
		if (uploadedImage != null) {
			FacesMessage message = new FacesMessage("Erfolg!",
					uploadedImage.getFileName() + " erfolgreich hochgeladen.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		try {
			currentUser = is.setImage(currentUser, uploadedImage,
					mapBean.getLatitude(), mapBean.getLongitude());
		} catch (IOException ex) {
			System.out.println(ex);
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Fehler!",
					uploadedImage.getFileName()
							+ "konnte nicht verarbeitet werden.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	} /* imageUpload */

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public UploadedFile getUploadedImage() {
		return uploadedImage;
	}

	public void setUploadedImage(UploadedFile image) {
		this.uploadedImage = image;
	}

	public void setMapBean(MapBean mapBean) {
		this.mapBean = mapBean;
	}

	public String getPasswordWdh() {
		return passwordWdh;
	}

	public void setPasswordWdh(String passwordWdh) {
		this.passwordWdh = passwordWdh;
	}
}
