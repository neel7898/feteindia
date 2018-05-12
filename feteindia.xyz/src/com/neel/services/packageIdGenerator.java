package com.neel.services;

import java.io.Serializable;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class packageIdGenerator implements IdentifierGenerator{

	@Override
	public Serializable generate(SessionImplementor arg0, Object arg1) throws HibernateException {
		Random random = new Random();
		int prefix = random.nextInt(1000);
		return "PKG"+prefix;
	}

}
