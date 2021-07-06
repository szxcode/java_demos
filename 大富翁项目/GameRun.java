import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class GameRun {
    Operation operation = new Operation();

    /**
     * 运行游戏
     */
    public void run() {
        while (true) {
            System.out.println("********************欢迎进入大富翁抽奖系统********************");
            System.out.println("                          1、注册");
            System.out.println("                          2、登陆");
            System.out.println("                          3、抽奖");
            System.out.println("                          4、查询");
            System.out.println("                          5、修改密码");
            System.out.println("                          6、删除账号");
            System.out.println("                          其他数字退出系统");
            System.out.println("**********************************************************");
            int menuId = menuSelect();
            switch (menuId) {
                case 1 -> menuRegister();
                case 2 -> menuLogin();
                case 3 -> operation.comparedLuckyNumber();
                case 4 -> menuInquire();
                case 5 -> menuChangePassword();
                case 6 -> menuDeleteUser();
            }
        }
    }

    /**
     * 用户注册
     */
    private void menuRegister() {
        boolean isEnd = false;
        while (!isEnd) {
            System.out.println("大富翁系统-->注册");
            String name = getEnter("账号");
            String password = getEnter("密码");
            int index = operation.register(name, password);
            if (index > -1) {
                System.out.println("注册成功！");
                operation.printUserInfo(index);
                System.out.println("还需要继续注册吗？（y/n) ");
                Scanner console = new Scanner(System.in);
                if (!console.nextLine().toUpperCase(Locale.ROOT).equals("Y")) {
                    isEnd = true;
                }
            } else {
                System.out.println("账号已存在，请重新输入！");
            }
        }
    }

    /**
     * 用户登陆
     */
    private void menuLogin() {
        boolean isEnd = false;
        while (!isEnd) {
            System.out.println("大富翁系统-->登陆");
            String name = getEnter("账号");
            String password = getEnter("密码");
            User user = operation.login(name, password);
            if (user != null) {
                System.out.println("登陆成功,欢迎用户" + user.getName());
                isEnd = true;
            } else {
                System.out.println("登陆失败，请重新输入！");
            }
        }
    }

    /**
     * 用户查询
     */
    private void menuInquire() {
        System.out.println("大富翁系统-->查询");
        operation.printUserListInfo();
    }

    /**
     * 用户修改密码
     */
    private void menuChangePassword() {
        boolean isEnd = false;
        while (!isEnd) {
            System.out.println("大富翁系统-->修改密码");
            String name = getEnter("账号");
            String oldPassword = getEnter("密码");
            int index = operation.matchUserIndex(name, oldPassword);
            if (index == -1) {
                System.out.println("你输入的账号密码不匹配，请重新输入！");
            } else {
                boolean isPasswordOk = false;
                String newPassword1 = "";
                while (!isPasswordOk) {
                    newPassword1 = getEnter("新密码");
                    System.out.print("请再次输入新密码: ");
                    Scanner console = new Scanner(System.in);
                    String newPassword2 = console.nextLine();
                    if (newPassword1.equals(newPassword2)) {
                        isPasswordOk = true;
                    } else {
                        System.out.println("新密码不一致，请重新输入！");
                    }
                }
                if (operation.changePassword(name, oldPassword, newPassword1)) {
                    System.out.println("修改密码成功！");
                }
            }
            System.out.println("还需要继续修改密码吗？（y/n) ");
            Scanner console = new Scanner(System.in);
            if (!console.nextLine().toUpperCase(Locale.ROOT).equals("Y")) {
                isEnd = true;
            }
        }
    }

    /**
     * 删除用户
     */
    private void menuDeleteUser() {
        boolean isEnd = false;
        while (!isEnd) {
            System.out.println("大富翁系统-->删除用户");
            String name = getEnter("账号");
            String password = getEnter("密码");
            int index = operation.matchUserIndex(name, password);
            if (index == -1) {
                System.out.println("你输入的账号密码不匹配，请重新输入！");
            } else {
                if (operation.deleteUser(name, password)) {
                    System.out.println("删除用户成功！");
                }
            }
            System.out.println("还需要继续删除用户吗？（y/n) ");
            Scanner console = new Scanner(System.in);
            if (!console.nextLine().toUpperCase(Locale.ROOT).equals("Y")) {
                isEnd = true;
            }
        }
    }

    /**
     * 菜单选择
     */
    private String getEnter(String name) {
        System.out.print("请输入" + name + ": ");
        Scanner console = new Scanner(System.in);
        return console.nextLine();
    }

    /**
     * 菜单选择
     */
    private int menuSelect() {
        System.out.print("请输入你的选择: ");
        Scanner console = new Scanner(System.in);
        while (true) {
            try {
                int menu = console.nextInt();
                if (menu == 1 || menu == 2 || menu == 3 || menu == 4 || menu == 5 || menu == 6) {
                    return menu;
                } else {
                    System.out.println("程序结束");
                    System.exit(0);
                }
            } catch (InputMismatchException e) {
                System.out.println("请输入整数型！");
            }
        }
    }
}
