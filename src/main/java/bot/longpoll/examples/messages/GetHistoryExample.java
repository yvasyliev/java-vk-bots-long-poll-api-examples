package bot.longpoll.examples.messages;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.GetHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class GetHistoryExample extends LongPollBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetHistoryExample.class);
    private static final int PEER_ID = 918650328;

    public static void main(String[] args) {
        try {
            GetHistoryExample example = new GetHistoryExample();
            example.getHistory();
            example.getHistoryAsync();
        } catch (VkApiException e) {
            LOGGER.error("Something went wrong...", e);
        }
    }

    public void getHistory() throws VkApiException {
        GetHistory.Response response = vk.messages.getHistory()
                .setPeerId(PEER_ID)
                .setGroupId(getGroupId())
                .execute();

        System.out.println("Sync response: " + response);
    }

    public void getHistoryAsync() {
        CompletableFuture<GetHistory.Response> future = vk.messages.getHistory()
                .setPeerId(PEER_ID)
                .setGroupId(getGroupId())
                .executeAsync();

        // Main thread is free...

        System.out.println("Async response: " + future.join());
    }

    @Override
    public String getAccessToken() {
        return "8458cbfa085ce2312f67905f84fb9709b76ffcf7e9a77c89b05e79c64b7e710a3a04eb48f46bfcf64e5c9";
    }
}
