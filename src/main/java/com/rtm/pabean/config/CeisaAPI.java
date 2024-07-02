package com.rtm.pabean.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CeisaAPI {

    @Value("${rtm.pabean.ceisa.host}")
    private String host;

    private String login = "https://apis-gw.beacukai.go.id/v2/auth-amws/v1/user/login";
    
    private String submit = "/openapi/document?isFinal=%s";
    
    private String responseByCar = "/openapi/status/{car}";

    private String responseByNpwp = "/openapi/status?idPerusahaan=%s";

    private String downloadResponseS3 = "/openapi/respon/download-s3?path={path}&fileName={fileName}&bucket={bucket}";

    private String downloadResponse = "/openapi/download-respon?path={path}";

    public String getLogin() {
        return this.login;
    }


    public String getSubmit() {
        return this.host + this.submit;
    }

    public String getResponseByCar() {
        return this.host + this.responseByCar;
    }

    public String getResponseByNpwp() {
        return this.host + this.responseByNpwp;
    }

    public String getDownloadResponseS3() {
        return this.host + this.downloadResponseS3;
    }

    public String getDownloadResponse() {
        return this.host + this.downloadResponse;
    }
    
}
