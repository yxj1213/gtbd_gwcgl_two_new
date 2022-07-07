package com.ttce.vehiclemanage.utils;

import com.ttce.vehiclemanage.R;

public class CarStateFactory {

    public static int getDeptIconByLevel(int level) {
        int resourceId = R.mipmap.company_level_1;
        switch (level) {
            case 1:
                resourceId = R.mipmap.company_level_1;
                break;
            case 2:
                resourceId = R.mipmap.company_level_2;
                break;
            case 3:
                resourceId = R.mipmap.company_level_3;
                break;
            case 4:
                resourceId = R.mipmap.company_level_4;
                break;
            case 5:
                resourceId = R.mipmap.company_level_5;
                break;
            case 6:
                resourceId = R.mipmap.company_level_6;
                break;
            case 7:
                resourceId = R.mipmap.company_level_7;
                break;
            case 8:
                resourceId = R.mipmap.company_level_8;
                break;
            case 9:
                resourceId = R.mipmap.company_level_9;
                break;
            case 10:
                resourceId = R.mipmap.company_level_10;
                break;
        }
        return resourceId;
    }

    public static int getCarColorByStatus(String status) {
        int resourceId = R.mipmap.icon_outline;
        switch (status) {
//            case ONLINE://
//            case STOP:
            case "green":
                resourceId = R.mipmap.icon_online;
                break;
            case "blue":
                resourceId = R.mipmap.icon_drive;
                break;
            case "orange":
                resourceId = R.mipmap.icon_ungps;
                break;
           // case OUTLINE:
            case "gray":
                resourceId = R.mipmap.icon_outline;
                break;
            case "red":
                resourceId = R.mipmap.icon_sos;
                break;
            case "purple":
                resourceId = R.mipmap.icon_exception;
                break;
        }
        return resourceId;
    }

    public static int getCarTextColorByStatus(String status) {
        int resourceId = R.color.car_status_outline;
        switch (status) {
//            case ONLINE://
//            case STOP:
            case "green":
                resourceId = R.color.car_status_online;
                break;
            case "blue":
                resourceId = R.color.car_status_drive;
                break;
            case "orange":
                resourceId = R.color.car_status_ungps;
                break;
          //  case OUTLINE:
            case "gray":
                resourceId = R.color.car_status_outline;
                break;
            case "red":
                resourceId = R.color.car_status_sos;
                break;
            case "purple":
                resourceId = R.color.car_status_exception;
                break;
        }
        return resourceId;
    }


    /**
     * 获取车辆的方向
     *
     * @param direction
     * @return
     */
    public static String getCarRouteByState(int direction) {
        if (direction < 22.5 || direction > 337.5) {
            return "正北";
        } else if (direction > 22.5 && direction < 67.5) {
            return "东北";
        } else if (direction > 67.5 && direction < 112.5) {
            return "正东";
        } else if (direction > 112.5 && direction < 157.5) {
            return "东南";
        } else if (direction > 157.5 && direction < 202.5) {
            return "正南";
        } else if (direction > 202.5 && direction < 247.5) {
            return "西南";
        } else if (direction > 247.5 && direction < 292.5) {
            return "正西";
        } else if (direction > 292.5 && direction < 337.5) {
            return "西北";
        }
        return "未知";
    }

    public enum StatusType {
        ONLINE("0"),
        DRIVE("1"),
        STOP("2"),
        UN_LOCATION("3"),
        OUTLINE("4"),
        WARING("5"),
        EXPIRE("6"),
        EXCEPTION("7"),
        TASK("8");


        private String status;

        private StatusType(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        /**
         * 根据类型的名称，返回类型的枚举实例。
         *
         * @param status 类型名称
         */
        public static StatusType fromStatus(String status) {
            for (StatusType type : StatusType.values()) {
                if (type.getStatus() .equals(status)) {
                    return type;
                }
            }
            return null;
        }
    }
}
