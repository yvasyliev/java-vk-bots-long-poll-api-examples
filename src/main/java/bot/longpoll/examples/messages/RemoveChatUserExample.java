package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollAPIException;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.messages.MessagesRemoveChatUser;
import api.longpoll.bots.model.response.IntegerResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class RemoveChatUserExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(RemoveChatUserExample.class);
    private static final Integer CHAT_ID = 8;
    private static final Integer USER_ID = 455751252;

    public void removeChatUser() {
        try {
            IntegerResult result = new MessagesRemoveChatUser(getAccessToken())
                    .setChatId(CHAT_ID)
                    .setUserId(USER_ID)
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollAPIException | BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    public void removeChatUserAsync() {
        CompletableFuture<IntegerResult> future = new MessagesRemoveChatUser(getAccessToken())
                .setChatId(CHAT_ID)
                .setUserId(USER_ID)
                .executeAsync();

        // Main thread is free...

        System.out.println("Async result: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }

    @Override
    public int getGroupId() {
        return 886761559;
    }

    public static void main(String[] args) {
        RemoveChatUserExample example = new RemoveChatUserExample();
        example.removeChatUser();
        example.removeChatUserAsync();
    }
}
