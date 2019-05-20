package Core.Controller.MVC;

import Core.DTO.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.gson.*;
import com.google.protobuf.Message;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Type;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Objects;

/**
 * ************************************************************************************
 * Author: Mada facka
 * Created: 02/09/2014 10:38.
 * *************************************************************************************
 */
@Controller
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.robins.io.pubnub.api.web"})
public class MessageController {

    private final static String PUBLISH_KEY = "pub-c-80463209-5f78-4ea8-b026-916cd755c43c";
    private final static String SUBSCRIBE_KEY = "sub-c-5503db9c-74e8-11e9-82e1-3e4d28349f9d";

    private Pubnub pubnub = new Pubnub(PUBLISH_KEY, SUBSCRIBE_KEY);
    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, (JsonDeserializer) (json, typeOfT, context)
            -> new Date(json.getAsJsonPrimitive().getAsLong())).setLenient().create();

    public void sendToUser(ResponseDTO responseOnject, String channel) {
        JSONObject jsonObj = new JSONObject(gson.toJson(responseOnject));
        Callback callback = new Callback() {
            public void successCallback(String channel, Object response) {
                System.out.println("Success: " + response.toString());
            }

            public void errorCallback(String channel, Object error) {
                System.out.println("Error: :" + error.toString());
            }
        };

        //Should submit to user email and : supervisor_666
        pubnub.publish(channel, jsonObj, callback);
    }


    /*

    @RequestMapping(method = RequestMethod.POST, value = "/message")
    @ResponseBody
    Message post(@RequestBody Message message) throws Exception {

        String json = mapper.writeValueAsString(message);
        JSONObject jsonObj = new JSONObject(json);

        Callback callback = new Callback() {
            public void successCallback(String channel, Object response) {
                System.out.println("Success: " + response.toString());
            }
            public void errorCallback(String channel, Object error) {
                System.out.println("Error: :" + error.toString());
            }
        };

        pubnub.publish("chat-engine-server", jsonObj , callback);

        return message;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MessageController.class, args);
    }

    */
}
