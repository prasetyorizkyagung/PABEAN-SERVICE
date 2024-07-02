package com.rtm.pabean.service.ceisa;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.rtm.pabean.config.CeisaAPI;
import com.rtm.pabean.config.HeaderConfig;
import com.rtm.pabean.config.HttpsByPass;
import com.rtm.pabean.dto.ceisa.RequestLogin;
import com.rtm.pabean.dto.ceisa.ResponseCeisa;
import com.rtm.pabean.dto.ceisa.document.bue.Peb;
import com.rtm.pabean.dto.ceisa.document.bup.Pib;
import com.rtm.pabean.utils.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@EnableAsync
public class CeisaServiceImp implements CeisaService {

    @Value("${rtm.pabean.folder.temp}")
    private String tempFolder;

    private HttpsByPass httpsByPass;

    private CeisaAPI ceisaAPI;

    private HeaderConfig headerConfig;

    @Autowired
    public CeisaServiceImp(HttpsByPass httpsByPass, CeisaAPI ceisaAPI, HeaderConfig headerConfig) {
        this.httpsByPass = httpsByPass;
        this.ceisaAPI = ceisaAPI;
        this.headerConfig = headerConfig;
    }

    @SuppressWarnings("null")
    @Override
    public ResponseCeisa login(String username, String password) {
        try {
            httpsByPass.fixIt();

            RestTemplate restTemplate = new RestTemplate();
            ResponseCeisa response = restTemplate.postForObject(ceisaAPI.getLogin(), new RequestLogin(username, password), ResponseCeisa.class);
            return response;
        } catch (HttpStatusCodeException e) {
            log.error(e.getLocalizedMessage(), e);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }
        return null;
    }

    @SuppressWarnings("null")
    @Override
    public String submitPib(String car, String token, boolean isFinal, Pib pib) {
        try {
            if (pib == null) {
                ResponseCeisa responseCeisa = new ResponseCeisa();
                responseCeisa.setStatus("Error");
                responseCeisa.setMessage(String.format("Data PIB not found in database car [%s]", car));
                return JsonUtil.createJsonString(responseCeisa);
            }

            httpsByPass.fixIt();
            
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Pib> request = new HttpEntity<>(pib, headerConfig.headerCeisa(token));
            ResponseEntity<ResponseCeisa> response = restTemplate.exchange(String.format(ceisaAPI.getSubmit(), isFinal), HttpMethod.POST, request, ResponseCeisa.class);
            return JsonUtil.createJsonString(response.getBody());
        } catch (HttpStatusCodeException e) {
            log.error(e.getLocalizedMessage(), e);
            return e.getResponseBodyAsString();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
            return e.getLocalizedMessage();
        }
        
    }

    @SuppressWarnings("null")
    @Override
    public String submitPeb(String car, String token, boolean isFinal, Peb peb) {
        try {
            if (peb == null) {
                ResponseCeisa responseCeisa = new ResponseCeisa();
                responseCeisa.setStatus("Error");
                responseCeisa.setMessage(String.format("Data PEB not found in database car [%s]", car));
                return JsonUtil.createJsonString(responseCeisa);
            }

            httpsByPass.fixIt();
            
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Peb> request = new HttpEntity<>(peb, headerConfig.headerCeisa(token));
            ResponseEntity<ResponseCeisa> response = restTemplate.exchange(String.format(ceisaAPI.getSubmit(), isFinal), HttpMethod.POST, request, ResponseCeisa.class);
            return JsonUtil.createJsonString(response.getBody());
        } catch (HttpStatusCodeException e) {
            log.error(e.getLocalizedMessage(), e);
            return e.getResponseBodyAsString();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
            return e.getLocalizedMessage();
        }
    }

    @SuppressWarnings("null")
    @Override
    public ResponseCeisa responseByCar(String car, String token) {
        try {
            httpsByPass.fixIt();

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<?> request = new HttpEntity<>(headerConfig.headerCeisa(token));
            ResponseEntity<ResponseCeisa> response = restTemplate.exchange(ceisaAPI.getResponseByCar(), HttpMethod.GET, request, ResponseCeisa.class, car);
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            log.error(e.getLocalizedMessage(), e);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }
        return null;
    }

    @SuppressWarnings("null")
    @Override
    public String downloadResponseS3(String token, String filepath) {
        try {
            httpsByPass.fixIt();

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<?> request = new HttpEntity<>(headerConfig.headerCeisa(token));

            File file = new File(filepath);
            String path = file.getAbsolutePath();
            String fileName = file.getName();

            Map<String, Object> param = new HashMap<>();
            param.put("path", path);
            param.put("fileName", fileName);
            param.put("bucket", "singlecore");

            ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(ceisaAPI.getDownloadResponseS3(), HttpMethod.GET, request, new ParameterizedTypeReference<Map<String, Object>>() {}, param);
            Object data = responseEntity.getBody().get("data");
            return data == null ? null : data.toString();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }
        return null;
    }

    @SuppressWarnings("null")
    @Override
    public Path downloadResponse(String token, String filePath) {
        try {
            httpsByPass.fixIt();

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<?> request = new HttpEntity<>(headerConfig.headerCeisa(token));

            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(ceisaAPI.getDownloadResponse(), HttpMethod.GET, request, byte[].class, filePath);
            Path path = Paths.get(tempFolder + UUID.randomUUID().toString() + ".pdf");
            Files.write(path, responseEntity.getBody());
            return path;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }
        return null;
    }
    
}
