/*
 * Copyright (c) 2016-present 贵州纳雍穿青人李裕江<1032694760@qq.com>
 *
 * The software is licensed under the Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 *     http://license.coscl.org.cn/MulanPSL2
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR
 * PURPOSE.
 * See the Mulan PSL v2 for more details.
 */

package com.ttce.vehiclemanage.utils.OrderTimeSelect;


import androidx.annotation.NonNull;

import com.github.gzuliyujiang.wheelpicker.contract.LinkageProvider;
import com.jaydenxiao.common.commonutils.ToastUitl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * @author 贵州山野羡民（1032694760@qq.com）
 * @since 2021/6/7 12:22
 */
public class AntFortuneLikeProvider implements LinkageProvider {

    @Override
    public boolean firstLevelVisible() {
        return true;
    }

    @Override
    public boolean thirdLevelVisible() {
        return true;
    }

    @NonNull
    @Override
    public List<String> provideFirstData() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd", Locale.getDefault());
//先取得今天的日历日时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
//转换得到今天的日期
        String today = sdf.format(calendar.getTime());

//转换得明天的日期
        calendar.add(calendar.DATE, +1);
        String tomorrow = sdf.format(calendar.getTime());

//转换得后天的日期
        calendar.add(calendar.DATE, +1);
        String day_after_tomorrow= sdf.format(calendar.getTime());


        return Arrays.asList("现在", today+" 今天", tomorrow +" 明天", day_after_tomorrow +" 后天");
    }

    @NonNull
    @Override
    public List<String> linkageSecondData(int firstIndex) {
        switch (firstIndex) {
            case 0:
                return new ArrayList<>();
            case 1:
                List<String> list=new ArrayList<>();
                Calendar cal=Calendar.getInstance();
                 // 当前小时
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                // 当前分钟
                int mimute = cal.get(Calendar.MINUTE);
                for (int i=hour;i<24;i++){
                    if(mimute>45&&mimute<=60){
                        list.add(i+1+"点");
                    }else{
                        list.add(i+"点");
                    }
                }
                return list;
        }
        return Arrays.asList("0点","1点","2点","3点","4点","5点","6点","7点","8点","9点","10点","11点","12点","13点","14点","15点","16点","17点","18点","19点","20点","21点","22点","23点");
    }

    @NonNull
    @Override
    public List<String> linkageThirdData(int firstIndex, int secondIndex) {
        if(firstIndex==1&&secondIndex==0){
            List<String> list=new ArrayList<>();
            Calendar cal=Calendar.getInstance();
            // 当前分钟
            int mimute = cal.get(Calendar.MINUTE);
            if(mimute>45&&mimute<=60){
                return Arrays.asList("00分","15分", "30分", "45分");
            }else{
                for (int i=mimute;i<60;i++){
                    if(i%15==0){
                        list.add(i+"分");
                    }
                }
                return list;
            }
        }else if(firstIndex==0){
            return new ArrayList<>();
        }else{
            return Arrays.asList("00分","15分", "30分", "45分");
        }
    }

    @Override
    public int findFirstIndex(Object firstValue) {
        return 0;
    }

    @Override
    public int findSecondIndex(int firstIndex, Object secondValue) {
        return 0;
    }

    @Override
    public int findThirdIndex(int firstIndex, int secondIndex, Object thirdValue) {
        return 0;
    }

}
