package oleg.bot.config;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public class BotConfig {

    private TransportClient transportClient;
    private VkApiClient vk;
    private GroupActor groupActor;
    private Integer ts;

    public BotConfig(String groupId, String accessToken) throws ClientException, ApiException {
        transportClient = HttpTransportClient.getInstance();
        vk = new VkApiClient(transportClient);

        groupActor = new GroupActor(Integer.parseInt(groupId), accessToken);

        ts = vk.messages().getLongPollServer(groupActor).execute().getTs();
    }

    public GroupActor getActor(){
        return groupActor;
    }

    public VkApiClient getVk(){
        return vk;
    }

    public int getTs(){
        return ts;
    }

    public void setTs(Integer ts) {
        this.ts = ts;
    }

}
