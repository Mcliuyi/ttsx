$(function(){

    var $add_x = $('#add_cart').offset().top;
    var $add_y = $('#add_cart').offset().left;

    var $to_x = $('#show_count').offset().top;
    var $to_y = $('#show_count').offset().left;

    var x = $to_x;
    var y = $to_y;


    $(".add_jump").css({'left':$add_y+80,'top':$add_x+10,'display':'block'});
    $('#add_cart').click(function(){
        console.log("$add_x: " + $add_x + " $add_y: " + $add_y + " $to_x: " + $to_x + " $to_y: " + $to_y);
        $(".add_jump").stop().animate({
                'left': y+7,
                'top': x+7},
            "fast", function() {
                $(".add_jump").fadeOut('fast',function(){
                    $('#show_count').html(parseInt($('#show_count').text()) + 1);
                    $(".add_jump").css({'left':$add_y+80,'top':$add_x+10,'display':'block'});

                });

            });

    });

    $("#add_cart").click(function () {
        var cid = $(this).attr("name");
        var num = $(".num_show").val();
        $.post('add', {
            'id': cid,
            'num':num
        },function (data) {
            console.log("data: " + data);
            if (data == "1") {
                console.log("商品添加成功")
            } else if (data == "2") {
                alert('网络异常请稍后再试！');

            }
        });
    });

    //增加数量
    $(".add").click(function () {

        var cid = $(this).attr("name");
        var num = $(".num_show").val();

        $(this).next().val(num + 1);
        num += 1;
        updatePrice($(this), num);
        var r = setCommodityNum(cid, num);
        console.log("r:"+ r)


    });

    //减少数量
    $(".minus").click(function () {
        total_num = 0;
        total_price = 0;
        var cid = $(this).parent().parent().parent().attr("id");
        var num = parseInt($(this).prev().val());
        if(num <= 0 ){
            alert("已经减到最小了，不能再减了！")
        }else {

            $(this).prev().val(num - 1);
            num -= 1;
            updatePrice($(this), num);
            setCommodityNum(cid, num );

        }


    });


    //更改商品小计
    function updatePrice(num) {
        var price = parseFloat($(".total").children().text());
        console.log("price"+price);
        price = parseFloat(price * num);
       // commodity.parent().parent().next().text(price.toFixed(2) + "元");
        $(".total").children().text(price.toFixed(2) + "元");
    }


    //发送请求修改后台数据
    function setCommodityNum(id, num) {

        $.post('cart', {
            'id': id,
            'num': num
        },function (data) {
            if (data == 2) {
                alert('网络异常请稍后再试！');
                return true;
            } else if (data == 1) {
                return false;
            }
        });

    }

});