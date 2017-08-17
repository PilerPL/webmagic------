package max.yjb.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import max.yjb.entity.LaGouInfo;
import top.dreamrdk.utils.HibernateUtils;

public class LaGouDao {
	public void save(LaGouInfo laGouInfo) {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		session.save(laGouInfo);
		tx.commit();
		session.close();
	}
}
