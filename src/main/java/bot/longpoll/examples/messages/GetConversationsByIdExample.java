package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.GetConversationsById;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class GetConversationsByIdExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetConversationsByIdExample.class);
    private static final int PEER_ID = 2000000008;
    private static final int GROUP_ID = 886761559;

    public static void main(String[] args) {
        try {
            GetConversationsByIdExample example = new GetConversationsByIdExample();
            example.getConversationsById();
            example.editMessageAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void getConversationsById() throws VkApiException {
        GetConversationsById.ResponseBody responseBody = vk.messages.getConversationsById()
                .setGroupId(GROUP_ID)
                .setPeerIds(PEER_ID)
                .setExtended(true)
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }

    public void editMessageAsync() {
        CompletableFuture<GetConversationsById.ResponseBody> future = vk.messages.getConversationsById()
                .setGroupId(GROUP_ID)
                .setPeerIds(PEER_ID)
                .setExtended(true)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async responseBody: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
