package org.archibald.discordcommunication.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.world.entity.ai.goal.Goal;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openjdk.nashorn.internal.runtime.JSONFunctions;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Helpers {
    public void getWebhookObject(String id) {
        String url = "";


    }

    public static JSONObject fetchResponse(String url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

            StringBuilder response = new StringBuilder();
            Scanner scanner = new Scanner(conn.getInputStream());

            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }

            JSONParser parser = new JSONParser();

            JSONObject data = (JSONObject) parser.parse(response.toString());

            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        JSONObject responseObject = Helpers.fetchResponse("http://api.open-notify.org/astros.json");
        System.out.println(responseObject.get("people"));
    }
}
