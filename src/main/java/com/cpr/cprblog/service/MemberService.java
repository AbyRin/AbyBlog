package com.cpr.cprblog.service;

import com.cpr.cprblog.entity.Member;
import com.cpr.cprblog.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    public boolean existsById(String email) {
        return memberRepository.existsById(email);
    }
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }


    public Member save(Member member) {
        return memberRepository.save(member);
    }
}