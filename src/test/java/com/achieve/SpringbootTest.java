package com.achieve;

import com.achieve.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AchievementApplication.class)
public class SpringbootTest {

    @Autowired
    private UserController testController;

    @Test
    public void test(){
        Stream<Integer> sortedReverseStreamV2 = Stream.of(1,3,7,4,5,8,6,2).sorted((Integer o1, Integer o2) -> o1 - o2);
        sortedReverseStreamV2.collect(Collectors.toList()).forEach(x -> System.out.print(x + " "));
    }
}
