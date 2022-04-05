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

import cryptoTrader.gui.TradingClient;

public class DataFetcher {
	
	HashMap<String, String> coin_map = new HashMap<String, String>();	// Hashmap used when translating all symbols to IDs in the method below
	 
	/**
	* Private helper method
	* @param String representing the symbol to be translated to an id
	* Uses the Coin Gecko API and sends request to find the corresponding symbol associated with the id
	* @return the id represented through the symbol given as a parameter
	*/
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

		
	
    /**
	* Private helper method
	* @param String representing the symbol to be translated to an id and the date of trade
	* First uses the above helper method but in case it fails additional mechanisms are put into place to ensure the translation
	* Second commits an http request to find the data associated with that symbol
	* @return the id represented through the symbol
	*/
	private JsonObject getDataForCrypto(String symbol, String date) {
		String id = translatefromSymboltoID(symbol);
		if (id==null) {
			if (symbol=="btc") {
				id = "bitcoin";
			}
			if (symbol =="eth") {
				id = "ethereum";
				
			}
			if (symbol=="xlm") {
				id = "stellar";
				
			}
		}
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
	
	 /**
	* Public getter method
	* @param String representing the symbol to be translated to an id and the date of trade
	* Uses the helper method to return the price associated with the coin
	*/
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
	/**
	* Public getter method
	* @param String representing the symbol to be translated to an id and the date of trade
	* Uses the helper method to return the market cap associated with the coin
	*/
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
	/**
	* Public getter method
	* @param String representing the symbol to be translated to an id and the date of trade
	* Uses the helper method to return the total volume associated with the coin
	*/
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
}
