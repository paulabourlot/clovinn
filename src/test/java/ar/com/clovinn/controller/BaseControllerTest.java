package ar.com.clovinn.controller;

import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import ar.com.clovinn.suite.IntegrationTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value={"test"})
@AutoConfigureMockMvc
@Category(IntegrationTest.class)
@Transactional
public abstract class BaseControllerTest {
	
	protected static final String CONTENT_TYPE_HTML = "text/html;charset=UTF-8";
	protected static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
	protected static final String CONTENT_TYPE_PDF = "application/pdf";
	protected static final int TAMANYO_PAGINA = 15;
	protected static final int TAMANYO_PAGINA_REST = 20;
	
	@Autowired
	protected MockMvc mockMvc;

}
