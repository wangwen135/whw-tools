/**
 * Copyright(C) 2015-2025 杏仁科技
 * All rights reserved
 * 2015年9月6日 Created
 */
package com.xingren.generate;

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
