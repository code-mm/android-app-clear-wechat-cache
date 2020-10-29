package com.ms.module.wechat.clear.utils;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.ms.module.wechat.clear.activity.file.FileChildNode;

import java.util.List;

public class ListDataUtils {


    public static boolean check(List<BaseNode> datas) {
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


}
