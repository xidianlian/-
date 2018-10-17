package test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import domain.Orders;
import domain.OrdersAndUser;
import domain.User;
import mapper.OrdersAndUserMapper;
import mapper.UserMapper;

public class OrdersAndUserMapperTest {
	private SqlSessionFactory  factory;
	@Before
	public void setup() throws IOException {
		String resource="SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		factory=new SqlSessionFactoryBuilder().build(inputStream);
	}
	@Test
	public void testFindOrdersUser() {
		SqlSession openSession = factory.openSession();
		OrdersAndUserMapper mapper = openSession.getMapper(OrdersAndUserMapper.class);
		//这里打断点可查看ordersAndUser继承Orders的属性值
		List<OrdersAndUser> list = mapper.findOrdersUser();
		
		for(OrdersAndUser l:list) {
			System.out.println(l);
		}
		openSession.close();
	}
	@Test
	public void testFindOrdersUserResultMap() {
		SqlSession openSession = factory.openSession();
		OrdersAndUserMapper mapper = openSession.getMapper(OrdersAndUserMapper.class);
		List<Orders> listOders = mapper.findOrdersUserResultMap();
		for(Orders l:listOders) {
			System.out.println(l);
		}
	}
	@Test
	public void testFindOrdersAndOrdersdetailResultMap() {
		SqlSession openSession = factory.openSession();
		OrdersAndUserMapper mapper = openSession.getMapper(OrdersAndUserMapper.class);
		List<Orders> listOders = mapper.findOrdersAndOrderDetailResultMap();
		for(Orders l:listOders) {
			System.out.println(l);
		}
		openSession.close();
	}
	@Test
	public void testfindUserAndItemsResultMap() {
		SqlSession openSession = factory.openSession();
		OrdersAndUserMapper mapper = openSession.getMapper(OrdersAndUserMapper.class);
		List<User> listUser = mapper.findUserAndItemsResultMap();
		for(User u:listUser) {
			System.out.println(u);
		}
		openSession.close();
	}
	//延迟加载测试
	@Test
	public void testFindOrdersUserLazyLoading() {
		SqlSession openSession = factory.openSession();
		OrdersAndUserMapper mapper = openSession.getMapper(OrdersAndUserMapper.class);
		//这里只查询orders单表
		List<Orders> listOrders = mapper.findOrdersUserLazyLoading();
		//在程序中去遍历listOrders，当我们调用Orders中的getUser方法时，开始进行延迟加载
		for(Orders o:listOrders) {
			System.out.println("user_id:"+o.getUser_id()+" ,number:"+o.getNumber()+" ,createtime:"+o.getCreatetime());
			//o.getUser();
		}
		//延迟加载，去调用UserMapper.xml中findUserbyId这个方法获取用户信息
		openSession.close();
	}
	/**
	 * 一级、二级缓存：
	 *   一级缓存是SqlSession级别的缓存。在操作数据库时需要构造 sqlSession对象，在对象中有一个数据结构（HashMap）用于存储缓存数据。
	 *   不同的sqlSession之间的缓存数据区域（HashMap）是互相不影响的。
	 *   二级缓存是mapper级别的缓存，多个SqlSession去操作同一个Mapper的sql语句，多个SqlSession可以共用二级缓存，二级缓存是跨SqlSession的。	
	 */
	@Test
	public void testCache() {
		SqlSession openSession = factory.openSession();
		UserMapper mapper = openSession.getMapper(UserMapper.class);
		//第一次发起查询用户id为1的用户信息，先去找缓存中是否有id为1的用户信息，如果没有，从数据库查询用户信息。
		//得到用户信息，将用户信息存储到一级缓存中。
		//如果sqlSession去执行commit操作（执行插入、更新、删除），清空SqlSession中的一级缓存，让缓存中存储的是最新的信息，避免脏读。
		//通过控制台的信息可以查看两次查询，只调用了一次sql语句
		User user1 = mapper.findUserById(1);
		System.out.println(user1);
		//可以插入、更新、删除，最后commit时，清除缓存
		user1.setUsername("王老五3");
		mapper.updateUser(user1);
		openSession.commit();
		//第二次发起查询用户id为1的用户信息，先去找缓存中是否有id为1的用户信息，缓存中有，直接从缓存中获取用户信息。
		User user2 = mapper.findUserById(1);
		System.out.println(user2);
		openSession.close();
	}
	/**
	 *二级缓存:
		sqlSession1去查询用户id为1的用户信息，查询到用户信息会将查询数据存储到二级缓存中。
		如果SqlSession3去执行相同 mapper下sql，执行commit提交，清空该 mapper下的二级缓存区域的数据。
		sqlSession2去查询用户id为1的用户信息，去缓存中找是否存在数据，如果存在直接从缓存中取出数据。
		二级缓存与一级缓存区别，二级缓存的范围更大，多个sqlSession可以共享一个UserMapper的二级缓存区域。
		UserMapper有一个二级缓存区域（按namespace分） ，其它mapper也有自己的二级缓存区域（按namespace分）。
		每一个namespace的mapper都有一个二缓存区域，两个mapper的namespace如果相同，这两个mapper执行sql查询到数据将存在相同 的二级缓存区域中。
		了解：
		在statement中设置useCache=false可以禁用当前select语句的二级缓存，即每次查询都会发出sql去查询，默认情况是true，即该sql使用二级缓存。
		<select id="findOrderListResultMap" resultMap="ordersUserMap" useCache="false">
		
		statement配置中的flushCache="true" 属性，默认情况下为true即刷新缓存，如果改成false则不会刷新。使用缓存时如果手动修改数据库表中的查询数据会出现脏读。
			如下：
			<insert id="insertUser" parameterType="cn.itcast.mybatis.po.User" flushCache="true">
	    二级缓存应用场景：
		对于访问多的查询请求且用户对查询结果实时性要求不高，此时可采用mybatis二级缓存技术降低数据库访问量，提高访问速度，业务场景比如：耗时较高的统计分析sql、电话账单查询sql等。
		实现方法如下：通过设置刷新间隔时间，由mybatis每隔一段时间自动清空缓存，根据数据变化频率设置缓存刷新间隔flushInterval，比如设置为30分钟、60分钟、24小时等，根据需求而定。
	  局限性：
		  mybatis二级缓存对细粒度的数据级别的缓存实现不好，
		    比如如下需求：对商品信息进行缓存，由于商品信息查询访问量大，但是要求用户每次都能查询最新的商品信息，
		    此时如果使用mybatis的二级缓存就无法实现当一个商品变化时只刷新该商品的缓存信息而不刷新其它商品的信息，
		    因为mybaits的二级缓存区域以mapper为单位划分，当一个商品信息变化会将所有商品信息的缓存数据全部清空。
		    解决此类问题需要在业务层根据需求对数据有针对性缓存。
	 */
	@Test
	public void testCache2() {
		SqlSession openSession1 = factory.openSession();
		SqlSession openSession2 = factory.openSession();
		SqlSession openSession3 = factory.openSession();
		UserMapper mapper1 = openSession1.getMapper(UserMapper.class);
		UserMapper mapper2 = openSession2.getMapper(UserMapper.class);
		UserMapper mapper3 = openSession3.getMapper(UserMapper.class);
		
		User user1 = mapper1.findUserById(1);
		System.out.println(user1);
		//关闭SqlSession将此缓存放入二级缓存区域
		openSession1.close();
		User user3 = mapper3.findUserById(1);
		user3.setUsername("sdaf");
		mapper3.updateUser(user3);
		openSession3.commit();
		openSession3.close();
		
		User user2 = mapper2.findUserById(1);
		System.out.println(user2);
		openSession2.close();
	}
}
