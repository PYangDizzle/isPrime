package primecalc;

import java.util.Arrays;
//import java.util.LinkedList;
import java.util.LinkedList;

public class Prime {
	
	Sieve sieve = null;
	
	public static void main( String[] args ) {
		System.out.println( ( Sieve.generate( 15 ) ) );
		
		Prime prime = new Prime();
		System.out.println( prime.isPrime( 29 ) );
		
		System.out.println( prime.sieve );
		
		System.out.println(prime.isPrime(35));
		System.out.println(prime.sieve);
		
		
		System.out.println(prime.isPrime(457));
		System.out.println(prime.sieve);
	}
	
	public boolean isPrime( int n ) {
		if( n <= 1 ) {
			return false;
		}
		
		if( sieve == null ) {
			sieve = Sieve.generate( n );
		}
		else if( sieve.max >= n ) {
			// use the stored sieve
		}
		else {
			// generate more
			sieve.more( n );
		}
		
		for( int i = 2; i*i <= n; ++i ) {
			if( sieve.get(i) ) {
				if( n % i == 0 ) {
					return false;
				}
			}
		}
		return true;
	}
}

//Sieve of Eratosthenes
class Sieve {
	
	boolean[] isPrime = null;
	int max = 0;
	
	boolean get( int i ) {
		return isPrime[i-2];
	}
	
	public String toString() {
		LinkedList<Integer> primes = new LinkedList<>();
		for( int i = 0; i < isPrime.length; ++i ) {
			if( isPrime[ i ] ) {
				primes.add( i + 2 );
			}
		}
		
		return Arrays.toString( primes.stream().mapToInt(i->i).toArray() );
	}
	
	// generate more sieve till n
	void more( int n ) {
		//System.out.println("more");
		boolean[] newIsPrime = new boolean[ n-1 ];
		Arrays.fill( newIsPrime, true );
		System.arraycopy( isPrime, 0, newIsPrime, 0, isPrime.length );
		
		//System.out.println( Arrays.toString( newIsPrime ) );
		for( int i = 2; i <= Math.sqrt( n ); ++i ) {
			if( newIsPrime[i-2] ) {
				for( int j = ( max / i ) + 1; ( i*j ) <= n; ++j ) {
					newIsPrime[i*j - 2 ] = false;
					//System.out.println( "with i = " + i + " ,to " + (i*j) + " , set false" );
				}
			}
		}
		
		isPrime = newIsPrime;
		max = n;
	}
	
	static Sieve generate( int n ) {
		// opt away, 1
		Sieve sieve = new Sieve();
		sieve.max = n;
		//boolean[] isPrime = sieve.isPrime;
		boolean[] isPrime = new boolean[n-1];
		sieve.isPrime = isPrime;
		//System.err.println( isPrime );
		//System.err.println( sieve.isPrime );
		Arrays.fill( isPrime, true );
		//int numPrime = n - 1;
		for( int i = 2; i <= Math.sqrt( n ); ++i ) {
			if( isPrime[i-2] ) {
				for( int j = 2; ( i*j ) <= n; ++j ) {
					isPrime[i*j - 2] = false;
					//numPrime--;
				}
			}
			else {
				continue;
			}
		}
		
		return sieve;

	}
}
