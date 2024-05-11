package tech.noetzold.JVMParamsCompare.message.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.noetzold.JVMParamsCompare.message.config.RabbitmqQueues;
import tech.noetzold.JVMParamsCompare.model.DataIntensiveModel;
import tech.noetzold.JVMParamsCompare.service.DataIntensiveService;

@Component
public class DataIntensiveConsumer {

    @Autowired
    DataIntensiveService dataIntensiveService;

    private static final Logger logger = LoggerFactory.getLogger(DataIntensiveConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.DATA_INTESIVE_QUEUE)
    public void consumerDataIntensive(String message) throws JsonProcessingException {
        DataIntensiveModel dataIntensiveModel = new ObjectMapper().readValue(message, DataIntensiveModel.class);
        try {
            dataIntensiveService.saveResult(dataIntensiveModel);
            logger.info("Consume dataIntensiveModel");
        }catch (Exception ex){
            logger.error("Error to consume cerate message for dataIntensiveModel - " + dataIntensiveModel.getId(), ex);
            throw new AmqpRejectAndDontRequeueException("Ops, an error! Message should go to DLQ");
        }

    }
}
