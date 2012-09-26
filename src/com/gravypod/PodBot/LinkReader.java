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

	public String html;
	public String ytOut;
	public URL linkURL;
	URLConnection urlConnection;
	BufferedReader in;
	Tidy tidy = new Tidy();
	Document doc;
	
	public String webRead(String Url) {

		try {

			linkURL = new URL(Url);
			urlConnection = linkURL.openConnection();
			in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			tidy.setShowErrors(0);
			tidy.setShowWarnings(false);
			tidy.setQuiet(true);
			doc = tidy.parseDOM(in, null);
			String titleText = doc.getElementsByTagName("title").item(0).getFirstChild().getNodeValue();
			html = titleText;
			ytOut = null;

			if (html != null) {

				ytOut = "\"" + html + "\"";

			}

			in.close();

		} catch (Exception e) {
		}
		
		linkURL = null;
		urlConnection = null;
		in = null;
		html = null;
		doc = null;

		return ytOut;

	}
	
}
