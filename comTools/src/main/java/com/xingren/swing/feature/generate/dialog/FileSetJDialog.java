/**
 * Copyright(C) 2015-2025 杏仁科技
 * All rights reserved
 * 2015年8月22日 Created
 */
package com.xingren.swing.feature.generate.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author wwh
 * @date 2015年8月22日 下午3:30:50
 *
 */
public class FileSetJDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private final JPanel contentPanel = new JPanel();
    private JTextField txt_do1;
    private JTextField txt_do2;
    private JTextField txt_do3;
    private JTextField txt_dto1;
    private JTextField txt_idao1;
    private JTextField txt_dao1;
    private JTextField txt_sqlmap1;
    private JTextField txt_daoTest1;
    private JTextField txt_iservice1;
    private JTextField txt_service1;
    private JTextField txt_serviceTest1;
    private JTextField txt_dto2;
    private JTextField txt_idao2;
    private JTextField txt_dao2;
    private JTextField txt_sqlmap2;
    private JTextField txt_daoTest2;
    private JTextField txt_iservice2;
    private JTextField txt_service2;
    private JTextField txt_serviceTest2;
    private JTextField txt_dto3;
    private JTextField txt_idao3;
    private JTextField txt_dao3;
    private JTextField txt_sqlmap3;
    private JTextField txt_daoTest3;
    private JTextField txt_iservice3;
    private JTextField txt_service3;
    private JTextField txt_serviceTest3;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            FileSetJDialog dialog = new FileSetJDialog();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    private FileSetJDialog() {
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("文件设置");
        setResizable(false);
        setBounds(100, 100, 598, 347);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[] { 80, 0, 0, 0, 0 };
        gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gbl_contentPanel.columnWeights = new double[] { 0.0, 2.0, 0.0, 1.0, Double.MIN_VALUE };
        gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        contentPanel.setLayout(gbl_contentPanel);
        {
            JLabel label = new JLabel("类型");
            label.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 14));
            label.setForeground(Color.BLUE);
            GridBagConstraints gbc_label = new GridBagConstraints();
            gbc_label.insets = new Insets(0, 0, 5, 5);
            gbc_label.gridx = 0;
            gbc_label.gridy = 0;
            contentPanel.add(label, gbc_label);
        }
        {
            JLabel label = new JLabel("生成文件名");
            label.setForeground(Color.BLUE);
            label.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 14));
            GridBagConstraints gbc_label = new GridBagConstraints();
            gbc_label.insets = new Insets(0, 0, 5, 5);
            gbc_label.gridx = 1;
            gbc_label.gridy = 0;
            contentPanel.add(label, gbc_label);
        }
        {
            JLabel label = new JLabel("文件后缀");
            label.setForeground(Color.BLUE);
            label.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 14));
            GridBagConstraints gbc_label = new GridBagConstraints();
            gbc_label.insets = new Insets(0, 0, 5, 5);
            gbc_label.gridx = 2;
            gbc_label.gridy = 0;
            contentPanel.add(label, gbc_label);
        }
        {
            JLabel label = new JLabel("文件输出相对目录");
            label.setForeground(Color.BLUE);
            label.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 14));
            GridBagConstraints gbc_label = new GridBagConstraints();
            gbc_label.insets = new Insets(0, 0, 5, 0);
            gbc_label.gridx = 3;
            gbc_label.gridy = 0;
            contentPanel.add(label, gbc_label);
        }
        {
            JLabel lblDo = new JLabel("DO文件");
            GridBagConstraints gbc_lblDo = new GridBagConstraints();
            gbc_lblDo.anchor = GridBagConstraints.EAST;
            gbc_lblDo.insets = new Insets(0, 0, 5, 5);
            gbc_lblDo.gridx = 0;
            gbc_lblDo.gridy = 1;
            contentPanel.add(lblDo, gbc_lblDo);
        }
        {
            txt_do1 = new JTextField();
            GridBagConstraints gbc_txt_do1 = new GridBagConstraints();
            gbc_txt_do1.insets = new Insets(0, 0, 5, 5);
            gbc_txt_do1.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_do1.gridx = 1;
            gbc_txt_do1.gridy = 1;
            contentPanel.add(txt_do1, gbc_txt_do1);
            txt_do1.setColumns(10);
        }
        {
            txt_do2 = new JTextField();
            GridBagConstraints gbc_txt_do2 = new GridBagConstraints();
            gbc_txt_do2.insets = new Insets(0, 0, 5, 5);
            gbc_txt_do2.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_do2.gridx = 2;
            gbc_txt_do2.gridy = 1;
            contentPanel.add(txt_do2, gbc_txt_do2);
            txt_do2.setColumns(10);
        }
        {
            txt_do3 = new JTextField();
            GridBagConstraints gbc_txt_do3 = new GridBagConstraints();
            gbc_txt_do3.insets = new Insets(0, 0, 5, 0);
            gbc_txt_do3.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_do3.gridx = 3;
            gbc_txt_do3.gridy = 1;
            contentPanel.add(txt_do3, gbc_txt_do3);
            txt_do3.setColumns(10);
        }
        {
            JLabel lblDto = new JLabel("DTO文件");
            GridBagConstraints gbc_lblDto = new GridBagConstraints();
            gbc_lblDto.anchor = GridBagConstraints.EAST;
            gbc_lblDto.insets = new Insets(0, 0, 5, 5);
            gbc_lblDto.gridx = 0;
            gbc_lblDto.gridy = 2;
            contentPanel.add(lblDto, gbc_lblDto);
        }
        {
            txt_dto1 = new JTextField();
            GridBagConstraints gbc_txt_dto1 = new GridBagConstraints();
            gbc_txt_dto1.insets = new Insets(0, 0, 5, 5);
            gbc_txt_dto1.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_dto1.gridx = 1;
            gbc_txt_dto1.gridy = 2;
            contentPanel.add(txt_dto1, gbc_txt_dto1);
            txt_dto1.setColumns(10);
        }
        {
            txt_dto2 = new JTextField();
            GridBagConstraints gbc_txt_dto2 = new GridBagConstraints();
            gbc_txt_dto2.insets = new Insets(0, 0, 5, 5);
            gbc_txt_dto2.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_dto2.gridx = 2;
            gbc_txt_dto2.gridy = 2;
            contentPanel.add(txt_dto2, gbc_txt_dto2);
            txt_dto2.setColumns(10);
        }
        {
            txt_dto3 = new JTextField();
            GridBagConstraints gbc_txt_dto3 = new GridBagConstraints();
            gbc_txt_dto3.insets = new Insets(0, 0, 5, 0);
            gbc_txt_dto3.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_dto3.gridx = 3;
            gbc_txt_dto3.gridy = 2;
            contentPanel.add(txt_dto3, gbc_txt_dto3);
            txt_dto3.setColumns(10);
        }
        {
            JLabel lblDao = new JLabel("DAO接口");
            GridBagConstraints gbc_lblDao = new GridBagConstraints();
            gbc_lblDao.anchor = GridBagConstraints.EAST;
            gbc_lblDao.insets = new Insets(0, 0, 5, 5);
            gbc_lblDao.gridx = 0;
            gbc_lblDao.gridy = 3;
            contentPanel.add(lblDao, gbc_lblDao);
        }
        {
            txt_idao1 = new JTextField();
            GridBagConstraints gbc_txt_idao1 = new GridBagConstraints();
            gbc_txt_idao1.insets = new Insets(0, 0, 5, 5);
            gbc_txt_idao1.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_idao1.gridx = 1;
            gbc_txt_idao1.gridy = 3;
            contentPanel.add(txt_idao1, gbc_txt_idao1);
            txt_idao1.setColumns(10);
        }
        {
            txt_idao2 = new JTextField();
            GridBagConstraints gbc_txt_idao2 = new GridBagConstraints();
            gbc_txt_idao2.insets = new Insets(0, 0, 5, 5);
            gbc_txt_idao2.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_idao2.gridx = 2;
            gbc_txt_idao2.gridy = 3;
            contentPanel.add(txt_idao2, gbc_txt_idao2);
            txt_idao2.setColumns(10);
        }
        {
            txt_idao3 = new JTextField();
            GridBagConstraints gbc_txt_idao3 = new GridBagConstraints();
            gbc_txt_idao3.insets = new Insets(0, 0, 5, 0);
            gbc_txt_idao3.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_idao3.gridx = 3;
            gbc_txt_idao3.gridy = 3;
            contentPanel.add(txt_idao3, gbc_txt_idao3);
            txt_idao3.setColumns(10);
        }
        {
            JLabel lblDao_1 = new JLabel("DAO实现");
            GridBagConstraints gbc_lblDao_1 = new GridBagConstraints();
            gbc_lblDao_1.anchor = GridBagConstraints.EAST;
            gbc_lblDao_1.insets = new Insets(0, 0, 5, 5);
            gbc_lblDao_1.gridx = 0;
            gbc_lblDao_1.gridy = 4;
            contentPanel.add(lblDao_1, gbc_lblDao_1);
        }
        {
            txt_dao1 = new JTextField();
            GridBagConstraints gbc_txt_dao1 = new GridBagConstraints();
            gbc_txt_dao1.insets = new Insets(0, 0, 5, 5);
            gbc_txt_dao1.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_dao1.gridx = 1;
            gbc_txt_dao1.gridy = 4;
            contentPanel.add(txt_dao1, gbc_txt_dao1);
            txt_dao1.setColumns(10);
        }
        {
            txt_dao2 = new JTextField();
            GridBagConstraints gbc_txt_dao2 = new GridBagConstraints();
            gbc_txt_dao2.insets = new Insets(0, 0, 5, 5);
            gbc_txt_dao2.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_dao2.gridx = 2;
            gbc_txt_dao2.gridy = 4;
            contentPanel.add(txt_dao2, gbc_txt_dao2);
            txt_dao2.setColumns(10);
        }
        {
            txt_dao3 = new JTextField();
            GridBagConstraints gbc_txt_dao3 = new GridBagConstraints();
            gbc_txt_dao3.insets = new Insets(0, 0, 5, 0);
            gbc_txt_dao3.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_dao3.gridx = 3;
            gbc_txt_dao3.gridy = 4;
            contentPanel.add(txt_dao3, gbc_txt_dao3);
            txt_dao3.setColumns(10);
        }
        {
            JLabel lblSqlmap = new JLabel("SQLMAP文件");
            GridBagConstraints gbc_lblSqlmap = new GridBagConstraints();
            gbc_lblSqlmap.anchor = GridBagConstraints.EAST;
            gbc_lblSqlmap.insets = new Insets(0, 0, 5, 5);
            gbc_lblSqlmap.gridx = 0;
            gbc_lblSqlmap.gridy = 5;
            contentPanel.add(lblSqlmap, gbc_lblSqlmap);
        }
        {
            txt_sqlmap1 = new JTextField();
            GridBagConstraints gbc_txt_sqlmap1 = new GridBagConstraints();
            gbc_txt_sqlmap1.insets = new Insets(0, 0, 5, 5);
            gbc_txt_sqlmap1.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_sqlmap1.gridx = 1;
            gbc_txt_sqlmap1.gridy = 5;
            contentPanel.add(txt_sqlmap1, gbc_txt_sqlmap1);
            txt_sqlmap1.setColumns(10);
        }
        {
            txt_sqlmap2 = new JTextField();
            GridBagConstraints gbc_txt_sqlmap2 = new GridBagConstraints();
            gbc_txt_sqlmap2.insets = new Insets(0, 0, 5, 5);
            gbc_txt_sqlmap2.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_sqlmap2.gridx = 2;
            gbc_txt_sqlmap2.gridy = 5;
            contentPanel.add(txt_sqlmap2, gbc_txt_sqlmap2);
            txt_sqlmap2.setColumns(10);
        }
        {
            txt_sqlmap3 = new JTextField();
            txt_sqlmap3.setColumns(10);
            GridBagConstraints gbc_txt_sqlmap3 = new GridBagConstraints();
            gbc_txt_sqlmap3.insets = new Insets(0, 0, 5, 0);
            gbc_txt_sqlmap3.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_sqlmap3.gridx = 3;
            gbc_txt_sqlmap3.gridy = 5;
            contentPanel.add(txt_sqlmap3, gbc_txt_sqlmap3);
        }
        {
            JLabel lblDao_2 = new JLabel("DAO测试");
            GridBagConstraints gbc_lblDao_2 = new GridBagConstraints();
            gbc_lblDao_2.anchor = GridBagConstraints.EAST;
            gbc_lblDao_2.insets = new Insets(0, 0, 5, 5);
            gbc_lblDao_2.gridx = 0;
            gbc_lblDao_2.gridy = 6;
            contentPanel.add(lblDao_2, gbc_lblDao_2);
        }
        {
            txt_daoTest1 = new JTextField();
            GridBagConstraints gbc_txt_daoTest1 = new GridBagConstraints();
            gbc_txt_daoTest1.insets = new Insets(0, 0, 5, 5);
            gbc_txt_daoTest1.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_daoTest1.gridx = 1;
            gbc_txt_daoTest1.gridy = 6;
            contentPanel.add(txt_daoTest1, gbc_txt_daoTest1);
            txt_daoTest1.setColumns(10);
        }
        {
            txt_daoTest2 = new JTextField();
            GridBagConstraints gbc_txt_daoTest2 = new GridBagConstraints();
            gbc_txt_daoTest2.insets = new Insets(0, 0, 5, 5);
            gbc_txt_daoTest2.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_daoTest2.gridx = 2;
            gbc_txt_daoTest2.gridy = 6;
            contentPanel.add(txt_daoTest2, gbc_txt_daoTest2);
            txt_daoTest2.setColumns(10);
        }
        {
            txt_daoTest3 = new JTextField();
            txt_daoTest3.setColumns(10);
            GridBagConstraints gbc_txt_daoTest3 = new GridBagConstraints();
            gbc_txt_daoTest3.insets = new Insets(0, 0, 5, 0);
            gbc_txt_daoTest3.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_daoTest3.gridx = 3;
            gbc_txt_daoTest3.gridy = 6;
            contentPanel.add(txt_daoTest3, gbc_txt_daoTest3);
        }
        {
            JLabel lblService = new JLabel("SERVICE接口");
            GridBagConstraints gbc_lblService = new GridBagConstraints();
            gbc_lblService.anchor = GridBagConstraints.EAST;
            gbc_lblService.insets = new Insets(0, 0, 5, 5);
            gbc_lblService.gridx = 0;
            gbc_lblService.gridy = 7;
            contentPanel.add(lblService, gbc_lblService);
        }
        {
            txt_iservice1 = new JTextField();
            GridBagConstraints gbc_txt_iservice1 = new GridBagConstraints();
            gbc_txt_iservice1.insets = new Insets(0, 0, 5, 5);
            gbc_txt_iservice1.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_iservice1.gridx = 1;
            gbc_txt_iservice1.gridy = 7;
            contentPanel.add(txt_iservice1, gbc_txt_iservice1);
            txt_iservice1.setColumns(10);
        }
        {
            txt_iservice2 = new JTextField();
            GridBagConstraints gbc_txt_iservice2 = new GridBagConstraints();
            gbc_txt_iservice2.insets = new Insets(0, 0, 5, 5);
            gbc_txt_iservice2.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_iservice2.gridx = 2;
            gbc_txt_iservice2.gridy = 7;
            contentPanel.add(txt_iservice2, gbc_txt_iservice2);
            txt_iservice2.setColumns(10);
        }
        {
            txt_iservice3 = new JTextField();
            txt_iservice3.setColumns(10);
            GridBagConstraints gbc_txt_iservice3 = new GridBagConstraints();
            gbc_txt_iservice3.insets = new Insets(0, 0, 5, 0);
            gbc_txt_iservice3.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_iservice3.gridx = 3;
            gbc_txt_iservice3.gridy = 7;
            contentPanel.add(txt_iservice3, gbc_txt_iservice3);
        }
        {
            JLabel lblService_1 = new JLabel("SERVICE实现");
            GridBagConstraints gbc_lblService_1 = new GridBagConstraints();
            gbc_lblService_1.anchor = GridBagConstraints.EAST;
            gbc_lblService_1.insets = new Insets(0, 0, 5, 5);
            gbc_lblService_1.gridx = 0;
            gbc_lblService_1.gridy = 8;
            contentPanel.add(lblService_1, gbc_lblService_1);
        }
        {
            txt_service1 = new JTextField();
            GridBagConstraints gbc_txt_service1 = new GridBagConstraints();
            gbc_txt_service1.insets = new Insets(0, 0, 5, 5);
            gbc_txt_service1.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_service1.gridx = 1;
            gbc_txt_service1.gridy = 8;
            contentPanel.add(txt_service1, gbc_txt_service1);
            txt_service1.setColumns(10);
        }
        {
            txt_service2 = new JTextField();
            GridBagConstraints gbc_txt_service2 = new GridBagConstraints();
            gbc_txt_service2.insets = new Insets(0, 0, 5, 5);
            gbc_txt_service2.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_service2.gridx = 2;
            gbc_txt_service2.gridy = 8;
            contentPanel.add(txt_service2, gbc_txt_service2);
            txt_service2.setColumns(10);
        }
        {
            txt_service3 = new JTextField();
            txt_service3.setColumns(10);
            GridBagConstraints gbc_txt_service3 = new GridBagConstraints();
            gbc_txt_service3.insets = new Insets(0, 0, 5, 0);
            gbc_txt_service3.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_service3.gridx = 3;
            gbc_txt_service3.gridy = 8;
            contentPanel.add(txt_service3, gbc_txt_service3);
        }
        {
            JLabel lblService_2 = new JLabel("SERVICE测试");
            GridBagConstraints gbc_lblService_2 = new GridBagConstraints();
            gbc_lblService_2.anchor = GridBagConstraints.EAST;
            gbc_lblService_2.insets = new Insets(0, 0, 0, 5);
            gbc_lblService_2.gridx = 0;
            gbc_lblService_2.gridy = 9;
            contentPanel.add(lblService_2, gbc_lblService_2);
        }
        {
            txt_serviceTest1 = new JTextField();
            GridBagConstraints gbc_txt_serviceTest1 = new GridBagConstraints();
            gbc_txt_serviceTest1.insets = new Insets(0, 0, 0, 5);
            gbc_txt_serviceTest1.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_serviceTest1.gridx = 1;
            gbc_txt_serviceTest1.gridy = 9;
            contentPanel.add(txt_serviceTest1, gbc_txt_serviceTest1);
            txt_serviceTest1.setColumns(10);
        }
        {
            txt_serviceTest2 = new JTextField();
            GridBagConstraints gbc_txt_serviceTest2 = new GridBagConstraints();
            gbc_txt_serviceTest2.insets = new Insets(0, 0, 0, 5);
            gbc_txt_serviceTest2.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_serviceTest2.gridx = 2;
            gbc_txt_serviceTest2.gridy = 9;
            contentPanel.add(txt_serviceTest2, gbc_txt_serviceTest2);
            txt_serviceTest2.setColumns(10);
        }
        {
            txt_serviceTest3 = new JTextField();
            txt_serviceTest3.setColumns(10);
            GridBagConstraints gbc_txt_serviceTest3 = new GridBagConstraints();
            gbc_txt_serviceTest3.fill = GridBagConstraints.HORIZONTAL;
            gbc_txt_serviceTest3.gridx = 3;
            gbc_txt_serviceTest3.gridy = 9;
            contentPanel.add(txt_serviceTest3, gbc_txt_serviceTest3);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);

            {
                JButton defaultButton = new JButton("默认值");
                defaultButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        entityToView(new FileSetEntity());
                    }
                });
                defaultButton.setActionCommand("default");
                buttonPane.add(defaultButton);
            }

            {
                JButton okButton = new JButton("确认");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        isOk = true;
                        setVisible(false);
                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("取消");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setVisible(false);
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }

    private boolean isOk = false;

    public static FileSetEntity showDialog(FileSetEntity entity) {
        FileSetJDialog fdialog = new FileSetJDialog();
        fdialog.entityToView(entity);
        fdialog.setVisible(true);
        if (fdialog.isOk) {
            return fdialog.viewToEntity();
        }

        return entity;
    }

    private void entityToView(FileSetEntity e) {
        txt_do1.setText(e.getDoFileName());
        txt_do2.setText(e.getDoFileNameSuffix());
        txt_do3.setText(e.getDoFileOutput());

        txt_dto1.setText(e.getDtoFileName());
        txt_dto2.setText(e.getDtoFileNameSuffix());
        txt_dto3.setText(e.getDtoFileOutput());

        txt_idao1.setText(e.getIdaoFileName());
        txt_idao2.setText(e.getIdaoFileNameSuffix());
        txt_idao3.setText(e.getIdaoFileOutput());

        txt_dao1.setText(e.getDaoFileName());
        txt_dao2.setText(e.getDaoFileNameSuffix());
        txt_dao3.setText(e.getDaoFileOutput());

        txt_sqlmap1.setText(e.getSqlmapFileName());
        txt_sqlmap2.setText(e.getSqlmapFileNameSuffix());
        txt_sqlmap3.setText(e.getSqlmapFileOutput());

        txt_daoTest1.setText(e.getDaoTestFileName());
        txt_daoTest2.setText(e.getDaoTestFileNameSuffix());
        txt_daoTest3.setText(e.getDaoTestFileOutput());

        txt_iservice1.setText(e.getIserviceFileName());
        txt_iservice2.setText(e.getIserviceFileNameSuffix());
        txt_iservice3.setText(e.getIserviceFileOutput());

        txt_service1.setText(e.getServiceFileName());
        txt_service2.setText(e.getServiceFileNameSuffix());
        txt_service3.setText(e.getServiceFileOutput());

        txt_serviceTest1.setText(e.getServiceTestFileName());
        txt_serviceTest2.setText(e.getServiceTestFileNameSuffix());
        txt_serviceTest3.setText(e.getServiceTestFileOutput());

    }

    private FileSetEntity viewToEntity() {
        FileSetEntity e = new FileSetEntity();

        e.setDoFileName(txt_do1.getText());
        e.setDoFileNameSuffix(txt_do2.getText());
        e.setDoFileOutput(txt_do3.getText());

        e.setDtoFileName(txt_dto1.getText());
        e.setDtoFileNameSuffix(txt_dto2.getText());
        e.setDtoFileOutput(txt_dto3.getText());

        e.setIdaoFileName(txt_idao1.getText());
        e.setIdaoFileNameSuffix(txt_idao2.getText());
        e.setIdaoFileOutput(txt_idao3.getText());

        e.setDaoFileName(txt_dao1.getText());
        e.setDaoFileNameSuffix(txt_dao2.getText());
        e.setDaoFileOutput(txt_dao3.getText());

        e.setSqlmapFileName(txt_sqlmap1.getText());
        e.setSqlmapFileNameSuffix(txt_sqlmap2.getText());
        e.setSqlmapFileOutput(txt_sqlmap3.getText());

        e.setDaoTestFileName(txt_daoTest1.getText());
        e.setDaoTestFileNameSuffix(txt_daoTest2.getText());
        e.setDaoTestFileOutput(txt_daoTest3.getText());

        e.setIserviceFileName(txt_iservice1.getText());
        e.setIserviceFileNameSuffix(txt_iservice2.getText());
        e.setIserviceFileOutput(txt_iservice3.getText());

        e.setServiceFileName(txt_service1.getText());
        e.setServiceFileNameSuffix(txt_service2.getText());
        e.setServiceFileOutput(txt_service3.getText());

        e.setServiceTestFileName(txt_serviceTest1.getText());
        e.setServiceTestFileNameSuffix(txt_serviceTest2.getText());
        e.setServiceTestFileOutput(txt_serviceTest3.getText());

        return e;
    }
}
