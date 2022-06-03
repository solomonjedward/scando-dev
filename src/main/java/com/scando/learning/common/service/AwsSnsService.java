package com.scando.learning.common.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.AmazonSNSException;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class AwsSnsService {

    public static final String TOPIC_ARN = "arn:aws:sns:ap-south-1:744012312434:Storiyoh";

    @Autowired
    private AmazonSNSClient amazonSNSClient;

    public boolean sendMessage(String message, String phoneNumber){
        try {


/*            Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
            messageAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
                    .withStringValue("SCANDO-E")
                    .withDataType("String"));*/

            PublishResult result = amazonSNSClient.publish(new PublishRequest().
                    withMessage(message).
                    withPhoneNumber(phoneNumber));
//                    .withMessageAttributes(messageAttributes));
//            LOGGER.debug("Message :{} was published successfully to :{} number", message, phoneNumber);
            LOGGER.debug("Message :{} was published successfully to :{} number with messageId :{}", message, phoneNumber, result.getMessageId());

            return true;
        }catch (AmazonSNSException e) {
            LOGGER.error(Arrays.toString(e.getStackTrace()));
            throw e;
        }
    }




}
