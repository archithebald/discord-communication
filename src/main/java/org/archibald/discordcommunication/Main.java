package org.archibald.discordcommunication;

import org.archibald.discordcommunication.utils.Helpers;

public class Main {
    public static void main(String[] args) {
        WebhookMain webhook = new WebhookMain("https://discord.com/api/webhooks/1328656507503640670/Dx29XUbLj2JocC3RrfbL2crzhbN4nZ6UoBWblvM9-uWiPJhCDxT_izQ58ueD0DgUuM_Q");
        webhook.execute("test");
    }
}
