package com.in28minutes.learn_spring_framework.examples.c1;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

//@Component
@Repository    //Bu class'ta veritabanı ile bağlantı işlemleri gerçekleştirdiği için 'Component' yerine 'Repository' annotation'ı kullanılabilir.
@Primary
public class MongoDbDataService implements DataService {

	@Override
	public int[] retrieveData() {
		return new int[] {11, 22, 33, 44, 55};
	}

}
