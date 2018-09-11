package com.example.democore;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class AnnotatedClassTest {

    @Test
    public void testLogin() {
        AnnotatedClass annotatedClass = new AnnotatedClass();

        // I have to use reflection magic to set private field:
        MyRepository myRepository = new MyRepository("Jano");
        ReflectionTestUtils.setField(annotatedClass,"myRepository", myRepository);

        Assertions.assertThat(annotatedClass.login("Fero")).isEqualTo(false);
    }
}