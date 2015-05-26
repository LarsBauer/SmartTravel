package de.hska.eb.smarttravel.util;

import java.util.List;

import de.hska.eb.smarttravel.model.ForecastObject;
import de.hska.eb.smarttravel.service.WeatherService;
import de.hska.eb.smarttravel.util.Mock;

/**
 * Diese Klasse dient dem Test der implementierten Methoden.
 * 
 * @author Andi
 * @version 0.0.1
 * 
 */
public class Test {

	public static void main(String[] args) {
		WeatherService service = new WeatherService();

		// TEST Weather-Object
		// String weather_xml = service.fetchWeatherByCoordinates(49.0, 8.4);
		// WeatherObject weather =
		// service.parseWeatherObject(mock_xml.weather_xml);
		// System.out.println(weather.toString());

		// TEST Forecast-Object
		// String forecast_xml = service.fetchForecastByCityName("Karlsruhe");
		List<ForecastObject> disislist = service
				.parseForecastObject(Mock.FORECAST_XML);
		System.out.println("Forecast Laenge: " + disislist.size());
		for (ForecastObject forecastObject : disislist) {
			System.out.println(forecastObject.toString());
		}
	}
}