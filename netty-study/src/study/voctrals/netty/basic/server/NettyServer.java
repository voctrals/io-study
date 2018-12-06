package study.voctrals.netty.basic.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author lei.liu
 * @since 18-12-5
 */
public class NettyServer {


    private int port;

    public NettyServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioServerSocketChannel.class);
            //设置过滤器
            bootstrap.childHandler(new NettyServerFilter());
            // 服务器绑定端口监听
            ChannelFuture f = bootstrap.bind(port).sync();
            System.out.println(NettyServer.class.getName() + "started and listen on" + f.channel().localAddress());
            // 监听服务器关闭监听
            f.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new NettyServer(5555).start();
    }

}
