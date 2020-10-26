var produces;
$(document).ready(function () {
    initTable();
    $(".search").click(function(){
        var table = $("#marketTable").DataTable();
        table.destroy();
        initTable();
    });
    $("#reset").click(function(){
        $("#provinceId").find("option:selected").removeAttr("selected");
        $("#marketType").find("option:selected").removeAttr("selected");
        $('#keyWord').val("");
    });
});

function initTable() {
    var provinceId = $("#provinceId").find("option:selected").attr("value");
    var marketType = $("#marketType").find("option:selected").attr("value");
    var keyWord = $('#keyWord').val();

    var table = $("#marketTable").DataTable({
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
            "url":contextPath+"/getMarketData.do",
            "data":function(d){
                return $.extend({},d,{
                    "provinceId":provinceId,
                    "marketType":marketType,
                    "keyWord":keyWord
                });
            }
        },
        "columns": [
            {
                "title": "序号",
                "className": "tc",
                "data": null,
                "width": "10%",
                "render": function(data, type, row,meta) {
                    return meta.settings._iDisplayStart + meta.row + 1;//行号
                }
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
                // "width": "15%",
                "data": "marketName",
                "render": function(data, type, row,meta) {
                    var marketId = row.marketId;
                    var url = contextPath+"/marketProduceTable.do?from=m&marketId="+marketId;
                    return "<a href='"+url+"'>"+data+"</a>"
                }
            },
            {
                "title": "销售类型",
                "className": "tc",
                "width": "10%",
                "data": "marketType"
            },
            {
                "title": "省份",
                "className": "tc",
                "width": "15%",
                "data": "provinceName"
            },
            {
                "title": "地址",
                "className": "tc",
                // "width": "15%",
                "data": "marketAddress"
            }
        ]
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
