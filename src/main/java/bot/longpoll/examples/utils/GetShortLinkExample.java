package bot.longpoll.examples.utils;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.utils.GetShortLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class GetShortLinkExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetShortLinkExample.class);
    private static final String URL = "https://google.com";

    public static void main(String[] args) {
        try {
            GetShortLinkExample example = new GetShortLinkExample();
            example.getShortLink();
            example.getShortLinkAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void getShortLink() throws VkApiException {
        GetShortLink.ResponseBody responseBody = vk.utils.getShortLink()
                .setUrl(URL)
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }

    public void getShortLinkAsync() {
        CompletableFuture<GetShortLink.ResponseBody> future = vk.utils.getShortLink()
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
