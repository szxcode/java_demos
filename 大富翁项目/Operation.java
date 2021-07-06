public class Operation {
    private User[] user = null;//存储用户对象的数组
    private int count = 0;//记录数组中元素的个数，并且可以作为下标遍历数组
    private String luckyNumber = "";//今日幸运号
    private int loginIndex = -1;//记录最后登录成功的用户下标

    public Operation() {
        this(10);
    }

    /**
     * 创建一个构造方法，传入一个数组的大小，user = new User[size];
     * 如果没有传入数，或者传入数小于0，则给他一个默认大小10
     *
     * @param size 大小
     */
    public Operation(int size) {
        if (size < 0)
            size = 10;
        luckyNumber = generateLuckyNumber();
        user = new User[size];
    }

    /**
     * 匹配用户
     *
     * @param userName     用户名
     * @param userPassword 密码
     * @return
     */
    public int matchUserIndex(String userName, String userPassword) {
        for (int i = 0; i < count; i++) {
            if (userName.equals(user[i].getName()) && userPassword.equals(user[i].getPassword())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 注册的方法：
     */
    public int register(String userName, String userPassword) {
        //先判断元素个数，如果数组满了，就新建一个更大的数组，把数据拷贝过来即可
        if (count == user.length) {
            user = java.util.Arrays.copyOf(user, user.length + 10);
        }
        //传入一个用户，遍历用户数组，查看已有用户的账号是否存在，如果存在，给出提示，重新输入
        for (int i = 0; i < count; i++) {
            if (userName.equals(user[i].getName())) {
                return -1;
            }
        }
        //如果不存在，则存储起来，count++;
        user[count] = new User(userName, userPassword, generateLuckyNumber());
        count++;
        return count - 1;
    }

    /**
     * 登陆的方法：
     */
    public User login(String userName, String userPassword) {
        //传入一个用户，或者转入账号密码；遍历数组，如果匹配到账号，查看密码相同，则返回boolean值 true
        int index = matchUserIndex(userName, userPassword);
        if (index > -1) {
            loginIndex = index;
            return user[index];
        }
        return null;
    }

    /**
     * 更改密码
     *
     * @param userName        用户名
     * @param userOldPassword 旧密码
     * @param userNewPassword 新密码
     * @return
     */
    public boolean changePassword(String userName, String userOldPassword, String userNewPassword) {
        int index = matchUserIndex(userName, userOldPassword);
        if (index > -1) {
            user[index].setPassword(userNewPassword);
            return true;
        }
        return false;
    }

    /**
     * 删除用户
     *
     * @param userName
     * @param userPassword
     * @return
     */
    public boolean deleteUser(String userName, String userPassword) {
        int index = matchUserIndex(userName, userPassword);
        if (index > -1) {
            for (int i = index; i < count; i++) {
                user[index] = user[index + 1];//把删除位置以后的元素 往前挪一位
            }
            user[count - 1] = null; //把最后的元素释放
            count--;//数量减1
            return true;
        }
        return false;
    }

    /**
     * 抽奖
     *
     * @return
     */
    public void comparedLuckyNumber() {
        if (loginIndex > -1) {
            User u = user[loginIndex];
            System.out.printf("今天的幸运数字为:%s,你的会员号为:%s：,今天%s是你的幸运日！", luckyNumber, u.getNums(),
                    u.getNums().equals(luckyNumber) ? "" : "不");
            System.out.println();
        } else {
            System.out.println("请先登录！");
        }

    }


    /**
     * 打印指定用户信息
     *
     * @param index
     */
    public void printUserInfo(int index) {
        user[index].print();
    }

    /**
     * 打印全部用户信息
     */
    public void printUserListInfo() {
        for (int i = 0; i < count; i++) {
            System.out.println(user[i].toString());
        }
    }

    /**
     * 生成幸运号
     *
     * @return
     */
    public String generateLuckyNumber() {
        return Math.round((Math.random() + 1) * 1000) + "";
    }

}
