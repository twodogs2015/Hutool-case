package com.twodog.util;

import cn.hutool.core.swing.clipboard.ClipboardUtil;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;

public class HutoolClipboardUtil {
    public static void main(String[] args) {
        getClipboard();


    }

    public static void getClipboard(){
        Clipboard clipboard = ClipboardUtil.getClipboard();
        Object o = ClipboardUtil.get(new DataFlavor());

        System.out.println(o);
    }
}
