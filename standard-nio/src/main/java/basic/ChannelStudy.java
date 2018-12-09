package basic;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lei.liu
 * @since 18-12-9
 */
public class ChannelStudy {

    @Test
    public void channel() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("pom.xml", "rw");
        // 得到channel
        FileChannel inChannel = aFile.getChannel();
        // 创建buffer
        ByteBuffer buf = ByteBuffer.allocate(48);
        // 读内容到buffer
        int bytesRead = inChannel.read(buf);
        // 返回值为读到的字节数，-1表示读完
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            // 反转buf write -> read
            buf.flip();

            // 一个字节一个字节地读
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            // 清除buf中的数据
            buf.clear();
            // 读完了再写入
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

}
