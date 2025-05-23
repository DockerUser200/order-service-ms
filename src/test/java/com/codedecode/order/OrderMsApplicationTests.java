package com.codedecode.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class OrderMsApplicationTests {

	@Test
    void mainTest() {
        try (MockedStatic<SpringApplication> mockedStatic =
                     Mockito.mockStatic(SpringApplication.class)) {

            mockedStatic
                    .when(() -> SpringApplication.run(OrderMsApplication.class, eq(Mockito.any())))
                    .thenReturn(null);

            OrderMsApplication.main(new String[] {});
            OrderMsApplication application = new OrderMsApplication();
            assertNotNull(application);
        }
    }

}
