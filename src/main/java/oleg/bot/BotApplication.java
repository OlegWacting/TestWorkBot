package oleg.bot;

import oleg.bot.config.AppConfig;
import oleg.bot.service.Impl.BotService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BotApplication {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        BotService botService = applicationContext.getBean(BotService.class);
        while (true){
            botService.sendMessageInVk();
        }
    }
}
