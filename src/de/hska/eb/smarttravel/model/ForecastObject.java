package de.hska.eb.smarttravel.model;

/** Diese Klasse bildet ein Forecast-Object ab.
 * @author Lars
 * @author Andi
 * @version 0.0.1
 *
 */
public class ForecastObject {

	/**Konstruktor
	 * 
	 */
	public ForecastObject() {
		super();
	}

	private String date;
	private String dayTemp;
	private String minTemp;
	private String maxTemp;
	private String iconCode;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDayTemp() {
		return dayTemp;
	}

	public void setDayTemp(String dayTemp) {
		this.dayTemp = dayTemp;
	}

	public String getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(String minTemp) {
		this.minTemp = minTemp;
	}

	public String getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(String maxTemp) {
		this.maxTemp = maxTemp;
	}

	public String getIconCode() {
		return iconCode;
	}

	public void setIconCode(String iconCode) {
		this.iconCode = iconCode;
	}

	@Override
	public String toString() {
		return "ForecastObject [date=" + date + ", dayTemp=" + dayTemp
				+ ", minTemp=" + minTemp + ", maxTemp=" + maxTemp
				+ ", iconCode=" + iconCode + "]";
	}
}
