package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Network {
    private Socket socket;
    private static InputStream is;
    private static OutputStream os;
    public Network() {
        try {
            socket = new Socket("localhost", 9999);
            is = socket.getInputStream();
            os = socket.getOutputStream();
            System.out.println("서버 접속 중");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void send(Protocol protocol)  {
        try {
            os.write(protocol.getPacket());
            System.out.println("서버에게" + " 전송");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static Protocol receive(int type) throws Exception {
        byte[] header = new byte[Protocol.LEN_HEADER];
        Protocol protocol = new Protocol();
        try {
            int totalReceived, readSize;
            do {
                totalReceived = 0;
                readSize = 0;
                is.read(header, 0, Protocol.LEN_HEADER);
                protocol.setPacketHeader(header);
                byte[] buf = new byte[protocol.getBodyLength()];
                while (totalReceived < protocol.getBodyLength()) {
                    readSize = is.read(buf, totalReceived, protocol.getBodyLength() - totalReceived);
                    totalReceived += readSize;
                    if (readSize == -1) {
                        throw new Exception("통신오류: 연결 끊어짐");
                    }
                }
                protocol.setPacketBody(buf);
                if (protocol.getType() == Protocol.UNDEFINED)
                    throw new Exception("통신오류: 서버에서 오류 발생함");
                else if (protocol.getType() == type)
                    return protocol;
            } while (true); // 현재 필요한 응답이 아닐경우 무시하고 다음 응답을 대기
        } catch (IOException e) {
            throw new Exception("통신오류: 데이터 수신 실패함");
        }
    }

    public static void exit() throws Exception {
        Protocol protocol = new Protocol(Protocol.EXIT);
        protocol.setBody("");
        send(protocol);
    }
}
