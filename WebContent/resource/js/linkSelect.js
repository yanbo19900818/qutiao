//将一个表单的数据返回成JSON对象
/*$.fn.serializeObject = function() {
  var o = {};
  var a = this.serializeArray();
  $.each(a, function() {
    if (o[this.name]) {
      if (!o[this.name].push) {
        o[this.name] = [ o[this.name] ];
      }
      o[this.name].push(this.value || '');
    } else {
      o[this.name] = this.value || '';
    }
  });
  return o;
};*/
$(document).ready(
    function() {
      var url ="/qutiao/cms/goods/modGroupList.do";
      var groupId = $("#selectGrId").val();
      var flag = 0;
      jQuery.ajax( {
        type : 'GET',
        contentType : 'application/json',
        url : url,
        dataType : 'json',
        success : function(data) {
          if (data && data.success == "true"){
        	  var html = "<option value = 0>请选择</option>";
            $.each(data.list, function(i, item) {
            	if(item.groupId == groupId){
            		flag = 1;
            		html += "<option value="+ item.groupId +" selected >" + item.moduleTitle + "</option>";
            	}else{
            		html += "<option value="+ item.groupId +">" + item.moduleTitle + "</option>";
            	}
            });
            $('#groupId').html(html).show();
            if(flag == 1){
            	$("#groupId").change();
            }
          }
        },
        error : function() {
          alert("error!***");
        }
      });
      
      $("#groupId").change(function() {
        var childId = $("#selectChildId").val();
        var url ="/qutiao/cms/goods/modChildList.do";
        jQuery.ajax( {
          type : 'POST',
          contentType : 'application/json',
          url : url,
          data:JSON.stringify({groupId:$("#groupId").val()}),
          dataType : 'json',
          success : function(data) {
        	  if (data && data.success == "true"){
            	  var html = "<option value = 0>请选择</option>";
                $.each(data.data, function(i, item) {
                	if(item.childId == childId){
                		html += "<option value="+ item.childId +" selected >" + item.moduleTitle + "</option>";
                	}else{
                		html += "<option value="+ item.childId +">" + item.moduleTitle + "</option>";
                	}
                });
                $('#childId').html(html).show();
              }
          },
          error : function(data) {
            alert("error");
          }
        });
      });
    });