var produces;
$(document).ready(function () {
    initSelectOption();
    initTable();
});

function initTable() {
    var categoryId = $("#categoryId").find("option:selected").attr("value");
    var produceId = $("#produceId").find("option:selected").attr("value");
    var marketId = $("#marketId").find("option:selected").attr("value");
    var startDate =  $('#startDate').val();
    var endDate = $('#endDate').val();
    var keyWord = $('#keyWord').val();

    var table = $("#priceTable").DataTable({
        "colReorder": false,
        "bLengthChange": false, //开关，是否显示每页显示多少条数据的下拉框
        "aLengthMenu": [10, 50, 100, 500],  //更改显示记录数选项
        'bFilter': false,  //是否使用内置的过滤功能（是否去掉搜索框）
        "searching": false,//搜索框
        "bInfo": true, //开关，是否显示表格的一些信息(当前显示XX-XX条数据，共XX条)
        "bPaginate": true, //开关，是否显示分页器
        "destroy": true,    //初始化表格
        "pagingType": "full_numbers",   //分页样式
        "bSort": false,   //是否排序
        "processing": true, //是否显示加载
        "serverSide": true,//开启服务器处理模式
        "iDisplayLength": 15, //默认显示的记录数  
        "bDestroy": true,
        // "iDisplayStart":startPage,  //当开启分页的时候，定义展示的记录的起始序号，不是页数，因此如果你每个分页有10条记录而且想从第三页开始，需要把该参数指定为20
        "oLanguage": {  //语言转换
            "sProcessing": "处理中...",
            "sLengthMenu": "显示 _MENU_ 项结果",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索：",
            "sUrl": "",
            "sEmptyTable": "结果为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页"
            },
            "oAria": {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        },
        "ajax": {
            "url":contextPath+"/getShowListData.do",
            "data":function(d){
                return $.extend({},d,{
                    "categoryId":categoryId,
                    "produceId":produceId,
                    "marketId":marketId,
                    "startDate":startDate,
                    "endDate":endDate,
                    "keyWord":keyWord
                });
            }
        },
        "columns": [
            {
                "title": "序号",
                "className": "tc",
                "data": null,
                "width": "15%",
                "render": function(data, type, row,meta) {
                    return meta.settings._iDisplayStart + meta.row + 1;//行号
                }
            },
            {
                "title": "marketProduceId",
                "className": "tc",
                "data": "marketProduceId",
                "visible": false
            },
            {
                "title": "produceId",
                "className": "tc",
                "data": "produceId",
                "visible": false
            },
            {
                "title": "农产品",
                "className": "tc",
                "width": "15%",
                "data": "produceName",
                "render": function(data, type, row,meta) {
                    var marketProduceId = row.marketProduceId;
                    var produceId = row.produceId;
                    var url = contextPath+"/produceInfo.do?from=s&marketProduceId="+marketProduceId+"&produceId="+produceId;
                    return "<a href='"+url+"'>"+data+"</a>"
                }
            },
            {
                "title": "categoryId",
                "className": "tc",
                "data": "categoryId",
                "visible": false
            },
            {
                "title": "类别",
                "className": "tc",
                "width": "15%",
                "data": "categoryName"
            },
            {
                "title": "marketId",
                "className": "tc",
                "data": "marketId",
                "visible": false
            },
            {
                "title": "市场",
                "className": "tc",
                "width": "25%",
                "data": "marketName",
                "render": function(data, type, row,meta) {
                    var marketId = row.marketId;
                    var url = contextPath+"/marketProduceTable.do?from=s&marketId="+marketId;
                    return "<a href='"+url+"'>"+data+"</a>"
                }
            },{
                "title": "操作",
                "className": "tc",
                "width": "15%",
                "render": function(data, type, row,meta) {
                    var marketProduceId = row.marketProduceId;

                    return "<a class='edit-btn btn btn-primary btn-xs' onclick = \"deleteShow('"+marketProduceId+"')\" >删除</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class='del-btn btn btn-primary btn-xs' onclick = \"addFavorite('"+marketProduceId+"')\"  >收藏</a>"
                }
            }
        ]
    });
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


function initSelectOption() {
    $.ajax({
        //请求方式
        type:"GET",
        //发送请求的地址以及传输的数据
        async : false,
        data :{
            "inList":true
        },
        url:contextPath+"/getAllProduce.do",
        dataType:'json',
        success:function(result){
            produces = result;
        },
        error:function(){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
    $("#categoryId").change(function(){
        var categoryId=$("#categoryId").find("option:selected").attr("value");
        $("#produceId option").remove();
        $("#produceId").append("<option>-选择农产品-</option>");
        if(categoryId!=null&&produces!=null){
            for(i in produces){
                if(categoryId == produces[i].category){
                    $("#produceId").append("<option value ="+produces[i].id+">"+produces[i].name+"</option>");
                }
            }
        }
    });

    $(".search").click(function(){
        var table = $("#priceTable").DataTable();
        table.destroy();
        initTable();
    });

    $("#reset").click(function(){
         $("#categoryId").find("option:selected").removeAttr("selected");
        $("#produceId").find("option:selected").removeAttr("selected");
        $("#marketId").find("option:selected").removeAttr("selected");
        $('#startDate').val(new Date(new Date()-1000 * 60 * 60 * 24).format("yyyy-MM-dd"));
        $('#endDate').val(new Date().format("yyyy-MM-dd"));
        $('#keyWord').val("");
    });
}


function deleteShow(id) {
    if (confirm("确定要取消该展示？")) {
        $.ajax({
            type : "POST",
            async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url : contextPath+"/showOpt.do",
            data : {
                "marketProduceId":id,
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

        initTable();
    }
}

function addFavorite(id) {
    $.ajax({
        type : "POST",
        async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : contextPath+"/favoriteOpt.do",
        data : {
            "marketProduceId":id,
            "opt":"add"
        },
        dataType : "json",        //返回数据形式为json
        success : function(result) {
            console.log(result);
            if(result=="success"){
                alert("收藏成功")
            }else if(result=="none"){
                if (confirm("当前还未登陆，前往登陆？")) {
                    window.location.href = contextPath+"/user/toLoginPage.do";
                }
            }else if(result=="exist"){
                alert("收藏列表中已存在，请勿重复添加")
            }else{
                return;
            }
        },
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}

function logout() {
    if (confirm("退出登陆？")) {
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

}
