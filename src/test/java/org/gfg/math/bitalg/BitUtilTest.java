package org.gfg.math.bitalg;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import static org.gfg.math.bitalg.BitUtil.*;

public class BitUtilTest{
    @Test
    public void bitStringToByteArray(){
        assertThat(toByteArray("1"), is(new byte[]{1}));
        assertThat(toByteArray("0"), is(new byte[]{0}));
        assertThat(toByteArray("11111111"), is(new byte[]{ (byte) 0xFF }));
        assertThat(toByteArray("111111111"), is(new byte[]{(byte) 0xFF, 0x1}));
        assertThat(toByteArray("000000000000000000000000000000"), is(new byte[]{0,0,0,0}));
    }

    @Test
    public void oppositeSignsTest(){
        assertThat(oppositeSigns(0, 0), is(false));
        assertThat(oppositeSigns(1, 2), is(false));
        assertThat(oppositeSigns(-1, -2), is(false));
        assertThat(oppositeSigns(0, -1), is(true));
        assertThat(oppositeSigns(-1, 0), is(true));
        assertThat(oppositeSigns(-1, 2), is(true));
        assertThat(oppositeSigns(-2, 1), is(true));
    }

    @Test
    public void addOneTest(){
        assertThat(addOne(0), is(1));
        assertThat(addOne(12), is(13));
        assertThat(addOne(-1), is(0));
        assertThat(addOne(11), is(12));
    }

    @Test
    public void multBy3Dot5(){
        assertThat(multiplyBy3Dot5(0), is(0));
        assertThat(multiplyBy3Dot5(2), is(7));
        assertThat(multiplyBy3Dot5(5), is(17));
    }

    @Test
    public void powerOfFour(){
        assertThat(isPowerOf4(0), is(false));
        assertThat(isPowerOf4(1), is(true));
        assertThat(isPowerOf4(3), is(false));
        assertThat(isPowerOf4(4), is(true));
        assertThat(isPowerOf4(8), is(false));
        assertThat(isPowerOf4(16), is(true));
        assertThat(isPowerOf4(32), is(false));
        assertThat(isPowerOf4(64), is(true));
    }

    @Test
    public void toIntTest(){
        assertThat(toInt("0"), is(0));
        assertThat(toInt("1"), is(1));
        assertThat(toInt("11111111111111111111111111111111"), is(-1));
        assertThat(toInt("10101001"), is(169));
    }

    @Test
    public void toBinTest(){
        assertThat(toBin(0),  is("00000000000000000000000000000000"));
        assertThat(toBin(1),  is("00000000000000000000000000000001"));
        assertThat(toBin(169),is("00000000000000000000000010101001"));
        assertThat(toBin(-1), is("11111111111111111111111111111111"));
        assertThat(toBin(128),is("00000000000000000000000010000000"));
        assertThat(toBin(-2), is("11111111111111111111111111111110"));
    }

    @Test
    public void addTest(){
        assertThat(add(toBin(Integer.MAX_VALUE), toBin(1)), is("10000000000000000000000000000000"));
        assertThat(toInt(add(toBin(-5), toBin(5))), is(0));
        assertThat(toInt(add(toBin(-5), toBin(15))), is(10));
        assertThat(toInt(add(toBin(-5), toBin(-17))), is(-22));
        assertThat(toInt(add(toBin(-15), toBin(5))), is(-10));
        assertThat(toInt(add(toBin(5), toBin(5))), is(10));
        assertThat(toInt(add(toBin(Integer.MAX_VALUE), toBin(1))), is(Integer.MIN_VALUE));
        assertThat(toInt(add(toBin(25), toBin(11))), is(36));
    }
}