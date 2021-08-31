package oleg.bot.service;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import oleg.bot.config.BotConfig;

public interface Messages {
    public Message getMessage() throws ClientException, ApiException;
    public BotConfig getBotConfig();
}
