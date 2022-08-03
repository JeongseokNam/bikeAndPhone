package com.kong.bike.iamPort;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.Certification;
import com.siot.IamportRestClient.response.IamportResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class IamPortController {

    @PostMapping("/certifications")
    @ResponseBody
    public String certifications(String imp_uid,String type) {
        String imp_key = "5060508556755327";
        String imp_secret = "Xl30MvTzqiJfdRVxwx4KepiS7tgSbZ7D56ok1vTJZ42e84ZJub5HYKSxR5W5iimT8CUIwnGQzOoYbXy2";
        IamportClient client = new IamportClient(imp_key, imp_secret);
        IamportResponse<Certification> certificationIamportResponse;
        try {
            certificationIamportResponse = client.certificationByImpUid(imp_uid);
        } catch (IamportResponseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*System.out.println(type);
        System.out.println("getMessage ->" + certificationIamportResponse.getMessage());
        System.out.println("getResponse ->" + certificationIamportResponse.getResponse()); //리턴객체값

        System.out.println("getBirth ->" + certificationIamportResponse.getResponse().getBirth().toString()); //생년월일정보
        System.out.println("getGender ->" + certificationIamportResponse.getResponse().getGender()); //성별
        System.out.println("getName ->" + certificationIamportResponse.getResponse().getName()); //이름
        System.out.println("getPgProvider ->" + certificationIamportResponse.getResponse().getPgProvider()); //결제사정보
        System.out.println("getImpUid ->" + certificationIamportResponse.getResponse().getImpUid()); //고유인증번호
        System.out.println("getMerchantUid ->" + certificationIamportResponse.getResponse().getMerchantUid()); //고유주문번호
        System.out.println("getPgTid ->" + certificationIamportResponse.getResponse().getPgTid()); //인증사id
        System.out.println("getUniqueKey ->" + certificationIamportResponse.getResponse().getUniqueKey()); //고유키 - 카카오는불가
        System.out.println("getOrigin ->" + certificationIamportResponse.getResponse().getOrigin()); //인증 원주소
        System.out.println("getCarrier ->" + certificationIamportResponse.getResponse().getCarrier()); //통신사
        System.out.println("getPhone ->" + certificationIamportResponse.getResponse().getPhone()); //휴대폰번호*/
        String name =certificationIamportResponse.getResponse().getName();
        String phone =certificationIamportResponse.getResponse().getPhone();
        String url = "/member/"+type+"?name="+name+"&phone="+phone;
        return url;
    }
    @PostMapping("/certifications/changePhone")
    @ResponseBody
    public Map<String,Object> changePhone(String imp_uid, String type) {
        String imp_key = "5060508556755327";
        String imp_secret = "Xl30MvTzqiJfdRVxwx4KepiS7tgSbZ7D56ok1vTJZ42e84ZJub5HYKSxR5W5iimT8CUIwnGQzOoYbXy2";
        IamportClient client = new IamportClient(imp_key, imp_secret);
        IamportResponse<Certification> certificationIamportResponse;
        try {
            certificationIamportResponse = client.certificationByImpUid(imp_uid);
        } catch (IamportResponseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String phone =certificationIamportResponse.getResponse().getPhone();
        String name = certificationIamportResponse.getResponse().getName();
        Map<String,Object> map = new HashMap<>();
        map.put("phone",phone);
        map.put("name",name);
        return map;
    }
}
