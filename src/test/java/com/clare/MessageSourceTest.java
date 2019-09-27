package com.clare;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageSourceTest {

    @Autowired
    private MessageSource messageSource;

    public static void main(String[] args) {
     //messageSource.getMessage()
    }

}
