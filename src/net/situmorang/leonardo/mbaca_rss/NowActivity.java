package net.situmorang.leonardo.mbaca_rss;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.R;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class NowActivity extends ListActivity {
	public final String TAG = "NOW.LEONARDO";
	private EditText edit;
	
	private final String[] names = {
			"violet", 
			"green", 
			"blue", 
			"red",
			"purple",
			"cyan",
			"gray"
	};
	
	private List<String> listOfNames;
	private int counter = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		listOfNames = new ArrayList<String>();
		this.initialiseListContent();
		
		edit = (EditText) this.findViewById(R.id.edit);
		
		// TODO: tambahan editview untuk menambah entry ke list
		// TODO: tambahkan textview untuk menampilan selected item dari list
		
		Log.d(TAG, "start_disini");
		// AndroidHttpClient httpClient = AndroidHttpClient.newInstance("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.77 Safari/535.7");
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = "http://leonardo.situmorang.net/feed/";
		HttpGet request = new HttpGet(url);
		
		try {
			HttpResponse response = httpClient.execute(request);
			
			final int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				Log.d(TAG, "Error: " + statusCode + " while retrieving " + url);
			}
			
			final HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream inputStream = null;
				
				try {
					/*
					Document doc = null;
					try {
						DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
						DocumentBuilder db = dbf.newDocumentBuilder();
						
						inputStream = entity.getContent();
						doc = db.parse(inputStream);	
						
						if (doc != null) {
							
						}
					}
					*/
					inputStream = entity.getContent();
					
					SaxOne sax = new SaxOne();
					sax.parse(inputStream);
					
					
					
					/*
					XPath xpath = XPathFactory.newInstance().newXPath();
					String expression = "/channel/item";
					
					String test = xpath.evaluate(expression, inputSource);
					Log.d(TAG, test);
					*/
					
					
					/*
					inputStream = entity.getContent();
					Log.d(TAG, "Success. Now...dump response...");
					
					final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
					
					String inputLine;
					while ((inputLine = reader.readLine()) != null) {
						Log.d(TAG, inputLine);
					}
					*/
					Log.d(TAG, "Done dumping response.");
				} finally {
					if (inputStream != null) {
						inputStream.close();
					}
					entity.consumeContent();
				}
			}
			
			Log.d(TAG, response.toString());
		} catch (IOException e) {
			request.abort();
			Log.d(TAG, e.toString());
		} catch (Exception e) {
			Log.d(TAG, e.toString());
		}
		Log.d(TAG, "stop_disini");
	}
	
	private void initialiseListContent() {
		for(int i = 0; i < names.length; i++) {
			listOfNames.add(names[i]);
		}
		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, listOfNames));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		edit.setText(((TextView) v).getText());
	}

	public class SaxOne extends DefaultHandler {
		public void parse(InputStream inputstream) {	
			try {
				SAXParserFactory spf = SAXParserFactory.newInstance();
				SAXParser sp = spf.newSAXParser();
				
				sp.parse(inputstream, this);
			} catch (SAXException e) {
				
			} catch (ParserConfigurationException e) {
				
			} catch (IOException e) {
				
			}
		}
		
		public void startElement(String namespaceURI, String localName, String rawName, Attributes attrs) {
			Log.d("NOW.LEONARDO", "another call to startElement");
			Log.d("NOW.LEONARDO", namespaceURI);
			Log.d("NOW.LEONARDO", localName);
			Log.d("NOW.LEONARDO", rawName);
		}
		
		public void endElement(String namespaceURI, String localName, String rawName, Attributes attrs) {
			Log.d("NOW.LEONARDO", "another call to startElement");
			Log.d("NOW.LEONARDO", namespaceURI);
			Log.d("NOW.LEONARDO", localName);
			Log.d("NOW.LEONARDO", rawName);
		}
		
		public void characters(char ch[], int start, int length) {
			Log.d("NOW.LEONARDO", "another call to characters()");
			Log.d("NOW.LEONARDO", new String(ch, start, length));
		} 
	}
}





















