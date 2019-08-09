package com.demo.telnet;

import com.demo.CheckException;
import com.demo.MmlException;
import com.demo.entity.Server;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Telnet {
    private TelnetClient client;
    private InputStream in;
    private OutputStream out;

    public Telnet(String ip, int port, String username, String password) throws CheckException, MmlException {
        client = new TelnetClient();
        try {
            client.connect(ip, port);
        } catch (IOException e) {
            throw new CheckException(1, "connect failure");
        }
        in = client.getInputStream();
        out = client.getOutputStream();
        login(username, password);
    }

    public Telnet(Server server) throws CheckException, MmlException {
        this(server.getIp(), Integer.valueOf(server.getPort()), server.getUsername(), server.getPassword());
    }

    private void login(String username, String password) throws MmlException {
        readUntil("username:");
        write(username);
        readUntil("password:");
        write(password);
        readUntil("$>");

        String result = sendCommand("set:format=ndfmml");
        log.info("result: " + result);
        log.info("------------------------");
    }

    private String sendCommand(String command) {
        try {
            write(" " + command);
            return readUntil("$>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String readUntil(String endFlag) {
        StringBuilder sb = new StringBuilder();
        byte[] buff = new byte[2048];
        int readBytes = 0;
        try {
            while ((readBytes = in.read(buff)) != 0) {
                String str = new String(buff, 0, readBytes);
                log.info("readBytes: " + readBytes);
                log.info("str: " + str);
                if (str.endsWith(endFlag)) {
                    sb.append(str);
                    return sb.toString();
                }
                sb.append(str);
                TimeUnit.SECONDS.sleep(1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void write(String value) throws MmlException {
        try {
            out.write((value + "\r\n").getBytes());
            out.flush();
        } catch (Exception e) {
            throw new MmlException();
        }
    }

    public Map<String, String> sendCmd(MmlExecutor mmls) throws MmlException {
        String result = sendCommand(mmls.getMml());
        log.info("mml result: " + result);
        return mmls.parseResult(result);
    }

    public void disconnect() {
        try {
            if (client != null)
                client.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
