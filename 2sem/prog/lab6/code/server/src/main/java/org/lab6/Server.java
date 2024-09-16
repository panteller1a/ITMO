package org.lab6;

import common.utils.ArgumentType;
import common.utils.Command;
import org.apache.logging.log4j.Logger;
import common.transfer.Request;
import common.transfer.Response;
import org.lab6.commands.Invoker;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private final DatagramSocket serverSocket;
    private final Invoker invoker;
    private final Logger logger = ServerMain.logger;
    private final byte[] buffer = new byte[4096];
    private boolean running = true;

    public Server(int port, Invoker invoker) throws IOException {
        this.serverSocket = new DatagramSocket(port);
        this.invoker = invoker;
    }

    public void run() {
        logger.info("UDP Server started on port " + serverSocket.getLocalPort());

        while (running) {
            try {
                handleRequest();
            } catch (SocketTimeoutException e) {
                logger.info("No client request, proceeding to server-local command execution");
                executeServerCommand();
            } catch (IOException e) {
                logger.error("Error in server execution: ", e);
            }
        }
        stop();
    }

    private void handleRequest() throws IOException {
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        serverSocket.receive(packet);

        InetAddress clientAddress = packet.getAddress();
        int clientPort = packet.getPort();

        serverSocket.setSoTimeout(8000);

        try {
            serverSocket.receive(packet);
        } catch (SocketTimeoutException e) {
            throw e;
        }

        Request request = deserializeRequest(packet.getData());
        if (request == null) {
            return;
        }
        logger.info("Received request from client: " + request);

        sendResponse(clientAddress, clientPort, new Response(Response.ResponseType.ACK, true, "ACK"));

        Response response;
        if (request.getRequestType() != Request.RequestType.LOCAL) {
            response = invoker.handle(request);
        } else {
            response = new Response(Response.ResponseType.DEFAULT, false, "Invalid request type");
        }

        sendResponse(clientAddress, clientPort, response);
    }

    private void executeServerCommand() {
        try {
            if (System.in.available() > 0) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String[] inputLine = bufferedReader.readLine().trim().split(" ", 2);

                Command command = invoker.getManager().getCommands().get(inputLine[0]);
                if (command == null) {
                    System.out.println("No such command: " + inputLine[0]);
                } else {
                    Map<ArgumentType, Object> args = new HashMap<>();
                    Request request = new Request(Request.RequestType.LOCAL, command, args);
                    Response response = invoker.handle(request);

                    System.out.println(response.getMessage());
                }
            }
        } catch (IOException e) {
            logger.error("Failed to read command from server", e);
        }
    }

    private Request deserializeRequest(byte[] data) {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        try (ObjectInputStream ois = new ObjectInputStream(bis)) {
            return (Request) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Failed to deserialize request", e);
            return null;
        }
    }

    private void sendResponse(InetAddress address, int port, Response response) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(response);
        }

        byte[] responseBytes = bos.toByteArray();
        DatagramPacket packet = new DatagramPacket(responseBytes, responseBytes.length, address, port);
        serverSocket.send(packet);
        logger.info("Sent response to client: " + response);
    }

    public void stop() {
        running = false;
        serverSocket.close();
        logger.info("Server stopped.");
    }
}