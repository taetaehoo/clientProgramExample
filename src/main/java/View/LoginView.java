package View;

import network.Network;
import network.Protocol;
import persistence.DTO.UserDTO;


public class LoginView extends View {



    /*
        public void printAll(List<UserDTO> dtos)
    {
        System.out.println("모든 사용자");
        for(UserDTO dto:dtos)
        {
            System.out.println("dto.toString()=" + dto.toString());
        }
    }
     */
    
    @Override
    public void viewList() {
        String role;
        int input;
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 종료");
            System.out.print("메뉴를 선택해주세요 : ");
    }

    @Override
    public int handle(int idx) throws Exception {
        switch (idx) {
            case 1:
                UserDTO loginInfo = login();
                System.out.println(loginInfo.getRole());
                if (loginInfo.getRole().equals("고객")) {
                    return 1;
                }
                else if (loginInfo.getRole().equals("점주")) {
                    return 2;
                }
                else if (loginInfo.getRole().equals("관리자")) {
                    return 3;
                }
                else {
                    System.out.println("로그인에 실패하였습니다.");
                    return 0;
                }

            case 2:
                //signUp();
                break;
            case 3:
                System.out.println("종료합니다.");
                Network.exit();
                System.exit(0);
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
        return 0;
    }


    public UserDTO login() {
        String id, pw;
        System.out.print("id 입력: ");
        id = sc.next();

        System.out.print("password 입력: ");
        pw = sc.next();

        int result;

        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setPw(pw);
        System.out.println(userDTO);
        try {
            Protocol p = Network.login(userDTO);
            System.out.println("통신 완료");
            if (p.getCode() == Protocol.SUCCESS) {
                UserDTO loginInfo = (UserDTO) p.getBody();

                return loginInfo;
            }
            else {
                return new UserDTO();
            }
        } catch (Exception e) {
            result = Protocol.FAIL;
        }
        return new UserDTO();
    }

    public int signUp() {
        String id, pw;
        System.out.print("id 입력: ");
        id = sc.nextLine();

        System.out.print("password 입력: ");
        pw = sc.nextLine();

        int result;

        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setPw(pw);
        try {
            result = Network.signUp(userDTO).getCode();
        } catch (Exception e) {
            result = Protocol.FAIL;
        }
        return result;
    }

    public void exit() {
        try {
            Network.exit();
        } catch (Exception e) {
            System.out.println("오류 발생");
        }
    }

}
