package com.ms.module.wechat.clear.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ms.module.wechat.clear.R;

public class DialogDelete {

    public static interface Call {

        void onCancel();

        void onDelete();

    }

    public static void show(Activity activity, Call call) {
        View view = View.inflate(activity, R.layout.dialog_delete, null);
        Dialog dialog = new Dialog(activity, R.style.DialogManager);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(view);

        TextView textViewTitle = view.findViewById(R.id.textViewTitle);
        TextView textViewContext = view.findViewById(R.id.textViewContext);
        Button buttonCancel = view.findViewById(R.id.buttonCancel);
        Button buttonDelete = view.findViewById(R.id.buttonDelete);


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (call != null) {
                    call.onCancel();
                    dialog.dismiss();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (call != null) {
                    call.onDelete();
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }
}
