package serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableTest {
    public static void main(String[] args){
        try{
            //序列化
//            FileOutputStream fos=new FileOutputStream("student.out");
//            ObjectOutputStream oos=new ObjectOutputStream(fos);
//            Student student1=new Student("lane","pwd123","20");
//            oos.writeObject(student1);
//            oos.flush();
//            fos.close();

            //反序列化
            FileInputStream fis=new FileInputStream("student.out");
            ObjectInputStream ois=new ObjectInputStream(fis);
            Student student2=(Student) ois.readObject();
            System.out.println(student2.getUserName()+" "+student2.getPassword()+" "+student2.getYear());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
