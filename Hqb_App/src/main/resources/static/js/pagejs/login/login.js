//主动加载jquery模块
var flag = "";
var flag2 = "";
 //验证码
 var InterValObj; //timer变量，控制时间 
 var count = 60; //间隔函数，1秒执行 
 var curCount; //当前剩余秒数 
 var code = ""; //验证码 
 var codeLength = 6; //验证码长度 
layui.use(['jquery', 'layer'], function(){ 
  var $ = layui.jquery //重点处
  ,layer = layui.layer;
  $(".mobileLogin").on('click',function() {
    $(".formLoginContent").hide();
    $(".mobileLoginContent").show();
  });
  $(".returnArrow").on('click',function() {
    $(".mobileLoginContent").hide();
    $(".formLoginContent").show();
  });
	layui.use(['form', 'layedit', 'laydate'], function(){
		var form = layui.form
		,layer = layui.layer;
		$("body").keydown(function () {
      var thEvent = window.event || arguments.callee.caller.arguments[0];
      if (thEvent.keyCode == "13") {
       	$("#loginBtn").click(); 
      }
      if(event.keyCode==32){ return false}
 		});
	  //表单登录监听提交
	  form.on('submit(loginBtn)', function(data){
        if(flag){//用于表单验证是否已经滑动成功
         $('.layui-form').submit();
		      console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        }else{
          layer.msg("请先通过滑块验证");
        }
		  //以下是登录的ajax，为了演示需要注释了~
	   /* $.ajax({
			  type: 'POST',
			  data:data.field,//登录入参
			  url: "url",//登录接口		  
			  dataType: JSON, 
			  success: function(data){
			    layer.msg('登录成功！', {icon:6,time:1500},function(){//登陆成功跳转到主页
						window.location.href='index.html'; 
					});
				},
			  error:function(e){
			  	layer.msg('系统异常，请稍后重试！', {icon: 5, anim: 6,time:1500});
			  }
			});*/
			/*layer.msg('登录成功！', {icon:6,time:1500},function(){
				window.location.href='index.html'; 
			});*/
	  });
    //手机验证登录监听提交
    form.on('submit(mobileLoginBtn)', function(data){
        if(flag){//用于表单验证是否已经滑动成功
         $('.layui-form2').submit();
          console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        }else{
          layer.msg("请先通过滑块验证");
        }
      //以下是登录的ajax，为了演示需要注释了~
     /* $.ajax({
        type: 'POST',
        data:data.field,//登录入参
        url: "url",//登录接口     
        dataType: JSON, 
        success: function(data){
          layer.msg('登录成功！', {icon:6,time:1500},function(){//登陆成功跳转到主页
            window.location.href='index.html'; 
          });
        },
        error:function(e){
          layer.msg('系统异常，请稍后重试！', {icon: 5, anim: 6,time:1500});
        }
      });*/
      /*layer.msg('登录成功！', {icon:6,time:1500},function(){
        window.location.href='index.html'; 
      });*/
    });


     //滑动验证表单
    $('#mpanel1').slideVerify({
        type : 1,   //类型
        vOffset : 5,  //误差量，根据需求自行调整
        barSize : {
          width : '275px',
          height : '40px',
        },
        ready : function() {
      },
        success : function() {
          flag = true;
        },
        // error : function() {
        //  alert('验证失败！');
        // }
        
    });
      //滑动验证登录
      $('#mpanel2').slideVerify({
        type : 1,   //类型
        vOffset : 5,  //误差量，根据需求自行调整
        barSize : {
          width : '275px',
          height : '40px',
        },
        ready : function() {
      },
        success : function() {
          flag2 = true;
        },
        // error : function() {
        //  alert('验证失败！');
        // }
        
    });
	});
});  
function sendMessages() {
        curCount = count;
        var phone = $("#phone").val()
        if(validatePhone(phone)) {
           return;
        }
        if(!flag2){
          layer.msg("请先通过滑块验证");
          return;
        }
        if(phone != "") {
           //设置button效果，开始计时 
           $("#getCode").addClass('layui-btn-disabled');
           $("#getCode").attr("disabled", "true");
           $("#getCode").val("请在" + curCount + "秒后再次获取");
           InterValObj = window.setInterval(SetRemainTimes, 1000); //启动计时器，1秒执行一次 
           //向后台发送处理数据 
           // $.ajax({
           //    url: "getCode.action",
           //    dataType: "json",
           //    type: "get",
           //    data: "phone=" + phone,
           //    success: function(data) {
           //       //保存验证码
           //       $("#code").val(data);
           //    }
           // });
        } else {
            layer.msg('请输入手机号');
        }
     }
     //验证手机号
     function validatePhone(phone) {
        if(phone == '') {
           layer.msg('请输入手机号');
           return true;
        }
        var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
        if(!myreg.test(phone)) {
            layer.msg('请输入有效的手机号');
           return true;
        }
        return false;
     }
     //验证码非空和错误验证
     function validateCode() {
        var phone = $("#phone").val();
        var code = $("#code").val();
        var rpcode = $("#rpcode").val();
        if(validatePhone(phone)) {
           return true;
        }
         if(code == '') {
           layer.msg('请先获取验证码');
           return true;
        }
        if(rpcode == '' || code != rpcode) {
           layer.msg('请正确输入验证码');
           return true;
        }
        alert(code != rpcode);
        return false;
     }
       //timer处理函数 
     function SetRemainTimes() {
        if(curCount == 0) {
           window.clearInterval(InterValObj); //停止计时器 
           $("#getCode").removeClass('layui-btn-disabled').removeAttr('disabled'); //启用按钮 
           $("#getCode").val("重新发送验证码");
           code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效   
        } else {
           curCount--;
           $("#getCode").val("请在" + curCount + "秒后再次获取");
        }
     }
