package View;

import lombok.NoArgsConstructor;
import persistence.DTO.MenuDTO;

import java.util.List;

@NoArgsConstructor

public class OwnerView extends View
{


    @Override
    public void viewList() {

            System.out.println("1. 메뉴 등록 신청");
            System.out.println("2. 메뉴 옵션 등록 신청");
            System.out.println("3. 음식점 영업시간 설정");
            System.out.println("4. 고객의 주문 접수 관리(승인/거절)");
            System.out.println("5. 리뷰와 별점 조회");
            System.out.println("6. 통계 정보 열람");
            System.out.println("7. 로그아웃");
            System.out.print("실행할 목록을 선택해주세요 : ");


    }

    @Override
    public int handle(int idx){
        switch(idx) {
            case 1:
                System.out.println("등록할 메뉴의 이름을 입력하세요 : ");
                String menuName = sc.next();
                // 메뉴 등록 신청
                break;
            case 2:
                // 메뉴 옵션 등록 신청
                break;
            case 3:
                // 음식점 영업시간 설정
                break;
            case 4:
                // 고객의 주문 접수 관리(승인/거절)
                break;
            case 5:
                // 리뷰 별점 조회
                System.out.print("리뷰를 등록할 주문내역을 선택하세요 : ");
                int menuPK = sc.nextInt();
                // 리뷰 답글 등록
                break;
            case 6:
                System.out.println("1. 메뉴별 전체 주문건수 열람");
                System.out.println("2. 메뉴별 전체 주문 매출 열람");
                System.out.print("조회할 목록을 선택하세요 : ");
                int choice = sc.nextInt();
                if(choice == 1) {
                    // 메뉴별 전체 주문건수 열람
                }
                else if(choice == 2) {
                    // 메뉴별 전체 주문 매출 열람
                }
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

    public void printList(List<MenuDTO> menuDTOS) {
        menuDTOS.stream().forEach(menuDTO -> {
            System.out.println(menuDTOS);
        });
    }

    public void insertResult(List<MenuDTO> rows) {
        if (rows.size() == 0) {
            System.out.println("데이터의 입력에 실패했습니다.");
        }
        else {
            System.out.println("데이터가 성공적으로 입력되었습니다.");
        }
    }


}


