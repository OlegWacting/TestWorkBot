package oleg.bot.config;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import oleg.bot.service.Impl.MessageService;
import oleg.bot.service.Messages;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(value = "oleg.bot")
@PropertySource("classpath:vkconfig.properties")
public class AppConfig {

    @Value("${group_id}")
    private String groupId;
    @Value("${access_token}")
    private String accessToken;

    @Bean
    public BotConfig getBotConfig() throws ClientException, ApiException {
        return new BotConfig(groupId, accessToken);
    }

    @Bean
    public static Messages getMessages(BotConfig botConfig) throws ClientException, ApiException {
        return new MessageService(botConfig);
    }
}
