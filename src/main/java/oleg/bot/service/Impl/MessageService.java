package oleg.bot.service.Impl;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;
import oleg.bot.config.BotConfig;
import oleg.bot.models.Answer;
import oleg.bot.service.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MessageService implements Messages {

    private BotConfig botConfig;
    private Answer answer;
    private List<Message> messages;
    private int maxMsgId = -1;

    @Autowired
    public MessageService(BotConfig botConfig) throws ClientException, ApiException {
        this.botConfig = botConfig;
    }

    public Message getMessage() throws ClientException, ApiException {
        MessagesGetLongPollHistoryQuery historyQuery = botConfig.getVk().messages()
                .getLongPollHistory(botConfig.getActor()).ts(botConfig.getTs());
        if(maxMsgId > 0){
            historyQuery.maxMsgId(maxMsgId);
        }
        messages = historyQuery.execute().getMessages().getItems();
        if (!messages.isEmpty()){
            try {
                botConfig.setTs(botConfig.getVk()
                        .messages()
                        .getLongPollServer(botConfig.getActor())
                        .execute()
                        .getTs());
            } catch (ClientException e){
                e.printStackTrace();
            }
        }

        if (!messages.isEmpty() && !messages.get(0).isOut()) {
            int messageId = messages.get(0).getId();
            if (messageId > maxMsgId) {
                maxMsgId = messageId;
            }
            return messages.get(0);
        }
        return null;
    }

    public BotConfig getBotConfig() {
        return botConfig;
    }

}
