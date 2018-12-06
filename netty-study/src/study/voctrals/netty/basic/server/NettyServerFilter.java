package study.voctrals.netty.basic.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author lei.liu
 * @since 18-12-5
 */
public class NettyServerFilter extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline ph = socketChannel.pipeline();
        // 以("\n")为结尾分割的 解码器
        ph.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        // 解码和编码，应和客户端一致
        ph.addLast("decoder", new StringDecoder());
        ph.addLast("encoder", new StringEncoder());
        ph.addLast("handler", new NettyServerHandler());// 服务端业务逻辑
    }
}
