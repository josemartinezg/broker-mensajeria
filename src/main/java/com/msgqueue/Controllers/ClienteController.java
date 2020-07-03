package com.msgqueue.Controllers;

import com.msgqueue.Consumidor;
import com.msgqueue.Utils.WebSocketUtil;
import freemarker.template.Configuration;
import org.eclipse.jetty.websocket.api.Session;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import javax.jms.JMSException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.webSocket;
import static spark.Spark.*;

public class ClienteController {
    public static List<Session> sessions = new ArrayList<>();
    public static void main(String[] args) throws JMSException {
        Configuration configuration = new Configuration(Configuration.getVersion());
        staticFiles.location("/publico/static");
        configuration.setClassForTemplateLoading(ClienteController.class, "/publico/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);


        String cola = "notificaciones_sensores";
        webSocket("/lecturaSocket", WebSocketUtil.class);
        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "index.ftl");
        }, freeMarkerEngine);
        Consumidor consumidor = new Consumidor(cola);
        consumidor.conectar();

    }
    /*Método para enviar el mensaje a la sesión del Web Socket a ser utilizado en el cliente.*/
    public static void enviarMensaje(String mensaje) {
        for (Session sesion : sessions) {
            try {
                sesion.getRemote().sendString(mensaje);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
