package de.hska.eb.smarttravel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import de.hska.eb.smarttravel.util.Util;

/**
 * Diese Klasse bildet ein Image-Object ab.
 * 
 * @author Andi
 * @author Lars
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Util.FIND_ALL_IMAGES, query = "SELECT i FROM Image i"),
		@NamedQuery(name = Util.FIND_IMAGE_BY_FILENAME, query = "SELECT i FROM Image i WHERE i.filename = :filename") })
public class Image implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Standardkonstruktor
	 * 
	 */
	public Image() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGE_ID_SEQ")
	@SequenceGenerator(name = "IMAGE_ID_SEQ", sequenceName = "IMAGE_ID_SEQ", allocationSize = 5)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "USER_FK", nullable = false)
	private User user;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	@Lob
	private byte[] bytes;

	private String filename;

	private Double latitude;

	private Double longitude;

	private String mimetype;

	@OneToMany(mappedBy = "image", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	//@OrderColumn(name = "TIMESTAMP")
	private List<Comment> comments = new ArrayList<Comment>();

	@OneToMany(mappedBy = "image", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Rating> ratings = new ArrayList<Rating>();

	@Transient
	private Double avgRating;

	@PostConstruct
	public void onPostConstruct() {
		this.time = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public Double getAvgRating() {
		if (ratings.isEmpty()) {
			return 0.0;
		} else {
			Double sum = 0.0;
			for (int i = 0; i < this.ratings.size(); i++) {
				sum += ratings.get(i).getScore();
			}
			return (sum / ratings.size());
		}
	}

	public void setAvgRating(Double avgRating) {
		this.avgRating = avgRating;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}
}
