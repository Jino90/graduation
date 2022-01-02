package website.yoborisov.graduation.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import website.yoborisov.graduation.TimingExtension;
import static website.yoborisov.graduation.util.ValidationUtil.getRootCause;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("hsqldb")
@Sql(scripts = "classpath:db/h2/data.sql", config = @SqlConfig(encoding = "UTF-8"), executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ExtendWith(TimingExtension.class)
@ContextConfiguration
@SpringBootTest
public abstract class AbstractServiceTest implements ApplicationContextAware {

    //  Check root cause in JUnit: https://github.com/junit-team/junit4/pull/778
    protected <T extends Throwable> void validateRootCause(Class<T> rootExceptionClass, Runnable runnable) {
        assertThrows(rootExceptionClass, () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                throw getRootCause(e);
            }
        });
    }
}
