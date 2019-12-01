import com.rbms.entities.Client;
import com.rbms.metier.UserMetier;
import com.rbms.metier.UserMetierImplTreasurer;


public class Test {
	
	public static void main(String[] args) {
		UserMetier um = new UserMetierImplTreasurer();                                
		Client c = new Client();
		c.setName("pharelle");
		c.setTel(67172740);
		
		um.showProduct();
		
	}

}
