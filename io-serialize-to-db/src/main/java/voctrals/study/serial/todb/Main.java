package voctrals.study.serial.todb;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import voctrals.study.serial.todb.entity.Detail;
import voctrals.study.serial.todb.entity.SerialTest;
import voctrals.study.serial.todb.helper.ByteHelper;
import voctrals.study.serial.todb.mapper.TestMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        TestMapper testMapper = session.getMapper(TestMapper.class);

        Detail detail = new Detail();
        detail.setType("try");
        detail.setSports(new String[]{"sing", "run", "dance"});
        SerialTest serialTest = new SerialTest();
        serialTest.setData(ByteHelper.objectToBytes(detail));
        serialTest.setName("test");

        testMapper.insertBlob(serialTest);
        session.commit();

        List<SerialTest> resultList = testMapper.getAllBlob();
        resultList.forEach(one -> {
            try {
                Detail aDetail = (Detail) ByteHelper.bytesToObject(one.getData());
                System.out.println(aDetail);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        session.close();
    }

}
