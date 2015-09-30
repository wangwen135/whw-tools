/**
 * Copyright(C) 2015-2025 杏仁科技
 * All rights reserved
 * 2015年9月6日 Created
 */
package com.xingren.swing.comm;

import javax.swing.SwingWorker;

import com.xingren.generate.MessagePublisher;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author wwh
 * @date 2015年9月6日 上午10:49:35
 *
 * @param <T>
 */
public abstract class MySwingWorker<T> extends SwingWorker<T, String> implements MessagePublisher {

    @Override
    public void publishMsg(java.lang.String... msg) {
        publish(msg);
    }
}
