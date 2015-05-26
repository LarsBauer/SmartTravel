package de.hska.eb.smarttravel.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** Diese Klasse bildet ein Weather-Object ab.
 * @author Lars
 * @author Andi
 * @version 0.0.1
 *
 */
public class WeatherObject {

	/**Konstruktor
	 * 
	 */
	public WeatherObject() {
		//super();
		DateFormat dfmtGUI = new SimpleDateFormat( "EEEE', 'dd. MMM");
		Date date = Calendar.getInstance().getTime();
		today = dfmtGUI.format(date);
	}

	private String cityName;
	private String currentTemp;
	private String humidity;
	private String wind;
	private String iconCode;
	private String today;
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCurrentTemp() {
		return currentTemp;
	}

	public void setCurrentTemp(String currentTemp) {
		this.currentTemp = currentTemp;
	}

	public String getIconCode() {
		return iconCode;
	}

	public void setIconCode(String iconCode) {
		this.iconCode = iconCode;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}
	
	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}	
}
