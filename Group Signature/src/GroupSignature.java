package encryption;


import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import org.iso200082.common.api.GroupSignatureScheme;
import org.iso200082.common.api.SchemeSelector;
import org.iso200082.common.api.ds.Signature;
import org.iso200082.common.api.exceptions.SchemeException;
import org.iso200082.common.api.parties.Issuer;
import org.iso200082.common.api.parties.Signer;
import org.iso200082.common.api.parties.Verifier;
import org.iso200082.common.api.parties.Opener;

public class GroupSignature {

	/**
	 * @param args
	 */
	private static int NUM_ITER = 30;
	static String identifier = "m5-nr-bigint-mixed";
	//"m4-nr-bigint-mixed"
	
	public static void main(String[] args) {
		System.out.println("----------------Group Signature---------------");
		System.setProperty("sig-scheme-impl",
				"org.iso200082.common.ISO20008Factory");
			testVerify_vary_message();
	}
	public static void testVerify_vary_message()
	  {
	    try {
	      GroupSignatureScheme scheme = SchemeSelector.load(identifier);
	      Issuer issuer  = scheme.createGroup();				// 群主创建群
	      Signer signer1  = issuer.addMember("member1");		// 加入群成员1:member1
	      Signer signer2  = issuer.addMember("member2");		// 加入群成员2:member2
	      Opener opener = scheme.getOpener();					// 指定监管者
	      Verifier verif = scheme.getVerifier();				// 指定接收人
	      System.out.println("群主："+issuer);
	      System.out.println("群成员1："+signer1.getName());
	      System.out.println("群成员2："+signer2.getName());
	      System.out.println("监管者："+opener);
	      System.out.println("接收人："+verif);
	      
	      String message1 = "Every move is a kiss!";
	      String message2 = "Love everything!";
	      System.out.println("------------------------------");
	      System.out.println("群成员1对消息1签名：");
	        Signature s1 = signer1.signMessage(message1);
	        System.out.println("签名为：" + s1);
	        boolean valid1 = verif.isSignatureValid(message1, s1);
	        System.out.println(valid1);
	        System.out.println("接收人验证签名......");
	        if(valid1) {
	        	System.out.println("验证成功！");
	        }else {
	        	System.out.println("验证失败！");
	        }
	        System.out.println("-----------------------------");
	        System.out.println("群成员2对消息2签名：");
	        Signature s2 = signer2.signMessage(message2);
	        System.out.println("签名为：" + s2);
	        boolean valid2 = verif.isSignatureValid(message2, s2);
	        System.out.println("接收人验证签名......");
	        if(valid2) {
	        	System.out.println("验证成功！");
	        }else {
	        	System.out.println("验证失败！");
	        }
	        System.out.println("----------------------------------");
	        System.out.println("群主根据签名追踪签名者身份：");
	        System.out.println("群主追踪第一个签名的签名者为："+opener.openSignature(s1));
	        System.out.println("群主追踪第二个签名的签名者为："+opener.openSignature(s2));
	     // Util.printMeanStdDev(results);
	      System.out.println("-----------------------------------------"); 
	    }
	    catch(Exception ex) {
	      ex.printStackTrace();
	      //Assert.fail(ex.getMessage());
	    }  
	  }
}
