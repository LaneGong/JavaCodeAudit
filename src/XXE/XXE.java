package XXE;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class XXE {
    public static void main(String[] args) throws ParserConfigurationException,IOException, SAXException{
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();//创建DOM解析的工厂
        DocumentBuilder builder=factory.newDocumentBuilder();//DOM解析器对象
        File f=new File("test.xml");//把要解析的xml文档转化为输入流，以便DOM解析器解析它
        Document doc=builder.parse(f);//解析XML文档，得到代表整个文档的Document对象
        Element root=doc.getDocumentElement();//得到XML文档的根结点
        System.out.println("***"+root.getElementsByTagName("name").item(0).getFirstChild().getNodeValue()+"***");//获取字节点
    }
}
