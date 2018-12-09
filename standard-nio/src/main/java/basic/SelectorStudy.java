package basic;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author lei.liu
 * @since 18-12-9
 */
public class SelectorStudy {

    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();

        //2. 打开一个ServerSocketChannel
        //其实我们没监听一个端口就需要一个channel
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);//设置为非阻塞
        ServerSocket ss = ssc.socket();
        InetSocketAddress address = new InetSocketAddress(9091);
        ss.bind(address);//监听一个端口
        //3. 注册到selector
        //register的第一个参数永远都是selector
        //第二个参数是我们要监听的事件
        //OP_ACCEPT是新建立连接的事件
        //也是适用于ServerSocketChannel的唯一事件类型
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Going to listen on " + 9091);

        while (true) {
            // 阻塞线程，直到有事件
            int readyChannels = selector.select();
            if (readyChannels == 0) continue;

            // 返回发生了事件的 SelectionKey 对象的一个集合
            Set selectedKeys = selector.selectedKeys();
            Iterator keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = (SelectionKey) keyIterator.next();
                if (key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.
                    System.out.println("isAcceptable");
                } else if (key.isConnectable()) {
                    // a connection was established with a remote server.
                    System.out.println("isConnectable");
                } else if (key.isReadable()) {
                    // a channel is ready for reading
                    System.out.println("isReadable");
                } else if (key.isWritable()) {
                    // a channel is ready for writing
                    System.out.println("isWritable");
                }
                keyIterator.remove();
            }
        }
    }

}
