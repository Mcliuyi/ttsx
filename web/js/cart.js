$(function(){

    //获取全部商品的选中框
    var selecteds = $("[name=checkbox]");
    var total_num = 0;
    var total_price = 0.0;
    var len = selecteds.length;


    //  全选 或者取消全选
    $("[name=allcheck]").click(function () {

            if($("[name=allcheck]").is(":checked")== true){
                console.log("选中");
                selecteds.prop("checked", true);
                //显示商品数量
                //1. 获取所有商品数量的对象
                CalculationNum();
                //2. 设置价格
                CalculationPrice();

            }else {
                console.log("未选中");
                selecteds.prop("checked", false);
                total_num = 0;
                total_price = 0;
                //将总价格设置为0
                $(".price").text(total_price);
                //将商品数量设置为0
                $('.snum').text(total_num);
            }

        }
    );


    //选择单个
    $("[name=checkbox]").click(function(){
        total_num = 0;
        total_price = 0.0;
        CalculationPrice();
        CalculationNum();
        var isAll = true;
        $.each($("[name=checkbox]"),function(i,item){
            if(item.checked != true) {
                isAll = false;
            }
        });

        if(isAll == true){
            $("[name=allcheck]").prop("checked", true);
        }else {
            $("[name=allcheck]").prop("checked", false);
        }


    });

    //购物车商品数量输入框失去焦点时
    $(".num_show").blur(function(){
        total_num = 0;
        total_price = 0.0;
        var num = $(this).val();
        var cid = $(this).parent().parent().parent().attr("id");
        CalculationNum();
        CalculationPrice();
        updatePrice($(this), num);
        setCommodityNum(cid, num);
    });

    //增加数量
    $(".add").click(function () {
        total_num = 0;
        total_price = 0;
        var cid = $(this).parent().parent().parent().attr("id");
        var num = parseInt($(this).next().val());

        $(this).next().val(num + 1);
        num += 1;
        updatePrice($(this), num);
        CalculationNum();
        CalculationPrice();
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
            CalculationNum();
            CalculationPrice();
            setCommodityNum(cid, num );

        }


    });

    //更改商品小计
    function updatePrice(commodity, num) {
        //更改商品小计
        var price = 0;
        price =  parseFloat(commodity.parent().parent().prev().text()) * num ;
        console.log("price"+price);
        commodity.parent().parent().next().text(price.toFixed(2) + "元");
    }



    //计算总价格并设置价格
    function CalculationPrice() {
        var selecteds = $("[name=checkbox]");
        var prices = $("[name=t_sprice] ");
        $.each(prices,function(i,item){
            if(selecteds[i].checked== true) {
                total_price += parseFloat(item.textContent);
            }
        });
        //设置总价格
        $(".price").text(total_price.toFixed(2) );
    }

    //计算商品数量并改变
    function CalculationNum() {
        var selecteds = $("[name=checkbox]");
        //1. 获取所有商品数量的对象
        var nums = $(".num_show ");
        //循环计算总数量
        $.each(nums,function(i,item){

            if(selecteds[i].checked== true){
                total_num += parseInt(item.value);
            }


        });
        //改变数量
        $('.snum').text(total_num);
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

    //删除
    $("[name=del]").click(function (e) {
        e.preventDefault(); //组织默认提交事件
        var cid = $(this).parent().parent().attr("id");
        $.post('del', {
            'id': cid
        },function (data) {
            console.log("data: " + data);
            if (data == "1") {
                //刷新当前页面
                window.location.href = "cart";
            } else if (data == "2") {
                alert('网络异常请稍后再试！');

            }
        });


    });


    //提交订单
    $(".place_order").click(function (e) {
        e.preventDefault();
        //获取所有已选中的商品
        var selectGoods = $('[name=checkbox]:checked');
        var arr = [];
        $.each(selectGoods, function (i, item) {
            var goodsObj = {};
            goodsObj.id = item.parentElement.parentElement.getAttribute("id");
            goodsObj.num = item.parentElement.parentElement.children[5].children[0].children[1].value;
            console.log("id"+ goodsObj.id);
            //console.log("num"+ goodsObj.num);
            arr.push(goodsObj);
        });
        console.log(arr);
        //发送post请求进行提交
        $.ajax(
            {
                type:"post",
                url : "settlement",
                timeout:3000,
                data:{
                    "goodsArr": JSON.stringify(arr)
                },
                success: function (data) {
                    if(data=="1"){
                        window.location.href = "place_order.jsp";
                    }

                },
                error: function () {
                    alert("请求出错");
                }

            });
    });


    
    
});