package network;

import persistence.DTO.*;

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
        send(protocol);//서버로 보내기

    }

    public static Protocol login(UserDTO userDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.LOGIN_REQ);
        protocol.setBody(userDTO);
        send(protocol);
        //서버가 실행
        protocol = receive(Protocol.LOGIN_RES);

        return protocol;
    }

    public static Protocol registerStore(StoreDTO storeDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.REGISTER_REQ, Protocol.STORE);
        protocol.setBody(storeDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.RES);
        if (receive.getCode() == Protocol.SUCCESS) {
            // 가게 등록
        }
        else {
            // 가게 등록 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol registerMenu(MenuDTO menuDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.REGISTER_REQ, Protocol.MENU);
        protocol.setBody(menuDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.RES);
        if (receive.getCode() == Protocol.SUCCESS) {
            // 메뉴 등록
        }
        else {
            // 메뉴 등록 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol registerMenuOption(MenuOptionDTO menuOptionDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.REGISTER_REQ, Protocol.MENU_OPTION);
        protocol.setBody(menuOptionDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.RES);
        if (receive.getCode() == Protocol.SUCCESS) {
            // 메뉴 옵션 등록
        }
        else {
            // 메뉴 옵션 등록 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol registerReviewAndRating(ReviewDTO reviewDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.REGISTER_REQ, Protocol.REVIEW_AND_RATING);
        protocol.setBody(reviewDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.RES);
        if (receive.getCode() == Protocol.SUCCESS) {
            // 리뷰와 별점 등록
        }
        else {
            // 리뷰와 별점 등록 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol registerReviewReply(ReviewDTO reviewDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.REGISTER_REQ, Protocol.REVIEW_REPLY);
        protocol.setBody(reviewDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.RES);
        if (receive.getCode() == Protocol.SUCCESS) {
            // 리뷰에 대한 답글 등록
        }
        else {
            // 리뷰에 대한 답글 등록 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol registerOrder(OrdertableDTO ordertableDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.REGISTER_REQ, Protocol.ORDER);
        protocol.setBody(ordertableDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.RES);
        if (receive.getCode() == Protocol.SUCCESS) {
            // 주문 등록
        }
        else {
            // 주문 등록 실패 메시지 보여주기
        }
        return protocol;
    }


    public static Protocol approveOrder(OrdertableDTO ordertableDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.REGISTER_REQ, Protocol.APPROVE_ORDER);
        protocol.setBody(ordertableDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.RES);
        if (receive.getCode() == Protocol.SUCCESS) {
            // 점주의 주문 승인
        }
        else {
            // 점주의 주문 승인 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol refuseOrder(OrdertableDTO ordertableDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.REGISTER_REQ, Protocol.REFUSE_ORDER);
        protocol.setBody(ordertableDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.RES);
        if (receive.getCode() == Protocol.SUCCESS) {
            // 점주의 주문 거절
        }
        else {
            // 점주의 주문 거절 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol approveStore(StoreDTO storeDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.REGISTER_REQ, Protocol.APPROVE_STORE);
        protocol.setBody(storeDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.RES);
        if (receive.getCode() == Protocol.SUCCESS) {
            // 관리자의 가게 등록 승인
        }
        else {
            // 관리자의 가게 등록 승인 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol refuseStore(StoreDTO storeDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.REGISTER_REQ, Protocol.REFUSE_STORE);
        protocol.setBody(storeDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.RES);
        if (receive.getCode() == Protocol.SUCCESS) {
            // 관리자의 가게 등록 거절
        }
        else {
            // 관리자의 가게 등록 거절 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol approveMenu(MenuDTO menuDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.REGISTER_REQ, Protocol.APPROVE_MENU);
        protocol.setBody(menuDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.RES);
        if (receive.getCode() == Protocol.SUCCESS) {
            // 관리자의 메뉴 등록 승인
        }
        else {
            // 관리자의 메뉴 등록 승인 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol refuseMenu(MenuDTO menuDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.REGISTER_REQ, Protocol.REFUSE_MENU);
        protocol.setBody(menuDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.RES);
        if (receive.getCode() == Protocol.SUCCESS) {
            // 관리자의 메뉴 등록 거절
        }
        else {
            // 관리자의 메뉴 등록 거절 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol approveOwner(UserDTO userDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.REGISTER_REQ, Protocol.APPROVE_OWNER);
        protocol.setBody(userDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.RES);
        if (receive.getCode() == Protocol.SUCCESS) {
            // 관리자의 점주 등록 승인
        }
        else {
            // 관리자의 점주 등록 승인 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol refuseOwner(UserDTO userDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.REGISTER_REQ, Protocol.REFUSE_OWNER);
        protocol.setBody(userDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.RES);
        if (receive.getCode() == Protocol.SUCCESS) {
            // 관리자의 점주 등록 거절
        }
        else {
            // 관리자의 점주 등록 거절 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol signUp(UserDTO userDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.REGISTER_REQ, Protocol.SIGN_UP);
        protocol.setBody(userDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.RES);
        if (receive.getCode() == Protocol.SUCCESS) {
            // 회원가입
        }
        else {
            // 회원가입 실패 메시지 보여주기
        }
        return protocol;
    }

    // 조회 요청
    public static Protocol inquiryOrderCntPerMenu(OrdertableDTO ordertableDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.INQUIRY_RES, Protocol.ORDERCNT_PER_MENU);
        protocol.setBody(ordertableDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.INQUIRY_RES);
        if (receive.getCode() == Protocol.ORDERCNT_PER_MENU) {
            // 메뉴 별 주문건수 조회 응답
        }
        else {
            // 메뉴 별 주문건수 조회 응답 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol inquirySalesPerMenu(OrdertableDTO ordertableDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.INQUIRY_RES, Protocol.SALES_PER_MENU);
        protocol.setBody(ordertableDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.INQUIRY_RES);
        if (receive.getCode() == Protocol.SALES_PER_MENU) {
            // 메뉴 별 매출 조회 응답
        }
        else {
            // 메뉴 별 매출 조회 응답 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol inquiryStore(StoreDTO storeDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.INQUIRY_RES, Protocol.STORE);
        protocol.setBody(storeDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.INQUIRY_RES);
        if (receive.getCode() == Protocol.STORE) {
            // 가게 조회 응답
        }
        else {
            // 가게 조회 응답 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol inquiryOrder(OrdertableDTO ordertableDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.INQUIRY_RES, Protocol.ORDER);
        protocol.setBody(ordertableDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.INQUIRY_RES);
        if (receive.getCode() == Protocol.ORDER) {
            // 주문 조회(고객) 응답
        }
        else {
            // 주문 조회(고객) 응답 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol inquiryOrderInfo(OrdertableDTO ordertableDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.INQUIRY_RES, Protocol.ORDER_INFO);
        protocol.setBody(ordertableDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.INQUIRY_RES);
        if (receive.getCode() == Protocol.ORDER_INFO) {
            // 주문 정보(점주) 조회 응답
        }
        else {
            // 주문 정보(점주) 조회 응답 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol inquiryReviewAndRating(ReviewDTO reviewDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.INQUIRY_RES, Protocol.REVIEW_AND_RATING);
        protocol.setBody(reviewDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.INQUIRY_RES);
        if (receive.getCode() == Protocol.REVIEW_AND_RATING) {
            // 리뷰 및 별점 조회 응답
        }
        else {
            // 리뷰 및 별점 조회 응답 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol inquiryOrderCntPerStore(OrdertableDTO ordertableDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.INQUIRY_RES, Protocol.ORDERCNT_PER_STORE);
        protocol.setBody(ordertableDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.INQUIRY_RES);
        if (receive.getCode() == Protocol.ORDERCNT_PER_STORE) {
            // 가게별 주문건수 조회 응답
        }
        else {
            // 가게별 주문건수 조회 응답 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol inquirySalesPerStore(OrdertableDTO ordertableDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.INQUIRY_RES, Protocol.SALES_PER_STORE);
        protocol.setBody(ordertableDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.INQUIRY_RES);
        if (receive.getCode() == Protocol.SALES_PER_STORE) {
            // 가게별 매출 조회 응답
        }
        else {
            // 가게별 매출 조회 응답 실패 메시지 보여주기
        }
        return protocol;
    }

    // 설정
    public static Protocol setBusinessHours(StoreDTO storeDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.SETTING_REQ, Protocol.BUSINESS_HOURS);
        protocol.setBody(storeDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.RES);
        if (receive.getCode() == Protocol.BUSINESS_HOURS) {
            // 운영 시간 설정
        }
        else {
            // 운영 시간 설정 실패 메시지 보여주기
        }
        return protocol;
    }

    // 수정
    public static Protocol updatePrivacy(UserDTO userDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.SETTING_REQ, Protocol.PRIVACY);
        protocol.setBody(userDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.RES);
        if (receive.getCode() == Protocol.SUCCESS) {
            // 개인정보 수정 성공
        }
        else {
            // 개인정보 수정 실패 메시지 보여주기
        }
        return protocol;
    }

    public static Protocol updatePassWord(UserDTO userDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.SETTING_REQ, Protocol.PASSWORD);
        protocol.setBody(userDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.RES);
        if (receive.getCode() == Protocol.SUCCESS) {
            // 비밀번호 수정 성공
        }
        else {
            // 비밀번호 수정 실패 메시지 보여주기
        }
        return protocol;
    }

    // 취소
    public static Protocol cancelOrder(OrdertableDTO ordertableDTO) throws Exception {
        Protocol protocol = new Protocol(Protocol.CANCEL_REQ, Protocol.ORDER);
        protocol.setBody(ordertableDTO);
        send(protocol);
        //서버가 실행
        Protocol receive = receive(Protocol.RES);
        if (receive.getCode() == Protocol.SUCCESS) {
            // 주문 취소 성공
        }
        else {
            // 주문 취소 실패 메시지 보여주기
        }
        return protocol;
    }

}
