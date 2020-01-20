package nia.chapter2.echoserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.StandardCharsets;

/**
 * 代码清单 2-1 EchoServerHandler
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
//标示一个ChannelHandler可以被多个 Channel 安全地共享
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("[Inbound]channelRegistered");
    super.channelRegistered(ctx);
  }

  @Override
  public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("[Inbound]channelUnregistered");
    super.channelUnregistered(ctx);
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("[Inbound]channelActive");

    super.channelActive(ctx);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("[Inbound]channelInactive");

    super.channelInactive(ctx);
  }

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    System.out.println("[Inbound]userEventTriggered");

    super.userEventTriggered(ctx, evt);
  }

  @Override
  public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
    System.out.println("[Inbound]channelWritabilityChanged");

    super.channelWritabilityChanged(ctx);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    System.out.println("[Inbound]channelRead");
    ByteBuf in = (ByteBuf) msg;
    System.out.println("receive:" + in.toString(StandardCharsets.UTF_8));
    //将消息记录到控制台
    ctx.pipeline().write(Unpooled.copiedBuffer("You wrote:\n", StandardCharsets.UTF_8));
    in.release();
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx)
      throws Exception {
    System.out.println("[Inbound]channelReadComplete");

    //将未决消息冲刷到远程节点，并且关闭该 Channel
    ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
//                .addListener(ChannelFutureListener.CLOSE);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx,
      Throwable cause) {
    //打印异常栈跟踪
    cause.printStackTrace();
    //关闭该Channel
    ctx.close();
  }
}
