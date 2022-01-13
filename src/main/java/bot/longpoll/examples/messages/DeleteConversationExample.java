package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.DeleteConversation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class DeleteConversationExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteConversationExample.class);
    private static final int CHAT_ID = 9;

    public static void main(String[] args) {
        try {
            DeleteConversationExample example = new DeleteConversationExample();
            example.deleteConversation();
            example.deleteConversationAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void deleteConversation() throws VkApiException {
        DeleteConversation.Response response = vk.messages.deleteConversation()
                .setGroupId(getGroupId())
                .setPeerId(CHAT_ID)
                .execute();

        System.out.println("Sync response: " + response);
    }

    public void deleteConversationAsync() {
        CompletableFuture<DeleteConversation.Response> future = vk.messages.deleteConversation()
                .setGroupId(getGroupId())
                .setPeerId(CHAT_ID)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async response: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
