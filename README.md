# spring-bean-invoke-demo
通过bean-invoke调用spring的bean的demo，结合接口访问工具可以进行bean方法的调用

InvokeParameterResolver主要是用来实现无法自动解析的参数,比如org.springframework.data.domain.Page,
可在convert方法中进行转换传入的参数


接口工具地址: http://joker_yyc.coding.me/bean-invoke-html/

操作主要步骤:
网页上首先通过接口获取到bean方法数据,然后选择要调用的方法,然后在每个参数类型的右侧填入参数点击调用即可 

参数值的转换是通过类型转换——>自定义解析——>json数据的转换


方法参数示例:
public void showOrder(Order order1, Order order2, Order... orders) {
}

Order实体
{
    name: string,
    price: int
}

Order   :  {name: '订单1', price: 100} 
Order   :  {name: '订单2', price: 200}
Order[] :  [{name: '订单3', price: 300}] 