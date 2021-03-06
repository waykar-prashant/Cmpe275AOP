import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.sjsu.cmpe275.lab1.UnauthorizedException;
import edu.sjsu.cmpe275.lab1.interfaces.ISecretService;
import edu.sjsu.cmpe275.lab1.model.Secret;
import edu.sjsu.cmpe275.lab1.services.SecretService;
/*
 * testA: Bob cannot read Alice’s secret, which has not been shared with Bob
 * testB: Alice shares a secret with Bob, and Bob can read it.
 * testC: Alice shares a secret with Bob, and Bob shares Alice’s it with Carl, and Carl can read this secret.
 * testD: Alice shares her secret with Bob; Bob shares Carl’s secret with Alice and encounters UnauthorizedException.
 * testE: Alice shares a secret with Bob, Bob shares it with Carl, Alice unshares it with Carl, and Carl cannot read this secret anymore.
 * testF: Alice shares a secret with Bob and Carl; Carl shares it with Bob, then Alice unshares it with Bob; Bob cannot read this secret anymore.
 * testG: Alice shares a secret with Bob; Bob shares it with Carl, and then unshares it with Carl. Carl can still read this secret.
 * testH: Alice shares a secret with Bob; Carl unshares it with Bob, and encounters UnauthorizedException.
 * testI: Alice shares a secret with Bob; Bob shares it with Carl; Alice unshares it with Bob; Bob shares it with Carl with again, and encounters UnauthorizedException. 
 * testJ: Alice stores the same secrete object twice, and get two different UUIDs.
*/
public class SecretServiceTest {
	@Autowired
	ISecretService secretService;

	@Before
	public void setUp() throws Exception {
		System.out.println("-----------------------------------------------------");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"edu/sjsu/cmpe275/lab1/bean/bean.xml");
		try {
			secretService = (ISecretService) context.getBean("secretservice", SecretService.class);
		} finally {
			((ClassPathXmlApplicationContext)context).close();
		}
		
	}

	@Test(expected = UnauthorizedException.class)
	public void testA() {
		//testA: Bob cannot read Alice’s secret, which has not been shared with Bob
		System.out.println("\n\ntestA:");
		System.out.println("-----------------------------------------------------");
		//secretService.storeSecret(userId, secret)
		UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
		//System.out.println("Alice Sec: " + aliceSecret);
		secretService.readSecret("Bob", aliceSecret);
	}
	
	@Test
    public void testB(){
		//testB: Alice shares a secret with Bob, and Bob can read it.
		System.out.println("\n\ntestB:");
		System.out.println("-----------------------------------------------------");
		UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
		secretService.shareSecret("Alice", aliceSecret, "Bob");
		secretService.readSecret("Bob",aliceSecret);
    }
	
	@Test
	public void testC(){
		//testC: Alice shares a secret with Bob, and Bob shares Alice’s id with Carl, and Carl can read this secret.
		System.out.println("\n\ntestC:");
		System.out.println("-----------------------------------------------------");
		UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
		secretService.shareSecret("Alice", aliceSecret, "Bob");
		secretService.shareSecret("Bob", aliceSecret, "Carl");
		secretService.readSecret("Carl",aliceSecret);
	}

	@Test(expected = UnauthorizedException.class)
	public void testD(){
		//testD: Alice shares her secret with Bob; Bob shares Carl’s secret with Alice and encounters UnauthorizedException.
		System.out.println("\n\ntestD:");
		System.out.println("-----------------------------------------------------");
		UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
		secretService.shareSecret("Alice", aliceSecret, "Bob");
		
		UUID carlSecret = secretService.storeSecret("Carl", new Secret());
		secretService.shareSecret("Bob", carlSecret, "Alice");
	}
	
	@Test(expected = UnauthorizedException.class)
	public void testE(){
		//testE: Alice shares a secret with Bob, Bob shares it with Carl, Alice unshares it with Carl, and Carl cannot read this secret anymore.
		System.out.println("\n\ntestE:");
		System.out.println("-----------------------------------------------------");
		UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
		secretService.shareSecret("Alice", aliceSecret, "Bob");
		
		secretService.shareSecret("Bob", aliceSecret, "Carl");
		secretService.unshareSecret("Alice", aliceSecret, "Carl");
		secretService.readSecret("Carl", aliceSecret);
	}
	
	@Test(expected = UnauthorizedException.class)
	public void testF(){
		//testF: Alice shares a secret with Bob and Carl; Carl shares it with Bob, then Alice unshares it with Bob; Bob cannot read this secret anymore.
		System.out.println("\n\ntestF:");
		System.out.println("-----------------------------------------------------");
		UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
		secretService.shareSecret("Alice", aliceSecret, "Bob");
		secretService.shareSecret("Alice", aliceSecret, "Carl");
		secretService.shareSecret("Carl", aliceSecret, "Bob");
		
		secretService.unshareSecret("Alice", aliceSecret, "Bob");
		secretService.readSecret("Bob", aliceSecret);
	}
	
	@Test
	public void testG(){
		//testG: Alice shares a secret with Bob; Bob shares it with Carl, and then unshares it with Carl. Carl can still read this secret.	
		System.out.println("\n\ntestG:");
		System.out.println("-----------------------------------------------------");
		UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
		secretService.shareSecret("Alice", aliceSecret, "Bob");
		secretService.shareSecret("Bob", aliceSecret, "Carl");
		secretService.unshareSecret("Bob", aliceSecret, "Carl");
		secretService.readSecret("Carl", aliceSecret);
	}
	
	@Test(expected = UnauthorizedException.class)
	public void testH(){
		//testH: Alice shares a secret with Bob; Carl unshares it with Bob, and encounters UnauthorizedException.
		System.out.println("\n\ntestH:");
		System.out.println("-----------------------------------------------------");
		UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
		secretService.shareSecret("Alice", aliceSecret, "Bob");
		secretService.unshareSecret("Carl", aliceSecret, "Bob");
	}
	
	@Test(expected = UnauthorizedException.class)
	public void testI(){
		//testI: Alice shares a secret with Bob; Bob shares it with Carl; Alice unshares it with Bob; Bob shares it with Carl with again, and encounters UnauthorizedException. 
		System.out.println("\n\ntestI:");
		System.out.println("-----------------------------------------------------");
		UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
		secretService.shareSecret("Alice", aliceSecret, "Bob");
		secretService.shareSecret("Bob", aliceSecret, "Carl");
		secretService.unshareSecret("Alice", aliceSecret, "Bob");
		secretService.shareSecret("Bob", aliceSecret, "Carl");
	}

	@Test
	public void testJ(){
		//testJ: Alice stores the same secrete object twice, and get two different UUIDs.
		System.out.println("\n\ntestJ");
		System.out.println("-----------------------------------------------------");
		UUID aliceSecret1 = secretService.storeSecret("Alice", new Secret());
		UUID aliceSecret2 = secretService.storeSecret("Alice", new Secret());
	}



}
