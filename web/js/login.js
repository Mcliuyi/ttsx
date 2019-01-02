$(function(){

    var error_name = false;
    var error_password = false;
    var error_check = false;


    $('#user_name').blur(function() {
        //check_user_name();
    });

    $('#pwd').blur(function() {
        //check_pwd();
    });

    $('#remember').click(function() {
        if($(this).is(':checked'))
        {
            error_check = true;
            $(this).siblings('span').hide();
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
            $('#user_name').next().hide();
            error_name = false;
        }
    }

    function check_pwd(){
        var len = $('#pwd').val().length;
        if(len<8||len>20)
        {
            $('#pwd').next().html('密码最少8位，最长20位');
            $('#pwd').next().show();
            error_password = true;
        }
        else
        {
            $('#pwd').next().hide();
            error_password = false;
        }
    }

    $('#lg_form').submit(function(event) {
        event.preventDefault();//使a自带的方法失效，即无法调整到href中的URL
       // check_user_name();
        //check_pwd();

        if(error_name == false && error_password == false)
        {
            var uname = $('#user_name').val();
            var upwd = $('#pwd').val();
            $.post('login', {
                'uname': uname,
                'upwd': upwd,
                'rm_name':error_check
            },function (data) {
                console.log(data);
                if (data == 1) {
                    $('#user_name').next().html('用户名或密码不能为空');
                    $('#user_name').next().show();
                } else if (data == 2) {
                    $('#user_name').next().html('用户不存在，请重新输入');
                    $('#user_name').next().show();
                } else if (data == 3) {
                    $(location).attr('href', 'index.jsp');
                }
            });


        }
        else
        {
            alert('请填写所有数据')
        }

    });


});