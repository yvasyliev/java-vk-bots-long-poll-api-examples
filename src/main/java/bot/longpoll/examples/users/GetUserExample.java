package bot.longpoll.examples.users;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollAPIException;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.methods.users.UsersGet;
import api.longpoll.bots.model.response.users.UsersGetResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetUserExample extends LongPollBot {
    private static final Logger log = LoggerFactory.getLogger(GetUserExample.class);
    private static final List<String> USER_IDS = Collections.singletonList("918650328");

    public void getUser() {
        try {
            UsersGetResult result = new UsersGet(getAccessToken())
                    .setUserIds(USER_IDS)
                    .execute();

            System.out.println("Sync result: " + result);

        } catch (BotsLongPollAPIException | BotsLongPollException e) {
            log.error("Error during execution.", e);
        }
    }

    public void getUserAsync() {
        CompletableFuture<UsersGetResult> future = new UsersGet(getAccessToken())
                .setUserIds(USER_IDS)
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
        GetUserExample example = new GetUserExample();
        example.getUser();
        example.getUserAsync();
    }
}