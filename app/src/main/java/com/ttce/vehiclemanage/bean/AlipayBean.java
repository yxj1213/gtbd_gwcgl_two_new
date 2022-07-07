package com.ttce.vehiclemanage.bean;

public class AlipayBean {

    private AlipayUserCertdocCertverifyPreconsultResponseBean alipay_User_Certdoc_Certverify_Preconsult_Response;
    private String sign;
    private String auth_url;

    public String getAuth_url() {
        return auth_url;
    }

    public void setAuth_url(String auth_url) {
        this.auth_url = auth_url;
    }

    public AlipayUserCertdocCertverifyPreconsultResponseBean getAlipay_User_Certdoc_Certverify_Preconsult_Response() {
        return alipay_User_Certdoc_Certverify_Preconsult_Response;
    }

    public void setAlipay_User_Certdoc_Certverify_Preconsult_Response(AlipayUserCertdocCertverifyPreconsultResponseBean alipay_User_Certdoc_Certverify_Preconsult_Response) {
        this.alipay_User_Certdoc_Certverify_Preconsult_Response = alipay_User_Certdoc_Certverify_Preconsult_Response;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public static class AlipayUserCertdocCertverifyPreconsultResponseBean {
        private String code;
        private String msg;
        private String verify_id;
        private String sub_code;
        private String sub_msg;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getVerify_id() {
            return verify_id;
        }

        public void setVerify_id(String verify_id) {
            this.verify_id = verify_id;
        }

        public String getSub_code() {
            return sub_code;
        }

        public void setSub_code(String sub_code) {
            this.sub_code = sub_code;
        }

        public String getSub_msg() {
            return sub_msg;
        }

        public void setSub_msg(String sub_msg) {
            this.sub_msg = sub_msg;
        }
    }
}
