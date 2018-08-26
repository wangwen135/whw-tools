package com.wwh.whwtools.swing.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.wwh.whwtools.swing.feature.codec.Base64IFrame;
import com.wwh.whwtools.swing.feature.codec.FileBase64IFrame;
import com.wwh.whwtools.swing.feature.codec.HexIFrame;
import com.wwh.whwtools.swing.feature.codec.Native2AsciiIFrame;
import com.wwh.whwtools.swing.feature.codec.UrlIFrame;
import com.wwh.whwtools.swing.feature.dbview.DatabaseViewIFrame;
import com.wwh.whwtools.swing.feature.digest.MD5IFrame;
import com.wwh.whwtools.swing.feature.digest.SHA1IFrame;
import com.wwh.whwtools.swing.feature.encrypt.AES128IFrame;
import com.wwh.whwtools.swing.feature.file.FileAppendIFrame;
import com.wwh.whwtools.swing.feature.generator.GenerateCodeIFrame;

/**
 * <pre>
 * 主窗口
 * </pre>
 *
 * @author wwh
 * @date 2015年8月7日 下午5:52:34
 *
 */
public class MainFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    // 窗口数量

    /**
     * 窗口列表
     */
    private List<BaseJInternalFrame> listIFrame = new ArrayList<BaseJInternalFrame>();

    // 记录当前活动的窗口
    private List<BaseJInternalFrame> activeOrder = new ArrayList<BaseJInternalFrame>();

    /**
     * 窗口编号
     */
    private int windowId = 0;

    private JDesktopPane desktopPane;
    private JToolBar toolBar;
    private JCheckBoxMenuItem cbmi_toolBar;
    private JMenuBar menuBar_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        String windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        try {
            UIManager.setLookAndFeel(windows);
        } catch (Exception e1) {
            //
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MainFrame() {
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setTitle("工具");
        setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/image/et.ico")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1020, 740);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        desktopPane = new JDesktopPane();
        desktopPane.setBackground(Color.WHITE);

        contentPane.add(desktopPane, BorderLayout.CENTER);

        menuBar_1 = new JMenuBar();
        contentPane.add(menuBar_1, BorderLayout.NORTH);

        createMenu(menuBar_1);

        createToolBar();

        initShortCutKey();
    }

    /**
     * 创建菜单
     * 
     * @param menuBar
     */
    public void createMenu(JMenuBar menuBar) {
        JMenu mn_start = new JMenu("开始");
        menuBar.add(mn_start);

        JMenuItem mntm_close = new JMenuItem("关闭");
        mntm_close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainFrame.this, "Ctrl + W 触发了");
            }
        });
        mntm_close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        mn_start.add(mntm_close);

        JMenuItem mntm_closeAll = new JMenuItem("全部关闭");
        mntm_closeAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
        mntm_closeAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeAllIFrame();

            }
        });
        mn_start.add(mntm_closeAll);

        mn_start.insertSeparator(2);

        JMenuItem mntm_exit = new JMenuItem("退出");
        mntm_exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mn_start.add(mntm_exit);

        JMenu mn_cedec = new JMenu("编码");
        menuBar.add(mn_cedec);

        JMenu mnBase = new JMenu("Base64");
        mn_cedec.add(mnBase);

        JMenuItem mntmBase64 = new JMenuItem("Base64编码/解码");
        mntmBase64.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addInternalFrame(new Base64IFrame());
            }
        });
        mnBase.add(mntmBase64);

        JMenuItem mntmFileBase64 = new JMenuItem("文件Base64编码/解码");
        mntmFileBase64.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addInternalFrame(new FileBase64IFrame());
            }
        });
        mnBase.add(mntmFileBase64);

        JMenuItem mntmHex = new JMenuItem("Hex");
        mntmHex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addInternalFrame(new HexIFrame());
            }
        });
        mn_cedec.add(mntmHex);

        JMenuItem mntmUrlEncode = new JMenuItem("UrlEncode");
        mntmUrlEncode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addInternalFrame(new UrlIFrame());
            }
        });
        mn_cedec.add(mntmUrlEncode);

        JMenuItem mntmNativeascii = new JMenuItem("Native2Ascii");
        mntmNativeascii.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addInternalFrame(new Native2AsciiIFrame());
            }
        });
        mn_cedec.add(mntmNativeascii);

        JMenu mn_encrypt = new JMenu("加密");
        menuBar.add(mn_encrypt);

        JMenuItem mntmMd_1 = new JMenuItem("MD5");
        mntmMd_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addInternalFrame(new MD5IFrame());
            }
        });
        mn_encrypt.add(mntmMd_1);

        JMenuItem mntmSha = new JMenuItem("SHA1");
        mntmSha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addInternalFrame(new SHA1IFrame());
            }
        });
        mn_encrypt.add(mntmSha);
        // 添加横杠
        mn_encrypt.insertSeparator(2);

        JMenuItem mntmDes = new JMenuItem("DES");
        mn_encrypt.add(mntmDes);

        JMenuItem mntmAes = new JMenuItem("AES128");
        mntmAes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addInternalFrame(new AES128IFrame());
            }
        });
        mn_encrypt.add(mntmAes);

        JMenu mn_file = new JMenu("文件");
        menuBar_1.add(mn_file);

        JMenuItem mntmReplace = new JMenuItem("替换");
        mn_file.add(mntmReplace);

        JMenuItem mntmAppend = new JMenuItem("追加");
        mntmAppend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addInternalFrame(new FileAppendIFrame());
            }
        });
        mn_file.add(mntmAppend);

        JMenu mnSocket = new JMenu("Socket");
        menuBar_1.add(mnSocket);

        JMenuItem mntmServer = new JMenuItem("Server");
        mnSocket.add(mntmServer);

        JMenuItem mntmClient = new JMenuItem("Client");
        mnSocket.add(mntmClient);

        JMenu mn_Rest = new JMenu("rest请求");
        menuBar.add(mn_Rest);

        JMenuItem menuItem_3 = new JMenuItem("当前版本");
        mn_Rest.add(menuItem_3);

        JMenu mn_database = new JMenu("数据库");
        menuBar.add(mn_database);

        JMenuItem menuItem_6 = new JMenuItem("查看表");
        menuItem_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addInternalFrame(new DatabaseViewIFrame());
            }
        });
        mn_database.add(menuItem_6);

        JMenuItem menuItem_7 = new JMenuItem("代码生成");
        menuItem_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addInternalFrame(new GenerateCodeIFrame());
            }
        });
        mn_database.add(menuItem_7);

        JMenu mn_view = new JMenu("视图");

        cbmi_toolBar = new JCheckBoxMenuItem("工具条");
        mn_view.add(cbmi_toolBar);
        cbmi_toolBar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
        cbmi_toolBar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cbmi_toolBar.isSelected()) {
                    if (toolBar == null)
                        createToolBar();
                } else {
                    removeToolBar();
                }
            }
        });
        cbmi_toolBar.setSelected(true);

        JMenu menu_4 = new JMenu("窗口排列");
        mn_view.add(menu_4);

        JMenuItem menuItem_tile = new JMenuItem("平铺");
        menuItem_tile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tileWindow();
            }
        });
        menuItem_tile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        menu_4.add(menuItem_tile);

        JMenuItem menuItem_cascade = new JMenuItem("层叠");
        menuItem_cascade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cascadeWindow();

            }
        });
        menuItem_cascade.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        menu_4.add(menuItem_cascade);

        JMenuItem menuItem_reset = new JMenuItem("还原窗口大小");
        menuItem_reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetWindowSize();
            }
        });
        menuItem_reset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        mn_view.add(menuItem_reset);

        menuBar.add(mn_view);
    }

    /**
     * 初始化快捷键
     */
    private void initShortCutKey() {

        // 这个不行
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                // 使用 alt + 数字键 激活对应的窗口
                if (e.isAltDown()) {
                    int code = e.getKeyCode();
                    int index = code - KeyEvent.VK_0;
                    if (index >= 0 && index <= 9 && index < listIFrame.size()) {
                        try {
                            listIFrame.get(index).setSelected(true);
                        } catch (PropertyVetoException e1) {
                            e1.printStackTrace();
                        }
                    }

                }
            }
        });

        // 前一个 后一个等

        // 看能不能做连续按键连续向前切换的。。。 试试玩

    }

    /**
     * 移除工具条
     */
    private void removeToolBar() {
        if (toolBar != null) {
            contentPane.remove(toolBar);
            toolBar = null;
            contentPane.revalidate();
        }
    }

    /**
     * 创建工具条
     */
    private void createToolBar() {
        toolBar = new JToolBar();
        toolBar.setToolTipText("工具条");
        toolBar.setOrientation(SwingConstants.VERTICAL);
        toolBar.setRollover(true);
        contentPane.add(toolBar, BorderLayout.WEST);

        JButton btn_hide = new JButton("隐藏");
        btn_hide.setForeground(new Color(0, 191, 255));
        btn_hide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeToolBar();
                cbmi_toolBar.setSelected(false);
            }
        });
        toolBar.add(btn_hide);

        JButton btn_closeAll = new JButton("全部关闭");
        btn_closeAll.setForeground(Color.RED);
        btn_closeAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeAllIFrame();
            }
        });
        toolBar.add(btn_closeAll);

        JButton btn_tile = new JButton("平铺(T)");
        btn_tile.setMnemonic('T');
        btn_tile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tileWindow();
            }
        });
        toolBar.add(btn_tile);

        JButton btn_cascade = new JButton("层叠(C)");
        btn_cascade.setMnemonic('C');
        btn_cascade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cascadeWindow();
            }
        });
        toolBar.add(btn_cascade);

        JButton btn_resetSize = new JButton("还原窗口");
        btn_resetSize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetWindowSize();
            }
        });
        toolBar.add(btn_resetSize);

        JButton btnBase64 = new JButton("Base64");
        btnBase64.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addInternalFrame(new Base64IFrame());
            }
        });

        toolBar.add(btnBase64);

        JButton btnHex = new JButton("HEX");
        btnHex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addInternalFrame(new HexIFrame());
            }
        });
        toolBar.add(btnHex);

        JButton btnUrlencode = new JButton("UrlEncode");
        btnUrlencode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addInternalFrame(new UrlIFrame());
            }
        });
        toolBar.add(btnUrlencode);

        JButton btnMd = new JButton("MD5");
        btnMd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addInternalFrame(new MD5IFrame());
            }
        });

        JButton btnNatasc = new JButton("Nat2Asc");
        btnNatasc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addInternalFrame(new Native2AsciiIFrame());
            }
        });
        toolBar.add(btnNatasc);
        toolBar.add(btnMd);

        JButton btnSha = new JButton("SHA1");
        btnSha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addInternalFrame(new SHA1IFrame());
            }
        });
        toolBar.add(btnSha);

        JButton btnAes = new JButton("AES128");
        btnAes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addInternalFrame(new AES128IFrame());
            }
        });
        toolBar.add(btnAes);
    }

    /**
     * 添加
     */
    public void addInternalFrame(BaseJInternalFrame bif) {

        windowId++;
        // 设置父窗口
        bif.setParentFrame(this);
        // 设置ID
        bif.setId(windowId);

        listIFrame.add(bif);

        activeOrder.add(bif);

        bif.setVisible(true);

        bif.setLayer(0);

        desktopPane.add(bif);

        try {
            bif.setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除
     */
    public void removeInternalFrame(BaseJInternalFrame bif) {
        listIFrame.remove(bif);
        activeOrder.remove(bif);
    }

    /**
     * 窗口激活
     * 
     * @param bif
     */
    public void internalFrameActivated(BaseJInternalFrame bif) {
        activeOrder.remove(bif);
        activeOrder.add(bif);
    }

    /**
     * 平铺窗口
     */
    private void tileWindow() {
        if (listIFrame.isEmpty()) {
            return;
        }

        int winSize = listIFrame.size();

        Double sqrt = Math.sqrt(winSize);
        int round = (int) Math.round(sqrt);
        int column = round, row = round;
        if ((column * row) < winSize) {
            column++;
        }
        int maxWidth = desktopPane.getWidth();
        int maxHeight = desktopPane.getHeight();

        int colWidth = maxWidth / column;
        int rowHeight = maxHeight / row;

        int lastRowHeight = maxHeight - (rowHeight * (row - 1));

        int lastColWidth = maxWidth - (colWidth * (column - 1));

        int widthT, heightT;

        for (int i = 1; i <= row; i++) {
            // 如果最后一行
            if (i == row && i != 1) {
                heightT = lastRowHeight;
            } else {
                heightT = rowHeight;
            }
            for (int j = 1; j <= column; j++) {
                // 如果最后一列
                if (j == column && j != 1) {
                    widthT = lastColWidth;
                } else {
                    widthT = colWidth;
                }

                int index = (i - 1) * column + j;

                if (index > winSize) {
                    break;
                }

                BaseJInternalFrame bif = listIFrame.get(index - 1);

                int x = (j - 1) * colWidth;
                int y = (i - 1) * rowHeight;

                bif.pack();

                // bif.setLocation(x, y);
                // bif.setSize(widthT, heightT);
                bif.setBounds(x, y, widthT, heightT);
            }
        }

    }

    /**
     * 层叠窗口
     */
    private void cascadeWindow() {
        if (listIFrame.isEmpty()) {
            return;
        }
        resetWindowSize();

        // X坐标 Y坐标
        int size = listIFrame.size();
        BaseJInternalFrame bif;
        int increment = 40;// 增量
        int maxWidth = desktopPane.getWidth();
        int maxHeight = desktopPane.getHeight();

        int x = 0, y = 0;
        int iwidth, iheight;
        // 第0个是固定的
        bif = listIFrame.get(0);
        bif.setLocation(x, y);

        for (int i = 1; i < size; i++) {
            bif = listIFrame.get(i);

            iwidth = bif.getWidth() / 2;
            iheight = bif.getHeight() / 2;

            if (maxWidth > increment) {
                x += increment;
                if (x >= maxWidth - iwidth) {
                    x = 0;
                }
            }

            if (maxHeight > increment) {
                y += increment;
                if (y >= maxHeight - iheight) {
                    y = 0;
                }
            }

            bif.setLocation(x, y);
            bif.toFront();
        }

        try {
            bif.setSelected(true);
        } catch (PropertyVetoException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 关闭所有内部窗口
     */
    private void closeAllIFrame() {
        if (listIFrame.size() < 1) {
            return;
        }

        int rt = JOptionPane.showConfirmDialog(MainFrame.this, "确认关闭所有的窗口？", "请确认", JOptionPane.YES_NO_OPTION);
        if (rt == JOptionPane.YES_OPTION) {
            List<BaseJInternalFrame> tmp = new ArrayList<BaseJInternalFrame>(listIFrame);
            for (BaseJInternalFrame bjif : tmp) {
                bjif.doDefaultCloseAction();
            }
            tmp = null;
        }

    }

    /**
     * 还原窗口大小
     */
    private void resetWindowSize() {
        for (BaseJInternalFrame bjif : listIFrame) {
            bjif.pack();
            Dimension ds = bjif.getPreferredSize();
            bjif.setSize((int) ds.getWidth(), (int) ds.getHeight());
        }
    }
}
