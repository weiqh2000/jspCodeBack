package com.lcvc.ebuy.bean.TCP;


import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class PopUp {
    private JFileChooser fileChooser = new JFileChooser();
    private FileSystemView fsv = FileSystemView.getFileSystemView();
    // 实现弹窗选择文件部分的方法
    public String PopUpTest(){

        // 设置默认打开的目录为桌面目录
        fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
        // 设置弹框标题
        fileChooser.setDialogTitle("请选择要上传的文件...");
        // 设置确认按钮的value值
        fileChooser.setApproveButtonText("确定");
        // 设置弹框的显示内容
        // FILES_AND_DIRECTORIES() 方法显示全部文件
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        // 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
        int result = fileChooser.showOpenDialog(null);
        // 初始化路径变量
        String path = null;
        if (JFileChooser.APPROVE_OPTION == result) {

            // 如果点击了"确定", 则获取选择的文件路径
            path = fileChooser.getSelectedFile().getPath();
        }

        return path;
    }
}