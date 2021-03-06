package bot.longpoll.examples.utils;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.utils.CheckLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class CheckLinkExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckLinkExample.class);
    private static final String URL = "https://google.com";

    public static void main(String[] args) {
        try {
            CheckLinkExample example = new CheckLinkExample();
            example.checkLink();
            example.checkLinkAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void checkLink() throws VkApiException {
        CheckLink.ResponseBody responseBody = vk.utils.checkLink()
                .setUrl(URL)
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }

    public void checkLinkAsync() {
        CompletableFuture<CheckLink.ResponseBody> future = vk.utils.checkLink()
                .setUrl(URL)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async responseBody: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
