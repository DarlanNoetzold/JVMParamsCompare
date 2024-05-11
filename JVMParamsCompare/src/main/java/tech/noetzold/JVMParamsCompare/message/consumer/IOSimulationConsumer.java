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
import tech.noetzold.JVMParamsCompare.model.IODataModel;
import tech.noetzold.JVMParamsCompare.service.IODataService;

@Component
public class IOSimulationConsumer {

    @Autowired
    private IODataService ioDataService;

    private static final Logger logger = LoggerFactory.getLogger(IOSimulationConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.IO_SIMULATION_QUEUE)
    public void consumerIOSimulation(String message) throws JsonProcessingException {
        IODataModel ioDataModel = new ObjectMapper().readValue(message, IODataModel.class);
        try {
            ioDataService.saveData(ioDataModel);
            logger.info("Consume IOSimulation");
        }catch (Exception ex){
            logger.error("Error to consume cerate message for IOSimulation - " + ioDataModel.getId(), ex);
            throw new AmqpRejectAndDontRequeueException("Ops, an error! Message should go to DLQ");
        }

    }
}
