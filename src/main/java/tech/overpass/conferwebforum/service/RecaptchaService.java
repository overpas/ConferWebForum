package tech.overpass.conferwebforum.service;

public interface RecaptchaService {

    String verifyRecaptcha(String ip, String recaptchaResponse);

}
