package ro.unibuc.fmi.bookstoread.Utils;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import ro.unibuc.fmi.bookstoread.Model.Book;

public final class QueryUtils {
    private QueryUtils() {}
    public static List<Book> fetchBookData(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("Exc: ", "Problem making the HTTP request.", e);
        }
        List<Book> books = extractFeatureFromJson(jsonResponse);
        return books;
    }
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("Exc: ", "Problem building the URL ", e);
        }
        return url;
    }
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("Error code: ", urlConnection.getResponseCode()+"");
            }
        } catch (IOException e) {
            Log.e("Exc: ", "Problem retrieving the book JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    private static List<Book> extractFeatureFromJson(String bookSJON) {
        if (TextUtils.isEmpty(bookSJON)) {
            return null;
        }
        List<Book> books = new ArrayList<>();
        try {
            JSONObject baseJsonResponse = new JSONObject(bookSJON);
            JSONArray bookArray = baseJsonResponse.getJSONArray("items");
            for (int i = 0; i < bookArray.length(); i++) {
                JSONObject currentBook = bookArray.getJSONObject(i);
                String currentID = currentBook.getString("id");
                JSONObject currentVolume = currentBook.getJSONObject("volumeInfo");
                String title = currentVolume.getString("title");
              //  JSONObject currentImageLinks = currentVolume.getJSONObject("imageLinks");
                String currentCover = "";
                if(currentVolume.has("imageLinks")) {
                    currentCover = currentVolume.getJSONObject("imageLinks").optString("smallThumbnail");
                }
                JSONArray authors = new JSONArray();
                if(currentVolume.has("authors")) {
                     authors = currentVolume.getJSONArray("authors");
                }
                String author="";
                for(int currentAuthor = 0;currentAuthor<authors.length();currentAuthor++)
                    author =authors.get(currentAuthor).toString()+"\n";
                String desc = currentVolume.optString("description");
                String url = currentBook.getJSONObject("saleInfo").optString("buyLink");
                Book book = new Book(currentID,title, author, desc, url,currentCover);
                books.add(book);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the book JSON results", e);
        }
        return books;
    }

}