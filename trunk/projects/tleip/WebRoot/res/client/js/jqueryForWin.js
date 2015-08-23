var openWindows = new Array;

var isAlertWindowOpen = false;
var isConfirmWindowOpen = false;

var _alertWindow;
var _confirmWindow;

var winTools = {
	alertDialog : function(msg, callback, titleText) {

		if (!isAlertWindowOpen) {
			isAlertWindowOpen = true;
			_alertWindow = document.createElement('div');
			$(_alertWindow).attr('id', 'alertDialog');
			$(_alertWindow).empty();
			if (msg) {
				$(_alertWindow).append(msg);
			}

			if (titleText != null && typeof (titleText) != 'undefined'
					&& titleText.length != 0) {
				$(_alertWindow).attr('title', titleText);
			} else {
				$(_alertWindow).attr('title', '提示');
			}

			var opt = {
				width : 300,
				height : 'auto',
				maxHeight : 600,
				autoOpen : false,
				modal : true,
				//simple : true,
				stack : true,
				bgiframe : true,
				resizable : false,
				buttons : {
					'确定' : function() {
						$(this).dialog('close');
						if ($.isFunction(callback))
							callback();
					}
				},
				close : function() {
					$(this).dialog('destroy');
					$(this).remove();
					isAlertWindowOpen = false;
				}
			};

			$(_alertWindow).dialog(opt);
			$(_alertWindow).dialog('open');
		} else {
			$(_alertWindow).empty();
			if (msg) {
				$(_alertWindow).append(msg);
			}
		}

	},
	confirmDialog : function(msg, callback1, callback2) {

		if (!isConfirmWindowOpen) {
			isConfirmWindowOpen = true;
			_confirmWindow = document.createElement('div');
			$(_confirmWindow).attr('id', 'confirmDialog');
			$(_confirmWindow).empty();
			if (msg) {
				$(_confirmWindow).append(msg);
			}

			$(_confirmWindow).attr('title', '信息提示');
			// $('#confirmDialog').dialog('destroy');
			var opt = {
				width : 300,
				height : 'auto',
				maxHeight : 600,
				autoOpen : false,
				modal : true,
				//simple : true,
				bgiframe : true,
				stack : true,
				resizable : false,
				buttons : {
					'取消' : function() {
						if ($.isFunction(callback2))
							callback2();
						$(this).dialog('close');
					},
					'确定' : function() {
						if ($.isFunction(callback1))
							callback1();
						$(this).dialog('close');
					}

				},
				close : function() {
					$(this).dialog('destroy');
					$(this).remove();
					isConfirmWindowOpen = false;
				}
			};

			$(_confirmWindow).dialog(opt);
			$(_confirmWindow).dialog('open');
		} else {
			$(_confirmWindow).empty();
			if (msg) {
				$(_confirmWindow).append(msg);
			}
		}

	},
	showWindow : function(p) {

		var flag = false;
		$.each(openWindows, function(i, n) {
			if (n.id == p.id) {
				flag = true;
			}
		});
		if (flag) {
			return;
		}

		var dt = '' + new Date().getTime();
		var srcurl = p.url + '?_=' + dt;
		var fid = "iframe_" + dt;

		var opts = {
			id : p.id,
			width : p.width,
			height : p.height,
			autoOpen : false,
			modal : p.modal,
			//simple : p.simple,
			bgiframe : true,
			buttons : p.buttons,
			resizable : false,
			close : function() {
				$('#' + fid).attr('src', '');
				$(this).dialog('destroy');
				$(this).remove();
				var _id;
				var _no = 0;
				var _relation;
				$.each(openWindows, function(i, n) {
					if (n.show) {
						_id = n.id;
						_no = i;
						_relation = n.relation;
						return;
					}
				});

				$.each(openWindows, function(i, n) {
					if (n.id == _relation) {
						n.show = true;
					}
				})

				var arr = new Array;
				$.each(openWindows, function(i, n) {
					if (_no != i) {
						arr.push(n);
					}
				});
				openWindows = arr;
				arr = null;

				if (p.onClose) {
					p.onClose();
				}
			}
		};

		$.each(openWindows, function(i, n) {
			n.show = false;
		});

		var _window = document.createElement('div');
		$(_window).attr('id', 'akfdialog' + p.id);

		if (openWindows.length == 0) {
			openWindows.push( {
				id : p.id,
				show : true,
				relation : ''
			});

		} else {
			var index = openWindows.length;
			openWindows.push( {
				id : p.id,
				show : true,
				relation : openWindows[index - 1].id
			});
		}
		$(_window).empty();
		$(_window).attr('title', p.title);
		$(_window).attr('id', 'akfdialog' + p.id);
		$(_window).dialog(opts);
		$(_window).dialog('open');

		if (p.type == 'html') {
			if (p.param) {
				$(_window).akfLoad(p.url, p.param);
			} else {
				$(_window).akfLoad(p.url);
			}

		}
		if (p.type == 'iframe') {
			if (p.param) {
				srcurl = srcurl + '&' + $.param(p.param);
			}

			var iframe = $(
					'<iframe id="' + fid + '" name="' + fid + '" src="'
							+ srcurl + '" frameborder="0" scrolling="auto" />')
					.css( {
						width : '100%',
						height : $('#akfdialog' + p.id).height() - 5,
						border : 0
					}).appendTo($(_window));
		}
	},
	closeWindow : function() {
		var _id;
		$.each(openWindows, function(i, n) {
			if (n.show) {
				_id = n.id;
				return;
			}
		});
		$('#akfdialog' + _id).dialog('close');
	}
};


/**
 * 
 * 功能：ext通用JS函数(Jquery扩展自定义js库)，依赖Jquery
 *		主要封装了flexigrid展示界面中进行增删改操作弹出框操作
 *      截取项目开发中的封装功能js库，并作出适当的删除修改
 * @author 任磊
 * @date 2011－11－11
 */

/**
 * 
 * 功能：返回当前已经打开的所有窗口/页面
 * 
 * @author 谢艳华(Kevin.xie)
 * @date 2009-09-30 10:46:26
 */
if (!window.top.winTools) {
	
	var openWindows = new Array;
}

/**
 * 
 * 功能：全局函数库，页面加载时就要用到
 * 
 * @author 谢艳华(Kevin.xie)
 * @date 2009-09-30 10:46:26
 */
(function($) {

	// 弹出对话框
	var _alertDialog = document.createElement('div');

	// 弹出确认对话框
	var _confirmDialog = document.createElement('div');

	// 超时 default 90 seconds
	var _timeout = "90000";

	// 是否显示loading的层，默认不显示
	var loading = false;

	/**
	 * 显示Loading条
	 * 
	 * @author 谢艳华(Kevin.xie)
	 */
	$.showLoader = function(p) {

	};

	/**
	 * 去掉Loading条
	 * 
	 * @author 谢艳华(Kevin.xie)
	 */
	$.removeLoader = function() {
		
	};

	/**
	 * 功能：弹出alert窗口
	 * 
	 * @author 谢艳华(Kevin.xie)
	 * @date 2009-09-30 10:46:26
	 * 
	 * @param msg
	 *            提示信息
	 * @param callback
	 *            点击确定的回调函数
	 * @param titleText
	 *            标题栏显示文本
	 * 
	 */
	$.alert = function(msg, callback ,titleText) {
		
		if (window.top.winTools) {
			window.top.winTools.alertDialog(msg, callback,titleText);
		} else {
			var opts = {
				width : 300,
				height : 'auto',
				maxHeight : 600,
				autoOpen : false,
				//modal : true,
				bgiframe: true,
				stack : true,
				
				resizable : false,
				buttons : {
					'确定' : function() {
				
						if ($.isFunction(callback)) {
							
							callback();
						}
						
						$(this).dialog('close');
					}
				}
			};

			$(_confirmDialog).attr('id', 'alertWindow');
			if(titleText!=null && typeof(titleText)!='undefined' && titleText.length!=0){
				
				$(_alertDialog).attr('title', titleText);
			}else{
				
				$(_alertDialog).attr('title', '提示');
			}
			
			_alertDialog.innerHTML = msg;
			$(_alertDialog).dialog(opts);
			$(_alertDialog).dialog('open');
		}
	};

	/**
	 * 功能：弹出comfrim窗口
	 * 
	 * @author 谢艳华(Kevin.xie)
	 * @date 2009-09-30 10:46:26
	 * 
	 * @param msg
	 *            提示信息
	 * @param callback1
	 *            点击确定的回调函数
	 * @param callback2
	 *            点击取消的回调函数
	 */
	$.confirm = function(msg, callback1, callback2) {

		if (window.top.winTools) {
			
			window.top.winTools.confirmDialog(msg, callback1, callback2);
		} else {
			
			var opts = {
				width : 300,
				height : 'auto',
				autoOpen : false,
				//modal : true,
				//simple : true,
				bgiframe : ture,
				resizable : false,
				buttons : {
					'取消' : function() {
				
						if ($.isFunction(callback2)){
							
							callback2();
						}
						
						$(this).dialog('close');
					},
					'确定' : function() {
						
						if ($.isFunction(callback1)){
							
							callback1();
						}
						
						$(this).dialog('close');
					}
				}
			};
			
			$(_confirmDialog).attr('id', 'confirmWindow');
			$(_confirmDialog).attr('title', '信息提示');
			_confirmDialog.innerHTML = msg;
			$(_confirmDialog).dialog(opts);
			$(_confirmDialog).dialog('open');
		}
	};
	
	/**
	 * 功能：弹出comfrim窗口2 重构选择窗口的按钮名称
	 * 
	 * @author yuyx
	 * @date 2011-03-09 20:46:26
	 * 
	 * @param msg
	 *            提示信息
	 * @param button1 修改确定按钮的名称
	 * @param button2 修改取消按钮的名称
	 * @param callback1
	 *            点击确定的回调函数
	 * @param callback2
	 *            点击取消的回调函数
	 */
	$.confirm2 = function(msg, callback1, callback2) {
		

		var opts = {
			width : 300,
			height : 'auto',
			autoOpen : false,
			modal : true,
			simple : true,
			resizable : false,
			buttons : {
				'删除' : function() {
			
					if ($.isFunction(callback2)){
						
						callback2();
					}
					
					$(this).dialog('close');
				},
				'失效' : function() {
					
					if ($.isFunction(callback1)){
						
						callback1();
					}
					
					$(this).dialog('close');
				}
			}
		};
		
		$(_confirmDialog).attr('id', 'confirmWindow');
		$(_confirmDialog).attr('title', '信息提示');
		_confirmDialog.innerHTML = msg;
		$(_confirmDialog).dialog(opts);
		$(_confirmDialog).dialog('open');
	
	};

	/**
	 * 功能：打开新窗口层
	 * 
	 * @author 谢艳华(Kevin.xie)
	 * @date 2009-09-30 10:46:26
	 * 
	 * @param p.url
	 *            请求路径
	 * @param p.title
	 *            窗口名称
	 * @param p.width
	 *            窗口宽度
	 * @param p.height
	 *            窗口高度
	 * @param p.type
	 *            弹出窗口类型：html为页面动态插入，iframe为通过iframe来获取页面，默认为iframe
	 * 
	 * @param p.onClose
	 *            窗口上面点击关闭图标的回调函数
	 */
	$.showWindow = function(p) {
		
		if (!p.url && !p.id) {
			
			$.alert("弹出窗口必须要设置url属性或者id属性！");
			
			return;
		}

		// 头"//" -> "/"
		if (p.url && p.url.substring(0, 2) == "//") {
			p.url = p.url.substring(1);
		}

		// apply default properties
		p = $.extend({
			title : '新窗口',
			type : 'iframe',
			width : 'auto',
			height : 'auto',
			buttons : {},
			simple : false,
			modal : true
		}, p);

		if (window.top.winTools) {
			
			window.top.winTools.showWindow(p);
			
			return;
		} else {
			
			/**
			 * 如果当前已经打开了一个窗口，则不再打开了
			 */
			var flag = false;
			$.each(openWindows, function(i, n) {
				
						if (n.id == p.id) {
							
							flag = true;
						}
					});
			if (flag) {
				
				return;
			}

			var dt = '' + new Date().getTime();
			var srcurl = p.url + '?_=' + dt;
			var fid = "iframe_" + dt;

			var opts = {
				id : p.id,
				width : p.width,
				height : p.height,
				autoOpen : false,
				modal : p.modal,
				simple : p.simple,
				buttons : p.buttons,
				resizable : false,
				close : function(event,ui,obj) {
				
					$('#' + fid).attr('src', '');
					$(this).dialog('destroy');
					$(this).remove();
					var _id;
					var _no = 0;
					var _relation;
					$.each(openWindows, function(i, n) {
						
								if (n.show) {
									
									_id = n.id;
									_no = i;
									_relation = n.relation;
									
									return;
								}
							});

					$.each(openWindows, function(i, n) {
						
								if (n.id == _relation) {
									
									n.show = true;
								}
							})

					var arr = new Array;
					$.each(openWindows, function(i, n) {
						
								if (_no != i) {
									
									arr.push(n);
								}
							});
					
					openWindows = arr;
					arr = null;

					if (p.onClose) {
						
						p.onClose(event,ui,obj);
					}
				}
			};

			$.each(openWindows, function(i, n) {
				
						n.show = false;
					});

			// create a window
			var _window = document.createElement('div');
			$(_window).attr('id', 'akfdialog' + p.id);

			if (openWindows.length == 0) {
				
				openWindows.push({
					
							id : p.id,
							show : true,
							relation : ''
						});

			} else {
				
				var index = openWindows.length;
				openWindows.push({
					
							id : p.id,
							show : true,
							relation : openWindows[index - 1].id
						});
			}
			
			$(_window).empty();
			$(_window).attr('title', p.title);
			$(_window).attr('id', 'akfdialog' + p.id);
			$(_window).dialog(opts);
			$(_window).dialog('open');

			if (p.type == 'html') {
				
				if (p.param) {
					
					$(_window).akfLoad(p.url, p.param);
				} else {
					
					$(_window).akfLoad(p.url);
				}
			}
			
			if (p.type == 'iframe') {
				
				if (p.param) {
					
					srcurl = srcurl + '&' + $.param(p.param);
				}

				var iframe = $('<iframe id="' + fid + '" name="' + fid
						+ '" src="' + srcurl
						+ '" frameborder="0" scrolling="auto" />').css({
							width : '100%',
							height : $('#akfdialog' + p.id).height() - 5,
							border : 0
						}).appendTo($(_window));
			}
		}
	};

	/**
	 * 功能：关闭窗口
	 * 
	 * @author 谢艳华(Kevin.xie)
	 * @date 2009-09-30 10:46:26
	 * 
	 * @param callback
	 *            关闭窗口时的回调函数
	 */
	$.closeWindow = function(callback) {

		if ($.isFunction(callback)){
			
			callback();
		}
		
		if (window.top.winTools) {
		
			window.top.winTools.closeWindow(callback);
			
			return;
		} else {
			
			var _id;
			$.each(openWindows, function(i, n) {
				
						if (n.show) {
							
							_id = n.id;
							
							return;
						}
					});
			
			$('#akfdialog' + _id).dialog('close');		
		}
	};

	$.decode = function(p) {
		
		if (p != undefined) {
			return eval("(" + p + ")");
		} else {
			
			return p;
		}
	};
	
		/**
	 * 功能：获取制定表单的所有input元素，拼装成AJAX请求的参数
	 * 
	 * @author 谢艳华(Kevin.xie)
	 * @date 2009-09-30 10:46:26
	 * 
	 * @param p
	 *            页面表单的对象
	 * 
	 * @return data 拼装后的参数数据（key-value形式）
	 */
	$.formParams = function(p) {
		
		if (p == undefined) {
			
			return;
		}

		var data = new Array;
        // all input elements
		$(p).find(":input").each(function(i, n) {
			
			var flag = "false";
			if ($(n).attr("param") == undefined) {
				
				flag = "true";
			} else {
				
				flag = $(n).attr("param");
			}
			
			if (flag == true || flag == "true") {
				
				if (($(n).attr('type') == "checkbox" || $(n).attr('type') == "radio")
						&& $(n).attr('checked') == true) {

					data.push({
								name : $(n).attr("name"),
								value : $(n).attr("value")
							})
				} else if ($(n).attr('type') == "text"
						|| $(n).attr('type') == "password"
						|| $(n).attr('type') == "hidden"
						|| $(n).attr('type') == "textarea") {

					data.push({
								name : $(n).attr("name"),
								value : $(n).attr("value")
							})
				}
			}
		});
		
		// all select elements
		$(p).find("select").each(function(i, n) {

					var flag = "false";
					if ($(n).attr("param") == undefined) {
						
						flag = "true";
					} else {
						
						flag = $(n).attr("param");
					}
					
					if (flag == true || flag == "true") {
						
						// 获取所有下拉选择框的值--有值则填写，无值则填""
						if ($.trim($(n).attr('value')) != '') {
							
							data.push({
										name : $(n).attr("name"),
										value : $(n).attr("value")
									})
						} else {
							
							data.push({
										name : $(n).attr("name"),
										value : ''
									})
						}
					}
				})
				
		return data;
	};
})(jQuery);