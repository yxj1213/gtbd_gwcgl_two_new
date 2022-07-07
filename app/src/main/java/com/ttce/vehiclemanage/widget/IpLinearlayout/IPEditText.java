package com.ttce.vehiclemanage.widget.IpLinearlayout;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.utils.OtherUtil;


public class IPEditText extends LinearLayout {
    private static final String TAG = "IPEditText";
    private EditText mFirstIP;
    private EditText mSecondIP;
    private EditText mThirdIP;
    private EditText mFourthIP;
    private boolean NullAndPoint = false;

    public IPEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        /**
         * 初始化控件
         */
        View view = LayoutInflater.from(context).inflate(
                R.layout.ip_edit_text, this);
        mFirstIP = (EditText) findViewById(R.id.ip_first);
        mSecondIP = (EditText) findViewById(R.id.ip_second);
        mThirdIP = (EditText) findViewById(R.id.ip_third);
        mFourthIP = (EditText) findViewById(R.id.ip_fourth);
        OperatingEditText(context);
    }

    /**
     * 获得EditText中的内容,当每个Edittext的字符达到三位时,自动跳转到下一个EditText,当用户点击.时,
     * 下一个EditText获得焦点
     */
    private void OperatingEditText(final Context context) {
        mFirstIP.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                /*
                 * 获得EditTe输入内容,做判断,如果大于255,提示不合法,当数字为合法的三位数下一个EditText获得焦点,
                 * 用户点击.时,下一个EditText获得焦点
                 */
                if (s != null && s.length() > 0 && mFirstIP.getSelectionStart() != 0) {
                    Log.i(TAG, s.toString());

                    if (!s.toString().contains(".") && Integer.parseInt(s.toString()) > 255) {
                        Toast.makeText(context, "请输入合法的格式",
                                Toast.LENGTH_LONG).show();
//                        mFirstIP.setText("255");
                        mFirstIP.setSelection(0);
                    } else if (s.length() > 2) {
                        mSecondIP.setFocusable(true);
                        mSecondIP.requestFocus();
                        if (mSecondIP.getText().toString().length() > 0) {
                            mSecondIP.selectAll();
                        }

                    } else {
                        mFirstIP.requestFocus();
                        mFirstIP.setSelection(s.length());
                    }
                    if (s.toString().length() == 1 && s.toString().equals(".")) {
                        mFirstIP.setText("");
                    }
                    if (s.toString().contains(".") && s.toString().length() > 1) {
                        //mFirstIP.setText(s.subSequence(0,s.length()-1));
                        mFirstIP.setText(s.toString().subSequence(0, s.toString().indexOf(".")) + s.toString().substring(s.toString().indexOf(".") + 1, s.toString().length()));
                        mSecondIP.setFocusable(true);
                        mSecondIP.requestFocus();
                        if (mSecondIP.getText().toString().length() > 0) {
                            mSecondIP.selectAll();
                        }
                    }
                    str1 = mFirstIP.getText().toString().trim();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mSecondIP.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                /*
                 * 获得EditTe输入内容,做判断,如果大于255,提示不合法,当数字为合法的三位数下一个EditText获得焦点,
                 * 用户点击啊.时,下一个EditText获得焦点
                 */
                if (s != null && s.length() > 0 && mSecondIP.getSelectionStart() != 0) {
                    Log.i(TAG, s.toString());
                    if (!s.toString().contains(".") && Integer.parseInt(s.toString()) > 255) {
                        Toast.makeText(context, "请输入合法的格式",
                                Toast.LENGTH_LONG).show();
                        mSecondIP.setText("255");
                        mSecondIP.setSelection(0);
                    } else if (s.length() > 2) {
                        mThirdIP.setFocusable(true);
                        mThirdIP.requestFocus();
                        if (mThirdIP.getText().toString().length() > 0) {
                            mThirdIP.selectAll();
                        }

                    } else {
                        mSecondIP.requestFocus();
                        mSecondIP.setSelection(s.length());
                    }
                    if (s.toString().length() == 1 && s.toString().equals(".")) {
                        NullAndPoint = true;
                        mSecondIP.setText("");
                    }
                    if (s.toString().contains(".") && s.toString().length() > 1) {
                        //mFirstIP.setText(s.subSequence(0,s.length()-1));
                        mSecondIP.setText(s.toString().subSequence(0, s.toString().indexOf(".")) + s.toString().substring(s.toString().indexOf(".") + 1, s.toString().length()));
                        mThirdIP.setFocusable(true);
                        mThirdIP.requestFocus();
                        if (mThirdIP.getText().toString().length() > 0) {
                            mThirdIP.selectAll();
                        }
                    }
                    str2 = mSecondIP.getText().toString().trim();
                }
                /*
                 * 当用户需要删除时,此时的EditText为空时,上一个EditText获得焦点
                 */
                if (start == 0 && s.length() == 0) {
                    if (NullAndPoint) {
                        NullAndPoint = false;
                    } else {
                        mFirstIP.setFocusable(true);
                        mFirstIP.requestFocus();
                        mFirstIP.setSelection(mFirstIP.getText().length());
                    }

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mThirdIP.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                /*
                 * 获得EditTe输入内容,做判断,如果大于255,提示不合法,当数字为合法的三位数下一个EditText获得焦点,
                 * 用户点击啊.时,下一个EditText获得焦点
                 */
                if (s != null && s.length() > 0 && mThirdIP.getSelectionStart() != 0) {
                    Log.i(TAG, s.toString());
                    if (!s.toString().contains(".") && Integer.parseInt(s.toString()) > 255) {
                        Toast.makeText(context, "请输入合法的格式",
                                Toast.LENGTH_LONG).show();
                        mThirdIP.setText("255");
                        mThirdIP.setSelection(0);
                    } else if (s.length() > 2) {
                        mFourthIP.setFocusable(true);
                        mFourthIP.requestFocus();
                        if (mFourthIP.getText().toString().length() > 0) {
                            mFourthIP.selectAll();
                        }

                    } else {
                        mThirdIP.requestFocus();
                        mThirdIP.setSelection(s.length());
                    }
                    if (s.toString().length() == 1 && s.toString().equals(".")) {
                        NullAndPoint = true;
                        mThirdIP.setText("");
                    }
                    if (s.toString().contains(".") && s.toString().length() > 1) {
                        //mFirstIP.setText(s.subSequence(0,s.length()-1));
                        mThirdIP.setText(s.toString().subSequence(0, s.toString().indexOf(".")) + s.toString().substring(s.toString().indexOf(".") + 1, s.toString().length()));

                        mFourthIP.setFocusable(true);
                        mFourthIP.requestFocus();
                        if (mFourthIP.getText().toString().length() > 0)
                            mFourthIP.selectAll();
                    }
                    str3 = mThirdIP.getText().toString().trim();
                }

                /*
                 * 当用户需要删除时,此时的EditText为空时,上一个EditText获得焦点
                 */
                if (start == 0 && s.length() == 0) {
                    if (NullAndPoint) {
                        NullAndPoint = false;
                    } else {
                        mSecondIP.setFocusable(true);
                        mSecondIP.requestFocus();
                        mSecondIP.setSelection(mSecondIP.getText().length());
                    }

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mFourthIP.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                /*
                 * 获得EditTe输入内容,做判断,如果大于255,提示不合法,当数字为合法的三位数下一个EditText获得焦点,
                 * 用户点击啊.时,下一个EditText获得焦点
                 */
                if (s != null && s.length() > 0 && mFourthIP.getSelectionStart() != 0) {
                    Log.d("控件返回结果值",str4+"---");
                    Log.i(TAG, s.toString());
                    if (!s.toString().contains(".") && Integer.parseInt(s.toString()) > 255) {
                        Toast.makeText(context, "请输入合法的格式",
                                Toast.LENGTH_LONG).show();
                        mFourthIP.setText("255");
                        mFourthIP.setSelection(0);
                    } else {
                        mFourthIP.requestFocus();
                        mFourthIP.setSelection(s.length());
                    }
                    if (s.toString().length() == 1 && s.toString().equals(".")) {
                        NullAndPoint = true;
                        mFourthIP.setText("");
                    }
                    if (s.toString().contains(".") && s.toString().length() > 1) {
                        //mFirstIP.setText(s.subSequence(0,s.length()-1));
                        mFourthIP.setText(s.toString().subSequence(0, s.toString().indexOf(".")) + s.toString().substring(s.toString().indexOf(".") + 1, s.toString().length()));
                    }
                    str4 = mFourthIP.getText().toString().trim();
                }

                /*
                 * 当用户需要删除时,此时的EditText为空时,上一个EditText获得焦点
                 */
                if (start == 0 && s.length() == 0) {
                    if (NullAndPoint) {
                        NullAndPoint = false;
                    } else {
                        mThirdIP.setFocusable(true);
                        mThirdIP.requestFocus();
                        mThirdIP.setSelection(mThirdIP.getText().length());
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public String getText(Context context) {
        if (TextUtils.isEmpty(mFirstIP.getText().toString()) || TextUtils.isEmpty(mSecondIP.getText().toString())
                || TextUtils.isEmpty(mThirdIP.getText().toString()) || TextUtils.isEmpty(mFourthIP.getText().toString())) {
            return "";
        }
        String ipadress = str1 + "." + str2 + "." + str3 + "." + str4;
        Log.i(TAG, "控件返回结果值：" + ipadress);
        return ipadress;
    }

    String str1 = "";
    String str2 = "";
    String str3 = "";
    String str4 = "";

    public void setText(String s, boolean isShow, int id) {
        if (s == null || s.equals("")) return;
        String[] ss = s.split("\\.|\\:");
        for (int i = 0; i < ss.length; i++) {
            str1 = ss[0];
            str2 = ss[1];
            str3 = ss[2];
            str4 = ss[3];
            if (isShow == true) {
                setEnable(true, id);

                if (i == 0) mFirstIP.setText(ss[0]);
                else if (i == 1) mSecondIP.setText(ss[1]);
                else if (i == 2) mThirdIP.setText(ss[2]);
                else if (i == 3) mFourthIP.setText(ss[3]);
            } else {
                if (i == 0) mFirstIP.setText(OtherUtil.setIp(ss[0]));
                else if (i == 1) mSecondIP.setText(OtherUtil.setIp(ss[1]));
                else if (i == 2) mThirdIP.setText(OtherUtil.setIp(ss[2]));
                else if (i == 3) mFourthIP.setText(OtherUtil.setIp(ss[3]));

                setEnable(false, id);
            }
            Log.i(TAG, mFirstIP.getText().toString() + "　" + mSecondIP.getText().toString() + " " + mThirdIP.getText().toString() + " " + mFourthIP.getText().toString());
        }
    }

    public void setEnable(boolean isenable, int id) {
        if (id == 0) {
            mFirstIP.setEnabled(false);
            mSecondIP.setEnabled(false);
            mThirdIP.setEnabled(false);
            mFourthIP.setEnabled(false);
        } else {
            //不可编辑状态：
            mFirstIP.setEnabled(isenable);
            mSecondIP.setEnabled(isenable);
            mThirdIP.setEnabled(isenable);
            mFourthIP.setEnabled(isenable);
        }

    }
}