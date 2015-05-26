package de.hska.eb.smarttravel.ui;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import de.hska.eb.smarttravel.model.ForecastObject;
import de.hska.eb.smarttravel.model.WeatherObject;
import de.hska.eb.smarttravel.service.WeatherService;
import de.hska.eb.smarttravel.util.Mock;
import de.hska.eb.smarttravel.util.Util;

/**
 * Bean fuer das Wetter.
 * 
 * @author Lars
 * 
 */
@ManagedBean
@SessionScoped
public class WeatherBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor
	 * 
	 */
	public WeatherBean() {
		super();
	}

	@EJB
	WeatherService ws;

	private WeatherObject weather;
	private List<ForecastObject> forecast;
	private double latitude;
	private double longitude;

	/**
	 * Init-Methode fuer das Wetter
	 * 
	 */
	public void initWeather() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		latitude = (double) Float.parseFloat(params.get("latitude"));
		longitude = (double) Float.parseFloat(params.get("longitude"));

		fetchWeather();
		// fetchForecast();
	}/* initWeather */

	/**
	 * Methode fuer das Abrufen des Wetters
	 * 
	 */
	public void fetchWeather() {
		System.out.println("DEBUG is " + Util.DEBUG_MODE);
		if (Util.DEBUG_MODE) {
			String xml = Mock.WEATHER_XML;
			weather = ws.parseWeatherObject(xml);
		} else {
			System.out.println("WeatherBean fetchWeather()");
			String xml = ws.fetchWeatherByCoordinates(latitude, longitude);
			weather = ws.parseWeatherObject(xml);
		}
	}/* fetchWeather */

	/**
	 * Methode fuer das Abrufen der Wettervorhersage
	 * 
	 */
	public void fetchForecast() {
		System.out.println("DEBUG is " + Util.DEBUG_MODE);
		if (Util.DEBUG_MODE) {
			String xml = Mock.FORECAST_XML;
			forecast = ws.parseForecastObject(xml);
		} else {
			System.out.println("WeatherBean fetchForecast()");
			String xml = ws.fetchForecastByCoordinates(latitude, longitude);
			forecast = ws.parseForecastObject(xml);
		}
	}/* fetchForecast */

	public WeatherObject getWeather() {
		return weather;
	}

	public void setWeather(WeatherObject weather) {
		this.weather = weather;
	}

	public List<ForecastObject> getForecast() {
		return forecast;
	}

	public void setForecast(List<ForecastObject> forecast) {
		this.forecast = forecast;
	}
}
