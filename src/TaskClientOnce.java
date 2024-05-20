import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskClientOnce {
    public static void main(String arg[]) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            System.out.println("localhostの" + port + "番ポートに接続を要求します");

            Socket socket = new Socket("localhost", port);
            System.out.println("接続されました");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            System.out.println("入力値に対する最大の素数を計算します");
            System.out.println("数字を入力してください↓");
            TaskObject task = new TaskObject();
            task.setExecNumber(scanner.nextInt());
            oos.writeObject(task);
            oos.flush();
            TaskObject result = (TaskObject) ois.readObject();
            System.out.println("入力値:" + task.getInputNumber() + "に対する最大の素数は" + result.getResult());

            // close処理
            oos.close();
            ois.close();
            socket.close();
            scanner.close();
        } catch (BindException be) {
            be.printStackTrace();
            System.err.println("ポート番号が不正、ポートが使用中です");
            System.err.println("別のポート番号を指定してください(6000など)");
        } catch (InputMismatchException i) {
            System.err.println("入力は数値でお願いします");
        } catch (Exception e) {
            System.err.println("エラーが発生したのでプログラムを終了します");
            throw new RuntimeException(e);
        }
    }
}
