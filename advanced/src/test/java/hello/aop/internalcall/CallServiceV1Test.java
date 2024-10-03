package hello.aop.internalcall;

import hello.aop.internalcall.aop.CallLogAspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV1Test {
    @Autowired
    CallServiceV1 callServiceV1; // 순환 참조 허용하려면 application.properties에 spring.main.allow-circular-references=true 옵션 명시 필요

    @Test
    void external(){
        callServiceV1.external();
    }

}