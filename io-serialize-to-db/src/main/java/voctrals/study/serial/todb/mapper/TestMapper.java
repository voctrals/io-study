package voctrals.study.serial.todb.mapper;

import voctrals.study.serial.todb.entity.SerialTest;

import java.util.List;

public interface TestMapper {
    void insertBlob(SerialTest serialTest);

    List<SerialTest> getAllBlob();
}
