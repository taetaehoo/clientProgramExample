package View;

import lombok.NoArgsConstructor;
import network.Network;
import network.Protocol;
import persistence.DTO.*;

import java.util.List;

@NoArgsConstructor

public class CustomerView extends View {


    @Override
    public void viewList() {
        int input;


            System.out.println("1. 개인 정보 및 비밀번호 수정");
            System.out.println("2. 음식점 조회");
            System.out.println("3. 음식 주문");
            System.out.println("4. 주문 취소");
            System.out.println("5. 주문 내역 조회");
            System.out.println("6. 리뷰와 별점 등록");
            System.out.println("7. 로그아웃");
            System.out.print("실행할 목록을 선택해주세요 : ");
    }

    @Override
    public int handle(int idx) {
        int choice;

        switch(idx) {
            case 1:
                // 개인 정보 조회(넣을지 말지 고민해봐야할듯!)
                System.out.println("1. 개인 정보 수정");
                System.out.println("2. 비밀번호 수정");
                System.out.print("수정할 목록을 선택해주세요 : ");
                choice = sc.nextInt();
                if(choice == 1) {
                    // 개인 정보 수정
                }
                else if(choice == 2) {
                    // 비밀번호 수정
                }
                break;
            case 2:
                // 음식점 조회
                break;
            case 3:
                order(); // 음식 주문
                break;
            case 4:
                // 주문 내역 조회
                System.out.print("주문 취소할 목록을 입력하세요 : ");
                choice = sc.nextInt();
                // 주문 취소
                break;
            case 5:
                // 주문 내역 조회
                break;
            case 6:
                // 주문 내역 조회
                System.out.print("리뷰와 별점을 등록할 목록을 입력하세요 : ");
                choice = sc.nextInt();
                // 리뷰와 별점 등록
                break;
            case 7:
                System.out.println("로그아웃합니다.");
                return 4;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
        return 0;
    }

    public void printList(List<UserDTO> userDTOS) {
        userDTOS.stream().forEach(userDTO -> {
            System.out.println(userDTO);
        });
    }

    public void insertUser(int rows) {
        if (rows == 0) {
            System.out.println("데이터의 입력에 실패했습니다.");
        }
        else {
            System.out.println("데이터가 성공적으로 입력되었습니다.");
        }
    }

    public int order()
    {
        OrdertableDTO ordertableDTO = new OrdertableDTO();
        System.out.println("가게 번호를 입력하십시오.");
        int store_pk = sc.nextInt();
        System.out.println("사용자 번호 입력하시오.");
        int user_pk = sc.nextInt();
        System.out.println("요청사항을 입력하시오.");
        String request = sc.next();
        System.out.println("금액을 입력하시오.");
        int total = sc.nextInt();
        /*ordertableDTO.setStore_pk(store_pk);
        ordertableDTO.setUser_pk(user_pk);
        ordertableDTO.setRequest(request);
        ordertableDTO.setTotal(total);*/
        int result;

        try {
            result = Network.registerOrder(ordertableDTO).getCode();
        } catch (Exception e) {
            result = Protocol.FAIL;
        }
        return result;

    }

}
