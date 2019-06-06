/**
 * The MIT License
 *
 *   Copyright (c) 2019, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 */
package org.jeasy.random.randomizers.range;

import static org.jeasy.random.randomizers.range.LongRangeRandomizer.aNewLongRangeRandomizer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LongRangeRandomizerTest extends AbstractRangeRandomizerTest<Long> {

    @BeforeEach
    void setUp() {
        min = 1L;
        max = 10L;
        randomizer = aNewLongRangeRandomizer(min, max);
    }

    @Test
    void generatedValueShouldBeWithinSpecifiedRange() {
        Long randomValue = randomizer.getRandomValue();
        assertThat(randomValue).isBetween(min, max);
    }

    @Test
    void whenSpecifiedMinValueIsAfterMaxValueThenThrowIllegalArgumentException() {
        assertThatThrownBy(() -> aNewLongRangeRandomizer(max, min)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenSpecifiedMinValueIsNullThenShouldUseDefaultMinValue() {
        randomizer = aNewLongRangeRandomizer(null, max);
        Long randomLong = randomizer.getRandomValue();
        assertThat(randomLong).isLessThanOrEqualTo(max);
    }

    @Test
    void whenSpecifiedMaxvalueIsNullThenShouldUseDefaultMaxValue() {
        randomizer = aNewLongRangeRandomizer(min, null);
        Long randomLong = randomizer.getRandomValue();
        assertThat(randomLong).isGreaterThanOrEqualTo(min);
    }

    @Test
    void shouldAlwaysGenerateTheSameValueForTheSameSeed() {
        // given
        LongRangeRandomizer longRangeRandomizer = aNewLongRangeRandomizer(min, max, SEED);
        
        // when
        Long l = longRangeRandomizer.getRandomValue();

        then(l).isEqualTo(7L);
    }
}
