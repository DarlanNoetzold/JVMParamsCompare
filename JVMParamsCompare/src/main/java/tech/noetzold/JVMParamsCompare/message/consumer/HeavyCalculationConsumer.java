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
import tech.noetzold.JVMParamsCompare.model.HeavyCalculationModel;
import tech.noetzold.JVMParamsCompare.service.HeavyCalculationService;

@Component
public class HeavyCalculationConsumer {

    @Autowired
    HeavyCalculationService heavyCalculationService;

    private static final Logger logger = LoggerFactory.getLogger(HeavyCalculationConsumer.class);

    @Transactional
    @RabbitListener(queues = RabbitmqQueues.HEAVY_CALCULATION_QUEUE)
    public void consumerHeavyCalculation(String message) throws JsonProcessingException {
        HeavyCalculationModel heavyCalculationModel = new ObjectMapper().readValue(message, HeavyCalculationModel.class);
        try {
            heavyCalculationService.saveResult(heavyCalculationModel);
            logger.info("Consume heavyCalculationModel");
        }catch (Exception ex){
            logger.error("Error to consume cerate message for heavyCalculationModel - " + heavyCalculationModel.getId(), ex);
            throw new AmqpRejectAndDontRequeueException("Ops, an error! Message should go to DLQ");
        }

    }
}
