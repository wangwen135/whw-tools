package com.wwh.whwtools.test.swing;

import java.awt.AWTEvent;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GlassPanel extends JPanel {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private Float alpha = 0.75f;

	private String currentMsg;

	private List<String> msgList = new ArrayList<String>();

	private JLabel lbl_currentMSG;

	private JLabel lbl_historyMsg;

	private AWTEventListener awteListener;

	public Float getAlpha() {
		return alpha;
	}

	public void setAlpha(Float alpha) {
		this.alpha = alpha;
	}

	public String getCurrentMessage() {
		return currentMsg;
	}

	/**
	 * <pre>
	 * 重置消息
	 * </pre>
	 */
	public void resetMessage() {
		currentMsg = null;
		msgList = new ArrayList<String>();
	}

	/**
	 * <pre>
	 * 设置消息
	 * </pre>
	 * 
	 * @param message
	 */
	public void setCurrentMessage(String message) {
	    
		if (currentMsg != null) {
			msgList.add(currentMsg);
		}
		currentMsg = message;

		lbl_currentMSG.setText(currentMsg);
		setHistoryMessage();
	}

	private void setHistoryMessage() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("<html>");
		for (int i = msgList.size() - 1; i >= 0; i--) {
			sbf.append(msgList.get(i));
			sbf.append("<br>");
		}
		sbf.append("</html>");

		lbl_historyMsg.setText(sbf.toString());
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				alpha));
		// g2d.setColor(g2d.getBackground().darker());
		g2d.setColor(getBackground());
		g2d.fillRect(0, 0, getWidth(), getHeight());

		super.paint(g);
	}

	/**
	 * Create the panel.
	 */
	public GlassPanel() {
		init();
	}

	private void init() {
		setBackground(SystemColor.inactiveCaption);
		setLayout(new BorderLayout(0, 0));
		setOpaque(false);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel lbl_loading = new JLabel();
		lbl_loading.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl_loading.setVerticalTextPosition(SwingConstants.BOTTOM);
		lbl_loading.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lbl_loading);
		lbl_loading.setIcon(new ImageIcon(GlassPanel.class
				.getResource("/image/loading.gif")));

		lbl_loading.setOpaque(false);

		JLabel lbl_empty = new JLabel(" ");
		panel.add(lbl_empty);

		lbl_currentMSG = new JLabel("");
		lbl_currentMSG.setForeground(Color.RED);
		lbl_currentMSG.setFont(new Font("幼圆", Font.BOLD, 14));
		lbl_currentMSG.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lbl_currentMSG);

		lbl_historyMsg = new JLabel("");
		lbl_historyMsg.setFont(new Font("幼圆", Font.PLAIN, 14));
		lbl_historyMsg.setVerticalAlignment(SwingConstants.TOP);
		lbl_historyMsg.setHorizontalAlignment(SwingConstants.CENTER);
		add(lbl_historyMsg, BorderLayout.CENTER);

		disableOperate();

	}

	@Override
	public void setVisible(boolean aFlag) {
		if (aFlag) {
			disableAllKeyEvent();
		} else {
			enableAllKeyEvent();
		}
		resetMessage();
		super.setVisible(aFlag);
	}

	private void disableAllKeyEvent() {
		final Toolkit toolkit = Toolkit.getDefaultToolkit();

		awteListener = new AWTEventListener() {

			public void eventDispatched(AWTEvent event) {
				if (event.getClass() == KeyEvent.class) {
					KeyEvent ke = (KeyEvent) event;
					ke.consume();
				}
			}
		};

		toolkit.addAWTEventListener(awteListener,
				java.awt.AWTEvent.KEY_EVENT_MASK);
	}

	private void enableAllKeyEvent() {
		if (awteListener != null) {
			final Toolkit toolkit = Toolkit.getDefaultToolkit();
			toolkit.removeAWTEventListener(awteListener);
		}
	}

	private void disableOperate() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Toolkit.getDefaultToolkit().beep();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				Toolkit.getDefaultToolkit().beep();
			}
		});
		setFocusTraversalKeysEnabled(false);
	}
}
