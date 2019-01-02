$(function(){


    $('#addr_form').submit(function(event) {
        event.preventDefault();//使a自带的方法失效，即无法调整到href中的URL

            var name = $('#name').val();
            var addr = $('#addr').val();
            var code =  $('#code').val();
            var phone = $('#phone').val();
            $.post('address', {
                'name': name,
                'addr': addr,
                'code':code,
                'phone':phone
            },function (data) {
                console.log(data);
                if (data == 1) {
                    alert('请填写所有数据');
                } else if (data == 2) {
                   alert("手机格式不对");
                } else if (data == 3) {
                    alert("修改成功");
                    window.location.reload();
                } else if (data == 4) {
                   alert("网络异常，请稍后再试");
                }
            });


    });


});