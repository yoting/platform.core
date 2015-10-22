package platform.core.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gusi.platform.core.dao.BaseDataMng;
import com.gusi.platform.core.model.User;
import com.gusi.platform.core.service.UtilService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/conf/gusi.platform.core.xml")
public class BaseDataMngTest {

	@Autowired
	private BaseDataMng baseDataMng;

	@Resource
	private UtilService utilService;

	@Test
	public void testSave() {
		for (int i = 1; i < 21; i++) {
			User user = new User();
			user.setUserName("name-" + (i));
			user.setPassword("pwd-" + (i));
			baseDataMng.saveObj(user);
		}
	}

	@Test
	public void testUpdate() {
		User creater = (User) baseDataMng.getObjById(User.class, 1L);
		for (int i = 2; i < 21; i++) {
			User user = (User) baseDataMng.getObjById(User.class, Long.parseLong(i + ""));
			user.setCreater(creater);
			user.setUpdater(creater);
			baseDataMng.updateObj(user);
		}
	}

	@Test
	public void testDelete() {
		// User user = (User) baseDataMng.getObjById(User.class, 1L);
		// baseDataMng.deleteObj(user);
	}

	@Test
	public void testGet() {
		@SuppressWarnings("unchecked")
		List<User> list = null;
		// list = baseDataMng.getObjList(User.class);
		// list = baseDataMng.getObjListPaged(User.class, 1, 2);
		// list = baseDataMng.getObjListSorted(User.class, "userName", null);
		// list = baseDataMng.getObjListPagedSortedByCondition(User.class,
		// "userName like '12%'", 0, 1, null, null);
		// System.out.println(list);
	}

	@Test
	public void testSql() {
		try {
			utilService.getCurrentMaxNumber("blog_category", "indexNum", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
