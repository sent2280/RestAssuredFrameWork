package reports;

public class StyleSheet {
	
	public String getStyleTemplate() {
	
		String styleTemplate = "<style>"
				+ "table { border-collapse: collapse } "
				+ "th {background-color: grey; color: black} "
				+ "body {background-color: #fefbd8 } "
				+ "p.headerRow { background-color: lightblue} "
			//	+ "p.passRow { background-color: lightgreen } "
			//	+ "p.failRow {background-color: lightcoral}"
				+ "</style>";
		return styleTemplate;
	}

}
