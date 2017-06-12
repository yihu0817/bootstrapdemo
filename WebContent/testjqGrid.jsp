<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>Bootstrap 表格测试样例</title>
    <meta name="keywords" content="Bootstrap模版,Bootstrap模版下载,Bootstrap教程,Bootstrap中文"/>
    <meta name="description" content="站长素材提供Bootstrap模版,Bootstrap教程,Bootstrap中文翻译等相关Bootstrap插件下载"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- basic styles -->
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/assets/css/jquery-ui-1.10.3.custom.min.css"/>
    <link rel="stylesheet" href="/assets/css/ui.jqgrid.css"/>

    <script src="/assets/js/jquery-2.0.3.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/js/bootstrap-table.js"></script>

    <script src="/assets/js/jquery-ui-1.7.2.custom.min.js"></script>
    <script src="/assets/js/grid.locale-zh_CN.js"></script>
    <script src="/assets/js/jquery.jqGrid.min.js"></script>

</head>
<body class="container">
<table id="gridTable" class="table table-striped table-hover"></table>
<div id="gridPager"></div>


<div id="toolbar" class="btn-group">
    <button id="btn_add" type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
    </button>
    <button id="btn_edit" type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
    </button>
    <button id="btn_delete" type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
    </button>
</div>

<script>
    $(function () {
        var grid_selector = "#gridTable";
        var pager_selector = "#gridPager";
        $(grid_selector).jqGrid({
            url: 'jqGridServlet',
            datatype: "json",
            height: 250,
            colNames: ['编号', '用户名', '性别', '邮箱', 'QQ', '手机号', '出生日期'],
            colModel: [
                {name: 'id', index: 'id', sorttype: "int"},
                {name: 'userName', index: 'userName'},
                {name: 'gender', index: 'gender'},
                {name: 'email', index: 'email'},
                {name: 'QQ', index: 'QQ'},
                {name: 'mobilePhone', index: 'mobilePhone'},
                {name: 'birthday', index: 'birthday', sorttype: "date"}
            ],
            sortname: 'id',
            sortorder: 'asc',
            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            jsonReader: {repeatitems: false},
            pager: pager_selector,
            multiselect: true,
            //multikey: "ctrlKey",
            multiboxonly: true,
            editurl: "",//nothing is saved
            caption: "jqGrid与Servlet集成"
        }).jqGrid('navGrid',pager_selector,{});

    });

    //    $(function(){
    //        $("#gridTable").jqGrid({
    //            datatype: "local",
    //            height: 250,
    //            colNames:['编号','用户名', '性别', '邮箱', 'QQ','手机号','出生日期'],
    //            colModel:[
    //                {name:'id',index:'id', width:60, sorttype:"int"},
    //                {name:'userName',index:'userName', width:90},
    //                {name:'gender',index:'gender', width:90},
    //                {name:'email',index:'email', width:125,sorttype:"string"},
    //                {name:'QQ',index:'QQ', width:100},
    //                {name:'mobilePhone',index:'mobilePhone', width:120},
    //                {name:'birthday',index:'birthday', width:100, sorttype:"date"}
    //            ],
    //            sortname:'id',
    //            sortorder:'asc',
    //            viewrecords:true,
    //            rowNum:10,
    //            rowList:[10,20,30],
    //            pager:"#gridPager",
    //            caption: "第一个jqGrid例子"
    //        }).navGrid('#pager2',{edit:false,add:false,del:false});
    //        var mydata = [
    //            {id:"1",userName:"polaris",gender:"男",email:"fef@163.com",QQ:"33334444",mobilePhone:"13223423424",birthday:"1985-10-01"},
    //            {id:"2",userName:"李四",gender:"女",email:"faf@gmail.com",QQ:"222222222",mobilePhone:"13223423",birthday:"1986-07-01"},
    //            {id:"3",userName:"王五",gender:"男",email:"fae@163.com",QQ:"99999999",mobilePhone:"1322342342",birthday:"1985-10-01"},
    //            {id:"4",userName:"马六",gender:"女",email:"aaaa@gmail.com",QQ:"23333333",mobilePhone:"132234662",birthday:"1987-05-01"},
    //            {id:"5",userName:"赵钱",gender:"男",email:"4fja@gmail.com",QQ:"22222222",mobilePhone:"1343434662",birthday:"1982-10-01"},
    //            {id:"6",userName:"小毛",gender:"男",email:"ahfi@yahoo.com",QQ:"4333333",mobilePhone:"1328884662",birthday:"1987-12-01"},
    //            {id:"7",userName:"小李",gender:"女",email:"note@sina.com",QQ:"21122323",mobilePhone:"13220046620",birthday:"1985-10-01"},
    //            {id:"8",userName:"小三",gender:"男",email:"oefh@sohu.com",QQ:"242424366",mobilePhone:"1327734662",birthday:"1988-12-01"},
    //            {id:"9",userName:"孙先",gender:"男",email:"76454533@qq.com",QQ:"76454533",mobilePhone:"132290062",birthday:"1989-11-21"}
    //        ];
    //        for(var i=0;i<=mydata.length;i++)
    //            jQuery("#gridTable").jqGrid('addRowData',i+1,mydata[i]);
    //    });
</script>
</body>