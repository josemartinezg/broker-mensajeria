package com.msgqueue.Controllers;

import com.msgqueue.Productor;
import org.apache.activemq.broker.BrokerService;

import javax.jms.JMSException;

public class QueueServerController {
    public static void main(String[] args) throws JMSException {

        String cola = " notificaciones_sensores";
        BrokerService brokerService = new BrokerService();
        try {
            brokerService.addConnector("tcp://sensor-queue-server:61616");
            brokerService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Productor().enviarMensaje(cola);
    }
}
