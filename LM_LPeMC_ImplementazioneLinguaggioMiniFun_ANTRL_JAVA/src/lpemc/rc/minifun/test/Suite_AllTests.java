package lpemc.rc.minifun.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ MiniFunFeatures_Tests.class, MiniFunPrograms_Tests.class,
		TypeSystemTests_Functions.class, TypeSystemTests_Lists.class })
public class Suite_AllTests {

}
