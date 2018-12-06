package study.voctrals.netty.basic.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;

/**
 * @author lei.liu
 * @since 18-12-5
 */
public class NettyClient {


    private final String host;
    private final int port;

    public NettyClient(String host, int port) {
        this.port = port;
        this.host = host;
    }

    public void start() throws InterruptedException, IOException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new NettyClientFilter());
        // 连接服务端
        Channel ch = bootstrap.connect(host, port).sync().channel();
        sendData(ch);
    }

    public static void sendData(Channel ch) throws IOException {
        String str = "Hello Netty";
        ch.writeAndFlush(str + "\r\n");
        System.out.println("sending data:" + str);
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        new NettyClient("localhost", 5555).start();
    }
}
