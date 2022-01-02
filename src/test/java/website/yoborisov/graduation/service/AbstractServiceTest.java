package website.yoborisov.graduation.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import website.yoborisov.graduation.GraduationApplication;
import website.yoborisov.graduation.TimingExtension;
import static website.yoborisov.graduation.util.ValidationUtil.getRootCause;

import static org.junit.jupiter.api.Assertions.assertThrows;


//@ExtendWith(SpringExtension.class)
//@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
//@SpringJUnitConfig(locations = {"classpath:application.properties"})
//@SpringJUnitConfig(locations = {
//        "classpath:spring/inmemory.xml"
//})
@ActiveProfiles("hsqldb")
//@SpringJUnitConfig(locations = {"classpath:spring/inmemory.xml"})
//@SpringJUnitConfig(classes = GraduationApplication.class)
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
