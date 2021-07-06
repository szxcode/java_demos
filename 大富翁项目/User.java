public class User {
    //账号
    private String name;
    //密码
    private String password;
    //会员号
    private String nums;

    public User() {
    }

    public User(String name, String password,String nums) {
        this.name = name;
        this.password = password;
        this.nums = nums;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public void print() {
        System.out.println("用户名    密码    会员号");
        System.out.printf("%s    %s    %s", name, password, nums);
        System.out.println();
    }

    @Override
    public String toString() {
        return "name:" + name + ", password:" + password + ", nums:" + nums;
    }
}
