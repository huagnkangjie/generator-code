package com.kabasiji.generator.util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 弹窗工具
 *
 * @author huang_kangjie
 * @date 2020/7/23 0023 13:38
 */
public class DailogUtil {

     public static void openDailog(String path) throws IOException {
          int flag = JOptionPane.showConfirmDialog(null, "是否打开文件所在目录", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
          //如果这个整数等于JOptionPane.YES_OPTION，则说明你点击的是“确定”按钮，则允许继续操作，否则结束
          if (flag == JOptionPane.YES_OPTION) {
               Desktop.getDesktop().open(new File(path));
          }
     }

}

