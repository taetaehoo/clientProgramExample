package network;

import View.*;

public class Client {
    static View view;
    public static void main(String [] args) throws Exception {
        Network net = new Network();
        Protocol protocol = new Protocol();
        byte[] buf = protocol.getPacket();

        LoginView loginView = new LoginView();

        view = loginView;

        while(true) {
            view.viewList();

            int sel = view.selMenu();;

            int result = view.handle(sel);
            System.out.println("result = " + result);
            setView(result);
        }
    }

    private static void setView(int result) {
        if (result == 1) {
            view = new CustomerView();
            System.out.println("고객 젼환");
        }
        else if(result == 2) {
            view = new OwnerView();
            System.out.println("점주 전환");
        } else if (result == 3) {
            view = new ManagerView();
            System.out.println("관리자 전환");
        }
        else if (result == 4){
            view = new LoginView();
            System.out.println("로그인 전환 없음");
        }
    }
}
