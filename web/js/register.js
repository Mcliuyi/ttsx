$(function(){

	var error_name = false;
	var error_password = false;
	var error_check_password = false;
	var error_email = false;
	var error_check = false;
	var error_phone = false;


	$('#user_name').blur(function() {
		check_user_name();
	});

	$('#pwd').blur(function() {
		check_pwd();
	});

	$('#cpwd').blur(function() {
		check_cpwd();
	});

	$('#email').blur(function() {
		check_email();
	});

	$('#phone').blur(function() {
		check_phone();
	});

	$('#allow').click(function() {
		if($(this).is(':checked'))
		{
			error_check = false;
			$(this).siblings('span').hide();
		}
		else
		{
			error_check = true;
			$(this).siblings('span').html('请勾选同意');
			$(this).siblings('span').show();
		}
	});


	function check_user_name(){
		var len = $('#user_name').val().length;
		if(len<5||len>20)
		{
			$('#user_name').next().html('请输入5-20个字符的用户名');
			$('#user_name').next().show();
			error_name = true;
		}
		else
		{
			$.get("verUserInfo",
				{
					"uname":$('#user_name').val()
				},function (data) {
					if (data == "1"){
						$('#user_name').next().html('用户名已存在');
						$('#user_name').next().show();
						error_name = true;
					} else{
						$('#user_name').next().hide();
						error_name = false;
					}

				});
		}
	}

	function check_pwd(){
		var len = $('#pwd').val().length;
		if(len<8||len>20)
		{
			$('#pwd').next().html('密码最少8位，最长20位')
			$('#pwd').next().show();
			error_password = true;
		}
		else
		{
			$('#pwd').next().hide();
			error_password = false;
		}		
	}


	function check_cpwd(){
		var pass = $('#pwd').val();
		var cpass = $('#cpwd').val();

		if(pass!=cpass)
		{
			$('#cpwd').next().html('两次输入的密码不一致')
			$('#cpwd').next().show();
			error_check_password = true;
		}
		else
		{
			$('#cpwd').next().hide();
			error_check_password = false;
		}		
		
	}

	function check_phone(){
		var re =  /^[1][3,4,5,7,8][0-9]{9}$/;
		var phone = $('#phone').val();

		if(re.test(phone))
		{
			$('#phone').next().hide();
			error_phone = false;
		}
		else
		{
			$('#phone').next().html('你输入的手机号格式不正确')
			$('#phone').next().show();
			error_check_password = true;
		}



	}

	function check_email(){
		var re = /^[a-z0-9][\w\.\-]*@[a-z0-9\-]+(\.[a-z]{2,5}){1,2}$/;

		if(re.test($('#email').val()))
		{
			$('#email').next().hide();
			error_email = false;
		}
		else
		{
			$('#email').next().html('你输入的邮箱格式不正确')
			$('#email').next().show();
			error_check_password = true;
		}

	}


	$('#reg_form').submit(function(event) {
		event.preventDefault();//使a自带的方法失效，即无法调整到href中的URL
		check_user_name();
		check_pwd();
		check_cpwd();
		check_email();
		if(error_name == false && error_password == false && error_check_password == false && error_email == false && error_check == false)
		{
			var uname = $('#user_name').val();
			var upwd = $('#pwd').val();
			var ucpwd =  $('#cpwd').val();
			var email = $('#email').val();
			var phone = $('#phone').val();
			$.post('register', {
				'uname': uname,
				'upwd': upwd,
				'cpwd': ucpwd,
				'email':email,
				'phone':phone
			},function (data) {
				console.log(data);
				if (data == 1) {
					alert('请填写所有数据');
				} else if (data == 2) {
					$('#user_name').next().html('请输入5-20个字符的用户名');
					$('#user_name').next().show();
				} else if (data == 3) {
					$('#cpwd').next().html('两次输入的密码不一致');
					$('#cpwd').next().show();
				} else if (data == 4) {
					$('#email').next().html('你输入的邮箱格式不正确');
					$('#email').next().show();
				} else if (data == 5) {
					$('#user_name').next().html('用户名已存在，请重新输入');
					$('#user_name').next().show();
				} else if (data == 6) {
					$('#user_name').next().html('网络异常，请稍后再试');
					$('#user_name').next().show();
				} else if (data == 7) {
					$(location).attr('href', 'login.jsp');
				}
			});


		}
		else
		{
			alert('请填写所有数据')
		}

	});


});