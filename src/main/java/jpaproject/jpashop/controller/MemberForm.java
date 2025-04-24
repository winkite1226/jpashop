package jpaproject.jpashop.controller;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;


@Getter @Setter
public class MemberForm {
    
    @NotEmpty(message = "아이디는 필수입니다.")
    private String loginId;
    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String password;
    
    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
