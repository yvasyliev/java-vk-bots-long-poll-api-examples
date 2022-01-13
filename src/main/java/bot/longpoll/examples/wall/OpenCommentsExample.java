package bot.longpoll.examples.wall;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.response.IntegerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class OpenCommentsExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenCommentsExample.class);
    private static final int POST_ID = 9;

    public static void main(String[] args) {
        try {
            OpenCommentsExample example = new OpenCommentsExample();
            example.openComments();
            example.openCommentsAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void openComments() throws VkApiException {
        IntegerResponse response = vk.wall.openComments()
                .setPostId(POST_ID)
                .setOwnerId(-getGroupId())
                .execute();

        System.out.println("Sync response: " + response);
    }

    public void openCommentsAsync() {
        CompletableFuture<IntegerResponse> future = vk.wall.openComments()
                .setPostId(POST_ID)
                .setOwnerId(-getGroupId())
                .executeAsync();

        // Main thread is free...

        System.out.println("Async response: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
