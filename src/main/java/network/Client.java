package network;

public class Client {
    public static void main(String [] args) throws Exception {
        Network net = new Network();
        Protocol protocol = new Protocol();
        byte[] buf = protocol.getPacket();

        Network.exit();
    }
}
