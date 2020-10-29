package com.ms.module.wechat.clear.utils;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.ms.module.wechat.clear.activity.file.FileChildNode;
import com.ms.module.wechat.clear.activity.file.FileHeaderNode;

import java.util.List;

public class ListDataUtils {

    public static boolean checkFileChildNode(List<BaseNode> datas) {
        boolean check = true;
        for (int i = 0; i < datas.size(); i++) {
            BaseNode baseNode = datas.get(i);
            if (baseNode instanceof FileChildNode) {
                FileChildNode fileChildNode = (FileChildNode) baseNode;
                if (!fileChildNode.isCheck()) {
                    check = false;
                }
            }
        }
        return check;
    }


    public static boolean checkAllNode(List<BaseNode> datas) {
        boolean check = true;
        for (int i = 0; i < datas.size(); i++) {
            BaseNode baseNode = datas.get(i);
            if (baseNode instanceof FileHeaderNode) {
                FileHeaderNode headerNode = (FileHeaderNode) baseNode;
                List<BaseNode> childNode = headerNode.getChildNode();
                for (int j = 0; j < childNode.size(); j++) {
                    BaseNode baseNodeT = childNode.get(j);
                    if (baseNodeT instanceof FileChildNode) {
                        FileChildNode childNode1 = (FileChildNode) baseNodeT;
                        if (!childNode1.isCheck()) {
                            check = false;
                        }
                    }
                }
            }
        }
        return check;
    }


    public static void setCheck(List<BaseNode> datas, boolean check) {
        for (int i = 0; i < datas.size(); i++) {
            BaseNode baseNode1 = datas.get(i);
            if (baseNode1 instanceof FileChildNode) {
                FileChildNode voiceChildNode = (FileChildNode) baseNode1;
                voiceChildNode.setCheck(false);
            }
        }
    }


}
