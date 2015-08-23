安装系统 系统初始化路径url: http://ip:port/tlblog/sys/systeminit.do  
	示例：http://localhost:8080/tlblog/sys/systeminit.do

***************************************************************
简单说明：2012-xx-xx
	
***************************************************************
详细说明：开发说明
	项目采用注解方式，减少spring配置文件中对bean的配置(以下以模块管理功能为例)：
	@Service用于标注业务层组件(如SysModuleService.java中类中标注@Service)、 @Controller用于标注控制层组件（如struts中的action，SysModuleAction.java
	中标注@Controller("/view/sysmodule")）、
	@Repository用于标注数据访问组件，即DAO组件。而@Component泛指组件，当组件不好归类的时候，
	我们可以使用这个注解进行标注。
	

	dao层：放在service包中的base包中,BaseDao.java、BaseDaoImpl.java，使用泛型声明。
		BaseDaoImpl.java在类入口中标注@Transactional 注解，类中的所有方法具有
		事务处理功能，在查询方法中，无需事务，在这些方法中标注
		@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)注解，
		声明该方法无需事务处理功能，在实现类中标注。
		BaseDaoImpl.java中采用@Resource(name="sessionFactory")注入sessionFactory
		java代码中使用@Autowired或@Resource注解方式进行装配(注入依赖对象)，这两个注解的区别是：
		@Autowired 默认按类型装配，@Resource默认按名称装配，当找不到与名称匹配的bean才会按类型装配，
		一般使用@Resource注入。
	service层：每个功能模块定义一个包层次结构，包含接口定义和实现类，包名如模块管理功能包sysmodule。业务层的实现类
		中标注@Transactional 注解，设计到查询业务的方法标注@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)注解。
	action：注入业务层(如模块管理中使用sysModuleService:
		@Resource
		private ISysModuleService sysModuleService;)。

	权限配置(控制到按钮)：
		在需要权限执行的action方法中，标注@Permission(model="模块编码", privilegeValue="执行权限值")，声明需要该方法
		操作的权限。模块编码必须与添加的模块中的模块编码一致。
		如模块管理中查看action：@Permission(model="sysModuleManage", privilegeValue="view")

		具体的权限管理数据结构可以看下数据库表sys_module、sys_privilege、sys_privilege、sys_role、sys_role_privilege、sys_user、
		sys_user_role。
		需要把具体的权限 如：sysModuleManage-view、sysModuleManage-add、sysModuleManage-delete...
		需要手工保存到数据库表sys_privilege中。同时在初始化InitAction中添加初始化信息。
		可以在测试用例ISysPrivilegeServiceTest.java中添加相应的权限，可参考
		@Test
		public void testSaveSysPrivilege(){}方法加入开发功能权限。
		
		页面中加入自定义标签

	页面开发说明：
		主要是分页处理使用pager-taglib组件，后台分页已封装，可参考模块管理，页面中都有说明。
		对于页面按钮的控制，使用自定义权限标签。
		
	有其他问题可以问下我，主要是每人对技术了解有差异，用我们都比较熟悉的才是最好的，以后慢慢的可以
	考虑改用其他的方式。
---------------------------------------------------------------
***************************************************************
简单说明：2012-06-05
	
***************************************************************
详细说明：加入搜索功能(搜索引擎)
	博客文章搜索，搜索文章标题、内容。
	pager-taglib组件附带参数中文乱码问题修复(修改源文件，重新编译jar包)，需要重新添加jar包。然后utf-8还需要修改tomcat配置server.xml，结合在一起才能解决中文乱码问题
	<Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8" />
	先删除以前的jar包，然后重新添加所有的jar包。
	
	
***************************************************************
简单说明：2012-06-19
	
***************************************************************
详细说明：修复后台权限问题
	1、系统初始化功能加强及bug修复：
	安装系统 系统初始化路径url: http://ip:port/tlblog/sys/systeminit.do  
	示例：http://localhost:8080/tlblog/sys/systeminit.do
	添加系统模块、权限值在 InitAction.java文件中添加。第一次执行系统初始化操作，增添系统管理员用户admin，密码admin，并授权。
	2、新增工具类BeanUtilEx.java解决复制对象属性包含Date为null时报错，以后直接使用该类替代
	BeanUtils.java工具类
	3、数据库设计文件使用PowerDesign 12,文件在DB文件夹下
	4、添加AllRightOpTest.java测试类，方便模块、权限功能的添加，需要在系统初始化Action中添加对应的功能代码,具体参考InitAction.java,在开发对应模块功能Action时添加权限注解