package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.GetHistoryAttachments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class GetHistoryAttachmentsExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetHistoryAttachmentsExample.class);
    private static final int PEER_ID = 918650328;
    private static final int GROUP_ID = 886761559;

    public static void main(String[] args) {
        try {
            GetHistoryAttachmentsExample example = new GetHistoryAttachmentsExample();
            example.getHistoryAttachments();
            example.getHistoryAttachmentsAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void getHistoryAttachments() throws VkApiException {
        GetHistoryAttachments.ResponseBody responseBody = vk.messages.getHistoryAttachments()
                .setGroupId(GROUP_ID)
                .setPeerId(PEER_ID)
                .setMediaType("photo")
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }

    public void getHistoryAttachmentsAsync() {
        CompletableFuture<GetHistoryAttachments.ResponseBody> future = vk.messages.getHistoryAttachments()
                .setGroupId(GROUP_ID)
                .setPeerId(PEER_ID)
                .setMediaType("photo")
                .executeAsync();

        // Main thread is free...

        System.out.println("Async responseBody: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
