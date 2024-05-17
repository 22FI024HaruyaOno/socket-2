import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CalcNumberClient {
    public static void main(String arg[]) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            System.out.println("localhostの" + port + "番ポートに接続を要求します");
            boolean isNext = true;
            while (isNext) {
                Socket socket = new Socket("localhost", port);
                System.out.println("接続されました");
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                CalcNumber calc = new CalcNumber();
                System.out.println("入力値に対する最大の素数を計算します");
                System.out.println("数字を入力してください(終了する場合→1以下の数字) ↓");
                int setNum = scanner.nextInt();
                calc.setExecNumber(setNum);
                if (calc.getInputNumber() <= 1) {
                    calc.setIsClose(true);
                    oos.writeObject(calc);
                    oos.flush();
                    System.out.println("終了します");
                    isNext = false;
                } else {
                    calc.setIsClose(false);
                    oos.writeObject(calc);
                    oos.flush();
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    CalcNumber result = (CalcNumber)ois.readObject();
                    System.out.println("入力値:" + calc.getInputNumber() + "に対する最大の素数は" + result.getResult());
                    System.out.println("----------------------------------------");
                    ois.close();
                }
                socket.close();
            }
            scanner.close();
        }
        // エラーが発生したらエラーメッセージを表示してプログラムを終了する
        catch (BindException be) {
            be.printStackTrace();
            System.err.println("ポート番号が不正か、サーバが起動していません");
            System.err.println("サーバが起動しているか確認してください");
            System.err.println("別のポート番号を指定してください(6000など)");
        } catch (InputMismatchException i) {
            System.err.println("入力は数値でお願いします");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}