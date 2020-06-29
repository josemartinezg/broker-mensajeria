package com.msgqueue;

import com.google.gson.Gson;
import com.msgqueue.Models.Dispositivo;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Consumidor {
    private static final String EXCHANGE_NAME = "logs";
    static Gson gson = new Gson();

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" Esperando mensajes... ");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String mensaje = new String(delivery.getBody(), "UTF-8");
            System.out.println(" Recibido: '" + mensaje + "'");
            Dispositivo dispositivo = gson.fromJson(mensaje, Dispositivo.class);
            System.out.println(" Recibido: '" + dispositivo.getFecha() + "'");

        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
