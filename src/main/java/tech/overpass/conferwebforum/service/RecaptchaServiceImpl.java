package tech.overpass.conferwebforum.service;

import tech.overpass.conferwebforum.captcha.RecaptchaConfig;
import tech.overpass.conferwebforum.captcha.RecaptchaUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecaptchaServiceImpl implements RecaptchaService {

    @Autowired
    private RecaptchaConfig recaptchaConfig;

    private static final String GOOGLE_RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public String verifyRecaptcha(String ip, String recaptchaResponse) {
        String secret = recaptchaConfig.getSecret();
        Map<String, String> body = new HashMap<>();
        body.put("secret", secret);
        body.put("response", recaptchaResponse);
        body.put("remoteip", ip);
        ResponseEntity<Map> recaptchaResponseEntity = restTemplateBuilder
                .build()
                .postForEntity(GOOGLE_RECAPTCHA_VERIFY_URL +
                                "?secret={secret}&response={response}&remoteip={remoteip}",
                        body,
                        Map.class,
                        body);
        Map<String, Object> responseBody = recaptchaResponseEntity.getBody();

        boolean recaptchaSucess = (Boolean) responseBody.get("success");
        if (!recaptchaSucess) {
            List<String> errorCodes = (List) responseBody.get("error-codes");

            String errorMessage = errorCodes.stream()
                    .map(RecaptchaUtil.RECAPTCHA_ERROR_CODE::get)
                    .collect(Collectors.joining(", "));

            return errorMessage;
        } else {
            return StringUtils.EMPTY;
        }
    }
}
