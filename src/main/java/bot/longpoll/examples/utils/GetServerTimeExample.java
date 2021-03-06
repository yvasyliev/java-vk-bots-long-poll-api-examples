package bot.longpoll.examples.utils;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.response.IntegerResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class GetServerTimeExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetServerTimeExample.class);

    public static void main(String[] args) {
        try {
            GetServerTimeExample example = new GetServerTimeExample();
            example.getServerTime();
            example.getServerTimeAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void getServerTime() throws VkApiException {
        IntegerResponseBody responseBody = vk.utils.getServerTime()
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }

    public void getServerTimeAsync() {
        CompletableFuture<IntegerResponseBody> future = vk.utils.getServerTime()
                .executeAsync();

        // Main thread is free...

        System.out.println("Async responseBody: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
