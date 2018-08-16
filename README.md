**通过bean-invoke调用spring的bean的示例，结合接口访问工具便可以进行项目中bean方法的调用**
</br>

InvokeParameterResolver主要是用来实现无法自动解析的参数,比如org.springframework.data.domain.Pageable,
可在convert方法中进行转换传入的参数

参数值的转换是通过类型转换——>自定义解析——>json数据的转换


bean-invoke:  https://github.com/joker-pper/bean-invoke.git
接口工具地址: http://joker_yyc.coding.me/bean-invoke-html/

> 接口工具操作主要步骤:
1. 设置接口地址路径
1. 添加请求头(可选)
1. 设置储存项(可作为项目,根据不同项储存相应的接口地址/bean名称/class类型)
1. 设置bean名称(用于通过bean名称找到对应实例执行对应的方法)/class类型(用于通过class类型执行对应的方法)
1. 点击加载按钮,加载当前bean/class的方法列表
1. 输入方法中各参数类型所对应的参数值(基本类型直接输入对应的数值,其他对象则输入符合类型的json格式数据)
1. 点击获取按钮获取执行结果

> 如何集成到项目:
1.  若使用提供的接口工具访问:
	1. 接口需提供跨域支持
	1. 请求方法执行接口结束路径必须为/result (post请求,参数在body中),获取方法列表接口结束路径必须为/method/entitys  (get请求)
1.  参考提供的InvokeBeanController在项目中提供相应的接口
	1. getBean(String name)是用来通过该name找到所对应的实例,然后执行对应的bean方法
	1. parameterResolver()是用来提供外部无法转换的数据的自定义解析




HandlerService方法参数示例:


>运行项目后接口工具使用截图(若未开启header token验证,则无需添加请求头)

 ![](http://joker_yyc.coding.me/bean-invoke-html/images/default.png)


```
 public void showOrder(Order order1, Order order2, Order... orders) {
        System.out.println("show order1 : " + JSONObject.toJSONString(order1));
        System.out.println("show order2 : " + JSONObject.toJSONString(order2));
}
```
>  对应参数:


> ![](http://joker_yyc.coding.me/bean-invoke-html/images/show_order.png)

```
public int add(int a, int b) {
        return a + b;
 }
```
>  对应参数: 

> ![](http://joker_yyc.coding.me/bean-invoke-html/images/add.png)

```
 public Page convertPage(Page page) {
        return page;
 }
```
>  对应参数: 

> ![](http://joker_yyc.coding.me/bean-invoke-html/images/covert_page.png)
