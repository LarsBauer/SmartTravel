package de.hska.eb.smarttravel.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import de.hska.eb.smarttravel.model.ForecastObject;
import de.hska.eb.smarttravel.model.WeatherObject;
import de.hska.eb.smarttravel.util.ProxyAuthenticator;
import de.hska.eb.smarttravel.util.Util;

/**
 * Diese Klasse beinhaltet die Anwendungslogik fuer den WeatherService.
 * 
 * @author Lars
 * @author Andi
 * @version 0.0.1
 * 
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class WeatherService {

	/**
	 * Ruft die aktuellen Wetterdaten zu best. Koordinaten ab.
	 * 
	 * @param latitude
	 *            Breitengrad
	 * @param longitude
	 *            Laengengrad
	 * @return Wetterdaten als XML-String
	 */
	public String fetchWeatherByCoordinates(double latitude, double longitude) {

		try {
			if (Util.PROXY_MODE) {
				String user = "bala1014";
				String password = "4uhP8k4F";
				Authenticator
						.setDefault(new ProxyAuthenticator(user, password));
				System.setProperty("http.proxyHost", "proxy.hs-karlsruhe.de");
				System.setProperty("http.proxyPort", "8888");
				System.setProperty("http.proxyUserName", user);
				System.setProperty("http.proxyPassword", password);
			}

			URL url = new URL(
					"HTTP",
					"api.openweathermap.org",
					"/data/2.5/weather?lat="
							+ latitude
							+ "&lon="
							+ longitude
							+ "&units=metric&mode=xml&APPID=3c7aac006bd9061b0169ba66cdbdc157");
			System.out.println(url);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			String inputLine;

			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			return null;
		}
	} /* fetchWeatherByCoordinates */

	/**
	 * Ueberfuehrt den XML-String mit den Wetterdaten in ein Weather-Objekt.
	 * 
	 * @param xml
	 *            XML-String mit den Wetterdaten
	 * @return gesparstes Wetter-Objekt
	 */
	public WeatherObject parseWeatherObject(String xml) {
		WeatherObject weather = new WeatherObject();
		try {
			// Document
			SAXBuilder buildr = new SAXBuilder();
			InputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
			Document doc = buildr.build(is);

			// Document doc = buildr.build(new StringReader(xml));
			// Root-Element
			Element root = doc.getRootElement();
			// Weather-Object fuellen
			weather.setCityName(root.getChild("city").getAttributeValue("name"));
			String curTemp = root.getChild("temperature").getAttributeValue(
					"value");
			if (curTemp.contains(".")) {
				weather.setCurrentTemp(curTemp.split("\\.")[0]);
			} else {
				weather.setCurrentTemp(curTemp);
			}
			weather.setHumidity(root.getChild("humidity").getAttributeValue(
					"value"));
			weather.setWind(root.getChild("wind").getChild("speed")
					.getAttributeValue("value"));
			weather.setIconCode(root.getChild("weather").getAttributeValue(
					"icon"));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return weather;
	}/* parseWeatherObject */

	/**
	 * Ueberfuehrt den XML-String mit den Vorhersagedaten in eine Liste von
	 * Forecast-Objekten.
	 * 
	 * @param xml
	 *            XML-String mit den Vorhersage-Daten
	 * @return Liste mit geparsten Vorhersage-Objekten
	 */
	public List<ForecastObject> parseForecastObject(String xml) {
		List<ForecastObject> forecasts = new ArrayList<ForecastObject>();
		try {
			// Document
			SAXBuilder buildr = new SAXBuilder();
			Document doc = buildr.build(new StringReader(xml));
			// Root-Element
			Element root = doc.getRootElement();
			// Liste mit allen time-day Elementen aus der XML
			List<Element> elTimeDay = root.getChild("forecast").getChildren(
					"time");
			// Ueber alle time-Elemente iterieren
			for (Element element : elTimeDay) {
				ForecastObject cast = new ForecastObject();
				// Datum formatieren
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = formatter.parse(element.getAttributeValue("day"));
				DateFormat dfmtGUI = new SimpleDateFormat("EEE");
				cast.setDate(dfmtGUI.format(date).toString());
				cast.setIconCode(element.getChild("symbol").getAttributeValue(
						"var"));
				Element elTemp = element.getChild("temperature");
				String dayTemp = elTemp.getAttributeValue("day");
				String maxTemp = elTemp.getAttributeValue("max");
				String minTemp = elTemp.getAttributeValue("min");
				if (dayTemp.contains(".")) {
					cast.setDayTemp(dayTemp.split("\\.")[0]);
				} else {
					cast.setDayTemp(dayTemp);
				}
				if (maxTemp.contains(".")) {
					cast.setMaxTemp(maxTemp.split("\\.")[0]);
				} else {
					cast.setMaxTemp(maxTemp);
				}
				if (minTemp.contains(".")) {
					cast.setMinTemp(minTemp.split("\\.")[0]);
				} else {
					cast.setMinTemp(minTemp);
				}
				forecasts.add(cast);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return forecasts;
	}/* parseForecastObject */

	/**
	 * Diese Methode ruft die Wettervorhersage mittels Koordinaten ab.
	 * 
	 * @param latitude
	 *            Breitengrad
	 * @param longitude
	 *            Laengengrad
	 * @return XML-String mit der Wettervorhersage
	 */
	public String fetchForecastByCoordinates(double latitude, double longitude) {

		try {

			if (Util.PROXY_MODE) {
				String user = "bala1014";
				String password = "4uhP8k4F";
				Authenticator
						.setDefault(new ProxyAuthenticator(user, password));
				System.setProperty("http.proxyHost", "proxy.hs-karlsruhe.de");
				System.setProperty("http.proxyPort", "8888");
				System.setProperty("http.proxyUserName", user);
				System.setProperty("http.proxyPassword", password);
			}

			URL url = new URL(
					"HTTP",
					"api.openweathermap.org",
					"/data/2.5/forecast/daily?lat="
							+ latitude
							+ "&lon="
							+ longitude
							+ "&mode=xml&units=metric&cnt=4&APPID=3c7aac006bd9061b0169ba66cdbdc157");
			System.out.println(url);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			return null;
		}
	}/* fetchForecastByCoordinates */
}
