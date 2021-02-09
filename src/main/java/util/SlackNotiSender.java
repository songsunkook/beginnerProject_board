package util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlackNotiSender {

    @Value("${slack.notify_url}")
    private String notify_url;
    @Value("${slack.channel_general_name}")
    private String channel_general_name;
    @Value("${slack.channel_notification_name}")
    private String channel_notification_name;

    private RestTemplate restTemplate = new RestTemplate();

    public void sendNotice(Object attachmentData){
        final List<Object> attachment = new ArrayList<Object>();
        attachment.add(attachmentData);
        Map<String, Object> params = new HashMap<String, Object>() {{
            put("channel", channel_general_name);
            put("text", "뽷 뽷 뽷 뽷 뽷 뽷");
            put("attachments", attachment);
        }};

        try {
            restTemplate.postForObject(notify_url,params,String.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }
}
