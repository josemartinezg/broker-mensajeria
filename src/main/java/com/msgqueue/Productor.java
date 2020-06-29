package com.msgqueue;

import com.google.gson.Gson;
import com.msgqueue.Models.Dispositivo;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import static spark.Spark.get;

public class Productor {
    private static final String EXCHANGE_NAME = "logs";
    static Random random;
    static Gson gson = new Gson();
    static Format format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    static ConnectionFactory connectionFactory = new ConnectionFactory();

    public static void main(String[] argv) throws Exception {
        connectionFactory.setHost("localhost");
        get("/", (request, response) -> {
            enviarMensaje();
            TimeUnit.SECONDS.sleep(1);
            response.redirect("/");
            return "";
        });
    }

    public static void enviarMensaje() throws Exception{
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            random = new Random();
            int id = random.nextInt(1 + 1)  + 1;
            String mensaje = nuevoDispositivo(id);
            channel.basicPublish(EXCHANGE_NAME, "", null, mensaje.getBytes("UTF-8"));
            System.out.println("Enviado: '" + mensaje + "'");
        }
    }

    public static String nuevoDispositivo(int idDispositivo) {
        String dateSrt = format.format(new Date());
        double tempreratura = generateRandom(100, 't');
        double humedad = generateRandom(100, 'h');
        Dispositivo dispositivo = new Dispositivo(dateSrt, idDispositivo, tempreratura, humedad);
        return gson.toJson(dispositivo);
    }

    public static double generateRandom(int bound, char var) {
        random = new Random();
        switch (var) {
            case 't':
                return random.nextInt((bound - (-bound)) + 1) + (-bound);
            case 'h':
                return random.nextInt(bound) +1;
        }
        return random.nextInt(10);
    }
}
