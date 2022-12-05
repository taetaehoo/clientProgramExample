package View;

import lombok.NoArgsConstructor;

@NoArgsConstructor

public class ManagerView extends View
{



    @Override
    public void viewList() {
        String role;
        int input;


            System.out.println("1. 점주의 가입 승인/거절");
            System.out.println("2. 가게 등록 신청 승인/거절");
            System.out.println("3. 메뉴 등록 신청 승인/거절");
            System.out.println("4. 통계정보 열람");
            System.out.println("5. 로그아웃");
            System.out.print("실행할 목록을 선택해주세요 : ");

    }

    @Override
    public int handle(int idx) {
        int choice;

        switch(idx) {
            case 1:
                // 점주의 신청 리스트 확인
                System.out.print("점주를 선택해주세요 : ");
                int ownerPK = sc.nextInt();
                System.out.println("1. 점주 가입 승인");
                System.out.println("2. 점주 가입 거절");
                System.out.print("점주 가입 여부를 선택해주세요 : ");
                choice = sc.nextInt();
                if(choice == 1) {
                    // 점주 가입 승인
                }
                else if(choice == 2) {
                    // 점주 가입 거절
                }
                break;
            case 2:
                // 가게의 신청 리스트 확인
                System.out.print("가게를 선택해주세요 : ");
                int storePK = sc.nextInt();
                System.out.println("1. 가게 등록 승인");
                System.out.println("2. 가게 등록 거절");
                System.out.print("가게 등록 여부를 선택해주세요 : ");
                choice = sc.nextInt();
                if(choice == 1) {
                    // 가게 등록 승인
                }
                else if(choice == 2) {
                    // 가게 등록 거절
                }
                break;
            case 3:
                // 메뉴의 신청 리스트 확인
                System.out.print("메뉴를 선택해주세요 : ");
                int menuPK = sc.nextInt();
                System.out.println("1. 메뉴 등록 승인");
                System.out.println("2. 메뉴 등록 거절");
                System.out.print("메뉴 등록 여부를 선택해주세요 : ");
                choice = sc.nextInt();
                if(choice == 1) {
                    // 메뉴 등록 승인
                }
                else if(choice == 2) {
                    // 메뉴 등록 거절
                }
                break;
            case 4:
                System.out.println("1. 음식점별 전체 주문건수 열람");
                System.out.println("2. 음식점별 전체 주문 매출 열람");
                System.out.print("조회할 목록을 선택하세요 : ");
                choice = sc.nextInt();
                if(choice == 1) {
                    // 음식점별 전체 주문건수 열람
                }
                else if(choice == 2) {
                    // 음식점별 전체 주문 매출 열람
                }
                break;
            case 5:
                System.out.println("로그아웃합니다.");
                return 4;

            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
        return 0;
    }
}
