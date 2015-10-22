package platform.core.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gusi.platform.core.dao.UserDao;
import com.gusi.platform.core.model.PageInfo;
import com.gusi.platform.core.model.User;
import com.gusi.platform.core.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/conf/gusi.platform.core.xml")
public class BaseDaoTest {

	@Resource
	private UserDao userDao;
	@Resource
	private UserService userService;

	@Test
	public void testSave() {// 现在跑不了。稍等，我把Service解注释
		for (int i = 1; i <= 21; i++) {
			User user = new User();
			user.setUserName("userName-" + i);
			user.setPassword("password-" + i);
			userService.save(user);
		}
	}

	@Test
	public void testUpdate() {
		User updater = userService.getUserById(12L);
		List<User> users = userService.getAllUser();
		for (User user : users) {
			user.setCreater(updater);
			user.setUpdater(updater);
			userService.updateUser(user);
		}

	}

	@Test
	public void testDelete() {
		// userService.deleteUser(1L);
	}

	@Test
	@Transactional
	public void testGet() {
		User user = userDao.getObjById(2L);
		List<User> users = null;
		users = userDao.getObjList();
		users = userDao.getObjListByCondition("obj.userName like '%3%'");
		users = userDao.getObjListSorted("userName", true);
		users = userDao.getObjListSortedByCondition("obj.userName like '%5%'", "id", true);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setFirstRow(0);
		pageInfo.setPageSize(5);
		// pageInfo.setCondition("obj.userName like '%user%'");
		pageInfo = userDao.getObjListPaged(pageInfo);
		System.out.println(pageInfo);
		List lista = pageInfo.getObjList();
		System.out.println(lista);
		// users = userDao.getObjListPaged(pageInfo).getList();
		//
		// users = userDao.getObjListPagedSorted(pageInfo, "id",
		// true).getList();
		pageInfo.getPageInfo(pageInfo.getBottomPageNo());
		System.out.println(pageInfo);
		pageInfo = userDao.getObjListPaged(pageInfo);
		List listb = pageInfo.getObjList();
		System.out.println(listb);
	}
}
