package ar.com.clovinn.services;

import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.com.clovinn.suite.IntegrationTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value={"test"})
@AutoConfigureMockMvc
@Category(IntegrationTest.class)
@Transactional
public abstract class BaseServiceTest {

}
