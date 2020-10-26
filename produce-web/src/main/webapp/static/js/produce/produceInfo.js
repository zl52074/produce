var mode = "rank";
var targetIndex = rankIndex;
$(document).ready(function () {
    initBarChart();
    initLineChart();
    initFavoriteButton();
    initShowButton();
    initSwitchButton();
});

function initBarChart() {
    var barChart = echarts.init(document.getElementById('barChart'));
    // 显示标题，图例和空的坐标轴
    barChart.setOption({
        title: {
            text: '当日市场价格',
            x:'center',
            y:'top'
        },
        tooltip: {},
        grid:{//直角坐标系内绘图网格
            bottom:"20%"
        },
        xAxis: {
            value:"category",
            name:"市场",
            data: [],
            axisLabel : {//坐标轴刻度标签的相关设置。
                clickable:true,//并给图表添加单击事件  根据返回值判断点击的是哪里
                interval : 0,
                formatter : function(params,index){
                    if (index % 2 != 0) {
                        return '\n\n' + params;
                    }
                    else {
                        return params;
                    }
                }
            }
        },
        yAxis: {
            type:"value",
            name:"价格(元/公斤)"
        },
        series: [{
            name: '价格',
            type: 'bar',
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            barWidth: '50px',
            data: [],
            itemStyle: {
                color: function (params) {
                    var key = params.dataIndex;
                    var index;
                    if(targetIndex<=6){
                        index = targetIndex-1;
                    }else{
                        index = 5;
                    }
                    if (key === index ) {
                        return '#c23531';
                    } else {
                        return '#428bca'
                    }
                }
            }
        }]
    });

    barChart.showLoading();    //数据加载完之前先显示一段简单的loading动画

    var market=[];    //类别数组（实际用来盛放X轴坐标值）
    var price=[];    //销量数组（实际用来盛放Y坐标值）

    $.ajax({
        type : "post",
        async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : contextPath+"/getBarChartData.do",    //请求发送到mapping处
        data : {
            "marketProduceId":marketProduceId,
            "date":date,
            "produceId":produceId,
            "mode":mode
        },
        dataType : "json",        //返回数据形式为json
        success : function(result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (result) {
                for(var i=0;i<result.length;i++){
                    market.push(result[i].market);    //挨个取出类别并填入类别数组
                }
                for(var i=0;i<result.length;i++){
                    price.push(result[i].price);    //挨个取出销量并填入销量数组
                }
                barChart.hideLoading();    //隐藏加载动画
                barChart.setOption({        //加载数据图表
                    xAxis: {
                        data: market
                    },
                    series: [{
                        data: price
                    }]
                });

            }

        },
        error : function(errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            barChart.hideLoading();
        }
    })

}



function initLineChart() {
    var dates = [];
    for(var i=29;i>=0;i--){
        dates[29-i] = new Date(new Date()-1000 * 3600 * 24 * i).format("yyyy-MM-dd")
    }
    var lineChart = echarts.init(document.getElementById('lineChart'));
    lineChart.clear();
    // 显示标题，图例和空的坐标轴
    lineChart.setOption({
        tooltip: {
            trigger: 'axis'
        },
        grid:{//直角坐标系内绘图网格
            left:"45px",
            right:"48px"
        },
        legend: {
            data: []
        },
        xAxis: {
            boundaryGap: false,
            type:"category",
            name:"日期",
            data: dates
        },
        yAxis: {
            min:'dataMin', //就是这两个 最小值
            max:'dataMax', //最大值
            type: 'value',
            name:"价格(元/公斤)"
        },
        series:[]
    });
    var market=[];    //销量数组（实际用来盛放Y坐标值）
    lineChart.showLoading();    //数据加载完之前先显示一段简单的loading动画
    $.ajax({
        type : "post",
        async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : contextPath+"/getLineChartData.do",    //请求发送到mapping处
        data : {
            "marketProduceId":marketProduceId,
            "date":date,
            "produceId":produceId,
            "mode":mode
        },
        dataType : "json",        //返回数据形式为json
        success : function(result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (result) {
                for(var i=0;i<result.length;i++){
                    market.push(result[i].name);    //挨个取出类别并填入类别数组
                }
                lineChart.hideLoading();    //隐藏加载动画
                lineChart.setOption({        //加载数据图表
                    legend: {
                        data: market
                    },
                    series: result
                });

            }

        },
        error : function(errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            lineChart.hideLoading();
        }
    })

}


Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

function initShowButton() {
    var showButton = $("#show");
    showButton.click(function(){
        if(showButton.hasClass("label-success")){
            $.ajax({
                type : "POST",
                async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                url : contextPath+"/showOpt.do",
                data : {
                    "marketProduceId":marketProduceId,
                    "produceId":produceId,
                    "opt":"add"
                },
                dataType : "json",        //返回数据形式为json
                success : function(result) {
                    console.log(result);
                    if(result=="success"){
                        showButton.removeClass("label-success").addClass("label-default");
                        showButton.html("已展示")
                    }else if(result=="overflow"){
                        alert("最多同时展示5个同类农产品，请取消部分展示再试")
                        return;
                    }else{
                        return;
                    }
                },
                error : function(e){
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
        }else if(showButton.hasClass("label-default")){
            $.ajax({
                type : "POST",
                async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                url : contextPath+"/showOpt.do",
                data : {
                    "marketProduceId":marketProduceId,
                    "opt":"delete"
                },
                dataType : "json",        //返回数据形式为json
                success : function(result) {
                },
                error : function(e){
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
            showButton.removeClass("label-default").addClass("label-success");
            showButton.html("展示")
        }
    });
}

function initFavoriteButton() {
    var favoriteButton = $("#favorite");
    favoriteButton.click(function(){
        if(favoriteButton.hasClass("label-success")){
            $.ajax({
                type : "POST",
                async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                url : contextPath+"/favoriteOpt.do",
                data : {
                    "marketProduceId":marketProduceId,
                    "opt":"add"
                },
                dataType : "json",        //返回数据形式为json
                success : function(result) {
                    console.log(result);
                    if(result=="success"){
                        favoriteButton.removeClass("label-success").addClass("label-default");
                        favoriteButton.html("已收藏")
                    }else if(result=="none"){
                        if (confirm("当前还未登陆，前往登陆？")) {
                            window.location.href = contextPath+"/user/toLoginPage.do";
                        }
                    }else{
                        return;
                    }
                },
                error : function(e){
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
        }else if(favoriteButton.hasClass("label-default")){
            $.ajax({
                type : "POST",
                async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                url : contextPath+"/favoriteOpt.do",
                data : {
                    "marketProduceId":marketProduceId,
                    "favoriteId":favoriteId,
                    "opt":"delete"
                },
                dataType : "json",        //返回数据形式为json
                success : function(result) {
                    console.log(result);
                    if(result=="success"){
                        favoriteButton.removeClass("label-default").addClass("label-success");
                        favoriteButton.html("收藏")
                    } else{
                        return;
                    }
                },
                error : function(e){
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
        }
    });
}

function initSwitchButton() {
    $("#switch").change(function () {
        var option = $("input[type='radio']:checked").val();
        if(option == "rank"){
            $("#title").html("<font style=\"font-size: 20px;\">当日低价排行</font>");
            $("#showDiv").attr("hidden","hidden");
            $("#rankDiv").removeAttr("hidden");
            mode="rank";
            targetIndex = rankIndex;
            initBarChart();
            initLineChart();
        }else if(option == "show"){
            $("#title").html("<font style=\"font-size: 20px;\">展示列表排行</font>");
            $("#rankDiv").attr("hidden","hidden");
            $("#showDiv").removeAttr("hidden");
            mode="show";
            targetIndex = showIndex;
            console.log(targetIndex);
            initBarChart();
            initLineChart();
        }

    });
}

function logout() {
    $.ajax({
        //请求方式
        type:"GET",
        //发送请求的地址以及传输的数据
        async : false,
        url:contextPath+"/user/logout.do",
        dataType:'json',
        success:function(result){
            location.reload(true);
        },
        error:function(){
        }
    });
}