package View;
import java.util.InputMismatchException;
import java.util.Scanner;
public abstract class View {

    Scanner sc = new Scanner(System.in);

    public abstract void viewList();
    public abstract int handle(int idx) throws Exception;

    public int selMenu() {
        int sel = 0;
        while (true) {
            try {
                sel = sc.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("잘못된 입력 형식입니다.");
                sc.next();
                continue;
            }
            return sel;
        }
    }



}
