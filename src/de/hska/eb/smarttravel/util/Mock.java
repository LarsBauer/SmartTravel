package de.hska.eb.smarttravel.util;

/**
 * Diese Klasse beinhaltet verschiedene XML-Strings, die zum Testen dienen.
 * 
 * @author Andi
 * @version 0.0.1
 * 
 */
public class Mock {

	// Weather
	public static final String WEATHER_XML = "<current>\r\n"
			+ "	<city id=\"2892794\" name=\"Karlsruhe\">\r\n"
			+ "		<coord lon=\"8.39\" lat=\"49.02\"/>\r\n"
			+ "		<country>DE</country>\r\n"
			+ "		<sun rise=\"2014-05-05T03:57:52\" set=\"2014-05-05T18:48:19\"/>\r\n"
			+ "	</city>\r\n"
			+ "	<temperature value=\"12.28\" min=\"12\" max=\"12.78\" unit=\"celsius\"/>\r\n"
			+ "	<humidity value=\"60\" unit=\"%\"/>\r\n"
			+ "	<pressure value=\"1014\" unit=\"hPa\"/>\r\n"
			+ "	<wind>\r\n"
			+ "		<speed value=\"1.54\" name=\"\"/>\r\n"
			+ "		<direction value=\"70\" code=\"ENE\" name=\"East-northeast\"/>\r\n"
			+ "	</wind>\r\n"
			+ "	<clouds value=\"0\" name=\"sky is clear\"/>\r\n"
			+ "	<precipitation mode=\"no\"/>\r\n"
			+ "	<weather number=\"800\" value=\"Sky is Clear\" icon=\"01d\"/>\r\n"
			+ "	<lastupdate value=\"2014-05-05T08:45:14\"/>\r\n" + "</current>";

	// Forecast
	public static final String FORECAST_XML = "<weatherdata>\r\n"
			+ "<location>\r\n"
			+ "<name>London</name>\r\n"
			+ "<type/>\r\n"
			+ "<country>CA</country>\r\n"
			+ "<timezone/>\r\n"
			+ "<location altitude=\"0\" latitude=\"42.983391\" longitude=\"-81.23304\" geobase=\"geonames\" geobaseid=\"0\"/>\r\n"
			+ "</location>\r\n"
			+ "<credit/>\r\n"
			+ "<meta>\r\n"
			+ "<lastupdate/>\r\n"
			+ "<calctime>0.0336</calctime>\r\n"
			+ "<nextupdate/>\r\n"
			+ "</meta>\r\n"
			+ "<sun rise=\"2014-05-06T10:11:05\" set=\"2014-05-07T00:31:55\"/>\r\n"
			+ "<forecast>\r\n"
			+ "<time day=\"2014-05-05\">\r\n"
			+ "<symbol number=\"800\" name=\"sky is clear\" var=\"01n\"/>\r\n"
			+ "<precipitation/>\r\n"
			+ "<windDirection deg=\"324\" code=\"NW\" name=\"Northwest\"/>\r\n"
			+ "<windSpeed mps=\"3.32\" name=\"\"/>\r\n"
			+ "<temperature day=\"5.66\" min=\"2.19\" max=\"5.66\" night=\"2.19\" eve=\"5.66\" morn=\"5.66\"/>\r\n"
			+ "<pressure unit=\"hPa\" value=\"993.01\"/>\r\n"
			+ "<humidity value=\"73\" unit=\"%\"/>\r\n"
			+ "<clouds value=\"sky is clear\" all=\"0\" unit=\"%\"/>\r\n"
			+ "</time>\r\n"
			+ "<time day=\"2014-05-06\">\r\n"
			+ "<symbol number=\"800\" name=\"sky is clear\" var=\"02d\"/>\r\n"
			+ "<precipitation/>\r\n"
			+ "<windDirection deg=\"51\" code=\"NE\" name=\"NorthEast\"/>\r\n"
			+ "<windSpeed mps=\"2.76\" name=\"Light breeze\"/>\r\n"
			+ "<temperature day=\"14.02\" min=\"3.89\" max=\"14.68\" night=\"6.09\" eve=\"12.73\" morn=\"3.89\"/>\r\n"
			+ "<pressure unit=\"hPa\" value=\"995.44\"/>\r\n"
			+ "<humidity value=\"82\" unit=\"%\"/>\r\n"
			+ "<clouds value=\"sky is clear\" all=\"8\" unit=\"%\"/>\r\n"
			+ "</time>\r\n"
			+ "<time day=\"2014-05-07\">\r\n"
			+ "<symbol number=\"501\" name=\"moderate rain\" var=\"10d\"/>\r\n"
			+ "<precipitation value=\"8\" type=\"rain\"/>\r\n"
			+ "<windDirection deg=\"125\" code=\"SE\" name=\"SouthEast\"/>\r\n"
			+ "<windSpeed mps=\"6.71\" name=\"Moderate breeze\"/>\r\n"
			+ "<temperature day=\"12.83\" min=\"6.61\" max=\"14.05\" night=\"8.22\" eve=\"12.22\" morn=\"6.61\"/>\r\n"
			+ "<pressure unit=\"hPa\" value=\"994.98\"/>\r\n"
			+ "<humidity value=\"63\" unit=\"%\"/>\r\n"
			+ "<clouds value=\"overcast clouds\" all=\"92\" unit=\"%\"/>\r\n"
			+ "</time>\r\n"
			+ "<time day=\"2014-05-08\">\r\n"
			+ "<symbol number=\"803\" name=\"broken clouds\" var=\"04d\"/>\r\n"
			+ "<precipitation/>\r\n"
			+ "<windDirection deg=\"142\" code=\"SE\" name=\"SouthEast\"/>\r\n"
			+ "<windSpeed mps=\"5.02\" name=\"Gentle Breeze\"/>\r\n"
			+ "<temperature day=\"18.85\" min=\"9.54\" max=\"21.21\" night=\"15.02\" eve=\"19.63\" morn=\"9.54\"/>\r\n"
			+ "<pressure unit=\"hPa\" value=\"992.44\"/>\r\n"
			+ "<humidity value=\"97\" unit=\"%\"/>\r\n"
			+ "<clouds value=\"broken clouds\" all=\"64\" unit=\"%\"/>\r\n"
			+ "</time>\r\n"
			+ "<time day=\"2014-05-09\">\r\n"
			+ "<symbol number=\"502\" name=\"heavy intensity rain\" var=\"10d\"/>\r\n"
			+ "<precipitation value=\"16\" type=\"rain\"/>\r\n"
			+ "<windDirection deg=\"209\" code=\"SSW\" name=\"South-southwest\"/>\r\n"
			+ "<windSpeed mps=\"9.26\" name=\"Fresh Breeze\"/>\r\n"
			+ "<temperature day=\"20.67\" min=\"15.5\" max=\"20.73\" night=\"15.5\" eve=\"16.51\" morn=\"17.27\"/>\r\n"
			+ "<pressure unit=\"hPa\" value=\"985.94\"/>\r\n"
			+ "<humidity value=\"75\" unit=\"%\"/>\r\n"
			+ "<clouds value=\"overcast clouds\" all=\"92\" unit=\"%\"/>\r\n"
			+ "</time>\r\n"
			+ "<time day=\"2014-05-10\">\r\n"
			+ "<symbol number=\"500\" name=\"light rain\" var=\"10d\"/>\r\n"
			+ "<precipitation/>\r\n"
			+ "<windDirection deg=\"261\" code=\"W\" name=\"West\"/>\r\n"
			+ "<windSpeed mps=\"6.9\" name=\"Moderate breeze\"/>\r\n"
			+ "<temperature day=\"17.09\" min=\"7.66\" max=\"17.09\" night=\"7.66\" eve=\"14.13\" morn=\"14.45\"/>\r\n"
			+ "<pressure unit=\"hPa\" value=\"999.13\"/>\r\n"
			+ "<humidity value=\"0\" unit=\"%\"/>\r\n"
			+ "<clouds value=\"sky is clear\" all=\"0\" unit=\"%\"/>\r\n"
			+ "</time>\r\n"
			+ "<time day=\"2014-05-11\">\r\n"
			+ "<symbol number=\"502\" name=\"heavy intensity rain\" var=\"10d\"/>\r\n"
			+ "<precipitation value=\"15.44\" type=\"rain\"/>\r\n"
			+ "<windDirection deg=\"71\" code=\"ENE\" name=\"East-northeast\"/>\r\n"
			+ "<windSpeed mps=\"7.53\" name=\"Moderate breeze\"/>\r\n"
			+ "<temperature day=\"12.18\" min=\"8.41\" max=\"12.18\" night=\"8.41\" eve=\"9.96\" morn=\"9.15\"/>\r\n"
			+ "<pressure unit=\"hPa\" value=\"998.67\"/>\r\n"
			+ "<humidity value=\"0\" unit=\"%\"/>\r\n"
			+ "<clouds value=\"overcast clouds\" all=\"98\" unit=\"%\"/>\r\n"
			+ "</time>\r\n" + "</forecast>\r\n" + "</weatherdata>";
}
