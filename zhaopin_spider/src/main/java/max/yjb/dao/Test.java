package max.yjb.dao;

import org.apache.log4j.chainsaw.Main;
import org.assertj.core.util.VisibleForTesting;
import org.hibernate.Session;
import org.hibernate.Transaction;

import max.yjb.entity.LaGouInfo;
import top.dreamrdk.utils.HibernateUtils;

public class Test {
	public static void main(String[] args) {
		
	
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		LaGouInfo lg = new LaGouInfo();
		lg.setCompany("淘宝");
		session.save(lg);
		tx.commit();
		session.close();
	}
}
