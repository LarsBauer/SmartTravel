package de.hska.eb.smarttravel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import de.hska.eb.smarttravel.util.Util;

/** Diese Klasse bildet ein User-Object ab.
 * @author Andi
 * @author Lars
 *
 */
@Entity
@Table(name="USER_TABLE")
@NamedQueries({
	@NamedQuery(name=Util.FIND_USER_BY_USERNAME,
				query="SELECT u FROM User u WHERE u.username = :username")
	})
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	/** Standardkonstruktor
	 * 
	 */
	public User() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_ID_SEQ")
	@SequenceGenerator(name="USER_ID_SEQ", sequenceName="USER_ID_SEQ", allocationSize=5)
	private Integer id;
	
	@Column(unique=true)
	private String username;
	
	private String password;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="user", cascade=CascadeType.ALL)
	private List<Image> images = new ArrayList<Image>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + "]";
	}
	
}
