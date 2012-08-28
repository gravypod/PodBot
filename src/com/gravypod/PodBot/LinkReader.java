package com.gravypod.PodBot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;

/**
 * 
 * Link reading class to find out the '<Title>' of a url
 * 
 * @author boom27777777
 *
 */
public class LinkReader {

	public static String html;
	public static String ytOut;

	public static String webRead(String Url) {
		
		try {
		
			URL linkURL = new URL(Url);
			URLConnection urlConnection = linkURL.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			Tidy tidy = new Tidy();
			tidy.setShowErrors(0);
			tidy.setShowWarnings(false);
			tidy.setQuiet(true);
			Document doc = tidy.parseDOM(in, null);
			String titleText = doc.getElementsByTagName("title").item(0).getFirstChild().getNodeValue();
			html = titleText;
			ytOut = null;
			
			if (html != null) {

				ytOut = "\"" + html + "\"";
		
			}
			
			in.close();

		} catch (Exception e){
		}
		
		return ytOut;
		
	}

	public static String getHtml() {
		return html;
	}

	public static void setHtml(String string) {
		html = string;
	}
}
