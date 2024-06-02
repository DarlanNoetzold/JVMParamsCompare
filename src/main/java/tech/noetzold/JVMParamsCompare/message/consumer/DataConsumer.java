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
import tech.noetzold.JVMParamsCompare.model.DataModel;
import tech.noetzold.JVMParamsCompare.service.DataService;

@Component
public class DataConsumer {

    @Autowired
    DataService dataService;

    private static final Logger logger = LoggerFactory.getLogger(DataConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.DATA_QUEUE)
    public void consumerData(String message) throws JsonProcessingException {
        DataModel dataModel = new ObjectMapper().readValue(message, DataModel.class);
        try {
            dataService.addData(dataModel);
            logger.info("Consume dataModel");
        }catch (Exception ex){
            logger.error("Error to consume cerate message for dataModel - " + dataModel.getId(), ex);
            throw new AmqpRejectAndDontRequeueException("Ops, an error! Message should go to DLQ");
        }

    }
}
