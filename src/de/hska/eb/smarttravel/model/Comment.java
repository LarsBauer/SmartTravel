package de.hska.eb.smarttravel.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/** Diese Klasse bildet ein Kommentar-Objekt ab.
 * @author Lars
 *
 */
@Entity
@Table(name="COMMENT_TABLE")
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**Konstruktor
	 * 
	 */
	public Comment() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COMMENT_ID_SEQ")
	@SequenceGenerator(name="COMMENT_ID_SEQ", sequenceName="COMMENT_ID_SEQ", allocationSize=5)
	private Integer id;
	
	private String content;
	
	@ManyToOne
	@JoinColumn(name="IMAGE_FK")
	private Image image;
	
	@ManyToOne
	@JoinColumn(name="USER_FK")
	private User user;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
	@Transient
	private String timeString;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getTimeString() {
		DateFormat formatter = new SimpleDateFormat("dd.MM.yy HH:mm");
		return formatter.format(timestamp);
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}
}
