package com.example.TutorialIndu.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    //La biblioteca de registro (logging) de RabbitMQ está basada en la
    // biblioteca de registro de Erlang, que proporciona un registro flexible
    // y escalable de eventos y errores. LoggerFactory se utiliza para
    // configurar y personalizar esta biblioteca de registro.
    //
    //Al configurar y utilizar LoggerFactory en RabbitMQ, los desarrolladores
    // pueden establecer los niveles de registro para los diferentes componentes
    // y módulos de RabbitMQ y dirigir los registros (logs) a diferentes
    // destinos, como archivos de registro, consolas o bases de datos. Esto permite
    // una mayor visibilidad en el comportamiento del sistema y facilita
    // la solución de problemas y la depuración de problemas en RabbitMQ.

    private static  final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);
    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public  void consume(String message){
        LOGGER.info(String.format("Received message -> %s",message));
    }
}
