

package com.donnfelker.android.bootstrap.test;

import android.test.ActivityInstrumentationTestCase2;

import com.donnfelker.android.bootstrap.ui.CarouselActivity;
import com.donnfelker.android.bootstrap.ui.CarouselActivity_;


/**
 * Test displaying of carousel.
 */
public class CarouselTest extends ActivityInstrumentationTestCase2<CarouselActivity_> {

    /**
     * Create test for {@link CarouselActivity}
     */
    public CarouselTest() {
        super(CarouselActivity_.class);
    }

    /**
     * Verify activity exists
     */
    public void testActivityExists() {
        assertNotNull(getActivity());
    }
}
