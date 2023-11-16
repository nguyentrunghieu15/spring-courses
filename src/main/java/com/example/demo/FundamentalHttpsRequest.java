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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class FundamentalHttpsRequest {

	public static String url = "https://jsonplaceholder.typicode.com/albums";

	public String callHttpRequestByBRLConnection(String url) {
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

	public String callHttpRequestByHttpClient(String url) {
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

	public Object parseToJSON(String value) {
		return JSONValue.parse(value);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		return;
	}

}
