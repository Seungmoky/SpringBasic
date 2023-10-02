package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        // 이런 반복문 자동 생성하는 방법 iter 을 입력하고 자동완성
        for (String beanDefinitionName : beanDefinitionNames) {
            // getBeanDefinition() 메소드를 사용하려면 AnnotationConfigApplicationContext로 선언해야한다
            // ApplicationContext로 선언하면 지원하지 않는 메소드이다.
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                // 출력문 자동완성 soutv 를 입력하고 자동완성
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                        "beanDefinition = " + beanDefinition);
            }

        }
    }
}
