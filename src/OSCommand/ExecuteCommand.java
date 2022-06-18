package OSCommand;
import java.io.IOException;

public class ExecuteCommand {
    public static void main(String args[]){
        //使用Runtime类的exec方法
        try{
            Process process1=Runtime.getRuntime().exec("open -a Calculator");
        }catch(IOException e){
            e.printStackTrace();
        }
        //使用ProcessBuilder类的command( )方法
//        ProcessBuilder processBuilder=new ProcessBuilder();
//        processBuilder.command("open","/Applications/Calculator.app");
//        try{
//            Process process2=processBuilder.start();
//        }catch(IOException e){
//            e.printStackTrace();
//        }
    }
}
