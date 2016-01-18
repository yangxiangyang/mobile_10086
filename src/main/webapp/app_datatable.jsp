<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

 <link rel="stylesheet" type="text/css" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css"/>
    <!--<link rel="stylesheet" href="http://cdn.datatables.net/1.10.6/css/jquery.dataTables.min.css"/>-->
    <link rel="stylesheet" type="text/css"
          href="http://cdn.datatables.net/plug-ins/28e7751dbec/integration/bootstrap/3/dataTables.bootstrap.css"/>
    <script src="http://cdn.gbtags.com/datatables/1.10.5/js/jquery.js"></script>
    <script src="http://cdn.gbtags.com/datatables/1.10.5/js/jquery.dataTables.min.js"></script>
    <!--<script src="dt.js"></script>-->
    <script type="text/javascript" language="javascript"
            src="http://cdn.datatables.net/plug-ins/28e7751dbec/integration/bootstrap/3/dataTables.bootstrap.js"></script>

    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <script src="js/datatable/plugin/date/bootstrap-datetimepicker.min.js"></script>

    <script src="js/datatable/plugin/handlebars-v3.0.1.js"></script>

</head>
<body>
<div class="container">
    <table id="example" class="table table-striped table-bordered">
        <thead>
        <tr>
            <th>应用名称</th>
            <th>版本</th>
            <th>应用图标</th>
            <th>应用路径</th>
            <th>更新时间</th>
            <th>开发者</th>
            <th>应用类型</th>
            <th>价格</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody></tbody>
        <!-- tbody是必须的 -->
    </table>
</div>
<!-- Button trigger modal -->


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <input type="text" class="form-control" id="appname" placeholder="应用名称">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="version" placeholder="版本">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="appicon" placeholder="应用图标">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="apkurl" placeholder="应用路径">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="updatetime" placeholder="更新时间"
                    data-date-format="yyyy-MM-dd">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="developer" placeholder="开发者">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="apptype" placeholder="应用类型">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="price" placeholder="价格">
                </div>
                
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" id="initData">添加模拟数据</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="save">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<!--定义操作列按钮模板-->
<script id="tpl" type="text/x-handlebars-template">
    {{#each func}}
    <button type="button" class="btn btn-{{this.type}} btn-sm" onclick="{{this.fn}}">{{this.name}}</button>
    {{/each}}
</script>

<script>
var table;
var editFlag = false;
$(function () {

    $('#start_date').datetimepicker();

    var tpl = $("#tpl").html();
    //预编译模板
    var template = Handlebars.compile(tpl);

    table = $('#example').DataTable({
        ajax: {
            url: "<%=request.getContextPath()%>/app/tablelist.do"
        },
        serverSide: true,
        columns: [
            {"data": "appname"},
            {"data": "version"},
            {"data": "appicon"},
            {"data": "apkurl"},
            {"data": "updatetime"},
            {"data": "developer"},
            {"data": "apptype"},
            {"data": "price"},
            {"data": "null"}
        ],
        columnDefs: [
            {
                targets: 8,
                render: function (a, b, c, d) {
                    var context =
                    {
                        func: [
                            {"name": "修改", "fn": "edit(\'" + c.name + "\',\'" + c.position + "\',\'" + c.salary + "\',\'" + c.start_date + "\',\'" + c.office + "\',\'" + c.extn + "\')", "type": "primary"},
                            {"name": "删除", "fn": "del(\'" + c.name + "\')", "type": "danger"}
                        ]
                    };
                    var html = template(context);
                    return html;
                }
            }

        ],
        "language": {
            "lengthMenu": "_MENU_ 条记录每页",
            "zeroRecords": "没有找到记录",
            "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
            "infoEmpty": "无记录",
            "infoFiltered": "(从 _MAX_ 条记录过滤)",
            "paginate": {
                "previous": "上一页",
                "next": "下一页"
            }
        },
        "dom": "<'row'<'col-xs-2'l><'#mytool.col-xs-4'><'col-xs-6'f>r>" +
                "t" +
                "<'row'<'col-xs-6'i><'col-xs-6'p>>",
        initComplete: function () {
            $("#mytool").append('<button id="datainit" type="button" class="btn btn-primary btn-sm">增加基础数据</button>&nbsp');
            $("#mytool").append('<button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">添加</button>');
            $("#datainit").on("click", initData);
        }

    });

    $("#save").click(add);

    $("#initData").click(initSingleData);

});

/**
 * 初始化基础数据
 */
function initSingleData() {
}

/**
 * 添加数据
 **/
function add() {
    var addJson = {
        "name": $("#name").val(),
        "position": $("#position").val(),
        "salary": $("#salary").val(),
        "start_date": $("#start_date").val(),
        "office": $("#office").val(),
        "extn": $("#extn").val()
    };

    ajax(addJson);
}

</script>



