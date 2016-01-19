
import java.math.BigInteger;
import java.util.*;

public class RSA
{
	public static String rsa()
	{
		Random rng =new Random();
		BigInteger p,q,n,v,k,d,en;
		p=new BigInteger(16,100,rng);
		q=new BigInteger(16,100,rng);
		System.out.println("p->"+p+"\nq->"+q);
		n=p.multiply(q);
		v=(p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		System.out.println("n->"+n+"\nv->"+v);
		k = new BigInteger("3");
		while(v.gcd(k).intValue() > 1)
		{
			k = k.add(new BigInteger("2"));
		}
		System.out.println("k ->:"+k);
		d=k.modInverse(v);
		System.out.println("d->"+d);
		System.out.println("public key pair is (k,n)->("+k+" , "+n+")");
		System.out.println("private key pair is (d,n)->("+d+" , "+n+")");
		String str=k+"/"+d+"/"+n;
		
		System.out.println(str);

		return str;
		
	}
	public static void main(String a[])
	{
		RSA aa=new RSA();	
		aa.rsa();
	}
}