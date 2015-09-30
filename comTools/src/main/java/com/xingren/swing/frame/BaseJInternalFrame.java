/**
 * Copyright(C) 2015-2025 杏仁科技
 * All rights reserved
 * 2015年8月7日 Created
 */
package com.xingren.swing.frame;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 * <pre>
 * 基础类
 * </pre>
 *
 * @author wwh
 * @date 2015年8月7日 下午7:16:26
 *
 */
public abstract class BaseJInternalFrame extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    /**
     * <pre>
     * 构造方法
     * </pre>
     *
     */
    public BaseJInternalFrame() {

        // 添加快捷键
        InputMap inputMap = getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK), "find");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK), "close");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK), "defaultSize");

        getActionMap().put("find", new AbstractAction() {
            private static final long serialVersionUID = 1L;
            @Override
            public void actionPerformed(ActionEvent e) {
                findInFrame();
            }
        });

        getActionMap().put("close", new AbstractAction() {
            private static final long serialVersionUID = 1L;
            @Override
            public void actionPerformed(ActionEvent e) {
                doDefaultCloseAction();
            }
        });

        addInternalFrameListener(new InternalFrameListener() {

            public void internalFrameOpened(InternalFrameEvent e) {
                frameOpened();
            }

            public void internalFrameIconified(InternalFrameEvent e) {

            }

            public void internalFrameDeiconified(InternalFrameEvent e) {

            }

            public void internalFrameDeactivated(InternalFrameEvent e) {

            }

            public void internalFrameClosing(InternalFrameEvent e) {
                frameClosing();
            }

            public void internalFrameClosed(InternalFrameEvent e) {
                parentFrame.removeInternalFrame(BaseJInternalFrame.this);
                frameClosed();

            }

            public void internalFrameActivated(InternalFrameEvent e) {
                parentFrame.internalFrameActivated(BaseJInternalFrame.this);
            }
        });
    }

    /**
     * 查找方法，CTRL + F 触发
     */
    protected void findInFrame() {

    }

    protected void frameClosing() {

    }

    protected void frameClosed() {

    }

    protected void frameOpened() {

    }

    /**
     * 父面窗口
     */
    private MainFrame parentFrame;

    /**
     * 编号
     */
    private int id;

    /**
     * 获取 parentFrame
     *
     * @return the parentFrame
     */
    public MainFrame getParentFrame() {
        return parentFrame;
    }

    /**
     * 设置 parentFrame
     *
     * @param parentFrame
     *            the parentFrame to set
     */
    public void setParentFrame(MainFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    /**
     * 获取 id
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置 id
     *
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return super.getTitle() + "  " + id;
    }
}
