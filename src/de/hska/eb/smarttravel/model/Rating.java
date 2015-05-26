package de.hska.eb.smarttravel.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Rating implements Serializable {

	private static final long serialVersionUID = 1L;

	public Rating() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RATING_ID_SEQ")
	@SequenceGenerator(name="RATING_ID_SEQ", sequenceName="RATING_ID_SEQ", allocationSize=5)
	private Integer id;
	
	private Integer score;
	
	@ManyToOne
	@JoinColumn(name="USER_FK")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="IMAGE_FK")
	private Image image;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
