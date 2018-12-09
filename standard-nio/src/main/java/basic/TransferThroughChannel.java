package basic;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author lei.liu
 * @since 18-12-9
 */
public class TransferThroughChannel {

    @Test
    public void transferFrom() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();
        toChannel.transferFrom(fromChannel, position, count);
    }

    @Test
    public void transferTo() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();
        toChannel.transferTo(position, count, fromChannel);
    }
}
