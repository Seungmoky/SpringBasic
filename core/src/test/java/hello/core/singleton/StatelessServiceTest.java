package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatelessServiceTest {

    // A가 주문하고 조회하는 사이에 B가 주문하면 조회 값에 B주문 금액이 나온다.
    @Test
    void statelessServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatelessService statelessService1 = ac.getBean(StatelessService.class);
        StatelessService statelessService2 = ac.getBean(StatelessService.class);

        //ThreadA: A사용자 10000원 주문
        int userAPrice = statelessService1.order("userA", 10000);
        //ThreadB: B사용자 20000원 주문
        int userBPrice = statelessService2.order("userB", 20000);

        //ThreadA: 사용자A 주문 금액 조회
        System.out.println("priceA = " + userAPrice);
        System.out.println("priceB = " + userBPrice);

        Assertions.assertThat(userAPrice).isEqualTo(10000);
    }

    static class TestConfig {

        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }



}