package com.msgqueue.Controllers;

import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    public static List<Session> sessions = new ArrayList<>();
    public static void main(String[] args){

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
