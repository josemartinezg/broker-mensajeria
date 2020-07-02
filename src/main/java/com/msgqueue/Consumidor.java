package com.msgqueue;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import com.google.gson.Gson;
import com.msgqueue.Models.Dispositivo;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static spark.Spark.staticFiles;
import static spark.Spark.get;
import static spark.Spark.*;

public class Consumidor {
    private static final String EXCHANGE_NAME = "logs";
    static Gson gson = new Gson();

    public static void main(String[] argv) throws Exception {
        port(8080);
        Configuration configuration = new Configuration(Configuration.getVersion());
        staticFiles.location("/publico/dist");
        configuration.setClassForTemplateLoading(Consumidor.class, "/publico/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);
        Map<String, Object> attributes = new HashMap<>();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" Esperando mensajes... ");

        get("/charts", (request, response) -> {
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String mensaje = new String(delivery.getBody(), "UTF-8");
                System.out.println(" Recibido: '" + mensaje + "'");
                Dispositivo dispositivo = gson.fromJson(mensaje, Dispositivo.class);
                System.out.println(" Recibido: '" + dispositivo.getFecha() + "'");
                attributes.put("dispositivo", dispositivo);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                response.redirect("/charts");
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
            return new ModelAndView(attributes, "charts.ftl");
        }, freeMarkerEngine);
    }
}
