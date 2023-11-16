package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class FundamentalHttpsRequest {

	public static String url = "https://jsonplaceholder.typicode.com/albums";

	public static String callHttpRequestByBRLConnection(String url) {
		String res = "";
		try {
			URL urlObject = new URL(url);
			HttpURLConnection con = (HttpURLConnection) urlObject.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(3000);
			con.setReadTimeout(3000);
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream inputStream = con.getInputStream();
				res = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public static String callHttpRequestByHttpClient(String url) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
		String res = "";
		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			res = response.body();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public static Object parseToJSON(String value) {
		return JSONValue.parse(value);
	}

	public static List<Album> createListAlbum(Object data) {
		List<Album> albums = new ArrayList<Album>();
		((JSONArray) data).stream().forEach(e -> {
			JSONObject item = (JSONObject) e;
			albums.add(Album.createBuilder().id(Integer.parseInt(item.get("id").toString()))
					.userId(Integer.parseInt(item.get("userId").toString())).title(item.get("title").toString())
					.build());
		});
		return albums;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String data = FundamentalHttpsRequest.callHttpRequestByBRLConnection(FundamentalHttpsRequest.url);
		JSONArray jsonData = (JSONArray) FundamentalHttpsRequest.parseToJSON(data);
		List<Album> albums = FundamentalHttpsRequest.createListAlbum(jsonData);
		System.out.println("Quantity album:" + albums.size());
		return;
	}

}
