package ru.stqa.ptf.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {
   @Test

    public void testsPrime(){
       Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
   }
    @Test
    public void testsNoPrime(){
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE-2));
    }
    @Test
    public void testsInNoPrime(){
        Assert.assertFalse(Primes.isPrime(4));
    }
    @Test
    public void testsPrimeFast(){
       Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
    }
}


