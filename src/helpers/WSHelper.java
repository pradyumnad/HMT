package helpers;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import com.pradyumna.hmt.HomeActivity;
import com.pradyumna.hmt.R;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WSHelper {

	public WSListener wsListener;

	private List<NameValuePair>params;
	private String url;
	private Context context;
	private WSType wsType;

	public WSHelper(String url, List<NameValuePair> params, Context context) {
		this.params = params;
		this.url = url;
		this.context = context;
	}

	public void processRequest(WSType type) {
		wsType = type;
		WSTask task = new WSTask();
		task.execute("");
	}

	public void addWSListener(WSListener listener) {
		wsListener = listener;
	}

	/**
	 * Inner class to carry out the Web service tasks
	 */
	class WSTask extends AsyncTask<String, Void, String> {

		private Exception exception;

		@Override
		protected void onPreExecute() {
			Log.d(this.getClass().toString(), "Started..");
		}

		protected String doInBackground(String... urls) {
			String json;
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpResponse response = null;
				switch (wsType) {
					case WSGET:
						// HTTP Parameters added
//						httpGet.setEntity(new UrlEncodedFormEntity(params));
						StringBuffer paramString = new StringBuffer("?");

						for (NameValuePair obj:params) {
							 paramString.append(obj.getName()+"="+obj.getValue()+"&");
						}

						Character endingChar = paramString.charAt(paramString.length()-1);
						if (endingChar.compareTo('&') == 0 || endingChar.compareTo('?') == 0) {
							paramString.deleteCharAt(paramString.length()-1);
						}
						url = url+""+paramString;
						Log.d(this.getClass().toString(), url);
						HttpGet httpGet = new HttpGet(url);
						// Execute HTTP Post Request
						response = httpclient.execute(httpGet);
						break;
					case WSPOST:
						HttpPost httpPost = new HttpPost(url);
						// HTTP Parameters added
						httpPost.setEntity(new UrlEncodedFormEntity(params));

						// Execute HTTP Post Request
						response = httpclient.execute(httpPost);
						break;
				}

				System.out.println(response.toString());
				BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
				json = reader.readLine();
				Log.d(this.getClass().toString(), json);


			} catch (Exception e) {
				this.exception = e;
				return null;
			}
			return json;
		}

		protected void onPostExecute(String feed) {
			if (exception != null) {
				Log.d(this.getClass().toString(), "Exception : "+this.exception);
				wsListener.onRequestFailed(exception);
			} else {
				System.out.println("Finished : " + feed);
				wsListener.onRequestCompleted(feed);
			}
		}
	}
}
