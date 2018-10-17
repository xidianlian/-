package mapper;

import java.util.List;

import domain.Orderdetail;
import domain.Orders;
import domain.OrdersAndUser;
import domain.User;

/**
 * 订单类mapper 
 *
 */
public interface OrdersAndUserMapper {
	//一对一 查询订单->用户信息用resultType
	public List<OrdersAndUser> findOrdersUser();
	//一对一 查询订单->用户信息使用resultMap
	public List<Orders> findOrdersUserResultMap();
	//一对多 查询订单->详细信息表使用resultMap
	public List<Orders> findOrdersAndOrderDetailResultMap();
	//多对多 查询用户->订单->详细信息->items表使用resultMap 即用户购买商品的信息
	public List<User> findUserAndItemsResultMap();
	//延迟加载，查询订单关联查询用户
	public List<Orders> findOrdersUserLazyLoading();
}
