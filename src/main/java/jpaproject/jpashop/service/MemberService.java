package jpaproject.jpashop.service;

import jpaproject.jpashop.domain.Member;
import jpaproject.jpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    //회원가입
    @Transactional //변경
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }
}
