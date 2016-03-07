package com.wwh.whwtools.generate;

/**
 * <pre>
 * 消息发布器
 * </pre>
 *
 * @author wwh
 * @date 2015年9月6日 上午10:37:17
 *
 */
public interface MessagePublisher {

    void publishMsg(String... msg);
}
