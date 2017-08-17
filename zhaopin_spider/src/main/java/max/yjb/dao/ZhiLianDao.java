package max.yjb.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import max.yjb.entity.LaGouInfo;
import max.yjb.entity.ZhiLianInfo;
import top.dreamrdk.utils.HibernateUtils;

public class ZhiLianDao {
	public void save(ZhiLianInfo zl) {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		session.save(zl);
		tx.commit();
		session.close();
	}
}
