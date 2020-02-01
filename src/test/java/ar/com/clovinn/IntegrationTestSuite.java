package ar.com.clovinn;

import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;

import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

import ar.com.clovinn.suite.IntegrationTest;

@RunWith(WildcardPatternSuite.class)
@SuiteClasses({"**/*Test.class"})
@IncludeCategory(IntegrationTest.class)
public class IntegrationTestSuite {
}
