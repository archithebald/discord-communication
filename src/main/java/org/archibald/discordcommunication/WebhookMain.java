package org.archibald.discordcommunication;

import org.archibald.discordcommunication.utils.Helpers;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WebhookMain {
    protected String webhook_url;
    protected JSONObject webhook_object;

    private String server_name;
    private String channel_id;
    private String guild_id;
    private String webhook_id;
    private String token;
    private String url;

    public String getServer_name() {
        return server_name;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public String getGuild_id() {
        return guild_id;
    }

    public String getWebhook_id() {
        return webhook_id;
    }

    public String getToken() {
        return token;
    }

    public String getUrl() {
        return url;
    }

    public WebhookMain(String webhook_url) {
        this.webhook_url = webhook_url;
        this.webhook_object = this.getWebhookObject(webhook_url);

        this.server_name = (String) this.webhook_object.get("name");
        this.guild_id = (String) this.webhook_object.get("guild_id");
        this.channel_id = (String) this.webhook_object.get("channel_id");
        this.webhook_id = (String) this.webhook_object.get("id");
        this.token = (String) this.webhook_object.get("token");
        this.url = (String) this.webhook_object.get("url");
    }

    private JSONObject getWebhookObject(String webhookUrl) {
        JSONObject webhookObject = Helpers.fetchResponse(webhookUrl, "GET", null, null);

        return webhookObject;
    }

    public void execute(String content) {
        JSONObject body = new JSONObject();
        body.put("content", content);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        JSONObject responseData = Helpers.fetchResponse("https://discord.com/api/v10/webhooks/" + this.webhook_id + "/" + this.token,
                "POST",
                body,
                headers
                );
        System.out.println(responseData);
    }
}
