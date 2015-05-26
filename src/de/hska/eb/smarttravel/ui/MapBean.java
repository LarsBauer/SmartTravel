package de.hska.eb.smarttravel.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import de.hska.eb.smarttravel.model.Image;
import de.hska.eb.smarttravel.service.ImageService;
import de.hska.eb.smarttravel.util.ImageHelper;
import de.hska.eb.smarttravel.util.Util;

/**
 * Bean-Klasse fuer die Kartenanzeige.
 * 
 * @author Lars
 * 
 */
@ManagedBean
@SessionScoped
public class MapBean implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor
	 * 
	 */
	public MapBean() {
		super();
	}

	@EJB
	ImageService is;

	@EJB
	ImageHelper helper;

	private double latitude;
	private double longitude;
	private int homeZoom = 15;
	private int bigZoom = 15;
	private MapModel homeMap = new DefaultMapModel();
	private MapModel bigMap = new DefaultMapModel();
	private Marker selectedMarker;
	private List<Image> images = new ArrayList<Image>();

	/**
	 * Diese Methode wird nach dem Konstruktor gerufen.
	 * 
	 */
	@PostConstruct
	public void onPostContruct() {
		// this.images = is.findAllImages();
	}/* onPostContruct */

	/**
	 * Init-Methode fuer die Position
	 * 
	 */
	public void initPosition() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		latitude = (double) Float.parseFloat(params.get("latitude"));
		longitude = (double) Float.parseFloat(params.get("longitude"));

		if (Util.DEBUG_MODE) {
			System.out.println("MapBean: " + latitude + "," + longitude);
		}

		// this.images = is.findAllImages();
		displayStartPosition();
		// displayMainPosition();
	}/* initPosition */

	/**
	 * Startposition anzeigen
	 * 
	 */
	public void displayStartPosition() {
		if (Util.DEBUG_MODE) {
			System.out.println("MapBean displayStartPosition()");
		}
		homeMap = new DefaultMapModel();
		homeZoom = 17;
		homeMap.addOverlay(new Marker(new LatLng(latitude, longitude),
				"You are here!"));
	}/* displayStartPosition */

	/**
	 * Main-Position anzeigen
	 * 
	 */
	public void displayMainPosition() {
		if (Util.DEBUG_MODE) {
			System.out.println("MapBean displayMainPosition()");
		}
		bigMap = new DefaultMapModel();
		bigZoom = 17;
		bigMap.addOverlay(new Marker(new LatLng(latitude, longitude),
				"You are here!"));

		this.images = is.findAllImages();

		for (Image image : images) {
			if (image.getLatitude() != null
					&& image.getLongitude() != null
					&& distance(latitude, longitude, image.getLatitude(),
							image.getLongitude(), 'M') <= 500.0) {
				helper.store(image);
				Marker marker = new Marker(new LatLng(image.getLatitude(),
						image.getLongitude()), image.getUser().getUsername(),
						image.getFilename());
				bigMap.addOverlay(marker);
			}
		}
	} /* displayMainPosition */

	/**
	 * Was passieren soll, wenn der Marker auf der Karte ausgewaehlt wird.
	 * 
	 * @param event
	 */
	public void onMarkerSelect(OverlaySelectEvent event) {
		selectedMarker = (Marker) event.getOverlay();
	}/* onMarkerSelect */

	/**
	 * Berechnet die Distanz zwischen zwei Koordinaten
	 * 
	 * @param lat1
	 *            Breitengrad Koordinate 1
	 * @param lon1
	 *            Laengengrad Koordinate 1
	 * @param lat2
	 *            Breitegrad Koordinate 2
	 * @param lon2
	 *            Laengengrad Koordinate 2
	 * @param unit
	 *            Einheit der Angaben
	 * @return Die Distanz zwischen beiden Koordinaten
	 */
	private double distance(double lat1, double lon1, double lat2, double lon2,
			char unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
				* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == 'K') {
			dist = dist * 1.609344;
		} else if (unit == 'N') {
			dist = dist * 0.8684;
		}
		return (dist);
	} /* distance */

	/**
	 * Konvertiert Grad zu Rad
	 * 
	 * @param deg
	 *            Grad-Angabe
	 * @return Rad-Wert
	 */
	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	} /* deg2rad */

	/**
	 * Konvertiert Rad zu Grad
	 * 
	 * @param rad
	 *            Rad-Angabe
	 * @return Grad-Wert
	 */
	private double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}/* rad2deg */

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getHomeZoom() {
		return homeZoom;
	}

	public void setHomeZoom(int homeZoom) {
		this.homeZoom = homeZoom;
	}

	public int getBigZoom() {
		return bigZoom;
	}

	public void setBigZoom(int bigZoom) {
		this.bigZoom = bigZoom;
	}

	public MapModel getHomeMap() {
		return homeMap;
	}

	public void setHomeMap(MapModel homeMap) {
		this.homeMap = homeMap;
	}

	public MapModel getBigMap() {
		return bigMap;
	}

	public void setBigMap(MapModel bigMap) {
		this.bigMap = bigMap;
	}

	public Marker getSelectedMarker() {
		return selectedMarker;
	}

	public void setSelectedMarker(Marker selectedMarker) {
		this.selectedMarker = selectedMarker;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
}
