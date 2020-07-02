package com.msgqueue;
import com.msgqueue.Controllers.ClienteController;
import com.msgqueue.Models.Lectura;
import freemarker.template.Configuration;
import org.apache.activemq.ActiveMQConnectionFactory;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import javax.jms.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static spark.Spark.staticFiles;
import static spark.Spark.get;
import static spark.Spark.*;

public class Consumidor {
    ActiveMQConnectionFactory factory;
    javax.jms.Connection connection;
    Session session;
    Queue queue;
    MessageConsumer consumer;
    String cola;

    public Consumidor(String cola){
        this.cola = cola;
    }

    public void conectar() throws JMSException {
        /*Gestión de conexión a una cola por un canal que se supone ya existe.*/
        factory = new ActiveMQConnectionFactory("admin", "admin",
                "failover:tcp://localhost:61616");
        //Inicializo la conexión
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //Creanddo la cola tipo Queue, recibida por el constructor para establecer la conexión.
        queue = session.createQueue(cola);
        System.out.println(" Esperando mensajes... ");
        consumer = session.createConsumer(queue);
        consumer.setMessageListener(message -> {

            try {
                TextMessage messageTexto = (TextMessage) message;
                ClienteController.enviarMensaje(messageTexto.getText());
                System.out.println("El mensaje de texto recibido: " + messageTexto.getText()+" - "
                        +new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
    }

}
