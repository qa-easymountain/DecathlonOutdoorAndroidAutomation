package com.decathlon.outdoor;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite()
//@SelectClasses({Login.class})
//@SelectClasses({RandomHikeDownload.class})
//@SelectClasses({RandomHikeAddToFavorite.class})
@SelectClasses({Login.class, RandomHikeDownload.class, RandomHikeAddToFavorite.class})
public class DecathlonOutdoorTest {
}  	