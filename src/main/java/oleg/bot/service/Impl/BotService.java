package oleg.bot.service.Impl;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import oleg.bot.models.Answer;
import oleg.bot.service.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class BotService {

    private Messages messages;

    @Autowired
    public BotService(Messages messages){
        this.messages = messages;
    }

    public void sendMessageInVk() throws InterruptedException {
        Thread.sleep(300);
        try {
            Message message = messages.getMessage();
            if (message != null){
                ExecutorService exec = Executors.newCachedThreadPool();
                Random random = new Random();
                Answer answer = new Answer();
                answer.setMessageBody(message.getText());
                messages.getBotConfig().getVk().messages().send(messages.getBotConfig().getActor())
                        .message(answer.modifyAnswer())
                        .userId(message.getFromId())
                        .randomId(random.nextInt(10000))
                        .execute();
            }
        } catch (ApiException | ClientException e){
            e.printStackTrace();
        }
    }
}
