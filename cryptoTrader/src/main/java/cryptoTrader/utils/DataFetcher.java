package cryptoTrader.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DataFetcher {
	
	HashMap<String, String> coin_map = new HashMap<String, String>();
	 
    private String translatefromSymboltoID(String symbol) {
 
        // Get coin list
        String urlString = String.format(
                "https://api.coingecko.com/api/v3/coins/list");
 
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();
            if (responsecode == 200) {
                String inline = "";
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    inline += sc.nextLine();
                }
                sc.close();
 
                // Adding json objects to an array
                JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
 
                // Iterating through coin list to get each coin object
                for (JsonElement obj : jsonArray) {
                    JsonObject object = obj.getAsJsonObject();
 
                    // Append each coin to coin map with key as symbol and value as id
                    coin_map.put(object.get("symbol").toString().replaceAll("\"", ""),
                            object.get("id").toString().replaceAll("\"", ""));
                }
                return coin_map.get(symbol);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong with the API call.");
        }
		return null;
    }

		
	

	private JsonObject getDataForCrypto(String symbol, String date) {
		String id = translatefromSymboltoID(symbol);

		String urlString = String.format(
				"https://api.coingecko.com/api/v3/coins/%s/history?date=%s", id, date);
		
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				JsonObject jsonObject = new JsonParser().parse(inline).getAsJsonObject();
				
				return jsonObject;
			}

		} catch (IOException e) {
			System.out.println("Something went wrong with the API call.");
		}
		return null;
	}
	
	public double getPriceForCoin(String id, String date) {
		double price = 0.0;
		
		JsonObject jsonObject = getDataForCrypto(id, date);
		
		if (jsonObject != null) {

			JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
			JsonObject currentPrice = marketData.get("current_price").getAsJsonObject();
			price = currentPrice.get("cad").getAsDouble();
		}
		
		return price;
	}
	
	public double getMarketCapForCoin(String id, String date) {
		double marketCap = 0.0;
		
		JsonObject jsonObject = getDataForCrypto(id, date);
		if (jsonObject != null) {
			JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
			JsonObject currentPrice = marketData.get("market_cap").getAsJsonObject();
			marketCap = currentPrice.get("cad").getAsDouble();
		}
		
		return marketCap;
	}
	
	public double getVolumeForCoin(String id, String date) {
		double volume = 0.0;
		
		JsonObject jsonObject = getDataForCrypto(id, date);
		if (jsonObject != null) {
			JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
			JsonObject currentPrice = marketData.get("total_volume").getAsJsonObject();
			volume = currentPrice.get("cad").getAsDouble();
		}
		
		return volume;
	}
	
//	public static void main(String[] args) {
//		DataFetcher fetcher = new DataFetcher();
//		double price = fetcher.getPriceForCoin("btc", "31-03-2022");
//		double marketCap = fetcher.getMarketCapForCoin("btc", "08-09-2021");
//		double volume = fetcher.getVolumeForCoin("btc", "08-09-2021");
//		
//		System.out.println(fetcher.translatefromSymboltoID("etc"));
//		
//		System.out.println("Bitcoin=>\tPrice: " + price + 
//								"\n\t\tMarket Cap: " + marketCap + 
//								"\n\t\tVolume: "+volume);
//		
//	}
}
