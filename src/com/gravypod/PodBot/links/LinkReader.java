package com.gravypod.PodBot.links;

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

	public static String webRead(String Url) {
		
		String ytOut = null;
		
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
			String html = titleText;
			

			if (html != null) {

				ytOut = "\"" + html + "\"";

			}

			in.close();

		} catch (Exception e) {
		}

		return ytOut;

	}

}
