package com.msgqueue;
import com.msgqueue.Controllers.ClienteController;

import org.apache.activemq.ActiveMQConnectionFactory;


import javax.jms.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Consumidor {
    ActiveMQConnectionFactory factory;
    Connection connection;
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
                "failover:tcp://openwire-queue:61616");
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
                System.out.println("[X] Mensaje recibido " + messageTexto.getText()+" *Recibido en:"
                        +new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
    }

}
