package serialize;
import java.io.Serializable;

public class Student implements Serializable{//注意：不加这句implements处理成Serializable则不满足序列化要求，进行序列化时会报错！！
    private String userName;
    private String password;
    private String year;
    public Student(String userName,String password,String year){
        this.userName=userName;
        this.password=password;
        this.year=year;
    }
    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    public String getYear(){
        return year;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setPassword(String password){
        this.password=password;
    }

}
