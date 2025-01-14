package org.archibald.discordcommunication.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

public class Helpers {
    public static JSONObject fetchResponse(String url, String method, JSONObject requestBodyObject, Map<String, String> headers) {
        try {

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod(method);
            conn.setDoOutput(true);

            if (headers != null) {
                headers.forEach((key, value) -> {
                    conn.setRequestProperty(key, value);
                });
            }

            if (requestBodyObject != null) {
                String requestBody = requestBodyObject.toString();

                OutputStream os = conn.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
                osw.write(requestBody);
                osw.flush();
                osw.close();
                os.close();
            }
            conn.connect();

            StringBuilder response = new StringBuilder();
            Scanner scanner = new Scanner(conn.getInputStream());

            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }

            conn.disconnect();
            scanner.close();

            if (response.length() > 0) {
                JSONParser parser = new JSONParser();

                JSONObject data = (JSONObject) parser.parse(response.toString());

                return data;
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
