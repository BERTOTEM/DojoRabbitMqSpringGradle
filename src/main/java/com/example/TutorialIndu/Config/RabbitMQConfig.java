package com.example.TutorialIndu.Config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.name}")
    private  String queue;

    @Value("${rabbitmq.queue.json.name}")
    private  String jsonQueue;

    @Value("${rabbitmq.exchange.name}")
    private  String exchange;
    @Value("${rabbitmq.routing.Key}")
    private  String routingKey;

    @Value("${rabbitmq.routing.json.Key}")
    private  String routingJsonKey;

    //Spring bean para Rabbitmq Cola
    //se debe de Usar el Queue de la dependencia AMQP.core
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }
    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
    }
    @Bean
    public TopicExchange exchange(){
        return  new TopicExchange(exchange);
    }
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }
    @Bean
    public Binding JsonBinding(){
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(routingJsonKey);
    }

    //Jackson2JsonMessageConverter es un convertidor de mensajes en RabbitMQ que se utiliza para
    // serializar objetos Java en formato JSON y viceversa. En otras palabras, este convertidor se encarga de transformar
    // objetos Java en mensajes JSON que pueden ser enviados a través de RabbitMQ y viceversa.
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    //La interfaz AmqpTemplate se utiliza para abstraer la complejidad de la comunicación con RabbitMQ
    // y proporcionar una API sencilla y flexible para enviar y recibir mensajes en una aplicación Java
    // Algunas de las funciones principales que puede realizar la interfaz AmqpTemplate son:

    //Enviar mensajes a una cola o intercambio.
    //Recuperar mensajes de una cola.
    //Convertir objetos Java en mensajes AMQP y viceversa.
    //Establecer propiedades de mensajes como el intercambio de destino, la ruta de enrutamiento y la persistencia.
    //En resumen, la interfaz AmqpTemplate es una herramienta muy útil para simplificar la implementación de
    // aplicaciones que se comunican con RabbitMQ, ya que proporciona una interfaz de programación de aplicaciones
    // (API) fácil de usar y una abstracción de la complejidad subyacente de la comunicación con RabbitMQ.
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return  rabbitTemplate;
    }

    //RabbitTemplate se utiliza principalmente para enviar mensajes a una cola en RabbitMQ. Proporciona métodos
    // simples para enviar mensajes, como convertAndSend(), que convierte el objeto proporcionado en un mensaje
    // y lo envía a la cola especificada. También ofrece una variedad de opciones para controlar el intercambio,
    // la ruta de enrutamiento y otros atributos del mensaje.
    //
    //Además de enviar mensajes, RabbitTemplate también proporciona métodos para recibir mensajes de una cola.
    // Estos métodos incluyen receive() y receiveAndConvert(), que se utilizan para recibir mensajes y convertirlos
    // en objetos Java.
    //
    //En general, RabbitTemplate es una herramienta útil para interactuar con RabbitMQ y simplificar
    // el proceso de enviar y recibir mensajes.
}
