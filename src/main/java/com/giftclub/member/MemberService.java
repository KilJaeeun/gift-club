package com.giftclub.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {


    private final MemberMapper memberMapper;

    public int regist(Member member) throws SQLException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(member.getMemberPassword().getBytes());
            member.setMemberPassword(String.format("%040x", new BigInteger(1, md.digest())));
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
     return memberMapper.insertMember(member);
    }
    public Member login(Map<String, String> map) throws SQLException {
        return memberMapper.LoginMember(map);
    }

    public Member checkEmail(String email) throws SQLException {
       return  memberMapper.checkEmail(email);
    }
}
