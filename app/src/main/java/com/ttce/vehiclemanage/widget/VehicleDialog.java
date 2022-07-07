package com.ttce.vehiclemanage.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.parkingwang.keyboard.KeyboardInputController;
import com.parkingwang.keyboard.OnInputChangedListener;
import com.parkingwang.keyboard.PopupKeyboard;
import com.parkingwang.keyboard.view.InputView;
import com.ttce.vehiclemanage.R;

/**
 * @author 黄浩杭 (msdx.android@qq.com)
 * @since 2018-12-14
 */
public class VehicleDialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_vehicle, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final InputView mInputView = view.findViewById(R.id.input_view);
        final Button lock_type = view.findViewById(R.id.lock_type);
        view.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkListClickListener.selectCar(mInputView.getNumber());
                dismissAllowingStateLoss();
            }
        });
        // 创建弹出键盘
        final PopupKeyboard mPopupKeyboard = new PopupKeyboard(getContext());
        // 弹出键盘内部包含一个KeyboardView，在此绑定输入两者关联。
        mPopupKeyboard.attach(mInputView, getDialog());

        // 隐藏确定按钮
        mPopupKeyboard.getKeyboardEngine().setHideOKKey(false);

        // KeyboardInputController提供一个默认实现的新能源车牌锁定按钮
        mPopupKeyboard.getController()
                .setDebugEnabled(true)
                .setSwitchVerify(false)
                .bindLockTypeProxy(new KeyboardInputController.ButtonProxyImpl(lock_type) {
                    @Override
                    public void onNumberTypeChanged(boolean isNewEnergyType) {
                        super.onNumberTypeChanged(isNewEnergyType);

                    }
                });

        mPopupKeyboard.getController().addOnInputChangedListener(new OnInputChangedListener() {
            @Override
            public void onChanged(String number, boolean isCompleted) {
                if (isCompleted) {
                    mPopupKeyboard.dismiss(getDialog().getWindow());
                }
            }

            @Override
            public void onCompleted(String number, boolean isAutoCompleted) {
                mPopupKeyboard.dismiss(getDialog().getWindow());
            }
        });
    }

    private SelectCarClickListener checkListClickListener;
    public void show(FragmentManager manager, SelectCarClickListener listener) {
        this.checkListClickListener = listener;
        super.show(manager, getTag());
    }
    public interface SelectCarClickListener {
        void selectCar(String number);
    }
}
