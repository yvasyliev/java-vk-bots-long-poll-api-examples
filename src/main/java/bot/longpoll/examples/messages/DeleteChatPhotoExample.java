package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.DeleteChatPhoto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class DeleteChatPhotoExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteChatPhotoExample.class);
    private static final int CHAT_ID = 1;
    private static final int GROUP_ID = 886761559;

    public static void main(String[] args) {
        try {
            DeleteChatPhotoExample example = new DeleteChatPhotoExample();
            example.deleteChatPhoto();
            example.deleteChatPhotoAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void deleteChatPhoto() throws VkApiException {
        DeleteChatPhoto.ResponseBody responseBody = vk.messages.deleteChatPhoto()
                .setGroupId(GROUP_ID)
                .setChatId(CHAT_ID)
                .execute();

        System.out.println("Sync responseBody: " + responseBody);
    }

    public void deleteChatPhotoAsync() {
        CompletableFuture<DeleteChatPhoto.ResponseBody> future = vk.messages.deleteChatPhoto()
                .setGroupId(GROUP_ID)
                .setChatId(CHAT_ID)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async responseBody: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
