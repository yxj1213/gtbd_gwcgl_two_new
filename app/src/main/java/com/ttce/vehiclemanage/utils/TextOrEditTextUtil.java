package com.ttce.vehiclemanage.utils;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/9/19.
 */

public class TextOrEditTextUtil {

  public static void textStyleBoldUtil(TextView txt,String str){
      txt.setText(str);
      txt.setGravity(Gravity.RIGHT);
      txt.getPaint().setFakeBoldText(true);
  }
    public static void editStyleBoldUtil(EditText txt,String str){
        txt.setText(str);
        txt.getPaint().setFakeBoldText(true);
    }
  public static void editStyleBoldUtil(EditText editText){
      editText.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {
              //这个方法被调用，说明在s字符串中，从start位置开始的count个字符即将被长度为after的新文本所取代。
              // 在这个方法里面改变s，会报错。
          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
              //这个方法被调用，说明在s字符串中，从start位置开始的count个字符刚刚取代了长度为before的旧文本。
              // 在这个方法里面改变s，会报错。
          }

          @Override
          public void afterTextChanged(Editable s) {
              //这个方法被调用，那么说明s字符串的某个地方已经被改变。
              editText.getPaint().setFakeBoldText(true);
          }
      });
  }
}
