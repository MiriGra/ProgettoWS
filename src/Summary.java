import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class Summary {

	private String inputText;
	private String summarizedText;
	
	public Summary(String text){
		this.inputText = text;
	}
	
	public void summarize(){
		DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://textcompactor.com");
        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("text_in", inputText));
            nameValuePairs.add(new BasicNameValuePair("percentage", "70"));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            String jsonString = EntityUtils.toString(response.getEntity());
            trasformResult(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private void trasformResult(String jsonString) {
        String toReturn = jsonString.substring(jsonString.indexOf("<div class=\"step\" id=\"output-step\">"));
        System.out.println(toReturn);
        toReturn = toReturn.substring(toReturn.indexOf("<p>")+1,toReturn.indexOf("<br>"));
        toReturn = toReturn.substring(toReturn.indexOf("<p>")+3);
        toReturn = toReturn.replace("<p>", "\n");
        toReturn = toReturn.replace("&#34;", "\"");
        summarizedText = toReturn.replace("\n\n", "\n");
	}	
	
	public String getSummarizedText(){
		return summarizedText;
	}
	
	public String getInputText(){
		return inputText;
	}
}
