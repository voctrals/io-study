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
public class BufferStudy {

    @Test
    public void bufferUse4Steps() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("pom.xml", "rw");
        // 得到channel
        FileChannel inChannel = aFile.getChannel();
        // 创建buffer
        ByteBuffer buf = ByteBuffer.allocate(48);
        //写入数据到Buffer
        int bytesRead = inChannel.read(buf);
        // 返回值为读到的字节数，-1表示读完
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            //调用flip()方法
            buf.flip();

            //从Buffer中读取数据
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            //调用clear()方法或者compact()方法
            // clear清空buffer，compact只会清空已经读过的buffer内容
            buf.clear();
            // 读完了再写入
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }


    @Test
    public void desc() {
        // 缓冲区本质上是一块可以写入数据，然后可以从中读取数据的内存

        // 这个对象有三个属性
        // １，capacity，不管读写这个数值从声明就固定
        // 你只能往里写capacity个byte、long，char等类型
        // 一旦Buffer满了，需要将其清空（通过读数据或者清除数据）才能继续写数据往里写数据。
        ByteBuffer buf = ByteBuffer.allocate(48);
        // ２，position
        // write : position表示当前的位置。初始的position值为0.当一个byte、long等数据写到Buffer后， position会向前移动到下一个可插入数据的Buffer单元。position最大可为capacity-1
        // read  : position表示从某个特定位置读。当将Buffer从写模式切换到读模式，position会被重置为0. 当从Buffer的position处读取数据时，position向前移动到下一个可读的位置。

        // ３，limit
        // write : limit表示你最多能往Buffer里写多少数据。
        // read  : limit表示你最多能读到多少数据。
        System.out.println(buf.position());
        System.out.println(buf.limit());
        buf.put((byte) 1);
        buf.put((byte) 1);
        buf.put((byte) 1);
        buf.put((byte) 1);
        System.out.println(buf.position());
        System.out.println(buf.limit());
        buf.flip();
        System.out.println(buf.position());
        System.out.println(buf.limit());
    }

    @Test
    public void scatterAndGather() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("pom.xml", "rw");
        FileChannel channel = aFile.getChannel();

        // 当header满了，会继续插入数据到body这个buffer元素中
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);
        ByteBuffer[] bufferArray = {header, body};
        // read data from buffers
        channel.read(bufferArray);
        // write data into buffers
        channel.write(bufferArray);

    }
}
