package hello.aop.member;

import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    @Override
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
